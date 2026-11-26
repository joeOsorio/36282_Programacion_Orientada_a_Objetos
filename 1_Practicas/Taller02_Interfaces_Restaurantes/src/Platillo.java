/* ****************************************************************
 * Taller 2: Clase Platillo
 * Clase base (concreta) de todos los platillos. Guarda los 4 toppings
 * como arreglo de boolean (true = lo lleva).
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Platillo implements IPlatillo {
	protected String nombre;
	protected double precio;
	protected String[] nombresToppings;
	protected boolean[] toppings;

	public Platillo(String nombre, double precio, String[] nombresToppings, boolean incluidos) {
		this.nombre = nombre;
		this.precio = precio;
		this.nombresToppings = nombresToppings;
		this.toppings = new boolean[nombresToppings.length];
		for (int i = 0; i < toppings.length; i++) {
			toppings[i] = incluidos;
		}
	}

	/* *********************** IPlatillo ************************/
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public double getPrecio() {
		return precio;
	}

	@Override
	public String preparar() {
		return "Preparando " + nombre + " " + resumenToppings();
	}

	/* *********************** Toppings ************************/
	// num va de 1 a 4 (como lo ve el usuario en el POS)
	public boolean tieneTopping(int num) {
		if (!numeroValido(num)) {
			return false;
		}
		return toppings[num - 1];
	}

	// Solo valida el rango y cambia el valor; cada subclase decide si agrega o
	// quita
	protected boolean cambiarTopping(int num, boolean valor) {
		if (!numeroValido(num)) {
			System.out.println("Error: el topping debe ser del 1 al " + toppings.length + ".");
			return false;
		}
		toppings[num - 1] = valor;
		return true;
	}

	public String listarToppings() {
		String texto = "";
		for (int i = 0; i < toppings.length; i++) {
			texto += "  " + (i + 1) + ") [" + (toppings[i] ? "x" : " ") + "] " + nombresToppings[i] + "\n";
		}
		return texto;
	}

	public String resumenToppings() {
		String texto = "";
		for (int i = 0; i < toppings.length; i++) {
			if (toppings[i]) {
				texto += (texto.isEmpty() ? "" : ", ") + nombresToppings[i];
			}
		}
		return texto.isEmpty() ? "(sin toppings)" : "con " + texto;
	}

	protected boolean numeroValido(int num) {
		return num >= 1 && num <= toppings.length;
	}

	@Override
	public String toString() {
		return String.format("%-12s $%7.2f  %s", nombre, precio, resumenToppings());
	}
}
