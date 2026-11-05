public class ReglaNoVacio implements IValidar {
    @Override
    public boolean validar(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "No vacio";
    }
}
