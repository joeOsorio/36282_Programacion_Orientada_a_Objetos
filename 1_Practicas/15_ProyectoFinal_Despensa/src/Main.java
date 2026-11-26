import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import utils.Lista;

/* ****************************************************************
 * Proyecto Final: Clase Main
 * Despensa / Lista del super en terminal. Al iniciar carga
 * data/despensa.txt, arranca HiloCaducidad en segundo plano y
 * autoguarda despues de cada cambio. Opcion oculta 11 = test().
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class Main {
	static final String NOMBRE_DESPENSA = "Despensa de casa";
	static final int INTERVALO_HILO_SEG = 10;
	static final int DIAS_AVISO = 3;
	static final int OPCION_SALIR = 10;
	static final int OPCION_TEST = 11; /* oculta, no se imprime en el menu */

	static Scanner sc = new Scanner(System.in, "UTF-8");
	static File carpetaDatos = carpetaDatos();
	static File archivoDespensa = new File(carpetaDatos, "despensa.txt");
	static File archivoLista = new File(carpetaDatos, "lista_super.txt");

	static Despensa despensa;
	static ListaSuper lista = new ListaSuper();
	static HiloCaducidad hilo;

	public static void main(String[] args) {
		despensa = GestorArchivos.cargarDespensa(NOMBRE_DESPENSA, archivoDespensa);
		hilo = new HiloCaducidad(despensa, INTERVALO_HILO_SEG, DIAS_AVISO);
		hilo.iniciar();

		int opcion;
		do {
			Consola.limpiar();
			Consola.titulo("DESPENSA Y LISTA DEL SUPER");
			Consola.info(despensa.getTotal() + " producto(s) | archivo: " + archivoDespensa.getPath());
			mostrarAvisosNuevos();
			mostrarMenu();
			opcion = leerEntero("Elige una opcion: ", 1, OPCION_TEST);
			System.out.println();

			switch (opcion) {
				case 1: verDespensa(); break;
				case 2: agregarProducto(); break;
				case 3: consumirProducto(); break;
				case 4: reabastecerProducto(); break;
				case 5: eliminarProducto(); break;
				case 6: verCaducidad(); break;
				case 7: menuListaSuper(); break;
				case 8: verAvisos(); break;
				case 9: cargarDatosPrueba(); break;
				case OPCION_SALIR: salir(); break;
				case OPCION_TEST: test(); break;
			}
			if (opcion != OPCION_SALIR && opcion != 7)
				pausa();
		} while (opcion != OPCION_SALIR);
	}

	/* *********************** Menu ************************/
	static void mostrarMenu() {
		Consola.subtitulo("Menu");
		Consola.opcion(1, "Ver despensa");
		Consola.opcion(2, "Agregar producto");
		Consola.opcion(3, "Consumir producto");
		Consola.opcion(4, "Reabastecer producto");
		Consola.opcion(5, "Eliminar producto");
		Consola.opcion(6, "Caducidad (vencidos y por vencer)");
		Consola.opcion(7, "Lista del super");
		Consola.opcion(8, "Avisos del hilo");
		Consola.opcion(9, "Cargar datos de prueba");
		Consola.opcion(OPCION_SALIR, "Guardar y salir");
		Consola.separador();
	}

	/* Avisos que preparo HiloCaducidad desde la ultima vez */
	static void mostrarAvisosNuevos() {
		Lista<String> nuevos = hilo.tomarAvisosNuevos();
		if (nuevos.estaVacia())
			return;
		System.out.println();
		Consola.aviso(Consola.color(Consola.NEGRITA, "Avisos de caducidad:"));
		for (var n = nuevos.begin(); n != null; n = n.sig)
			Consola.aviso("  " + n.valor);
	}

	/* *********************** 1. Ver ************************/
	static void verDespensa() {
		Consola.subtitulo("Despensa (" + despensa.getTotal() + " productos)");
		imprimirTabla(despensa.getProductos(), true);
		if (despensa.getTotal() > 0) {
			int vencidos = despensa.vencidos().getSize();
			int reponer = despensa.porReponer().getSize();
			int caducan = despensa.porVencer(DIAS_AVISO).getSize();
			System.out.println();
			Consola.info("Resumen: " + Consola.color(Consola.ROJO, vencidos + " vencido(s)") + "  "
					+ Consola.color(Consola.AMARILLO, caducan + " por vencer") + "  "
					+ Consola.color(Consola.AMARILLO, reponer + " por reponer"));
		}
	}

	static void imprimirTabla(Lista<Producto> productos, boolean numerada) {
		if (productos.estaVacia()) {
			Consola.info("(vacio)");
			return;
		}
		System.out.println(Consola.color(Consola.NEGRITA, String.format(" %3s  %-13s %-18s %8s %-4s %-8s %s",
				"#", "TIPO", "NOMBRE", "CANT", "UN", "ESTADO", "DETALLE")));
		Consola.separador();
		int i = 1;
		for (var n = productos.begin(); n != null; n = n.sig, i++) {
			Producto p = n.valor;
			String estado = p.getEstado();
			System.out.println(String.format(" %3s  %-13s %-18s %8s %-4s ", numerada ? i : "",
					p.getTipo(), recortar(p.getNombre(), 18), Producto.formato(p.getCantidad()),
					p.getUnidad().getAbreviatura())
					+ Consola.color(Consola.colorEstado(estado), String.format("%-8s", estado)) + " "
					+ Consola.color(Consola.TENUE, p.getDetalle()));
		}
	}

	/* Muestra la tabla numerada y regresa el producto elegido (null = cancelar) */
	static Producto elegirProducto(String accion) {
		Lista<Producto> productos = despensa.getProductos();
		if (productos.estaVacia()) {
			Consola.error("La despensa esta vacia (usa la opcion 2 o la 9)");
			return null;
		}
		Consola.subtitulo(accion);
		imprimirTabla(productos, true);
		int i = leerEntero("Numero del producto (0 = cancelar): ", 0, productos.getSize());
		return (i == 0) ? null : productos.get(i - 1);
	}

	/* *********************** 2. Agregar ************************/
	static void agregarProducto() {
		Consola.subtitulo("Agregar producto");
		Consola.opcion(1, "Perecedero   (leche, carne, fruta...)");
		Consola.opcion(2, "No perecedero (arroz, latas, pasta...)");
		Consola.opcion(3, "Limpieza     (cloro, jabon...)");
		int tipo = leerEntero("Tipo (0 = cancelar): ", 0, 3);
		if (tipo == 0)
			return;

		String nombre = leerTexto("Nombre: ");
		if (despensa.buscar(nombre) != null) {
			Consola.error("Ya existe \"" + nombre + "\". Usa Reabastecer (opcion 4)");
			return;
		}
		Unidad unidad = leerUnidad();
		double cantidad = leerDouble("Cantidad que tienes (" + unidad.getAbreviatura() + "): ", 0);
		double minima = leerDouble("Cantidad minima antes de reponer (" + unidad.getAbreviatura() + "): ", 0);
		if (!Producto.datosValidos(nombre, cantidad, minima))
			return;

		Producto p; /* upcasting: cualquier subclase se guarda como Producto */
		if (tipo == 1)
			p = new Perecedero(nombre, cantidad, minima, unidad, leerFecha("Fecha de caducidad (dd/mm/aaaa o +dias): "));
		else if (tipo == 2)
			p = new NoPerecedero(nombre, cantidad, minima, unidad);
		else
			p = new Limpieza(nombre, cantidad, minima, unidad, leerSiNo("¿Es peligroso (cloro, acido...)? (s/n): "));

		if (despensa.agregar(p)) {
			Consola.ok("Se agrego " + p.getNombre() + " [" + p.getTipo() + "]");
			guardar();
		}
	}

	/* *********************** 3. Consumir ************************/
	static void consumirProducto() {
		Producto p = elegirProducto("Consumir producto");
		if (p == null)
			return;
		double cuanto = leerDouble("¿Cuanto usaste? (" + p.getUnidad().getAbreviatura() + "): ", 0);
		if (despensa.consumir(p.getNombre(), cuanto)) {
			Consola.ok("Quedan " + Producto.formato(p.getCantidad()) + " " + p.getUnidad().getAbreviatura() + " de " + p.getNombre());
			if (p.necesitaReponer())
				Consola.aviso("Ya toca reponer " + p.getNombre() + " (aparecera en la lista del super)");
			guardar();
		}
	}

	/* *********************** 4. Reabastecer ************************/
	static void reabastecerProducto() {
		Producto p = elegirProducto("Reabastecer producto");
		if (p == null)
			return;
		double cuanto = leerDouble("¿Cuanto compraste? (" + p.getUnidad().getAbreviatura() + "): ", 0);
		if (!despensa.reabastecer(p.getNombre(), cuanto))
			return;
		/* Solo el perecedero tiene fecha: se pregunta la del lote nuevo */
		if (p instanceof Perecedero && leerSiNo("¿Cambiar la fecha de caducidad? (s/n): "))
			((Perecedero) p).setFechaCaducidad(leerFecha("Nueva fecha (dd/mm/aaaa o +dias): "));
		Consola.ok("Ahora hay " + Producto.formato(p.getCantidad()) + " " + p.getUnidad().getAbreviatura() + " de " + p.getNombre());
		guardar();
	}

	/* *********************** 5. Eliminar ************************/
	static void eliminarProducto() {
		Producto p = elegirProducto("Eliminar producto");
		if (p == null)
			return;
		if (leerSiNo("¿Seguro que quieres eliminar " + p.getNombre() + "? (s/n): ") && despensa.eliminar(p.getNombre())) {
			Consola.ok("Se elimino " + p.getNombre());
			guardar();
		}
	}

	/* *********************** 6. Caducidad ************************/
	static void verCaducidad() {
		Consola.subtitulo("Vencidos");
		imprimirTabla(despensa.vencidos(), false);
		Consola.subtitulo("Vencen en los proximos " + DIAS_AVISO + " dias");
		imprimirTabla(despensa.porVencer(DIAS_AVISO), false);
	}

	/* *********************** 7. Lista del super ************************/
	static void menuListaSuper() {
		int op;
		do {
			Consola.limpiar();
			Consola.titulo("LISTA DEL SUPER");
			lista.generar(despensa); /* siempre al dia con la despensa */
			imprimirLista();
			Consola.subtitulo("Opciones");
			Consola.opcion(1, "Agregar algo extra");
			Consola.opcion(2, "Quitar un extra");
			Consola.opcion(3, "Exportar a " + archivoLista.getName());
			Consola.opcion(4, "Regresar");
			Consola.separador();
			op = leerEntero("Elige una opcion: ", 1, 4);
			switch (op) {
				case 1:
					if (lista.agregarExtra(leerTexto("¿Que quieres agregar?: ")))
						Consola.ok("Agregado");
					break;
				case 2:
					if (lista.getExtras().estaVacia())
						Consola.error("No hay extras");
					else if (lista.quitarExtra(leerEntero("Numero de extra: ", 1, lista.getExtras().getSize())))
						Consola.ok("Quitado");
					break;
				case 3:
					if (GestorArchivos.exportarLista(lista, archivoLista))
						Consola.ok("Lista exportada a " + archivoLista.getPath());
					break;
			}
			if (op != 4)
				pausa();
		} while (op != 4);
	}

	static void imprimirLista() {
		Consola.subtitulo("Hace falta (" + lista.getPendientes().getSize() + ")");
		if (lista.getPendientes().estaVacia())
			Consola.info("(la despensa esta completa)");
		Lista<String> renglones = lista.renglones();
		int i = 0, totalPend = lista.getPendientes().getSize();
		for (var n = renglones.begin(); n != null; n = n.sig, i++) {
			if (i == totalPend)
				Consola.subtitulo("Extras (" + lista.getExtras().getSize() + ")");
			String prefijo = (i < totalPend) ? "    " : String.format(" %2d ", i - totalPend + 1);
			System.out.println(prefijo + n.valor);
		}
	}

	/* *********************** 8. Avisos ************************/
	static void verAvisos() {
		Consola.subtitulo("HiloCaducidad");
		Consola.info("Estado: " + (hilo.estaActivo() ? Consola.color(Consola.VERDE, "corriendo") : Consola.color(Consola.ROJO, "detenido"))
				+ " | revisa cada " + hilo.getIntervaloSeg() + " s | avisa " + hilo.getDiasAviso()
				+ " dias antes | revisiones: " + hilo.getRevisiones());
		hilo.revisar(); /* revision al momento, por si acabas de hacer cambios */
		hilo.tomarAvisosNuevos();
		Lista<String> avisos = hilo.getAvisos();
		System.out.println();
		if (avisos.estaVacia())
			Consola.ok("Sin avisos: nada vencido ni por vencer");
		for (var n = avisos.begin(); n != null; n = n.sig)
			Consola.aviso(n.valor);
	}

	/* *********************** 9. Datos de prueba ************************/
	static void cargarDatosPrueba() {
		LocalDate hoy = LocalDate.now();
		Producto[] demo = {
				new Perecedero("Leche", 2, 1, Unidad.LITRO, hoy.plusDays(1)),
				new Perecedero("Huevo", 12, 6, Unidad.PIEZA, hoy.plusDays(10)),
				new Perecedero("Yogurt", 1, 2, Unidad.PIEZA, hoy.minusDays(2)),
				new Perecedero("Pan", 1, 1, Unidad.PAQUETE, hoy.plusDays(2)),
				new NoPerecedero("Arroz", 2, 1, Unidad.KG),
				new NoPerecedero("Frijol", 0.5, 1, Unidad.KG),
				new NoPerecedero("Atun", 3, 2, Unidad.PIEZA),
				new Limpieza("Cloro", 1, 1, Unidad.LITRO, true),
				new Limpieza("Jabon de trastes", 2, 1, Unidad.PIEZA, false) };
		int agregados = 0;
		for (Producto p : demo)
			if (despensa.buscar(p.getNombre()) == null && despensa.agregar(p))
				agregados++;
		Consola.ok("Se agregaron " + agregados + " producto(s) de prueba (los que ya existian se respetaron)");
		guardar();
		hilo.revisar();
	}

	/* *********************** 10. Salir ************************/
	static void salir() {
		hilo.detener();
		guardar();
		Consola.ok("Todo guardado en " + archivoDespensa.getPath() + ". ¡Hasta luego!");
	}

	static void guardar() {
		if (!GestorArchivos.guardarDespensa(despensa, archivoDespensa))
			Consola.error("No se pudo guardar el archivo");
	}

	/* *********************** Lectura segura ************************/
	static String leerTexto(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String s = sc.nextLine().trim();
			if (s.isEmpty())
				Consola.error("No puede estar vacio");
			else if (s.contains(IGuardable.SEPARADOR))
				Consola.error("No uses '" + IGuardable.SEPARADOR + "'");
			else
				return s;
		}
	}

	static int leerEntero(String mensaje, int min, int max) {
		while (true) {
			System.out.print(mensaje);
			try {
				int n = Integer.parseInt(sc.nextLine().trim());
				if (n >= min && n <= max)
					return n;
			} catch (NumberFormatException e) {
				/* se vuelve a pedir */
			}
			Consola.error("Escribe un numero entre " + min + " y " + max);
		}
	}

	static double leerDouble(String mensaje, double min) {
		while (true) {
			System.out.print(mensaje);
			try {
				double d = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
				if (d >= min)
					return d;
			} catch (NumberFormatException e) {
				/* se vuelve a pedir */
			}
			Consola.error("Escribe un numero mayor o igual a " + Producto.formato(min));
		}
	}

	static boolean leerSiNo(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String s = sc.nextLine().trim().toLowerCase();
			if (s.equals("s") || s.equals("si") || s.equals("sí"))
				return true;
			if (s.equals("n") || s.equals("no"))
				return false;
			Consola.error("Responde s o n");
		}
	}

	static Unidad leerUnidad() {
		Unidad[] u = Unidad.values();
		StringBuilder sb = new StringBuilder("Unidad: ");
		for (int i = 0; i < u.length; i++)
			sb.append(i + 1).append(") ").append(u[i].name().toLowerCase()).append("  ");
		Consola.info(sb.toString());
		return u[leerEntero("Unidad: ", 1, u.length) - 1];
	}

	/* Acepta 28/11/2026 o +3 (dentro de 3 dias) */
	static LocalDate leerFecha(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String s = sc.nextLine().trim();
			try {
				if (s.startsWith("+"))
					return LocalDate.now().plusDays(Integer.parseInt(s.substring(1)));
				return LocalDate.parse(s, Perecedero.FORMATO_FECHA);
			} catch (NumberFormatException | DateTimeParseException e) {
				Consola.error("Formato: dd/mm/aaaa (ej. 28/11/2026) o +dias (ej. +5)");
			}
		}
	}

	static void pausa() {
		System.out.println();
		System.out.print(Consola.color(Consola.TENUE, "Presiona Enter para continuar..."));
		sc.nextLine();
	}

	static String recortar(String s, int max) {
		return s.length() <= max ? s : s.substring(0, max - 1) + "…";
	}

	/* data/ junto a src/: funciona corriendo desde la carpeta del proyecto o con compilar.ps1 */
	static File carpetaDatos() {
		try {
			File clases = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			File proyecto = clases.getParentFile();
			if (proyecto != null && new File(proyecto, "src").isDirectory())
				return new File(proyecto, "data");
		} catch (Exception e) {
			/* si falla, se usa la carpeta actual */
		}
		return new File("data");
	}

	/* *********************** Test oculto (opcion 11) ************************/
	static int pasan, fallan;

	static void check(String nombre, boolean ok) {
		if (ok) {
			pasan++;
			System.out.println(Consola.color(Consola.VERDE, "  [OK]   ") + nombre);
		} else {
			fallan++;
			System.out.println(Consola.color(Consola.ROJO, "  [FALLA] ") + nombre);
		}
	}

	static void test() {
		pasan = 0;
		fallan = 0;
		LocalDate hoy = LocalDate.now();
		Consola.titulo("TEST (los 'Error:' que salen son esperados)");

		/* ---------- 1. Productos y validacion ---------- */
		Consola.subtitulo("1. Productos y validacion");
		check("datosValidos acepta datos correctos", Producto.datosValidos("Leche", 2, 1));
		check("datosValidos rechaza nombre vacio", !Producto.datosValidos("  ", 2, 1));
		check("datosValidos rechaza ';' en el nombre", !Producto.datosValidos("a;b", 2, 1));
		check("datosValidos rechaza cantidad negativa", !Producto.datosValidos("X", -1, 1));
		Producto leche = new Perecedero("Leche", 2, 1, Unidad.LITRO, hoy.minusDays(1));
		Producto yogurt = new Perecedero("Yogurt", 4, 1, Unidad.PIEZA, hoy.plusDays(2));
		Producto arroz = new NoPerecedero("Arroz", 3, 1, Unidad.KG);
		Producto cloro = new Limpieza("Cloro", 0, 1, Unidad.LITRO, true);
		Producto jabon = new Limpieza("Jabon", 1.2, 1, Unidad.PIEZA, false);
		check("formato(2.0) = \"2\" y formato(1.5) = \"1.5\"", Producto.formato(2).equals("2") && Producto.formato(1.5).equals("1.5"));

		/* ---------- 2. Polimorfismo ---------- */
		Consola.subtitulo("2. Polimorfismo (misma llamada, distinta respuesta)");
		check("Perecedero vencido: estaVencido() = true", leche.estaVencido());
		check("Perecedero vigente: estaVencido() = false", !yogurt.estaVencido());
		check("NoPerecedero nunca vence", !arroz.estaVencido());
		check("Limpieza nunca vence", !cloro.estaVencido());
		check("Leche vencida con 2 L: necesitaReponer() = true", leche.necesitaReponer());
		check("Arroz 3 kg (min 1): necesitaReponer() = false", !arroz.necesitaReponer());
		check("Cloro en 0: necesitaReponer() = true", cloro.necesitaReponer());
		check("Jabon 1.2 (min 1, margen 1.5): necesitaReponer() = true", jabon.necesitaReponer());
		check("Leche vencida: cantidadAComprar() = 2 (todo de nuevo)", leche.cantidadAComprar() == 2);
		check("Cloro: cantidadAComprar() = 2 (doble del minimo)", cloro.cantidadAComprar() == 2);
		check("Yogurt vence en 2 dias: estado CADUCA", yogurt.getEstado().equals("CADUCA"));
		check("Leche: estado VENCIDO, Cloro: AGOTADO, Arroz: OK",
				leche.getEstado().equals("VENCIDO") && cloro.getEstado().equals("AGOTADO") && arroz.getEstado().equals("OK"));
		Producto[] arreglo = { leche, arroz, cloro };
		String tipos = "";
		for (Producto p : arreglo)
			tipos += p.getTipo() + " ";
		check("getTipo() por dynamic dispatch", tipos.equals("PERECEDERO NOPERECEDERO LIMPIEZA "));

		/* ---------- 3. Despensa ---------- */
		Consola.subtitulo("3. Despensa (Lista<Producto>)");
		Despensa d = new Despensa("Test");
		check("agregar 5 productos de 3 tipos", d.agregar(leche) && d.agregar(yogurt) && d.agregar(arroz)
				&& d.agregar(cloro) && d.agregar(jabon) && d.getTotal() == 5);
		check("agregar duplicado (otro case) -> false", !d.agregar(new NoPerecedero("ARROZ", 1, 1, Unidad.KG)));
		check("agregar null -> false", !d.agregar(null));
		check("buscar ignora mayusculas", d.buscar("yOgUrT") == yogurt);
		check("buscar inexistente -> null", d.buscar("Caviar") == null);
		check("consumir 1 de Arroz -> quedan 2", d.consumir("Arroz", 1) && arroz.getCantidad() == 2);
		check("consumir mas de lo que hay -> false", !d.consumir("Arroz", 50) && arroz.getCantidad() == 2);
		check("consumir 0 o negativo -> false", !d.consumir("Arroz", 0) && !d.consumir("Arroz", -3));
		check("consumir inexistente -> false", !d.consumir("Caviar", 1));
		check("reabastecer Cloro +3 -> 3 L", d.reabastecer("Cloro", 3) && cloro.getCantidad() == 3);
		check("reabastecer negativo -> false", !d.reabastecer("Cloro", -1));
		check("vencidos() = [Leche]", d.vencidos().getSize() == 1 && d.vencidos().get(0) == leche);
		check("porVencer(3) = [Yogurt] (no incluye la vencida)", d.porVencer(3).getSize() == 1 && d.porVencer(3).get(0) == yogurt);
		check("porVencer(1) = [] (yogurt vence en 2)", d.porVencer(1).estaVacia());
		check("porReponer() = [Leche, Jabon]", d.porReponer().getSize() == 2);
		check("getProductos() es copia (no cambia la despensa)", d.getProductos().eliminar(0) && d.getTotal() == 5);

		/* ---------- 4. Lista del super ---------- */
		Consola.subtitulo("4. Lista del super");
		ListaSuper ls = new ListaSuper();
		check("generar() -> 2 pendientes", ls.generar(d) == 2);
		check("agregarExtra valido", ls.agregarExtra("Servilletas"));
		check("agregarExtra vacio -> false", !ls.agregarExtra("   "));
		check("renglones = 2 pendientes + 1 extra", ls.renglones().getSize() == 3);
		check("quitarExtra(5) inexistente -> false", !ls.quitarExtra(5));
		check("quitarExtra(1) -> true", ls.quitarExtra(1) && ls.getExtras().estaVacia());
		check("regenerar conserva los extras", ls.agregarExtra("Papel") && ls.generar(d) == 2 && ls.getExtras().getSize() == 1);

		/* ---------- 5. Archivos de texto ---------- */
		Consola.subtitulo("5. Archivos de texto");
		check("toLinea Perecedero", leche.toLinea().equals("PERECEDERO;Leche;2;1;LITRO;" + hoy.minusDays(1)));
		check("toLinea NoPerecedero", arroz.toLinea().equals("NOPERECEDERO;Arroz;2;1;KG"));
		check("toLinea Limpieza", jabon.toLinea().equals("LIMPIEZA;Jabon;1.2;1;PIEZA;false"));
		check("crearDesdeLinea crea la subclase correcta",
				GestorArchivos.crearDesdeLinea("PERECEDERO;Pan;1;1;PAQUETE;2030-01-01") instanceof Perecedero
						&& GestorArchivos.crearDesdeLinea("NOPERECEDERO;Sal;1;1;KG") instanceof NoPerecedero
						&& GestorArchivos.crearDesdeLinea("LIMPIEZA;Fabuloso;1;1;L;false") instanceof Limpieza);
		check("crearDesdeLinea tipo desconocido -> null", GestorArchivos.crearDesdeLinea("JUGUETE;Lego;1;1;PIEZA") == null);
		check("crearDesdeLinea numero mal -> null", GestorArchivos.crearDesdeLinea("NOPERECEDERO;Sal;mucho;1;KG") == null);
		check("crearDesdeLinea fecha mal -> null", GestorArchivos.crearDesdeLinea("PERECEDERO;Pan;1;1;PAQUETE;ayer") == null);
		check("crearDesdeLinea campos de menos -> null", GestorArchivos.crearDesdeLinea("PERECEDERO;Pan;1") == null);

		File tmp = new File(System.getProperty("java.io.tmpdir"), "despensa_test_" + System.nanoTime());
		File fDespensa = new File(tmp, "despensa.txt");
		File fLista = new File(tmp, "lista_super.txt");
		check("guardarDespensa crea el archivo", GestorArchivos.guardarDespensa(d, fDespensa) && fDespensa.exists());
		Despensa cargada = GestorArchivos.cargarDespensa("Cargada", fDespensa);
		check("cargarDespensa: mismos 5 productos", cargada.getTotal() == 5);
		check("cargarDespensa: Leche sigue siendo Perecedero con su fecha",
				cargada.buscar("Leche") instanceof Perecedero
						&& ((Perecedero) cargada.buscar("Leche")).getFechaCaducidad().equals(hoy.minusDays(1)));
		check("cargarDespensa: Cloro peligroso y Jabon con 1.2",
				((Limpieza) cargada.buscar("Cloro")).esPeligroso() && cargada.buscar("Jabon").getCantidad() == 1.2);
		check("cargarDespensa: archivo inexistente -> despensa vacia",
				GestorArchivos.cargarDespensa("X", new File(tmp, "no_existe.txt")).getTotal() == 0);
		check("exportarLista crea el .txt", GestorArchivos.exportarLista(ls, fLista) && fLista.length() > 0);
		fDespensa.delete();
		fLista.delete();
		tmp.delete();

		/* ---------- 6. Eliminar ---------- */
		Consola.subtitulo("6. Eliminar");
		check("eliminar existente", d.eliminar("jabon") && d.getTotal() == 4 && d.buscar("Jabon") == null);
		check("eliminar inexistente -> false", !d.eliminar("Jabon"));

		/* ---------- 7. Hilos y synchronized ---------- */
		Consola.subtitulo("7. Hilos y synchronized");
		HiloCaducidad h = new HiloCaducidad(d, 1, 3);
		h.revisar();
		check("revisar() genera 2 avisos (Leche vencida, Yogurt por vencer)", h.hayNuevos() && h.getAvisos().getSize() == 2);
		check("tomarAvisosNuevos() los entrega una sola vez", h.tomarAvisosNuevos().getSize() == 2 && !h.hayNuevos());
		h.revisar();
		check("sin cambios no hay avisos nuevos", !h.hayNuevos());
		d.eliminar("Leche");
		h.revisar();
		check("si cambia la despensa hay avisos nuevos", h.hayNuevos() && h.getAvisos().getSize() == 1);

		int antes = h.getRevisiones();
		h.iniciar();
		dormir(1500);
		check("el hilo corre solo en segundo plano", h.estaActivo() && h.getRevisiones() > antes);
		h.detener();
		dormir(200);
		int despues = h.getRevisiones();
		dormir(1200);
		check("detener() para el hilo", !h.estaActivo() && h.getRevisiones() == despues);

		/* Dos hilos agregando y consumiendo a la vez la MISMA despensa */
		Despensa compartida = new Despensa("Compartida");
		compartida.agregar(new NoPerecedero("Galletas", 2000, 1, Unidad.PIEZA));
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 500; i++) {
				compartida.agregar(new NoPerecedero("A" + i, 1, 1, Unidad.PIEZA));
				compartida.consumir("Galletas", 1);
			}
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 500; i++) {
				compartida.agregar(new NoPerecedero("B" + i, 1, 1, Unidad.PIEZA));
				compartida.consumir("Galletas", 1);
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		check("2 hilos x 500 altas = 1001 productos (no se pierde ninguno)", compartida.getTotal() == 1001);
		check("2 hilos x 500 consumos: Galletas 2000 -> 1000", compartida.buscar("Galletas").getCantidad() == 1000);

		/* ---------- Resumen ---------- */
		System.out.println();
		Consola.separador();
		String resumen = "Resultado: " + pasan + " OK, " + fallan + " fallas (" + (pasan + fallan) + " pruebas)";
		if (fallan == 0)
			Consola.ok(resumen);
		else
			Consola.error(resumen);
	}

	static void dormir(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
