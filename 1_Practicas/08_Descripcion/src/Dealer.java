/* ********************************************************************
 * Laboratorio 8: Clase Dealer
 * Lleva el control del juego: arma el mazo completo (52 cartas unicas,
 * generadas a partir del dominio Carta.VALORES x Carta.PALOS), lo
 * revuelve, reparte cartas iniciales y administra que los jugadores
 * agarren cartas del mazo.

 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * ********************************************************************/
import java.util.Random; /* Para utilizar randon en la reparticion y acomodo de las cartas */
public class Dealer {

	private Carta[] mazo;
	private int cartasDisponibles; /* funciona como "indice tope": cuantas cartas quedan sin repartir */
    
	Dealer() {
		this.mazo = new Carta[Carta.VALORES.length * Carta.PALOS.length];
		int i = 0;
		for (char palo : Carta.PALOS) {
			for (char valor : Carta.VALORES) {
				mazo[i] = new Carta(valor, palo);
				i++;
			}
		}
		this.cartasDisponibles = mazo.length;
	}

	/* *********************** Mazo ************************/
	void revolverMazo() {
		Random random = new Random();
		for (int i = cartasDisponibles - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			Carta temp = mazo[i];
			mazo[i] = mazo[j];
			mazo[j] = temp;
		}
	}

	Carta agarrarCartaMazo() {
		if (cartasDisponibles <= 0) {
			System.out.println("Error: el mazo esta vacio.");
			return null;
		}
		cartasDisponibles--;
		Carta carta = mazo[cartasDisponibles];
		mazo[cartasDisponibles] = null; /* Nos salimos  de mazo*/
		return carta;
	}

	boolean repartirCartas(Mano[] jugadores, int cantidadPorJugador) {
		if (jugadores == null || jugadores.length == 0) {
			System.out.println("Error: no hay jugadores a quien repartir.");
			return false;
		}
		int necesarias = jugadores.length * cantidadPorJugador;
		if (necesarias > cartasDisponibles) {
			System.out.println("Error: no hay suficientes cartas en el mazo para repartir.");
			return false;
		}
		for (int ronda = 0; ronda < cantidadPorJugador; ronda++) {
			for (Mano jugador : jugadores) {
				jugador.agarrarCarta(agarrarCartaMazo());
			}
		}
		return true;
	}

	/* *********************** Interaccion con jugadores ************************/
	void verMano(Mano jugador) {
		if (jugador == null) {
			System.out.println("Error: jugador invalido.");
			return;
		}
		System.out.println("--- " + jugador.getNombreJugador() + " le muestra su mano al dealer ---");
		jugador.mostrarMano();
	}

	/* *********************** Getters ************************/
	int getCartasDisponibles() {
		return cartasDisponibles;
	}
}
