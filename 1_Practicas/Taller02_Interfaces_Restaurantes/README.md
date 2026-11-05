# Taller 2 — Interfaces, herencia y polimorfismo: Lanzables y restaurantes

| Tipo | Unidad | Temas |
|---|---|---|
| Taller (diagrama) + código del ejercicio 2 | U3 Polimorfismo y reutilización | Interfaces, herencia, `instanceof`, sobrescritura |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
**Ejercicio 1 (solo diagrama):** pelota, baseball, balón de americano y piedra se pueden aventar, pero no todos rebotan. **Ejercicio 2:** una cadena de 3 restaurantes (hamburguesas, tortas, tacos) con toppings: en la hamburguesa el cliente **agrega** toppings, en la torta y el taco los **quita**.

## Clases y relaciones
**Ejercicio 1:** «interface» `ILanzable`, `IRebotable`; `Arrojadizo` (concreta) implementa `ILanzable`; `Pelota`/`Baseball`/`BalonAmericano` extienden e implementan `IRebotable`; `Piedra` solo extiende.

**Ejercicio 2 (código):**
| Clase / interfaz | Responsabilidad |
|---|---|
| «interface» `IPlatillo`, `IAgregable`, `IRemovible` | Contratos de platillo y de agregar/quitar toppings |
| `Platillo` | Base concreta con `toppings boolean[4]` |
| `Hamburguesa` (`IAgregable`), `Torta`, `Taco` (`IRemovible`) | Hamburguesa empieza sin toppings; torta y taco con todos |
| `Orden` | Hasta 10 platillos |
| `Restaurante` | Hasta 50 órdenes; `crearPlatillo()` lo sobrescribe cada restaurante |
| `Hamburgueseria`, `Torteria`, `Taqueria` | Menú y platillo propios |
| `CadenaRestaurantes` | Los 3 restaurantes |
| `TerminalPOS` | Toma la orden y usa `instanceof IAgregable/IRemovible` para editar toppings |
| `Main` | Menú + datos de prueba + `test()` oculto (38 checks) |

Relaciones: `CadenaRestaurantes` ◆ `Restaurante` (1 – 3); `Restaurante` ◆ `Orden` (0..50); `Orden` ◆ `Platillo` (0..10); `TerminalPOS` → `Orden`/`Restaurante`.

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Taller02_Interfaces_Restaurantes      # Windows
./compilar.sh Taller02_Interfaces_Restaurantes        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- `docs/UML.drawio`: diagrama final, con ambos ejercicios en una sola hoja
- `docs/Ejercicio1.png/.svg` y `docs/Ejercicio2.png/.svg`: exportaciones
- `docs/borradores/`: `UML_borrador.drawio` y `UML_v2.drawio` (ya integrados en `UML.drawio`)
- Código del ejercicio 2 en `src/`

## Notas: cómo se resolvió
- Precios: hamburguesa $95, torta $80, taco $25. Cadena "Antojitos Joe": Burger Joe, Tortas La Tijuanense y Tacos El Primo.
- Se agregaron `getContador()` y `getRestaurante()`, que no estaban en el primer UML; el diagrama final ya los incluye.
- El ejercicio 1 no tiene código.
