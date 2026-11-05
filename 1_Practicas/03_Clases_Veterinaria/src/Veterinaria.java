import java.util.Scanner;

public class Veterinaria {

  public static Scanner input = new Scanner(System.in);
  public static Mascota[] mascotas = new Mascota[5];
  public static int contador = 0;

  public static void main(String[] args) {
    int opcion;

    do {
      System.out.println("--------------------------------------------");
      System.out.println("                Veterinaria                 ");
      System.out.println("--------------------------------------------");
      System.out.println("1 -\tRegistro mascota");
      System.out.println("2 -\tDar diagnóstico");
      System.out.println("3 -\tSalir");
      System.out.printf("\nOpcion:\t");

      opcion = input.nextInt();
      input.nextLine();
      switch (opcion) {
        case 1:
          registrar();
          // System.out.println("");
          break;
        case 2:
          darDiagnostico();
          break;
        case 3:
          System.out.println("Saliendo...");
          break;
        case 4:
          test();
          break;
        default:
          System.out.println("No se encontro la opcion");
      }
    } while (opcion != 3);

  }

  public static void registrar() {
    int opcion;

    if (contador >= mascotas.length) {
      System.out.println("\nYa se alcanzo el maximo de mascotas registradas.");
      return;
    }
    if (mascotas[contador] == null) {
      mascotas[contador] = new Mascota();
    }

    do {
      System.out.println("--------------------------------------------");
      System.out.println("         Registrar datos de mascota         ");
      System.out.println("--------------------------------------------");
      System.out.println("1 -\tNombre");
      System.out.println("2 -\tEdad");
      System.out.println("3 -\tTipo");
      System.out.println("4 -\tRaza");
      System.out.println("5 -\tDiagnostico");
      System.out.println("0 -\tRegresar");
      System.out.printf("\nOpcion:\t");

      opcion = input.nextInt();
      input.nextLine();
      switch (opcion) {
        case 1:
          mascotas[contador].setNombre(capturar("nombre"));
          System.out.printf("Captura correcta: %s", mascotas[contador].getNombre());
          break;
        case 2:
          System.out.printf("\nFavor de ingresar edad:");
          int tmpInt = input.nextInt();
          input.nextLine();
          mascotas[contador].setEdad(tmpInt);
          System.out.printf("Captura correcta: %d", mascotas[contador].getEdad());
          break;
        case 3:
          mascotas[contador].setTipo(capturar("tipo"));
          System.out.printf("Captura correcta: %s", mascotas[contador].getTipo());
          break;
        case 4:
          mascotas[contador].setRaza(capturar("raza"));
          System.out.printf("Captura correcta: %s", mascotas[contador].getRaza());
          break;
        case 5:
          mascotas[contador].setDiagnostico(capturar("diagnostico"));
          System.out.printf("Captura correcta: %s", mascotas[contador].getDiagnostico());
          break;
        case 0:
          System.out.println("Saliendo...");
          break;
        default:
          System.out.println("No se encontro la opcion");
      }
    } while (opcion != 0);

    contador++;
  }

  public static String capturar(String msj) {
    String tmp;
    System.out.printf("\nFavor de ingresar %s:", msj);
    tmp = input.nextLine();
    return tmp;
  }

  public static void darDiagnostico() {
    String nombre;
    boolean encontrada = false;

    System.out.printf("\nNombre de la mascota:\t");
    nombre = input.nextLine();

    for (int i = 0; i < contador; i++) {
      if (mascotas[i].getNombre().equalsIgnoreCase(nombre)) {
        System.out.printf("\nDiagnostico actual: %s\n", mascotas[i].getDiagnostico());
        mascotas[i].setDiagnostico(capturar("nuevo diagnostico"));
        System.out.printf("Captura correcta: %s\n", mascotas[i].getDiagnostico());
        encontrada = true;
        break;
      }
    }

    if (!encontrada) {
      System.out.println("\nMascota no encontrada.");
    }
  }

  public static void test() {
    mascotas[contador++] = new Mascota("Peneko", 18, "Canino", "Shitzu", "Se murio");
    mascotas[contador++] = new Mascota("Roma", 10, "Canino", "Shitzu", "no mira");
    mascotas[contador++] = new Mascota("Lucas", 2, "Canino", "Shitzu", "Esta malito");

    Mascota m;
    System.out.printf("|%-10s\t%-5s\t%-10s\t%-10s\t%-15s\n", "Nombre", "Edad", "Tipo", "Raza", "Diagnostico");
    for (int i = 0; i < contador; i++) {
      m = mascotas[i];
      System.out.printf("\n%-10s\t%-5d\t%-10s\t%-10s\t%-15s\n", m.getNombre(), m.getEdad(), m.getTipo(), m.getRaza(),
          m.getDiagnostico());
    }
  }
}
