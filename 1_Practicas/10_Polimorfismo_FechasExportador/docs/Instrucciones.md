Resuelve los siguientes ejercicios, usando herencia, polimorfismo y ligas dinámicas (dynamic distpach)

Ejercicio 1: Dado un número que representa el UNIX timestamp, calcular la fecha en diferentes formatos:
formato americano ( mes / día / año)
formato latino/europeo (día / mes / año)
formato internacional (año / mes / día)
El UNIX timestamp es lo que recibe de parámetro de constructor

para calcular se utiliza el siguiente algoritmo:
z = timestamp//86400
z+ = 719468
era = z // 146097
doe = z - era _146097
yoe = (doe - doe // 1460 + doe // 36524 - doe // 146096) // 365
doy = doe - (365 _ yoe + yoe //4 - yoe // 100)
mp = ( 5* doy +2) //153
dias = doy - (153 * mp +2) //5 + 1
mes = mp + 3 si mp < 10 de lo contrario mp -9
año = yoe + era \*400 + (mes <= 2)

// = división entera
prueba e imprime qué fechas son los siguientes timestamp en TODOS los formatos
1800000000
10000000000
1791169430
Ejercicio 2: Dado unos registros por ejemplo:
"1", "omar", "ventas"
"3", "jessy", "diseño"
"12122", "itzel", "soporte"
crea un programa que dado los registros se pueda exportar (imprimir) en diferentes tipos de archivo:
en JSON:
{
"datos": [
{
"id": 1,
"persona": "omar",
"departamento": "ventas"
},
{
"id": 2,
"persona": "jessy",
"departamento": "diseño"
}
]
}
en MarkDown
| id | persona | departamento |
|:---:| :---: | :---: |
|1 | omar | ventas |
| 2 | jessy | diseño |
en CVS
id, persona, departamento
1, omar, ventas
2, jessy, diseño

el método de exportar recibe los registros y regresa el string a imprimir

sube tu código java,
