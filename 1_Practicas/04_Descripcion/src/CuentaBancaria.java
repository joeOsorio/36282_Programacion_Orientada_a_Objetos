import java.util.Locale;

/* **************************************************
 * Laboratorio 4: Clase CuentaBancaria
 * Representa una cuenta bancaria y su logica: numero
 * de cuenta, titular, saldo, depositos, retiros y
 * cierre (saldar) de la cuenta.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class CuentaBancaria {

	private static final int MAX_HISTORIAL = 20;

	private int numeroCuenta;
	private String titular;
	private double saldo;

	/* Historial de movimientos (arreglo de tamano fijo, como el resto del curso) */
	private String[] historial;
	private int contadorHistorial;

	public CuentaBancaria(int numeroCuenta, String titular, double saldo) {
		this.numeroCuenta = numeroCuenta;
		this.titular = titular;
		this.saldo = saldo;
		this.historial = new String[MAX_HISTORIAL];
		this.contadorHistorial = 0;
		registrarHistorial(String.format("Apertura de cuenta. Saldo inicial: $%.2f", saldo));
	}
	/*
	 * Pruba debugg
	 * public CuentaBancaria() {
	 * this(0, "Nuevo", 0);
	 * }
	 */

	/* Setters */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/* Getters */
	public String getTitular() {
		return titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	/* *********************** Depositar ************************/
	public boolean depositar(double monto) {
		if (monto <= 0) {
			System.out.println("Error: el monto a depositar debe ser mayor a 0.");
			return false;
		}
		saldo += monto;
		registrarHistorial(String.format("Deposito: +$%.2f -> Saldo: $%.2f", monto, saldo));
		return true;
	}

	/* *********************** Retirar ************************/
	public boolean retirar(double monto) {
		if (monto <= 0) {
			System.out.println("Error: el monto a retirar debe ser mayor a 0.");
			return false;
		}
		if (monto > saldo) {
			System.out.println("Error: saldo insuficiente para realizar el retiro.");
			return false;
		}
		saldo -= monto;
		registrarHistorial(String.format("Retiro: -$%.2f -> Saldo: $%.2f", monto, saldo));
		return true;
	}

	/* *********************** Saldar cuenta ************************/
	/*
	 * "Saldar" la cuenta es el paso previo a eliminarla: se registra el
	 * cierre en el historial y despues se borran los datos sensibles
	 * (titular, numero de cuenta y saldo).
	 * La clase Banco, que administra el arreglo de cuentas, es quien saca la
	 * cuenta de la "base de datos" despues de llamar a este metodo.
	 */
	public void saldarCuenta() {
		registrarHistorial(String.format("Cierre de cuenta. Saldo final: $%.2f", saldo));
		this.titular = "";
		this.numeroCuenta = 0;
		this.saldo = 0;
	}

	/* *********************** Historial ************************/
	private void registrarHistorial(String movimiento) {
		if (contadorHistorial >= historial.length) {
			/*
			 * Arreglo lleno: se recorre para conservar solo los mas recientes. hacer push a
			 * la cola pero aun no la desarollo
			 */
			for (int i = 1; i < historial.length; i++) {
				historial[i - 1] = historial[i];
			}
			historial[historial.length - 1] = movimiento;
		} else {
			historial[contadorHistorial++] = movimiento;
		}
	}

	public String historialToString() {
		if (contadorHistorial == 0) {
			return "  Sin movimientos registrados.\n";
		}
		StringBuilder sb = new StringBuilder();
		/*
		 * Resulve el maldito problema de que en java los string no son mutables y
		 * StringBuilder permite ir concatenando para que se mire mas bonito y sin
		 * batallar
		 */
		for (int i = 0; i < contadorHistorial; i++) {
			sb.append("  ").append(i + 1).append(". ").append(historial[i]).append("\n");
		}
		return sb.toString();
	}

	/* Para ir debuggeando */
	@Override
	public String toString() {
		return String.format("Cuenta #%d | Titular: %s | Saldo: $%.2f", numeroCuenta, titular, saldo);
	}
}
