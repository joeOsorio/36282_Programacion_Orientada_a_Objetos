/* **************************************************
 * Exporta los registros como tabla de Markdown.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class ExportadorMarkdown extends Exportador {

	@Override
	public String getTipo() {
		return "Markdown";
	}

	@Override
	public String exportar(Registro[] registros) {
		String salida = "| id | persona | departamento |\n";
		salida += "|:---:|:---:|:---:|\n";
		if (registros == null) {
			return salida;
		}
		for (Registro r : registros) {
			salida += "| " + r.getId() + " | " + r.getPersona() + " | " + r.getDepartamento() + " |\n";
		}
		return salida;
	}
}
