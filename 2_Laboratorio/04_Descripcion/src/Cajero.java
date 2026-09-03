import java.util.Scanner;

/*

Haz el código del siguiente problema.

1.- Realiza un programa donde simule un cajero electrónico y sus operaciones que puedes realizar en una cuenta bancaria.

A) La clase CuentaBancaria contiene
Numero de cuenta, titular, saldo.
operación de depositar y de retirar
operación de saldar cuenta
B) Un cajero contiene:
Operación de Ingresar, 
Operación de depósito, retiro, eliminar cuenta
Operación de imprimir recibo
Operación de salir
El cajero cada vez que haces un depósito o retiro o eliminar cuenta , debe de imprimir un recibo con la operación que se hizo y el historial del saldo de la cuenta, así como información sensible de la cuenta.
*/


public class Cajero{
  private CuentaBancaria[]  cuentas;
  private contador = 0;
  
  Cajero(int cantidaCuentas){
    cuentas = new CuentaBancaria[cantidaCuentas];
  }
  
  
  /* Operación de Ingresar */
  public String ingresar(int numerocuenta, double saldo){
    CuentasBancaria c = cuentas.buscarCuenta(numerocuenta);
    c.
  }
  /* Operación de depósito */
  /* retiro, eliminar cuenta */
  /* Operación de imprimir recibo */
  
  
  
  public CuentaBancaria buscarCuenta(int numeroCuenta) {
		for (int i = 0; i < contador; i++) {
			if (cuentas[i].getNumeroCuenta() == numeroCuenta) {return cuentas[i];}
		}
		return null;
	}
	
  public String capturar(String msj) {
    String tmp;
    System.out.printf("\nFavor de ingresar %s:", msj);
    tmp = input.nextLine();
    return tmp;
  }
  
}
