public class Main {
	private static int ok = 0;
	private static int fallos = 0;

	public static void main(String[] args) {
		test();
	}

	public static void test() {
		System.out.println("==================== TEST LABORATORIO 12 ====================");
		pruebasUnitarias();
		escenarioProductos();
		System.out.println("\n==================== RESUMEN ====================");
		System.out.println("Checks OK: " + ok + "   Fallos: " + fallos);
	}

	private static void check(String descripcion, boolean condicion) {
		if (condicion)
			ok++;
		else
			fallos++;
		System.out.println((condicion ? "  [OK]    " : "  [FALLO] ") + descripcion);
	}

	private static void pruebasUnitarias() {
		System.out.println("\n---- 1) Resena: validacion de estrellas ----");
		Resena r6 = new Resena("XX@000", "prueba", 6, "texto de prueba con cinco palabras", true);
		check("Calificacion 6 se ajusta a 5", r6.getCalificacion() == 5);
		Resena r0 = new Resena("XX@000", "prueba", 0, "texto de prueba con cinco palabras", false);
		check("Calificacion 0 se ajusta a 1", r0.getCalificacion() == 1);
		Resena r4 = new Resena("XX@000", "prueba", 4, "  uno   dos tres  ", true);
		check("Calificacion 4 se respeta", r4.getCalificacion() == 4);
		check("contarPalabras ignora espacios de sobra (3)", r4.contarPalabras() == 3);
		check("Ids unicos y consecutivos", r0.getId() == r6.getId() + 1 && r4.getId() == r0.getId() + 1);
		check("Veredicto inicial = REVISION_MANUAL (pendiente)", r4.getVeredicto() == Veredicto.REVISION_MANUAL);

		System.out.println("\n---- 2) Veredicto: severidad ----");
		check("RECHAZADA es mas severo que REVISION_MANUAL",
				Veredicto.RECHAZADA.esMasSeveroQue(Veredicto.REVISION_MANUAL));
		check("REVISION_MANUAL es mas severo que APROBADA",
				Veredicto.REVISION_MANUAL.esMasSeveroQue(Veredicto.APROBADA));
		check("APROBADA NO es mas severo que RECHAZADA", !Veredicto.APROBADA.esMasSeveroQue(Veredicto.RECHAZADA));

		System.out.println("\n---- 3) FiltroGroserias ----");
		Filtro g = new FiltroGroserias();
		check("Texto limpio -> APROBADA", g.revisar(nueva("Muy buen producto lo recomiendo")) == Veredicto.APROBADA);
		check("'idiota' -> RECHAZADA", g.revisar(nueva("El vendedor es un idiota total")) == Veredicto.RECHAZADA);
		check("Mayusculas + acento (ESTUPIDO con tilde) -> RECHAZADA",
				g.revisar(nueva("Que producto tan ESTÚPIDO la verdad")) == Veredicto.RECHAZADA);
		check("Pegada a signos 'idiota!!' -> RECHAZADA",
				g.revisar(nueva("Eres un idiota!! no compren")) == Veredicto.RECHAZADA);
		check("Palabra que solo CONTIENE una groseria ('basurero') -> APROBADA",
				g.revisar(nueva("Lo uso como basurero en la cocina")) == Veredicto.APROBADA);

		System.out.println("\n---- 4) FiltroLongitud ----");
		Filtro l = new FiltroLongitud();
		check("4 palabras -> RECHAZADA", l.revisar(nueva("no me gusto nada")) == Veredicto.RECHAZADA);
		check("5 palabras (limite) -> APROBADA", l.revisar(nueva("no me gusto para nada")) == Veredicto.APROBADA);
		check("255 palabras (limite) -> APROBADA", l.revisar(nueva(repetir("bien ", 255))) == Veredicto.APROBADA);
		check("256 palabras -> RECHAZADA", l.revisar(nueva(repetir("bien ", 256))) == Veredicto.RECHAZADA);
		check("Texto vacio -> RECHAZADA", l.revisar(nueva("")) == Veredicto.RECHAZADA);

		System.out.println("\n---- 5) FiltroSpam ----");
		FiltroSpam s = new FiltroSpam();
		check("Texto normal -> APROBADA", s.revisar(nueva("Llego rapido y en buen estado")) == Veredicto.APROBADA);
		check("TODO MAYUSCULAS -> REVISION_MANUAL",
				s.revisar(nueva("COMPREN YA OFERTA INCREIBLE AQUI")) == Veredicto.REVISION_MANUAL);
		check("Exactamente 65% NO es spam (debe ser MAS del 65%)",
				s.revisar(nueva("ABCDEFGHIJKLMhijklmn")) == Veredicto.APROBADA); // 13/20 = 0.65
		check("Numeros/signos no cuentan como letras", s.porcentajeMayusculas("OK 123 !!!") == 1.0);

		System.out.println("\n---- 6) Moderador: el filtro mas severo gana ----");
		Moderador m = new Moderador(new Filtro[] { new FiltroSpam(), new FiltroLongitud(), new FiltroGroserias() });
		Resena spamCorta = nueva("PURA BASURA");
		check("Spam + corta + groseria -> RECHAZADA", m.moderar(spamCorta) == Veredicto.RECHAZADA);
		check("Causa = primer filtro que RECHAZA (FiltroLongitud), no FiltroSpam",
				spamCorta.getFiltroCausante().equals("FiltroLongitud"));
		Resena soloSpam = nueva("ESTE PRODUCTO ES LO MEJOR DEL MUNDO");
		check("Solo spam -> REVISION_MANUAL", m.moderar(soloSpam) == Veredicto.REVISION_MANUAL);
		check("Causa = FiltroSpam", m.getFiltroMasSevero().equals("FiltroSpam"));
		Resena buena = nueva("Funciona perfecto desde hace un mes");
		check("Resena buena -> APROBADA", m.moderar(buena) == Veredicto.APROBADA);
		check("Sin causa ('-')", buena.getFiltroCausante().equals("-"));

		System.out.println("\n---- 7) Producto ----");
		Producto p = new Producto("ZZ@999", "Producto de prueba");
		check("Agregar resena de otro SKU -> false", !p.agregarResena(new Resena("OTRO@1", "x", 5, "a b c d e", true)));
		check("Agregar null -> false", !p.agregarResena(null));
		for (int i = 0; i < Producto.MAX_RESENAS; i++)
			p.agregarResena(new Resena("ZZ@999", "u" + i, 3, "a b c d e", true));
		check("Lleno con " + Producto.MAX_RESENAS + " resenas", p.getContador() == Producto.MAX_RESENAS);
		check("Resena 21 -> false (arreglo lleno)",
				!p.agregarResena(new Resena("ZZ@999", "extra", 3, "a b c d e", true)));
		check("Sin moderar no hay aprobadas", p.getAprobadas().length == 0 && !p.cumpleMinimo());

		System.out.println("\n---- 8) Promedios ----");
		Resena a = new Resena("PP@1", "a", 5, repetir("w ", 5), true); // p = 5/8 = 0.625
		Resena b = new Resena("PP@1", "b", 3, repetir("w ", 45), true); // p = 45/48 = 0.9375
		Resena c = new Resena("PP@2", "c", 1, repetir("w ", 20), true); // otro sku
		Resena d = new Resena("PP@1", "d", 1, repetir("w ", 20), true); // no aprobada
		a.setVeredicto(Veredicto.APROBADA);
		b.setVeredicto(Veredicto.APROBADA);
		c.setVeredicto(Veredicto.APROBADA);
		d.setVeredicto(Veredicto.RECHAZADA);
		Resena[] mezcla = { a, b, c, d };
		CalcularCalificacion simple = new PromedioSimple();
		CalcularCalificacion bayes = new PromedioBayesiano();
		check("Simple PP@1 = (5+3)/2 = 4.0 (ignora otro sku y rechazadas)",
				cerca(simple.calcular(mezcla, "PP@1"), 4.0));
		check("Bayesiano PP@1 = (5*0.625 + 3*0.9375)/2 = 2.96875", cerca(bayes.calcular(mezcla, "PP@1"), 2.96875));
		check("Sin resenas del sku -> 0.0",
				cerca(simple.calcular(mezcla, "NADA"), 0.0) && cerca(bayes.calcular(new Resena[0], "PP@1"), 0.0));
	}

	private static void escenarioProductos() {
		System.out.println("\n\n==================== ESCENARIO: 3 PRODUCTOS ====================");
		Producto audifonos = new Producto("VX@123", "Audifonos inalambricos");
		Producto teclado = new Producto("KB@456", "Teclado mecanico");
		Producto mouse = new Producto("MS@789", "Mouse ergonomico");
		audifonos.agregarResena(
				new Resena("VX@123", "ana_g", 5, "Suenan increible y la bateria dura todo el dia sin problema", true));
		audifonos.agregarResena(new Resena("VX@123", "bot_777", 5, "Excelente!!!", false));
		audifonos.agregarResena(
				new Resena("VX@123", "trol99", 5, "Cinco estrellas para que el idiota de mi vecino los compre", false));
		teclado.agregarResena(
				new Resena("KB@456", "carlos_m", 5, "Los switches se sienten muy bien y el RGB es bonito", true));
		teclado.agregarResena(
				new Resena("KB@456", "luisa", 4, "Buen teclado aunque un poco ruidoso para la oficina", true));
		teclado.agregarResena(new Resena("KB@456", "pepe", 4, "Muy bueno", false));
		teclado.agregarResena(new Resena("KB@456", "spammer", 4, "COMPRALO YA ES EL MEJOR", false));
		teclado.agregarResena(
				new Resena("KB@456", "rosa", 3, "Cumple pero la tecla espaciadora rechina un poco", true));
		teclado.agregarResena(
				new Resena("KB@456", "enojon", 4, "El repartidor es un imbecil pero el teclado bien", false));
		teclado.agregarResena(new Resena("KB@456", "miguel", 3, "Esta bien por el precio aunque esperaba mas", true));
		teclado.agregarResena(new Resena("KB@456", "x", 4, "ok", false));
		teclado.agregarResena(
				new Resena("KB@456", "sofia", 4, "Lo uso diario para programar y no me ha fallado", true));
		teclado.agregarResena(
				new Resena("KB@456", "grosero", 3, "Para ser tan caro esta medio pendejo el software", false));

		mouse.agregarResena(new Resena("MS@789", "dani", 5, "La mejor compra del ano, adios dolor de muneca", true));
		mouse.agregarResena(
				new Resena("MS@789", "jorge", 4, "Comodo para jornadas largas de trabajo en la compu", true));
		mouse.agregarResena(new Resena("MS@789", "marta", 4, "Buen agarre y los botones laterales son utiles", true));
		mouse.agregarResena(new Resena("MS@789", "ivan", 5, "Excelente", false));
		mouse.agregarResena(new Resena("MS@789", "paola", 3, "Se tarda en acostumbrarse a la forma vertical", true));
		mouse.agregarResena(new Resena("MS@789", "raul", 4, "Funciona bien con bluetooth y con el receptor usb", true));
		mouse.agregarResena(new Resena("MS@789", "elena", 4, "Bonito diseno y la bateria dura semanas enteras", true));
		mouse.agregarResena(new Resena("MS@789", "promo", 5, "SUPER OFERTA ENTRA A MI PERFIL Y GANA", false));
		mouse.agregarResena(new Resena("MS@789", "tomas", 3, "Es bueno pero la rueda se siente algo floja", true));
		mouse.agregarResena(
				new Resena("MS@789", "lucia", 4, "Lo recomiendo para quien pasa horas en la computadora", true));
		mouse.agregarResena(new Resena("MS@789", "beto", 4, "Llego antes de lo esperado y bien empacado", true));
		mouse.agregarResena(new Resena("MS@789", "hater", 3, "Una mierda el empaque pero el mouse sirve", false));
		mouse.agregarResena(new Resena("MS@789", "nora", 4, "Silencioso y preciso, justo lo que buscaba", true));

		Producto[] productos = { audifonos, teclado, mouse };
		Moderador moderador = new Moderador(
				new Filtro[] { new FiltroGroserias(), new FiltroLongitud(), new FiltroSpam() });
		System.out.println("\nFiltros del moderador (en orden de ejecucion):");
		for (Filtro f : moderador.getFiltros())
			System.out.println("  * " + f.getNombre());

		for (Producto p : productos) {
			System.out.println("\n>>> Moderando " + p + "  (" + p.getContador() + " resenas, promedio de todas: "
					+ String.format("%.2f", p.promedioTodas()) + ")");
			for (int i = 0; i < p.getContador(); i++)
				moderador.moderar(p.getResena(i));
		}

		System.out.println("\n---- Resumen de moderacion ----");
		for (Producto p : productos) {
			int rech = p.contarPorVeredicto(Veredicto.RECHAZADA);
			int rev = p.contarPorVeredicto(Veredicto.REVISION_MANUAL);
			System.out.println(p + ": " + p.getContador() + " resenas | aprobadas " + p.getAprobadas().length
					+ " | no publicadas " + noPublicadas(p) + " (rechazadas " + rech + ", revision manual " + rev
					+ ")");
			for (int i = 0; i < p.getContador(); i++) {
				Resena r = p.getResena(i);
				if (r.getVeredicto() != Veredicto.APROBADA)
					System.out.println("     #" + r.getId() + " " + r.getUsuario() + " -> " + r.getVeredicto() + " por "
							+ r.getFiltroCausante());
			}
		}
		check("Producto 1: 3 resenas de 5*, 2 no publicadas", audifonos.getContador() == 3
				&& cerca(audifonos.promedioTodas(), 5.0) && noPublicadas(audifonos) == 2);
		check("Producto 2: 10 resenas, promedio 3.8, 5 no publicadas", teclado.getContador() == 10
				&& cerca(teclado.promedioTodas(), 3.8) && noPublicadas(teclado) == 5);
		check("Producto 3: 13 resenas, promedio 4.0, 3 no publicadas", mouse.getContador() == 13
				&& cerca(mouse.promedioTodas(), 4.0) && noPublicadas(mouse) == 3);

		/* ---- Calificacion y ranking ---- */
		CalcularCalificacion[] estrategias = { new PromedioSimple(), new PromedioBayesiano() };
		for (CalcularCalificacion e : estrategias)
			mostrarRanking(productos, e, estrategias);

		check("Producto 1 (una sola resena de 5*) NO queda arriba: va ultimo por no cumplir el minimo",
				ordenar(productos, estrategias[0])[2] == audifonos
						&& ordenar(productos, estrategias[1])[2] == audifonos);
	}

	private static Producto[] ordenar(Producto[] productos, CalcularCalificacion e) {
		Producto[] orden = new Producto[productos.length];
		for (int i = 0; i < productos.length; i++)
			orden[i] = productos[i];

		for (int i = 0; i < orden.length - 1; i++)
			for (int j = 0; j < orden.length - 1 - i; j++)
				if (vaAntes(orden[j + 1], orden[j], e)) {
					Producto tmp = orden[j];
					orden[j] = orden[j + 1];
					orden[j + 1] = tmp;
				}
		return orden;
	}

	private static boolean vaAntes(Producto a, Producto b, CalcularCalificacion e) {
		if (a.cumpleMinimo() != b.cumpleMinimo())
			return a.cumpleMinimo();
		return e.calcular(a.getAprobadas(), a.getSku()) > e.calcular(b.getAprobadas(), b.getSku());
	}

	private static void mostrarRanking(Producto[] productos, CalcularCalificacion e, CalcularCalificacion[] todas) {
		System.out.println("\n---- Ranking ordenado por " + e.getNombre() + " (minimo "
				+ Producto.MIN_RESENAS_RANKING + " resenas aprobadas) ----");
		System.out.printf("  %-3s %-32s %-10s %-9s %-11s %s%n", "#", "Producto", "Aprobadas", "Simple", "Bayesiano",
				"Estrellas");
		Producto[] orden = ordenar(productos, e);
		for (int i = 0; i < orden.length; i++) {
			Producto p = orden[i];
			double s = todas[0].calcular(p.getAprobadas(), p.getSku());
			double b = todas[1].calcular(p.getAprobadas(), p.getSku());
			double usado = e.calcular(p.getAprobadas(), p.getSku());
			String nota = p.cumpleMinimo() ? estrellas(usado) : "(pocas resenas, no rankea)";
			System.out.printf("  %-3d %-32s %-10d %-9.3f %-11.3f %s%n", i + 1, p.toString(), p.getAprobadas().length, s,
					b, nota);
		}
	}

	private static int noPublicadas(Producto p) {
		return p.getContador() - p.getAprobadas().length;
	}

	private static String estrellas(double promedio) {
		int llenas = (int) Math.round(promedio);
		String s = "";
		for (int i = 0; i < Resena.MAX_ESTRELLAS; i++)
			s += (i < llenas) ? "*" : ".";
		return s;
	}

	private static Resena nueva(String texto) {
		return new Resena("TT@000", "tester", 3, texto, true);
	}

	private static String repetir(String s, int n) {
		String r = "";
		for (int i = 0; i < n; i++)
			r += s;
		return r;
	}

	private static boolean cerca(double a, double b) {
		return Math.abs(a - b) < 1e-9;
	}
}
