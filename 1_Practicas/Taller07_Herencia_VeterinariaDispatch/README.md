# Taller 7 — Herencia y polimorfismo: Veterinaria con dynamic dispatch

| Tipo | Unidad | Temas |
|---|---|---|
| Taller (diagrama) + código | U3 Polimorfismo y reutilización | Herencia, `@Override`, upcasting, dynamic dispatch |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Rediseñar la veterinaria (máximo 50 mascotas) para que el doctor pueda dar consulta y diagnóstico a **cualquier tipo de animal** usando *dynamic dispatching*. El enunciado pide solo el diagrama; también se implementó el código.

## Clases y relaciones
| Clase | Responsabilidad |
|---|---|
| `Mascota` | Superclase (atributos `protected`): nombre, edad, raza, diagnóstico; `getTipo`/`examinar`/`generarDiagnostico` por defecto |
| `Perro`, `Gato`, `Ave` | Subclases con su atributo propio (`vacunaRabia`, `esDeInterior`, `puedeVolar`) y `@Override` |
| `Doctor` | `darConsulta(Mascota)` / `diagnosticar(Mascota)`: trabaja con la superclase |
| `Veterinaria` | Arreglo de 50 mascotas + su doctor |
| `Main` | Menú (registro / diagnóstico / salir) + `test()` oculto (opción 4) |

Relaciones: `Perro`/`Gato`/`Ave` ▷ `Mascota` (herencia); `Veterinaria` ◇ `Mascota` (0..50) y ◇ `Doctor`; `Doctor` ⇢ `Mascota` (dependencia por parámetro).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Taller07_Herencia_VeterinariaDispatch      # Windows
./compilar.sh Taller07_Herencia_VeterinariaDispatch        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output src/*.java
java "-Dstdout.encoding=UTF-8" -cp output Main
```

## Qué se entregó
- `docs/UML.drawio`, `docs/UML.png`, `docs/UML.svg`: diagrama de clases
- Código en `src/`

## Notas: cómo se resolvió
- El doctor no sabe qué animal recibe: llama `m.examinar()` y la JVM ejecuta la versión de `Perro`, `Gato` o `Ave` según el objeto real.
- Al registrar, `crearMascota(tipo)` hace el *upcasting* (`Mascota m = new Perro(...)`).
- En el diagrama, `Mascota` quedó como clase concreta (no abstracta) y las relaciones con `Veterinaria` como agregación.
