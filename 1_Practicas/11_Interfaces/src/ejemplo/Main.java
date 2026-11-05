package ejemplo;

/* ****************************************************************
 * Laboratorio 11: Clase Main
 * Menu para (1) ocultar datos sensibles con IOcultar (Email, Tarjeta,
 * Password) y (2) validar cadenas en una linea de ensamble de reglas
 * que implementan IValidar.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static final int MAX_DATOS = 10;

	private static Scanner input = new Scanner(System.in);

	// Datos capturados: arreglo de tipo interfaz (polimorfismo)
	private static IOcultar[] datos = new IOcultar[MAX_DATOS];
	private static int contador = 0;

	// Linea de ensamble con las 4 reglas
	private static ReglaLongitudMinima reglaLongitud = new ReglaLongitudMinima(3);
	private static LineaEnsamble linea = crearLinea(reglaLongitud);

	public static void main(String[] args) {
		int opcion;

		do {
			System.out.println("\n========== Laboratorio 11: Interfaces ==========");
			System.out.println("--- Ocultar datos ---");
			System.out.println("1. Registrar email");
			System.out.println("2. Registrar tarjeta");
			System.out.println("3. Registrar password");
			System.out.println("4. Ver datos ocultos");
			System.out.println("--- Linea de ensamble ---");
			System.out.println("5. Validar una cadena");
			System.out.println("6. Cambiar longitud minima (actual: " + reglaLongitud.getMinimo() + ")");
			System.out.println("--- ---");
			System.out.println("7. Cargar datos de prueba");
			System.out.println("8. Salir");
			opcion = leerEntero("Opcion: ");

			switch (opcion) {
				case 1:
					guardar(new Email(leerEmail("Correo: ")));
					break;
				case 2:
					guardar(new Tarjeta(leerTarjeta("Numero de tarjeta: ")));
					break;
				case 3:
					guardar(new Password(leerPassword("Password: ")));
					break;
				case 4:
					mostrarDatos();
					break;
				case 5:
					System.out.print("Cadena a validar (puede ir vacia): ");
					linea.reporte(input.nextLine());
					break;
				case 6:
					reglaLongitud.setMinimo(leerEnteroRango("Nueva longitud minima: ", 1, 100));
					break;
				case 7:
					cargarDatosPrueba();
					break;
				case 8:
					System.out.println("Saliendo...");
					break;
				case 9:
					test();
					break;
				default:
					System.out.println("Error: opcion invalida.");
			}
		} while (opcion != 8);
	}

	/* *********************** Linea de ensamble ************************/
	private static LineaEnsamble crearLinea(ReglaLongitudMinima longitud) {
		LineaEnsamble l = new LineaEnsamble();
		l.agregarRegla(new ReglaNoNulo());
		l.agregarRegla(new ReglaNoVacio());
		l.agregarRegla(longitud);
		l.agregarRegla(new ReglaEsNumero());
		return l;
	}

	/* *********************** Datos ocultos ************************/
	private static boolean guardar(IOcultar dato) {
		if (contador >= MAX_DATOS) {
			System.out.println("Error: ya hay " + MAX_DATOS + " datos registrados.");
			return false;
		}
		datos[contador++] = dato;
		System.out.println("Guardado -> " + dato.ocultar());
		return true;
	}

	// A Main no le importa si es Email, Tarjeta o Password: solo ve IOcultar
	private static void mostrarDatos() {
		if (contador == 0) {
			System.out.println("No hay datos registrados.");
			return;
		}
		for (int i = 0; i < contador; i++) {
			System.out.printf("%2d. %-9s %s%n", i + 1,
					datos[i].getClass().getSimpleName(), datos[i].ocultar());
		}
	}

	private static void cargarDatosPrueba() {
		guardar(new Email("omar.zamarron@uabc.edu.mx"));
		guardar(new Tarjeta("1234-5678-321-6543"));
		guardar(new Password("12341234"));
		guardar(new Email("joshua.osorio@uabc.edu.mx"));
	}

	/* *********************** Test (opcion oculta 9) ************************/
	private static int pasaron, total;

	private static void verificar(String descripcion, boolean resultado) {
		total++;
		if (resultado) {
			pasaron++;
		}
		System.out.println((resultado ? "  [OK]    " : "  [FALLA] ") + descripcion);
	}

	private static void test() {
		pasaron = 0;
		total = 0;
		System.out.println("\n================ TEST ================");

		System.out.println("\n1) Email");
		Email e = new Email("omar.zamarron@uabc.edu.mx");
		verificar("omar.zamarron@uabc.edu.mx -> *************@uabc.edu.mx",
				e.ocultar().equals("*************@uabc.edu.mx"));
		verificar("un * por cada caracter del usuario",
				new Email("a@b.com").ocultar().equals("*@b.com"));
		verificar("sin @ es invalido (se espera Error)", !e.setCorreo("omar.uabc.edu.mx"));
		verificar("dos @ es invalido (se espera Error)", !e.setCorreo("a@b@c.com"));
		verificar("usuario vacio es invalido (se espera Error)", !e.setCorreo("@uabc.edu.mx"));
		verificar("dominio sin punto es invalido (se espera Error)", !e.setCorreo("omar@uabc"));
		verificar("null es invalido (se espera Error)", !e.setCorreo(null));
		verificar("si el set falla se conserva el correo anterior",
				e.getCorreo().equals("omar.zamarron@uabc.edu.mx"));

		System.out.println("\n2) Tarjeta");
		Tarjeta t = new Tarjeta("1234-5678-321-6543");
		verificar("1234-5678-321-6543 -> ****-****-****-6543", t.ocultar().equals("****-****-****-6543"));
		verificar("se guarda sin guiones", t.getNumero().equals("123456783216543"));
		verificar("con espacios tambien funciona",
				new Tarjeta("4152 3138 0000 1111").ocultar().equals("****-****-****-1111"));
		verificar("letras es invalido (se espera Error)", !t.setNumero("1234-ABCD-5678-9012"));
		verificar("muy corta es invalida (se espera Error)", !t.setNumero("1234-5678"));
		verificar("muy larga es invalida (se espera Error)", !t.setNumero("12345678901234567890"));
		verificar("si el set falla se conserva el numero anterior", t.ocultar().endsWith("6543"));

		System.out.println("\n3) Password");
		Password p = new Password("12341234");
		verificar("12341234 -> ********", p.ocultar().equals("********"));
		verificar("misma longitud que la clave", new Password("abc").ocultar().equals("***"));
		verificar("clave vacia es invalida (se espera Error)", !p.setClave(""));
		verificar("clave con espacios es invalida (se espera Error)", !p.setClave("hola mundo"));
		verificar("la clave real no se modifica al ocultar", p.getClave().equals("12341234"));

		System.out.println("\n4) Polimorfismo con IOcultar");
		IOcultar[] arr = { new Email("omar.zamarron@uabc.edu.mx"), new Tarjeta("1234-5678-321-6543"),
				new Password("12341234") };
		String[] esperado = { "*************@uabc.edu.mx", "****-****-****-6543", "********" };
		boolean todos = true;
		for (int i = 0; i < arr.length; i++) {
			System.out.println("  " + arr[i].getClass().getSimpleName() + " -> " + arr[i].ocultar());
			todos &= arr[i].ocultar().equals(esperado[i]);
		}
		verificar("el mismo metodo ocultar() hace algo distinto en cada clase", todos);

		System.out.println("\n5) Reglas por separado");
		IValidar noNulo = new ReglaNoNulo();
		IValidar noVacio = new ReglaNoVacio();
		ReglaLongitudMinima longitud = new ReglaLongitudMinima(5);
		IValidar esNumero = new ReglaEsNumero();

		verificar("NoNulo: \"hola\" pasa", noNulo.validar("hola"));
		verificar("NoNulo: \"\" pasa (vacio no es null)", noNulo.validar(""));
		verificar("NoNulo: null no pasa", !noNulo.validar(null));

		verificar("NoVacio: \"hola\" pasa", noVacio.validar("hola"));
		verificar("NoVacio: \"\" no pasa", !noVacio.validar(""));
		verificar("NoVacio: \"   \" no pasa (solo espacios)", !noVacio.validar("   "));
		verificar("NoVacio: null no pasa (sin NullPointerException)", !noVacio.validar(null));

		verificar("LongitudMinima(5): \"hola\" no pasa", !longitud.validar("hola"));
		verificar("LongitudMinima(5): \"12345\" pasa (justo el limite)", longitud.validar("12345"));
		verificar("LongitudMinima(5): null no pasa", !longitud.validar(null));
		verificar("LongitudMinima: 0 es invalido (se espera Error)", !longitud.setMinimo(0));
		verificar("si el set falla se conserva el minimo anterior", longitud.getMinimo() == 5);

		verificar("EsNumero: \"42\" pasa", esNumero.validar("42"));
		verificar("EsNumero: \"-3.1416\" pasa", esNumero.validar("-3.1416"));
		verificar("EsNumero: \"1e3\" pasa (notacion cientifica)", esNumero.validar("1e3"));
		verificar("EsNumero: \"12abc\" no pasa", !esNumero.validar("12abc"));
		verificar("EsNumero: \"\" no pasa", !esNumero.validar(""));
		verificar("EsNumero: null no pasa", !esNumero.validar(null));

		System.out.println("\n6) Linea de ensamble (NoNulo, NoVacio, Longitud>=3, EsNumero)");
		LineaEnsamble l = crearLinea(new ReglaLongitudMinima(3));
		verificar("tiene 4 reglas", l.getContador() == 4);
		String[] cadenas = { null, "", "   ", "12", "hola", "3.1416", "2026" };
		boolean[] aceptadas = { false, false, false, false, false, true, true };
		for (int i = 0; i < cadenas.length; i++) {
			l.reporte(cadenas[i]);
			verificar("resultado esperado: " + (aceptadas[i] ? "ACEPTADA" : "RECHAZADA"),
					l.validar(cadenas[i]) == aceptadas[i]);
		}
		verificar("agregar regla null (se espera Error)", !l.agregarRegla(null));
		for (int i = l.getContador(); i < LineaEnsamble.MAX_REGLAS; i++) {
			l.agregarRegla(new ReglaNoNulo());
		}
		verificar("regla 11 rebasa el maximo (se espera Error)", !l.agregarRegla(new ReglaNoVacio()));
		verificar("getRegla fuera de rango (se espera Error)", l.getRegla(20) == null);

		System.out.println("\n========== " + pasaron + "/" + total + " pruebas pasaron ==========");
	}

	/* *********************** Captura segura ************************/
	private static int leerEntero(String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				int valor = input.nextInt();
				input.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("Error: escribe un numero entero.");
				input.nextLine();
			}
		}
	}

	private static int leerEnteroRango(String mensaje, int min, int max) {
		while (true) {
			int valor = leerEntero(mensaje);
			if (valor >= min && valor <= max) {
				return valor;
			}
			System.out.println("Error: debe estar entre " + min + " y " + max + ".");
		}
	}

	// Usa la misma regla de la linea de ensamble para no aceptar vacios
	private static String leerTexto(String mensaje) {
		IValidar noVacio = new ReglaNoVacio();
		while (true) {
			System.out.print(mensaje);
			String texto = input.nextLine().trim();
			if (noVacio.validar(texto)) {
				return texto;
			}
			System.out.println("Error: no puede ir vacio.");
		}
	}

	private static String leerEmail(String mensaje) {
		while (true) {
			String correo = leerTexto(mensaje);
			if (Email.esValido(correo)) {
				return correo;
			}
			System.out.println("Error: formato usuario@dominio.com");
		}
	}

	private static String leerTarjeta(String mensaje) {
		while (true) {
			String numero = leerTexto(mensaje);
			if (Tarjeta.esValido(numero)) {
				return numero;
			}
			System.out.println("Error: solo digitos (guiones/espacios opcionales), "
					+ Tarjeta.MIN_DIGITOS + " a " + Tarjeta.MAX_DIGITOS + " digitos.");
		}
	}

	private static String leerPassword(String mensaje) {
		while (true) {
			String clave = leerTexto(mensaje);
			if (Password.esValido(clave)) {
				return clave;
			}
			System.out.println("Error: la clave no puede tener espacios.");
		}
	}
}
