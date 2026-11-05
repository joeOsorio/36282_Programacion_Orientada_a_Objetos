# Taller 1 — Herencia y polimorfismo: Batalla de microbios

| Tipo | Unidad | Temas |
|---|---|---|
| Taller (diagrama) + código | U3 Polimorfismo y reutilización | Herencia, sobrescritura, composición, dynamic dispatch |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Cuatro microbios (256 de energía) viajan órgano por órgano dentro de una persona alienígena (2048 de energía) y la atacan en cada salto. Cada órgano (cerebro, corazón, estómago, páncreas, riñones, intestino) aplica un efecto distinto. La simulación termina cuando caen todos los microbios o la persona. El enunciado pide solo el diagrama; también se implementó el código.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Organo` | Clase base concreta: `aplicarEfecto(Persona, Microbio)` (por defecto no hace nada) y `getDescripcion()` |
| `Cerebro`, `Corazon`, `Estomago`, `Pancreas`, `Rinones` | `@Override` de su efecto (las constantes de cada efecto son `static final`) |
| `Intestino` | No hace nada; método propio `SoloExistir()` |
| `Persona` | Energía, defensa y sus 6 órganos (los crea en el constructor) |
| `Microbio` | Energía, defensa y órgano actual; `saltar(o, p)` = atacar y luego `o.aplicarEfecto(p, this)` |
| `Simulacion` | Persona + 4 microbios; corre la batalla por turnos |
| `Main` | 1 Iniciar / 2 Salir / 3 `test()` oculto (8 escenarios) |

Relaciones: los 6 órganos ▷ `Organo`; `Persona` ◆ `Organo` (1 – 6); `Simulacion` ◆ `Persona` y → `Microbio` (1 – 4); `Microbio` ⇢ `Organo` ("afecta"); `Persona` ◇ `Microbio` ("infecta").

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Taller01_HerenciaPolimorfismo_Microbios      # Windows
./compilar.sh Taller01_HerenciaPolimorfismo_Microbios        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- `docs/UML.drawio`: diagrama de clases
- `docs/Ejemplo.drawio`: diagrama de ejemplo
- Código en `src/`

## Notas: cómo se resolvió
- **Dynamic dispatch:** `Microbio.saltar()` no sabe en qué órgano cae; llama `o.aplicarEfecto(...)` y cada órgano aplica el suyo.
- **Mecánica:** daño = ataque − defensa para ambos lados. El corazón da +30 de defensa a la persona; el cerebro pone la defensa del microbio en −30 (recibe 30 extra). Los efectos duran 3 saltos y, si ya están activos, solo suman 1 turno.
- **Supuestos que no vienen en el enunciado:** el ataque del microbio es 40, y páncreas/riñones usan división entera con mínimo 1 para que la batalla siempre termine.
- En 30 corridas los resultados quedaron parejos (~14 vs 16 victorias).
