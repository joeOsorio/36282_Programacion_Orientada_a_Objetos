/* **************************************************
 * Exporta los registros en formato JSON.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class ExportadorJSON extends Exportador {

	@Override
	public String getTipo() {
		return "JSON";
	}

	@Override
	public String exportar(Registro[] registros) {
		if (registros == null || registros.length == 0) {
			return "{\n\t\"datos\": []\n}";
		}
		String salida = "{\n\t\"datos\": [\n";
		for (int i = 0; i < registros.length; i++) {
			Registro r = registros[i];
			salida += "\t\t{\n";
			salida += "\t\t\t\"id\": " + r.getId() + ",\n";
			salida += "\t\t\t\"persona\": \"" + r.getPersona() + "\",\n";
			salida += "\t\t\t\"departamento\": \"" + r.getDepartamento() + "\"\n";
			salida += "\t\t}" + (i < registros.length - 1 ? "," : "") + "\n"; /* sin coma en el ultimo */
		}
		return salida + "\t]\n}";
	}
}
