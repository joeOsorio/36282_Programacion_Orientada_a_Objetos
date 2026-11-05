# Laboratorio 8 — Mecánicas de un juego de cartas (Go Fish)

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio | U2 Modularidad y jerarquía | Composición, arreglos con tope, `Random` |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Simular las mecánicas principales de un juego de cartas al estilo Go Fish, sin reglas: revolver el mazo, repartir 5 cartas por mano, agarrar del mazo, pasar cartas entre jugadores, mostrar la mano al dealer y contar cartas del mismo valor.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Carta` | Valor y palo (`VALORES` × `PALOS`); `mismoValor()`, `toString()` |
| `Mano` | Arreglo de hasta 48 cartas + contador; agarrar, tirar, transferir una o varias, contar por valor |
| `Dealer` | Arma el mazo con cartas únicas, lo revuelve con `Random`, reparte y lleva el tope de cartas disponibles |
| `Main` | Menú (8 opciones + datos de prueba) y `test()` |

Relaciones: `Dealer` ◆ `Carta` (crea el mazo); `Mano` ◇ `Carta` (las recibe del dealer o de otra mano); `Dealer` → `Mano` (reparte y revisa).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab08_Composicion_CartasGoFish      # Windows
./compilar.sh Lab08_Composicion_CartasGoFish        # Linux / Git Bash
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
- El mazo usa `cartasDisponibles` como "índice tope": las cartas ya repartidas quedan después del tope en lugar de borrarse del arreglo.
- Para transferir varias cartas, los índices se ordenan (inserción) y se transfieren de mayor a menor, para que los índices restantes sigan siendo válidos.
