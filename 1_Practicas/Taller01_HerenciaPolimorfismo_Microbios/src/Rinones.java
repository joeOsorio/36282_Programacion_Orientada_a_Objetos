/* **************************************************
 * Ataca al microbio: le quita el 15% de su energia.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Rinones extends Organo {

	private static final int PORCENTAJE = 15;

	public Rinones() {
		super("Rinones");
	}

	@Override
	public void aplicarEfecto(Persona persona, Microbio microbio) {
		/*
		 * Division entera; minimo 1 para que un microbio con poca energia si pueda
		 * morir lo mismo que realizamos con el pancreas.
		 */
		int quita = Math.max(1, microbio.getEnergia() * PORCENTAJE / 100);
		System.out.println("\t   " + nombre + ": filtra al microbio (" + PORCENTAJE + "% = " + quita + ").");
		microbio.recibirDanio(quita);
	}

	@Override
	public String getDescripcion() {
		return "Ataca al microbio, le quita el " + PORCENTAJE + "% de su energia";
	}
}
