/* **************************************************
 * Taller 7: Clase Gato (hereda de Mascota)
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Gato extends Mascota {

	private boolean esDeInterior;

	public Gato(String nombre, int edad, String raza, boolean esDeInterior) {
		super(nombre, edad, raza);
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
