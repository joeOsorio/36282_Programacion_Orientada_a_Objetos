import java.util.InputMismatchException; /* Para el try catch */
import java.util.Scanner;

/* **************************************************
 * Laboratorio 6: Programa principal
 * Orquesta la clase Veterinaria (que a su vez orquesta
 * a Doctor y Mascota). La Veterinaria NO es el main.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Veterinaria veterinaria = new Veterinaria();

    public static void main(String[] args) {
        int opcion;

        System.out.println("==================================================");
        System.out.println("   VETERINARIA - SISTEMA DE GESTION DE CONSULTAS   ");
        System.out.println("==================================================");
        System.out.println("(Modo demo) Super usuario -> usuario: " + Veterinaria.USUARIO_SUPER
                + "  clave: " + Veterinaria.CLAVE_SUPER);

        do {
            imprimirEstadoSesion();
            /*
             * System.out.println("\n1  -\tIniciar sesion como super usuario");
             * System.out.println("2  -\tIniciar sesion como doctor");
             * System.out.println("3  -\tCerrar sesion");
             * System.out.println("4  -\tContratar doctor");
             * System.out.println("5  -\tDespedir doctor");
             * System.out.println("6  -\tSuspender / reactivar doctor");
             * System.out.println("7  -\tDar de alta mascota");
             * System.out.println("8  -\tDar de baja mascota");
             * System.out.println("9  -\tDar diagnostico a una mascota");
             * System.out.println("10 -\tMostrar doctores");
             * System.out.println("11 -\tMostrar mascotas");
             * System.out.println("12 -\tCargar datos de prueba");
             */
            System.out.printf("\nTest\t");
            System.out.println("0  -\tSalir del programa");
            System.out.printf("\nOpcion:\t");

            opcion = leerEntero();
            switch (opcion) {
                case 13:
                    test();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }
        } while (opcion != 0);

        input.close();
    }

    /* *********************** Sesion ************************/
    private static void imprimirEstadoSesion() {
        if (veterinaria.haySesionSuperUsuario()) {
            System.out.println("\n[Sesion activa: SUPER USUARIO]");
        } else if (veterinaria.haySesionDoctor()) {
            System.out.println("\n[Sesion activa: Doctor " + veterinaria.getDoctorActual().getNombre() + "]");
        } else {
            System.out.println("\n[Sin sesion iniciada]");
        }
    }
    /*
     * *********************** Datos de prueba (para probar rapido)
     ************************/

    /*
     * *********************** Prueba automatica de todos los metodos
     ************************/
    private static void test() {
        System.out.println("\n========== TEST: todas las operaciones ==========");

        Veterinaria veterinariaPrueba = new Veterinaria();

        System.out.println("\n---------- 1) Login de super usuario ----------");
        System.out.println("Credenciales invalidas (debe rechazar): "
                + veterinariaPrueba.loginSuperUsuario("hacker", "1234"));
        System.out.println("Credenciales validas (debe aceptar): "
                + veterinariaPrueba.loginSuperUsuario(Veterinaria.USUARIO_SUPER, Veterinaria.CLAVE_SUPER));

        System.out.println("\n---------- 2) Contratar doctores ----------");

        Doctor d1 = veterinariaPrueba.contratarDoctor("Yuliana Flores", "+16641112233", "Yuls", "1234");
        System.out.println("Contratado: " + d1);
        Doctor d2 = veterinariaPrueba.contratarDoctor("Zaide Quintero", "+526642223344", "Pato", "1234");
        System.out.println("Contratado: " + d2);
        Doctor d3 = veterinariaPrueba.contratarDoctor("Andrea Mercado", "+526643334455", "Andy", "1234");
        System.out.println("Contratado: " + d3);

        System.out.println("\nContratar con usuario repetido (invalido, debe rechazar):");
        veterinariaPrueba.contratarDoctor("Otro Doctor", "664-000-0000", "Yuls", "0000");

        System.out.println("\nContratar con nombre vacio (invalido, debe rechazar):");
        veterinariaPrueba.contratarDoctor("", "664-000-0000", "otro", "0000");

        System.out.println("\n---------- 3) Sin sesion de super usuario no se puede contratar ----------");
        Veterinaria veterinariaSinSesion = new Veterinaria();
        veterinariaSinSesion.logout();
        System.out.println("Intentar contratar sin login (invalido, debe rechazar): "
                + (veterinariaSinSesion.contratarDoctor("X", "000", "x", "0000") == null));

        System.out.println("\n---------- 4) Login de doctores ----------");
        System.out.println("Clave incorrecta (invalido, debe rechazar): "
                + veterinariaPrueba.loginDoctor("Yuls", "clave-mala"));
        System.out.println("Credenciales validas (debe aceptar): "
                + veterinariaPrueba.loginDoctor("Yuls", "1234"));

        System.out.println("\n---------- 5) Sin sesion de doctor no se pueden gestionar mascotas ----------");
        Veterinaria vetSinDoctor = new Veterinaria();
        vetSinDoctor.loginSuperUsuario(Veterinaria.USUARIO_SUPER, Veterinaria.CLAVE_SUPER);
        vetSinDoctor.contratarDoctor("Yarely Flores", "664-111-2233", "Acidita", "1234");
        System.out.println("Dar de alta sin doctor logueado (invalido, debe rechazar): "
                + (vetSinDoctor.darDeAltaMascota("X", "Perro", 1, "Y", "000") == null));

        System.out.println("\n---------- 6) Dar de alta mascotas (doctor Yuls logueada) ----------");
        System.out.println("Doctor actual : " + veterinariaPrueba.getDoctorActual());

        Mascota m1 = veterinariaPrueba.darDeAltaMascota("Aruma", "Perro", 3, "Xcaret Palma", "664-444-5566");
        System.out.println("Registrada: " + m1);
        Mascota m2 = veterinariaPrueba.darDeAltaMascota("Sky", "Lobo", 2, "Daniela Rosa", "664-555-6677");
        System.out.println("Registrada: " + m2);
        Mascota m3 = veterinariaPrueba.darDeAltaMascota("Caguama", "Conejo", 5, "Hiromi Ortiz", "664-444-5566");
        System.out.println("Registrada: " + m3);
        Mascota m4 = veterinariaPrueba.darDeAltaMascota("Joy", "Gato", 1, "Samantha Andrade", "664-666-7788");
        System.out.println("Registrada: " + m4);

        System.out.println("\nDar de alta con nombre vacio (invalido, debe rechazar):");
        veterinariaPrueba.darDeAltaMascota("", "Perro", 1, "Nadie", "000");

        System.out.println("\n---------- 7) Suspender doctor y probar que ya no puede operar ----------");
        veterinariaPrueba.logout(); /* Yuls sale, super usuario tiene que suspenderla */
        veterinariaPrueba.loginSuperUsuario(Veterinaria.USUARIO_SUPER, Veterinaria.CLAVE_SUPER);
        veterinariaPrueba.suspenderDoctor(d1.getNumeroEmpleado());
        System.out.println("Yuls suspendida: " + d1.estaSuspendido()); /* Maldita canina */
        veterinariaPrueba.logout();

        veterinariaPrueba.loginDoctor("Yuls", "1234"); /* Suspendida SI puede loguearse */
        System.out.println("Dar diagnostico estando suspendida (invalido, debe rechazar): "
                + veterinariaPrueba.darDiagnostico(m1.getId(), "Diagnostico no deberia guardarse"));

        System.out.println("\n---------- 8) Reactivar doctor y volver a intentar ----------");
        veterinariaPrueba.logout();
        veterinariaPrueba.loginSuperUsuario(Veterinaria.USUARIO_SUPER, Veterinaria.CLAVE_SUPER);
        veterinariaPrueba.suspenderDoctor(d1.getNumeroEmpleado());
        System.out.println("Yuls suspendida: " + d1.estaSuspendido() + " (ya reactivada)");
        veterinariaPrueba.logout();

        veterinariaPrueba.loginDoctor("Yuls", "1234");
        System.out.println("\n---------- 9) Dar diagnostico (ya reactivada) ----------");
        System.out.println("Diagnostico a mascota inexistente (invalido, debe rechazar): "
                + veterinariaPrueba.darDiagnostico(9999, "X"));
        System.out
                .println("Diagnostico valido: " + veterinariaPrueba.darDiagnostico(m1.getId(), "Sano, vacuna anual."));
        System.out.println("Diagnostico valido: "
                + veterinariaPrueba.darDiagnostico(m2.getId(), "Infeccion leve en el oido."));

        System.out.println("\n---------- 10) Historial de atenciones del doctor (asociacion) ----------");
        d1.imprimirMascotasAtendidas();

        System.out.println("\n---------- 11) Dar de baja mascota ----------");
        System.out.println("Id inexistente (invalido, debe rechazar): " + veterinariaPrueba.darDeBajaMascota(9999));
        System.out.println("Id valido: " + veterinariaPrueba.darDeBajaMascota(m3.getId()));

        System.out.println("\n---------- 12) Despedir doctor ----------");
        System.out.println("Se cierra sesion de Yuls para poder despedirla desde super usuario:");
        veterinariaPrueba.logout();
        veterinariaPrueba.loginSuperUsuario(Veterinaria.USUARIO_SUPER, Veterinaria.CLAVE_SUPER);
        System.out.println("Numero inexistente (invalido, debe rechazar): " + veterinariaPrueba.despedirDoctor(9999));
        System.out.println("Numero valido (a Luis, que no tiene sesion abierta): "
                + veterinariaPrueba.despedirDoctor(d2.getNumeroEmpleado()));

        System.out.println("\n---------- 13) Estado final ----------");
        System.out.println("Doctores:");
        veterinariaPrueba.imprimirDoctores();
        System.out.println("Mascotas:");
        veterinariaPrueba.imprimirMascotas();

        System.out.println("\n---------- 14) Logout ----------");
        veterinariaPrueba.logout();
        System.out.println("Hay sesion de super usuario despues del logout (debe ser false): "
                + veterinariaPrueba.haySesionSuperUsuario());

        System.out.println("\n========== FIN DEL TEST ==========");
    }

    /* *********************** Lectura segura de datos ************************/
    private static int leerEntero() {
        while (true) {
            try {
                int valor = input.nextInt();
                input.nextLine(); /* importante limpiar el buffer. */
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Valor invalido:\t");
                input.nextLine();
            }
        }
    }
}