package utils;

/* ****************************************************************
 * Nodo de la lista doblemente enlazada. Ya NO necesita ser
 * Serializable: la Lista guarda solo los valores (version 2).
 * @Author: J03 O^2
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class Nodo<T> {
	public T valor;
	public Nodo<T> sig;
	public Nodo<T> prev;

	public Nodo(T valor) {
		this.valor = valor;
		sig = null;
		prev = null;
	}
}
