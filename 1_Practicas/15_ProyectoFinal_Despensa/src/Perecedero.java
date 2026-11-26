import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/* ****************************************************************
 * Proyecto Final: Clase Perecedero
 * Producto con fecha de caducidad (leche, carne, fruta...).
 * Si ya vencio hay que reponerlo aunque todavia quede.
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class Perecedero extends Producto {
	public static final String TIPO = "PERECEDERO";
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private LocalDate fechaCaducidad;

	public Perecedero(String nombre, double cantidad, double cantidadMinima, Unidad unidad, LocalDate fechaCaducidad) {
		super(nombre, cantidad, cantidadMinima, unidad);
		this.fechaCaducidad = (fechaCaducidad == null) ? LocalDate.now() : fechaCaducidad;
	}

	/* *********************** Caducidad ************************/
	/* Negativo = ya vencio, 0 = vence hoy */
	public long diasParaCaducar() {
		return ChronoUnit.DAYS.between(LocalDate.now(), fechaCaducidad);
	}

	public boolean venceEnMenosDe(int dias) {
		long d = diasParaCaducar();
		return d >= 0 && d <= dias;
	}

	/* *********************** Sobrescritos ************************/
	@Override
	public String getTipo() {
		return TIPO;
	}

	@Override
	public boolean estaVencido() {
		return diasParaCaducar() < 0;
	}

	/* Vencido = hay que reponerlo, quede lo que quede */
	@Override
	public boolean necesitaReponer() {
		return estaVencido() || super.necesitaReponer();
	}

	/* Si vencio, lo que queda no sirve: se compra todo de nuevo */
	@Override
	public double cantidadAComprar() {
		if (estaVencido())
			return Math.max(1, cantidadMinima * 2);
		return super.cantidadAComprar();
	}

	@Override
	public String getEstado() {
		if (!estaVencido() && cantidad > 0 && venceEnMenosDe(3))
			return "CADUCA";
		return super.getEstado();
	}

	@Override
	public String getDetalle() {
		long d = diasParaCaducar();
		String cuando;
		if (d < 0)
			cuando = "vencio hace " + (-d) + " dia(s)";
		else if (d == 0)
			cuando = "vence HOY";
		else
			cuando = "vence en " + d + " dia(s)";
		return "Cad. " + fechaCaducidad.format(FORMATO_FECHA) + " (" + cuando + ")";
	}

	/* ...;fecha en formato ISO (yyyy-MM-dd) */
	@Override
	public String toLinea() {
		return super.toLinea() + SEPARADOR + fechaCaducidad;
	}

	/* Getters */
	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	/* Setters */
	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		if (fechaCaducidad != null)
			this.fechaCaducidad = fechaCaducidad;
	}
}
