# Taller 3 — Interfaces: Conversión de bases

| Tipo | Unidad | Temas |
|---|---|---|
| Taller (diagrama + código) | U3 Polimorfismo y reutilización | Implementar varias interfaces, hash de cadenas |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Una clase `Numero` implementa las interfaces mostrar binario, octal y hexadecimal (sin parámetros, usando sus atributos). Luego se implementan las mismas interfaces en una clase `Texto`, convirtiendo el texto en número con la suma ASCII o con un hash.

## Clases y relaciones
| Clase / interfaz | Responsabilidad |
|---|---|
| «interface» `IMostrarBinario`, `IMostrarOctal`, `IMostrarHexadecimal` | Sin parámetros; regresan `String` |
| `Numero` | `- valor: long`; implementa las 3 |
| `Texto` | `- texto: String`; hash polinomial (P = 131, M = 1e9+9, cada char vale ASCII + 1) y `sumaAscii()`; implementa las 3 |
| `Conversor` | `static convertir(long, base)` por divisiones sucesivas |
| `Main` | 1 Número / 2 Texto / 3 Comparar (arreglos del tipo interfaz) / 4 Salir / 5 `test()` oculto (21 checks contra `Long.toBinaryString`, etc.) |

Relaciones: `Numero` y `Texto` realizan las 3 interfaces; ambos ⇢ `Conversor` (dependencia).

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Taller03_Interfaces_ConversorBases      # Windows
./compilar.sh Taller03_Interfaces_ConversorBases        # Linux / Git Bash
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
- Se eligió el hash polinomial de cp-algorithms porque la suma ASCII hace que los anagramas choquen ("amor" y "roma" suman lo mismo, pero su hash es distinto). La comparación se muestra en el programa.
- **⚠️ Pendiente:** `Main.java` tiene caracteres sueltos en `leerEntero()` (`input.nextLine();``, cerca de la línea 155) que impiden compilar. Hay que borrar los dos acentos graves.
