public class FiltroLongitud implements Filtro {
	public static final int MIN_PALABRAS = 5;
	public static final int MAX_PALABRAS = 255;

	@Override
	public String getNombre() {
		return "FiltroLongitud";
	}

	@Override
	public Veredicto revisar(Resena r) {
		int n = r.contarPalabras();
		if (n < MIN_PALABRAS || n > MAX_PALABRAS)
			return Veredicto.RECHAZADA;
		return Veredicto.APROBADA;
	}
}
