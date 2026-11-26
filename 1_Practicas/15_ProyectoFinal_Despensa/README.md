# Proyecto Final POO: Despensa y Lista del Súper

| | |
|---|---|
| **Materia** | 36282 Programación Orientada a Objetos, UABC FCQI |
| **Docente** | GOZG |
| **Alumno** | Joshua Osorio Osorio (1293271) |
| **Fecha** | Noviembre 2026 |

Programa de terminal para llevar el control de lo que hay en casa. Registra los productos de la despensa, avisa qué está vencido o por caducar y arma la lista del súper con lo que hace falta. Todo se guarda en archivos de texto, así que al volver a abrirlo sigue ahí.

## Requisitos del proyecto

| Requisito | Dónde se cumple |
|---|---|
| Mínimo 8 clases | 12 clases + `utils.Lista<V>` y `utils.Nodo<T>` (de la Práctica 14) |
| Polimorfismo | `Producto` abstracta ← `Perecedero`, `NoPerecedero`, `Limpieza`; interfaz `IGuardable` |
| Archivo de texto | `data/despensa.txt` (se carga al iniciar y se guarda tras cada cambio) y `data/lista_super.txt` |
| Extra: concurrencia | `HiloCaducidad` (Runnable) revisa la despensa en segundo plano; `Despensa` usa `synchronized` |

## Clases

| Clase | Responsabilidad |
|---|---|
| `Producto` (abstracta) | Nombre, cantidad, mínimo y unidad. Abstractos `getTipo()` y `estaVencido()`. `necesitaReponer()` y `cantidadAComprar()` tienen versión por defecto |
| `Perecedero` | Tiene fecha de caducidad. Vencido = hay que reponerlo aunque quede; se compra todo de nuevo |
| `NoPerecedero` | Nunca vence; se repone solo por cantidad |
| `Limpieza` | Nunca vence; se repone antes del mínimo (margen ×1.5); aviso si es peligroso |
| `enum Unidad` | PIEZA, KG, LITRO, PAQUETE con su abreviatura |
| «interface» `IGuardable` | `toLinea()`: cada producto sabe escribirse en una línea del .txt |
| `Despensa` | `Lista<Producto>` con altas, bajas, búsqueda, consumo y consultas. Métodos `synchronized` |
| `ListaSuper` | Se genera preguntando `necesitaReponer()` a cada producto + extras escritos a mano |
| `GestorArchivos` | Guardar/cargar la despensa y exportar la lista. Al cargar crea la subclase según el TIPO de la línea |
| `HiloCaducidad` | Hilo en segundo plano: cada 10 s revisa vencidos y por vencer (3 días) y prepara avisos |
| `Consola` | Colores, títulos y mensajes para que la terminal se vea bien |
| `Main` | Menú, lectura segura de datos y `test()` oculto |

## Estructura

```
15_ProyectoFinal_Despensa/
├── README.md
├── docs/          Diagrama UML
├── src/           Código fuente
│   └── utils/     Lista<V> y Nodo<T>
├── data/          despensa.txt y lista_super.txt (los crea el programa)
└── output/        .class compilados (se generan al compilar)
```

## Cómo compilar y ejecutar

Requiere **JDK 17 o superior** (`javac -version`). Todos los comandos se corren **desde la carpeta del proyecto** (donde está este README).

### Windows (PowerShell)
```powershell
chcp 65001
javac -encoding UTF-8 -d output (Get-ChildItem -Recurse src -Filter *.java).FullName
java "-Dstdout.encoding=UTF-8" -cp output Main
```

### Linux / macOS / Git Bash
```bash
javac -encoding UTF-8 -d output $(find src -name "*.java")
java -Dstdout.encoding=UTF-8 -cp output Main
```

> Si la terminal muestra símbolos raros en lugar de colores, ejecuta con `-Dcolor=false`:
> `java "-Dstdout.encoding=UTF-8" -Dcolor=false -cp output Main`

## Uso

| Opción | Qué hace |
|---|---|
| 1 | Ver la despensa en tabla, con el estado de cada producto en color |
| 2 | Agregar producto (perecedero, no perecedero o limpieza) |
| 3 | Consumir: resta la cantidad que usaste |
| 4 | Reabastecer: suma lo que compraste (y la fecha nueva si es perecedero) |
| 5 | Eliminar producto |
| 6 | Caducidad: vencidos y los que vencen en los próximos 3 días |
| 7 | Lista del súper: se genera sola, puedes agregar/quitar extras y exportarla a `data/lista_super.txt` |
| 8 | Avisos del hilo: estado de `HiloCaducidad` y avisos actuales |
| 9 | Cargar datos de prueba (9 productos con casos de todo tipo) |
| 10 | Guardar y salir |
| 11 | *(oculta)* Corre el `test()`: 65 pruebas automáticas, incluyendo dos hilos modificando la misma despensa |

Las fechas se escriben como `dd/mm/aaaa` (ej. `28/11/2026`) o `+días` (ej. `+5` = dentro de 5 días).

## Formato de `data/despensa.txt`

```
# TIPO;nombre;cantidad;minima;UNIDAD;[fecha yyyy-MM-dd | peligroso]
PERECEDERO;Leche;2;1;LITRO;2026-11-27
NOPERECEDERO;Arroz;2;1;KG
LIMPIEZA;Cloro;1;1;LITRO;true
```

Las líneas que empiezan con `#` se ignoran. Si una línea viene mal, se salta y el programa avisa cuántas ignoró.
