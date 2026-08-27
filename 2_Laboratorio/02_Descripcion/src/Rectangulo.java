public class Rectangulo{
  private double base;
  private double altura;
  
  /* Constructor*/
  Rectangulo(){base =0; altura = 0;}  
  Rectangulo(int base, int altura){
    this.base = base;
    this.altura = altura;
  }

  public void setBase(double base){
    this.base = base;
  } 
  
  public void setAltura(double altura){
    this.altura = altura;
  }
  
  public double getAltura(){
    return altura;
  }
  
  public double getBase(){
    return base;
  }
  
  public double area(){
   return base * altura;
  }
  
  public double perimetro(){return (base * 2) + (altura * 2);}

}
