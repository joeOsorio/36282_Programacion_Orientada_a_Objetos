/* **************************************************
 * Taller 7: Clase Perro (hereda de Mascota)
 * Sobrescribe los metodos de Mascota con la
 * revision y el diagnostico propios de un Perro.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Perro extends Mascota {

	private boolean vacunaRabia;

	public Perro(String nombre, int edad, String raza, boolean vacunaRabia) {
		super(nombre, edad, raza); /* El constructor padre inicializa lo comun */
		this.vacunaRabia = vacunaRabia;
	}

	/* *********************** Sobrescritos (@Override) ************************/
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
		/* nombre y edad son protected: se usan directo sin getters */
		if (edad >= 8) {
			return "Perro senior: revisar articulaciones y dieta baja en grasa.";
		}
		return "Perro sano, vacuna antirrabica al dia.";
	}

	@Override
	public String toString() {
		/* Reutiliza el toString del padre y agrega el dato propio */
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
