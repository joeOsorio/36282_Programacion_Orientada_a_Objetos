public class ReglaLongitudMinima implements IValidar {
    private int minimo;

    public ReglaLongitudMinima(int minimo) {
        this.minimo = 1;
        setMinimo(minimo);
    }

    @Override
    public boolean validar(String texto) {
        return texto != null && texto.length() >= minimo;
    }

    /* Setters */
    public boolean setMinimo(int minimo) {
        if (minimo < 1) {
            System.out.println("Error: la longitud minima debe ser mayor a 0.");
            return false;
        }
        this.minimo = minimo;
        return true;
    }

    /* Getters */
    public int getMinimo() {
        return minimo;
    }

    @Override
    public String toString() {
        return "Longitud minima (" + minimo + ")";
    }
}