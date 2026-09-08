/* **************************************************
 * Laboratorio 1: Clase Principal, creacion de menus
 * @Author: J03_0$0
 * @Date: Agust/2026
 * **************************************************/

import java.util.Scanner;

public class Principal1 {
	/* Inicializar Scanner como si fuera global para usar en todo en otros metodos*/
	
	private static Scanner input = new Scanner(System.in);
	private static Alumno[] alumnos = new Alumno[3];
	
	public static void main(String[] args){
		/*Inicializar Scanner */	
		/* Scanner input = new Scanner(System.in);*/
	
		int opcion;
		/* Creear arreglo de alumnos no inicializados */
		/* Alumno alumnos[] = new Alumno[3];*/
		do{
			System.out.println("----------------------------------------");
			System.out.println("          LABORATORIO 1: CLASES         ");
			System.out.println("----------------------------------------");
			System.out.println("1 -\tCapturar datos de los alumnos");
			System.out.println("2 -\tMostrar informacion");
			System.out.println("3 -\tSalir");
			System.out.printf("\nOpcion:\t");
			
			opcion = input.nextInt();
			input.nextLine();
			/*input.nexLine();Imortante para limpiar buffer*/		
			switch (opcion){
				case 1:
					capturarNombres();
					break;
				case 2:
					mostrarInformacion();
					break;
				case 3:
					System.out.println("Saliendo...");
					break;
				case 4:
					test();
					mostrarInformacion();
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
		alumnos[0] = new Alumno();
		alumnos[0].setNombre("Elias Gabriel");
		alumnos[0].setApellidos("Frausto De La Rosa");
		alumnos[0].setMatricula(1293270);
		alumnos[0].setCalificacion1(80);
		alumnos[0].setCalificacion2(80);
		alumnos[0].setCalificacion3(80);
	
		alumnos[1] = new Alumno();
		alumnos[1].setNombre("Armando Damian");
		alumnos[1].setApellidos("De La Rosa Hernandez");
		alumnos[1].setMatricula(1293271);
		alumnos[1].setCalificacion1(80);
		alumnos[1].setCalificacion2(90);
		alumnos[1].setCalificacion3(100);
	
		alumnos[2] = new Alumno();
		alumnos[2].setNombre("Ethan Oleg");
		alumnos[2].setApellidos("Osorio Bernal");
		alumnos[2].setMatricula(1293272);
		alumnos[2].setCalificacion1(100);
		alumnos[2].setCalificacion2(90);
		alumnos[2].setCalificacion3(100);
	}

	private static void mostrarInformacion(){
		// Variable a  auxiliar para no escribir tanto.
		Alumno a;
		System.out.printf("|%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s","Nombre", "Matricula", "Parcial 1", "Parcial 2", "Parcial 3", "Promedio");
		for(int i = 0; i < 3; i++){
			a = alumnos[i];
			System.out.printf("\n%-10s\t%-7d\t\t%-3.0f\t\t%-3.0f\t\t%-3.0f\t\t%-3.2f\n", a.getNombres(), a.getMatricula(), a.getCalificacion1(), a.getCalificacion2(), a.getCalificacion3(), a.promedio());
		}
	
	}

	private static void capturarNombres(){
		String 	tempTxt;
		int	tempInt;
		System.out.println("\nCaptura de informacion");
		for(int alumno= 0; alumno < 3; alumno++){
			alumnos[alumno] = new Alumno();
			System.out.printf("\n---------- Alumno %d ----------\n", alumno+1);
			
			/* Nombes*/
			System.out.println("Nombres:\t");
			tempTxt = input.nextLine();
			alumnos[alumno].setNombre(tempTxt);
			
			System.out.println("Apellidos:\t");
			tempTxt = input.nextLine();
			alumnos[alumno].setApellidos(tempTxt);

			System.out.println("Matricula de 6 digitos:\t");
			tempInt = input.nextInt();
			alumnos[alumno].setMatricula(tempInt);
			
			System.out.println("\n\nCapturar de  calificaciones");
			/* Capturar las 3 calificaciones */
			// for(int cal = 0; cal < 3; cal++){
			System.out.println("Calificacion\t1\t\t0 - 100");
			tempInt = input.nextInt();
			alumnos[alumno].setCalificacion1(tempInt);
			//}
			
			System.out.println("Calificiacion\t2\t\t0 - 100");
			tempInt = input.nextInt();
			alumnos[alumno].setCalificacion2(tempInt);

			System.out.println("Calificacion\t3\t\t0 - 100");
			tempInt =input.nextInt();
			input.nextLine();
			alumnos[alumno].setCalificacion3(tempInt);
		}
		
	
	}
}
