/* @Date: Octuber/2026 
 * Un numero de tarjeta al ocultarlo se muestra solo los ultimos 4 digitos de la tarjeta.
 * digitos: 1234-5678-321-6543 -> ****-****-****-6543
 * */

public class Tarjeta implements IOcultar {
    public static final int MIN_DIGITOS = 13;
    public static final int MAX_DIGITOS = 19;

    private String numero;

    /* Guardar estandarizado, todo pegado sin guinones. */
    public Tarjeta(String numero) {
        this.numero = "";
        setNumero(numero);
    }

    /* Interfaz */
    @Override
    public String ocultar() {
        if (numero.length() < 4)
            return numero;
        return "****-****-****-" + numero.substring(numero.length() - 4);
    }

    /* Quitar guiones y espacios; lo que quede debe ser solo digitos */
    public static String limpiar(String numero) {
        if (numero == null)
            return "";
        return numero.replace("-", "").replace(" ", "");
    }

    public static boolean esValido(String numero) {
        String digitos = limpiar(numero);
        if (digitos.length() < MIN_DIGITOS || digitos.length() > MAX_DIGITOS)
            return false;
        for (int i = 0; i < digitos.length(); i++) {
            if (!Character.isDigit(digitos.charAt(i)))
                return false;
        }
        return true;
    }

    /* Setters */
    public boolean setNumero(String numero) {
        if (!esValido(numero)) {
            System.out.println("Error: tarjeta invalida(" + numero + "), debe tener " + MIN_DIGITOS + " a "
                    + MAX_DIGITOS + "digitos.");
            return false;
        }
        this.numero = limpiar(numero);
        return true;
    }

    /* Getters */
    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Tarjeta: " + ocultar();
    }
}
