public enum Veredicto {
	APROBADA, REVISION_MANUAL, RECHAZADA;

	public boolean esMasSeveroQue(Veredicto otro) {
		return this.ordinal() > otro.ordinal();
	}
}
