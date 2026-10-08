import java.util.InputMismatchException; /* Para el try catch */
import java.util.Scanner;

/* **************************************************
 * Laboratorio 5: Programa principal 
 * Orquesta las clases Empresa, Empleado y Equipo.
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Main {

	private static Scanner input = new Scanner(System.in);
	private static Empresa empresa = new Empresa();

	public static void main(String[] args) {
		int opcion;

		do {
			System.out.println("\n==================================================");
			System.out.println("        EMPRESA - GESTION DE RECURSOS HUMANOS      ");
			System.out.println("==================================================");
			System.out.println("1 -\tContratar empleado");
			System.out.println("2 -\tCrear equipo de proyecto");
			System.out.println("3 -\tAsignar empleado a un equipo");
			System.out.println("4 -\tMostrar empleados de la empresa");
			System.out.println("5 -\tMostrar equipos de la empresa");
			System.out.println("6 -\tCargar datos de prueba");
			System.out.println("0 -\tSalir del programa");
			System.out.printf("\nOpcion:\t");

			opcion = leerEntero();
			switch (opcion) {
				case 1:
					contratarEmpleado();
					break;
				case 2:
					crearEquipo();
					break;
				case 3:
					asignarEmpleadoAEquipo();
					break;
				case 4:
					System.out.println("\n---------- Empleados de la empresa ----------");
					empresa.imprimirEmpleados();
					break;
				case 5:
					System.out.println("\n---------- Equipos de la empresa ----------");
					empresa.imprimirEquipos();
					break;
				case 6:
					cargarDatosPrueba();
					break;
				case 7:
					test();
					break;
				case 0:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo.");
			}
		} while (opcion != 0);

		input.close();
	}

	/* *********************** Contratar empleado ************************/
	private static void contratarEmpleado() {
		System.out.println("\n---------- Contratar empleado ----------");
		System.out.print("Nombre del empleado:\t");
		String nombre = input.nextLine();

		Empleado nuevo = empresa.contratarEmpleado(nombre);
		if (nuevo != null) {
			System.out.printf("\nEmpleado contratado con exito. Numero de empleado: %d\n", nuevo.getNumeroEmpleado());
			System.out.println("Conserva este numero, lo necesitas para asignarlo a un equipo.");
		}
	}

	/* *********************** Crear equipo ************************/
	private static void crearEquipo() {
		System.out.println("\n---------- Crear equipo de proyecto ----------");
		System.out.print("Nombre del equipo (ej. backend_poo):\t");
		String nombreEquipo = input.nextLine();

		Equipo nuevo = empresa.crearEquipo(nombreEquipo);
		if (nuevo != null) {
			System.out.printf("\nEquipo \"%s\" creado con exito. Aun no tiene empleados asignados.\n",
					nuevo.getNombre());
		}
	}

	/* *********************** Asignar empleado a equipo ************************/
	private static void asignarEmpleadoAEquipo() {
		System.out.println("\n---------- Asignar empleado a un equipo ----------");
		System.out.print("Numero de empleado:\t");
		int numeroEmpleado = leerEntero();
		System.out.print("Nombre del equipo:\t");
		String nombreEquipo = input.nextLine();

		boolean exito = empresa.asignarEmpleadoAEquipo(numeroEmpleado, nombreEquipo);
		if (exito) {
			System.out.printf("\nEl empleado %d fue asignado al equipo \"%s\".\n", numeroEmpleado, nombreEquipo);
		}
	}

	/*
	 * *********************** Datos de prueba (para probar rapido)
	 ************************/
	private static void cargarDatosPrueba() {
		Empleado e1 = empresa.contratarEmpleado("Ana Torres");
		Empleado e2 = empresa.contratarEmpleado("Luis Perez");
		Empleado e3 = empresa.contratarEmpleado("Maria Lopez");
		Empleado e4 = empresa.contratarEmpleado("Carlos Ruiz");

		empresa.crearEquipo("backend_poo");
		empresa.crearEquipo("frontend_poo");
		empresa.crearEquipo("qa_poo"); /* Haber que pasa si lo dejo vacio */

		empresa.asignarEmpleadoAEquipo(e1.getNumeroEmpleado(), "backend_poo");
		empresa.asignarEmpleadoAEquipo(e2.getNumeroEmpleado(), "backend_poo");
		empresa.asignarEmpleadoAEquipo(e3.getNumeroEmpleado(), "frontend_poo");
		/*
		 * e4 se asigna a los dos equipos para probar que un empleado puede estar en
		 * varios
		 */
		empresa.asignarEmpleadoAEquipo(e4.getNumeroEmpleado(), "backend_poo");
		empresa.asignarEmpleadoAEquipo(e4.getNumeroEmpleado(), "frontend_poo");

		System.out.println("\nSe cargaron 4 empleados y 3 equipos de prueba (backend_poo, frontend_poo y qa_poo).");
	}

	/*
	 * *********************** Prueba automatica de todos los metodos
	 ************************/
	private static void test() {
		System.out.println("\n========== TEST: todas las operaciones ==========");

		Empresa empresaPrueba = new Empresa();

		System.out.println("\n---------- 1) Contratar empleados ----------");
		Empleado e1 = empresaPrueba.contratarEmpleado("Ana Torres");
		System.out.println("Contratado: " + e1);
		Empleado e2 = empresaPrueba.contratarEmpleado("Luis Perez");
		System.out.println("Contratado: " + e2);
		Empleado e3 = empresaPrueba.contratarEmpleado("Maria Lopez");
		System.out.println("Contratado: " + e3);

		System.out.println("\nContratar empleado con nombre vacio (invalido, debe rechazar):");
		empresaPrueba.contratarEmpleado("");

		System.out.println("\n---------- 2) Crear equipos ----------");
		Equipo backend = empresaPrueba.crearEquipo("backend_poo");
		System.out.println("Creado: " + backend);
		Equipo frontend = empresaPrueba.crearEquipo("frontend_poo");
		System.out.println("Creado: " + frontend);
		Equipo qa = empresaPrueba.crearEquipo("qa_poo");
		System.out.println("Creado (se dejara vacio): " + qa);

		System.out.println("\nCrear equipo con nombre repetido (invalido, debe rechazar):");
		empresaPrueba.crearEquipo("backend_poo");

		System.out.println("\n---------- 3) Los equipos solo agrupan empleados existentes ----------");
		System.out.println("Asignar empleado inexistente (numero 999) a backend_poo (invalido, debe rechazar):");
		empresaPrueba.asignarEmpleadoAEquipo(999, "backend_poo");

		System.out.println("\nAsignar empleado valido a un equipo inexistente (invalido, debe rechazar):");
		empresaPrueba.asignarEmpleadoAEquipo(e1.getNumeroEmpleado(), "devops_poo");

		System.out.println("\nAsignar empleados validos a equipos existentes:");
		System.out.println(
				"e1 -> backend_poo: " + empresaPrueba.asignarEmpleadoAEquipo(e1.getNumeroEmpleado(), "backend_poo"));
		System.out.println(
				"e2 -> backend_poo: " + empresaPrueba.asignarEmpleadoAEquipo(e2.getNumeroEmpleado(), "backend_poo"));
		System.out.println(
				"e3 -> frontend_poo: " + empresaPrueba.asignarEmpleadoAEquipo(e3.getNumeroEmpleado(), "frontend_poo"));

		System.out.println("\n---------- 4) Un empleado puede estar en multiples equipos ----------");
		System.out.println("e1 -> frontend_poo (mismo empleado, otro equipo): "
				+ empresaPrueba.asignarEmpleadoAEquipo(e1.getNumeroEmpleado(), "frontend_poo"));

		System.out.println("\nIntentar asignar de nuevo e1 -> backend_poo (ya es miembro, debe rechazar):");
		empresaPrueba.asignarEmpleadoAEquipo(e1.getNumeroEmpleado(), "backend_poo");

		System.out.println("\n---------- 5) Los equipos pueden estar vacios ----------");
		System.out.println("qa_poo esta vacio: " + qa.estaVacio());

		System.out.println("\n---------- 6) Informacion de los equipos de la empresa ----------");
		empresaPrueba.imprimirEquipos();

		System.out.println("\n---------- 7) Informacion de los empleados de la empresa ----------");
		empresaPrueba.imprimirEmpleados();

		System.out.println("\n========== FIN DEL TEST ==========");
	}

	/* *********************** Lectura segura de datos ************************/
	private static int leerEntero() {
		while (true) {
			try {
				int valor = input.nextInt();
				input.nextLine(); /* importante limpiar el buffer. */
				return valor;
			} catch (InputMismatchException e) {
				System.out.print("Valor invalido:\t");
				input.nextLine();
			}
		}
	}
}
