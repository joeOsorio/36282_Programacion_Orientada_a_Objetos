<#
****************************************************************
 Compila y ejecuta una practica de POO (Windows / PowerShell)

 Uso:
   .\compilar.ps1 <carpeta_practica> [-Main <Clase>] [-SoloCompilar]

 Ejemplos (desde 1_Practicas):
   .\compilar.ps1 Lab04_Composicion_Cajero
   .\compilar.ps1 Lab01_Clases_AlumnoLibro -Main Principal2
   .\compilar.ps1 Taller05_Paquetes_Genericos -Main app.Main

 - Compila TODO src\ (incluye subcarpetas / paquetes) hacia output\
 - Usa UTF-8 para leer el codigo y para la consola (n con tilde, acentos)
 - Si no le pasas -Main, busca la clase que tenga main()
 - Funciona con rutas con espacios (OneDrive)
****************************************************************
#>
param(
    [Parameter(Position = 0)][string]$Practica = ".",
    [string]$Main = "",
    [switch]$SoloCompilar
)

# Continue: en PowerShell 5.1 los mensajes de javac por stderr no deben abortar el script
$ErrorActionPreference = "Continue"

# --- Consola en UTF-8 (equivale a chcp 65001) ---
chcp 65001 > $null
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
[Console]::InputEncoding  = [System.Text.Encoding]::UTF8
$OutputEncoding           = [System.Text.Encoding]::UTF8

# --- Resolver la carpeta (relativa a donde estas o a este script) ---
if (Test-Path (Join-Path $Practica "src")) {
    $dir = (Resolve-Path $Practica).Path
} elseif (Test-Path (Join-Path (Join-Path $PSScriptRoot $Practica) "src")) {
    $dir = (Resolve-Path (Join-Path $PSScriptRoot $Practica)).Path
} else {
    Write-Host "Error: no existe la carpeta src\ en '$Practica'" -ForegroundColor Red
    exit 1
}

$src     = Join-Path $dir "src"
$out     = Join-Path $dir "output"
$nombre  = Split-Path $dir -Leaf
$fuentes = @(Get-ChildItem -Path $src -Recurse -Filter *.java | ForEach-Object { $_.FullName })

if ($fuentes.Count -eq 0) {
    Write-Host "Error: no hay archivos .java en $src" -ForegroundColor Red
    exit 1
}

# --- Compilar ---
if (Test-Path $out) { Remove-Item $out -Recurse -Force }
Write-Host "Compilando $($fuentes.Count) archivos .java de $nombre\src ..." -ForegroundColor Cyan
# Cada ruta se pasa como argumento separado (no importa que tenga espacios)
& javac -encoding UTF-8 -d $out $fuentes
if ($LASTEXITCODE -ne 0) {
    Write-Host "Error de compilacion en $nombre" -ForegroundColor Red
    exit $LASTEXITCODE
}
Write-Host "Compilacion exitosa -> $nombre\output" -ForegroundColor Green

if ($SoloCompilar) { exit 0 }

# --- Detectar la clase principal (con paquete, si tiene) ---
if (-not $Main) {
    $candidatos = @()
    foreach ($f in Get-ChildItem -Path $src -Recurse -Filter *.java) {
        $texto = Get-Content -Path $f.FullName -Raw -Encoding UTF8
        if ($texto -match 'void\s+main\s*\(') {
            $paquete = ""
            if ($texto -match '(?m)^\s*package\s+([\w\.]+)\s*;') { $paquete = $Matches[1] + "." }
            $candidatos += ($paquete + $f.BaseName)
        }
    }
    $conMain = @($candidatos | Where-Object { $_ -match '(^|\.)Main$' })
    if ($conMain.Count -ge 1)        { $Main = $conMain[0] }
    elseif ($candidatos.Count -eq 1) { $Main = $candidatos[0] }
    else {
        Write-Host "Hay varias clases con main(), elige una con -Main:" -ForegroundColor Yellow
        $candidatos | ForEach-Object { Write-Host "  $_" }
        exit 1
    }
}

# --- Ejecutar ---
Write-Host "Ejecutando $Main" -ForegroundColor Cyan
Write-Host "----------------------------------------------------------------"
# Los -D van entre comillas: PowerShell parte los argumentos que tienen punto
& java "-Dstdout.encoding=UTF-8" "-Dstderr.encoding=UTF-8" -cp $out $Main
