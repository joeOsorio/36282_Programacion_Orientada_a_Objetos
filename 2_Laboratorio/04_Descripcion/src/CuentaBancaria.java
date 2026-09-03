public class CuentaBancaria{
  private int numeroCuenta;
  private String titular;
  private double saldo;
  
  
  public CuentaBancaria(int numeroCuenta, String titular, double saldo){
    this.numeroCuenta = numeroCuenta;
    this.titular = titular;
    this.saldo = saldo;
  }
  public CuentaBancaria(){this(0, "Nuevo", 0);}
  /* Settters */
  public void setTitular(String titular){this.titular = titular;}
  public void setSaldo(double saldo){this.saldo = saldo;}
  /* Getters */
  public String getTitular(){return titular;}
  public double getSaldo(){return saldo;}
  public int getNumeroCuenta(){return numeroCuenta;}
  /* Metodos  */
  public void depositar(double abono){
  if(abono != 0 && abono > 0){
    saldo += abono;
  }
  System.out.println("Error: El abono es 0 o menor.");
  }
  
  public void retirar(double retiro){
    if(retiro != 0 && retiro <= saldo){
      saldo -= retiro;
    }
    System.out.println("Error: El retiro es 0 o mayor al saldo.");
  }
  public void saldarCuenta(){System.out.printf("Es como elimier cuenta");}
}
