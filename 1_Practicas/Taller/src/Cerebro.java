/* **************************************************
 * Debilita las defensas del microbio (-30 por 3 saltos)
 * y le restaura 2^5 ( 32 )de energia a la persona.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Cerebro extends Organo {
	private static final int DEBILITA = 30, TURNOS = 3, RESTAURA = 32;

	public Cerebro() {
		super("Cerebro");
	}

	@Override
	public void aplicarEfecto(Persona persona, Microbio microbio) {
		System.out.println("\t   " + nombre + ": debilita al microbio y cura a la persona.");
		microbio.debilitar(DEBILITA, TURNOS);
		persona.restaurarEnergia(RESTAURA);
	}

	@Override
	public String getDescripcion() {
		return "Debilita defensas del microbio -" + DEBILITA + " (" + TURNOS + " saltos) y restaura "
				+ RESTAURA + " de energia a la persona";
	}
}
