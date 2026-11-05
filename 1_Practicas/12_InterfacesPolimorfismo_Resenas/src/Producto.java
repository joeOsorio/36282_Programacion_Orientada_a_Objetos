public class Producto {
	public static final int MAX_RESENAS = 20;
	public static final int MIN_RESENAS_RANKING = 5;

	private String sku;
	private String nombre;
	private Resena[] resenas;
	private int contador;

	public Producto(String sku, String nombre) {
		this.sku = sku;
		this.nombre = nombre;
		this.resenas = new Resena[MAX_RESENAS];
		this.contador = 0;
	}

	public boolean agregarResena(Resena r) {
		if (r == null) {
			System.out.println("Error: resena nula");
			return false;
		}
		if (!r.getSku().equals(sku)) {
			System.out.println("Error: la resena es de " + r.getSku() + ", no de " + sku);
			return false;
		}
		if (contador >= MAX_RESENAS) {
			System.out.println("Error: " + sku + " ya tiene el maximo de " + MAX_RESENAS + " resenas");
			return false;
		}
		resenas[contador++] = r;
		return true;
	}

	public int contarPorVeredicto(Veredicto v) {
		int n = 0;
		for (int i = 0; i < contador; i++)
			if (resenas[i].getVeredicto() == v)
				n++;
		return n;
	}

	public Resena[] getAprobadas() {
		Resena[] aprobadas = new Resena[contarPorVeredicto(Veredicto.APROBADA)];
		int j = 0;
		for (int i = 0; i < contador; i++)
			if (resenas[i].getVeredicto() == Veredicto.APROBADA)
				aprobadas[j++] = resenas[i];
		return aprobadas;
	}

	public boolean cumpleMinimo() {
		return contarPorVeredicto(Veredicto.APROBADA) >= MIN_RESENAS_RANKING;
	}

	public double promedioTodas() {
		if (contador == 0)
			return 0.0;
		double suma = 0;
		for (int i = 0; i < contador; i++)
			suma += resenas[i].getCalificacion();
		return suma / contador;
	}

	public String getSku() {
		return sku;
	}

	public String getNombre() {
		return nombre;
	}

	public int getContador() {
		return contador;
	}

	public Resena getResena(int i) {
		return (i >= 0 && i < contador) ? resenas[i] : null;
	}

	@Override
	public String toString() {
		return sku + " - " + nombre;
	}
}
