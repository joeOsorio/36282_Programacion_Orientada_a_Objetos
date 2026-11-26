/* ****************************************************************
 * Taller 2: Clase Orden
 * Una orden del POS: guarda hasta 10 platillos (composicion) y genera el ticket.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Orden {
	private static final int MAX_PLATILLOS = 10;
	private int folio;
	private Platillo[] platillos;
	private int contador;

	public Orden(int folio) {
		this.folio = folio;
		this.platillos = new Platillo[MAX_PLATILLOS];
		this.contador = 0;
	}

	/* *********************** Gestion de platillos ************************/
	public boolean agregarPlatillo(Platillo p) {
		if (p == null) {
			System.out.println("Error: platillo invalido.");
			return false;
		}
		if (contador >= MAX_PLATILLOS) {
			System.out.println("Error: la orden ya tiene el maximo de " + MAX_PLATILLOS + " platillos.");
			return false;
		}
		platillos[contador++] = p;
		return true;
	}

	// indice empieza en 0; recorre los demas para no dejar huecos
	public boolean eliminarPlatillo(int indice) {
		if (indice < 0 || indice >= contador) {
			System.out.println("Error: no existe ese platillo en la orden.");
			return false;
		}
		for (int i = indice; i < contador - 1; i++) {
			platillos[i] = platillos[i + 1];
		}
		platillos[--contador] = null;
		return true;
	}

	public Platillo getPlatillo(int indice) {
		if (indice < 0 || indice >= contador) {
			System.out.println("Error: no existe ese platillo en la orden.");
			return null;
		}
		return platillos[indice];
	}

	/* *********************** Informacion ************************/
	public double calcularTotal() {
		double total = 0;
		for (int i = 0; i < contador; i++) {
			total += platillos[i].getPrecio();
		}
		return total;
	}

	public String generarTicket() {
		String ticket = "---------------- Orden #" + folio + " ----------------\n";
		if (contador == 0) {
			ticket += "  (orden vacia)\n";
		}
		for (int i = 0; i < contador; i++) {
			ticket += "  " + (i + 1) + ". " + platillos[i] + "\n";
		}
		ticket += String.format("  TOTAL: $%.2f%n", calcularTotal());
		ticket += "--------------------------------------------";
		return ticket;
	}

	/* Getters */
	public int getFolio() {
		return folio;
	}

	public int getContador() {
		return contador;
	}
}
