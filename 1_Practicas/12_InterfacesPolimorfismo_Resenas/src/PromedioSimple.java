public class PromedioSimple implements CalcularCalificacion {

	@Override
	public String getNombre() {
		return "Promedio simple";
	}

	@Override
	public double calcular(Resena[] aprobadas, String sku) {
		double suma = 0;
		int n = 0;
		for (Resena r : aprobadas) {
			if (r == null || !r.getSku().equals(sku) || r.getVeredicto() != Veredicto.APROBADA)
				continue;
			suma += r.getCalificacion();
			n++;
		}
		return (n == 0) ? 0.0 : suma / n;
	}
}
