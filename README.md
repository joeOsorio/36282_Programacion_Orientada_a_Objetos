# 📘 Programación Orientada a Objetos (36282)

Repositorio académico de la materia **Programación Orientada a Objetos** (clave 36282, grupo 541) de **Ingeniería en Computación**, Facultad de Ciencias Químicas e Ingeniería (FCQI), UABC. Semestre **2026-2** (10 ago – 28 nov 2026).

**Docente:** Gustavo Omar Zamarrón de la Garza (GOZG)
**Alumno:** Joshua Osorio Osorio

## 🎯 Competencia del curso
Aplicar el paradigma de programación orientada a objetos en la solución de problemas de procesamiento de información, empleando un lenguaje de modelado (UML) y un lenguaje de programación orientado a objetos (Java).

## 🗂️ Unidades
| Unidad | Temas | Semanas |
|---|---|---|
| U1 Abstracción y encapsulamiento | Clases, objetos, niveles de acceso, métodos | 1-3 |
| U2 Modularidad y jerarquía | Composición, asociación, herencia, paquetes | 4-6 |
| U3 Polimorfismo y reutilización | Polimorfismo, interfaces, clases abstractas | 7-10 |
| U4 Persistencia y concurrencia | Cohesión, acoplamiento, persistencia (archivos) | 11-13 |
| U5 Concurrencia y proyecto | Concurrencia y paralelismo (hilos), avances y entrega del proyecto | 14-16 |

El calendario completo está en [`0_Recursos/00_Encuadre/`](0_Recursos/00_Encuadre/) y el resumen en [`Notas.md`](Notas.md#3-calendario-y-evaluación).

## 📂 Estructura
```
36282_Programacion_Orientada_a_Objetos/
├── 0_Recursos/
│   ├── 00_Encuadre/        Encuadre del curso y anexo con el plan de actividades
│   ├── 01_Presentaciones/  Presentaciones del profe, en orden del curso
│   ├── 02_Libros/          Bibliografía (PDF)
│   ├── 03_Plantillas/      Plantilla del reporte de prácticas
│   └── 04_Apps/            Instalador del JDK (ignorado en git)
├── 1_Practicas/
│   ├── LabNN_Tema_Problema/      Laboratorios (código Java)
│   ├── TallerNN_Tema_Problema/   Talleres (diagramas UML y, a veces, código)
│   ├── Extra_*/                  Material extra (prácticas de examen)
│   ├── compilar.ps1              Compilar/ejecutar una práctica (Windows)
│   └── compilar.sh               Compilar/ejecutar una práctica (Linux/Git Bash)
├── Notas.md                Guía del curso: cómo compilar, teoría y tips
└── README.md
```

Cada práctica tiene la misma forma:
```
LabNN_Tema_Problema/
├── README.md          Resumen, clases y relaciones, cómo compilar, entregables y notas
├── docs/
│   ├── Instrucciones.md   Enunciado tal cual viene en Classroom
│   ├── UML.drawio         Diagrama de clases
│   └── Reporte_*.pdf/docx Reporte (cuando se pidió)
├── src/               Código fuente (un archivo por clase)
└── output/            .class compilados (se genera, no se sube a git)
```

## 🧪 Índice de prácticas
| Práctica | Unidad | Tema | Problema |
|---|---|---|---|
| [Lab01](1_Practicas/Lab01_Clases_AlumnoLibro/) | U1 | Clases y objetos | Alumnos y libros (setters/getters, arreglos) |
| [Lab02](1_Practicas/Lab02_Clases_FigurasCalculadora/) | U1 | Clases, constructores | Calculadora de figuras |
| [Lab03](1_Practicas/Lab03_Clases_Veterinaria/) | U1 | Clases, arreglos de objetos | Registro de mascotas |
| [Lab04](1_Practicas/Lab04_Composicion_Cajero/) | U2 | Asociación y composición | Cajero automático |
| [Lab05](1_Practicas/Lab05_Composicion_EquiposRRHH/) | U2 | Asociación y composición | Empleados y equipos |
| [Lab06](1_Practicas/Lab06_Composicion_VeterinariaDoctores/) | U2 | Asociación y composición | Veterinaria con doctores y login |
| [Lab07](1_Practicas/Lab07_Composicion_Banco/) | U2 | Asociación y composición | Banco y cajero con sesión |
| [Lab08](1_Practicas/Lab08_Composicion_CartasGoFish/) | U2 | Composición | Mecánicas de cartas (Go Fish) |
| [Taller07](1_Practicas/Taller07_Herencia_VeterinariaDispatch/) | U3 | Herencia, *dynamic dispatch* | Veterinaria con Perro/Gato/Ave |
| [Lab10](1_Practicas/Lab10_Polimorfismo_FechasExportador/) | U3 | Herencia y polimorfismo | Formatos de fecha y exportadores |
| [Lab11](1_Practicas/Lab11_Interfaces_OcultarValidar/) | U3 | Interfaces | Ocultar datos y validar cadenas |
| [Lab12](1_Practicas/Lab12_InterfacesPolimorfismo_Resenas/) | U3 | Interfaces y polimorfismo | Moderación de reseñas y ranking |
| [Taller01](1_Practicas/Taller01_HerenciaPolimorfismo_Microbios/) | U3 | Herencia y polimorfismo | Batalla de microbios |
| [Taller02](1_Practicas/Taller02_Interfaces_Restaurantes/) | U3 | Interfaces, herencia | Lanzables + cadena de restaurantes (POS) |
| [Taller03](1_Practicas/Taller03_Interfaces_ConversorBases/) | U3 | Interfaces | Conversión a binario/octal/hexadecimal |
| [Taller04](1_Practicas/Taller04_Interfaces_ReglasNegocio/) | U3 | Interfaces, Composite | Motor de reglas de negocio (solo UML) |
| [Taller05](1_Practicas/Taller05_Paquetes_Genericos/) | U2/U3 | Paquetes y genéricos | Lista doblemente enlazada genérica |
| [Extra](1_Practicas/Extra_PracticaExamen2/) | — | — | Esqueleto de práctica para examen |

> **Numeración de talleres:** en las primeras semanas el "taller" de cada lab era su diagrama (Lab 1) o su **reporte en PDF** (labs 2 a 7), por eso esos entregables viven dentro de la carpeta de cada lab. Después vino el Taller 7 (herencia) y, en la Unidad 3, el profe reinició la numeración con los Talleres 1 a 5.

## ▶️ Compilar y ejecutar rápido
```powershell
cd 1_Practicas
.\compilar.ps1 Lab04_Composicion_Cajero
```
```bash
cd 1_Practicas
./compilar.sh Lab04_Composicion_Cajero
```
Los comandos manuales (`javac`/`java`) y cómo funcionan están en [`Notas.md`](Notas.md#2-cómo-compilar-y-ejecutar).

## 🛠️ Tecnologías
- **Lenguaje:** Java — JDK 27 (instalador en `0_Recursos/04_Apps/`)
- **Modelado:** UML con draw.io (`.drawio`)
- **Editor:** VS Code (Extension Pack for Java)
- **Control de versiones:** Git y GitHub

## 📊 Evaluación
| Criterio | Valor | Qué es |
|---|---|---|
| Laboratorios | 10% | Código de ejercicios de los temas vistos en clase |
| Talleres | 5% | Diagramas de clases/objetos/relación |
| Proyecto | 10% | Programa con una temática que englobe todo lo visto |
| Exámenes | 75% | 4 exámenes (18.75% c/u) |

## 🏗️ Proyecto final
Programa en Java con **herencia, interfaces, polimorfismo y clases abstractas** (e hilos/serialización si se alcanza a ver). Estructura modular: un folder para el código fuente, un folder para el ejecutable y **un archivo por clase**.

## 📚 Bibliografía
- Gaddis, T. — *Starting Out with Java: From Control Structures through Objects* (6ª ed.), Pearson
- Weisfeld, M. — *The Object-Oriented Thought Process* (5ª ed.), Addison-Wesley
- Schildt, H. — *Java: The Complete Reference* (12ª ed.), McGraw Hill
- Horstmann, C. S. — *Core Java Volume I: Fundamentals* (12ª ed.), Oracle Press
- OMG — *UML 2.5.1 Specification* (referencia de notación)

## 📌 Nota
Repositorio con fines educativos.
