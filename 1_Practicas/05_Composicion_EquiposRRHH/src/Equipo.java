/* **************************************************
 * Laboratorio 5: Clase Equipo
 * Representa un equipo de proyecto dentro de la
 * Empresa: nombre y hasta 5 miembros. Un Equipo solo
 * agrupa (asociacion) a Empleados que ya existen en la
 * Empresa y un mismo Empleado puede estar en varios Equipos.
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Equipo {

	private static final int MAX_MIEMBROS = 5;

	private String nombre;
	private Empleado[] miembros;
	private int contador;

	public Equipo(String nombre) {
		this.nombre = nombre;
		this.miembros = new Empleado[MAX_MIEMBROS];
		this.contador = 0;
	}

	public Equipo() {
		this("");
	}

	/* Setters */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* Getters */
	public String getNombre() {
		return nombre;
	}

	public int getContador() {
		return contador;
	}

	/* *********************** Agregar miembro ************************/
	public boolean agregarMiembro(Empleado empleado) {
		if (empleado == null) {
			System.out.println("Error: el empleado no puede ser nulo.");
			return false;
		}
		if (contador >= miembros.length) {
			System.out.println("Error: el equipo " + nombre + " alcanzo el maximo de miembros.");
			return false;
		}
		if (yaEsMiembro(empleado.getNumeroEmpleado())) {
			System.out
					.println("Error: el empleado " + empleado.getNombre() + " ya pertenece al equipo " + nombre + ".");
			return false;
		}
		miembros[contador++] = empleado;
		return true;
	}

	/* *********************** Eliminar miembro ************************/
	public boolean eliminarEmpleado(int numeroEmpleado) {
		for (int i = 0; i < contador; i++) {
			if (miembros[i].getNumeroEmpleado() == numeroEmpleado) {
				for (int j = i; j < contador - 1; j++) {
					miembros[j] = miembros[j + 1];
				}
				miembros[contador - 1] = null; /* importante para no tener duplicado al ultimo miembro */
				contador--;
				return true;
			}
		}
		return false;
	}

	/* El Equipo no crea empleados, solo verifica que no se repitan dentro de el */
	private boolean yaEsMiembro(int numeroEmpleado) {
		for (int i = 0; i < contador; i++) {
			if (miembros[i].getNumeroEmpleado() == numeroEmpleado) {
				return true;
			}
		}
		return false;
	}

	public boolean estaVacio() {
		return contador == 0;
	}

	/* *********************** Info del equipo ************************/
	public String miembrosToString() {
		if (contador == 0) {
			return "  (equipo vacio, sin empleados)\n";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < contador; i++) {
			sb.append("  ").append(i + 1).append(". ").append(miembros[i]).append("\n");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return String.format("Equipo: %s (%d/%d miembros)", nombre, contador, miembros.length);
	}
}
