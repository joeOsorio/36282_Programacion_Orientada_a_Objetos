/* ****************************************************************
 * Proyecto Final: Interfaz IGuardable
 * Todo lo que se pueda escribir en el archivo de texto sabe
 * convertirse en UNA linea con campos separados por ';'.
 * Cada subclase de Producto agrega sus propios campos (polimorfismo).
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public interface IGuardable {
	String SEPARADOR = ";";

	String toLinea();
}
