import utils.Lista;

/* ****************************************************************
 * Proyecto Final: Clase ListaSuper
 * Se arma sola preguntandole a cada producto necesitaReponer() y
 * cantidadAComprar() (cada subclase responde distinto) y ademas
 * acepta cosas extra que el usuario escriba a mano.
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class ListaSuper {
	private Lista<Producto> pendientes;
	private Lista<String> extras;

	public ListaSuper() {
		pendientes = new Lista<>();
		extras = new Lista<>();
	}

	/* *********************** Generar ************************/
	/* Rehace la parte automatica; los extras se conservan */
	public int generar(Despensa despensa) {
		pendientes = despensa.porReponer();
		return pendientes.getSize();
	}

	/* *********************** Extras ************************/
	public boolean agregarExtra(String texto) {
		if (texto == null || texto.trim().isEmpty()) {
			System.out.println("Error: escribe que quieres agregar");
			return false;
		}
		extras.append(texto.trim());
		return true;
	}

	/* numero = el que se ve en pantalla (empieza en 1) */
	public boolean quitarExtra(int numero) {
		if (numero < 1 || numero > extras.getSize()) {
			System.out.println("Error: no hay extra con el numero " + numero);
			return false;
		}
		return extras.eliminar(numero - 1);
	}

	/* *********************** Salida ************************/
	/* Renglones listos para imprimir o escribir en el .txt */
	public Lista<String> renglones() {
		Lista<String> r = new Lista<>();
		for (var n = pendientes.begin(); n != null; n = n.sig) {
			Producto p = n.valor;
			String motivo = p.estaVencido() ? "vencido" : (p.getCantidad() == 0 ? "agotado" : "queda poco");
			r.append(String.format("[ ] %-18s %6s %-4s (%s)", p.getNombre(),
					Producto.formato(p.cantidadAComprar()), p.getUnidad().getAbreviatura(), motivo));
		}
		for (var n = extras.begin(); n != null; n = n.sig)
			r.append(String.format("[ ] %-18s %11s (extra)", n.valor, ""));
		return r;
	}

	public boolean estaVacia() {
		return pendientes.estaVacia() && extras.estaVacia();
	}

	/* Getters */
	public Lista<Producto> getPendientes() {
		return pendientes;
	}

	public Lista<String> getExtras() {
		return extras;
	}
}
