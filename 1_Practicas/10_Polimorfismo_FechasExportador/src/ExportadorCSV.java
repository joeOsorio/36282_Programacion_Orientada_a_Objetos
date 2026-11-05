/* **************************************************
 * Exporta los registros separados por comas (CSV).
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class ExportadorCSV extends Exportador {

	@Override
	public String getTipo() {
		return "CSV";
	}

	@Override
	public String exportar(Registro[] registros) {
		String salida = "id, persona, departamento\n";
		if (registros == null) {
			return salida;
		}
		for (Registro r : registros) {
			salida += r.getId() + ", " + r.getPersona() + ", " + r.getDepartamento() + "\n";
		}
		return salida;
	}
}
