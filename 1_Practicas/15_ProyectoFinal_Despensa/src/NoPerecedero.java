/* ****************************************************************
 * Proyecto Final: Clase NoPerecedero
 * Alimentos que no caducan pronto (arroz, frijol, latas, pasta).
 * Nunca se marca como vencido; se repone solo por cantidad.
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class NoPerecedero extends Producto {
	public static final String TIPO = "NOPERECEDERO";

	public NoPerecedero(String nombre, double cantidad, double cantidadMinima, Unidad unidad) {
		super(nombre, cantidad, cantidadMinima, unidad);
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
	public String getDetalle() {
		return "No caduca";
	}
}
