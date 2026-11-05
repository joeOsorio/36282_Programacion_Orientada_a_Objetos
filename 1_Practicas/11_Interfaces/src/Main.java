/*
 * Laboratorio 11: Interfaces.
 * Clase main con pare de test con pruebas unitarias del comportamiento esperado.
 * Se implementa interfaces de IColtar e IValidar.
 * @Data: Octiuber/2026
 * @Author: Joe O^2 
*/

import java.util.Scanner;

import java.util.InputMismatchException;

public class Main {
    public static final int MAX_DATOS = 10;
    private static Scanner input = new Scanner(System.in);
    private static IOcultar[] datos = new IOcultar[MAX_DATOS];
    private static int contador = 0;

    private static ReglaLongitudMinima reglaLongitud = new ReglaLongitudMinima(3);
    private static LineaEnsamble linea = crearLinea(reglaLongitud);

    public static void main(String[] args) {
        /*
         * String correo = "omar.zamarron@uabc.edu.mx";
         * int indice = correo.indexOf("@");
         * System.out.println(correo.indexOf("@"));
         * System.out.println(correo);
         * System.out.println("*".repeat(indice));
         * System.out.println("*".repeat(indice) + correo.substring(indice));
         * String ofuscado = "*".repeat(indice) + correo.substring(indice);
         * System.out.println(ofuscado);
         */

        int opcion;
        do {
            System.out.println("\nLaboratorio 11: Interfaces");
            System.out.println("Ocultar datos");
            /*
             * System.out.println("1.\tRegistrar email");
             * System.out.println("2.\tRegistrar tarjeta");
             * System.out.println("3.\tRegistrar password");
             * System.out.println("4.\tVer datos ocultos");
             * System.out.println("Linea de ensamble");
             * System.out.println("5.\tValidar una cadena");
             * System.out.println("6.\tCambiar longitud minima (actual: " +
             * reglaLongitud.getMinimo() + ")");
             * System.out.println("-------------");
             * System.out.println("7.\tCargar datos de prueba");
             */
            System.out.println("8.\tSalir");
            System.out.println(ANSI_VERDE + "9.\tEjecutar test" + ANSI_RESET);
            opcion = leerEntero("Opcion:");
            switch (opcion) {
                case 1:
                    // guardar(new Email(leerEmail("Correo: ")));
                    break;
                case 2:
                    // guardar(new Tarjeta(leertarjeta("Numero de tarjeta :")));
                    break;
                case 3:
                    // guardar(new Password(leerPassword("Password: ")));
                    break;
                case 4:
                    // mostrarDatos();
                    break;
                case 5:
                    System.out.print("Cadena a validar (puede ir vacia): ");
                    linea.reporte(input.nextLine());
                    break;
                case 6:
                    // reglaLongitud.setMinimo(leerEnteroRango("Nueva longitud minima: ", 1, 100));
                    break;
                case 7:
                    // cargarDatosPrueba();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                case 9:
                    test();
                    break;
                default:
                    System.out.println("Error: opcion invalida.");
            }
        } while (opcion != 8);
    }

    /* Line de ensamblaje */

    /* *********************** Linea de ensamble ************************/
    private static LineaEnsamble crearLinea(ReglaLongitudMinima longitud) {
        LineaEnsamble l = new LineaEnsamble();
        l.agregarRegla(new ReglaNoNulo());
        l.agregarRegla(new ReglaNoVacio());
        l.agregarRegla(longitud);
        l.agregarRegla(new ReglaEsNumero());
        return l;
    }

    /* Parte del test */
    private static int pasaron, total;
    public static final String ANSI_ROJO = "\u001B[31m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m"; // Restablece por defecto

    private static void verificar(String descripcion, boolean resultado) {
        total++;
        if (resultado) {
            pasaron++;
        }

        System.out.println((resultado ? ANSI_VERDE + "  [OK]    " + ANSI_RESET : ANSI_ROJO + "  [FALLA] " + ANSI_RESET)
                + descripcion);
    }

    private static void test() {
        pasaron = 0;
        total = 0;
        System.out.println("\n============= TEST =============");
        System.out.println("1)\t Email");
        Email e = new Email("Joshua.Osorio@UABC.EDU.MX");
        verificar("Joshua.Osorio@UABC.EDU.MX ->    Joshua.Osorio@UABC.EDU.MX",
                e.ocultar().equals("*************@UABC.EDU.MX"));
        verificar("Un * por cada caracter del usuario", new Email("a@uabc.mx").ocultar().equals("*@uabc.mx"));
        verificar("Sin @ es invalido (se espera error)", !e.setCorreo("Joshua.OsorioUABC.EDU.MX"));
        verificar("Dos @ es invalido (se espera error)", !e.setCorreo("a@@gmail.com"));
        verificar("Usuario vacio es invalidop  (se espera error)", !e.setCorreo("@UABC.MX"));
        verificar("Dominio sin punto es invalido (se espera error)", !e.setCorreo("Joshua.Osorio@UABCEDUMX"));
        verificar("null es invalido (se espera error)", !e.setCorreo(null));
        verificar("Si el set falla se conserva el correo anterior", e.getCorreo().equals("Joshua.Osorio@UABC.EDU.MX"));

        System.out.println("\n2) Tarjeta");

        Tarjeta t = new Tarjeta("1234-5678-1234-5678");
        verificar("1234-5678-1234-5678 -> ****-****-****-5678", t.ocultar().equals("****-****-****-5678"));
        verificar("se guarda sin guiones", t.getNumero().equals("1234567812345678"));
        verificar("con espacios tambien funcionan",
                new Tarjeta("1234 5678 1234 5678").ocultar().equals("****-****-****-5678"));
        verificar("letras es invalido (se espera error)", !t.setNumero("1234-ABCD-1234-5678"));
        verificar("muy corta es invalida (se espera error)", !t.setNumero("1234-5678"));
        verificar("muy larga es invalida (se espara error)", !t.setNumero("1234-5678-1234-5678-1234"));
        verificar("si el set falla se conserva el numero anterior", !t.ocultar().endsWith("6543"));

        System.out.println("\n3) Password");
        Password p = new Password("123456789");
        verificar("123456789 -> *********", p.ocultar().equals("*********"));
        verificar("misma longitud que la clave", new Password("abc").ocultar().equals("***"));
        verificar("clave vacia es invalida (se espera error)", !p.setClave(""));
        verificar("clave con espacios es invalida (se espera error)", !p.setClave("Hola mundo"));
        verificar("la clave real no se modifica al ocultar", p.getClave().equals("123456789"));

        System.out.println("\4) Plimorfismo con IOcultar");
        IOcultar[] arr = { new Email("Joshua.Osorio@UABC.EDU.MX"), new Tarjeta("1234-5678-1234-5678"),
                new Password("123456789") };
        String[] esperado = { "*************@UABC.EDU.MX", "****-****-****-5678", "*********" };
        boolean todos = true;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("  " + arr[i].getClass().getSimpleName() + " -> " + arr[i].ocultar());
            todos &= arr[i].ocultar().equals(esperado[i]);
        }
        verificar("el mismo metodo ocultar() hace algo distinto en cada clase", todos);
        /* Ya m harte FUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA */

        System.out.println("\n5) Reglas por separado");
        IValidar noNulo = new ReglaNoNulo();
        IValidar noVacio = new ReglaNoVacio();
        ReglaLongitudMinima longitud = new ReglaLongitudMinima(5);
        IValidar esNumero = new ReglaEsNumero();

        verificar("NoNulo: \"hola\" pasa", noNulo.validar("hola"));
        verificar("NoNulo: \"\" pasa (vacio no es null)", noNulo.validar(""));
        verificar("NoNulo: null no pasa", !noNulo.validar(null));

        verificar("NoVacio: \"hola\" pasa", noVacio.validar("hola"));
        verificar("NoVacio: \"\" no pasa", !noVacio.validar(""));
        verificar("NoVacio: \"   \" no pasa (solo espacios)", !noVacio.validar("   "));
        verificar("NoVacio: null no pasa (sin NullPointerException)", !noVacio.validar(null));

        verificar("LongitudMinima(5): \"hola\" no pasa", !longitud.validar("hola"));
        verificar("LongitudMinima(5): \"12345\" pasa (justo el limite)", longitud.validar("12345"));
        verificar("LongitudMinima(5): null no pasa", !longitud.validar(null));
        verificar("LongitudMinima: 0 es invalido (se espera Error)", !longitud.setMinimo(0));
        verificar("si el set falla se conserva el minimo anterior", longitud.getMinimo() == 5);

        verificar("EsNumero: \"42\" pasa", esNumero.validar("42"));
        verificar("EsNumero: \"-3.1416\" pasa", esNumero.validar("-3.1416"));
        verificar("EsNumero: \"1e3\" pasa (notacion cientifica)", esNumero.validar("1e3"));
        verificar("EsNumero: \"12abc\" no pasa", !esNumero.validar("12abc"));
        verificar("EsNumero: \"\" no pasa", !esNumero.validar(""));
        verificar("EsNumero: null no pasa", !esNumero.validar(null));

        System.out.println("\n6) Linea de ensamble (NoNulo, NoVacio, Longitud>=3, EsNumero)");
        LineaEnsamble l = crearLinea(new ReglaLongitudMinima(3));
        verificar("tiene 4 reglas", l.getContador() == 4);
        String[] cadenas = { null, "", "   ", "12", "hola", "3.1416", "2026" };
        boolean[] aceptadas = { false, false, false, false, false, true, true };
        for (int i = 0; i < cadenas.length; i++) {
            l.reporte(cadenas[i]);
            verificar("resultado esperado: "
                    + (aceptadas[i] ? (ANSI_VERDE + "ACEPTADA" + ANSI_RESET) : (ANSI_ROJO + "RECHAZADA" + ANSI_RESET)),
                    l.validar(cadenas[i]) == aceptadas[i]);
        }
        verificar("agregar regla null (se espera Error)", !l.agregarRegla(null));
        for (int i = l.getContador(); i < LineaEnsamble.MAX_REGLAS; i++) {
            l.agregarRegla(new ReglaNoNulo());
        }
        verificar("regla 11 rebasa el maximo (se espera Error)", !l.agregarRegla(new ReglaNoVacio()));
        verificar("getRegla fuera de rango (se espera Error)", l.getRegla(20) == null);

        System.out.println("\n========== " + pasaron + "/" + total + " pruebas pasaron ==========");

    }

    /*
     * Captura Segura de: numero, rango de nuemero, texto, tarjeta, email y clave.
     */

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = input.nextInt();
                input.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Escribe un numero entro.");
                input.nextLine();
            }
        }
    }

    private static int leerEnteroRango(String mensaje, int min, int max) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (min <= valor && valor <= max) {
                return valor;
            }
            System.out.println("Error: debe estar entre " + min + " y " + max + ".");
        }
    }

    private static String leerTexto(String mensaje) {
        IValidar noVacio = new ReglaNoVacio();
        while (true) {
            System.out.println(mensaje);
            String texto = input.nextLine().trim();
            if (noVacio.validar(texto)) {
                return texto;
            }
            System.out.println("Error: no puede ir vacio.");
        }
    }

    private static String leerEmail(String mensaje) {
        while (true) {
            String correo = leerTexto(mensaje);
            if (Email.esValido(correo))
                return correo;
            System.out.println("Error: formato usuario@dominio.com");
        }
    }

    private static String leerTarjeta(String mensaje) {
        while (true) {
            String numero = leerTexto(mensaje);
            if (Tarjeta.esValido(numero))
                return numero;
            System.out.println("Error: solo digitos (guiones/espacios opcionales), " + Tarjeta.MIN_DIGITOS + " a "
                    + Tarjeta.MAX_DIGITOS + " digitos.");
        }
    }

    private static String leerPassword(String mensaje) {
        while (true) {
            String password = leerTexto(mensaje);
            if (Password.esValido(password))
                return password;
            System.out.println("Error: la clave no puede tener espacios.");
        }
    }
}
