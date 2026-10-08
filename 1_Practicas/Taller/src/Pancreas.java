/* **************************************************
 * Ataca al microbio: le quita el 50% (entero) de su energia.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Pancreas extends Organo {
	private static final int PORCENTAJE = 50;

	public Pancreas() {
		super("Pancreas");
	}

	@Override
	public void aplicarEfecto(Persona persona, Microbio microbio) {
		/*
		 * Division entera; minimo 1 para que un microbio con 1 de energia si pueda
		 * morir en caso que la division de menor a 0 el mat.max solecionara 1
		 */

		int quita = Math.max(1, microbio.getEnergia() * PORCENTAJE / 100);
		System.out.println("\t   " + nombre + ": ataca al microbio (" + PORCENTAJE + "% = " + quita + ").");
		microbio.recibirDanio(quita);
	}

	@Override
	public String getDescripcion() {
		return "Ataca al microbio, le quita el " + PORCENTAJE + "% de su energia";
	}
}