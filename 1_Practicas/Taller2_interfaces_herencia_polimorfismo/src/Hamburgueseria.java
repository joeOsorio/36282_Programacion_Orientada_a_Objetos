/* ****************************************************************
 * Taller 2: Clase Hamburgueseria
 * Restaurante que vende Hamburguesas. Redefine getMenu() y crearPlatillo().
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Hamburgueseria extends Restaurante {

	public Hamburgueseria(String nombre) {
		super(nombre);
	}

	@Override
	public String getMenu() {
		return String.format("  1) Hamburguesa  $%.2f", new Hamburguesa().getPrecio());
	}

	// Regresa un Hamburguesa como Platillo (upcasting)
	@Override
	public Platillo crearPlatillo(int opcion) {
		if (opcion != 1) {
			System.out.println("Error: opcion de menu invalida.");
			return null;
		}
		return new Hamburguesa();
	}
}
