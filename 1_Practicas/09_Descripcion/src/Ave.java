/* **************************************************
 * Taller 7: Clase Ave (hereda de Mascota)
 * Sobrescribe los metodos de Mascota con la
 * revision y el diagnostico propios de un Ave.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Ave extends Mascota {

	private boolean puedeVolar;

	public Ave(String nombre, int edad, String raza, boolean puedeVolar) {
		super(nombre, edad, raza); /* El constructor padre inicializa lo comun */
		this.puedeVolar = puedeVolar;
	}

	/* *********************** Sobrescritos (@Override) ************************/
	@Override
	public String getTipo() {
		return "Ave";
	}

	@Override
	public String examinar() {
		return "Revision aviar: pico, plumaje, patas y alas (" + nombre + " canta durante la consulta).";
	}

	@Override
	public String generarDiagnostico() {
		if (!puedeVolar) {
			return "No puede volar: revisar posible lesion en alas, reposo en jaula.";
		}
		return "Ave sana, plumaje en buen estado.";
	}

	@Override
	public String toString() {
		/* Reutiliza el toString del padre y agrega el dato propio */
		return super.toString() + " | Vuela: " + (puedeVolar ? "si" : "no") + "";
	}

	/* Setters */
	public void setPuedeVolar(boolean puedeVolar) {
		this.puedeVolar = puedeVolar;
	}

	/* Getters */
	public boolean isPuedeVolar() {
		return puedeVolar;
	}
}
