public class Password implements IOcultar {
    private String clave;

    public Password(String clave) {
        this.clave = "";
        setClave(clave);
    }

    @Override
    public String ocultar() {
        return "*".repeat(clave.length());
    }

    public static boolean esValido(String clave) {
        /* Revisar que no sea nulo, sea vacio, y solo tenga espacios */
        return clave != null && !clave.isEmpty() && !clave.contains(" ");
    }

    public boolean setClave(String clave) {
        if (!esValido(clave)) {
            System.out.println("Error: la clave no puede estar vacia ni tener espacios.");
            return false;
        }
        this.clave = clave;
        return true;
    }

    public String getClave() {
        return clave;
    }

    @Override
    public String toString() {
        return "Password:" + ocultar();
    }
}