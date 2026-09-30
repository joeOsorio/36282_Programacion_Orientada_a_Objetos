/* **************************************************
 * Taller 7: Clase Veterinaria
 * Registra hasta 50 mascotas de cualquier tipo en un
 * solo arreglo de Mascota (upcasting) y le pide a su
 * doctor que las consulte y diagnostique.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Veterinaria {

	public static final int MAX_MASCOTAS = 50;

	/*
	 * Agregacion: las mascotas se crean afuera (en el Main) y la Veterinaria solo
	 * guarda la referencia. Un mismo arreglo de Mascota guarda Perros, Gatos y Aves.
	 */
	private Mascota[] mascotas;
	private int contador;
	private Doctor doctor;

	public Veterinaria(String nombreDoctor, String cedula) {
		this.mascotas = new Mascota[MAX_MASCOTAS];
		this.contador = 0;
		this.doctor = new Doctor(nombreDoctor, cedula);
	}

	/* *********************** Gestion de mascotas ************************/
	public boolean registrarMascota(Mascota mascota) {
		if (mascota == null) {
			System.out.println("Error: la mascota no existe.");
			return false;
		}
		if (contador >= MAX_MASCOTAS) {
			System.out.println("Error: ya se alcanzo el limite de mascotas (" + MAX_MASCOTAS + ").");
			return false;
		}
		if (mascota.getNombre() == null || mascota.getNombre().isEmpty()) {
			System.out.println("Error: el nombre de la mascota es obligatorio.");
			return false;
		}
		if (mascota.getEdad() < 0) {
			System.out.println("Error: la edad no puede ser negativa.");
			return false;
		}
		if (buscarMascota(mascota.getNombre()) != null) {
			System.out.println("Error: ya existe una mascota con ese nombre.");
			return false;
		}
		mascotas[contador++] = mascota;
		return true;
	}

	public Mascota buscarMascota(String nombre) {
		if (nombre == null) {
			return null;
		}
		for (int i = 0; i < contador; i++) {
			if (mascotas[i].getNombre().equalsIgnoreCase(nombre)) {
				return mascotas[i];
			}
		}
		return null;
	}

	public boolean darDiagnostico(String nombre) {
		Mascota mascota = buscarMascota(nombre);
		if (mascota == null) {
			System.out.println("Error: no existe una mascota con ese nombre.");
			return false;
		}
		System.out.println("Antes:   " + mascota);
		doctor.darConsulta(mascota);
		doctor.diagnosticar(mascota);
		System.out.println("Despues: " + mascota); /* Muestra que si cambio */
		return true;
	}

	/* *********************** Informacion ************************/
	public void mostrarMascotas() {
		if (contador == 0) {
			System.out.println("No hay mascotas registradas.");
			return;
		}
		for (int i = 0; i < contador; i++) {
			System.out.println("  " + mascotas[i]);
		}
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public int getContador() {
		return contador;
	}
}
