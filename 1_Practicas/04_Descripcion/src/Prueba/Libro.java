package Prueba;
public class Libro {
	private String titulo;
	private String autor;
	private String editorial;
	private int ano;
	private int paginas;

	/* *********************** Constructores ************************/
	Libro(String titulo, String autor, String editorial, int ano, int paginas) {
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.ano = ano;
		this.paginas = paginas;

	}

	/* *********************** Setters ************************/

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	/* **************** Getters ********************/
	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public int getAno() {
		return ano;
	}

	public int getPaginas() {
		return paginas;
	}

	@Override
	public String toString() {
		return String.format("%-20s%-20s%-25s%-6d%-8d", titulo, autor, editorial, ano, paginas);
	}
}
