/* ********************************************************************
 * Laboratorio 8: Clase Mano
 * Representa la mano de cartas de UN jugador. Arreglo de tamano fijo
 * (maximo 48 cartas segun la practica) + contador de cuantas 
 * tiene realmente en uso 
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * ********************************************************************/
public class Mano {

	public static final int MAX_CARTAS = 48;

	private Carta[] cartas;
	private int cantidad;
	private String nombreJugador;

	Mano(String nombreJugador) {
		this.nombreJugador = nombreJugador;
		this.cartas = new Carta[MAX_CARTAS];
		this.cantidad = 0;
	}

	/* *********************** Agarrar / tirar ************************/
	boolean agarrarCarta(Carta carta) {
		if (carta == null) {
			System.out.println("Error: no se puede agarrar una carta nula.");
			return false;
		}
		if (cantidad >= MAX_CARTAS) {
			System.out.println("Error: la mano de " + nombreJugador + " ya esta llena.");
			return false;
		}
		cartas[cantidad] = carta;
		cantidad++;
		return true;
	}

	Carta tirarCarta(int indice) {
		if (indice < 0 || indice >= cantidad) {
			System.out.println("Error: indice de carta invalido.");
			return null;
		}
		Carta carta = cartas[indice];
		// Recorremos el resto un lugar a la izquierda para no dejar huecos.
		for (int i = indice; i < cantidad - 1; i++) {
			cartas[i] = cartas[i + 1];
		}
		cartas[cantidad - 1] = null;
		cantidad--;
		return carta;
	}

	/* *********************** Transferencias ************************/
	boolean transferirCarta(int indice, Mano destino) {
		if (destino == null) {
			System.out.println("Error: mano destino invalida.");
			return false;
		}
		Carta carta = tirarCarta(indice);
		if (carta == null) {
			return false;
		}
		if (!destino.agarrarCarta(carta)) {
			// Si no se pudo entregar (p.ej. destino lleno), la regresamos para no perderla.
			agarrarCarta(carta);
			return false;
		}
		return true;
	}

	int transferirVariasCartas(int[] indices, Mano destino) {
		if (indices == null) {
			return 0;
		}
		// Ordenamos de mayor a menor para que tirar una carta no invalide
		// los indices de las que faltan por transferir.
		int[] copia = indices.clone();
		ordenarInsercion(copia);
		int transferidas = 0;
		for (int i = copia.length - 1; i >= 0; i--) {
			if (transferirCarta(copia[i], destino)) {
				transferidas++;
			}
		}
		return transferidas;
	}

	/* *********************** Consultas ************************/
	int contarPorValor(char valor) {
		int total = 0;
		for (int i = 0; i < cantidad; i++) {
			if (cartas[i].getValor() == valor) {
				total++;
			}
		}
		return total;
	}

	void mostrarMano() {
		System.out.println("Mano de " + nombreJugador + " (" + cantidad + " cartas):");
		for (int i = 0; i < cantidad; i++) {
			System.out.println("  [" + i + "] " + cartas[i]);
		}
	}

	/* *********************** Getters ************************/
	int getCantidad() {
		return cantidad;
	}

	String getNombreJugador() {
		return nombreJugador;
	}

	Carta getCarta(int indice) {
		if (indice < 0 || indice >= cantidad) {
			return null;
		}
		return cartas[indice];
	}

	static void ordenarInsercion(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int actual = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > actual) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = actual;
		}
	}
}
