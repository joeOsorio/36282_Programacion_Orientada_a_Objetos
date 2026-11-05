# 📒 Notas del curso — Programación Orientada a Objetos (36282)

Guía para entender este repositorio y repasar lo que se vio en el curso. Está pensada para que le sirva a quien tome la materia después: cómo está organizado todo, cómo compilar a mano y un resumen de la teoría con ejemplos de las prácticas.

**Contenido**
1. [El repositorio](#1-el-repositorio)
2. [Cómo compilar y ejecutar](#2-cómo-compilar-y-ejecutar)
3. [Calendario y evaluación](#3-calendario-y-evaluación)
4. [Teoría de POO](#4-teoría-de-poo)
5. [Chuleta de UML](#5-chuleta-de-uml)
6. [Convenciones de código usadas](#6-convenciones-de-código-usadas)
7. [Errores comunes y cómo se resolvieron](#7-errores-comunes-y-cómo-se-resolvieron)
8. [Reglas del curso](#8-reglas-del-curso)

---

## 1. El repositorio

### Estructura
```
0_Recursos/        Material del curso (no se modifica)
  00_Encuadre/       Encuadre y anexo con el plan de actividades semana por semana
  01_Presentaciones/ Presentaciones del profe, numeradas en el orden del curso
  02_Libros/         Bibliografía
  03_Plantillas/     Plantilla del reporte de prácticas (Word)
  04_Apps/           Instalador del JDK (ignorado en git por su tamaño)
1_Practicas/       Una carpeta por práctica + scripts para compilar
Notas.md           Este archivo
README.md          Portada e índice de prácticas
```

### Nombres de las prácticas
`Tipo##_Tema_Problema`, con CamelCase en cada parte:

| Tipo | Qué es | Evidencia que se entrega |
|---|---|---|
| `Lab` | Laboratorio | Código Java |
| `Taller` | Taller | Diagrama UML (y en algunos también código o el reporte en PDF) |
| `Extra` | Material de apoyo | Prácticas para examen, ejercicios sueltos |

Ejemplo: `Lab04_Composicion_Cajero` = laboratorio 4, tema composición, problema del cajero.

### Estructura de cada práctica
```
Lab04_Composicion_Cajero/
├── README.md            Resumen, clases y relaciones, cómo compilar, entregables, notas
├── docs/
│   ├── Instrucciones.md     Enunciado copiado TAL CUAL de Classroom (no se edita)
│   ├── UML.drawio           Diagrama de clases (draw.io)
│   ├── UML.png / .svg       Exportación del diagrama (opcional)
│   ├── Reporte_*.docx/.pdf  Reporte, cuando se pide
│   └── borradores/          Versiones previas de diagramas
├── src/                 Código fuente, un archivo .java por clase
├── referencia_profesor/ (opcional) Código que compartió el profe
└── output/              .class generados al compilar (no se suben a git)
```

### Agregar una práctica nueva
1. Crea `1_Practicas/LabNN_Tema_Problema/` con `docs/` y `src/`.
2. Pega el enunciado de Classroom en `docs/Instrucciones.md`.
3. Haz el diagrama en `docs/UML.drawio`. La plantilla del reporte está en `0_Recursos/03_Plantillas/`.
4. Escribe el código en `src/` (un archivo por clase, y el `main` en `Main.java`).
5. Compila con `compilar.ps1` o `compilar.sh` (sección 2).
6. Llena el `README.md` de la práctica (copia el de otra práctica como base).
7. Agrégala al índice del `README.md` principal.

---

## 2. Cómo compilar y ejecutar

### Lo mínimo que hay que entender
| Comando / opción | Qué hace |
|---|---|
| `javac` | **Compila**: convierte `.java` (texto) en `.class` (bytecode) |
| `java` | **Ejecuta** una clase que tenga `main` (la JVM interpreta el bytecode) |
| `-d output` | Guarda los `.class` en la carpeta `output/` (si no, quedan revueltos con el código) |
| `-cp output` | *classpath*: le dice a `java` dónde buscar los `.class` |
| `-encoding UTF-8` | Lee el código como UTF-8 (sin esto los acentos y la ñ de los `.java` se corrompen) |
| `-sourcepath src` | Deja que `javac` encuentre por su cuenta las clases que usa el archivo que compilas |

Se ejecuta el **nombre de la clase**, no el archivo: `java -cp output Main` (sin `.class`). Si la clase está en un paquete, va con el paquete: `java -cp output app.Main`.

### Con los scripts (recomendado)
Desde `1_Practicas/`:
```powershell
# Windows (PowerShell)
.\compilar.ps1 Lab04_Composicion_Cajero                    # detecta la clase con main
.\compilar.ps1 Lab01_Clases_AlumnoLibro -Main Principal2   # cuando hay varias con main
.\compilar.ps1 Taller05_Paquetes_Genericos                 # con paquetes también funciona
.\compilar.ps1 Lab04_Composicion_Cajero -SoloCompilar
```
```bash
# Linux / Git Bash / macOS
./compilar.sh Lab04_Composicion_Cajero
./compilar.sh Lab01_Clases_AlumnoLibro Principal2
./compilar.sh Lab04_Composicion_Cajero --solo-compilar
```
Los dos scripts compilan **todo** `src/` (subcarpetas incluidas) hacia `output/`, ponen la consola en UTF-8 y ejecutan el `main`.

> Si PowerShell no deja correr scripts: `Set-ExecutionPolicy -Scope CurrentUser RemoteSigned` (una sola vez).

### A mano — clases sin paquete (casi todas las prácticas)
Desde la carpeta de la práctica (`cd 1_Practicas\Lab04_Composicion_Cajero`):
```powershell
# PowerShell
chcp 65001                                         # consola en UTF-8 (para la ñ)
javac -encoding UTF-8 -d output src/*.java         # compila todo src/ -> output/
java "-Dstdout.encoding=UTF-8" -cp output Main     # ejecuta
```
```bash
# Bash
javac -encoding UTF-8 -d output src/*.java
java -Dstdout.encoding=UTF-8 -cp output Main
```

### A mano — con paquetes (Taller 5)
`src/*.java` **no** entra a subcarpetas, así que hay que pasarle todos los archivos:
```powershell
# PowerShell: cada ruta va como argumento separado (funciona con espacios de OneDrive)
javac -encoding UTF-8 -d output (Get-ChildItem src -Recurse -Filter *.java).FullName
java "-Dstdout.encoding=UTF-8" -cp output app.Main
```
```bash
# Bash: -print0 / -0 para que no se rompa con espacios en la ruta
find src -name "*.java" -print0 | xargs -0 javac -encoding UTF-8 -d output
java -Dstdout.encoding=UTF-8 -cp output app.Main
```
Regla de oro de paquetes: `package utils;` → el archivo va en `src/utils/` y su `.class` termina en `output/utils/`.

### Problemas típicos al compilar
| Síntoma | Causa | Solución |
|---|---|---|
| La ñ o los acentos salen como `?` o `Ã±` | La consola no está en UTF-8 | `chcp 65001` + `-encoding UTF-8` al compilar + `-Dstdout.encoding=UTF-8` al ejecutar |
| `javac @archivo.txt` falla | Las rutas de OneDrive tienen espacios y el *argfile* las parte | Pasar los archivos como en los ejemplos de arriba |
| `Could not find or load main class` | Le pasaste el archivo (`Main.java`) o te faltó el paquete | `java -cp output Main` o `java -cp output app.Main` |
| `class X is public, should be declared in a file named X.java` | El nombre del archivo no coincide con la clase | Renombrar el archivo, respetando mayúsculas (`nodo.java` → `Nodo.java`) |
| `java -version` y `javac -version` muestran versiones distintas | Hay dos JDK instalados en el PATH | Dejar solo el JDK 27 en el PATH / `JAVA_HOME` |
| `illegal character: '¿'` o similar | Archivo guardado en otra codificación, o caracteres basura | Guardar en UTF-8 y revisar la línea del error |

### En VS Code
- **Run** (▶) en un archivo con `main`: usa *Code Runner* (`.vscode/settings.json`), que compila a `../output`.
- **F5 → "Depurar archivo actual (output)"**: compila con la tarea `Compilar Java (output)` y depura con breakpoints.
- Para prácticas con paquetes es más confiable usar `compilar.ps1`.

---

## 3. Calendario y evaluación

### Plan de actividades 2026-2 (del anexo del encuadre)
| Sem | Unidad | Actividades |
|---|---|---|
| 1 | U1 | Firma de encuadre, instalación del JDK, tarea de paradigmas OO |
| 2 | U1 | Modelado de clases y objetos · Lab de clases y objetos |
| 3 | U1 | Modelado de nivel de acceso y métodos · Lab de nivel de acceso y métodos |
| 4 | U2 | **Examen 1 (2 sep)** · Lab de composición |
| 5 | U2 | Modelado de composición · Lab de herencia |
| 6 | U2 | Modelado de herencia · Lab de paquetes (16 sep festivo) |
| 7 | U3 | Modelado de herencia y paquetes · **Examen 2 (23 sep)** |
| 8 | U3 | Modelado de composición, herencia y paquetes · Lab de polimorfismo |
| 9 | U3 | Modelado de polimorfismo · Lab de interfaces |
| 10 | U3 | Modelado de interfaces · Lab de clases abstractas |
| 11 | U4 | Modelado de clases abstractas · **Examen 3 (21 oct)** |
| 12 | U4 | Lab de cohesión |
| 13 | U4 | Lab de acoplamiento (2 nov festivo) |
| 14 | U5 | Modelado de cohesión y acoplamiento · Lab de concurrencia y paralelismo |
| 15 | U5 | **Examen 4 (18 nov)** · Avance de proyecto (16 nov festivo) |
| 16 | U5 | Avances de proyecto (23 y 25 nov) · **Entrega del proyecto (26 nov)** |

Cada lab vale 0.91%, cada modelado (taller) 0.46%, cada examen 18.75% y cada avance o entrega del proyecto 2.5%.

### Evaluación
| Criterio | Valor |
|---|---|
| Laboratorios (código) | 10% |
| Talleres (diagramas) | 5% |
| Proyecto | 10% |
| Exámenes (4) | 75% |

- **Exentar el ordinario:** promedio ≥ 80, haber entregado el proyecto final y no haber reprobado más de un examen.
- **Derecho a ordinario:** ≥ 80% de asistencia. **Extraordinario:** ≥ 60%.

### Proyecto final
- Debe usar **herencia, interfaces, polimorfismo y clases abstractas** (e hilos y serialización, si se alcanzan a ver).
- Estructura modular: un folder para el código fuente (`src/`), un folder para el ejecutable (`output/`) y un archivo por clase.

---

## 4. Teoría de POO

### 4.1 Paradigma orientado a objetos
- **Clase:** el molde. Define atributos (estado) y métodos (comportamiento).
- **Objeto:** una instancia concreta de la clase, creada con `new`.
- **Los 4 pilares:**
  - **Abstracción:** modelar solo lo relevante del problema.
  - **Encapsulamiento:** ocultar el estado y exponer solo una interfaz controlada.
  - **Herencia:** reutilizar y especializar clases.
  - **Polimorfismo:** un mismo mensaje con distintos comportamientos según el objeto.

```java
Mascota m = new Mascota("Firulais", 3);   // m es una referencia a un objeto Mascota
```

### 4.2 Encapsulamiento y niveles de acceso
| Modificador | Misma clase | Mismo paquete | Subclase | Todos | UML |
|---|:-:|:-:|:-:|:-:|:-:|
| `private` | ✅ | ❌ | ❌ | ❌ | `-` |
| *(sin modificador)* | ✅ | ✅ | ❌* | ❌ | `~` |
| `protected` | ✅ | ✅ | ✅ | ❌ | `#` |
| `public` | ✅ | ✅ | ✅ | ✅ | `+` |

\* Solo si la subclase está en otro paquete.

- Los atributos van `private`, y se accede a ellos con **getters/setters**.
- El setter es el lugar para **validar**: en el Lab 12, la calificación de una reseña se ajusta a 1..5 en el setter.
- No todo atributo necesita un setter público. El PIN del Lab 7 lo genera el Banco y solo se cambia con una operación controlada.

### 4.3 Constructores, `this`, `static`, `final`
```java
public class CuentaBancaria {
    private static int siguienteFolio = 1000;   // static: compartido por TODAS las cuentas
    public static final int MAX_CUENTAS = 10;   // constante de clase
    private final int numeroCuenta;             // final: se asigna una sola vez
    private double saldo;

    public CuentaBancaria(double saldoInicial) {  // constructor: mismo nombre, sin tipo de retorno
        this.numeroCuenta = siguienteFolio++;     // this = el objeto que se está creando
        this.saldo = saldoInicial;
    }
}
```
- **Sobrecarga de constructores:** `Circulo()` y `Circulo(double radio)` (Lab 2).
- **`static`** pertenece a la clase, no al objeto. Por eso `main` es `static`, y por eso la `Calculadora` del Lab 2 no se instancia.
- **`toString()`** se sobrescribe para imprimir el objeto con `System.out.println(obj)`.

### 4.4 Relaciones entre clases
| Relación | Significado | En Java | UML | Ejemplo del curso |
|---|---|---|---|---|
| **Dependencia** | "usa" temporalmente | Parámetro o variable local | `- - - >` | `Doctor.diagnosticar(Mascota m)` (Taller 7) |
| **Asociación** | "conoce a" | Atributo con una referencia a un objeto que ya existe | `———>` | `Cajero` → `Banco` (Lab 7) |
| **Agregación** | "tiene", pero las partes viven por su cuenta | Arreglo de referencias que llegan de afuera | `◇———` | `Equipo` ◇ `Empleado` (Lab 5) |
| **Composición** | "es dueño de": crea y destruye las partes | La clase hace el `new` de sus partes | `◆———` | `Banco` ◆ `CuentaBancaria` (Labs 4 y 7) |
| **Herencia** | "es un" | `extends` | `———▷` | `Perro extends Mascota` |
| **Realización** | "cumple el contrato" | `implements` | `- - -▷` | `Email implements IOcultar` (Lab 11) |

Para decidir entre agregación y composición, pregúntate **quién hace el `new` y qué pasa con las partes si se destruye el todo**. En el Lab 5, la Empresa contrata (crea) a los empleados y los equipos solo los agrupan: Empresa ◆ Empleado, Equipo ◇ Empleado.

**Patrón "repositorio con arreglo"** (el curso pide arreglos, no `ArrayList`):
```java
private Mascota[] mascotas = new Mascota[50];
private int contador = 0;

public boolean registrar(Mascota m) {
    if (contador >= mascotas.length) { System.out.println("Error: lleno"); return false; }
    mascotas[contador++] = m;
    return true;
}
// Eliminar = recorrer a la izquierda los de la derecha y bajar el contador
```

### 4.5 Herencia
```java
public class Mascota {                       // superclase
    protected String nombre;                 // protected: lo ven las subclases
    public Mascota(String nombre) { this.nombre = nombre; }
    public String examinar() { return "Revision general"; }
}

public class Perro extends Mascota {         // subclase
    private boolean vacunaRabia;
    public Perro(String nombre, boolean vacuna) {
        super(nombre);                       // llama al constructor de la superclase (va primero)
        this.vacunaRabia = vacuna;
    }
    @Override                                // el compilador verifica que sí sobrescribes algo
    public String examinar() { return "Revision canina, rabia: " + vacunaRabia; }
}
```
- Java tiene **herencia simple** de clases: un solo `extends`. La "herencia múltiple" se logra con interfaces.
- **`super.metodo()`** reutiliza la versión del padre, por ejemplo `toString()` en `Perro` que usa `super.toString()`.
- **Upcasting** (automático): `Mascota m = new Perro(...)`.
- **Downcasting** (manual, con cuidado): `if (m instanceof Perro p) { ... }`.

### 4.6 Polimorfismo
- **Sobrecarga** (en compilación): mismo nombre, distintos parámetros. Ejemplo: `perimetro()` y `perimetro(ladoA, ladoB)`.
- **Sobrescritura** (en ejecución, *dynamic dispatch*): la JVM llama la versión del **tipo real** del objeto, no la del tipo de la variable.
```java
Mascota[] pacientes = { new Perro("Max", true), new Gato("Michi", true), new Ave("Piolin", true) };
for (Mascota m : pacientes)
    System.out.println(m.examinar());   // cada uno ejecuta SU examinar()
```
Así funcionan `Doctor.diagnosticar(Mascota)` (Taller 7), `Exportador.exportar()` (Lab 10) y `Organo.aplicarEfecto()` (Taller 1). Agregar un tipo nuevo no obliga a tocar el código que lo usa.

### 4.7 Clases abstractas e interfaces
| | Clase abstracta | Interfaz |
|---|---|---|
| Palabra clave | `abstract class` / `extends` | `interface` / `implements` |
| ¿Se instancia? | No | No |
| Atributos | Sí (de instancia) | Solo constantes (`public static final`) |
| Métodos | Abstractos y concretos | Abstractos (+ `default`/`static` desde Java 8) |
| ¿Cuántas? | Una sola (`extends`) | Varias (`implements A, B, C`) |
| Relación | "es un" con código compartido | "puede hacer" (un contrato) |

```java
public interface IOcultar { String ocultar(); }          // contrato

public class Tarjeta implements IOcultar {
    private String numero;
    @Override public String ocultar() { return "****-****-****-" + numero.substring(numero.length() - 4); }
}

IOcultar[] datos = { new Email("a@uabc.edu.mx"), new Tarjeta("1234-5678-321-6543") };  // polimorfismo por interfaz
```
- En el Taller 3, la clase `Numero` implementa **tres** interfaces a la vez.
- En el Taller 2, `TerminalPOS` revisa con `instanceof IAgregable` / `IRemovible` qué puede hacer cada platillo.
- Nombres: el curso usa el prefijo `I` (`IOcultar`, `IValidar`). En el Lab 12 se usó un nombre de rol (`Filtro`, `CalcularCalificacion`).

### 4.8 `enum`
```java
public enum Veredicto {
    APROBADA, REVISION_MANUAL, RECHAZADA;                     // orden = severidad
    public boolean esMasSeveroQue(Veredicto otro) { return this.ordinal() > otro.ordinal(); }
}
```
Un conjunto cerrado de valores con seguridad de tipos. Mejor que usar `String` o `int` "mágicos" (Labs 4 y 12, Taller 4).

### 4.9 Paquetes
```java
package utils;            // primera línea: el archivo vive en src/utils/
public class Lista<V> { ... }

package app;
import utils.Lista;       // importar una clase de otro paquete
```
- Sirven para organizar, evitar choques de nombres y controlar el acceso: sin modificador = visible solo en el paquete.
- La carpeta debe coincidir con el paquete, y se ejecuta con el nombre completo: `java -cp output app.Main`.
- Convención de Java: paquetes en minúsculas (`reglas`, `motor`). El ejemplo del profe del Taller 5 usa `Reglas` y `MotorReglas`; compila igual, pero no sigue la convención.

### 4.10 Genéricos
```java
public class Nodo<T> {           // T = tipo que se decide al usar la clase
    public T valor;
    public Nodo<T> siguiente;
    public Nodo(T valor) { this.valor = valor; }
}
Lista<Integer> numeros = new Lista<>();   // diamond operator <>
Lista<String>  nombres = new Lista<>();
```
- Convención de letras: `T` *type*, `E` elemento, `K` *key*, `V` *value*, `N` número.
- Solo aceptan tipos objeto: `Lista<Integer>`, no `Lista<int>` (autoboxing).
- Evitan *casts* y detectan errores de tipo al compilar.

### 4.11 Cohesión y acoplamiento
- **Alta cohesión ✅:** cada clase tiene **una** responsabilidad clara. En el Lab 7, `Banco` administra las cuentas, `Cajero` maneja la sesión y `Main` solo es la interfaz con el usuario.
- **Bajo acoplamiento ✅:** las clases dependen de abstracciones (interfaces), no de detalles. `Moderador` recibe un `Filtro[]` y no sabe qué filtros concretos son (Lab 12).
- Señales de alarma: un `Main` que hace todo, clases que acceden a los atributos de otras, `if (tipo == "perro") ... else if ...` en lugar de polimorfismo.

### 4.12 Patrones de diseño que salieron en el curso
| Patrón | Idea | Dónde |
|---|---|---|
| **Strategy** | Intercambiar algoritmos detrás de una interfaz | `CalcularCalificacion` (simple/bayesiano), `Filtro`, `Exportador` |
| **Composite** | Un objeto compuesto se trata igual que uno simple | `ReglaAnd(Regla a, Regla b)` (Taller 4) |
| **Template / Hook** | La superclase define el flujo y las subclases rellenan pasos | `Restaurante.crearPlatillo()` (Taller 2) |
| **Repositorio** | Una clase administra la colección y su CRUD | `Banco`, `Veterinaria`, `Empresa` |

### 4.13 Unidad 4-5: persistencia y concurrencia (adelanto)
- **Persistencia:** guardar objetos más allá de la ejecución. Con archivos de texto (`FileWriter`/`BufferedReader`) o con serialización (`implements Serializable` + `ObjectOutputStream`).
- **Concurrencia:** varios hilos al mismo tiempo, con `extends Thread` / `implements Runnable` y `start()`. Para proteger datos compartidos se usa `synchronized`.

---

## 5. Chuleta de UML

```
┌──────────────────────────┐
│       NombreClase        │   «interface» / «abstract» / «enum» arriba del nombre
├──────────────────────────┤
│ - atributo: Tipo         │   - private  + public  # protected  ~ paquete
│ - CONST: int {static}    │   subrayado o {static} = de clase
├──────────────────────────┤
│ + metodo(p: Tipo): Ret   │   en cursiva = abstracto
└──────────────────────────┘
```
| Flecha | Relación |
|---|---|
| `———▷` línea continua, triángulo vacío | Herencia (`extends`) |
| `- - -▷` punteada, triángulo vacío | Realización (`implements`) |
| `◆———` rombo lleno del lado del dueño | Composición |
| `◇———` rombo vacío del lado del contenedor | Agregación |
| `———>` línea continua, flecha abierta | Asociación |
| `- - ->` punteada, flecha abierta | Dependencia |

**Multiplicidad:** `1`, `0..1`, `0..*`, `1..*`, `0..50`. Por ejemplo, `Banco 1 ◆—— 0..10 CuentaBancaria`.

Tips para draw.io: todo en **una sola hoja**, una caja de leyenda que explique las flechas y las clases ordenadas de arriba (`Main`) hacia abajo (clases hoja). Exporta a `.png`/`.svg` si lo vas a pegar en el reporte.

---

## 6. Convenciones de código usadas

- Todo en español: clases, métodos, comentarios y mensajes.
- **Un archivo por clase.** El programa principal se llama `Main` y **no** es la clase de dominio ("la veterinaria no es el main").
- Encabezado en cada archivo:
  ```java
  /* ****************************************************************
   * Laboratorio N: Clase X
   * Descripcion corta de la responsabilidad de la clase
   * @Author: Joshua Osorio Osorio
   * @Date:   Mes/2026
   * **************************************************************** */
  ```
- Secciones con `/* ********* Nombre ********* */` y getters/setters agrupados.
- Colecciones con **arreglo de tamaño fijo + contador** (no `ArrayList`).
- Los errores se reportan con `System.out.println("Error: ...")` y la función regresa `false`/`null`, en lugar de lanzar excepciones.
- **Lectura segura** con `leerEntero()` / `leerDouble()`, que atrapan `InputMismatchException` y limpian el buffer con `nextLine()`.
- **`test()` oculto en el menú:** una opción no listada que ejercita todos los métodos con casos válidos e inválidos, sin teclado. Es la forma de verificar antes de usar el menú. Muchas prácticas también tienen la opción "Cargar datos de prueba".

---

## 7. Errores comunes y cómo se resolvieron

| Error | Dónde salió | Solución |
|---|---|---|
| `IllegalFormatConversionException: d != java.lang.String` | Lab 6, `String.format` | El formato decía `#-5d` en lugar de `%-5d`; el especificador inválido desfasa todos los argumentos |
| Doble punto al imprimir el diagnóstico | Lab 6 | El formato ya traía el punto y el texto también; se quitó del formato |
| La ñ sale como `?` | Lab 6, Taller 5 | `javac -encoding UTF-8` + `chcp 65001` + `-Dstdout.encoding=UTF-8` |
| `nextInt()` seguido de `nextLine()` "se salta" la lectura | Lab 2 y todos los menús | Después de `nextInt()`/`nextDouble()` llamar `nextLine()` para consumir el Enter |
| `InputMismatchException` al escribir letras en un número | Menús | `try/catch` en `leerEntero()` |
| `NullPointerException` al recorrer un arreglo | Arreglos + contador | Recorrer hasta `contador`, no hasta `length` |
| `class Nodo is public, should be declared in a file named Nodo.java` | Taller 5 | El archivo se llamaba `nodo.java`; en Java importan las mayúsculas aunque Windows no las distinga |
| `cannot find symbol: class T` | Taller 5 | La clase era `Lista<V>` pero el método usaba `T`; el parámetro de tipo debe ser el mismo |
| Comparar `String` con `==` | Lab 1 (búsqueda de libros) | Usar `.equals()` / `.equalsIgnoreCase()`: `==` compara referencias |
| `illegal character: '`'` | Taller 3 | Caracteres basura pegados por accidente (`input.nextLine();``) |

---

## 8. Reglas del curso
- Está **prohibido usar IA durante los laboratorios y talleres en clase**; la sanción es la anulación de la asistencia.
- 3 retardos = 1 falta. Las faltas solo se justifican con un justificante de la Dirección.
- Plataforma oficial: **Google Classroom**.
- Si llegas tarde, entra sin interrumpir la clase.
