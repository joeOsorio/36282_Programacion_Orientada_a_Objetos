/* ****************************************************************
 * Taller 2: Clase Restaurante
 * Clase base (concreta) de los restaurantes de la cadena. Registra hasta 50
 * ordenes; cada subclase redefine su menu y que platillo crea.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Restaurante {
	private static final int MAX_ORDENES = 50;
	protected String nombre;
	protected Orden[] ordenes;
	protected int contador;
	protected int siguienteFolio;

	public Restaurante(String nombre) {
		this.nombre = nombre;
		this.ordenes = new Orden[MAX_ORDENES];
		this.contador = 0;
		this.siguienteFolio = 1;
	}

	/*
	 * *********************** Menu (lo redefine cada restaurante)
	 ************************/
	public String getMenu() {
		return "  (este restaurante no tiene menu)";
	}

	public Platillo crearPlatillo(int opcion) {
		System.out.println("Error: este restaurante no vende platillos.");
		return null;
	}

	/* *********************** Gestion de ordenes ************************/
	// La orden se crea aqui pero se registra hasta que se cobra
	public Orden abrirOrden() {
		return new Orden(siguienteFolio++);
	}

	public boolean registrarOrden(Orden o) {
		if (o == null) {
			System.out.println("Error: orden invalida.");
			return false;
		}
		if (o.getContador() == 0) {
			System.out.println("Error: no se puede cobrar una orden vacia.");
			return false;
		}
		if (contador >= MAX_ORDENES) {
			System.out.println("Error: se alcanzo el limite de " + MAX_ORDENES + " ordenes.");
			return false;
		}
		ordenes[contador++] = o;
		return true;
	}

	public double calcularVentas() {
		double total = 0;
		for (int i = 0; i < contador; i++) {
			total += ordenes[i].calcularTotal();
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

	@Override
	public String toString() {
		return String.format("%-22s ordenes: %2d   ventas: $%.2f", nombre, contador, calcularVentas());
	}
}
