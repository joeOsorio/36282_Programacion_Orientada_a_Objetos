/* @Date: Octubre/2026 
*  @Coments: Al ocultar el correo se tapa el usuario (Lo que va  antes del @) con un * por cada caracter y se deja el dominio.*/
public class Email implements IOcultar {
    private String correo;

    public Email(String correo) {
        this.correo = "";
        setCorreo(correo); /* Utilizo el acccesador porque ahi implemnto logica */
    }

    /* ******************* INTERFAZ ******************* */
    /* omar.zamarron@uabc.edu.mx -> *************@uabc.edu.mx */

    @Override
    public String ocultar() {
        int arroba = correo.indexOf('@');
        if (arroba < 0)
            return correo;
        return "*".repeat(arroba) + correo.substring(arroba);
    }

    /* ******************* Validacion ******************* */
    /* Un solo @, usuario vacio,dominio con punto y sin espacios */
    public static boolean esValido(String correo) {
        if (correo == null || correo.contains(" "))
            return false;
        int arroba = correo.indexOf('@');
        if (arroba <= 0 || arroba != correo.lastIndexOf("@")) {
            return false;
        }
        String dominio = correo.substring(arroba + 1);
        int punto = dominio.lastIndexOf('.');
        return punto > 0 && punto < dominio.length() - 1;
    }

    /* ******************* Setters ******************* */

    public boolean setCorreo(String correo) {
        if (!esValido(correo)) {
            System.out.println("Error: Correo invalido ("
                    + correo + ").");
            return false;
        }
        this.correo = correo.trim();
        return true;
    }
    /* ******************* Getters ******************* */

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "Email: " + ocultar();
    }
}