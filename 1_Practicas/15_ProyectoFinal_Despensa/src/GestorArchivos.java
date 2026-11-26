import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import utils.Lista;

/* ****************************************************************
 * Proyecto Final: Clase GestorArchivos
 * Persistencia en archivos de TEXTO (UTF-8). Guardar = cada producto
 * escribe su linea con toLinea() (polimorfismo). Cargar = segun el
 * primer campo (TIPO) se crea la subclase correcta (fabrica).
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public final class GestorArchivos {
	public static final String COMENTARIO = "#";

	private GestorArchivos() {
	}

	/* *********************** Despensa ************************/
	public static boolean guardarDespensa(Despensa despensa, File archivo) {
		crearCarpeta(archivo);
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
			bw.write("# Despensa: " + despensa.getNombre() + " | guardado " + ahora());
			bw.newLine();
			bw.write("# TIPO;nombre;cantidad;minima;UNIDAD;[fecha yyyy-MM-dd | peligroso]");
			bw.newLine();
			for (var n = despensa.getProductos().begin(); n != null; n = n.sig) {
				bw.write(n.valor.toLinea());
				bw.newLine();
			}
			return true;
		} catch (IOException e) {
			System.out.println("Error: no se pudo guardar " + archivo + " (" + e.getMessage() + ")");
			return false;
		}
	}

	/* Si el archivo no existe regresa una despensa vacia; las lineas malas se saltan */
	public static Despensa cargarDespensa(String nombre, File archivo) {
		Despensa d = new Despensa(nombre);
		if (!archivo.exists())
			return d;
		int malas = 0;
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				linea = linea.trim();
				if (linea.isEmpty() || linea.startsWith(COMENTARIO))
					continue;
				Producto p = crearDesdeLinea(linea);
				if (p == null || !d.agregar(p))
					malas++;
			}
		} catch (IOException e) {
			System.out.println("Error: no se pudo leer " + archivo + " (" + e.getMessage() + ")");
		}
		if (malas > 0)
			System.out.println("Aviso: se ignoraron " + malas + " linea(s) con formato incorrecto");
		return d;
	}

	/* Fabrica: el TIPO decide que subclase se crea (upcasting a Producto) */
	public static Producto crearDesdeLinea(String linea) {
		if (linea == null)
			return null;
		String[] c = linea.split(IGuardable.SEPARADOR, -1);
		if (c.length < 5)
			return null;
		try {
			String nombre = c[1].trim();
			double cantidad = Double.parseDouble(c[2].trim());
			double minima = Double.parseDouble(c[3].trim());
			Unidad unidad = Unidad.desdeTexto(c[4]);
			if (nombre.isEmpty() || unidad == null || cantidad < 0 || minima < 0)
				return null;

			switch (c[0].trim().toUpperCase()) {
				case Perecedero.TIPO:
					if (c.length < 6)
						return null;
					return new Perecedero(nombre, cantidad, minima, unidad, LocalDate.parse(c[5].trim()));
				case NoPerecedero.TIPO:
					return new NoPerecedero(nombre, cantidad, minima, unidad);
				case Limpieza.TIPO:
					boolean peligroso = c.length > 5 && Boolean.parseBoolean(c[5].trim());
					return new Limpieza(nombre, cantidad, minima, unidad, peligroso);
				default:
					return null;
			}
		} catch (NumberFormatException | DateTimeParseException e) {
			return null;
		}
	}

	/* *********************** Lista del super ************************/
	/* Archivo legible para abrirlo en el cel o imprimirlo */
	public static boolean exportarLista(ListaSuper lista, File archivo) {
		crearCarpeta(archivo);
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
			bw.write("LISTA DEL SUPER - " + ahora());
			bw.newLine();
			bw.write("==========================================");
			bw.newLine();
			Lista<String> r = lista.renglones();
			if (r.estaVacia()) {
				bw.write("(no hace falta nada)");
				bw.newLine();
			}
			for (var n = r.begin(); n != null; n = n.sig) {
				bw.write(n.valor);
				bw.newLine();
			}
			return true;
		} catch (IOException e) {
			System.out.println("Error: no se pudo exportar " + archivo + " (" + e.getMessage() + ")");
			return false;
		}
	}

	/* *********************** Utilidades ************************/
	private static void crearCarpeta(File archivo) {
		File padre = archivo.getAbsoluteFile().getParentFile();
		if (padre != null && !padre.exists())
			padre.mkdirs();
	}

	private static String ahora() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}
}
