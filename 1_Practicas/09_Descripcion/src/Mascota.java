/* **************************************************
 * Taller 7: Clase Mascota (clase padre)
 * Clase base de todos los animales de la veterinaria.
 * Ya NO es abstracta: sus metodos tienen una version
 * por defecto que cada animal sobrescribe (@Override).
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Mascota {

	/* protected: las clases hijas (Perro, Gato, Ave) los usan directamente */
	protected String nombre;
	protected int edad;
	protected String raza;
	protected String diagnostico;

	public Mascota(String nombre, int edad, String raza) {
		this.nombre = nombre;
		this.edad = edad;
		this.raza = raza;
		this.diagnostico = "Sin diagnostico";
	}

	/* *********************** Polimorfismo ************************/
	/*
	 * Version por defecto. Si el objeto real es un Perro, Gato o Ave, Java ejecuta
	 * la version de la hija en tiempo de ejecucion (dynamic dispatching).
	 */
	public String getTipo() {
		return "Mascota";
	}

	public String examinar() {
		return "Revision general: peso, temperatura y estado del pelaje/plumaje.";
	}

	public String generarDiagnostico() {
		return "Estado general estable, se recomienda revision anual.";
	}

	/* Setters */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	/* Getters */
	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public String getRaza() {
		return raza;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	/* *********************** Informacion ************************/
	@Override
	public String toString() {
		/* getTipo() tambien es polimorfico: imprime Perro/Gato/Ave segun el objeto */
		return String.format("[%-7s] %-10s (%-12s, %2d anios) | Diagnostico: %s",
				getTipo(), nombre, raza, edad, diagnostico);
	}
}
