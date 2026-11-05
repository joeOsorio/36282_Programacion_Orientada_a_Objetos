package ejemplo;
/* ****************************************************************
 * Laboratorio 11: Clase ReglaEsNumero
 * La cadena debe poder convertirse a numero con Double.parseDouble().
 * Si lanza NumberFormatException no es numero.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class ReglaEsNumero implements IValidar {

	@Override
	public boolean validar(String texto) {
		if (texto == null) {
			return false;
		}
		try {
			Double.parseDouble(texto);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Es numero";
	}
}
