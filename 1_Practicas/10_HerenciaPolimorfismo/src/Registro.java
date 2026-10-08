/* **************************************************
 * Laboratorio 10: Clase Registro
 * Un renglon de datos del ejercicio 2: id, persona y departamento.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Registro {

	private int id;
	private String persona;
	private String departamento;

	/* Los registros llegan como texto */
	public Registro(String id, String persona, String departamento) {
		this.id = Integer.parseInt(id.trim());
		this.persona = persona;
		this.departamento = departamento;
	}

	/* Getters */
	public int getId() {
		return id;
	}

	public String getPersona() {
		return persona;
	}

	public String getDepartamento() {
		return departamento;
	}

	@Override
	public String toString() {
		return id + " - " + persona + " (" + departamento + ")";
	}
}
