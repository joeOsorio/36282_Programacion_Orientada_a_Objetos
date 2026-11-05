public class Moderador {
	private Filtro[] filtros;
	private String filtroMasSevero;

	public Moderador(Filtro[] filtros) {
		this.filtros = filtros;
		this.filtroMasSevero = "-";
	}

	public Veredicto moderar(Resena r) {
		Veredicto peor = Veredicto.APROBADA;
		filtroMasSevero = "-";

		System.out.println("  Resena " + r);
		for (Filtro f : filtros) {
			Veredicto v = f.revisar(r);
			System.out.println("     - " + String.format("%-16s", f.getNombre()) + "-> " + v);
			if (v.esMasSeveroQue(peor)) {
				peor = v;
				filtroMasSevero = f.getNombre();
			}
		}
		r.setVeredicto(peor);
		r.setFiltroCausante(filtroMasSevero);
		System.out
				.println("     => " + peor + (peor == Veredicto.APROBADA ? "" : "  (causa: " + filtroMasSevero + ")"));
		return peor;
	}

	public String getFiltroMasSevero() {
		return filtroMasSevero;
	}

	public Filtro[] getFiltros() {
		return filtros;
	}
}
