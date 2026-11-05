import java.util.InputMismatchException; /* Para el manejo de errores en el try catch */
import java.util.Scanner;

/* ********************************************************************
 * Laboratorio 8: Clase Main
 * Menu interactivo para probar las mecanicas principales de un juego
 * de cartas tipo Go Fish: revolver mazo, repartir cartas, agarrar
 * cartas del mazo, transferir cartas entre manos, mostrar mano al
 * dealer y contar cartas del mismo valor.
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * ********************************************************************/
public class Main {

	static Scanner input = new Scanner(System.in);
	static Dealer dealer = new Dealer();
	static Mano jugador1 = new Mano("Jugador 1");
	static Mano jugador2 = new Mano("Jugador 2");

	public static void main(String[] args) {
		int opcion;
		do {
			mostrarMenu();
			opcion = leerEntero("Elige una opcion: ");
			switch (opcion) {
				case 1:
					dealer.revolverMazo();
					System.out.println("Mazo revuelto.");
					break;
				case 2:
					if (dealer.repartirCartas(new Mano[] { jugador1, jugador2 }, 5)) {
						System.out.println("Cartas repartidas (5 por jugador).");
					}
					break;
				case 3:
					agarrarCartaMenu();
					break;
				case 4:
					transferirCartaMenu();
					break;
				case 5:
					mostrarManoMenu();
					break;
				case 6:
					contarValorMenu();
					break;
				case 7:
					System.out.println("Cartas disponibles en el mazo: " + dealer.getCartasDisponibles());
					break;
				case 8:
					cargarDatosPrueba();
					break;
				case 9:
					test();
					break;
				case 0:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Error: opcion invalida.");
			}
		} while (opcion != 0);
	}

	static void mostrarMenu() {
		System.out.println("\n===== Go Fish - Laboratorio 8 =====");
		System.out.println("1. Revolver mazo");
		System.out.println("2. Repartir cartas iniciales (5 c/u)");
		System.out.println("3. Agarrar carta del mazo");
		System.out.println("4. Transferir carta entre jugadores");
		System.out.println("5. Mostrar mano al dealer");
		System.out.println("6. Contar cartas del mismo valor");
		System.out.println("7. Ver cartas disponibles en el mazo");
		System.out.println("8. Cargar datos de prueba");
		System.out.println("0. Salir");
	}

	/* *********************** Opciones del menu ************************/
	static void agarrarCartaMenu() {
		Mano jugador = elegirJugador();
		Carta carta = dealer.agarrarCartaMazo();
		if (carta != null) {
			jugador.agarrarCarta(carta);
			System.out.println(jugador.getNombreJugador() + " agarro: " + carta);
		}
	}

	static void transferirCartaMenu() {
		System.out.println("Origen:");
		Mano origen = elegirJugador();
		Mano destino = (origen == jugador1) ? jugador2 : jugador1;
		if (origen.getCantidad() == 0) {
			System.out.println("Error: " + origen.getNombreJugador() + " no tiene cartas.");
			return;
		}
		origen.mostrarMano();
		int indice = leerEntero("Indice de la carta a transferir a " + destino.getNombreJugador() + ": ");
		if (origen.transferirCarta(indice, destino)) {
			System.out.println("Carta transferida.");
		}
	}

	static void mostrarManoMenu() {
		Mano jugador = elegirJugador();
		dealer.verMano(jugador);
	}

	static void contarValorMenu() {
		Mano jugador = elegirJugador();
		System.out.print("Valor a buscar (A,2-9,T=10,J,Q,K): ");
		char valor = Character.toUpperCase(input.next().charAt(0));
		int total = jugador.contarPorValor(valor);
		System.out.println(jugador.getNombreJugador() + " tiene " + total + " carta(s) con valor "
				+ Carta.nombreValor(valor) + ".");
	}

	static Mano elegirJugador() {
		System.out.println("1. " + jugador1.getNombreJugador() + "   2. " + jugador2.getNombreJugador());
		int op = leerEntero("Elige jugador: ");
		return (op == 2) ? jugador2 : jugador1;
	}

	static void cargarDatosPrueba() {
		dealer.revolverMazo();
		dealer.repartirCartas(new Mano[] { jugador1, jugador2 }, 5);
		System.out.println("Datos de prueba cargados: mazo revuelto y 5 cartas repartidas a cada jugador.");
	}

	/* *********************** Test oculto ************************/
	static void test() {
		System.out.println("\n===== TEST AUTOMATICO =====");

		Dealer dealerPrueba = new Dealer();
		Mano manoA = new Mano("Prueba A");
		Mano manoB = new Mano("Prueba B");

		System.out.println("Cartas iniciales en mazo de prueba (esperado 52): " + dealerPrueba.getCartasDisponibles());

		dealerPrueba.revolverMazo();
		System.out.println("Mazo revuelto (el orden cambia, el tamano se mantiene en 52).");

		boolean repartioOk = dealerPrueba.repartirCartas(new Mano[] { manoA, manoB }, 5);
		System.out.println("Repartir 5 c/u -> " + (repartioOk ? "OK" : "FALLO")
				+ " | manoA=" + manoA.getCantidad() + " manoB=" + manoB.getCantidad()
				+ " | mazo restante (esperado 42)=" + dealerPrueba.getCartasDisponibles());

		Carta agarrada = dealerPrueba.agarrarCartaMazo();
		manoA.agarrarCarta(agarrada);
		System.out.println("Agarrar 1 carta del mazo a manoA -> OK, carta=" + agarrada
				+ " | manoA=" + manoA.getCantidad() + " | mazo restante (esperado 41)="
				+ dealerPrueba.getCartasDisponibles());

		boolean transOk = manoA.transferirCarta(0, manoB);
		System.out.println("Transferir carta[0] de manoA a manoB -> " + (transOk ? "OK" : "FALLO")
				+ " | manoA=" + manoA.getCantidad() + " manoB=" + manoB.getCantidad());

		boolean transInvalida = manoA.transferirCarta(99, manoB);
		System.out.println("Transferir con indice invalido (99), deberia fallar -> "
				+ (!transInvalida ? "OK (fallo esperado)" : "ERROR (no debio pasar)"));

		Mano manoC = new Mano("Prueba C");
		Mano manoD = new Mano("Prueba D");
		Dealer dealerPrueba2 = new Dealer();
		dealerPrueba2.repartirCartas(new Mano[] { manoC }, 5);
		int[] indicesDesordenados = { 3, 0, 4 };
		int transferidas = manoC.transferirVariasCartas(indicesDesordenados, manoD);
		System.out.println("Transferir varias cartas (indices desordenados 3,0,4) -> transferidas=" + transferidas
				+ " (esperado 3) | manoC=" + manoC.getCantidad() + " (esperado 2) manoD=" + manoD.getCantidad()
				+ " (esperado 3)");

		char valorPrueba = manoA.getCarta(0).getValor();
		int conteo = manoA.contarPorValor(valorPrueba);
		System.out.println(
				"Contar valor " + Carta.nombreValor(valorPrueba) + " en manoA -> " + conteo + " (esperado >= 1)");

		System.out.println("Mostrar mano al dealer:");
		dealerPrueba.verMano(manoA);

		Dealer dealerVacio = new Dealer();
		for (int i = 0; i < 52; i++) {
			dealerVacio.agarrarCartaMazo();
		}
		Carta debeSerNull = dealerVacio.agarrarCartaMazo();
		System.out
				.println("Agarrar carta de mazo vacio, deberia dar null -> " + (debeSerNull == null ? "OK" : "ERROR"));

		System.out.println("Mazo del juego real sigue intacto (no lo toco el test): " + dealer.getCartasDisponibles()
				+ " cartas disponibles.");

		System.out.println("===== FIN DEL TEST =====\n");
	}

	/* *********************** Utilidades ************************/
	static int leerEntero(String mensaje) {
		int valor;
		while (true) {
			System.out.print(mensaje);
			try {
				valor = input.nextInt();
				input.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("Error: ingresa un numero valido.");
				input.nextLine();
			}
		}
	}
}
