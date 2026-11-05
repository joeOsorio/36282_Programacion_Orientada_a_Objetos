package ejemplo;
/* ****************************************************************
 * Laboratorio 11: Clase Password
 * Una clave. Al ocultarla se cambia cada caracter por un *
 * 12341234 -> ********
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Password implements IOcultar {
	private String clave;

	public Password(String clave) {
		this.clave = "";
		setClave(clave);
	}

	/* *********************** Interfaz ************************/
	@Override
	public String ocultar() {
		return "*".repeat(clave.length());
	}

	/* *********************** Validacion ************************/
	// Sin espacios y que no venga vacia
	public static boolean esValido(String clave) {
		return clave != null && !clave.isEmpty() && !clave.contains(" ");
	}

	/* Setters */
	public boolean setClave(String clave) {
		if (!esValido(clave)) {
			System.out.println("Error: la clave no puede estar vacia ni tener espacios.");
			return false;
		}
		this.clave = clave;
		return true;
	}

	/* Getters */
	public String getClave() {
		return clave;
	}

	@Override
	public String toString() {
		return "Password: " + ocultar();
	}
}
