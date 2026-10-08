/* **************************************************
 * Es un inutil no hace nada: NO sobrescribe aplicarEfecto() ni
 * getDescripcion(), asi que usa las de Organo.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Intestino extends Organo {

	public Intestino() {
		super("Intestino");
	}

	/*
	 * Solo para fines practicos: metodo propio que Organo no tiene (requiere
	 * downcasting)
	 */
	public String SoloExistir() {
		return nombre + ": solo existe... y hacer que la persona se eche peditos.";
	}
}
