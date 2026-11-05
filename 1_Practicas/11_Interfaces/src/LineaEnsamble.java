/* @Date: October/2026 
 * @Comments: Guardamos las reglas (Arreglo tipo Ivalidar) y pasa la cadena por cada una. Solo ve la interfaz, no conoce como funciona*/

public class LineaEnsamble {
    public static final int MAX_REGLAS = 10;
    private IValidar[] reglas;
    private int contador;

    /* Solo para resaltar lo impotante */
    public static final String ANSI_ROJO = "\u001B[31m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m"; // Restablece por defecto

    public LineaEnsamble() {
        reglas = new IValidar[MAX_REGLAS];
        contador = 0;
    }

    /* Reglas */

    public boolean agregarRegla(IValidar regla) {
        if (regla == null) {
            System.out.println("Error: la regla no puede ser null.");
            return false;
        }
        if (contador >= MAX_REGLAS) {
            System.out.println("Error: la linea ya tiene " + MAX_REGLAS + " reglas.");
            return false;
        }
        reglas[contador++] = regla;
        return true;
    }

    /* Validacion */
    public boolean validar(String texto) {
        for (int i = 0; i < contador; i++) {
            if (!reglas[i].validar(texto)) {
                return false;
            }
        }
        return true;
    }

    /* Muesta si fue aceptada por cada estacion. */
    public void reporte(String texto) {
        String mostrar = (texto == null) ? "null" : "\"" + texto + "\"";
        System.out.println("Cadena " + mostrar + ":");
        for (int i = 0; i < contador; i++) {
            boolean paso = reglas[i].validar(texto);
            System.out.printf(" %-24s %s%n", reglas[i],
                    paso ? (ANSI_VERDE + "[PASA]" + ANSI_RESET) : (ANSI_ROJO + "[NO PASA]" + ANSI_RESET));
        }
        System.out.println(" Resultado: "
                + (validar(texto) ? (ANSI_VERDE + "ACEPTADA" + ANSI_RESET) : (ANSI_ROJO + "RECHAZADA" + ANSI_RESET)));
    }

    /* Getters */

    public int getContador() {
        return contador;
    }

    public IValidar getRegla(int indice) {
        if (indice < 0 || indice >= contador) {

            System.out.println("Error: no existe la regla " + indice + ".");
            return null;
        }
        return reglas[indice];
    }

}