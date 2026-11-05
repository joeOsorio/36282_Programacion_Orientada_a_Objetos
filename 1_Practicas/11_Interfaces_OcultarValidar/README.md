# Laboratorio 11 — Interfaces: Ocultar datos y validar cadenas

| Tipo | Unidad | Temas |
|---|---|---|
| Laboratorio | U3 Polimorfismo y reutilización | Interfaces, polimorfismo por interfaz |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
**Problema 1:** una interfaz con el método `ocultar()`, implementada por `Email` (oculta el usuario), `Tarjeta` (deja los últimos 4 dígitos) y `Password` (todo con `*`). **Problema 2:** una línea de ensamble que valida una cadena con reglas que implementan una interfaz `validar`: no nulo, no vacío, longitud mínima y es número (`Double.parseDouble`).

## Clases y relaciones
| Clase / interfaz | Responsabilidad |
|---|---|
| «interface» `IOcultar` | `ocultar(): String` |
| `Email`, `Tarjeta`, `Password` | Implementan `IOcultar` usando sus propios atributos |
| «interface» `IValidar` | `validar(String): boolean` |
| `ReglaNoNulo`, `ReglaNoVacio`, `ReglaLongitudMinima`, `ReglaEsNumero` | Implementan `IValidar` |
| `LineaEnsamble` | Arreglo de `IValidar` + contador; corre todas las reglas |
| `Main` | Menú + `test()` con pruebas unitarias |

Relaciones: realización (`- - -▷`) de cada clase hacia su interfaz; `LineaEnsamble` ◇ `IValidar`.

## Cómo compilar y ejecutar
Desde `1_Practicas/`:
```powershell
.\compilar.ps1 Lab11_Interfaces_OcultarValidar      # Windows
./compilar.sh Lab11_Interfaces_OcultarValidar        # Linux / Git Bash
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
- `LineaEnsamble` no conoce las reglas concretas, solo `IValidar`. Agregar una regla nueva no obliga a modificarla (bajo acoplamiento).
- `ReglaEsNumero` atrapa la `NumberFormatException` de `Double.parseDouble()` para regresar `false`.
- En la reorganización se borró `src/ejemplo/`, que era otra versión de la misma práctica.
