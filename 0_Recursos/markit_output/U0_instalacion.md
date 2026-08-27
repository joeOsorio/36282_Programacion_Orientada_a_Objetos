| LPOO |     |              |     | JAVA |     |
| ---- | --- | ------------ | --- | ---- | --- |
|      |     | Instalaci´on | de  |      |     |
Para facilitar el proceso de instalaci´on del libro Core Java Vol-
| umen 1 Fundamentals |     | leer | el capitulo | 2 donde | detalla como |
| ------------------- | --- | ---- | ----------- | ------- | ------------ |
se instala.
| 1 Descargar | el JDK | ya sea | de la | pagina oficial |     |
| ----------- | ------ | ------ | ----- | -------------- | --- |
https://www.oracle.com/java/technologies/downloads/
| o de la       | versi´on | opensource | https://adoptium.net/ |               |             |
| ------------- | -------- | ---------- | --------------------- | ------------- | ----------- |
| se recomienda | que      | bajen el   | LTS ya                | que es el que | tiene mejor |
soporte
66

| LPOO |     |              |     | JAVA |     |     |
| ---- | --- | ------------ | --- | ---- | --- | --- |
|      |     | Instalaci´on |     | de   |     |     |
WINDOWS
1 ejecutar el instalador Cambiar la direcci´on de instalaci´on
| a una | direcci´on | sin | espacios | ejemplo: |     |     |
| ----- | ---------- | --- | -------- | -------- | --- | --- |
1 C: JDK
\
| 2 ir a | sus variables | de  | entorno | y agregar | el folder | bin en |
| ------ | ------------- | --- | ------- | --------- | --------- | ------ |
| donde  | instalaron    | el  | JDK por | ejemplo   |           |        |
|        |               |     | 1 C:    | JDK bin   |           |        |
\ \
| abrir | una consola | y   | teclear | javac –version | debe | aparecer |
| ----- | ----------- | --- | ------- | -------------- | ---- | -------- |
3
| la versi´on | que | acaban | de  | instalar del JDK |     |     |
| ----------- | --- | ------ | --- | ---------------- | --- | --- |
77

| LPOO |     |              |     | JAVA |     |
| ---- | --- | ------------ | --- | ---- | --- |
|      |     | Instalaci´on | de  |      |     |
MAC
| ejecutar                                          | el instalador(por |     | lo general | lo instala | en    |
| ------------------------------------------------- | ----------------- | --- | ---------- | ---------- | ----- |
| /Library/Java/JavaVirtualMachines/jdk-x.x.x.jdk/) |                   |     |            |            | x.x.x |
es la versi´on.
| abrir una   | consola | y teclear | javac       | –version debe | aparecer |
| ----------- | ------- | --------- | ----------- | ------------- | -------- |
| la versi´on | que     | acaban    | de instalar | del JDK       |          |
88

| LPOO |     |              |     | JAVA |     |     |     |
| ---- | --- | ------------ | --- | ---- | --- | --- | --- |
|      |     | Instalaci´on |     | de   |     |     |     |
LINUX
| descargar | el      | archivo       | .tar.gz |     |         |     |          |
| --------- | ------- | ------------- | ------- | --- | ------- | --- | -------- |
| abre la   | consola | y descomprime |         | el  | archivo | con | tar -xvf |
archivo.tar.gz
| copiar    | el folder | que | se creo                      | en /opt  |            |           |         |
| --------- | --------- | --- | ---------------------------- | -------- | ---------- | --------- | ------- |
|           |           |     | 1terminal>sudocp−rjdk−23.0.x |          | /opt/jdk   |           |         |
| agregar   | el folder | en  | las variables                |          | de entorno | (abrir    | en      |
| cualquier | editor    | de  | texto                        | /.bashrc | , si       | no existe | crealo) |
→
.bashrc
1exportPATH=/opt/jdk/bin:$PATH
| reinicia | la consola | o   | en consola | teclear |     | source | /.bashrc |
| -------- | ---------- | --- | ---------- | ------- | --- | ------ | -------- |
→
| teclear | javac       | –version | debe    | aparecer | la  | versi´on | que |
| ------- | ----------- | -------- | ------- | -------- | --- | -------- | --- |
| acaban  | de instalar |          | del JDK |          |     |          |     |
99

| LPOO |     | Instalaci´on |     |     |     |
| ---- | --- | ------------ | --- | --- | --- |
Probar
| Java es  | un lenguaje 100%      | Orientado | a objetos  | por lo que | la  |
| -------- | --------------------- | --------- | ---------- | ---------- | --- |
| funci´on | principal esta dentro | de una    | clase como | un m´etodo |     |
est´atico y tambi´en el archivo.java se tiene que llamar como la
| primera | clase de dicho archivo | (con | mayu´sculas | y todo) |     |
| ------- | ---------------------- | ---- | ----------- | ------- | --- |
1100

| LPOO | Instalaci´on |     |     |
| ---- | ------------ | --- | --- |
Probar
| crea un archivo | llamado HolaMundo.java |     |     |
| --------------- | ---------------------- | --- | --- |
1
| en un editor | de texto abrelo | y pega el siguiente | c´odigo |
| ------------ | --------------- | ------------------- | ------- |
2
HolaMundo.java
| 1 public | class HolaMundo |                    |       |
| -------- | --------------- | ------------------ | ----- |
| 2 {      |                 |                    |       |
| 3        | static public   | void main(String[] | args) |
4
{
| 5   | System.out.println(”Hola | Mundo!”); |     |
| --- | ------------------------ | --------- | --- |
| 6   | }                        |           |     |
7
}
1111

| LPOO |     | Instalaci´on |     |     |     |     |
| ---- | --- | ------------ | --- | --- | --- | --- |
Probar
| 3 para compilar | es necesario |     | abrir | la terminal |     | y teclear lo |
| --------------- | ------------ | --- | ----- | ----------- | --- | ------------ |
siguiente:
terminal
|             | 1 folder             | programa  | > javac     | HolaMundo.java |            |         |
| ----------- | -------------------- | --------- | ----------- | -------------- | ---------- | ------- |
| Esto genera | un ‘HolaMundo.class‘ |           |             | es             | un archivo | que     |
| contiene    | el c´odigo java      | traducido |             | en             | byte code  | para la |
| maquina     | virtual lo pueda     |           | ejecutar    |                |            |         |
| Finalmente  | para ejecutar        |           | el programa |                | se ejecuta |         |
4
| llamando | java con el | nombre | de  | la clase | a ejecutar |     |
| -------- | ----------- | ------ | --- | -------- | ---------- | --- |
terminal
|     | 1 folder | programa | > java | HolaMundo |     |     |
| --- | -------- | -------- | ------ | --------- | --- | --- |
1122

| LPOO |     | texto | VIM |     |
| ---- | --- | ----- | --- | --- |
Editor de
| Para este  | curso usaremos | el editor       | de texto llamado  | VIM,   |
| ---------- | -------------- | --------------- | ----------------- | ------ |
| ya que     | es r´apido,    | no consume      | mucha memoria ram | y esta |
| disponible | para todos     | los sistemas.   |                   |        |
| Lo pueden  | descargar      | en la siguiente | p´agina.          |        |
https://www.vim.org/download.php
1133

| LPOO   |                 | texto     | VIM                     |
| ------ | --------------- | --------- | ----------------------- |
|        | Editor          | de        |                         |
| VIM se | caracteriza por | que no es | necesario usar el mouse |
para nada, por lo que tiene muchas formas de realizar ciertas
operaciones, para tener una referencia de lo b´asico la siguiente
| pagina nos | en lista algunas | operaciones | b´asicas |
| ---------- | ---------------- | ----------- | -------- |
https://vim.rtorr.com/
1144

| LPOO           |                | texto       | VIM           |         |       |
| -------------- | -------------- | ----------- | ------------- | ------- | ----- |
|                | Editor         | de          |               |         |       |
| Durante        | el curso vamos | a ir viendo | como hacer    | ciertas | cosas |
| con el editor, | pero en        | sus tiempos | libres pueden | revisar | las   |
| siguientes     | gu´ıas:        |             |               |         |       |
https://vim-adventures.com/
http://vimgenius.com/
https://openvim.com/
1155