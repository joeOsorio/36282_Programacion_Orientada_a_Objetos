/* **************************************************
 * Recibe un UNIX timestamp y calcula dia, mes y anio con el algoritmo proporcionado por Don omar.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Fecha {

	protected long timestamp;
	protected int dia;
	protected int mes;
	protected long anio;

	public Fecha(long timestamp) {
		this.timestamp = timestamp;
		calcular();
	}

	/* *********************** Algoritmo *********************** */
	/* Este código es una implementación del algoritmo de Howard Hinnant */
	/* Math.floorDiv = division entera (//) que tambien sirve con negativos */
	private void calcular() {
		long z = Math.floorDiv(timestamp, 86400);
		z += 719468;
		long era = Math.floorDiv(z, 146097);
		long doe = z - era * 146097;
		long yoe = (doe - doe / 1460 + doe / 36524 - doe / 146096) / 365;
		long doy = doe - (365 * yoe + yoe / 4 - yoe / 100);
		long mp = (5 * doy + 2) / 153;
		dia = (int) (doy - (153 * mp + 2) / 5 + 1);
		mes = (int) (mp < 10 ? mp + 3 : mp - 9);
		anio = yoe + era * 400 + (mes <= 2 ? 1 : 0);
	}

	/* *********************** Polimorfismo ************************/
	/* Cada hija sobrescribe estos dos metodos con su propio formato */
	public String getNombreFormato() {
		return "Generico";
	}

	public String formatear() {
		return String.format("%04d-%02d-%02d", anio, mes, dia);
	}

	/* Getters */
	public long getTimestamp() {
		return timestamp;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public long getAnio() {
		return anio;
	}

	/* *********************** Informacion ************************/
	@Override
	public String toString() {
		return String.format("%-14s %s", getNombreFormato() + ":", formatear());
	}
}
