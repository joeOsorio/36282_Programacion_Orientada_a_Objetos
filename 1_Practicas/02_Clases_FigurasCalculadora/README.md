# Laboratorio 2 — Clases pt2: Calculadora de figuras

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio + Taller 2 (reporte en PDF) | U1 Abstracción y encapsulamiento | Constructores, sobrecarga, métodos, `static` |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Clases `Rectangulo`, `Circulo` y `Triangulo`, con un constructor que inicializa todos los atributos, setters/getters y métodos para calcular el área y el perímetro. `Calculadora` **no se instancia**: contiene el `main` con un menú (y submenús por figura) para hacer todas las operaciones.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Rectangulo` | `base`, `altura`; `area()`, `perimetro()` |
| `Circulo` | `radio`; `area()`, `perimetro()` |
| `Triangulo` | `base`, `altura`; `area()`, `perimetro(ladoA, ladoB)` |
| `Calculadora` | `main` + submenús (`MenuRecatangulo`, `MenuCirculo`, `MenuTriangulo`); todo `static` |

Relaciones: `Calculadora` ◆ composición con una instancia de cada figura (atributos `static` creados por la propia calculadora). Ver `docs/UML.drawio`.

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab02_Clases_FigurasCalculadora -Main Calculadora      # Windows
./compilar.sh Lab02_Clases_FigurasCalculadora Calculadora        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Calculadora
```

## Qué se entregó
- `docs/Reporte_Lab2_JoshuaOsorio.pdf` / `.docx`: reporte (Taller 2)
- `docs/UML.drawio`: diagrama de clases (rehecho en la reorganización; el anterior era una copia del UML del Lab 1)
- Código en `src/`

## Notas: cómo se resolvió
- El menú se armó como menú principal + un submenú por figura. Las figuras son `static` para poder usarlas desde todos los submenús.
- Cada submenú tiene una prueba accesible que no aparece en el menú visible.
- Problemas: errores de sintaxis por nombres mal escritos (se resolvieron leyendo los errores de `javac`), y el clásico problema de `Scanner` al mezclar `nextInt()`/`nextDouble()` con `nextLine()`.
- **Pendiente detectado en la revisión:** en `Circulo`, `area()` regresa `Math.PI * radio` y `perimetro()` regresa `Math.PI * radio²`. Las fórmulas correctas son área `π·r²` y perímetro `2·π·r`. No se modificó el código.
