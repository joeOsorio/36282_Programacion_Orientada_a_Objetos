/* ****************************************************************
 * Taller 2: Clase Hamburguesa
 * Hamburguesa: inicia SIN toppings, el cliente elige cuales agregar.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Hamburguesa extends Platillo implements IAgregable {
	private static final String[] TOPPINGS = { "lechuga", "tomate", "pepinillos", "cebolla" };
	private static final double PRECIO = 95.0;

	public Hamburguesa() {
		super("Hamburguesa", PRECIO, TOPPINGS, false);
	}

	/* *********************** IAgregable ************************/
	@Override
	public boolean agregarTopping(int num) {
		if (numeroValido(num) && tieneTopping(num)) {
			System.out.println("Error: la hamburguesa ya tiene " + nombresToppings[num - 1] + ".");
			return false;
		}
		return cambiarTopping(num, true);
	}
}
