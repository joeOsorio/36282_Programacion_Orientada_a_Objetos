/* **************************************************
 * Actividad 10: Clase Organo es una clase padre
 * Clase base de todos los organos de la persona. NO es
 * abstracta ( El profe asi lo pidio): su efecto por defecto es no hacer nada,
 * y cada organo hijo lo sobrescribe con @Override.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Organo {

	/* protected: para que las clases hijas lo usen directamente */
	protected String nombre;

	public Organo(String nombre) {
		this.nombre = nombre;
	}

	/* *********************** Polimorfismo ************************/
	/*
	 * Version por defecto: no hace nada. Las hijas la sobrescriben; el que no la
	 * sobrescribe (Intestino) se queda con esta.
	 */
	public void aplicarEfecto(Persona persona, Microbio microbio) {
		System.out.println("\t   " + nombre + ": no hace nada.");
	}

	public String getDescripcion() {
		return "No hace nada";
	}

	/* Getters */
	public String getNombre() {
		return nombre;
	}

	/* *********************** Informacion ************************/
	@Override
	public String toString() {
		/*
		 * getDescripcion() es polimorfico: sale la version del organo real asi que
		 * aplica para todos los organos
		 */
		return String.format("%-10s -> %s", nombre, getDescripcion());
	}
}
