#!/usr/bin/env bash
# ****************************************************************
# Compila y ejecuta una practica de POO (Linux / Git Bash / macOS)
#
# Uso:
#   ./compilar.sh <carpeta_practica> [ClasePrincipal] [--solo-compilar]
#
# Ejemplos:
#   ./compilar.sh Lab04_Composicion_Cajero
#   ./compilar.sh Lab01_Clases_AlumnoLibro Principal2
#   ./compilar.sh Taller05_Paquetes_Genericos app.Main
#
# - Compila TODO src/ (incluye subcarpetas / paquetes) hacia output/
# - Usa UTF-8 para leer el codigo y para imprimir (n con tilde, acentos)
# - Si no le pasas la clase principal, busca la que tenga main()
# ****************************************************************
set -euo pipefail

solo_compilar=0
args=()
for a in "$@"; do
    if [ "$a" = "--solo-compilar" ]; then solo_compilar=1; else args+=("$a"); fi
done

dir="${args[0]:-.}"
main="${args[1]:-}"
script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Acepta la ruta relativa a donde estas o al folder de este script
if [ ! -d "$dir/src" ] && [ -d "$script_dir/$dir/src" ]; then
    dir="$script_dir/$dir"
fi
if [ ! -d "$dir/src" ]; then
    echo "Error: no existe la carpeta src/ en '$dir'"
    exit 1
fi

src="$dir/src"
out="$dir/output"

total=$(find "$src" -name '*.java' | wc -l)
if [ "$total" -eq 0 ]; then
    echo "Error: no hay archivos .java en $src"
    exit 1
fi

rm -rf "$out"
echo "Compilando $total archivos .java de $(basename "$dir")/src ..."
# -print0 / -0 para que funcione aunque la ruta tenga espacios (OneDrive)
find "$src" -name '*.java' -print0 | xargs -0 javac -encoding UTF-8 -d "$out"
echo "Compilacion exitosa -> $(basename "$dir")/output"

[ "$solo_compilar" -eq 1 ] && exit 0

# Detectar la clase principal (con paquete, si tiene)
if [ -z "$main" ]; then
    candidatos=()
    while IFS= read -r f; do
        pkg=$(sed -nE 's/^[[:space:]]*package[[:space:]]+([A-Za-z0-9_.]+)[[:space:]]*;.*/\1./p' "$f" | head -1)
        candidatos+=("${pkg}$(basename "$f" .java)")
    done < <(grep -rlE 'void[[:space:]]+main[[:space:]]*\(' --include='*.java' "$src" || true)

    for c in "${candidatos[@]}"; do
        case "$c" in Main|*.Main) main="$c"; break ;; esac
    done
    if [ -z "$main" ] && [ "${#candidatos[@]}" -eq 1 ]; then
        main="${candidatos[0]}"
    fi
    if [ -z "$main" ]; then
        echo "Hay varias clases con main(), elige una:"
        printf '  %s\n' "${candidatos[@]}"
        echo "Ejemplo: ./compilar.sh $(basename "$dir") ${candidatos[0]:-Main}"
        exit 1
    fi
fi

echo "Ejecutando $main"
echo "----------------------------------------------------------------"
java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "$out" "$main"
