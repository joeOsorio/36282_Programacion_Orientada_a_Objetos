/* **************************************************
 * Laboratorio 7: Clase CuentaBancaria
 * Representa una cuenta bancaria individual: su
 * numero, titular, PIN y saldo, y las operaciones
 * basicas sobre el saldo.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class CuentaBancaria {

	private int numeroCuenta;
	private String titular;
	private String pin;
	private double saldo;

	public CuentaBancaria(int numeroCuenta, String titular, String pin, double saldoInicial) {
		this.numeroCuenta = numeroCuenta;
		this.titular = titular;
		this.pin = pin;
		this.saldo = saldoInicial;
	}

	/* *********************** Operaciones ************************/
	public boolean retirar(double monto) {
		if (monto <= 0) {
			System.out.println("Error: el monto a retirar debe ser mayor a cero.");
			return false;
		}
		if (monto > saldo) {
			System.out.println("Error: fondos insuficientes.");
			return false;
		}
		saldo -= monto;
		return true;
	}

	public boolean abonar(double monto) {
		if (monto <= 0) {
			System.out.println("Error: el monto a abonar debe ser mayor a cero.");
			return false;
		}
		saldo += monto;
		return true;
	}

	public boolean verificarPin(String pin) {
		return this.pin != null && this.pin.equals(pin);
	}

	/* Setters */
	public void setPin(String nuevoPin) {
		this.pin = nuevoPin;
	}

	/* Getters */
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public String getTitular() {
		return titular;
	}

	public double getSaldo() {
		return saldo;
	}

	/*
	 * En un sistema real el PIN nunca se expondria asi (ni siquiera al banco).
	 * Lo dejo con getter porque el Banco lo genera automaticamente (el titular
	 * no lo escoge) y alguien tiene que poder comunicarselo/probarlo; ver
	 * Reflexion del reporte.
	 */
	public String getPin() {
		return pin;
	}

	@Override
	public String toString() {
		return String.format("Cuenta #%d - Titular: %s - Saldo: $%.2f", numeroCuenta, titular, saldo);
	}
}
