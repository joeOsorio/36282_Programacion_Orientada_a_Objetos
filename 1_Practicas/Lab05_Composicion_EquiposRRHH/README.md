# Laboratorio 5 — Asociación y composición pt2: Equipos de RRHH

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio | U2 Modularidad y jerarquía | Composición vs agregación, arreglos compartidos |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Sistema de recursos humanos. La empresa contrata empleados (máximo 50) y los organiza en equipos de proyecto (máximo 10 equipos de 5 personas). Un empleado puede estar en varios equipos, y los equipos pueden estar vacíos.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Empleado` | Número de empleado y nombre |
| `Equipo` | Nombre + arreglo de hasta 5 empleados; `agregarMiembro`, `eliminarEmpleado`, evita duplicados |
| `Empresa` | Contrata empleados (50) y crea equipos (10); asigna empleados a equipos |
| `Main` | Menú + "Cargar datos de prueba" + `test()` oculto |

Relaciones: `Empresa` ◆ `Empleado` (los crea al contratar); `Empresa` ◆ `Equipo`; `Equipo` ◇ `Empleado` (solo agrupa empleados que ya existen).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab05_Composicion_EquiposRRHH      # Windows
./compilar.sh Lab05_Composicion_EquiposRRHH        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- `docs/Reporte_Laboratorio5.pdf` y `.docx`: reporte
- `docs/UML.drawio` / `docs/UML.png` y `docs/diagrama.DRAWIO`: dos versiones del diagrama (se conservan las dos)
- Código en `src/`
- `referencia_profesor/ImplementacionProfesor/`: esbozo que hizo el profe en clase (incompleto, no compila; solo como referencia)

## Notas: cómo se resolvió
- La clave fue distinguir **quién crea** al empleado (la empresa, composición) de **quién solo lo referencia** (el equipo, agregación). Por eso un mismo empleado puede estar en varios equipos sin duplicarse.
- En la implementación del profe, `Empleado` usa un contador `static` para autogenerar el id y separa los datos personales en una clase `Info`.
