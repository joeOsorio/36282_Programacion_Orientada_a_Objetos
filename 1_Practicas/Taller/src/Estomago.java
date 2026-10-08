/* **************************************************
 * Le restaura 2^4 de energia al microbio (lo alimenta).
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Estomago extends Organo {
	private static final int RESTAURA = 16; /* 16 */

	public Estomago() {
		super("Estomago");
	}

	@Override
	public void aplicarEfecto(Persona persona, Microbio microbio) {
		System.out.println("\t   " + nombre + ": el microbio se come lo que encuentra {es un atascado}.");
		microbio.restaurarEnergia(RESTAURA);
	}

	@Override
	public String getDescripcion() {
		return "Restaura " + RESTAURA + " de energia al microbio";
	}
}
