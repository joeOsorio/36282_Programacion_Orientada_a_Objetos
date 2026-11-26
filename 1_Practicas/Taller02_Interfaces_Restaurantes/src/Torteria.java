/* ****************************************************************
 * Taller 2: Clase Torteria
 * Restaurante que vende Tortas. Redefine getMenu() y crearPlatillo().
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Torteria extends Restaurante {

	public Torteria(String nombre) {
		super(nombre);
	}

	@Override
	public String getMenu() {
		return String.format("  1) Torta  $%.2f", new Torta().getPrecio());
	}

	// Regresa un Torta como Platillo (upcasting)
	@Override
	public Platillo crearPlatillo(int opcion) {
		if (opcion != 1) {
			System.out.println("Error: opcion de menu invalida.");
			return null;
		}
		return new Torta();
	}
}
