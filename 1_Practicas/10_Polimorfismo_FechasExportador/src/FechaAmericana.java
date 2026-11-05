/* **************************************************
 * Formato Americano: mes / dia / anio
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class FechaAmericana extends Fecha {

	public FechaAmericana(long timestamp) {
		super(timestamp);
	}

	@Override
	public String getNombreFormato() {
		return "Americano";
	}

	@Override
	public String formatear() {
		return String.format("%02d/%02d/%04d", mes, dia, anio);
	}
}
