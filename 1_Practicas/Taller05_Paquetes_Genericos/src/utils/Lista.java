package utils;

/* ****************************************************************
 * Taller 5: Clase Lista
 * Lista doblemente enlazada generica (head <-> ... <-> tail).
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Lista<V> {
	private Nodo<V> head;
	private Nodo<V> tail;
	private int size;

	public Lista() {
		head = null;
		tail = null;
		size = 0;
	}

	/* *********************** Insercion ************************/
	public void append(V valor) {
		Nodo<V> nuevo = new Nodo<>(valor);
		if (head == null) {
			head = nuevo;
		} else {
			nuevo.anterior = tail;
			tail.siguiente = nuevo;
		}
		tail = nuevo;
		size++;
	}

	/* *********************** Eliminacion ************************/
	// id = posicion (0 .. size-1)
	public void eliminar(int id) {
		if (id < 0 || id >= size) {
			System.out.println("Error: posicion " + id + " fuera de rango.");
			return;
		}
		if (size == 1) { /* Era el unico nodo */
			head = null;
			tail = null;
		} else if (id == 0) {
			head = head.siguiente;
			head.anterior = null;
		} else if (id == size - 1) {
			tail = tail.anterior;
			tail.siguiente = null;
		} else {
			/* buscar id */
			Nodo<V> actual = head;
			for (int i = 0; i < id; i++)
				actual = actual.siguiente;
			actual.anterior.siguiente = actual.siguiente;
			actual.siguiente.anterior = actual.anterior;
		}
		size--;
	}

	/* *********************** Informacion ************************/
	public int getSize() {
		return size;
	}

	public void mostrar() {
		if (head == null) {
			System.out.println("(lista vacia)");
			return;
		}
		for (Nodo<V> n = head; n != null; n = n.siguiente) {
			Object valorP = (n.anterior == null) ? "null" : n.anterior.valor;
			Object valorS = (n.siguiente == null) ? "null" : n.siguiente.valor;
			System.out.println("[" + valorP + "] <" + n.valor + "> [" + valorS + "]");
		}
	}
}
