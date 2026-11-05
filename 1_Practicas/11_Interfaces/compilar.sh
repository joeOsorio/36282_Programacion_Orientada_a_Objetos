echo "Compilando archivos .java de src"
javac -encoding UTF-8 -d output -sourcepath src src/*.java
echo "Ejecutando programa principal"
java -cp output Main
echo "Ejecucion Exitosa"
