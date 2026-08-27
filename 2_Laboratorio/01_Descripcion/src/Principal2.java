/* **************************************************
 * Laboratorio 1: Clase Principal, creacion de menus prog2
 * @Author: J03_0$0
 * @Date: Agust/2026
 * **************************************************/

import java.util.Scanner;

public class Principal2 {
	/* Inicializar Scanner como si fuera global para usar en todo en otros metodos*/
	
	private static Scanner input = new Scanner(System.in);
	
	/* Creear arreglo de libros no inicializados */
	private static Libro[] libros = new Libro[3];
	
	public static void main(String[] args){
		int opcion;
		do{
			System.out.println("----------------------------------------");
			System.out.println("          LABORATORIO 1: Programa 2        ");
			System.out.println("----------------------------------------");
			System.out.println("1 -\tCapturar datos del Libro");
			System.out.println("2 -\tBuscar Libro");
			System.out.println("3 -\tSalir");
			System.out.printf("\nOpcion:\t");
			
			opcion = input.nextInt();
			input.nextLine();
			/*input.nexLine();Imortante para limpiar buffer*/		
			switch (opcion){
				case 1:
					capturarInfo();
					break;
				case 2:
					buscarLibro();
					break;
				case 3:
					System.out.println("Saliendo...");
					break;
				case 4:
					test();
					mostrarInformacion();
					/* buscarLibros(); */
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo.");
			}

		} while (opcion != 3);
		/* Cerrar el Scanner es buena practica, creeo que no es necesario por lo que recoge basura*/
		input.close();
	}

	private static void test(){
		/* Test para no estar rellenando en las pruebas de ver la info */
		libros[0] = new Libro();
		libros[0].setTitulo("El Principito");
    libros[0].setAutor("Antoine de Saint");
    libros[0].setEditorial("Reynal & Hitchcock");
    libros[0].setAno(1943);
    libros[0].setPaginas(96);

    libros[1] = new Libro();
		libros[1].setTitulo("El arte de amar");
    libros[1].setAutor("Erich Fromm");
    libros[1].setEditorial("Paidos");
    libros[1].setAno(1959);
    libros[1].setPaginas(128);
		
		libros[2] = new Libro();
		libros[2].setTitulo("Amor líquido");
    libros[2].setAutor("Zygmunt Bauman");
    libros[2].setEditorial("Fondo de Cultura Económica");
    libros[2].setAno(2005);
    libros[2].setPaginas(1500);
		}

	private static void mostrarInformacion(){
		// Variable l  auxiliar para no escribir tanto.
		Libro l;
		System.out.printf("|%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n","Titulo", "Autor","Editorial", "Ano", "Paginas");
		for(int i = 0; i < 3; i++){
			l = libros[i];
			System.out.printf("\n%-10s\t%-10s\t\t%-10s\t\t%-4d\t\t%-4d\n", l.getTitulo(), l.getAutor(), l.getEditorial(), l.getAno(), l.getPaginas());
		}
	}

	private static void capturarInfo(){
		String 	tempTxt;
		int	    tempInt;
		Libro l;
		System.out.println("\nCaptura de informacion");
		for(int libro = 0; libro < 3; libro++){
			libros[libro] = new Libro();
			l = libros[libro];
			System.out.printf("\n---------- Libro %d ----------\n", libro + 1);

			System.out.println("TItulo:\t");
			tempTxt = input.nextLine();
			l.setTitulo(tempTxt);

			System.out.println("Autor:\t");
			tempTxt = input.nextLine();
			l.setAutor(tempTxt);

			System.out.println("Editorial:\t");
			tempTxt = input.nextLine();
			l.setEditorial(tempTxt);

			System.out.println("Ano:\t dddd");
			tempInt = input.nextInt();
			l.setAno(tempInt);

			System.out.println("Paginas:\t dddddd");
			tempInt =input.nextInt();
			input.nextLine();
			l.setPaginas(tempInt);
		}


	}

	private static void buscarLibro(){
		String tempTxt;
		boolean encontrado = false;
		Libro l;
		System.out.println("\nTitulo del libro a buscar:\t");
		tempTxt = input.nextLine();
		
		for(int libro = 0; libro < libros.length; libro++){
		  l= libros[libro];
			if( l != null && l.getTitulo().equalsIgnoreCase(tempTxt)){
				System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n","Titulo", "Autor","Editorial", "Ano", "Paginas");
				System.out.printf("\n%-10s\t%-10s\t\t%-10s\t\t%-4d\t\t%-4d\n", l.getTitulo(), l.getAutor(), l.getEditorial(), l.getAno(), l.getPaginas());
				encontrado = true;
				break;
			}
		}
		if(!encontrado){
			System.out.println("\nLibro no encontrado.");
		}
	}
}
