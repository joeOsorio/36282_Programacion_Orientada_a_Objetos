/* ****************************************************************
 * Taller 2: Interfaz IPlatillo
 * Contrato que cumple cualquier platillo vendido en el POS.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************************** */

public interface IPlatillo {
	String getNombre();

	double getPrecio();

	String preparar();
}
