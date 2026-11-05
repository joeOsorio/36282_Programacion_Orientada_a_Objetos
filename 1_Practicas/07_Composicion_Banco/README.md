# Laboratorio 7 — Sistema de banco

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio + Taller (reporte en PDF) | U2 Modularidad y jerarquía | Composición, asociación, sesión de un solo usuario |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Sistema de banco (que no es el main). El banco abre y cierra cuentas. En el cajero, el usuario entra con su número de cuenta y PIN, y puede retirar, abonar o eliminar su cuenta. Solo un usuario a la vez puede usar el cajero.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `CuentaBancaria` | Número de cuenta, titular, PIN y saldo |
| `Banco` | Arreglo fijo de 10 cuentas, folio desde 1000, PIN autogenerado de 4 dígitos, super usuario `root`/`admin` para abrir cuentas |
| `Cajero` | Sesión de **una** cuenta a la vez (login/logout); delega retirar/abonar/eliminar/cambiar PIN |
| `Main` | Menú; `test()` oculto con 14 escenarios |

Relaciones: `Banco` ◆ `CuentaBancaria` (1 – 0..10); `Cajero` → `Banco` (asociación); `Cajero` → `CuentaBancaria` (sesión activa, 1 – 0..1).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab07_Composicion_Banco      # Windows
./compilar.sh Lab07_Composicion_Banco        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- `docs/UML.drawio`: diagrama de clases
- Código en `src/`
- Reporte: **pendiente**

## Notas: cómo se resolvió
- `Banco.cerrarCuenta()` no exige la sesión de super usuario por dentro: la autorización la hace quien la llama (el menú de admin o `Cajero.eliminarCuentaActual()` para el titular ya logueado).
- `CuentaBancaria` sí expone `getPin()`, porque el PIN lo genera el banco y alguien tiene que poder comunicarlo. Es una decisión consciente, documentada en el código.
- Mismo patrón que el Lab 6: las opciones reales del menú quedaron comentadas y la verificación se hace con `test()`.
- Ojo: el `Instrucciones.md` dice "reporte del laboratorio 6", pero se refiere al 7 (así viene en Classroom).
