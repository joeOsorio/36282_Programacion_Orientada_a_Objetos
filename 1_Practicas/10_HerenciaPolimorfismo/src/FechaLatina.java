/* **************************************************
 * Formato Latino: dia / mes / anio
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class FechaLatina extends Fecha {

	public FechaLatina(long timestamp) {
		super(timestamp);
	}

	@Override
	public String getNombreFormato() {
		return "Latino";
	}

	@Override
	public String formatear() {
		return String.format("%02d/%02d/%04d", dia, mes, anio);
	}
}
