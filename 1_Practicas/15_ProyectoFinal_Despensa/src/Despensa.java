import utils.Lista;

/* ****************************************************************
 * Proyecto Final: Clase Despensa
 * Guarda todos los productos en una Lista<Producto> (cualquier
 * subclase cabe por upcasting). La comparten el hilo del menu y
 * HiloCaducidad, por eso TODOS los metodos que tocan la lista son
 * synchronized: solo un hilo a la vez puede estar adentro.
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class Despensa {
	private String nombre;
	private Lista<Producto> productos;

	public Despensa(String nombre) {
		this.nombre = nombre;
		this.productos = new Lista<>();
	}

	/* *********************** Altas y bajas ************************/
	public synchronized boolean agregar(Producto p) {
		if (p == null) {
			System.out.println("Error: producto invalido");
			return false;
		}
		if (indiceDe(p.getNombre()) != -1) {
			System.out.println("Error: ya existe \"" + p.getNombre() + "\" (usa Reabastecer)");
			return false;
		}
		productos.append(p);
		return true;
	}

	public synchronized boolean eliminar(String nombre) {
		int i = indiceDe(nombre);
		if (i == -1) {
			System.out.println("Error: no existe \"" + nombre + "\"");
			return false;
		}
		return productos.eliminar(i);
	}

	/* *********************** Busqueda ************************/
	/* Recorre los nodos; regresa la posicion o -1 */
	private int indiceDe(String nombre) {
		if (nombre == null)
			return -1;
		int i = 0;
		for (var n = productos.begin(); n != null; n = n.sig, i++)
			if (n.valor.getNombre().equalsIgnoreCase(nombre.trim()))
				return i;
		return -1;
	}

	public synchronized Producto buscar(String nombre) {
		int i = indiceDe(nombre);
		return (i == -1) ? null : productos.get(i);
	}

	/* *********************** Movimientos ************************/
	public synchronized boolean consumir(String nombre, double cuanto) {
		Producto p = buscar(nombre);
		if (p == null) {
			System.out.println("Error: no existe \"" + nombre + "\"");
			return false;
		}
		return p.consumir(cuanto);
	}

	public synchronized boolean reabastecer(String nombre, double cuanto) {
		Producto p = buscar(nombre);
		if (p == null) {
			System.out.println("Error: no existe \"" + nombre + "\"");
			return false;
		}
		return p.reabastecer(cuanto);
	}

	/* *********************** Consultas (polimorficas) ************************/
	/* estaVencido() lo responde cada subclase a su manera */
	public synchronized Lista<Producto> vencidos() {
		Lista<Producto> r = new Lista<>();
		for (var n = productos.begin(); n != null; n = n.sig)
			if (n.valor.estaVencido())
				r.append(n.valor);
		return r;
	}

	/* Solo los Perecedero pueden caducar: se revisa con instanceof */
	public synchronized Lista<Producto> porVencer(int dias) {
		Lista<Producto> r = new Lista<>();
		for (var n = productos.begin(); n != null; n = n.sig)
			if (n.valor instanceof Perecedero && ((Perecedero) n.valor).venceEnMenosDe(dias))
				r.append(n.valor);
		return r;
	}

	public synchronized Lista<Producto> porReponer() {
		Lista<Producto> r = new Lista<>();
		for (var n = productos.begin(); n != null; n = n.sig)
			if (n.valor.necesitaReponer())
				r.append(n.valor);
		return r;
	}

	/* Copia de la lista: quien la recorra no choca con el otro hilo */
	public synchronized Lista<Producto> getProductos() {
		Lista<Producto> copia = new Lista<>();
		for (var n = productos.begin(); n != null; n = n.sig)
			copia.append(n.valor);
		return copia;
	}

	public synchronized void vaciar() {
		productos = new Lista<>();
	}

	/* Getters */
	public synchronized int getTotal() {
		return productos.getSize();
	}

	public String getNombre() {
		return nombre;
	}
}
