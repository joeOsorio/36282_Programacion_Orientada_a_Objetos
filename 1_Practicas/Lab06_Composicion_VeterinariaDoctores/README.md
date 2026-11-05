# Laboratorio 6 — Asociación y composición pt3: Veterinaria con doctores

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio + Taller 6 (reporte en PDF) | U2 Modularidad y jerarquía | Composición, asociación, sesión/login, super usuario |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Sistema de consultas de una veterinaria (que **no es el main**), con hasta 10 doctores y 50 mascotas. Un super usuario contrata, despide y suspende doctores. Un doctor que inicia sesión (y no está suspendido) puede dar de alta y de baja mascotas y diagnosticarlas. Doctores y mascotas tienen información sensible.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Mascota` | Datos personales, dueño y diagnóstico |
| `Doctor` | Información personal y de trabajo (usuario, clave, no. de empleado), estado suspendido, `mascotasAtendidas[]` |
| `Veterinaria` | Doctores (10) y mascotas (50); login de doctor y de super usuario (`root`/`admin`) |
| `Main` | Menú; `test()` oculto que demuestra todos los puntos |

Relaciones: `Veterinaria` ◆ `Doctor` (1 – 0..10); `Veterinaria` ◆ `Mascota` (1 – 0..50); `Doctor` → `Mascota` (asociación: guarda referencias a las mascotas que atendió).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab06_Composicion_VeterinariaDoctores      # Windows
./compilar.sh Lab06_Composicion_VeterinariaDoctores        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- `docs/Practica_6_POO_Taller_Joshua_Osorio_Osorio.pdf` y `docs/Practica_6_POO.docx`: reporte (11 páginas)
- `docs/UML.drawio`: diagrama de clases (antes estaba sin extensión)
- `docs/Capturas/`: capturas para el reporte y `cap_test.txt` (entradas usadas para generarlas)
- Código en `src/`

## Notas: cómo se resolvió
- **Decisión de diseño:** se evaluó guardar solo los ids de las mascotas en el doctor (como una llave foránea) o crear una clase de asociación `Consulta`. Se descartó por alcance y quedó documentado en la reflexión del reporte.
- **Bugs reales:**
  1. `String.format` con `"#-5d"` en lugar de `"%-5d"` provocaba `IllegalFormatConversionException: d != java.lang.String`.
  2. Salía doble punto en el diagnóstico porque el formato y el texto traían punto; se quitó del formato.
  3. La ñ de "Dueño" salía como `?`; se resolvió con `javac -encoding UTF-8` + `chcp 65001`.
- En el menú, las opciones "de verdad" quedaron comentadas; solo están cableadas Test y Salir.
