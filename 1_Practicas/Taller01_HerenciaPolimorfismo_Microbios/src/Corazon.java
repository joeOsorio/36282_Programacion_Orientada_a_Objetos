/* **************************************************
 * Aumenta la defensa de la persona en 30 por 3 saltos.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Corazon extends Organo {

	private static final int DEFENSA = 30;
	private static final int TURNOS = 3;

	public Corazon() {
		super("Corazon");
	}

	@Override
	public void aplicarEfecto(Persona persona, Microbio microbio) {
		System.out.println("\t   " + nombre + ": late fuerte y protege a la persona.");
		persona.aumentarDefensa(DEFENSA, TURNOS);
	}

	@Override
	public String getDescripcion() {
		return "Aumenta la defensa de la persona +" + DEFENSA + " (" + TURNOS + " saltos)";
	}
}
