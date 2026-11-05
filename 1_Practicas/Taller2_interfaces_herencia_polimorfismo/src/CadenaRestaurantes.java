/* ****************************************************************
 * Taller 2: Clase CadenaRestaurantes
 * La cadena tiene sus 3 restaurantes (composicion).
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class CadenaRestaurantes {
	private static final int MAX_RESTAURANTES = 3;
	private String nombre;
	private Restaurante[] restaurantes;
	private int contador;

	public CadenaRestaurantes(String nombre) {
		this.nombre = nombre;
		this.restaurantes = new Restaurante[MAX_RESTAURANTES];
		this.contador = 0;
	}

	/* *********************** Gestion de restaurantes ************************/
	public boolean agregarRestaurante(Restaurante r) {
		if (r == null) {
			System.out.println("Error: restaurante invalido.");
			return false;
		}
		if (contador >= MAX_RESTAURANTES) {
			System.out.println("Error: la cadena ya tiene sus " + MAX_RESTAURANTES + " restaurantes.");
			return false;
		}
		restaurantes[contador++] = r;
		return true;
	}

	public Restaurante getRestaurante(int indice) {
		if (indice < 0 || indice >= contador) {
			System.out.println("Error: no existe ese restaurante.");
			return null;
		}
		return restaurantes[indice];
	}

	public String listarRestaurantes() {
		String texto = "";
		for (int i = 0; i < contador; i++) {
			texto += "  " + (i + 1) + ") " + restaurantes[i] + "\n";
		}
		return texto;
	}

	/* *********************** Informacion ************************/
	public double calcularVentasTotales() {
		double total = 0;
		for (int i = 0; i < contador; i++) {
			total += restaurantes[i].calcularVentas();
		}
		return total;
	}

	/* Getters */
	public String getNombre() {
		return nombre;
	}

	public int getContador() {
		return contador;
	}
}
