/* ****************************************************************
 * Proyecto Final: Enum Unidad
 * Unidades de medida de los productos de la despensa, cada una con
 * su abreviatura para mostrarla en las tablas.
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public enum Unidad {
	PIEZA("pz"),
	KG("kg"),
	LITRO("L"),
	PAQUETE("paq");

	private final String abreviatura;

	Unidad(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/* Getters */
	public String getAbreviatura() {
		return abreviatura;
	}

	/* Convierte el texto del archivo (o lo que escriba el usuario) en Unidad, null si no existe */
	public static Unidad desdeTexto(String texto) {
		if (texto == null)
			return null;
		for (Unidad u : values())
			if (u.name().equalsIgnoreCase(texto.trim()) || u.abreviatura.equalsIgnoreCase(texto.trim()))
				return u;
		return null;
	}
}
