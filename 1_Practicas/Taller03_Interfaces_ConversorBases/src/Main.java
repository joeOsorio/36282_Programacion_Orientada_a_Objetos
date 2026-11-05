/* ****************************************************************
 * Taller 3: Clase Main
 * Menu para convertir numeros y textos a binario, octal y hexadecimal
 * usando las 3 interfaces (Numero y Texto las implementan).
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion;

		do {
			System.out.println("\n========== Taller 3: Interfaces ==========");
			System.out.println("1. Convertir un numero");
			System.out.println("2. Convertir un texto");
			System.out.println("3. Comparar numero y texto (polimorfismo)");
			System.out.println("4. Salir");
			opcion = leerEntero("Opcion: ");

			switch (opcion) {
				case 1:
					Numero n = new Numero(leerLong("Numero entero: "));
					System.out.println(n);
					break;
				case 2:
					System.out.print("Texto: ");
					Texto t = new Texto(input.nextLine());
					System.out.println(t);
					break;
				case 3:
					compararPolimorfismo();
					break;
				case 4:
					System.out.println("Saliendo...");
					break;
				case 5:
					test();
					break;
				default:
					System.out.println("Error: opcion invalida.");
			}
		} while (opcion != 4);
	}

	/* *********************** Polimorfismo ************************/
	// A Main no le importa si es Numero o Texto: solo ve la interfaz
	private static void compararPolimorfismo() {
		Numero n = new Numero(leerLong("Numero entero: "));
		System.out.print("Texto: ");
		Texto t = new Texto(input.nextLine());

		IMostrarBinario[] binarios = { n, t };
		IMostrarOctal[] octales = { n, t };
		IMostrarHexadecimal[] hexas = { n, t };

		for (int i = 0; i < binarios.length; i++) {
			System.out.println("\n" + binarios[i].getClass().getSimpleName() + ":");
			System.out.println("  BIN: " + binarios[i].mostrarBinario());
			System.out.println("  OCT: " + octales[i].mostrarOctal());
			System.out.println("  HEX: " + hexas[i].mostrarHexadecimal());
		}
	}

	/* *********************** Test (opcion oculta 5) ************************/
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

		System.out.println("\n1) Conversor (divisiones sucesivas)");
		verificar("0 -> \"0\"", Conversor.convertir(0, 2).equals("0"));
		verificar("10 en base 2 = 1010", Conversor.convertir(10, 2).equals("1010"));
		verificar("255 en base 16 = FF", Conversor.convertir(255, 16).equals("FF"));
		verificar("-8 en base 8 = -10", Conversor.convertir(-8, 8).equals("-10"));
		verificar("base invalida (1) regresa null (se espera Error)", Conversor.convertir(5, 1) == null);

		System.out.println("\n2) Numero (comparado contra la libreria de Java)");
		long[] valores = { 0, 1, 7, 42, 255, 1024, 2026, 123456789L };
		boolean coinciden = true;
		for (long v : valores) {
			Numero n = new Numero(v);
			coinciden &= n.mostrarBinario().equals(Long.toBinaryString(v))
					&& n.mostrarOctal().equals(Long.toOctalString(v))
					&& n.mostrarHexadecimal().equals(Long.toHexString(v).toUpperCase());
		}
		verificar("8 valores coinciden con Long.toBinary/Octal/HexString", coinciden);
		Numero neg = new Numero(-42);
		verificar("negativo -42 -> BIN -101010", neg.mostrarBinario().equals("-101010"));
		neg.setValor(42);
		verificar("setValor cambia lo que muestran las interfaces", neg.mostrarHexadecimal().equals("2A"));
		System.out.println(new Numero(2026));

		System.out.println("\n3) Texto (hash polinomial)");
		Texto vacio = new Texto("");
		verificar("texto vacio -> hash 0", vacio.calcularHash() == 0 && vacio.mostrarBinario().equals("0"));
		Texto a = new Texto("a");
		verificar("\"a\" -> hash 98 (ASCII 97 + 1)", a.calcularHash() == 98);
		verificar("\"a\" -> HEX 62", a.mostrarHexadecimal().equals("62"));
		Texto ab = new Texto("ab");
		verificar("\"ab\" -> 98 + 99*131 = 13067", ab.calcularHash() == 98 + 99L * 131);
		Texto t1 = new Texto("Hola mundo");
		Texto t2 = new Texto("Hola mundo");
		verificar("mismo texto -> mismo hash", t1.calcularHash() == t2.calcularHash());
		verificar("el hash queda dentro del modulo", t1.calcularHash() >= 0 && t1.calcularHash() < Texto.M);
		String largo = "";
		for (int i = 0; i < 500; i++) {
			largo += "z";
		}
		Texto tl = new Texto(largo);
		verificar("texto largo (500) no se desborda", tl.calcularHash() >= 0 && tl.calcularHash() < Texto.M);
		Texto nulo = new Texto(null);
		verificar("texto null se vuelve vacio (se espera Error)", nulo.getTexto().equals(""));

		System.out.println("\n4) Suma ASCII vs hash (anagramas)");
		Texto amor = new Texto("amor");
		Texto roma = new Texto("roma");
		verificar("\"amor\" y \"roma\" tienen la MISMA suma ASCII", amor.sumaAscii() == roma.sumaAscii());
		verificar("\"amor\" y \"roma\" tienen DIFERENTE hash", amor.calcularHash() != roma.calcularHash());
		System.out.println(amor);
		System.out.println(roma);

		System.out.println("\n5) Polimorfismo por interfaz");
		IMostrarBinario[] binarios = { new Numero(98), new Texto("a") };
		verificar("Numero(98) y Texto(\"a\") muestran el mismo binario",
				binarios[0].mostrarBinario().equals(binarios[1].mostrarBinario()));
		IMostrarHexadecimal h = new Numero(255);
		verificar("referencia de tipo interfaz llama al metodo de Numero", h.mostrarHexadecimal().equals("FF"));
		h = new Texto("a");
		verificar("la misma referencia ahora llama al de Texto", h.mostrarHexadecimal().equals("62"));

		System.out.println("\n========== " + pasaron + "/" + total + " pruebas pasaron ==========");
	}

	/* *********************** Captura ************************/
	private static int leerEntero(String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				int valor = input.nextInt();
				input.nextLine();``
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("Error: escribe un numero entero.");
				input.nextLine();
			}
		}
	}

	private static long leerLong(String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				long valor = input.nextLong();
				input.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("Error: escribe un numero entero.");
				input.nextLine();
			}
		}
	}
}
