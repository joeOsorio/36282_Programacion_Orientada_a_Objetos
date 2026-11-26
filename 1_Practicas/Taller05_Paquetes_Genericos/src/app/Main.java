package app;

import utils.Lista;

/* ****************************************************************
 * Taller 5: Programa principal
 * Prueba la lista doblemente enlazada generica del paquete utils.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Main {
	public static void main(String[] args) {
		Lista<Integer> lista = new Lista<>();

		for (int i = 1; i <= 5; i++)
			lista.append(i * 10);
		System.out.println("Lista inicial (" + lista.getSize() + " nodos):");
		lista.mostrar();

		System.out.println("\nEliminar posicion 0 (head), 3 (tail) y 1 (en medio):");
		lista.eliminar(0);
		lista.eliminar(3);
		lista.eliminar(1);
		lista.mostrar();

		System.out.println("\nEliminar posicion invalida:");
		lista.eliminar(10);

		System.out.println("\nVaciar la lista:");
		lista.eliminar(0);
		lista.eliminar(0);
		lista.mostrar();

		Lista<String> nombres = new Lista<>();
		nombres.append("Ñandú");
		nombres.append("Peña");
		System.out.println("\nLista de String (prueba de ñ):");
		nombres.mostrar();
	}
}
