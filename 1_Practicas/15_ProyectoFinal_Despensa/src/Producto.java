import java.util.Locale;

/* ****************************************************************
 * Proyecto Final: Clase abstracta Producto
 * Base de todo lo que hay en la despensa. Las subclases deciden
 * si caducan, cuando hay que reponerlas y cuanto comprar.
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public abstract class Producto implements IGuardable {
	protected String nombre;
	protected double cantidad;
	protected double cantidadMinima;
	protected Unidad unidad;

	public Producto(String nombre, double cantidad, double cantidadMinima, Unidad unidad) {
		this.nombre = nombre.trim();
		this.cantidad = Math.max(0, cantidad);
		this.cantidadMinima = Math.max(0, cantidadMinima);
		this.unidad = (unidad == null) ? Unidad.PIEZA : unidad;
	}

	/* *********************** Abstractos ************************/
	public abstract String getTipo();

	public abstract boolean estaVencido();

	/* *********************** Reglas (se pueden sobrescribir) ************************/
	/* Por defecto: se repone cuando llega al minimo */
	public boolean necesitaReponer() {
		return cantidad <= cantidadMinima;
	}

	/* Por defecto: comprar lo necesario para quedar al doble del minimo (al menos 1) */
	public double cantidadAComprar() {
		double falta = cantidadMinima * 2 - cantidad;
		return Math.max(1, falta);
	}

	/* Texto corto del estado para la tabla */
	public String getEstado() {
		if (estaVencido())
			return "VENCIDO";
		if (cantidad == 0)
			return "AGOTADO";
		if (necesitaReponer())
			return "REPONER";
		return "OK";
	}

	/* Info extra de cada tipo (fecha, aviso, etc.) */
	public String getDetalle() {
		return "";
	}

	/* *********************** Operaciones ************************/
	public boolean consumir(double cuanto) {
		if (cuanto <= 0) {
			System.out.println("Error: la cantidad a consumir debe ser mayor a 0");
			return false;
		}
		if (cuanto > cantidad) {
			System.out.println("Error: solo hay " + formato(cantidad) + " " + unidad.getAbreviatura() + " de " + nombre);
			return false;
		}
		cantidad -= cuanto;
		return true;
	}

	public boolean reabastecer(double cuanto) {
		if (cuanto <= 0) {
			System.out.println("Error: la cantidad a agregar debe ser mayor a 0");
			return false;
		}
		cantidad += cuanto;
		return true;
	}

	/* *********************** Validacion ************************/
	/* Se llama ANTES de construir el objeto; imprime el error y regresa false */
	public static boolean datosValidos(String nombre, double cantidad, double cantidadMinima) {
		if (nombre == null || nombre.trim().isEmpty()) {
			System.out.println("Error: el nombre no puede estar vacio");
			return false;
		}
		if (nombre.contains(SEPARADOR)) {
			System.out.println("Error: el nombre no puede llevar '" + SEPARADOR + "'");
			return false;
		}
		if (cantidad < 0 || cantidadMinima < 0) {
			System.out.println("Error: las cantidades no pueden ser negativas");
			return false;
		}
		return true;
	}

	/* 2 -> "2", 1.5 -> "1.5" */
	public static String formato(double valor) {
		if (valor == Math.rint(valor))
			return String.format(Locale.ROOT, "%d", (long) valor);
		return String.format(Locale.ROOT, "%.2f", valor).replaceAll("0+$", "");
	}

	/* *********************** IGuardable ************************/
	/* TIPO;nombre;cantidad;minima;UNIDAD  (las subclases agregan lo suyo) */
	@Override
	public String toLinea() {
		return getTipo() + SEPARADOR + nombre + SEPARADOR + formato(cantidad) + SEPARADOR
				+ formato(cantidadMinima) + SEPARADOR + unidad.name();
	}

	/* Getters */
	public String getNombre() {
		return nombre;
	}

	public double getCantidad() {
		return cantidad;
	}

	public double getCantidadMinima() {
		return cantidadMinima;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	/* Setters */
	public void setCantidadMinima(double cantidadMinima) {
		if (cantidadMinima >= 0)
			this.cantidadMinima = cantidadMinima;
	}

	@Override
	public String toString() {
		return String.format("%-13s %-18s %8s %-4s %-8s %s", getTipo(), nombre,
				formato(cantidad), unidad.getAbreviatura(), getEstado(), getDetalle());
	}
}
