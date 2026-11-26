/* ****************************************************************
 * Taller 3: Clase Texto
 * Un texto que se convierte a numero con hashing polinomial
 * (cp-algorithms: hash = sum s[i] * p^i mod m) y ese hash se muestra
 * en binario, octal y hexadecimal. Tambien calcula la suma ASCII para comparar.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Texto implements IMostrarBinario, IMostrarOctal, IMostrarHexadecimal {
	public static final int P = 131; // primo mayor que el alfabeto ASCII (0-127)
	public static final long M = 1_000_000_009L; // primo grande para el modulo
	private String texto;

	public Texto(String texto) {
		setTexto(texto);
	}

	/* *********************** Hash ************************/
	// hash(s) = s[0] + s[1]*p + s[2]*p^2 + ... (mod m)
	// Cada caracter se toma como su codigo ASCII (+1 para que ningun caracter valga
	// 0)
	public long calcularHash() {
		long hash = 0;
		long potencia = 1;
		for (int i = 0; i < texto.length(); i++) {
			hash = (hash + (texto.charAt(i) + 1) * potencia) % M;
			potencia = (potencia * P) % M;
		}
		return hash;
	}

	// Alternativa simple: suma de los ASCII (los anagramas chocan, ej. "amor" y
	// "roma")
	public long sumaAscii() {
		long suma = 0;
		for (int i = 0; i < texto.length(); i++) {
			suma += texto.charAt(i);
		}
		return suma;
	}

	/* *********************** Interfaces ************************/
	@Override
	public String mostrarBinario() {
		return Conversor.convertir(calcularHash(), 2);
	}

	@Override
	public String mostrarOctal() {
		return Conversor.convertir(calcularHash(), 8);
	}

	@Override
	public String mostrarHexadecimal() {
		return Conversor.convertir(calcularHash(), 16);
	}

	/* Setters */
	public void setTexto(String texto) {
		if (texto == null) {
			System.out.println("Error: el texto no puede ser null, se usa texto vacio.");
			texto = "";
		}
		this.texto = texto;
	}

	/* Getters */
	public String getTexto() {
		return texto;
	}

	@Override
	public String toString() {
		return String.format("Texto \"%s\"%n  Suma ASCII: %d%n  Hash:       %d%n  BIN: %s%n  OCT: %s%n  HEX: %s",
				texto, sumaAscii(), calcularHash(), mostrarBinario(), mostrarOctal(), mostrarHexadecimal());
	}
}
