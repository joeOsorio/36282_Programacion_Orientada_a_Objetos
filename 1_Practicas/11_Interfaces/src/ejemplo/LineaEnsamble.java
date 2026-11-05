package ejemplo;
/* ****************************************************************
 * Laboratorio 11: Clase LineaEnsamble
 * Guarda las reglas (arreglo de tipo IValidar) y pasa la cadena por
 * cada una. Solo ve la interfaz: no sabe que regla concreta es.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class LineaEnsamble {
	public static final int MAX_REGLAS = 10;
	private IValidar[] reglas;
	private int contador;

	public LineaEnsamble() {
		reglas = new IValidar[MAX_REGLAS];
		contador = 0;
	}

	/* *********************** Reglas ************************/
	public boolean agregarRegla(IValidar regla) {
		if (regla == null) {
			System.out.println("Error: la regla no puede ser null.");
			return false;
		}
		if (contador >= MAX_REGLAS) {
			System.out.println("Error: la linea ya tiene " + MAX_REGLAS + " reglas.");
			return false;
		}
		reglas[contador++] = regla;
		return true;
	}

	/* *********************** Validacion ************************/
	// true solo si la cadena pasa TODAS las reglas
	public boolean validar(String texto) {
		for (int i = 0; i < contador; i++) {
			if (!reglas[i].validar(texto)) {
				return false;
			}
		}
		return true;
	}

	// Muestra estacion por estacion si paso o no
	public void reporte(String texto) {
		String mostrar = (texto == null) ? "null" : "\"" + texto + "\"";
		System.out.println("Cadena " + mostrar + ":");
		for (int i = 0; i < contador; i++) {
			boolean paso = reglas[i].validar(texto);
			System.out.printf("  %-24s %s%n", reglas[i], paso ? "[PASA]" : "[NO PASA]");
		}
		System.out.println("  Resultado: " + (validar(texto) ? "ACEPTADA" : "RECHAZADA"));
	}

	/* Getters */
	public int getContador() {
		return contador;
	}

	public IValidar getRegla(int indice) {
		if (indice < 0 || indice >= contador) {
			System.out.println("Error: no existe la regla " + indice + ".");
			return null;
		}
		return reglas[indice];
	}
}
