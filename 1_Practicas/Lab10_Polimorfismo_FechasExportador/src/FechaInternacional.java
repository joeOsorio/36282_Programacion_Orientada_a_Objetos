/* **************************************************
 * Formato Internacional: anio / mes / dia
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class FechaInternacional extends Fecha {

	public FechaInternacional(long timestamp) {
		super(timestamp);
	}

	@Override
	public String getNombreFormato() {
		return "Internacional";
	}

	@Override
	public String formatear() {
		return String.format("%04d/%02d/%02d", anio, mes, dia);
	}
}
