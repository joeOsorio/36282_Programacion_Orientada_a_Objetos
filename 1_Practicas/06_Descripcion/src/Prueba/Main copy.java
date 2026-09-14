package Prueba;
import java.util.InputMismatchException; /* Para el try catch */
import java.util.Scanner;

import Doctor;
import Mascota;
import Veterinaria;

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
            System.out.println("\n1  -\tIniciar sesion como super usuario");
            System.out.println("2  -\tIniciar sesion como doctor");
            System.out.println("3  -\tCerrar sesion");
            System.out.println("4  -\tContratar doctor");
            System.out.println("5  -\tDespedir doctor");
            System.out.println("6  -\tSuspender / reactivar doctor");
            System.out.println("7  -\tDar de alta mascota");
            System.out.println("8  -\tDar de baja mascota");
            System.out.println("9  -\tDar diagnostico a una mascota");
            System.out.println("10 -\tMostrar doctores");
            System.out.println("11 -\tMostrar mascotas");
            System.out.println("12 -\tCargar datos de prueba");
            System.out.println("0  -\tSalir del programa");
            System.out.printf("\nOpcion:\t");

            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    loginSuperUsuario();
                    break;
                case 2:
                    loginDoctor();
                    break;
                case 3:
                    veterinaria.logout();
                    System.out.println("Sesion cerrada.");
                    break;
                case 4:
                    contratarDoctor();
                    break;
                case 5:
                    despedirDoctor();
                    break;
                case 6:
                    suspenderDoctor();
                    break;
                case 7:
                    darDeAltaMascota();
                    break;
                case 8:
                    darDeBajaMascota();
                    break;
                case 9:
                    darDiagnostico();
                    break;
                case 10:
                    System.out.println("\n---------- Doctores de la veterinaria ----------");
                    veterinaria.imprimirDoctores();
                    break;
                case 11:
                    System.out.println("\n---------- Mascotas registradas ----------");
                    veterinaria.imprimirMascotas();
                    break;
                case 12:
                    cargarDatosPrueba();
                    break;
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

    private static void loginSuperUsuario() {
        System.out.println("\n---------- Iniciar sesion como super usuario ----------");
        System.out.print("Usuario:\t");
        String usuario = input.nextLine();
        System.out.print("Clave:\t");
        String clave = input.nextLine();

        if (veterinaria.loginSuperUsuario(usuario, clave)) {
            System.out.println("Sesion de super usuario iniciada.");
        }
    }

    private static void loginDoctor() {
        System.out.println("\n---------- Iniciar sesion como doctor ----------");
        System.out.print("Usuario:\t");
        String usuario = input.nextLine();
        System.out.print("Clave:\t");
        String clave = input.nextLine();

        if (veterinaria.loginDoctor(usuario, clave)) {
            System.out.println("Sesion iniciada. Bienvenido/a, " + veterinaria.getDoctorActual().getNombre() + ".");
        }
    }

    /* *********************** Gestion de doctores ************************/
    private static void contratarDoctor() {
        System.out.println("\n---------- Contratar doctor ----------");
        System.out.print("Nombre:\t");
        String nombre = input.nextLine();
        System.out.print("Telefono:\t");
        String telefono = input.nextLine();
        System.out.print("Usuario (para su login):\t");
        String usuario = input.nextLine();
        System.out.print("Clave:\t");
        String clave = input.nextLine();

        Doctor nuevo = veterinaria.contratarDoctor(nombre, telefono, usuario, clave);
        if (nuevo != null) {
            System.out.printf("\nDoctor contratado con exito. Numero de empleado: %d\n", nuevo.getNumeroEmpleado());
        }
    }

    private static void despedirDoctor() {
        System.out.println("\n---------- Despedir doctor ----------");
        System.out.print("Numero de empleado:\t");
        int numeroEmpleado = leerEntero();

        if (veterinaria.despedirDoctor(numeroEmpleado)) {
            System.out.println("Doctor despedido con exito.");
        }
    }

    private static void suspenderDoctor() {
        System.out.println("\n---------- Suspender / reactivar doctor ----------");
        System.out.print("Numero de empleado:\t");
        int numeroEmpleado = leerEntero();

        Doctor doctor = veterinaria.buscarDoctorPorNumero(numeroEmpleado);
        if (veterinaria.suspenderDoctor(numeroEmpleado)) {
            String estado = doctor.estaSuspendido() ? "suspendido" : "reactivado";
            System.out.println("El doctor quedo " + estado + ".");
        }
    }

    /* *********************** Gestion de mascotas ************************/
    private static void darDeAltaMascota() {
        System.out.println("\n---------- Dar de alta mascota ----------");
        System.out.print("Nombre de la mascota:\t");
        String nombre = input.nextLine();
        System.out.print("Especie:\t");
        String especie = input.nextLine();
        System.out.print("Edad:\t");
        int edad = leerEntero();
        System.out.print("Nombre del dueno:\t");
        String nombreDueno = input.nextLine();
        System.out.print("Telefono del dueno:\t");
        String telefonoDueno = input.nextLine();

        Mascota nueva = veterinaria.darDeAltaMascota(nombre, especie, edad, nombreDueno, telefonoDueno);
        if (nueva != null) {
            System.out.printf("\nMascota registrada con exito. Id: %d\n", nueva.getId());
        }
    }

    private static void darDeBajaMascota() {
        System.out.println("\n---------- Dar de baja mascota ----------");
        System.out.print("Id de la mascota:\t");
        int id = leerEntero();

        if (veterinaria.darDeBajaMascota(id)) {
            System.out.println("Mascota dada de baja con exito.");
        }
    }

    private static void darDiagnostico() {
        System.out.println("\n---------- Dar diagnostico ----------");
        System.out.print("Id de la mascota:\t");
        int id = leerEntero();
        System.out.print("Diagnostico:\t");
        String diagnostico = input.nextLine();

        if (veterinaria.darDiagnostico(id, diagnostico)) {
            System.out.println("Diagnostico registrado con exito.");
        }
    }

    /*
     * *********************** Datos de prueba (para probar rapido)
     ************************/
    private static void cargarDatosPrueba() {
        veterinaria.loginSuperUsuario(Veterinaria.USUARIO_SUPER, Veterinaria.CLAVE_SUPER);

        Doctor d1 = veterinaria.contratarDoctor("Yuliana Flores", "+1 6641112233", "Yuls", "1234");
        System.out.println("Contratado: " + d1);
        Doctor d2 = veterinaria.contratarDoctor("Zaide Quintero", "6642223344", "Pato", "1234");
        System.out.println("Contratado: " + d2);
        Doctor d3 = veterinaria.contratarDoctor("Andrea Mercado", "6643334455", "Andy", "1234");
        System.out.println("Contratado: " + d3);
        veterinaria.logout(); /* Cierra la sesion de super usuario */

        veterinaria.loginDoctor("Yuls", "1234");
        Mascota m1 = veterinaria.darDeAltaMascota("Aruma", "Perro", 3, "Xcaret Palma", "664-444-5566");
        System.out.println("Ingresado: " + m1);
        Mascota m2 = veterinaria.darDeAltaMascota("Sky", "Lobo", 2, "Daniela Rosa", "664-555-6677");
        System.out.println("Ingresado: " + m2);
        Mascota m3 = veterinaria.darDeAltaMascota("Caguama", "Conejo", 5, "Hiromi Ortiz", "664-444-5566");
        System.out.println("Ingresado: " + m3);
        Mascota m4 = veterinaria.darDeAltaMascota("Joy", "Gato", 1, "Samantha Andrade", "664-666-7788");
        System.out.println("Ingresado: " + m4);

        if (m1 != null) {
            veterinaria.darDiagnostico(m1.getId(), "Sano, solo requiere vacuna anal."); /* -- */
        }
        if (m2 != null) {
            veterinaria.darDiagnostico(m2.getId(), "Infeccion leve en el oido, tratamiento con gotas de mi troson.");
        }

        System.out.println("\nSe cargaron 3 doctores (Yuls/1234, Pato/1234, Andy/1234) y 4 mascotas de prueba.");
        System.out.println(
                "Sesion actual: doctor Andea M. (ya logueada en mi corazon, lista para seguir probando mis besos)."); /*
                                                                                                                       * --
                                                                                                                       */
    }

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