import java.util.InputMismatchException; /* Para el try catch */
import java.util.Scanner;

/* **************************************************
 * Taller 7: Programa principal
 * Menu del laboratorio 3 (registro y diagnostico),
 * ahora con herencia y polimorfismo.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Main {

	private static Scanner input = new Scanner(System.in);
	private static Veterinaria vet = new Veterinaria("Yuliana Flores", "CED-12345");

	public static void main(String[] args) {
		int opcion;

		do {
			System.out.println("\n==================================================");
			System.out.println("                   VETERINARIA                    ");
			System.out.println("==================================================");
			System.out.println(vet.getDoctor());
			System.out.println("Mascotas registradas: " + vet.getContador() + "/" + Veterinaria.MAX_MASCOTAS);
			System.out.println("\n1 -\tRegistro mascota");
			System.out.println("2 -\tDar diagnostico");
			System.out.println("3 -\tSalir");

			opcion = leerEntero("\nOpcion:\t");
			switch (opcion) {
				case 1:
					registrarMascota();
					break;
				case 2:
					darDiagnostico();
					break;
				case 3:
					System.out.println("Saliendo del programa...");
					break;
				case 4: /* Oculto */
					test();
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo.");
			}
		} while (opcion != 3);

		input.close();
	}

	/* *********************** Menu ************************/
	private static void registrarMascota() {
		System.out.println("\n----------- Registro de mascota -----------");
		System.out.println("1 -\tPerro");
		System.out.println("2 -\tGato");
		System.out.println("3 -\tAve");
		System.out.println("4 -\tOtro");
		int tipo = leerEntero("Tipo:\t");

		Mascota nueva = crearMascota(tipo);
		if (nueva != null && vet.registrarMascota(nueva)) {
			System.out.println("Registrada: " + nueva);
		}
	}

	private static void darDiagnostico() {
		System.out.println("\n----------- Dar diagnostico -----------");
		vet.mostrarMascotas();
		if (vet.getContador() == 0) {
			return;
		}
		vet.darDiagnostico(leerTexto("\nNombre de la mascota:\t"));
	}

	/* *********************** Captura ************************/
	/*
	 * Upcasting: regresa un Mascota, pero el objeto real es Perro, Gato o Ave.
	 */
	private static Mascota crearMascota(int tipo) {
		if (tipo < 1 || tipo > 4) {
			System.out.println("Error: tipo de mascota no valido.");
			return null;
		}
		String nombre = leerTexto("Nombre:\t");
		int edad = leerEntero("Edad:\t");
		String raza = leerTexto("Raza:\t");

		switch (tipo) {
			case 1:
				return new Perro(nombre, edad, raza, leerSiNo("Tiene vacuna antirrabica? (s/n):\t"));
			case 2:
				return new Gato(nombre, edad, raza, leerSiNo("Es de interior? (s/n):\t"));
			case 3:
				return new Ave(nombre, edad, raza, leerSiNo("Puede volar? (s/n):\t"));
			default:
				return new Mascota(nombre, edad, raza);
		}
	}

	private static int leerEntero(String msj) {
		System.out.print(msj);
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

	private static String leerTexto(String msj) {
		System.out.print(msj);
		return input.nextLine().trim();
	}

	private static boolean leerSiNo(String msj) {
		return leerTexto(msj).equalsIgnoreCase("s");
	}

	/* *********************** Prueba automatica ************************/
	private static void test() {
		System.out.println("\n========== TEST: herencia y polimorfismo ==========");
		Veterinaria vetPrueba = new Veterinaria("Zaide Quintero", "CED-99999");
		System.out.println(vetPrueba.getDoctor());

		System.out.println("\n---------- 1) Registrar mascotas de distintos tipos ----------");
		Mascota m1 = new Perro("Aruma", 3, "Labrador", true);
		Mascota m2 = new Perro("Peneko", 10, "Shih tzu", true);
		Mascota m3 = new Gato("Joy", 1, "Siames", false);
		Mascota m4 = new Ave("Kiwi", 2, "Periquito", false);
		Mascota m5 = new Mascota("Caguama", 5, "Conejo enano"); /* Mascota ya no es abstracta */
		System.out.println("Perro valido (debe aceptar): " + vetPrueba.registrarMascota(m1));
		System.out.println("Perro valido (debe aceptar): " + vetPrueba.registrarMascota(m2));
		System.out.println("Gato valido (debe aceptar): " + vetPrueba.registrarMascota(m3));
		System.out.println("Ave valida (debe aceptar): " + vetPrueba.registrarMascota(m4));
		System.out.println("Mascota generica (debe aceptar): " + vetPrueba.registrarMascota(m5));

		System.out.println("\n---------- 2) Registros invalidos ----------");
		System.out.println("Null (debe rechazar): " + vetPrueba.registrarMascota(null));
		System.out.println("Nombre vacio (debe rechazar): " + vetPrueba.registrarMascota(new Gato("", 1, "X", true)));
		System.out.println("Edad negativa (debe rechazar): " + vetPrueba.registrarMascota(new Ave("Pio", -1, "X", true)));
		System.out.println("Nombre repetido (debe rechazar): " + vetPrueba.registrarMascota(new Gato("aruma", 1, "X", true)));

		System.out.println("\n---------- 3) Mascotas registradas (toString polimorfico) ----------");
		vetPrueba.mostrarMascotas();

		System.out.println("\n---------- 4) Dynamic dispatching: mismo codigo, distinto metodo ----------");
		Mascota[] pacientes = { m1, m3, m4, m5 };
		for (Mascota m : pacientes) {
			/* La variable es Mascota, pero se ejecuta el examinar() del objeto real */
			System.out.printf("%-7s -> %s%n", m.getTipo(), m.examinar());
		}

		System.out.println("\n---------- 5) Dar diagnostico (el doctor no sabe que animal es) ----------");
		System.out.println("Perro joven: " + vetPrueba.darDiagnostico("Aruma"));
		System.out.println("Perro senior: " + vetPrueba.darDiagnostico("PENEKO"));
		System.out.println("Gato exterior: " + vetPrueba.darDiagnostico("Joy"));
		System.out.println("Ave que no vuela: " + vetPrueba.darDiagnostico("Kiwi"));
		System.out.println("Mascota generica: " + vetPrueba.darDiagnostico("Caguama"));
		System.out.println("Inexistente (debe rechazar): " + vetPrueba.darDiagnostico("Firulais"));
		System.out.println("Null (debe rechazar): " + vetPrueba.darDiagnostico(null));

		System.out.println("\n---------- 6) Cambiar atributo propio y volver a diagnosticar ----------");
		((Perro) m1).setVacunaRabia(false); /* Downcasting para usar un metodo propio de Perro */
		vetPrueba.darDiagnostico("Aruma");

		System.out.println("\n---------- 7) instanceof: el objeto real sigue siendo la hija ----------");
		System.out.println("m1 instanceof Perro (true): " + (m1 instanceof Perro));
		System.out.println("m1 instanceof Mascota (true): " + (m1 instanceof Mascota));
		System.out.println("m5 instanceof Perro (false): " + (m5 instanceof Perro));

		System.out.println("\n---------- 8) Limite de " + Veterinaria.MAX_MASCOTAS + " mascotas ----------");
		int aceptadas = 0;
		for (int i = vetPrueba.getContador(); i < Veterinaria.MAX_MASCOTAS; i++) {
			if (vetPrueba.registrarMascota(new Gato("Gato" + i, 1, "Criollo", true))) {
				aceptadas++;
			}
		}
		System.out.println("Relleno aceptado: " + aceptadas + " | Total: " + vetPrueba.getContador());
		System.out.println("Mascota 51 (debe rechazar): " + vetPrueba.registrarMascota(new Ave("Extra", 1, "X", true)));

		System.out.println("\n---------- 9) Doctor con mascota null ----------");
		vetPrueba.getDoctor().darConsulta(null);
		vetPrueba.getDoctor().diagnosticar(null);

		System.out.println("\n========== FIN DEL TEST ==========");
	}
}
