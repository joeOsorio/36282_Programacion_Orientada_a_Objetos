/* **************************************************
 * Laboratorio 6: Clase Mascota
 * Datos de una mascota registrada en la veterinaria.
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Mascota {

	/* Informacion sensible (personal y del dueno) */
	private int id;
	private String nombre;
	private String especie;
	private int edad;
	private String nombreDueno;
	private String telefonoDueno;
	private String diagnostico;

	public Mascota(int id, String nombre, String especie, int edad, String nombreDueno, String telefonoDueno) {
		this.id = id;
		this.nombre = nombre;
		this.especie = especie;
		this.edad = edad;
		this.nombreDueno = nombreDueno;
		this.telefonoDueno = telefonoDueno;
		this.diagnostico = null; /* Todavia no tiene diagnostico */
	}

	/* Setters */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setNombreDueno(String nombreDueno) {
		this.nombreDueno = nombreDueno;
	}

	public void setTelefonoDueno(String telefonoDueno) {
		this.telefonoDueno = telefonoDueno;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	/* Getters */
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public int getEdad() {
		return edad;
	}

	public String getNombreDueno() {
		return nombreDueno;
	}

	public String getTelefonoDueno() {
		return telefonoDueno;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	/* *********************** Otros metodos ************************/
	public boolean tieneDiagnostico() {
		return diagnostico != null && !diagnostico.isEmpty();
	}

	/*
	 * @Override
	 * public String toString() {
	 * String diag = tieneDiagnostico() ? diagnostico : "Sin diagnostico";
	 * return String.format(
	 * "[Mascota #%d] %s (%s, %d anios) | Dueno: %s, tel. %s | Diagnostico: %s",
	 * id, nombre, especie, edad, nombreDueno, telefonoDueno, diag);
	 * }
	 */

	@Override
	public String toString() {
		String diag = tieneDiagnostico() ? diagnostico : "Sin diagnostico";
		return String.format("[Mascota %-2d]\t%-10s (%-8s, %2d anios) | Dueño: %-17s, tel. %-14s | Diagnostico: %s",
				id, nombre, especie, edad, nombreDueno, telefonoDueno, diag);
	}
}
