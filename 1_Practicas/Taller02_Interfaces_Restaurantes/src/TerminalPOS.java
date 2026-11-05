/* ****************************************************************
 * Taller 2: Clase TerminalPOS
 * La terminal (tipo telefono) con la que se toma una orden. Atiende a un
 * restaurante y decide si agrega o quita toppings segun la interfaz del platillo.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class TerminalPOS {
	private Restaurante restaurante;
	private Orden ordenActual;

	public TerminalPOS(Restaurante restaurante) {
		this.restaurante = restaurante;
		this.ordenActual = null;
	}

	/* *********************** Orden ************************/
	public boolean nuevaOrden() {
		if (ordenActual != null) {
			System.out.println("Error: ya hay una orden en curso (#" + ordenActual.getFolio() + ").");
			return false;
		}
		ordenActual = restaurante.abrirOrden();
		return true;
	}

	public Platillo seleccionarPlatillo(int opcion) {
		if (ordenActual == null) {
			System.out.println("Error: primero abre una orden.");
			return null;
		}
		Platillo p = restaurante.crearPlatillo(opcion);
		if (p == null || !ordenActual.agregarPlatillo(p)) {
			return null;
		}
		return p;
	}

	public String cerrarOrden() {
		if (ordenActual == null) {
			System.out.println("Error: no hay orden en curso.");
			return null;
		}
		if (!restaurante.registrarOrden(ordenActual)) {
			return null;
		}
		String ticket = ordenActual.generarTicket();
		ordenActual = null;
		return ticket;
	}

	public void cancelarOrden() {
		ordenActual = null;
	}

	/* *********************** Toppings (segun la interfaz del platillo) ************************/
	public boolean agregarTopping(Platillo p, int num) {
		if (p instanceof IAgregable) {
			return ((IAgregable) p).agregarTopping(num);
		}
		System.out.println("Error: a este platillo no se le agregan toppings, solo se le quitan.");
		return false;
	}

	public boolean quitarTopping(Platillo p, int num) {
		if (p instanceof IRemovible) {
			return ((IRemovible) p).quitarTopping(num);
		}
		System.out.println("Error: a este platillo no se le quitan toppings, solo se le agregan.");
		return false;
	}

	// Le dice a la pantalla que debe preguntarle al cliente
	public String opcionesDe(Platillo p) {
		if (p instanceof IAgregable) {
			return "Que topping le AGREGAS?";
		}
		if (p instanceof IRemovible) {
			return "Que topping le QUITAS?";
		}
		return "Este platillo no tiene toppings editables.";
	}

	/* Getters */
	public Orden getOrdenActual() {
		return ordenActual;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}
}
