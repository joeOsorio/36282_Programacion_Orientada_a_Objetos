package Prueba;
/* **************************************************
 * Laboratorio 4: Programa principal, gestion de libreria
 * @Author: J03_0$0
 * @Date: Agust/2026
 * **************************************************/

import java.util.Scanner;

public class ProgramaLibreria {

	/* Scanner "global" para usarlo en todos los metodos */
	private static Scanner input = new Scanner(System.in);

	private static Libreria libreria = new Libreria();

	public static void main(String[] args) {
		int opcion;
		do {
			System.out.println("----------------------------------------");
			System.out.println("          LABORATORIO 4: Libreria        ");
			System.out.println("----------------------------------------");
			System.out.println("1 -\tRegistrar");
			System.out.println("2 -\tBuscar");
			System.out.println("3 -\tEditar");
			System.out.println("4 -\tEliminar");
			System.out.println("5 -\tMostrar todos");
			System.out.println("0 -\tSalir");
			System.out.printf("\nOpcion:\t");

			opcion = input.nextInt();
			input.nextLine();
			/* input.nextLine(); Importante para limpiar buffer */
			switch (opcion) {
				case 1:
					registrarLibro();
					break;
				case 2:
					buscarLibro();
					break;
				case 3:
					editarLibro();
					break;
				case 4:
					eliminarLibro();
					break;
				case 5:
					libreria.mostrarInformacion();
					break;
				case 6:
					test();
					break;
				case 0:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo.");
			}

		} while (opcion != 0);
		/*
		 * Cerrar el Scanner es buena practica, creeo que no es necesario por lo que
		 * recoge basura
		 */
		input.close();
	}

	private static void registrarLibro() {
		String titulo, autor, editorial;
		int ano, paginas;

		System.out.println("\n---------- Registrar libro ----------");
		System.out.print("Titulo:\t\t");
		titulo = input.nextLine();
		System.out.print("Autor:\t\t");
		autor = input.nextLine();
		System.out.print("Editorial:\t");
		editorial = input.nextLine();
		System.out.print("Ano:\t\t");
		ano = input.nextInt();
		System.out.print("Paginas:\t");
		paginas = input.nextInt();
		input.nextLine();

		if (libreria.registrarLibro(titulo, autor, editorial, ano, paginas)) {
			System.out.println("\nLibro registrado con exito.");
		} else {
			System.out.println("\nLa libreria esta llena, no se puede registrar mas libros.");
		}
	}

	private static void buscarLibro() {
		System.out.print("\nTitulo del libro a buscar:\t");
		String titulo = input.nextLine();

		Libro l = libreria.buscarLibro(titulo);
		if (l != null) {
			System.out.printf("%-20s%-20s%-25s%-6s%-8s%n", "Titulo", "Autor", "Editorial", "Ano", "Paginas");
			System.out.println(l);
		} else {
			System.out.println("\nLibro no encontrado.");
		}
	}

	private static void editarLibro() {
		System.out.print("\nTitulo del libro a editar:\t");
		String tituloBuscado = input.nextLine();

		if (libreria.buscarLibro(tituloBuscado) == null) {
			System.out.println("\nLibro no encontrado.");
			return;
		}

		String titulo, autor, editorial;
		int ano, paginas;

		System.out.println("\nCaptura los nuevos datos del libro:");
		System.out.print("Titulo:\t\t");
		titulo = input.nextLine();
		System.out.print("Autor:\t\t");
		autor = input.nextLine();
		System.out.print("Editorial:\t");
		editorial = input.nextLine();
		System.out.print("Ano:\t\t");
		ano = input.nextInt();
		System.out.print("Paginas:\t");
		paginas = input.nextInt();
		input.nextLine();

		libreria.editarLibro(tituloBuscado, titulo, autor, editorial, ano, paginas);
		System.out.println("\nLibro editado con exito.");
	}

	private static void eliminarLibro() {
		System.out.print("\nTitulo del libro a eliminar:\t");
		String titulo = input.nextLine();

		if (libreria.eliminarLibro(titulo)) {
			System.out.println("\nLibro eliminado con exito.");
		} else {
			System.out.println("\nLibro no encontrado.");
		}
	}

	/*
	 * Recorre todas las operaciones de Libreria con datos fijos, sin pedir
	 * entrada al usuario, para comprobar rapidamente que la clase funciona.
	 */
	private static void test() {
		System.out.println("\n========== TEST: todas las operaciones ==========");

		System.out.println("\n-- Registrar --");
		libreria.registrarLibro("El Principito", "Antoine de Saint", "Reynal & Hitchcock", 1943, 96);
		libreria.registrarLibro("El arte de amar", "Erich Fromm", "Paidos", 1959, 128);
		libreria.registrarLibro("Amor liquido", "Zygmunt Bauman", "Fondo de Cultura Economica", 2005, 1500);
		libreria.mostrarInformacion();

		System.out.println("\n-- Buscar (\"El arte de amar\") --");
		Libro l = libreria.buscarLibro("El arte de amar");
		if (l != null) {
			System.out.printf("%-20s%-20s%-25s%-6s%-8s%n", "Titulo", "Autor", "Editorial", "Ano", "Paginas");
			System.out.println(l);
		} else {
			System.out.println("Libro no encontrado.");
		}

		System.out.println("\n-- Editar (\"El arte de amar\") --");
		libreria.editarLibro("El arte de amar", "El arte de amar (2da ed.)", "Erich Fromm", "Paidos", 1960, 130);
		libreria.mostrarInformacion();

		System.out.println("\n-- Eliminar (\"Amor liquido\") --");
		libreria.eliminarLibro("Amor liquido");
		libreria.mostrarInformacion();

		System.out.println("\n========== FIN DEL TEST ==========");
	}
}
