| Universidad | Aut´onoma   | de Baja   | California    |
| ----------- | ----------- | --------- | ------------- |
| Facultadad  | de Ciencias | Qu´ımicas | e Ingenier´ıa |
ISTE
Unidad 0
LPOO
|     |     | Repaso | Estructurada |
| --- | --- | ------ | ------------ |

LPOO
JAVA
InfluenciadeJava InfluenciadoporJava
C C#
C++ Scala
Java
Smalltalk Groovy
Kotlin
Dart
11

| LPOO    |             |                  | JAVA      |               |
| ------- | ----------- | ---------------- | --------- | ------------- |
|         |             | Historia         | de        |               |
| Java es | un lenguaje | de programaci´on | orientado | a objetos que |
fue desarrollado originalmente por un equipo de ingenieros de
| Sun Microsystems, |            | liderados | por **James Gosling**, | a princi- |
| ----------------- | ---------- | --------- | ---------------------- | --------- |
| pios de           | la d´ecada | de 1990.  |                        |           |
A finales de los 80 e inicios de los 90, Sun Microsystems bus-
caba nuevas formas de innovar y anticiparse a la tendencia de
la “computaci´on distribuida” y los dispositivos inteligentes.
22

| LPOO         |              |                  | JAVA          |          |          |          |
| ------------ | ------------ | ---------------- | ------------- | -------- | -------- | -------- |
|              |              | Historia         | de            |          |          |          |
| Se form´o    | un grupo     | de trabajo,      | conocido      | como     | el       | proyecto |
| “Green”,     | en el que    | participaban     | James         | Gosling, | Mike     | Sheri-   |
| dan, Patrick | Naughton,    | entre            | otros.        |          |          |          |
| En 1991,     | James        | Gosling comenz´o | a desarrollar |          | un       | lenguaje |
| llamado      | inicialmente | “Oak”,           | en referencia | a        | un roble | (oak     |
| tree) que    | ve´ıa desde  | su ventana.      |               |          |          |          |
La idea original de Oak era que fuera un lenguaje port´atil y
eficiente para dispositivos embebidos (por ejemplo, decodifi-
cadores de TV por cable, dispositivos dom´esticos inteligentes,
etc.).
| Sin embargo, | el mercado | de  | la tecnolog´ıa | de  | consumo | y los |
| ------------ | ---------- | --- | -------------- | --- | ------- | ----- |
dispositivos digitales au´n no estaba listo para un lenguaje as´ı.
33

| LPOO     |           |          |          |       | JAVA      |           |     |         |
| -------- | --------- | -------- | -------- | ----- | --------- | --------- | --- | ------- |
|          |           |          | Historia | de    |           |           |     |         |
| Oak se   | renombr´o | despu´es |          | como  | Java por  | motivos   | de  | marca y |
| registro | (adem´as  | de       | que      | “Oak” | ya estaba | utilizado | por | otro    |
software).
La principal innovaci´on de Java fue su lema “Write once, run
anywhere”(WORA),queselogr´ograciasalaJavaVirtualMa-
| chine (JVM)      | y              | a los    | applets,    | pequen˜os    | programas    |                  | que      | pod´ıan |
| ---------------- | -------------- | -------- | ----------- | ------------ | ------------ | ---------------- | -------- | ------- |
| correr           | en navegadores |          | compatibles |              | sin depender |                  | de la    | arqui-  |
| tectura          | del sistema.   |          |             |              |              |                  |          |         |
| Sun Microsystems |                |          | lanz´o      | oficialmente | Java         | 1.0              | en 1995, | con     |
| mucha            | publicidad     | enfocada |             | en su        | capacidad    | multiplataforma, |          |         |
ya que el mismo bytecode pod´ıa ejecutarse en diferentes sis-
temas operativos.
44

JAVA
| LPOO |     |     | Caracter´ısticas |     |     | de  |     |     |     |
| ---- | --- | --- | ---------------- | --- | --- | --- | --- | --- | --- |
Principal
|     | Portabilidad: |                     | “Escribir    | una        | vez,       | ejecutar   | en        | cualquier   |     |
| --- | ------------- | ------------------- | ------------ | ---------- | ---------- | ---------- | --------- | ----------- | --- |
|     | parte”        | (WORA),             | gracias      |            | a la       | JVM.       |           |             |     |
|     | Orientaci´on  | a                   | objetos:     | Un         | modelo     | de         | clases,   | interfaces  | y   |
|     | herencia      | simple              | y efectivo.  |            |            |            |           |             |     |
|     | Gesti´on      | autom´atica         |              | de memoria |            | (garbage   |           | collector): |     |
|     | Facilita      | la programaci´on    |              |            | y reduce   | errores    | de        | gesti´on    |     |
|     | manual        | de memoria.         |              |            |            |            |           |             |     |
|     | Seguridad     | y robustez:         |              | El         | bytecode   | se         | ejecuta   | en la       |     |
|     | m´aquina      | virtual,            | aislando     |            | el sistema | anfitri´on |           | de posibles |     |
|     | dan˜os        | o vulnerabilidades. |              |            |            |            |           |             |     |
|     | Amplio        | ecosistema:         |              | Frameworks |            | como       | Spring,   | JavaEE      |     |
|     | (Jakarta      | EE),                | herramientas |            | de         | desarrollo | (Eclipse, |             |     |
|     | IntelliJ,     | NetBeans)           |              | y una      | comunidad  |            | muy       | activa.     |     |
55

| LPOO | [Comentarios] |     |     |
| ---- | ------------- | --- | --- |
Java
| comentario  | de una linea  |            |              |
| ----------- | ------------- | ---------- | ------------ |
| comentario  | de            | una linea  |              |
| 1 //        | Esto es un    | comentario | de una linea |
| comentarios | de mu´ltiples | lineas     |              |
comentariomu´ltiplelineas
| 1 /∗∗ | Este estilo es JavaDoc |             |     |
| ----- | ---------------------- | ----------- | --- |
| 2 ∗   | Tambi´en funciona como | comentarios |     |
| 3 ∗   | De varias lineas       |             |     |
| 4 ∗   | o mas bien un bloque   | de codigo   |     |
| ∗     | @version 0.1           |             |     |
5
| 6 ∗ | @autor UABC FCQI |     |     |
| --- | ---------------- | --- | --- |
7 ∗ permite tener marcadores para generar documentaci´on utilizando
| 8 ∗ | JavaDoc |     |     |
| --- | ------- | --- | --- |
| 9 ∗ | /       |     |     |
1177

| LPOO |     | [Comentarios] |
| ---- | --- | ------------- |
Java
Existen est´andares para comentar co´digo y poder generar doc-
| umentaci´on | autom´atica | tales como: |
| ----------- | ----------- | ----------- |
JavaDoc
PyDoc
1188

Primitivos]
| LPOO |     | datos |
| ---- | --- | ----- |
[Tipos
Java
Java es un lenguaje que esta fuertemente tipado , esto quiere
decir que al definir una variable se debe conocer de que tipo
es.
| Java tiene | 8 tipos | de datos primitivos |
| ---------- | ------- | ------------------- |
1199

LPOO
Java
[Tipos datos
Primitivos]
2200

LPOO
Java
[Tipos datos
Primitivos]
2211

Primitivos]
| LPOO |     | datos |     |
| ---- | --- | ----- | --- |
[Tipos
Java
char
Originalmente se usaba para describir caracteres individuales
peroyanoeselcaso,actualmenteseutilizaparadescribir/utilizar
Unicode ya que algunos se puede utiliza con 1 car´acter y otros
| con 2, se representan | con la | comillas simple. |     |
| --------------------- | ------ | ---------------- | --- |
char
| 1 char a = ’A’; |     |     |     |
| --------------- | --- | --- | --- |
2 //error
| 3 char b = ”B”; | //<− esto | no es un char, | es un String |
| --------------- | --------- | -------------- | ------------ |
2222

Primitivos]
| LPOO |     | datos |     |     |
| ---- | --- | ----- | --- | --- |
[Tipos
Java
boolean
estos tipo de dato solamente contiene dos valores true y false
| se utilizan | para evaluar | condiciones  | l´ogicas      |             |
| ----------- | ------------ | ------------ | ------------- | ----------- |
| En Java     | el 0 no se   | evalu´a como | falso como en | el lenguaje |
C/C++
bolean
|     | 1 boolean x=false; |     |     |     |
| --- | ------------------ | --- | --- | --- |
2 /∗ no quiere decir falso, quiere decir que X es igual a 0 y no compila
|     | 3 if(x== 0) |     |     |     |
| --- | ----------- | --- | --- | --- |
4
|     | 5 { ... |     |     |     |
| --- | ------- | --- | --- | --- |
∗/
6 }//
|     | 7 deber´ıa de ser |     |     |     |
| --- | ----------------- | --- | --- | --- |
|     | 8 if(x==false)    |     |     |     |
9 {
| 10  | ... |     |     |     |
| --- | --- | --- | --- | --- |
11
}
2233

LPOO
Java
[variables y
Constantes]
variables
1 //una variable no puede empezar con numero o caracter especial ni espacios
2 double salario;
3 float calificacion= 0.f;
4 int semestre=5;
5 boolean fin;
6 long poblacion mundial
7 char letra1;
8 //constantes en java se usa final
9 final double PI = 3.1415;
10 //la palabra const esta reservada como parte del lenguaje pero no se usa para nada
2244

| LPOO | [Enumeradores] |     |     |     |
| ---- | -------------- | --- | --- | --- |
Java
| Cuando una                                    | variable necesite | almacenar | un conjunto | de val- |
| --------------------------------------------- | ----------------- | --------- | ----------- | ------- |
| oresrestringidossepuedenutilizarenumeradores, |                   |           | porejemplo  |         |
| el taman˜o                                    | de una pizza      |           |             |         |
Enumeradores
| 1   | enum pizza tam = |     |     |     |
| --- | ---------------- | --- | --- | --- |
{
| 2   | EXTRAGRANDE, |     |     |     |
| --- | ------------ | --- | --- | --- |
| 3   | GRANDE,      |     |     |     |
| 4   | MEDIANA,     |     |     |     |
| 5   | CHICA        |     |     |     |
| 6   | ;            |     |     |     |
}
7
| 8   | //se usar´ıa de   | esta forma |              |     |
| --- | ----------------- | ---------- | ------------ | --- |
| 9   | pizza tam taman˜o | = pizza    | tam.MEDIANA; |     |
2255

LPOO
Java
[Operadores]
2266

| LPOO |     | Matem´aticas |     |     |
| ---- | --- | ------------ | --- | --- |
Funciones
| Utilizando | la clase | de java Math | tenemos acceso | a mu´ltiples |
| ---------- | -------- | ------------ | -------------- | ------------ |
funciones
Math.pow(valor)
Math.sqrt(valor)
Math.round(valor)
Math.sin(valor)
Math.cos(valor)
Math.tan(valor)
Math.exp(valor)
Math.log(valor)
Math.log10(valor)
| Math.PI | constante |     |     |     |
| ------- | --------- | --- | --- | --- |
| Math.E  | constante |     |     |     |
2277

| LPOO |     | de tipos |     |
| ---- | --- | -------- | --- |
Casteo
| Permite transformar | de un tipo | de dato | a otro compatible. |
| ------------------- | ---------- | ------- | ------------------ |
Cast
| 1 float x =              | 9.997f;  |             |           |
| ------------------------ | -------- | ----------- | --------- |
| 2 int z = (int)x;        | //casteo | de flotante | −> entero |
| 3 System.out.println(z); |          | //imprime   | 9         |
2288

| LPOO |     | de tipos |     |
| ---- | --- | -------- | --- |
Casteo
| La lineas punteadas | es donde hay | una perdida | de informaci´on |
| ------------------- | ------------ | ----------- | --------------- |
2299

Incremento/Decremento
LPOO
| Utiliza la | misma forma | que lenguaje | C para hacer | incrementos |     |
| ---------- | ----------- | ------------ | ------------ | ----------- | --- |
o decrementos
1 int i=7;
int j=7;
2
| 3 // esto  | da como resultado | 16, ya | que primero | incrementa | i   |
| ---------- | ----------------- | ------ | ----------- | ---------- | --- |
| 4 int x =  | 2 ∗ ++i;          |        |             |            |     |
| 5 // esto  | da como resultado | 14, ya | que primero | hace 2∗7   | y   |
| // despues | incrementa        | j;     |             |            |     |
6
| 7 int y = | 2 ∗ j++; |     |     |     |     |
| --------- | -------- | --- | --- | --- | --- |
3300

Relacionales
LPOO
Operadores
| los operadores | relacionales | son los mismo | que se manejan | en  |
| -------------- | ------------ | ------------- | -------------- | --- |
| cualquier otro | lenguaje     |               |                |     |
| ==, igual      | que          |               |                |     |
| !=, diferente  | que          |               |                |     |
| >, mayor       | que          |               |                |     |
| >=, mayor      | igual que    |               |                |     |
| <, menor       | que          |               |                |     |
| <=, menor      | igual que    |               |                |     |
| &&, and        | (l´ogico)    |               |                |     |
| or (l´ogico)   |              |               |                |     |
||
3311

LPOO Condicional
Operador
| Es un if de una sola | linea |         |        |
| -------------------- | ----- | ------- | ------ |
| 1 int menor          | = (x  | < y ) ? | x : y; |
| //equivalente        | a     | decir   |        |
2
| 3 if (x < | y)  |     |     |
| --------- | --- | --- | --- |
4
{
| 5 menor | = x; |     |     |
| ------- | ---- | --- | --- |
6
}
7 else
8
{
| 9 menor | = y; |     |     |
| ------- | ---- | --- | --- |
10
}
3322

| LPOO |     | BitWise |     |
| ---- | --- | ------- | --- |
Operadores
| >>, Corrimientos | ceros a | la derecha (divisi´on         | entera) |
| ---------------- | ------- | ----------------------------- | ------- |
| <<, Corrimientos | ceros a | la izquierda (multiplicaci´on |         |
entera)
| ˆ, operaci´on binaria | XOR |     |     |
| --------------------- | --- | --- | --- |
| ˜, operaci´on binaria | NOT |     |     |
| &, operaci´on binaria | AND |     |     |
| , operaci´on binaria  | OR  |     |     |
|
3333

| LPOO            |         | texto           |            |                |     |
| --------------- | ------- | --------------- | ---------- | -------------- | --- |
|                 | Cadenas | de              |            |                |     |
| Conceptualmente | con     | secuencias de   | caracteres | UNICODE        | ,   |
| java no tiene   | un tipo | de dato String, | por lo     | que para poder |     |
hacer uso se tiene que utilizar La clase String e instaciar un
objeto
3344

| LPOO |         | texto |     |
| ---- | ------- | ----- | --- |
|      | Cadenas | de    |     |
LosStringssonInmutablesquieredecirquenosepuedencam-
biar letras individuales ya que no es un arreglo de caracteres
| como en otros  | lenguajes,  | si no una clase  | concreta.             |
| -------------- | ----------- | ---------------- | --------------------- |
| Para modificar | letras      | individuales hay | que recrear una nueva |
| instancia o    | concatenar. |                  |                       |
3355

| LPOO            |         |          | texto |     |
| --------------- | ------- | -------- | ----- | --- |
|                 | Cadenas | de       |       |     |
| Ejemplo String  |         |          |       |     |
| String mistring | = ””;   | //cadena | vacia |     |
1
| String saludo | = ”Hola”; | //  |     |     |
| ------------- | --------- | --- | --- | --- |
2
3
4 //sub cadenas
| 5 String subcadena | = saludo.substring(0,3); |     |     | //”hol” |
| ------------------ | ------------------------ | --- | --- | ------- |
6
7 //concatenar
| 8 String concatenar | = saludo | +   | subcadena; | // ”holahol” |
| ------------------- | -------- | --- | ---------- | ------------ |
3366

| LPOO             |                                     | texto |     |
| ---------------- | ----------------------------------- | ----- | --- |
|                  | Cadenas                             | de    |     |
| Strings tienen   | m´etodos u´tiles                    | como: |     |
| equals           | if(saludo.equals(”hola”))           |       |     |
| equalsIgnoreCase | if(saludo.equalsIgnoreCase(”HOLA”)) |       |     |
| compareTo        | if(saludo.compareTo(”Hola”)==       |       | 0)  |
| length           | int tam = saludo.length             |       |     |
| charAt           | saludo.charAt(2)                    |       |     |
1 if(saludo.equalsIgnoreCase(”Hola”)) //si saludo es == a ”hola”
2
{
| 3   | System.out.println(”Bienvenido”); |     |     |
| --- | --------------------------------- | --- | --- |
4
}
3377

| LPOO |     |         | texto |     |     |
| ---- | --- | ------- | ----- | --- | --- |
|      |     | Cadenas | de    |     |     |
En el caso de String no se usa el operador == para ver si dos
| cadenas | de texto son | iguales, | ya que no son | texto si | no una |
| ------- | ------------ | -------- | ------------- | -------- | ------ |
instancia de una clase (objeto) y solo verificar´ıa que si est´an
| almacenadas | en el | mismo espacio | de memoria |     |     |
| ----------- | ----- | ------------- | ---------- | --- | --- |
3388

| LPOO |     | Input/Output |     |     |     |
| ---- | --- | ------------ | --- | --- | --- |
Para mostrar informaci´on en la consola (stdin) se utiliza la
librer´ıa est´andar en especifico System.out en donde podemos
| encontrar | funciones | como      |            |          |          |
| --------- | --------- | --------- | ---------- | -------- | -------- |
| print,    | imprime   | un String | en consola |          |          |
| println,  | imprime   | un String | en consola | y agrega | un salto |
de linea.
3399

LPOO Input/Output
Ejemplo
| 1 System.println(”Esto | es un print  | con salto de linea”); |
| ---------------------- | ------------ | --------------------- |
| 2 System.print(”print  | numero ”+2); |                       |
4400

LPOO Input/Output
| Se puede formatear | el texto        | que queremos | mostrar     | en la con- |
| ------------------ | --------------- | ------------ | ----------- | ---------- |
| sola, al estilo    | de C utilizando | la clase     | String y su | m´etodo    |
format
1System.out.println(String.format(”pi es %5.2f y euler en exponencial %.4e”, Math.PI, Math.E));
4411

LPOO Input/Output
Los caracteres de escape y sus significados para formatiar un
texto.
4422

| LPOO       |     |          |     | Input/Output |     |             |              |
| ---------- | --- | -------- | --- | ------------ | --- | ----------- | ------------ |
| Para poder |     | capturar | la  | informaci´on |     | del usuario | en Java ten- |
emos que usar la clase Scanner el cual adhiere el System.in y
| podemos    | guardarlo |          | en       | una variable. |           |                  |     |
| ---------- | --------- | -------- | -------- | ------------- | --------- | ---------------- | --- |
| Esta clase | contiene  |          | m´etodos | como:         |           |                  |     |
| next(),    |           | adquiere | el       | texto         | del stdin | sin espacios     |     |
| nextLine() |           | adquiere |          | el texto      | del       | stdin incluyendo |     |
espacios.
| nextInt(),nextFloat(),nexDouble() |                   |              |     |                                |          | adquiere     | el texto del |
| --------------------------------- | ----------------- | ------------ | --- | ------------------------------ | -------- | ------------ | ------------ |
| stdin                             | y                 | lo convierte |     | al dato                        | deseado  |              |              |
| para mas                          | informaci´on      |              | de  | los                            | m´etodos | que contiene | la clase,    |
| revisar                           | la documentaci´on |              |     |                                |          |              |              |
| https://devdocs.io/openjdk        |                   |              |     | 21/java.base/java/util/scanner |          |              |              |
4433

| LPOO |     | Input/Output |
| ---- | --- | ------------ |
Input
| 1 public class | Capturar |     |
| -------------- | -------- | --- |
2
| { public static | void main(String[] | args) |
| --------------- | ------------------ | ----- |
3
4 {
5 //capturar
| 6 Scanner | input = new Scanner(System.in); |     |
| --------- | ------------------------------- | --- |
7
| 8 System.println(”Cual | es                         | tu nombre?”);    |
| ---------------------- | -------------------------- | ---------------- |
| 9 // puede             | contener espacios          | por eso nextLine |
| 10 String              | nombre = input.nextLine(); |                  |
11 //String nombre = input.next(); //si no va contener espacios
| System.println(”En | que | semestre vas?”); |
| ------------------ | --- | ---------------- |
12
| 13 int | semestre = input.nextInt(); |     |
| ------ | --------------------------- | --- |
14 System.println(”Tu nombre es ”+ nombre +” y vas en el semestre ”+semestre );
15
}
16 }
4444

| LPOO |     |     |     | de  | c´odigo |     |     |
| ---- | --- | --- | --- | --- | ------- | --- | --- |
Bloques
| En  | java para | abrir | un bloque | de c´odigo |     | mejor conocido | como |
| --- | --------- | ----- | --------- | ---------- | --- | -------------- | ---- |
´ambito o en ingles scope, se tienen que poner las llaves que
{}
lo simboliza.
|     | 1   | public | class Main |     |     |     |     |
| --- | --- | ------ | ---------- | --- | --- | --- | --- |
2
{
|     | 3   | public | static | main(String |     | [] args) |     |
| --- | --- | ------ | ------ | ----------- | --- | -------- | --- |
4
{
|     | 5   |     | int x=0; |        |     |         |     |
| --- | --- | --- | -------- | ------ | --- | ------- | --- |
|     | 6   |     | //nuevo  | bloque | de  | co´digo |     |
{
|     | 7   |     | //existe | x          | en este | bloque     |     |
| --- | --- | --- | -------- | ---------- | ------- | ---------- | --- |
|     | 8   |     | int      | y=20;      |         |            |     |
|     | 9   |     | // fin   | del bloque |         | de co´digo |     |
}
|     | 10  |     | // ’y’ | no existe | en  | este bloque |     |
| --- | --- | --- | ------ | --------- | --- | ----------- | --- |
11
}
12
}
4455

LPOO
|     | if - | else |
| --- | ---- | ---- |
if-else
//#1
1
| 2 if( condicional | )   |     |
| ----------------- | --- | --- |
3
| 4 { //si | se cumple la condicional |     |
| -------- | ------------------------ | --- |
5 }//#2
6
7 if(condicional)
8 {
| 9 //si | se cumple la condicional |     |
| ------ | ------------------------ | --- |
1 0
1 1 }else
1 2 {
| 1 3 //si | no se cumple la | condicional |
| -------- | --------------- | ----------- |
1 4
}//#3
1 5
16 if(condicional1)
1 7
| 1 8 { // si | se cumple |     |
| ----------- | --------- | --- |
1 9 }else
2 0 if(condicional2)
2 1
| { // no | se cumple pero | verifica otra condicional |
| ------- | -------------- | ------------------------- |
2 2
2 3 }//...
| 2 4 muchos | else if |     |
| ---------- | ------- | --- |
25 else
2 6 {
| 2 7 // no | se cumple ninguna | condicional |
| --------- | ----------------- | ----------- |
28
}
4466

LPOO
switch
| Es diferente     | al que tradicionalmente |             | se maneja | en             | C, este |
| ---------------- | ----------------------- | ----------- | --------- | -------------- | ------- |
| puede tomar      | como valor,             | un String,  | enteros,  | o enumeradores |         |
| y puede regresar | valores,                | sin embargo | se puede  | utilizar       | como    |
| se usa en        | C.                      |             |           |                |         |
4477

LPOO
switch
| por ejemplo                  | como si fuera                | C           |                |
| ---------------------------- | ---------------------------- | ----------- | -------------- |
| 1 Scanner                    | in = new Scanner(System.in); |             |                |
| System.out.print(”Selecciona |                              | una opcion: | (1, 2,3,4) ”); |
2
| 3 int    | opcion = in.nextInt(); |     |     |
| -------- | ---------------------- | --- | --- |
| 4 switch | (opcion)               |     |     |
| 5 {      |                        |     |     |
| 6        | case 1:                |     |     |
| 7        | case 4:                |     |     |
| 8        | //co´digo              |     |     |
break;
9
| 10  | case 2: |     |     |
| --- | ------- | --- | --- |
case 3:
11
| 12  | //co´digo |     |     |
| --- | --------- | --- | --- |
break;
13
| 14   | default:                  |               |     |
| ---- | ------------------------- | ------------- | --- |
| 15   | System.out.println(”valor | incorrecto”); |     |
| 16 } |                           |               |     |
4488

LPOO
switch
Por ejemplo el modo java
1 Scanner in = new Scanner(System.in);
2 System.out.print(”Select an option (1, 2,3, 4) ”);
3 int choice = in.nextInt();
4 switch (choice)
5
{
6 case 1,4−>
7 //codigo
8 case 2,3−>
9 //codigo
10 default −>
11 Syste.out.println(”valor incorrecto”);
12
}
4499

LPOO
switch
| Otro ejemplo el modo | java                          |            |         |
| -------------------- | ----------------------------- | ---------- | ------- |
| 1 e n u m S i z      | e S M AL L , M ED IUM, LARGE, | EXTRALARGE | ; . . . |
| 2 S i z e i t e m    | S{iz e = L A R GE ;           |            | }       |
| String label         | = switch (itemSize)           |            |         |
3
4 {
| 5 case | SMALL−> ”S”; // no | need to use | Size.SMALL |
| ------ | ------------------ | ----------- | ---------- |
| 6 case | MEDIUM−> ”M”;      |             |            |
| 7 case | LARGE−> ”L”;       |             |            |
| case   | EXTRALARGE−> ”XL”; |             |            |
8
9 } ;
10
| 11 int numLetters | = switch (seasonName) |     |     |
| ----------------- | --------------------- | --- | --- |
1 2
| { case | ”Spring”, ”Summer”, | ”Winter”−> | 6;  |
| ------ | ------------------- | ---------- | --- |
1 3 ”Fall”−>
| 14 case    | 4;     |     |     |
| ---------- | ------ | --- | --- |
| 15 default | −> −1; |     |     |
16 ;
17 }
| String seasonName | = switch (seasonCode) |     |     |
| ----------------- | --------------------- | --- | --- |
18
1 9 {
| 2 0 case   | 0−> ”Spring”; |     |     |
| ---------- | ------------- | --- | --- |
| 21 case    | 1−> ”Summer”; |     |     |
| 22 case    | 2−> ”Fall”;   |     |     |
| case       | 3−> ”Winter”; |     |     |
| 23         | −>            |     |     |
| 24 default | ”???”;        |     |     |
25 } ;
5500

LPOO
while
| Opera | de la misma manera | que lenguaje | C   |
| ----- | ------------------ | ------------ | --- |
|       | while              | sintaxis     |     |
|       | while              | ( condicio´n | )   |
1
2
{
|     | 3   | // repetir | codigo |
| --- | --- | ---------- | ------ |
4
}
5511

LPOO
do-while
Funciona igual que C
do-while sintaxis
1 // garantiza 1 repetici´on
2 do
3
{
4 //repetir co´digo
5 while(condicio´n );
}
5522

LPOO
for
| Funciona igual | que el lenguaje    | C                   |
| -------------- | ------------------ | ------------------- |
| 1 //           | (inicio ;condicion | termino;incremento) |
| 2 for(int      | i=0; i<10;++i)     |                     |
3
{
| 4   | //repeticion |     |
| --- | ------------ | --- |
5
}
5533

LPOO
foreach
Es un ciclo para iteradores (un arreglo o una colecci´on), en
| donde       | cada iteraci´on | agarramos       | un elemento | del arreglo | o   |
| ----------- | --------------- | --------------- | ----------- | ----------- | --- |
| colecci´on, | por lo que      | no es necesario | saber       | la cantidad | de  |
elementos.
|     | 1 int num[]=  | 1,2,3 ; |     |     |     |
| --- | ------------- | ------- | --- | --- | --- |
|     | for(int n { : | num) }  |     |     |     |
2
3
{
|     | 4 System.out.println(n); |     | //imprime | 1, 2, 3 |     |
| --- | ------------------------ | --- | --------- | ------- | --- |
5
}
5544

LPOO
break
Java ofrece la palabra reservada para interrumpir a los ciclos.
| break interrumpe | el ciclo m´as | pr´oximo. |
| ---------------- | ------------- | --------- |
break
| 1 for(int | i=0;i<100;i++) |     |
| --------- | -------------- | --- |
2
{ for(int j =0;j<100;++j)
3
4
{
| 5   | if( i+j == | 300) |
| --- | ---------- | ---- |
6
{
| 7   | break; | //rompe el ciclo interior |
| --- | ------ | ------------------------- |
| 8   | }      |                           |
9
}
10
}
5555

LPOO
break
| break tambi´en | puede actuar    | como | si fuera goto de ensam- |
| -------------- | --------------- | ---- | ----------------------- |
| blador y salta | a una etiqueta  |      |                         |
| 1 salir:       |                 |      |                         |
| 2 for(int      | i=0;i<100;i++)  |      |                         |
| 3 {            |                 |      |                         |
| 4 for(int      | j =0;j<100;++j) |      |                         |
5
{ if( i+j == 300)
6
7
| 8   | { break salir; |              |                        |
| --- | -------------- | ------------ | ---------------------- |
| 9   | // rompe ambos | ciclos ya    | que en realidad rompio |
| 10  | // el bloque   | de codigo de | la etiqueta salir      |
11
}
| 12 } |     |     |     |
| ---- | --- | --- | --- |
13
}
5566

LPOO continue
la palabra reservada continue actua para saltarse iteraciones
| pero no interrumpe        | ciclos. |     |     |
| ------------------------- | ------- | --- | --- |
| 1 for(int j =0;j<100;++j) |         |     |     |
2
{
| 3 if( (j+1)&1 | == 1) |     |     |
| ------------- | ----- | --- | --- |
4
{
| 5 continue; | //se salta todos | los numeros | pares |
| ----------- | ---------------- | ----------- | ----- |
6
}
7 }
5577

| LPOO |     |     | Arreglos |     |     |     |
| ---- | --- | --- | -------- | --- | --- | --- |
Unarregloenjava,almacenaunacolecci´ondedatosdelmismo
| tipo, | en donde | puedes | acceder | a cada dato | individual | con un |
| ----- | -------- | ------ | ------- | ----------- | ---------- | ------ |
index
|     | 1 //  | declaracion |     |     |     |     |
| --- | ----- | ----------- | --- | --- | --- | --- |
|     | 2 int | [] arreglo  | ;   |     |     |     |
3
//inicializacion
4
|     | arreglo | =   | new int | [10]; |     |     |
| --- | ------- | --- | ------- | ----- | --- | --- |
5
|     | float | [] otro | arreglo | = new | float [3]; |     |
| --- | ----- | ------- | ------- | ----- | ---------- | --- |
6
|     | 7 int | [] arr | = 1,2,3 | ;   |     |     |
| --- | ----- | ------ | ------- | --- | --- | --- |
{ }
5588

| LPOO |     | Arreglos |     |     |     |
| ---- | --- | -------- | --- | --- | --- |
una vez definidos la cantidad de elementos que va utilizar, no
se puede cambiar, por lo que si trata de ingresar indexes que
| est´an mas | altos que | la cantidad | el compilador |     | marcara una |
| ---------- | --------- | ----------- | ------------- | --- | ----------- |
excepci´on
Como los arreglos es una variable que apunta a una referencia
| de un espacio | en                  | memoria, no  | se puede | copiar | directamente |
| ------------- | ------------------- | ------------ | -------- | ------ | ------------ |
| debems        | de hacer uso        | de una clase | llamada  | Arrays | que se en-   |
| cuentra       | en java.util.Arrays |              |          |        |              |
5599

LPOO Arreglos
copiar arrreglos
| 1 import | java.util.Arrays; |     |     |
| -------- | ----------------- | --- | --- |
| public   | class Main        |     |     |
2
3
{
| 4 public | static void | main(String[] | args) |
| -------- | ----------- | ------------- | ----- |
5 {
| 6   | int a[3]=                               | 1,2,3 ; |     |
| --- | --------------------------------------- | ------- | --- |
|     | {                                       | }       |     |
| 7   | int[]b = a;                             |         |     |
| 8   | a[2]= 100;                              |         |     |
| 9   | //imprime 1,2,100                       |         |     |
| 10  | System.out.println(Arrays.toString(b)); |         |     |
| 11  | b = Arrays.copyOf(a,a.length);          |         |     |
| 12  | a[2]=10000;                             |         |     |
| 13  | //imprime 1,2,100                       |         |     |
| 14  | System.out.println(Arrays.toString(b)); |         |     |
15
}
16
}
6600

| LPOO         |                 |         | Funciones     |               |            |              |
| ------------ | --------------- | ------- | ------------- | ------------- | ---------- | ------------ |
| ** m´as      | adelante        | se      | profundizar´a | en            | arreglos y | en funciones |
| (m´etodos)   | **              |         |               |               |            |              |
| En java      | los par´ametros |         | de            | una funci´on  | son pase   | por valor    |
| en otras     | palabras        | copias, |               | no es posible | cambiar    | el valor de  |
| la variable, | pero            | lo que  | guarda        | si es una     | referencia | como un      |
| arreglo o    | un objeto       | si      | se puede      | modificar.    |            |              |
6611

LPOO Funciones
para estos ejemplos se utilizar´a el modificador static (se ver´a
m´as adelante el porque.)
p´arametros
1 import java.util.Arrays;
| 2 public class | Main |     |
| -------------- | ---- | --- |
3 4{
| public | static void main(String[] | args) |
| ------ | ------------------------- | ----- |
5 {
| 6 in t   | [] a rr ={1,2,3};             |              |
| -------- | ----------------------------- | ------------ |
| 7 m o d( | arr ) ;                       |              |
| 8 //     | como es por copia, la         | variable arr |
| 9 //     | no se modifica en la funci´on | mod          |
10 mod2(arr); System.out.println(Arrays.toString(arr));
| 11 //el  | elemento 2 de arr cambio   | a 10              |
| -------- | -------------------------- | ----------------- |
| 12 //    | ya que la variable arr     | tiene referencias |
| 13 14 // | al contenido en este caso  | a los elementos   |
| 15 //    | por lo que se puede editar |                   |
16 System.out.println(Arrays.toString(arr));
1 7
| 1 8 }public | static void mod(int[] | a)  |
| ----------- | --------------------- | --- |
1 9
| 2 0 { a=new | int[3]; |     |
| ----------- | ------- | --- |
21 a[0]=10;
22 a[1]=20;
23 a[2]=30;
2 4 }public
| 2 5 | static void mod2(int[] | a)  |
| --- | ---------------------- | --- |
2 6 {
2 7 a[2]=10;
2 8 }
2 9}
6622

| LPOO |     | completo |     |     |
| ---- | --- | -------- | --- | --- |
Ejemplo
| Se quiere | hacer un sistema | de calificaciones | de un sal´on | de  |
| --------- | ---------------- | ----------------- | ------------ | --- |
clases, en donde te muestre la lista de los alumnos, las califi-
| caciones | parciales y si el | alumno aprob´o o | no la materia. |     |
| -------- | ----------------- | ---------------- | -------------- | --- |
(por ahorita todo en el main y est´atico para no entrar a ori-
| entado | a objetos) |     |     |     |
| ------ | ---------- | --- | --- | --- |
6633

LPOO
Ejemplo
completo
1 2 i p m u p b o li r c tj c a l v a a ss .u S t i i s l t . e R m an a d C om al ; ificacion
3 4{ public staticvoidmain(String[]args)
5 6 7 8 9 { / / S f / / l t o 4 4 r a 0 0 in ∗ t g 5 S p t a f r a l l i r u o n c m g a i s t n a s o le s n s e [ u n ] [ lo ] = 0 s [] n = ew ne S w tri f n lo g a [ t 40 [4 ]; 0][5];
1 1 1 1 1 1 1 0 1 2 3 4 5 6 R / S S f / o a t t n r l r r d l i i ( e o n n i m n g g n a t r n r a a o i p n = m a e d l l 0 b l ; r i = i a e d < z s o n a 4 t e r t 0 e w e ; m e i m + p R l p a [ + ] n n [ ) = ] d o = o { m m { ” b O ” ( r M P e ) E A ; R R E ” Z , ” ” , P ” E G D O R N O Z ” A , LE ” S J ” U , A ” N HE ” R , NA ” N RO DE S Z A ” RI , O ”A ” V , A ” LO JE S S ” I , C ” A BA ” R , BA ”C ” A , R ”Q LO U S IN ” O , ” ” , AN ” N FL A O ” R , ES ”C ” A , R ”C L O A R ” DO}B ; A”};
1 1 1 2 7 8 9 0 { a f in o lu t r m ( n i i d n o t s = [ j i = r ] a 0 = n ; d j< n . o n 5 m e ;j x b + t r + I e n s ) t t ( e 7 m ) p ; [id]+””+apellidotemp[id];
2 2 2 1 2 3 { / p / ar e c l ia r l a e n s d [ o i m ][ e j] s = de (0. 0 4 . + 0 ra a nd 1. . 0 nextFloat())∗100.f;
2 2 2 2 2 4 5 6 7 8 }/ S f / o ys r i t m ( e } i p m n r t . im o a u i = r t. 0 l p ; a a r < in t 4 a t 0 l b ; n a l + a ( + S d ) t e rin a g pr . o fo b r o m n a o t(”|%−18s|%−5s|%−5s|%−5s|%−5s|%−5s|%−5s|%−10s|”,”NOMBRE”,”P1”,”P2”,”P3”,”P4”,”P5”,”TOTAL”,”APROBO”));
2 3 3 3 3 9 0 1 2 3 { S t p f o l y a o s t r t a a c e t l i m a ,A . l t e o o p s u t ro t a [ b a . l o ] p = [ ( r 0 t in ] o S t , t u l a p m n l a a ( ) r P S ) c a ) t ia ; r r c i l n i e a g s le [ . a s fo ( ] r [ p m 1 a ] a r t , c ( p i ” a a| l r e c % s ia [ − a l 1 e 8 ] s s ); [a|] % [2 − ] 2 , .3 p f ar|ci % al − e 2 s .3 [a f ][|3 % ], − p 2 a .3 r f cia|le % s − [ 2 a .3 ][ f 4]|, %−2.3f|%−2.3f|%b|”,alumnos[a],
3 3 3 4 5 6 }publ } ic static floatSumaParciales(float[]p)
3 3 3 7 8 9 { f f o lo r a ( t int s= i 0 = ; 0;i<p.length;i++)
4 4 0 1 { s+=p[i];
4 4 2 3 }returns/p.length;
4 4 4 5 }public staticbooleanAprobo(floatc)
4 4 6 7 { returnc>=60;
4 4 8 9} }
6644