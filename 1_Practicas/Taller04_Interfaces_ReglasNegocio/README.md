# Taller 4 — Interfaces y polimorfismo: Motor de reglas de negocio

| Tipo | Unidad | Temas |
|---|---|---|
| Taller (solo diagrama) | U3 Polimorfismo y reutilización | Interfaces, Composite, enums |

📄 Enunciado completo (tal cual viene en Classroom): [`docs/Instrucciones.md`](docs/Instrucciones.md)

## Resumen del problema
Plataforma de e-commerce que aplica descuentos y restricciones a órdenes según varios criterios (monto mínimo, tipo de cliente, categoría, hora, día, envío gratis), con un registro de qué regla se aplicó a cada orden para el equipo legal.

## Clases y relaciones
| Clase / interfaz | Responsabilidad |
|---|---|
| «interface» `Regla` | `aplicar(Orden)`, `ejecutar(Orden)`, `describe()` |
| `ReglaAnd`, `ReglaOr`, `ReglaNot` | Envuelven otras reglas (Composite) |
| `ReglaMontoMinimo`, `ReglaDescuento`, `ReglaClientePremium`, `ReglaEnvioGratis`, `ReglaHora`, `ReglaCategoria`, `ReglaDia` | Reglas de negocio |
| `Orden`, `Cliente`, enums `Categoria` y `Dia` | Dominio |
| `MotorReglas` | Recibe `Regla[]`; `procesar(Orden): ReporteReglas` |
| `ReporteReglas` | Registra las descripciones (máx. 20) y `reportar()` |

Relaciones: todas las reglas realizan `Regla`; `ReglaAnd`/`ReglaOr`/`ReglaNot` ◇ `Regla`; `MotorReglas` ◇ `Regla` y «crea» `ReporteReglas`; `ReporteReglas` → `Orden`.


## Qué se entregó
- `docs/UML.drawio`, `docs/UML.png`, `docs/UML.svg`: diagrama de clases (una sola hoja)
- `docs/image.png`: imagen del enunciado (referenciada en `Instrucciones.md`)
- `docs/borradores/UML_borrador.drawio`: borrador inicial

## Notas: cómo se resolvió
- **Condición vs acción:** en las reglas "condición" `ejecutar()` está vacío; en las reglas "acción" `aplicar()` siempre es `true`.
- La implementación que hizo el profe en clase (con paquetes) está en el Taller 5, en `referencia_profesor/motor_reglas_negocio/`.
