/* **************************************************
 * Taller 7: Clase Perro (hereda de Mascota)
 * Sobrescribe los metodos de Mascota con la
 * revision y el diagnostico propios de un Perro.
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Perro extends Mascota {

	private boolean vacunaRabia;

	public Perro(String nombre, int edad, String raza, boolean vacunaRabia) {
		super(nombre, edad, raza);
		this.vacunaRabia = vacunaRabia;
	}

	@Override
	public String getTipo() {
		return "Perro";
	}

	@Override
	public String examinar() {
		return "Revision canina: dientes, oidos, cadera y latido (" + nombre + " ladra al estetoscopio).";
	}

	@Override
	public String generarDiagnostico() {
		if (!vacunaRabia) {
			return "Sano, pero FALTA vacuna antirrabica: aplicar lo antes posible.";
		}
		if (edad >= 8) {
			return "Perro senior: revisar articulaciones y dieta baja en grasa.";
		}
		return "Perro sano, vacuna antirrabica al dia.";
	}

	@Override
	public String toString() {
		return super.toString() + " | Vacuna rabia: " + (vacunaRabia ? "si" : "no") + "";
	}

	/* Setters */
	public void setVacunaRabia(boolean vacunaRabia) {
		this.vacunaRabia = vacunaRabia;
	}

	/* Getters */
	public boolean isVacunaRabia() {
		return vacunaRabia;
	}
}
