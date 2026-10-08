/* **************************************************
 * Laboratorio 5: Clase Empleado
 * Representa a un empleado contratado por la Empresa:
 * numero de empleado (asignado por la Empresa al
 * contratarlo) y nombre. Un mismo Empleado puede
 * pertenecer a varios Equipos (asociacion), pero solo
 * la Empresa lo crea y lo administra (composicion).
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Empleado {

	private int numeroEmpleado;
	private String nombre;

	public Empleado(int numeroEmpleado, String nombre) {
		this.numeroEmpleado = numeroEmpleado;
		this.nombre = nombre;
	}

	public Empleado() {
		this(0, "");
	}

	/* Setters */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroEmpleado(int numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	/* Getters */
	public String getNombre() {
		return nombre;
	}

	public int getNumeroEmpleado() {
		return numeroEmpleado;
	}

	/* Para imprimir al empleado dentro de las listas de un Equipo */
	@Override
	public String toString() {
		return String.format("#%d - %s", numeroEmpleado, nombre);
	}
}
