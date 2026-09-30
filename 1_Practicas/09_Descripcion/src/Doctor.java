/* **************************************************
 * Taller 7: Clase Doctor
 * Da consulta y diagnostico a CUALQUIER mascota.
 * Recibe el parametro como Mascota (tipo padre) y
 * gracias al dynamic dispatching se ejecuta el metodo
 * del animal real (Perro, Gato o Ave).
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Doctor {

	private String nombre;
	private String cedula;

	public Doctor(String nombre, String cedula) {
		this.nombre = nombre;
		this.cedula = cedula;
	}

	/* *********************** Atencion (polimorfismo) ************************/
	/*
	 * Dependencia: el doctor NO guarda mascotas, solo las recibe como parametro.
	 * Un solo metodo sirve para todos los animales, no hay que hacer un
	 * darConsultaPerro(), darConsultaGato(), etc.
	 */
	public void darConsulta(Mascota mascota) {
		if (mascota == null) {
			System.out.println("Error: no hay mascota que consultar.");
			return;
		}
		System.out.println("Dr. " + nombre + " consulta a " + mascota.getNombre()
				+ " (" + mascota.getTipo() + ")");
		System.out.println("  -> " + mascota.examinar());
	}

	public void diagnosticar(Mascota mascota) {
		if (mascota == null) {
			System.out.println("Error: no hay mascota que diagnosticar.");
			return;
		}
		mascota.setDiagnostico(mascota.generarDiagnostico());
	}

	/* Getters */
	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}

	/* *********************** Informacion ************************/
	@Override
	public String toString() {
		return String.format("[Doctor] %s | Cedula: %s", nombre, cedula);
	}
}
