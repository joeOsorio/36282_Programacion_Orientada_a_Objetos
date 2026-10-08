/* ********************************************************************
 * Laboratorio 8: Clase Carta
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * Comentarios:
 * Representa UNA carta individual de una baraja estandar (valor + palo).
 * Es un objeto de valor: una vez creada, ya no cambia (no tiene setters).
 * Las 52 combinaciones posibles (13 valores x 4 palos)
 * dominio para cualquier mazo que exista, por eso lo manejo como statico
 ********************************************************************/
public class Carta {

	// Dominio fijo de valores y palos. 'T' representa el 10.
	public static final char[] VALORES = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
	public static final char[] PALOS = { 'T', 'E', 'D', 'C' }; // Trebol, Espadas, Diamantes, Corazon

	private final char valor;
	private final char palo;

	Carta(char valor, char palo) {
		this.valor = valor;
		this.palo = palo;
	}

	/* *********************** Getters ************************/
	char getValor() {
		return valor;
	}

	char getPalo() {
		return palo;
	}

	/* *********************** Comparaciones ************************/
	boolean mismoValor(Carta otra) {
		if (otra == null) {
			return false;
		}
		return this.valor == otra.valor;
	}

	/* *********************** Utilidades de texto ************************/
	static String nombreValor(char valor) {
		if (valor == 'T') {
			return "10";
		}
		return String.valueOf(valor);
	}

	static String nombrePalo(char palo) {
		switch (palo) {
			case 'T':
				return "Trebol";
			case 'E':
				return "Espadas";
			case 'D':
				return "Diamantes";
			case 'C':
				return "Corazon";
			default:
				return "?";
		}
	}

	@Override
	public String toString() {
		return nombreValor(valor) + " de " + nombrePalo(palo);
	}
}
