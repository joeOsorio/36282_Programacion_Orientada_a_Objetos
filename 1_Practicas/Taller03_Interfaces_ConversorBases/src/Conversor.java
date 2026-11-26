/* ****************************************************************
 * Taller 3: Clase Conversor
 * Utileria con el algoritmo de divisiones sucesivas para pasar un
 * numero decimal a cualquier base de 2 a 16 (sin usar Integer.toBinaryString).
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Conversor {
	private static final String DIGITOS = "0123456789ABCDEF";

	/* *********************** Conversion ************************/
	// Divide entre la base y junta los residuos al reves
	public static String convertir(long valor, int base) {
		if (base < 2 || base > 16) {
			System.out.println("Error: la base debe ser de 2 a 16.");
			return null;
		}
		if (valor == 0) {
			return "0";
		}
		boolean negativo = valor < 0;
		long n = Math.abs(valor);
		String resultado = "";
		while (n > 0) {
			resultado = DIGITOS.charAt((int) (n % base)) + resultado;
			n /= base;
		}
		return negativo ? "-" + resultado : resultado;
	}
}
