public class Circulo{
  private double radio;
  
  Circulo(){this.radio = 0;}
  Circulo(double radio){this.radio = radio;}

  public void setRadio(double radio){this.radio = radio;}
  public double getRadio(){return radio;}
  
  public double area(){ return Math.PI * radio;}
  public double perimetro(){return Math.PI * Math.pow(radio, 2);}
}
