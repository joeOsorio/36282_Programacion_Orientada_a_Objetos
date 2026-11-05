# Laboratorio 12 — Interfaces y polimorfismo: Moderación de reseñas

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio | U3 Polimorfismo y reutilización | Interfaces, enum, Strategy, ordenamiento |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Las reseñas de productos se publican solo si pasan filtros automáticos (groserías, longitud, spam). Luego los productos se ordenan por calificación con dos estrategias (promedio simple y bayesiano), con un mínimo de reseñas para entrar al ranking. Escenario: 3 productos con 3, 10 y 13 reseñas.

## Clases y relaciones
| Clase / interfaz | Responsabilidad |
|---|---|
| enum `Veredicto` | `APROBADA < REVISION_MANUAL < RECHAZADA`; `esMasSeveroQue()` por `ordinal()` |
| `Resena` | Id autoincremental, SKU (`VX@123`), usuario, calificación ajustada a 1..5, texto, compra verificada, veredicto y filtro causante |
| «interface» `Filtro` | `getNombre()`, `revisar(Resena): Veredicto` |
| `FiltroGroserias`, `FiltroLongitud`, `FiltroSpam` | Groserías (sin acentos, palabra por palabra) / 5..255 palabras / >65% mayúsculas → revisión manual |
| `Moderador` | Recibe `Filtro[]`, corre todos y registra el más severo |
| «interface» `CalcularCalificacion` | `calcular(Resena[], sku): double` |
| `PromedioSimple`, `PromedioBayesiano` | Σc/n y Σ(c·p)/n con p = palabras/(palabras+3) |
| `Producto` | SKU, nombre, `Resena[20]`, `MIN_RESENAS_RANKING = 5` (agregada, no venía en el enunciado) |
| `Main` | Sin menú: `main()` solo corre `test()` (41 checks) |

Relaciones: realización hacia `Filtro` y `CalcularCalificacion`; `Moderador` ◇ `Filtro`; `Producto` ◆ `Resena`.

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab12_InterfacesPolimorfismo_Resenas      # Windows
./compilar.sh Lab12_InterfacesPolimorfismo_Resenas        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- Código en `src/`

## Notas: cómo se resolvió
- **Supuestos:** `REVISION_MANUAL` también cuenta como no publicada, y el mínimo de reseñas se aplica en el ranking (los productos que no llegan a 5 aprobadas van al final).
- `Veredicto` empezó como una clase con un enum interno y se simplificó a un `enum` de primer nivel.
- El ranking usa burbuja y se muestra con ambas estrategias (patrón Strategy).
