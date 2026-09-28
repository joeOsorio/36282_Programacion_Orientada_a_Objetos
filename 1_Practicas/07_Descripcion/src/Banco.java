import java.util.Random;

/* **************************************************
 * Laboratorio 7: Clase Banco
 * Administra el arreglo de cuentas bancarias y
 * controla el login del super usuario (quien puede
 * abrir cuentas). El Banco NO es el main.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Banco {

	/*
	 * En un sistema real esto NUNCA se dejaria fijo en el codigo fuente, por
	 * temas de seguridad.
	 */
	public static final String USUARIO_SUPER = "root";
	public static final String CLAVE_SUPER = "admin";

	/*
	 * Composicion: el Banco crea y controla el ciclo de vida de sus cuentas
	 */
	private CuentaBancaria[] cuentas;
	private int contadorCuentas;
	private int siguienteNumeroCuenta;

	/* Sesion activa */
	private boolean superUsuarioActivo;

	private static final Random RANDOM = new Random();

	public Banco() {
		this.cuentas = new CuentaBancaria[10];
		this.contadorCuentas = 0;
		this.siguienteNumeroCuenta = 1000;
		this.superUsuarioActivo = false;
	}

	/* *********************** Sesion ************************/
	public boolean loginSuperUsuario(String usuario, String clave) {
		if (!USUARIO_SUPER.equals(usuario) || !CLAVE_SUPER.equals(clave)) {
			System.out.println("Error: usuario o clave de super usuario incorrectos.");
			return false;
		}
		superUsuarioActivo = true;
		return true;
	}

	public void logout() {
		superUsuarioActivo = false;
	}

	public boolean haySesionSuperUsuario() {
		return superUsuarioActivo;
	}

	/*
	 ******************************************************
	 * Gestion de cuentas (requiere super usuario)
	 ******************************************************
	 */
	public CuentaBancaria abrirCuenta(String titular, double saldoInicial) {
		if (!superUsuarioActivo) {
			System.out.println("Error: se necesita sesion de super usuario para abrir una cuenta.");
			return null;
		}
		if (contadorCuentas >= cuentas.length) {
			System.out.println("Error: ya se alcanzo el limite de cuentas (10).");
			return null;
		}
		if (titular == null || titular.isEmpty()) {
			System.out.println("Error: el titular es obligatorio.");
			return null;
		}
		if (saldoInicial < 0) {
			System.out.println("Error: el saldo inicial no puede ser negativo.");
			return null;
		}

		String pin = generarPinAleatorio();
		CuentaBancaria nueva = new CuentaBancaria(siguienteNumeroCuenta, titular, pin, saldoInicial);
		siguienteNumeroCuenta++;
		cuentas[contadorCuentas] = nueva;
		contadorCuentas++;
		System.out.println("Cuenta #" + nueva.getNumeroCuenta() + " creada. PIN inicial: " + pin
				+ " (cambialo la primera vez que inicies sesion).");
		return nueva;
	}

	/*
	 * Ojo: cerrarCuenta() NO exige sesion de super usuario aqui adentro, a
	 * proposito: la usan dos flujos distintos con su propia autorizacion -
	 * el super usuario la llama desde el menu administrativo (Main ya
	 * verifico su sesion antes de llamarla), y el Cajero la llama en nombre
	 * del titular ya logueado (Cajero.eliminarCuentaActual()). Ver Reflexion.
	 */
	public boolean cerrarCuenta(int numeroCuenta) {
		int indice = indiceCuentaPorNumero(numeroCuenta);
		if (indice == -1) {
			System.out.println("Error: no existe una cuenta con ese numero.");
			return false;
		}

		/* Recorre el arreglo para no dejar huecos */
		for (int i = indice; i < contadorCuentas - 1; i++) {
			cuentas[i] = cuentas[i + 1];
		}
		cuentas[contadorCuentas - 1] = null;
		contadorCuentas--;
		return true;
	}

	/* *********************** Consulta (usada por el Cajero) ************************/
	public CuentaBancaria buscarCuenta(int numeroCuenta) {
		int indice = indiceCuentaPorNumero(numeroCuenta);
		return indice == -1 ? null : cuentas[indice];
	}

	public boolean validarPin(int numeroCuenta, String pin) {
		CuentaBancaria cuenta = buscarCuenta(numeroCuenta);
		return cuenta != null && cuenta.verificarPin(pin);
	}

	private int indiceCuentaPorNumero(int numeroCuenta) {
		for (int i = 0; i < contadorCuentas; i++) {
			if (cuentas[i].getNumeroCuenta() == numeroCuenta) {
				return i;
			}
		}
		return -1;
	}

	private String generarPinAleatorio() {
		return String.format("%04d", RANDOM.nextInt(10000));
	}

	/* *********************** Informacion ************************/
	public void imprimirCuentas() {
		if (contadorCuentas == 0) {
			System.out.println("No hay cuentas registradas.");
			return;
		}
		for (int i = 0; i < contadorCuentas; i++) {
			System.out.println(cuentas[i]);
		}
	}
}
