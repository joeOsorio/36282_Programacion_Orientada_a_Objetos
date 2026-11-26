package utils;

/* ****************************************************************
 * Taller 5: Clase Nodo
 * Nodo generico de una lista doblemente enlazada.
 * Convencion de nombres para genericos:
 *   E: elemento, K: key, N: numero, T: type, V: value
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Nodo<T> {
	public T valor;
	public Nodo<T> siguiente;
	public Nodo<T> anterior;

	public Nodo(T valor) {
		this.valor = valor; /* Antes no se guardaba el valor */
		siguiente = null;
		anterior = null;
	}
}
