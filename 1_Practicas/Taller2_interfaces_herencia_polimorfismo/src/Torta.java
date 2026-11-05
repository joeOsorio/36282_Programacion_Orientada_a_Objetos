/* ****************************************************************
 * Taller 2: Clase Torta
 * Torta: inicia CON todos los toppings, el cliente elige cuales quitar.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Torta extends Platillo implements IRemovible {
	private static final String[] TOPPINGS = {"lechuga", "tomate", "pepinillos", "cebolla"};
	private static final double PRECIO = 80.0;

	public Torta() {
		super("Torta", PRECIO, TOPPINGS, true);
	}

	/* *********************** IRemovible ************************/
	@Override
	public boolean quitarTopping(int num) {
		if (numeroValido(num) && !tieneTopping(num)) {
			System.out.println("Error: la torta ya no tiene " + nombresToppings[num - 1] + ".");
			return false;
		}
		return cambiarTopping(num, false);
	}
}
