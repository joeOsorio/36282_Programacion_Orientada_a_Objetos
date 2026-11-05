public class FiltroGroserias implements Filtro {
	private String[] palabras;

	public FiltroGroserias() {
		this(new String[] { "idiota", "estupido", "imbecil", "pendejo", "mierda", "basura" });
	}

	public FiltroGroserias(String[] palabras) {
		this.palabras = new String[palabras.length];
		for (int i = 0; i < palabras.length; i++)
			this.palabras[i] = normalizar(palabras[i]);
	}

	/* minusculas y sin acentos: "ESTÚPIDO" -> "estupido" */
	/* letras con tilde y su equivalente (misma posicion en ambos arreglos) */
	private static final char[] TILDE = { 'á', 'é', 'í', 'ó', 'ú', 'ü', 'ñ' };
	private static final char[] EQUIVALENTE = { 'a', 'e', 'i', 'o', 'u', 'u', 'n' };

	private static String normalizar(String s) {
		String texto = s.toLowerCase(); /* primero a minusculas, asi 'Ú' ya es 'ú' y nos arromos mucho */
		for (int i = 0; i < TILDE.length; i++)
			texto = texto.replace(TILDE[i], EQUIVALENTE[i]);
		return texto;
	}

	@Override
	public String getNombre() {
		return "FiltroGroserias";
	}

	@Override
	public Veredicto revisar(Resena r) {
		String[] tokens = normalizar(r.getTexto()).split("[^a-z0-9]+");
		for (String t : tokens)
			for (String g : palabras)
				if (t.equals(g))
					return Veredicto.RECHAZADA;
		return Veredicto.APROBADA;
	}
}
