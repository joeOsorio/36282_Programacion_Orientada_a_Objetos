package utils;

import utils.Nodo;

public class Lista<V> {
    private Nodo<V> head;
    private Nodo<V> tail;
    private int size;

    public Lista() {
        head = null;
        tail = null;
    }

    public void append(T valor) {
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

    public void eliminar(int id) {
        if (id >= size)
            return;
        if (id == 0) {
            head = head.siguiente;
            head.anterior = null;
            size--;
            return;
        } else if (id == size - 1) {
            tail = tail.anterior;
            tail.siguiente = null;
            size--;
            return;
        }

        /* buscar id */
        Nodo<V> actual = head;
        for (int i = 0; i < id; i++)
            actual = actual.siguiente;
        actual.anterior.siguiente = actual.siguiente;
        actual.siguiente.anterior = actual.anterior;
        actual = null;
        size--;
    }

    public void mostrar() {
        for (var n = head; n != null; n = n.siguiente) {
            var valorP = (n.anterior == null) ? "null" : n.anterior.valor;
            var valorS = (n.siguiente == null) ? "null" : n.anterior.valor;
            System.out.print("[" + valorP + "] <" + n.valor + "> [" + valorS + "]");
        }
    }
}