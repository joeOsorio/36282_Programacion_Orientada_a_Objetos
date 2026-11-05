package ejemplo;
/* ****************************************************************
 * Laboratorio 11: Clase ReglaNoNulo
 * La cadena no debe ser null.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class ReglaNoNulo implements IValidar {

	@Override
	public boolean validar(String texto) {
		return texto != null;
	}

	@Override
	public String toString() {
		return "No nulo";
	}
}
