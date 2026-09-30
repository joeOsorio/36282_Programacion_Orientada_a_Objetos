/* **************************************************
 * Taller 7: Clase Gato (hereda de Mascota)
 * Sobrescribe los metodos de Mascota con la
 * revision y el diagnostico propios de un Gato.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Gato extends Mascota {

	private boolean esDeInterior;

	public Gato(String nombre, int edad, String raza, boolean esDeInterior) {
		super(nombre, edad, raza); /* El constructor padre inicializa lo comun */
		this.esDeInterior = esDeInterior;
	}

	/* *********************** Sobrescritos (@Override) ************************/
	@Override
	public String getTipo() {
		return "Gato";
	}

	@Override
	public String examinar() {
		return "Revision felina: bolas de pelo, rinones, unas y pelaje (" + nombre + " no coopera).";
	}

	@Override
	public String generarDiagnostico() {
		if (esDeInterior) {
			return "Gato de interior sano: vigilar peso y dar desparasitante interno.";
		}
		return "Gato de exterior: aplicar desparasitante externo y revisar heridas.";
	}

	@Override
	public String toString() {
		/* Reutiliza el toString del padre y agrega el dato propio */
		return super.toString() + " | Interior: " + (esDeInterior ? "si" : "no") + "";
	}

	/* Setters */
	public void setEsDeInterior(boolean esDeInterior) {
		this.esDeInterior = esDeInterior;
	}

	/* Getters */
	public boolean isEsDeInterior() {
		return esDeInterior;
	}
}
