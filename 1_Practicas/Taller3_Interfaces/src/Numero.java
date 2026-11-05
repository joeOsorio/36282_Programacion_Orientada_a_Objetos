/* ****************************************************************
 * Taller 3: Clase Numero
 * Un numero entero que se puede mostrar en binario, octal y hexadecimal.
 * Implementa las 3 interfaces usando su atributo valor.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public class Numero implements IMostrarBinario, IMostrarOctal, IMostrarHexadecimal {
	private long valor;

	public Numero(long valor) {
		this.valor = valor;
	}

	/* *********************** Interfaces ************************/
	@Override
	public String mostrarBinario() {
		return Conversor.convertir(valor, 2);
	}

	@Override
	public String mostrarOctal() {
		return Conversor.convertir(valor, 8);
	}

	@Override
	public String mostrarHexadecimal() {
		return Conversor.convertir(valor, 16);
	}

	/* Setters */
	public void setValor(long valor) {
		this.valor = valor;
	}

	/* Getters */
	public long getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return String.format("Numero %d%n  BIN: %s%n  OCT: %s%n  HEX: %s",
				valor, mostrarBinario(), mostrarOctal(), mostrarHexadecimal());
	}
}
