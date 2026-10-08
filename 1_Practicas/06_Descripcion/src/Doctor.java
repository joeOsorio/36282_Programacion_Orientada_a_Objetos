/* **************************************************
 * Laboratorio 6: Clase Doctor
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Doctor {

    /* Informacion sensible (personal) */
    private String nombre;
    private String telefono;

    /* Informacion de trabajo */
    private int numeroEmpleado;
    private String usuario;
    private String clave;
    private boolean suspendido;

    /*
     * Asociacion (no composicion): solo guarda referencias a mascotas que ya
     * existen en la Veterinaria, no es dueno de ellas.
     */
    private Mascota[] mascotasAtendidas;
    private int contadorAtendidas;

    public Doctor(String nombre, String telefono, int numeroEmpleado, String usuario, String clave) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.numeroEmpleado = numeroEmpleado;
        this.usuario = usuario;
        this.clave = clave;
        this.suspendido = false;
        this.mascotasAtendidas = new Mascota[50];
        this.contadorAtendidas = 0;
    }

    /* Setters */
    public void setSuspendido(boolean suspendido) {
        this.suspendido = suspendido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /* Getters */
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean estaSuspendido() {
        return suspendido;
    }

    /* *********************** Otros metodos ************************/
    /* No hay getClave(): la clave es informacion sensible, solo se verifica. */
    public boolean verificarClave(String intento) {
        return clave.equals(intento);
    }

    public boolean registrarAtencion(Mascota mascota) {
        if (mascota == null) {
            System.out.println("Error: la mascota no existe.");
            return false;
        }
        if (contadorAtendidas >= mascotasAtendidas.length) {
            System.out.println("Error: el historial de este doctor ya esta lleno.");
            return false;
        }
        mascotasAtendidas[contadorAtendidas++] = mascota;
        return true;
    }

    public void imprimirMascotasAtendidas() {
        if (contadorAtendidas == 0) {
            System.out.println("Este doctor todavia no ha atendido ninguna mascota.");
            return;
        }
        for (int i = 0; i < contadorAtendidas; i++) {
            System.out.println("  " + mascotasAtendidas[i]);
        }
    }

    @Override
    public String toString() {
        String estado = (suspendido) ? "SUSPENDIDO" : "activo";
        return String.format("[Doctor #%d] %-15s | usuario: %-15s | tel. %-14s | estado: %10s",
                numeroEmpleado, nombre, usuario, telefono, estado);
    }
}