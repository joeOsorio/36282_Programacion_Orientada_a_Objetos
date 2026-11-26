/* ****************************************************************
 * Proyecto Final: Clase Consola
 * Utilidades para que la terminal se vea bonita: colores ANSI,
 * titulos en caja, separadores y limpiar pantalla. Si la terminal
 * no soporta colores se corre con: java -Dcolor=false ...
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public final class Consola {
	public static final int ANCHO = 78;
	private static final boolean COLOR = !"false".equalsIgnoreCase(System.getProperty("color"));

	public static final String RESET = "\u001B[0m";
	public static final String NEGRITA = "\u001B[1m";
	public static final String TENUE = "\u001B[2m";
	public static final String ROJO = "\u001B[31m";
	public static final String VERDE = "\u001B[32m";
	public static final String AMARILLO = "\u001B[33m";
	public static final String AZUL = "\u001B[34m";
	public static final String MAGENTA = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";

	private Consola() {
	}

	/* *********************** Color ************************/
	public static String color(String codigo, String texto) {
		return COLOR ? codigo + texto + RESET : texto;
	}

	/* Color segun el estado del producto */
	public static String colorEstado(String estado) {
		switch (estado) {
			case "VENCIDO":
			case "AGOTADO":
				return ROJO;
			case "CADUCA":
			case "REPONER":
				return AMARILLO;
			default:
				return VERDE;
		}
	}

	/* *********************** Dibujo ************************/
	public static void limpiar() {
		if (COLOR) {
			System.out.print("\u001B[H\u001B[2J");
			System.out.flush();
		} else {
			System.out.println();
		}
	}

	public static void titulo(String texto) {
		String linea = "═".repeat(ANCHO - 2);
		int izq = (ANCHO - 2 - texto.length()) / 2;
		int der = ANCHO - 2 - texto.length() - izq;
		System.out.println(color(CYAN, "╔" + linea + "╗"));
		System.out.println(color(CYAN, "║") + color(NEGRITA, " ".repeat(Math.max(0, izq)) + texto
				+ " ".repeat(Math.max(0, der))) + color(CYAN, "║"));
		System.out.println(color(CYAN, "╚" + linea + "╝"));
	}

	public static void subtitulo(String texto) {
		System.out.println();
		System.out.println(color(NEGRITA + AZUL, "── " + texto + " ") + color(AZUL, "─".repeat(Math.max(0, ANCHO - texto.length() - 4))));
	}

	public static void separador() {
		System.out.println(color(TENUE, "─".repeat(ANCHO)));
	}

	public static void opcion(int numero, String texto) {
		System.out.println("   " + color(CYAN + NEGRITA, String.format("%2d", numero)) + "  " + texto);
	}

	/* *********************** Mensajes ************************/
	public static void ok(String texto) {
		System.out.println(color(VERDE, " ✔ " + texto));
	}

	public static void error(String texto) {
		System.out.println(color(ROJO, " ✘ " + texto));
	}

	public static void aviso(String texto) {
		System.out.println(color(AMARILLO, " ⚠ " + texto));
	}

	public static void info(String texto) {
		System.out.println(color(TENUE, "   " + texto));
	}
}
