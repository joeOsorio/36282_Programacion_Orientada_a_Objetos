public class PromedioBayesiano implements CalcularCalificacion {
	public static final double K = 3.0;

	@Override
	public String getNombre() {
		return "Promedio bayesiano";
	}

	public double confianza(Resena r) {
		int palabras = r.contarPalabras();
		return palabras / (palabras + K);
	}

	@Override
	public double calcular(Resena[] aprobadas, String sku) {
		double suma = 0;
		int n = 0;
		for (Resena r : aprobadas) {
			if (r == null || !r.getSku().equals(sku) || r.getVeredicto() != Veredicto.APROBADA)
				continue;
			suma += r.getCalificacion() * confianza(r);
			n++;
		}
		return (n == 0) ? 0.0 : suma / n;
	}
}
