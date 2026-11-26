/* ****************************************************************
 * Proyecto Final: Clase Limpieza
 * Productos de limpieza (cloro, jabon, detergente). No caducan, y
 * los peligrosos llevan un aviso para guardarlos lejos de la comida.
 * Se repone ANTES de llegar al minimo (no puedes quedarte sin jabon).
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class Limpieza extends Producto {
	public static final String TIPO = "LIMPIEZA";
	/* Margen extra: se avisa cuando queda menos de minimo + 50% */
	public static final double MARGEN = 1.5;

	private boolean peligroso;

	public Limpieza(String nombre, double cantidad, double cantidadMinima, Unidad unidad, boolean peligroso) {
		super(nombre, cantidad, cantidadMinima, unidad);
		this.peligroso = peligroso;
	}

	/* *********************** Sobrescritos ************************/
	@Override
	public String getTipo() {
		return TIPO;
	}

	@Override
	public boolean estaVencido() {
		return false;
	}

	@Override
	public boolean necesitaReponer() {
		return cantidad < cantidadMinima * MARGEN || cantidad == 0;
	}

	@Override
	public String getDetalle() {
		return peligroso ? "Peligroso: lejos de alimentos" : "No caduca";
	}

	/* ...;true/false */
	@Override
	public String toLinea() {
		return super.toLinea() + SEPARADOR + peligroso;
	}

	/* Getters */
	public boolean esPeligroso() {
		return peligroso;
	}
}
