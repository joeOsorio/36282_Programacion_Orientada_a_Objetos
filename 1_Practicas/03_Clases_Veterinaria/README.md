# Laboratorio 3 — Clases pt3: Veterinaria

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio + Taller 3 (reporte en PDF) | U1 Abstracción y encapsulamiento | Arreglos de objetos, constructores, búsqueda y modificación |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Registro de mascotas (máximo 5) y asignación de diagnósticos en una veterinaria, con el menú: 1) Registro de mascota, 2) Dar diagnóstico (buscar una mascota, cambiarle el diagnóstico e imprimirla para comprobar el cambio), 3) Salir.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Mascota` | Nombre, edad, tipo, raza y diagnóstico, con constructores y setters/getters |
| `Veterinaria` | `main`; arreglo estático `Mascota[5]` + contador; `registrar()`, `darDiagnostico()`, `test()` |

Relaciones: `Veterinaria` ◇ agregación de 0..5 `Mascota`.

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab03_Clases_Veterinaria -Main Veterinaria      # Windows
./compilar.sh Lab03_Clases_Veterinaria Veterinaria        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Veterinaria
```

## Qué se entregó
- `docs/Practica_3_JoshuaOsorioOsorio.pdf` y `docs/Practica_3_POO.docx`: reporte (Taller 3, pedido EN PDF)
- `docs/UML.drawio`, `docs/UML.png` y `docs/Diagrama_UML_Veterinaria.html`: diagrama de clases
- Código en `src/`

## Notas: cómo se resolvió
- Primer uso del patrón "arreglo de tamaño fijo + contador" para registrar objetos.
- El diagrama se hizo también como página HTML a petición, y luego se rehízo en `.drawio` con la agregación `Veterinaria`–`Mascota` (1 a 0..5).
