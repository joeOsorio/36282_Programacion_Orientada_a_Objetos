# Taller 5 — Paquetes y genéricos: Lista doblemente enlazada

| Tipo | Unidad | Temas |
|---|---|---|
| Taller (código) | U2/U3 Modularidad · Reutilización | Paquetes (`package`/`import`), clases genéricas `<T>` |

## Resumen del problema
Implementar una lista doblemente enlazada **genérica** dentro de un paquete `utils`, y usarla desde un `Main` en el paquete `app`. *(No hay enunciado de Classroom guardado para este taller.)*

## Clases y relaciones
| Clase | Paquete | Responsabilidad |
|---|---|---|
| `Nodo<T>` | `utils` | `valor`, `siguiente`, `anterior` |
| `Lista<V>` | `utils` | `head`, `tail`, `size`; `append()`, `eliminar(pos)`, `mostrar()`, `getSize()` |
| `Main` | `app` | Prueba la lista con `Integer` y `String` |

Relaciones: `Lista` ◆ `Nodo` (crea sus nodos); `Main` ⇢ `Lista`.

```
src/
├── app/Main.java
└── utils/
    ├── Lista.java
    └── Nodo.java
```

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Taller05_Paquetes_Genericos -Main app.Main      # Windows
./compilar.sh Taller05_Paquetes_Genericos app.Main        # Linux / Git Bash
```
A mano, desde esta carpeta:
```powershell
chcp 65001
javac -encoding UTF-8 -d output (Get-ChildItem src -Recurse -Filter *.java).FullName
java "-Dstdout.encoding=UTF-8" -cp output app.Main
```

## Qué se entregó
- Código en `src/`
- `referencia_profesor/motor_reglas_negocio/`: el motor de reglas del Taller 4 que hizo el profe en clase, separado en paquetes (`app`, `Reglas`, `ReglasNegocio`, `MotorReglas`)

## Notas: cómo se resolvió
### Problemas que no dejaban compilar y cómo se corrigieron
| Archivo | Problema | Corrección |
|---|---|---|
| `Main.java` | `import utils` sin `;` y una línea basura `import utils.Lista;,Lista;` | Un solo `import utils.Lista;` |
| `Main.java` | Estaba en `src/` pero declaraba `package app;` | Movido a `src/app/Main.java` (la carpeta debe coincidir con el paquete) |
| `Main.java` | `new Lista()` (*raw type*) y `append()` sin argumento | `new Lista<>()` y `append(valor)` |
| `nodo.java` | El archivo se llamaba `nodo.java`, pero la clase pública es `Nodo` | Renombrado a `Nodo.java` |
| `Nodo.java` | El constructor recibía `valor` pero nunca lo guardaba (siempre `null`) | `this.valor = valor;` |
| `Lista.java` | `append(T valor)` cuando el parámetro de tipo de la clase es `V` | `append(V valor)` |
| `Lista.java` | `import utils.Nodo;` dentro del mismo paquete | Se quitó (no hace falta) |
| `Lista.java` | `eliminar()` tronaba con `NullPointerException` al borrar el único nodo, y no validaba posiciones negativas | Caso `size == 1` y validación `id < 0` |
| `Lista.java` | En `mostrar()`, el valor siguiente usaba `n.anterior.valor` | `n.siguiente.valor`, un nodo por línea |

### Motor de reglas del profe
Compila sin cambios. Su `como_compilar.txt` está incompleto (solo lista 3 archivos y corre `Main` sin paquete). La forma correcta es:
```powershell
cd referencia_profesor/motor_reglas_negocio
javac -encoding UTF-8 -d build (Get-ChildItem src -Recurse -Filter *.java).FullName
java -cp build app.Main
```
Su `main` es de instancia (`public void main`, sin `static`). Eso funciona desde Java 25 (JDK 27 sí lo corre); en versiones anteriores daría error.
