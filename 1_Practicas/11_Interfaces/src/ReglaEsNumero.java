public class ReglaEsNumero implements IValidar {
    @Override
    public boolean validar(String texto) {
        if (texto == null)
            return false;
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Es numerito";
    }
}
