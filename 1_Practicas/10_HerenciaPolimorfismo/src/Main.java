import java.util.InputMismatchException; /* Para el try catch */
import java.util.Scanner;

/* **************************************************
 * Ejercicio 1 (fechas desde UNIX timestamp) y Ejercicio 2
 * (exportar registros) con herencia, polimorfismo y dynamic dispatch.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Main {
	private static Scanner input = new Scanner(System.in);

	/* Timestamps que pide don omar */
	private static final long[] TIMESTAMPS = { 1800000000L, 10000000000L, 1791169430L };

	/* Registros del enunciado */
	private static Registro[] registros = {
			new Registro("110702", "Yuls", "Mi corazon"),
			new Registro("060702", "Zaide", "Contabilidad"),
			new Registro("020604", "Andrea", "Administracion")
	};

	public static void main(String[] args) {
		int opcion;
		do {
			System.out.println("==================================================");
			System.out.println("        LAB 10: HERENCIA Y POLIMORFISMO           ");
			System.out.println("==================================================");
			System.out.println("1 -\tEjercicio 1: fechas de los timestamps del enunciado");
			System.out.println("2 -\tEjercicio 1: convertir un timestamp propio");
			System.out.println("3 -\tEjercicio 2: exportar registros");
			System.out.println("4 -\tSalir");

			opcion = leerEntero("\nOpcion:\t");
			switch (opcion) {
				case 1:
					for (long ts : TIMESTAMPS) {
						imprimirFechas(ts);
					}
					break;
				case 2:
					imprimirFechas(leerLong("Timestamp:\t"));
					break;
				case 3:
					exportarRegistros(registros);
					break;
				case 4:
					System.out.println("Saliendo del programa...");
					break;
				case 5: /* Oculto */
					test();
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo.");
			}
		} while (opcion != 4);
	}

	/* *********************** Ejercicio 1 ************************/
	private static void imprimirFechas(long timestamp) {
		/* Upcasting: todas se guardan como Fecha */
		Fecha[] formatos = {
				new FechaAmericana(timestamp),
				new FechaLatina(timestamp),
				new FechaInternacional(timestamp)
		};
		System.out.println("\n---------- Timestamp " + timestamp + " ----------");
		for (Fecha f : formatos) {
			System.out.println(f); /* toString() llama al formatear() de la hija */
		}
	}

	/* *********************** Ejercicio 2 ************************/
	private static void exportarRegistros(Registro[] datos) {
		Exportador[] exportadores = { new ExportadorJSON(), new ExportadorMarkdown(), new ExportadorCSV() };
		for (Exportador e : exportadores) {
			System.out.println("\n---------- " + e.getTipo() + " ----------");
			System.out.println(e.exportar(datos)); /* dynamic dispatch */
		}
	}

	/* ************************* captura ************************* */
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

	private static long leerLong(String msj) {
		System.out.print(msj);
		while (true) {
			try {
				long valor = input.nextLong();
				input.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.print("Valor invalido:\t");
				input.nextLine();
			}
		}
	}

	/* *********************** Prueba automatica ************************/
	private static void test() {
		System.out.println("\n========== TEST: herencia y polimorfismo ==========");

		System.out.println("\n---------- 1) Fechas conocidas (esperado vs obtenido) ----------");
		probarFecha(0L, "1970-01-01");
		probarFecha(86399L, "1970-01-01"); /* ultimo segundo del dia */
		probarFecha(951782400L, "2000-02-29"); /* anio bisiesto */
		probarFecha(1800000000L, "2027-01-15");
		probarFecha(10000000000L, "2286-11-20");
		probarFecha(1791169430L, "2026-10-05");
		probarFecha(-86400L, "1969-12-31"); /* negativo: por eso Math.floorDiv */

		System.out.println("\n---------- 2) Dynamic dispatch con Fecha ----------");
		Fecha f = new Fecha(1791169430L); /* Fecha no es abstracta aun no llegamos a esa clase */
		System.out.println(f);
		f = new FechaAmericana(1791169430L);
		System.out.println(f + "   <- misma variable, otro metodo");
		f = new FechaLatina(1791169430L);
		System.out.println(f);
		f = new FechaInternacional(1791169430L);
		System.out.println(f);
		System.out.println("f instanceof Fecha (true): " + (f instanceof Fecha));
		System.out.println("f instanceof FechaLatina (false): " + (f instanceof FechaLatina));

		System.out.println("\n---------- 3) Exportar con todos los formatos ----------");
		Exportador[] exps = { new Exportador(), new ExportadorJSON(), new ExportadorMarkdown(), new ExportadorCSV() };
		for (Exportador e : exps) {
			System.out.println("\n[" + e.getTipo() + "]");
			System.out.println(e.exportar(registros));
		}

		System.out.println("\n---------- 4) Casos limite del exportador ----------");
		Registro[] uno = { new Registro(" 7 ", "joe", "sistemas") };
		System.out.println("JSON con un solo registro (sin coma final):");
		System.out.println(new ExportadorJSON().exportar(uno));
		System.out.println("JSON vacio:");
		System.out.println(new ExportadorJSON().exportar(new Registro[0]));
		System.out.println("Base con null (debe dar error):");
		System.out.println(new Exportador().exportar(null));
		System.out.println("CSV con null (solo encabezado):");
		System.out.println(new ExportadorCSV().exportar(null));

		System.out.println("========== FIN DEL TEST ==========");
	}

	private static void probarFecha(long ts, String esperado) {
		String obtenido = new Fecha(ts).formatear();
		System.out.printf("%12d -> %s | esperado %s | %s%n", ts, obtenido, esperado,
				obtenido.equals(esperado) ? "OK" : "FALLA");
	}
}
