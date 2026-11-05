/* ****************************************************************
 * Taller 2: Clase Main
 * Punto de entrada del POS de la cadena de restaurantes: crea la cadena con
 * sus 3 restaurantes y une todo (TerminalPOS, Orden, Platillos) en un menu.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static Scanner input = new Scanner(System.in);
	private static CadenaRestaurantes cadena;
	private static TerminalPOS pos;

	public static void main(String[] args) {
		cadena = crearCadena("Antojitos Joe");
		int opcion;

		do {
			System.out.println("\n========== POS - " + cadena.getNombre() + " ==========");
			System.out.println("Restaurante actual: " + (pos == null ? "(ninguno)" : pos.getRestaurante().getNombre()));
			System.out.println("1. Elegir restaurante");
			System.out.println("2. Tomar orden");
			System.out.println("3. Ver ventas");
			System.out.println("4. Cargar datos de prueba");
			System.out.println("5. Salir");
			opcion = leerEntero("Opcion: ");

			switch (opcion) {
				case 1:
					elegirRestaurante();
					break;
				case 2:
					tomarOrden();
					break;
				case 3:
					verVentas();
					break;
				case 4:
					cargarDatosPrueba();
					break;
				case 5:
					System.out.println("Cerrando el POS...");
					break;
				case 6:
					test();
					break;
				default:
					System.out.println("Error: opcion invalida.");
			}
		} while (opcion != 5);
	}

	/* *********************** Configuracion ************************/
	// La cadena crea sus 3 restaurantes (composicion); se guardan como Restaurante (upcasting)
	private static CadenaRestaurantes crearCadena(String nombre) {
		CadenaRestaurantes c = new CadenaRestaurantes(nombre);
		c.agregarRestaurante(new Hamburgueseria("Burger Joe"));
		c.agregarRestaurante(new Torteria("Tortas La Tijuanense"));
		c.agregarRestaurante(new Taqueria("Tacos El Primo"));
		return c;
	}

	/* *********************** Menu ************************/
	private static void elegirRestaurante() {
		System.out.println("\n--- Restaurantes ---");
		System.out.print(cadena.listarRestaurantes());
		Restaurante r = cadena.getRestaurante(leerEntero("Restaurante: ") - 1);
		if (r != null) {
			pos = new TerminalPOS(r);
			System.out.println("POS listo en " + r.getNombre() + ".");
		}
	}

	private static void tomarOrden() {
		if (pos == null) {
			System.out.println("Error: primero elige un restaurante.");
			return;
		}
		if (!pos.nuevaOrden()) {
			return;
		}
		int opcion;
		do {
			Orden orden = pos.getOrdenActual();
			System.out.println("\n--- " + pos.getRestaurante().getNombre() + " | Orden #" + orden.getFolio()
					+ " | " + orden.getContador() + " platillo(s) ---");
			System.out.println("1. Agregar platillo");
			System.out.println("2. Editar toppings de un platillo");
			System.out.println("3. Eliminar platillo");
			System.out.println("4. Ver orden");
			System.out.println("5. Cobrar orden");
			System.out.println("6. Cancelar orden");
			opcion = leerEntero("Opcion: ");

			switch (opcion) {
				case 1:
					System.out.println(pos.getRestaurante().getMenu());
					Platillo nuevo = pos.seleccionarPlatillo(leerEntero("Platillo: "));
					if (nuevo != null) {
						editarToppings(nuevo);
					}
					break;
				case 2:
					System.out.println(orden.generarTicket());
					Platillo p = orden.getPlatillo(leerEntero("Numero de platillo: ") - 1);
					if (p != null) {
						editarToppings(p);
					}
					break;
				case 3:
					System.out.println(orden.generarTicket());
					if (orden.eliminarPlatillo(leerEntero("Numero de platillo: ") - 1)) {
						System.out.println("Platillo eliminado.");
					}
					break;
				case 4:
					System.out.println(orden.generarTicket());
					break;
				case 5:
					String ticket = pos.cerrarOrden();
					if (ticket != null) {
						System.out.println("Orden cobrada. Ticket:\n" + ticket);
					} else {
						opcion = 0; // no se pudo cobrar, sigue en la orden
					}
					break;
				case 6:
					pos.cancelarOrden();
					System.out.println("Orden cancelada.");
					break;
				default:
					System.out.println("Error: opcion invalida.");
			}
		} while (opcion != 5 && opcion != 6);
	}

	// La pantalla pregunta "agregas" o "quitas" segun la interfaz del platillo
	private static void editarToppings(Platillo p) {
		int num;
		do {
			System.out.println("\n" + p.getNombre() + ":");
			System.out.print(p.listarToppings());
			num = leerEntero(pos.opcionesDe(p) + " (0 = listo): ");
			if (num == 0) {
				break;
			}
			if (p instanceof IAgregable) {
				pos.agregarTopping(p, num);
			} else {
				pos.quitarTopping(p, num);
			}
		} while (true);
		System.out.println(p.preparar());
	}

	private static void verVentas() {
		System.out.println("\n--- Ventas de " + cadena.getNombre() + " ---");
		System.out.print(cadena.listarRestaurantes());
		System.out.printf("  TOTAL CADENA: $%.2f%n", cadena.calcularVentasTotales());
	}

	// Siembra una orden cobrada en cada restaurante de la cadena real
	private static void cargarDatosPrueba() {
		for (int i = 0; i < cadena.getContador(); i++) {
			TerminalPOS t = new TerminalPOS(cadena.getRestaurante(i));
			t.nuevaOrden();
			Platillo a = t.seleccionarPlatillo(1);
			Platillo b = t.seleccionarPlatillo(1);
			if (a instanceof IAgregable) {
				t.agregarTopping(a, 1);
				t.agregarTopping(a, 2);
				t.agregarTopping(b, 4);
			} else {
				t.quitarTopping(a, 3);
				t.quitarTopping(b, 1);
			}
			t.cerrarOrden();
		}
		System.out.println("Datos de prueba cargados (1 orden por restaurante).");
	}

	/* *********************** Test (opcion oculta 6) ************************/
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
		CadenaRestaurantes c = crearCadena("Cadena Test");

		System.out.println("\n1) Cadena: 3 restaurantes, no cabe un cuarto (se espera Error)");
		verificar("la cadena tiene 3 restaurantes", c.getContador() == 3);
		verificar("no acepta un 4to restaurante", !c.agregarRestaurante(new Taqueria("Extra")));
		verificar("indice invalido regresa null", c.getRestaurante(5) == null);

		System.out.println("\n2) Estado inicial de los platillos");
		Platillo h = new Hamburguesa();
		Platillo t = new Torta();
		Platillo tc = new Taco();
		verificar("hamburguesa inicia sin toppings", !h.tieneTopping(1) && !h.tieneTopping(4));
		verificar("torta inicia con todos", t.tieneTopping(1) && t.tieneTopping(4));
		verificar("taco inicia con todos", tc.tieneTopping(2) && tc.tieneTopping(3));
		verificar("hamburguesa es IAgregable y no IRemovible", h instanceof IAgregable && !(h instanceof IRemovible));
		verificar("torta y taco son IRemovible", t instanceof IRemovible && tc instanceof IRemovible);

		System.out.println("\n3) POS sin orden (se esperan Errores)");
		TerminalPOS p = new TerminalPOS(c.getRestaurante(0));
		verificar("no se selecciona platillo sin orden", p.seleccionarPlatillo(1) == null);
		verificar("no se cierra sin orden", p.cerrarOrden() == null);

		System.out.println("\n4) Hamburgueseria: agregar toppings");
		verificar("abre orden", p.nuevaOrden());
		verificar("no abre una segunda orden a la vez", !p.nuevaOrden());
		Platillo ham = p.seleccionarPlatillo(1);
		verificar("crea una Hamburguesa (upcasting)", ham instanceof Hamburguesa);
		verificar("opcion de menu invalida regresa null", p.seleccionarPlatillo(2) == null);
		verificar("agrega lechuga", p.agregarTopping(ham, 1) && ham.tieneTopping(1));
		verificar("no agrega lechuga dos veces", !p.agregarTopping(ham, 1));
		verificar("topping fuera de rango (5)", !p.agregarTopping(ham, 5));
		verificar("a la hamburguesa no se le quita (no es IRemovible)", !p.quitarTopping(ham, 1));
		verificar("el POS pregunta AGREGAS", p.opcionesDe(ham).contains("AGREGAS"));
		System.out.println("  " + ham.preparar());

		System.out.println("\n5) Orden: limite, eliminar y total");
		for (int i = 0; i < 9; i++) {
			p.seleccionarPlatillo(1);
		}
		Orden o = p.getOrdenActual();
		verificar("la orden tiene 10 platillos", o.getContador() == 10);
		verificar("no acepta el platillo 11", p.seleccionarPlatillo(1) == null);
		verificar("elimina el ultimo", o.eliminarPlatillo(9) && o.getContador() == 9);
		verificar("no elimina indice invalido", !o.eliminarPlatillo(20));
		verificar("total = 9 x 95", o.calcularTotal() == 9 * 95.0);
		String ticket = p.cerrarOrden();
		verificar("cobra la orden y regresa ticket", ticket != null && p.getOrdenActual() == null);
		System.out.println(ticket);

		System.out.println("\n6) Torteria y Taqueria: quitar toppings");
		TerminalPOS p2 = new TerminalPOS(c.getRestaurante(1));
		p2.nuevaOrden();
		Platillo tor = p2.seleccionarPlatillo(1);
		verificar("quita pepinillos a la torta", p2.quitarTopping(tor, 3) && !tor.tieneTopping(3));
		verificar("no quita pepinillos dos veces", !p2.quitarTopping(tor, 3));
		verificar("a la torta no se le agrega (no es IAgregable)", !p2.agregarTopping(tor, 3));
		verificar("el POS pregunta QUITAS", p2.opcionesDe(tor).contains("QUITAS"));
		System.out.println("  " + tor.preparar());
		verificar("cobra orden de torteria", p2.cerrarOrden() != null);

		TerminalPOS p3 = new TerminalPOS(c.getRestaurante(2));
		p3.nuevaOrden();
		Platillo tac = p3.seleccionarPlatillo(1);
		verificar("quita cilantro al taco", p3.quitarTopping(tac, 4) && !tac.tieneTopping(4));
		System.out.println("  " + tac.preparar());
		p3.seleccionarPlatillo(1);
		verificar("cobra 2 tacos", p3.cerrarOrden() != null);

		System.out.println("\n7) Orden vacia y ventas (se espera Error)");
		p3.nuevaOrden();
		verificar("no cobra una orden vacia", p3.cerrarOrden() == null);
		p3.cancelarOrden();
		verificar("cancelar libera el POS", p3.getOrdenActual() == null && p3.nuevaOrden());
		verificar("folios consecutivos por restaurante", p3.getOrdenActual().getFolio() == 3);
		p3.cancelarOrden();
		verificar("ventas hamburgueseria = 855", c.getRestaurante(0).calcularVentas() == 855.0);
		verificar("ventas totales = 855 + 80 + 50", c.calcularVentasTotales() == 985.0);
		System.out.print(c.listarRestaurantes());

		System.out.println("\n8) Restaurante base sin menu (se espera Error)");
		Restaurante base = new Restaurante("Generico");
		verificar("el restaurante base no crea platillos", base.crearPlatillo(1) == null);

		System.out.println("\n========== " + pasaron + "/" + total + " pruebas pasaron ==========");
	}

	/* *********************** Captura ************************/
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
}
