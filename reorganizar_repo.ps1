<#
****************************************************************
 Reorganizacion del repo 36282_Programacion_Orientada_a_Objetos

 Uso (desde la raiz del repo):
   .\reorganizar_repo.ps1              -> SIMULACION: solo muestra lo que haria
   .\reorganizar_repo.ps1 -Ejecutar    -> aplica los cambios

 Antes de ejecutar:
   1. Haz commit de lo que tengas pendiente (git status limpio)
   2. Cierra VS Code y pausa OneDrive (bloquean carpetas al renombrar)

 Que hace (en orden):
   1. Borra compilados (.class, output/, build/, bin/, .DS_Store)
   2. Borra duplicados y sobrantes (los duplicados se verifican por hash)
   3. Renombra la descripcion de cada practica a docs/Instrucciones.md
   4. Reacomoda casos especiales (referencias del profe, borradores, extras)
   5. Renombra las carpetas de las practicas (Tipo##_Tema_Problema)
   6. Reorganiza 0_Recursos (encuadre, presentaciones, libros, plantillas, apps)
      y limpia .vscode\launch.json
   7. Borra carpetas vacias
   8. Vuelve a indexar en git (respeta mayusculas y el .gitignore nuevo)
   9. Compila todas las practicas para verificar que nada se rompio
****************************************************************
#>
param([switch]$Ejecutar)

$ErrorActionPreference = "Stop"
$raiz = $PSScriptRoot
Set-Location $raiz

if (-not (Test-Path (Join-Path $raiz "1_Practicas")) -or -not (Test-Path (Join-Path $raiz "0_Recursos"))) {
    Write-Host "Error: corre este script desde la raiz del repo (donde estan 0_Recursos y 1_Practicas)." -ForegroundColor Red
    exit 1
}

$modo = if ($Ejecutar) { "EJECUTANDO" } else { "SIMULACION (no se cambia nada; usa -Ejecutar para aplicar)" }
Write-Host "`n=== Reorganizacion del repo: $modo ===`n" -ForegroundColor Cyan

$script:acciones = 0
$script:avisos   = @()

# ---------------------------------------------------------------- helpers
function Rel($p) { return $p.Replace($raiz + [IO.Path]::DirectorySeparatorChar, "") }

function Paso($titulo) { Write-Host "`n--- $titulo ---" -ForegroundColor Cyan }

function Borrar($ruta) {
    $p = Join-Path $raiz $ruta
    if (-not (Test-Path -LiteralPath $p)) { return }
    Write-Host "  [borrar] $ruta" -ForegroundColor Red
    $script:acciones++
    if ($Ejecutar) { Remove-Item -LiteralPath $p -Recurse -Force }
}

# Borra $ruta solo si su contenido es identico a $referencia
function BorrarSiIgual($ruta, $referencia) {
    $p = Join-Path $raiz $ruta
    $r = Join-Path $raiz $referencia
    if (-not (Test-Path -LiteralPath $p)) { return }
    if (-not (Test-Path -LiteralPath $r)) {
        $script:avisos += "No se borro $ruta : no existe la referencia $referencia"
        return
    }
    $h1 = (Get-FileHash -LiteralPath $p -Algorithm SHA256).Hash
    $h2 = (Get-FileHash -LiteralPath $r -Algorithm SHA256).Hash
    if ($h1 -eq $h2) {
        Write-Host "  [borrar] $ruta  (identico a $referencia)" -ForegroundColor Red
        $script:acciones++
        if ($Ejecutar) { Remove-Item -LiteralPath $p -Force }
    } else {
        $script:avisos += "No se borro $ruta : ya NO es identico a $referencia (revisalo a mano)"
    }
}

# Mueve/renombra. Soporta cambios solo de mayusculas (Docs -> docs) en Windows.
function Mover($de, $a) {
    $pDe = Join-Path $raiz $de
    $pA  = Join-Path $raiz $a
    if (-not (Test-Path -LiteralPath $pDe)) { return }
    $soloMayusculas = ($pDe -ieq $pA) -and ($pDe -cne $pA)
    if ((Test-Path -LiteralPath $pA) -and -not $soloMayusculas) {
        $script:avisos += "No se movio $de : ya existe $a"
        return
    }
    Write-Host "  [mover]  $de" -ForegroundColor Yellow
    Write-Host "        -> $a" -ForegroundColor Green
    $script:acciones++
    if (-not $Ejecutar) { return }
    $padre = Split-Path $pA -Parent
    if (-not (Test-Path -LiteralPath $padre)) { New-Item -ItemType Directory -Path $padre -Force | Out-Null }
    if ($soloMayusculas) {
        $tmp = "$pA.__tmp__"
        Move-Item -LiteralPath $pDe -Destination $tmp
        Move-Item -LiteralPath $tmp -Destination $pA
    } else {
        Move-Item -LiteralPath $pDe -Destination $pA
    }
}

$P = "1_Practicas"
$R = "0_Recursos"

# ================================================================ 1
Paso "1. Compilados y basura del sistema"
$excluir = [regex]::Escape((Join-Path $raiz ".git"))
$compilados = @(Get-ChildItem -Path (Join-Path $raiz $P) -Recurse -Directory -Force |
    Where-Object { $_.Name -in @("output", "build", "bin") } |
    Sort-Object { $_.FullName.Length })
foreach ($c in $compilados) { if (Test-Path -LiteralPath $c.FullName) { Borrar (Rel $c.FullName) } }
$basura = @(Get-ChildItem -Path $raiz -Recurse -File -Force -Include *.class, .DS_Store, Thumbs.db |
    Where-Object { $_.FullName -notmatch $excluir })
foreach ($b in $basura) { if (Test-Path -LiteralPath $b.FullName) { Borrar (Rel $b.FullName) } }

# ================================================================ 2
Paso "2. Duplicados y sobrantes"
# Plantillas: donde ya hay reporte propio, la Plantilla.docx sobra
BorrarSiIgual "$P\05_Descripcion\docs\Plantilla.docx" "$R\Plantillas\Plantilla.docx"
BorrarSiIgual "$P\03_Descripcion\docs\Plantilla.docx" "$P\02_Descripcion\docs\Plantilla.docx"
Borrar        "$P\04_Descripcion\docs\Plantilla.docx"   # ya existe Practica_4_POO.docx
# En el Lab 2 la Plantilla.docx ES el reporte (no hay otro docx): solo se renombra
Mover "$P\02_Descripcion\docs\Plantilla.docx" "$P\02_Descripcion\docs\Reporte_Lab2_JoshuaOsorio.docx"

# Copia por conflicto de OneDrive (mismo texto que Reporte_Laboratorio5.pdf)
Borrar "$P\05_Descripcion\docs\Reporte_Laboratorio5-AORUS-JOE.pdf"
# El diagrama del Lab 2 era una copia del UML del Lab 1 (ya hay docs\UML.drawio nuevo)
BorrarSiIgual "$P\02_Descripcion\docs\diagrama.drawio" "$P\01_Descripcion\docs\UML.drawio"
# UML del Lab 6 sin extension
Mover "$P\06_Descripcion\docs\UML" "$P\06_Descripcion\docs\UML.drawio"

# Comprimidos de entrega
Borrar "$P\05_Descripcion\src\Practica5.zip"
Borrar "$P\12_InterfacesYPolimorfismo\src\src.rar"
Borrar "$P\Taller5\listasEnlazadas.zip"
Borrar "$P\Taller5\motor_reglas_negocio.zip"

# Versiones viejas, esqueletos vacios y pruebas sueltas
Borrar "$P\06_Descripcion\src\Prueba"            # Main copy.java (version vieja)
Borrar "$P\03_Descripcion\src\ejemplos"          # Circulo/Rectangulo vacios
Borrar "$P\11_Interfaces\src\ejemplo"            # otra version de la practica
Borrar "$P\02_Descripcion\src\Cal2.java"         # prueba suelta
Borrar "$P\Taller5\nota.md"                      # vacio
Borrar "$P\Taller5\src\Main.java"                # reemplazado por src\app\Main.java

# compilar.sh por practica -> ahora hay uno solo en 1_Practicas
Borrar "$P\08_Descripcion\compilar.sh"
Borrar "$P\11_Interfaces\compilar.sh"
Borrar "$P\12_InterfacesYPolimorfismo\compilar.sh"

# Conversiones temporales PDF -> Markdown
Borrar "$R\markit_output"

# ================================================================ 3
Paso "3. Descripcion de cada practica -> docs\Instrucciones.md (contenido intacto)"
Mover "$P\01_Descripcion\docs\notas.md"            "$P\01_Descripcion\docs\Instrucciones.md"
Mover "$P\02_Descripcion\docs\notas.md"            "$P\02_Descripcion\docs\Instrucciones.md"
Mover "$P\03_Descripcion\docs\Descripcion.md"      "$P\03_Descripcion\docs\Instrucciones.md"
Mover "$P\04_Descripcion\docs\Descripcion.md"      "$P\04_Descripcion\docs\Instrucciones.md"
Mover "$P\05_Descripcion\docs\Descripcion.md"      "$P\05_Descripcion\docs\Instrucciones.md"
Mover "$P\06_Descripcion\docs\Descripcion.md"      "$P\06_Descripcion\docs\Instrucciones.md"
Mover "$P\07_Descripcion\docs\notas.md"            "$P\07_Descripcion\docs\Instrucciones.md"
# 08 ya se llama Instrucciones.md
Mover "$P\09_Descripcion\docs\Descripcion.md"      "$P\09_Descripcion\docs\Instrucciones.md"
Mover "$P\10_HerenciaPolimorfismo\docs\Descripcion.md"   "$P\10_HerenciaPolimorfismo\docs\Instrucciones.md"
Mover "$P\11_Interfaces\docs\Descripcion.md"              "$P\11_Interfaces\docs\Instrucciones.md"
Mover "$P\12_InterfacesYPolimorfismo\docs\Descripcion.md" "$P\12_InterfacesYPolimorfismo\docs\Instrucciones.md"
Mover "$P\Taller1\docs\Descripcion.md"                    "$P\Taller1\docs\Instrucciones.md"
Mover "$P\Taller2_interfaces_herencia_polimorfismo\docs\Descripcion.md" "$P\Taller2_interfaces_herencia_polimorfismo\docs\Instrucciones.md"
Mover "$P\Taller3_Interfaces\docs\Descripcion.md"         "$P\Taller3_Interfaces\docs\Instrucciones.md"
Mover "$P\Taller4_InteracesYpolimorfismo\Docs\Descripcion.md" "$P\Taller4_InteracesYpolimorfismo\Docs\Instrucciones.md"

# ================================================================ 4
Paso "4. Casos especiales"
# Taller 4: Docs -> docs
Mover "$P\Taller4_InteracesYpolimorfismo\Docs" "$P\Taller4_InteracesYpolimorfismo\docs"
# Borradores de diagramas
Mover "$P\Taller2_interfaces_herencia_polimorfismo\docs\UML_borrador.drawio" "$P\Taller2_interfaces_herencia_polimorfismo\docs\borradores\UML_borrador.drawio"
Mover "$P\Taller2_interfaces_herencia_polimorfismo\docs\UML_v2.drawio"       "$P\Taller2_interfaces_herencia_polimorfismo\docs\borradores\UML_v2.drawio"
Mover "$P\Taller4_InteracesYpolimorfismo\docs\UML_borrador.drawio"           "$P\Taller4_InteracesYpolimorfismo\docs\borradores\UML_borrador.drawio"
# Implementaciones del profe: fuera de src (no se compilan, quedan de referencia)
Mover "$P\05_Descripcion\src\ImplementacionProfesor" "$P\05_Descripcion\referencia_profesor\ImplementacionProfesor"
Mover "$P\Taller5\motor_reglas_negocio\motor_reglas_negocio\src"               "$P\Taller5\referencia_profesor\motor_reglas_negocio\src"
Mover "$P\Taller5\motor_reglas_negocio\motor_reglas_negocio\como_compilar.txt" "$P\Taller5\referencia_profesor\motor_reglas_negocio\como_compilar.txt"
Borrar "$P\Taller5\motor_reglas_negocio"
# Taller 5: nodo.java -> Nodo.java (el nombre del archivo debe coincidir con la clase)
Mover "$P\Taller5\src\utils\nodo.java" "$P\Taller5\src\utils\Nodo.java"
# Ejercicio complementario de la Libreria (Lab 4): fuera de src
Mover "$P\04_Descripcion\src\Prueba\Descripcion.md" "$P\04_Descripcion\extra_EjercicioLibreria\Instrucciones.md"
Mover "$P\04_Descripcion\src\Prueba"                "$P\04_Descripcion\extra_EjercicioLibreria\src"
# Practica de examen: codigo dentro de src
Mover "$P\PracticaExamen2\Main.java"          "$P\PracticaExamen2\src\Main.java"
Mover "$P\PracticaExamen2\IMostrarOctal.java" "$P\PracticaExamen2\src\IMostrarOctal.java"

# ================================================================ 5
Paso "5. Nombres de las practicas"
$practicas = [ordered]@{
    "01_Descripcion"                           = "01_Clases_AlumnoLibro"
    "02_Descripcion"                           = "02_Clases_FigurasCalculadora"
    "03_Descripcion"                           = "03_Clases_Veterinaria"
    "04_Descripcion"                           = "04_Composicion_Cajero"
    "05_Descripcion"                           = "05_Composicion_EquiposRRHH"
    "06_Descripcion"                           = "06_Composicion_VeterinariaDoctores"
    "07_Descripcion"                           = "07_Composicion_Banco"
    "08_Descripcion"                           = "08_Composicion_CartasGoFish"
    "09_Descripcion"                           = "Taller07_Herencia_VeterinariaDispatch"
    "10_HerenciaPolimorfismo"                  = "10_Polimorfismo_FechasExportador"
    "11_Interfaces"                            = "11_Interfaces_OcultarValidar"
    "12_InterfacesYPolimorfismo"               = "12_InterfacesPolimorfismo_Resenas"
    "Taller1"                                  = "Taller01_HerenciaPolimorfismo_Microbios"
    "Taller2_interfaces_herencia_polimorfismo" = "Taller02_Interfaces_Restaurantes"
    "Taller3_Interfaces"                       = "Taller03_Interfaces_ConversorBases"
    "Taller4_InteracesYpolimorfismo"           = "Taller04_Interfaces_ReglasNegocio"
    "Taller5"                                  = "Taller05_Paquetes_Genericos"
    "PracticaExamen2"                          = "Extra_PracticaExamen2"
}
foreach ($k in $practicas.Keys) { Mover "$P\$k" "$P\$($practicas[$k])" }

# ================================================================ 6
Paso "6. 0_Recursos"
$E = "$R\00_Encuadre"; $D = "$R\01_Presentaciones"; $L = "$R\02_Libros"
Mover "$R\2026_1_Formato_Encuadre_FCQI_V2.pdf"      "$E\Encuadre_2026-2_FCQI.pdf"
Mover "$R\ANEXO_Plan de Actividades_OOP_ 2026-2.pdf" "$E\Anexo_Plan_Actividades_2026-2.pdf"
Mover "$R\U0_instalacion.pdf"              "$D\01_Instalacion_JDK.pdf"
Mover "$R\U0_Repaso_Estructurada.pdf"      "$D\02_Repaso_Programacion_Estructurada.pdf"
Mover "$R\U1_paradigmaOO.pdf"              "$D\03_Paradigma_Orientado_a_Objetos.pdf"
Mover "$R\U2_Clases_part1.pdf"             "$D\04_Clases_y_Objetos.pdf"
Mover "$R\U2_Herencia_Y_Polimorfismo.pdf"  "$D\05_Herencia_y_Polimorfismo.pdf"
Mover "$R\Interfaces.pdf"                  "$D\06_Interfaces.pdf"
Mover "$R\clases_abstractas.pdf"           "$D\07_Clases_Abstractas.pdf"
Mover "$R\genericos.pdf"                   "$D\08_Genericos.pdf"
Mover "$R\cohesion_acomplamiento.pdf"      "$D\09_Cohesion_y_Acoplamiento.pdf"
Mover "$R\Libros\Starting Out with Java_ From Control Structures Through Objects.pdf" "$L\Gaddis_Starting_Out_with_Java.pdf"
Mover "$R\Libros\The Object-Oriented Thought Process.pdf"  "$L\Weisfeld_The_Object-Oriented_Thought_Process.pdf"
Mover "$R\Libros\Java_ The Complete Reference.pdf"         "$L\Schildt_Java_The_Complete_Reference.pdf"
Mover "$R\Libros\Core Java Volume I Fundamentals.pdf"      "$L\Horstmann_Core_Java_Vol_I_Fundamentals.pdf"
Mover "$R\Libros\UML2.5.1.pdf"                             "$L\OMG_UML_2.5.1_Especificacion.pdf"
Mover "$R\Plantillas\Plantilla.docx" "$R\03_Plantillas\Plantilla_Reporte_Practica.docx"
Mover "$R\Apps" "$R\04_Apps"

# ================================================================ 6b
Paso "6b. .vscode\launch.json (quitar configuraciones viejas)"
$launch = @'
{
  // Configuraciones de depuracion (F5) para las practicas.
  // Se quitaron "Main", "ProgramaLibreria" y "Veterinaria": apuntaban a
  // projectName con hash viejos (36282_..._21dc808a / _cc8e42b5) que VS Code
  // genero automaticamente y ya no corresponden a ninguna carpeta.
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Ejecutar archivo actual",
      "request": "launch",
      "mainClass": "${file}",
      "console": "integratedTerminal"
    },
    {
      "type": "java",
      "name": "Depurar archivo actual (output)",
      "request": "launch",
      "mainClass": "${fileBasenameNoExtension}",
      "classPaths": ["${fileDirname}/../output"],
      "preLaunchTask": "Compilar Java (output)",
      "console": "integratedTerminal"
    }
  ]
}
'@
Write-Host "  [reescribir] .vscode\launch.json  (se quitan Main/ProgramaLibreria/Veterinaria con projectName viejo)" -ForegroundColor Yellow
$script:acciones++
if ($Ejecutar) { Set-Content -LiteralPath (Join-Path $raiz ".vscode\launch.json") -Value $launch -Encoding UTF8 }

# ================================================================ 7
Paso "7. Carpetas vacias"
if ($Ejecutar) {
    do {
        $vacias = @(Get-ChildItem -Path $raiz -Recurse -Directory -Force |
            Where-Object { $_.FullName -notmatch $excluir -and -not (Get-ChildItem -LiteralPath $_.FullName -Force) })
        foreach ($v in $vacias) { Write-Host "  [borrar] $(Rel $v.FullName)\  (vacia)" -ForegroundColor Red; Remove-Item -LiteralPath $v.FullName -Force }
    } while ($vacias.Count -gt 0)
} else {
    Write-Host "  (se borran al ejecutar: Libros\, Plantillas\, src\ vacios, Capturas\ vacias, etc.)"
}

# ================================================================ 8
Paso "8. Git"
if ($Ejecutar -and (Get-Command git -ErrorAction SilentlyContinue) -and (Test-Path (Join-Path $raiz ".git"))) {
    # Quita todo del indice y lo vuelve a agregar: asi git registra los cambios de
    # mayusculas (Docs -> docs, nodo -> Nodo) y deja de seguir lo que ahora ignora el .gitignore
    git rm -r --cached -q .
    git add -A
    Write-Host "  Cambios listos en git (sin commit). Revisa con: git status" -ForegroundColor Green
} else {
    Write-Host "  (al ejecutar: git rm -r --cached . ; git add -A)"
}

# ================================================================ 9
Paso "9. Verificacion: compilar todas las practicas"
if ($Ejecutar) {
    $compilar = Join-Path $raiz "$P\compilar.ps1"
    foreach ($d in Get-ChildItem -Path (Join-Path $raiz $P) -Directory) {
        if (-not (Test-Path (Join-Path $d.FullName "src"))) { continue }
        $ok = $false
        try { & $compilar $d.FullName -SoloCompilar *> $null; $ok = ($LASTEXITCODE -eq 0) } catch { $ok = $false }
        if ($ok) { Write-Host "  [OK]    $($d.Name)" -ForegroundColor Green }
        else                     { Write-Host "  [ERROR] $($d.Name)  -> .\1_Practicas\compilar.ps1 $($d.Name)" -ForegroundColor Red }
        $o = Join-Path $d.FullName "output"
        if (Test-Path $o) { Remove-Item $o -Recurse -Force }
    }
} else {
    Write-Host "  (al ejecutar se compila cada practica con 1_Practicas\compilar.ps1 -SoloCompilar)"
}

# ================================================================ resumen
Write-Host "`n=== $($script:acciones) acciones ($modo) ===" -ForegroundColor Cyan
if ($script:avisos.Count -gt 0) {
    Write-Host "`nAvisos:" -ForegroundColor Yellow
    $script:avisos | ForEach-Object { Write-Host "  - $_" -ForegroundColor Yellow }
}
if ($Ejecutar) {
    Write-Host "`nSiguiente paso: git status  ->  git commit -m `"Reorganizacion del repo`"" -ForegroundColor Cyan
    Write-Host "Este script ya no se necesita: puedes borrarlo despues del commit." -ForegroundColor DarkGray
}
