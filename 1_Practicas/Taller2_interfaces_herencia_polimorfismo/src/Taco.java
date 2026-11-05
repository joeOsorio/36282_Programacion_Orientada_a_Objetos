/* ****************************************************************
 * Taller 2: Clase Taco
 * Taco: inicia CON todos los toppings, el cliente elige cuales quitar.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Taco extends Platillo implements IRemovible {
	private static final String[] TOPPINGS = {"salsa", "aguacate", "cebolla", "cilantro"};
	private static final double PRECIO = 25.0;

	public Taco() {
		super("Taco", PRECIO, TOPPINGS, true);
	}

	/* *********************** IRemovible ************************/
	@Override
	public boolean quitarTopping(int num) {
		if (numeroValido(num) && !tieneTopping(num)) {
			System.out.println("Error: el taco ya no tiene " + nombresToppings[num - 1] + ".");
			return false;
		}
		return cambiarTopping(num, false);
	}
}
