package Prueba;
/* **************************************************
 * Laboratorio 4: Clase Libreria, gestion de libros
 * @Author: J03_0$0
 * @Date: Agust/2026
 * **************************************************/

public class Libreria {

	/* Arreglo de libros disponibles */
	private Libro[] libros = new Libro[5];
	private int contador = 0;

	/* *********************** Registrar ************************/
	public boolean registrarLibro(String titulo, String autor, String editorial, int ano, int paginas) {
		if (contador >= libros.length) {
			return false;
		}
		libros[contador++] = new Libro(titulo, autor, editorial, ano, paginas);
		return true;
	}

	/* *********************** Buscar ************************/
	public Libro buscarLibro(String titulo) {
		for (int i = 0; i < contador; i++) {
			if (libros[i].getTitulo().equalsIgnoreCase(titulo)) {
				return libros[i];
			}
		}
		return null;
	}

	/* *********************** Editar ************************/
	public boolean editarLibro(String tituloBuscado, String titulo, String autor, String editorial, int ano,
			int paginas) {
		Libro l = buscarLibro(tituloBuscado);
		if (l == null) {
			return false;
		}
		l.setTitulo(titulo);
		l.setAutor(autor);
		l.setEditorial(editorial);
		l.setAno(ano);
		l.setPaginas(paginas);
		return true;
	}

	/* *********************** Eliminar ************************/
	public boolean eliminarLibro(String titulo) {
		for (int i = 0; i < contador; i++) {
			if (libros[i].getTitulo().equalsIgnoreCase(titulo)) {
				for (int j = i; j < contador - 1; j++) {
					libros[j] = libros[j + 1];
				}
				libros[contador - 1] = null;
				contador--;
				return true;
			}
		}
		return false;
	}

	/* *********************** Mostrar informacion ************************/
	public void mostrarInformacion() {
		if (contador == 0) {
			System.out.println("\nNo hay libros registrados.");
			return;
		}
		System.out.printf("%-20s%-20s%-25s%-6s%-8s%n", "Titulo", "Autor", "Editorial", "Ano", "Paginas");
		for (int i = 0; i < contador; i++) {
			System.out.println(libros[i]);
		}
	}
}
