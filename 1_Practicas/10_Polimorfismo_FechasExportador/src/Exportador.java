/* **************************************************
 * Clase padre, exportar() recibe los registros y regresa el String a imprimir; cada hijo pone su formato.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Exportador {

	public String getTipo() {
		return "Texto plano";
	}

	public String exportar(Registro[] registros) {
		if (registros == null || registros.length == 0) {
			return "Error: no hay registros para exportar.";
		}
		String salida = "";
		for (Registro r : registros) {
			salida += r + "\n";
		}
		return salida;
	}
}
