
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n Examen 2");
            System.out.println(" Opcion");
            System.out.println(" opcion");
            opcion = leerEntero("Opcion");
            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Error: opcion invalida.");
            }
        } while (opcion != 0);

    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = input.nextInt();
                input.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: escribe un numero entero.");
                input.nextLine();

            }
        }
    }
}