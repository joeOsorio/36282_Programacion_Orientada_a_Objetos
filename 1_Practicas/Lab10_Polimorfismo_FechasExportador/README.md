# Laboratorio 10 — Herencia y polimorfismo: Fechas y exportadores

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio | U3 Polimorfismo y reutilización | Herencia, sobrescritura, dynamic dispatch |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
**Ejercicio 1:** a partir de un UNIX timestamp, calcular la fecha en formato americano (mm/dd/aaaa), latino (dd/mm/aaaa) e internacional (aaaa/mm/dd) con el algoritmo de días civiles. **Ejercicio 2:** exportar (como String) unos registros en JSON, Markdown y CSV.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Fecha` | Recibe el timestamp en el constructor y calcula día/mes/año; `formatear()` y `getNombreFormato()` por defecto |
| `FechaAmericana`, `FechaLatina`, `FechaInternacional` | `@Override` de `formatear()` y `getNombreFormato()` |
| `Registro` | id, persona, departamento |
| `Exportador` | `exportar(Registro[]): String` y `getTipo()` por defecto |
| `ExportadorJSON`, `ExportadorMarkdown`, `ExportadorCSV` | Cada uno sobrescribe `exportar()` |
| `Main` | Menú: timestamps de prueba / timestamp propio / exportar; `test()` oculto (opción 5) |

Relaciones: las 3 fechas ▷ `Fecha`; los 3 exportadores ▷ `Exportador`; `Exportador` ⇢ `Registro` (dependencia por parámetro).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab10_Polimorfismo_FechasExportador      # Windows
./compilar.sh Lab10_Polimorfismo_FechasExportador        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- Código en `src/` (esta práctica no pidió diagrama)

## Notas: cómo se resolvió
- Las dos jerarquías se recorren con arreglos del tipo padre (`Fecha[]`, `Exportador[]`), así que el mismo ciclo imprime todos los formatos.
- El cálculo de la fecha vive una sola vez en `Fecha.calcular()`; las subclases solo cambian cómo se presenta.
- Ojo con la división entera (`//` en el pseudocódigo): con `long`, `/` ya trunca. Se probó con los timestamps del enunciado.
