/* ****************************************************************
 * Taller 2: Clase Taqueria
 * Restaurante que vende Tacos. Redefine getMenu() y crearPlatillo().
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Taqueria extends Restaurante {

	public Taqueria(String nombre) {
		super(nombre);
	}

	@Override
	public String getMenu() {
		return String.format("  1) Taco  $%.2f", new Taco().getPrecio());
	}

	// Regresa un Taco como Platillo (upcasting)
	@Override
	public Platillo crearPlatillo(int opcion) {
		if (opcion != 1) {
			System.out.println("Error: opcion de menu invalida.");
			return null;
		}
		return new Taco();
	}
}
