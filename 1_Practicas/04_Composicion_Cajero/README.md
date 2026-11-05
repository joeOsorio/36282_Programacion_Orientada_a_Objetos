# Laboratorio 4 — Asociación / composición: Cajero automático

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio + Taller 4 (reporte en PDF) | U2 Modularidad y jerarquía | Asociación, composición, delegación |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Simular un cajero electrónico. `CuentaBancaria` tiene número de cuenta, titular y saldo, y permite depositar, retirar y saldar. El cajero permite ingresar, depositar, retirar, eliminar la cuenta, imprimir recibo y salir. Cada operación imprime un recibo con lo que se hizo, el historial de saldo y la información sensible de la cuenta.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `CuentaBancaria` | Número, titular, saldo e historial; `depositar`, `retirar`, `saldar` |
| `Banco` | Administra el arreglo de cuentas, con folio autoincremental desde 1000 |
| `Cajero` | Sesión sobre una cuenta; delega en `Banco`/`CuentaBancaria`; recibo con el número enmascarado |
| `Main` | Pantalla de inicio + menú del cajero; `test()` oculto |

Relaciones: `Banco` ◆ composición con `CuentaBancaria`; `Cajero` → asociación con `Banco` (lo consulta, no lo posee).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab04_Composicion_Cajero      # Windows
./compilar.sh Lab04_Composicion_Cajero        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- `docs/Practica_4_POO.pdf` y `.docx`: reporte (Taller 4)
- `docs/UML.drawio` y `docs/UML.png`: diagrama de clases
- Código en `src/`
- `extra_EjercicioLibreria/`: ejercicio complementario de clase (Librería con CRUD de libros), con su propio `Instrucciones.md`

## Notas: cómo se resolvió
- Esta práctica fijó el **estilo de referencia** del resto del curso: encabezado por archivo, secciones comentadas, validaciones que imprimen `Error: ...`, `leerEntero()`/`leerDouble()` seguros y `test()` oculto.
- Se agregó la clase `Banco` (no venía en el enunciado) para que el cajero no fuera el dueño de las cuentas.
- El ejercicio de la Librería se ejecuta aparte (sus clases están en el paquete `Prueba`):
  ```powershell
  .\compilar.ps1 Lab04_Composicion_Cajero\extra_EjercicioLibreria      # desde 1_Practicas
  ```
