/* **************************************************
 * Laboratorio 7: Clase Cajero
 * Terminal que un titular usa para operar su propia
 * cuenta. NO es dueno del Banco, solo lo consulta
 * para autenticar. Solo puede haber una sesion (una
 * cuenta) activa a la vez.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/

public class Cajero {

	/* Asociacion: el Cajero solo consulta al Banco, no lo crea ni lo destruye */
	private Banco banco;

	/* Sesion activa: la cuenta que inicio sesion en este cajero (o null) */
	private CuentaBancaria cuentaActual;

	public Cajero(Banco banco) {
		this.banco = banco;
		this.cuentaActual = null;
	}

	/* *********************** Sesion ************************/
	public boolean login(int numeroCuenta, String pin) {
		if (cuentaActual != null) {
			System.out.println("Error: ya hay una sesion activa en el cajero, cierra sesion primero.");
			return false;
		}
		CuentaBancaria cuenta = banco.buscarCuenta(numeroCuenta);
		if (cuenta == null || !banco.validarPin(numeroCuenta, pin)) {
			System.out.println("Error: numero de cuenta o PIN incorrectos.");
			return false;
		}
		cuentaActual = cuenta;
		return true;
	}

	public void logout() {
		cuentaActual = null;
	}

	public boolean haySesion() {
		return cuentaActual != null;
	}

	public CuentaBancaria getCuentaActual() {
		return cuentaActual;
	}

	/*
	 ******************************************************
	 * Operaciones (requieren sesion)
	 ******************************************************
	 */
	public boolean retirar(double monto) {
		if (!puedeOperar()) {
			return false;
		}
		return cuentaActual.retirar(monto);
	}

	public boolean abonar(double monto) {
		if (!puedeOperar()) {
			return false;
		}
		return cuentaActual.abonar(monto);
	}

	public boolean cambiarPin(String pinActual, String pinNuevo) {
		if (!puedeOperar()) {
			return false;
		}
		if (!cuentaActual.verificarPin(pinActual)) {
			System.out.println("Error: el PIN actual no coincide.");
			return false;
		}
		if (pinNuevo == null || !pinNuevo.matches("\\d{4}")) {
			System.out.println("Error: el PIN nuevo debe ser de 4 digitos numericos.");
			return false;
		}
		cuentaActual.setPin(pinNuevo);
		return true;
	}

	/* Delega el borrado real en el Banco; el titular logueado es la autorizacion */
	public boolean eliminarCuentaActual() {
		if (!puedeOperar()) {
			return false;
		}
		int numero = cuentaActual.getNumeroCuenta();
		boolean eliminada = banco.cerrarCuenta(numero);
		if (eliminada) {
			logout();
		}
		return eliminada;
	}

	private boolean puedeOperar() {
		if (cuentaActual == null) {
			System.out.println("Error: se necesita iniciar sesion en el cajero.");
			return false;
		}
		return true;
	}

	/* *********************** Informacion ************************/
	public void imprimirRecibo() {
		if (!puedeOperar()) {
			return;
		}
		System.out.println("---------- Recibo ----------");
		System.out.println("Cuenta: " + enmascarar(cuentaActual.getNumeroCuenta()));
		System.out.println("Titular: " + cuentaActual.getTitular());
		System.out.printf("Saldo actual: $%.2f%n", cuentaActual.getSaldo());
		System.out.println("-----------------------------");
	}

	private String enmascarar(int numeroCuenta) {
		String numero = String.valueOf(numeroCuenta);
		if (numero.length() <= 4) {
			return "****" + numero;
		}
		return "****" + numero.substring(numero.length() - 4);
	}
}
