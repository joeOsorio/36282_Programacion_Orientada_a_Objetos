# Laboratorio 1 — Clases pt1: Alumnos y Libros

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio + Taller 1 (diagrama) | U1 Abstracción y encapsulamiento | Clases, atributos privados, setters/getters, arreglos de objetos |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Dos programas. **Problema 1:** capturar nombre, matrícula y 3 calificaciones de 3 alumnos y mostrar una tabla con su promedio. **Problema 2:** capturar al menos 3 libros (título, autor, año, páginas, editorial) y buscar uno por título para mostrar su autor y número de páginas. Las clases van **sin constructor**, solo con setters y getters.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Alumno` | Nombre, apellidos, matrícula y 3 calificaciones (privados) con setters/getters |
| `Libro` | Título, autor, editorial, año y páginas (privados) con setters/getters |
| `Principal1` | `main` del problema 1: arreglo `Alumno[3]`, captura y tabla de promedios |
| `Principal2` | `main` del problema 2: arreglo `Libro[3]`, captura y búsqueda por título |
| `HolaMundo` | Primer programa para probar la instalación del JDK |

Relaciones: `Principal1` usa un arreglo de `Alumno` y `Principal2` uno de `Libro` (dependencias simples: aún no hay composición entre clases de dominio).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab01_Clases_AlumnoLibro -Main Principal1      # Windows
./compilar.sh Lab01_Clases_AlumnoLibro Principal1        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Principal1
```
Para el problema 2: `-Main Principal2` / `java -cp output Principal2`.

## Qué se entregó
- `docs/UML.drawio`: diagrama de clases (Taller 1)
- Código en `src/`

## Notas: cómo se resolvió
- Primer contacto con el encapsulamiento: todo atributo es `private` y se accede por setters/getters.
- Para buscar por título se usa `equalsIgnoreCase()`, no `==`. Con `==` se comparan referencias y la búsqueda nunca encuentra el libro.
- Cada `Principal` tiene un `test()` que llena datos de ejemplo sin teclado.
- Dificultades: se trabajó en terminal de Linux con Vim, y copiar/pegar y dar formato tomó más tiempo del esperado.
- Hay tres clases con `main()`, así que al compilar hay que elegir cuál ejecutar.
