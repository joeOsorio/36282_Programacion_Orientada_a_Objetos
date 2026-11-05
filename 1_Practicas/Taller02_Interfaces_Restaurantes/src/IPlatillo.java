/* ****************************************************************
 * Taller 2: Interfaz IPlatillo
 * Contrato que cumple cualquier platillo vendido en el POS.
 * @Author: Joshua Osorio Osorio
 * @Date:   Octubre/2026
 * **************************************************************** */

public interface IPlatillo {
	String getNombre();
	double getPrecio();
	String preparar();
}
