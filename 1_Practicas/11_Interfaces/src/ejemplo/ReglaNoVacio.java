package ejemplo;
/* ****************************************************************
 * Laboratorio 11: Clase ReglaNoVacio
 * La cadena no debe estar vacia. Solo espacios cuenta como vacia.
 * Un null tambien falla (no se truena con NullPointerException).
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class ReglaNoVacio implements IValidar {

	@Override
	public boolean validar(String texto) {
		return texto != null && !texto.trim().isEmpty();
	}

	@Override
	public String toString() {
		return "No vacio";
	}
}
