# Extra — Práctica para examen

| Tipo | Unidad | Temas |
|---|---|---|
| Extra | U3 Polimorfismo y reutilización | Plantilla de menú + interfaz |

## Resumen del problema
Esqueleto preparado para practicar antes del examen: un `Main` con menú `do-while` y lectura segura de enteros, y la interfaz `IMostrarOctal` (del Taller 3).

## Clases y relaciones
| Clase / interfaz | Responsabilidad |
|---|---|
| `Main` | Menú base y `leerEntero()` con `try/catch` |
| «interface» `IMostrarOctal` | `mostrarOctal(): String` |

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Extra_PracticaExamen2      # Windows
./compilar.sh Extra_PracticaExamen2        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- Código en `src/` (esqueleto, sin lógica todavía)

## Notas: cómo se resolvió
- Sirve como plantilla rápida para arrancar un ejercicio en el examen: menú, `Scanner` estático y validación de entrada ya listos.
