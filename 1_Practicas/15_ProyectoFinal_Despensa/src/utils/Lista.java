package utils;

import java.io.*;

/* ****************************************************************
 * Lista doblemente enlazada serializable : head, tail y size son transient 
 * y se guardan "a mano" con un ciclo en writeObject/readObject, para que 
 * notruene con StackOverflowError en listas largas.
 * @Author: J03 O^2
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class Lista<V> implements Serializable {
	private static final long serialVersionUID = 1L;

	/* transient: los nodos no se serializan uno por uno */
	private transient Nodo<V> head;
	private transient Nodo<V> tail;
	public transient int size;

	public Lista() {
		head = null;
		tail = null;
		size = 0;
	}

	/* *********************** Operaciones ************************/
	public void append(V valor) {
		Nodo<V> nuevo = new Nodo<>(valor);
		if (head == null) {
			head = nuevo;
		} else {
			nuevo.prev = tail;
			tail.sig = nuevo;
		}
		tail = nuevo;
		size++;
	}

	public boolean eliminar(int id) {
		if (id < 0 || id >= size) {
			System.out.println("Error: indice " + id + " fuera de rango");
			return false;
		}
		Nodo<V> actual = head;
		for (int i = 0; i < id; i++)
			actual = actual.sig;

		if (actual.prev == null)
			head = actual.sig;
		else
			actual.prev.sig = actual.sig;

		if (actual.sig == null)
			tail = actual.prev;
		else
			actual.sig.prev = actual.prev;

		size--;
		return true;
	}

	public V get(int id) {
		if (id < 0 || id >= size)
			return null;
		Nodo<V> actual = head;
		for (int i = 0; i < id; i++)
			actual = actual.sig;
		return actual.valor;
	}

	/* Getters */
	public Nodo<V> begin() {
		return head;
	}

	public Nodo<V> end() {
		return tail;
	}

	public int getSize() {
		return size;
	}

	public boolean estaVacia() {
		return size == 0;
	}

	/* *********************** Serializacion ************************/
	/* Se guarda el tamano y luego cada valor, con un ciclo (sin recursion) */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(size);
		for (var n = head; n != null; n = n.sig)
			out.writeObject(n.valor);
	}

	/* Se reconstruye la lista agregando los valores en orden */
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject(); /* head, tail = null; size = 0 */
		int total = in.readInt();
		for (int i = 0; i < total; i++)
			append((V) in.readObject());
	}

	/* *********************** Impresion ************************/
	public void mostrar() {
		for (var n = head; n != null; n = n.sig) {
			var valorP = (n.prev == null) ? "null" : n.prev.valor;
			var valorS = (n.sig == null) ? "null" : n.sig.valor;
			System.out.println("[" + valorP + "] <" + n.valor + "> [" + valorS + "]");
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (var n = head; n != null; n = n.sig) {
			sb.append(n.valor);
			if (n.sig != null)
				sb.append(", ");
		}
		return sb.append("]").toString();
	}
}
