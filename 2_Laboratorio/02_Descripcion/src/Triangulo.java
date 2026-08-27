
public class Triangulo{
  private double base;
  private double altura;
  /*
  private double ladoA;
  private double ladoB;
  */
  Triangulo(){base = 0; altura = 0;} 
  Triangulo(double base, double altura){
    this.base = base;
    this.altura = altura;
  }
  
  public void setBase(double base){ this.base = base;}
  public void setAltura(double altura){this.altura = altura;}
  public double getBase(){return base;}
  public double getAltura(){return altura;}
  public double area(){return (base * altura)/2;}
  public double perimetro(double ladoA, double ladoB){
    return ladoA + ladoB + base;
    /*      
    System.out.prinln("Intgresa la longitud del lado A:");
    System.out.prinln("Ingresa la longitudn del lado B:")
    */
  
  }
}
