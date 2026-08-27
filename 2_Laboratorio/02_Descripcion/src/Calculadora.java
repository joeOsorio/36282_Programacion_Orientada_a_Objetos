
import java.util.Scanner;
public class Calculadora{
  private static Scanner input = new Scanner(System.in);
  
  /* Inicializar los constructores */ 

  private static Rectangulo rectangulo = new Rectangulo();;
  private static Circulo circulo = new Circulo();
  private static Triangulo triangulo = new Triangulo();
  
  public static void main (String[] args){
    int opcion;
    
    do{
      System.out.println("--------------------------------------------");
			System.out.println("          LABORATORIO 2: Calculadora        ");
			System.out.println("--------------------------------------------");
			System.out.println("1 -\tRactangulo");
			System.out.println("2 -\tCirculo");
			System.out.println("3 -\tTriangulo");
			System.out.println("4 -\tSalir");
			System.out.printf("\nOpcion:\t");
			
			opcion = input.nextInt();
			input.nextLine();
    switch(opcion){
      case 1:
        /* System.out.println("Rectangulo"); */
        MenuRecatangulo();
        break;
      case 2:
        /* System.out.println("Circulo"); */
        MenuCirculo();
        break;
      case 3:
        /* System.out.println("Traingulo"); */
        MenuTriangulo();
        break;
      case 4:
        System.out.println("Saliendo...");
        break;
      case 5:
        System.out.println("prueba");
        break;
      default:
        System.out.println("No se encontro la opcion");
    }
    
    
    }while(opcion != 4);
  }
  
  private static void MenuRecatangulo(){
  int opcion;
  double tmpD;
  do{
      System.out.println("--------------------------------------------");
			System.out.println("                  Rectangulo                ");
			System.out.println("--------------------------------------------");
			System.out.println("1 -\tCapturar base");
			System.out.println("2 -\tCapturar altura");
			System.out.println("3 -\tCalcular Perimetro");
			System.out.println("4 -\tCalcular Area");
			System.out.println("5 -\tRegresar");
			System.out.printf("\nOpcion:\t");
			
			opcion = input.nextInt();
			input.nextLine();
    switch(opcion){
      case 1:
        System.out.println("Base:");
        tmpD = input.nextDouble();
        rectangulo.setBase(tmpD);
        System.out.printf("\nCaptura correcta. Base = %4.2f\n", rectangulo.getBase());
        break;
      case 2:
        System.out.println("Altura:");
        tmpD = input.nextDouble();
        rectangulo.setAltura(tmpD);
        System.out.printf("\nCaptura correcta. Altura = %4.2f\n", rectangulo.getAltura());
        break;
      case 3:
        System.out.printf("Calcular perimetro.\nPerimetro = %4.2f\n", rectangulo.perimetro());
        break;
      case 4:
        System.out.printf("Calcular Area.\nArea = %4.2f\n", rectangulo.area());
        break;
      case 5:
        System.out.println("Regresando al menu principal...\n");
        break;
      case 6:
        Rectangulo r = rectangulo;
        rectangulo.setBase(5);
        rectangulo.setAltura(10);
        System.out.println("prueba");      
        System.out.printf("Base = %4.2f\tAltura = %4.2f\t Area = %4.2f\t Perimetro = %4.2f\n" , r.getBase(), r.getAltura(), r.perimetro(), r.area());
        break;
      default:
        System.out.println("No se encontro la opcion");
    }
    }while(opcion != 5);
  
  }
  
  private static void MenuCirculo(){
  int opcion;
  double tmpD;
  do{
      System.out.println("--------------------------------------------");
			System.out.println("                  Circulo                   ");
			System.out.println("--------------------------------------------");
			System.out.println("1 -\tCapturar radio");
			System.out.println("2 -\tCalcular Perimetro");
			System.out.println("3 -\tCalcular Area");
			System.out.println("4 -\tRegresar");
			System.out.printf("\nOpcion:\t");
			
			opcion = input.nextInt();
			input.nextLine();
    switch(opcion){
      case 1:
        System.out.println("radio:");
        tmpD = input.nextDouble();
        circulo.setRadio(tmpD);
        System.out.printf("\nCaptura correcta. Radio = %4.2f\n", circulo.getRadio());
        break;
      case 2:
        System.out.printf("Calcular perimetro.\nPerimetro = %4.2f\n", circulo.perimetro());
        break;
      case 3:
        System.out.printf("Calcular Area.\nArea = %4.2f\n", circulo.area());
        break;
      case 4:
        System.out.println("Regresando al menu principal...\n");
        break;
      case 5:
        Circulo c = new Circulo(3.5);
        System.out.println("prueba");      
        System.out.printf("Radio = %4.2f\t Area = %4.2f\t Perimetro = %4.2f\n" , c.getRadio(), c.perimetro(), c.area());
        break;
      default:
        System.out.println("No se encontro la opcion");
    }
    }while(opcion != 4);
    }
  
  private static void MenuTriangulo(){
  int opcion;
  double tmpD;
  do{
      System.out.println("--------------------------------------------");
			System.out.println("                  Triangulo                ");
			System.out.println("--------------------------------------------");
			System.out.println("1 -\tCapturar base");
			System.out.println("2 -\tCapturar altura");
			System.out.println("3 -\tCalcular Perimetro");
			System.out.println("4 -\tCalcular Area");
			System.out.println("5 -\tRegresar");
			System.out.printf("\nOpcion:\t");
			
			opcion = input.nextInt();
			input.nextLine();
    switch(opcion){
      case 1:
        System.out.println("Base:");
        tmpD = input.nextDouble();
        triangulo.setBase(tmpD);
        System.out.printf("\nCaptura correcta. Base = %4.2f\n", triangulo.getBase());
        break;
      case 2:
        System.out.println("Altura:");
        tmpD = input.nextDouble();
        input.nextLine();
        triangulo.setAltura(tmpD);
        System.out.printf("\nCaptura correcta. Altura = %4.2f\n", triangulo.getAltura());
        break;
      case 3:
        System.out.println("Lado A:");
        tmpD = input.nextDouble();
        System.out.println("Lado B:");
        double tmpD2 = input.nextDouble();
        System.out.printf("\nCalcular perimetro.\nPerimetro = %4.2f\n", triangulo.perimetro(tmpD, tmpD2));
        break;
      case 4:
        System.out.printf("\nCalcular Area.\nArea = %4.2f\n", triangulo.area());
        break;
      case 5:
        System.out.println("Regresando al menu principal...");
        break;
      case 6:
        Triangulo t = new Triangulo(5,10);
        System.out.println("prueba");      
        System.out.printf("Base = %4.2f\tAltura = %4.2f\t Area = %4.2f\t Perimetro = %4.2f\n" , t.getBase(), t.getAltura(), t.perimetro(9.6824,9.6824), t.area());
        break;
      default:
        System.out.println("No se encontro la opcion");
    }
    }while(opcion != 5);
  
  }
}
