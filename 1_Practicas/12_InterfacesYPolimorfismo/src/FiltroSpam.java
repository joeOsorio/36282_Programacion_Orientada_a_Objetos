public class FiltroSpam implements Filtro {
	public static final double LIMITE_MAYUSCULAS = 0.65;

	@Override
	public String getNombre() {
		return "FiltroSpam";
	}

	public double porcentajeMayusculas(String texto) {
		int letras = 0, mayus = 0;
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (Character.isLetter(c)) {
				letras++;
				if (Character.isUpperCase(c))
					mayus++;
			}
		}
		return (letras == 0) ? 0.0 : (double) mayus / letras;
	}

	@Override
	public Veredicto revisar(Resena r) {
		if (porcentajeMayusculas(r.getTexto()) > LIMITE_MAYUSCULAS)
			return Veredicto.REVISION_MANUAL;
		return Veredicto.APROBADA;
	}
}
