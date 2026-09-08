/* **************************************************
 * Laboratorio 5: Clase Empresa
 * Administra los arreglos de empleados y equipos.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Empresa {

	private static final int MAX_EMPLEADOS = 50;
	private static final int MAX_EQUIPOS = 10;

	private Empleado[] empleados;
	private int contadorEmpleados;
	private Equipo[] equipos;
	private int contadorEquipos;
	private int siguienteNumeroEmpleado;

	public Empresa() {
		empleados = new Empleado[MAX_EMPLEADOS];
		contadorEmpleados = 0;
		equipos = new Equipo[MAX_EQUIPOS];
		contadorEquipos = 0;
		siguienteNumeroEmpleado = 1;
	}

	/* *********************** Contratar empleado ************************/
	public Empleado contratarEmpleado(String nombre) {
		if (contadorEmpleados >= empleados.length) {
			System.out.println("Error: la empresa alcanzo el maximo de empleados contratados.");
			return null;
		}
		if (nombre == null || nombre.trim().isEmpty()) {
			System.out.println("Error: el nombre del empleado no puede estar vacio.");
			return null;
		}
		Empleado nuevo = new Empleado(siguienteNumeroEmpleado, nombre);
		empleados[contadorEmpleados++] = nuevo;
		siguienteNumeroEmpleado++;
		return nuevo;
	}

	/* *********************** Buscar empleado ************************/
	public Empleado buscarEmpleado(int numeroEmpleado) {
		for (int i = 0; i < contadorEmpleados; i++) {
			if (empleados[i].getNumeroEmpleado() == numeroEmpleado) {
				return empleados[i];
			}
		}
		return null;
	}

	/* *********************** Crear equipo ************************/
	public Equipo crearEquipo(String nombreEquipo) {
		if (contadorEquipos >= equipos.length) {
			System.out.println("Error: la empresa alcanzo el maximo de equipos registrados.");
			return null;
		}
		if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
			System.out.println("Error: el nombre del equipo no puede estar vacio.");
			return null;
		}
		if (buscarEquipo(nombreEquipo) != null) {
			System.out.println("Error: ya existe un equipo con el nombre " + nombreEquipo + ".");
			return null;
		}
		Equipo nuevo = new Equipo(nombreEquipo);
		equipos[contadorEquipos++] = nuevo;
		return nuevo;
	}

	/* *********************** Buscar equipo ************************/
	public Equipo buscarEquipo(String nombreEquipo) {
		for (int i = 0; i < contadorEquipos; i++) {
			if (equipos[i].getNombre().equalsIgnoreCase(nombreEquipo)) {
				return equipos[i];
			}
		}
		return null;
	}

	/* *********************** Asignar empleado a equipo ************************/
	/*
	 * Un Equipo solo agrupa a empleados que ya existen en la Empresa: primero
	 * se busca al empleado (debe estar contratado) y despues se agrega al
	 * equipo. Como es asociacion y no composicion, un mismo empleado puede
	 * quedar asignado a varios equipos sin ningun problema.
	 */
	public boolean asignarEmpleadoAEquipo(int numeroEmpleado, String nombreEquipo) {
		Empleado empleado = buscarEmpleado(numeroEmpleado);
		if (empleado == null) {
			System.out.println("Error: no existe ningun empleado contratado con el numero " + numeroEmpleado + ".");
			return false;
		}
		Equipo equipo = buscarEquipo(nombreEquipo);
		if (equipo == null) {
			System.out.println("Error: no existe ningun equipo con el nombre " + nombreEquipo + ".");
			return false;
		}
		return equipo.agregarMiembro(empleado);
	}

	/* *********************** Info de la empresa ************************/
	public void imprimirEmpleados() {
		if (contadorEmpleados == 0) {
			System.out.println("La empresa aun no tiene empleados contratados.");
			return;
		}
		for (int i = 0; i < contadorEmpleados; i++) {
			System.out.println("  " + empleados[i]);
		}
	}

	public void imprimirEquipos() {
		if (contadorEquipos == 0) {
			System.out.println("La empresa aun no tiene equipos registrados.");
			return;
		}
		for (int i = 0; i < contadorEquipos; i++) {
			Equipo equipo = equipos[i];
			System.out.println("\nEquipo: " + equipo.getNombre());
			System.out.print(equipo.miembrosToString());
		}
	}

	public int getContadorEmpleados() {
		return contadorEmpleados;
	}

	public int getContadorEquipos() {
		return contadorEquipos;
	}
}
