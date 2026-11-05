package utils;

public class Nodo <T> {
    /*  
    E: elemento
    K: key
    N: numero
    T: Type

    */
   public T valor;
   public Nodo <T> siguiente;
   public Nodo <T> anterior;


   public Nodo(T valor){
    siguiente = null;
    anterior = null;
   }
    
}
