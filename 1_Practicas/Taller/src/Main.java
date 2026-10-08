import java.util.InputMismatchException; /* Para el try catch */
import java.util.Scanner;

/* **************************************************
 * Microbios: 4 microbios contra una persona que viajan
 * por sus organos (se aplico: herencia, sobrescritura y polimorfismo).
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Main {
	private static Scanner input = new Scanner(System.in);
	private static Simulacion simulacion;

	public static void main(String[] args) {
		int opcion;
		do {
			System.out.println("==================================================");
			System.out.println("            MICROBIOS: BATALLA EPICA              ");
			System.out.println("==================================================");
			System.out.println("Microbios: " + Simulacion.MAX_MICROBIOS + " (energia " + Microbio.ENERGIA_INICIAL
					+ ") vs Persona (energia " + Persona.ENERGIA_INICIAL + ")");
			System.out.println("\n1 -\tIniciar simulacion");
			System.out.println("0 -\tSalir");

			opcion = leerEntero("\nOpcion:\t");
			switch (opcion) {
				case 1:
					iniciarSimulacion();
					break;
				case 2:
					System.out.println("Saliendo del programa...");
					break;
				case 3: /* Oculto */
					test();
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo.");
			}
		} while (opcion != 0);
	}

	/* *********************** Menu ************************/
	private static void iniciarSimulacion() {
		System.out.println("\n----------- Nueva simulacion -----------");
		String nombre = leerTexto("Nombre del alien:\t");
		if (nombre.isEmpty()) {
			System.out.println("Error: el nombre es obligatorio.");
			return;
		}

		Persona persona = new Persona(nombre); /* Crea sus 6 organos */
		System.out.println("\nOrganos de " + persona.getNombre() + ":");
		for (int i = 0; i < persona.getContador(); i++) {
			System.out.println("  " + persona.getOrgano(i));
		}

		simulacion = new Simulacion(persona);
		for (int i = 1; i <= Simulacion.MAX_MICROBIOS; i++) {
			simulacion.agregarMicrobio(new Microbio(i));
		}

		leerTexto("\nPresiona Enter para comenzar la batalla...");
		simulacion.iniciar();
		leerTexto("\nPresiona Enter para volver al menu...");
	}

	/* ************************* Captura ************************* */
	private static int leerEntero(String msj) {
		System.out.print(msj);
		while (true) {
			try {
				int valor = input.nextInt();
				input.nextLine(); /* Importante para limpiar el buffer */
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

	/* *********************** Prueba automatica ************************/
	private static void test() {
		System.out.println("\n========== TEST: herencia, sobrescritura y polimorfismo ==========");

		System.out.println("\n---------- 1) Persona crea sus organos (composicion) ----------");
		Persona p = new Persona("Yuls"); /* Ella no tiene corazon :( */
		System.out.println(p);
		System.out.println("Organos creados (debe ser 6): " + p.getContador());
		System.out.println("Septimo organo (debe rechazar): " + p.agregarOrgano(new Corazon())); /*
																									 * Intentar ponerle
																									 * otro por que el
																									 * que tiene esta
																									 * muy dañado
																									 */
		System.out.println("Organo null (debe rechazar): " + p.agregarOrgano(null));
		System.out.println("Indice 10 (debe ser null): " + p.getOrgano(10));

		System.out.println("\n---------- 2) Dynamic dispatching: toString/getDescripcion ----------");
		/* Pruevo que se puedan utilizar metodos de organos en sus clases hijas. */
		for (int i = 0; i < p.getContador(); i++) {
			System.out.println("  " + p.getOrgano(i)); /* Variable Organo, metodo del objeto real */
		}
		Organo generico = new Organo("Generico");
		System.out.println("  " + generico + "   <- Como organo no es abstracta si se puede instanciar");

		System.out.println("\n---------- 3) instanceof y downcasting ----------");
		Organo o = p.getOrgano(5);
		/* Con instanceof pregunto si esa (o) instancia proviene de la clase (organo) */
		System.out.println("Intestino instanceof Organo (true): " + (o instanceof Organo));
		System.out.println("Intestino instanceof Cerebro (false): " + (o instanceof Cerebro));
		/*
		 * Reviso que o sea instancia de intestino para hacer un dowcastin que consiste
		 * en hacer un casteo al tipo de objeto que es y asi poder ejecutar metodos
		 * especifico de la clase
		 */
		if (o instanceof Intestino) {
			System.out.println(((Intestino) o).SoloExistir()); /* Metodo propio de la hija */
		}

		System.out.println("\n---------- 4) Un microbio visita cada organo (valores esperados) ----------");
		Microbio m = new Microbio(1);
		System.out.println(m); /* Ventajas de sobre escribir toString */
		System.out.println("\n[Intestino] persona 2048-40 = 2008, microbio igual (256)");
		m.saltar(p.getOrgano(5), p); /* Lo bueno que esto ejecuta toda logica de atacar y saltar */
		System.out.println("\n[Corazon] persona 2008-40 = 1968, defensa 30 por 3 saltos");
		m.saltar(p.getOrgano(1), p);
		System.out.println("\n[Intestino] con defensa: 40-30 = 10 -> 1958, quedan 2 saltos");
		m.saltar(p.getOrgano(5), p);
		System.out.println("\n[Corazon otra vez] 10 de danio -> 1948, ya existe: solo refresca 1+1 = 2 saltos");
		m.saltar(p.getOrgano(1), p);
		System.out.println("Defensa (30): " + p.getDefensa() + " | Saltos (2): " + p.getTurnosDefensa());
		System.out.println("\n[Pancreas] microbio 256 - 50% = 128");
		m.saltar(p.getOrgano(3), p);
		System.out.println("\n[Estomago] microbio 128 + 16 = 144");
		m.saltar(p.getOrgano(2), p);
		System.out.println("\n[Rinones] microbio 144 - 15% (21) = 123");
		m.saltar(p.getOrgano(4), p);
		System.out.println("\n[Cerebro] microbio defensa -30 por 3 saltos, persona +32");
		m.saltar(p.getOrgano(0), p);
		System.out.println("\n[Pancreas debilitado] 123*50% = 61 + 30 extra = 91 -> 32");
		m.saltar(p.getOrgano(3), p);
		System.out.println(p);
		System.out.println(m);

		System.out.println("\n---------- 5) Limites de energia ----------");
		Persona llena = new Persona("Yarely"); /* Otra que no tiene corazon */
		llena.restaurarEnergia(500);
		System.out.println("Persona no pasa de 2048: " + llena.getEnergia());
		Microbio lleno = new Microbio(9);
		lleno.restaurarEnergia(100);
		System.out.println("Microbio no pasa de 256: " + lleno.getEnergia());
		llena.recibirAtaque(-50);
		System.out.println("Danio negativo no cura (2048): " + llena.getEnergia());

		System.out.println("\n---------- 6) Microbio con 1 de energia muere en Rinones ----------");
		Microbio debil = new Microbio(7);
		debil.recibirDanio(255);
		debil.saltar(p.getOrgano(4), p);
		System.out.println("Esta vivo (false): " + debil.estaVivo());
		debil.saltar(p.getOrgano(0), p); /* Debe rechazar poque lo mandaron con san pedro */

		System.out.println("\n---------- 7) Validaciones de la simulacion ----------");
		Simulacion sinPersona = new Simulacion(null);
		sinPersona.iniciar();
		Simulacion sp = new Simulacion(new Persona("Zaide")); /* Otra loquita */
		sp.iniciar(); /* Sin microbios pa que juego */
		System.out.println("Microbio null (debe rechazar): " + sp.agregarMicrobio(null));
		for (int i = 1; i <= Simulacion.MAX_MICROBIOS; i++) {
			System.out.println("Microbio #" + i + " (debe aceptar): " + sp.agregarMicrobio(new Microbio(i)));
		}
		System.out.println("Quinto microbio (debe rechazar): " + sp.agregarMicrobio(new Microbio(5)));
		Simulacion sr = new Simulacion(new Persona("Juliett")); /* ufff Yuls pero con en cute */
		sr.agregarMicrobio(new Microbio(1));
		System.out.println("Id repetido (debe rechazar): " + sr.agregarMicrobio(new Microbio(1)));
		System.out.println("Saltar a organo null:");
		m.saltar(null, p);

		System.out.println("\n---------- 8) Simulacion completa automatica ----------");
		sp.iniciar();
		System.out.println("Turnos jugados: " + sp.getTurno());

		System.out.println("\n========== FIN DEL TEST ==========");
	}
}
