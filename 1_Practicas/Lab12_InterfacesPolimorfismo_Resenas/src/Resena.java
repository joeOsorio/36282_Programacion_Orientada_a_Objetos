public class Resena {
	public static final int MIN_ESTRELLAS = 1;
	public static final int MAX_ESTRELLAS = 5;
	private static int siguienteId = 1273271;

	private int id;
	private int calificacion;
	private String sku;
	private String usuario;
	private String texto;
	private String filtroCausante;
	private boolean compraVerificada;
	private Veredicto veredicto;

	public Resena(String sku, String usuario, int calificacion, String texto, boolean compraVerificada) {
		this.id = siguienteId++;
		this.sku = sku;
		this.usuario = usuario;
		this.texto = (texto == null) ? "" : texto;
		this.compraVerificada = compraVerificada;
		this.veredicto = Veredicto.REVISION_MANUAL;
		this.filtroCausante = "-";
		if (calificacion > MAX_ESTRELLAS) {
			System.out.println("Error: calificacion " + calificacion + " fuera de rango, se ajusta a " + MAX_ESTRELLAS);
			calificacion = MAX_ESTRELLAS;
		} else if (calificacion < MIN_ESTRELLAS) {
			System.out.println("Error: calificacion " + calificacion + " fuera de rango, se ajusta a " + MIN_ESTRELLAS);
			calificacion = MIN_ESTRELLAS;
		}
		this.calificacion = calificacion;
	}

	public int contarPalabras() {
		String t = texto.trim();
		if (t.isEmpty())
			return 0;
		return t.split("\\s+").length;
	}

	public void setVeredicto(Veredicto veredicto) {
		this.veredicto = veredicto;
	}

	public void setFiltroCausante(String filtroCausante) {
		this.filtroCausante = filtroCausante;
	}

	public int getId() {
		return id;
	}

	public String getSku() {
		return sku;
	}

	public String getUsuario() {
		return usuario;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public String getTexto() {
		return texto;
	}

	public boolean isCompraVerificada() {
		return compraVerificada;
	}

	public Veredicto getVeredicto() {
		return veredicto;
	}

	public String getFiltroCausante() {
		return filtroCausante;
	}

	@Override
	public String toString() {
		String corto = texto.length() > 40 ? texto.substring(0, 37) + "..." : texto;
		return String.format("#%-3d %-7s %-10s %d* %s \"%s\"", id, sku, usuario, calificacion,
				compraVerificada ? "[verif]" : "[  -  ]", corto);
	}
}
