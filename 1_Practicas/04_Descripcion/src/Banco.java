/* **************************************************
 * Laboratorio 4: Clase Banco
 * Administra el arreglo de cuentas bancarias (la
 * "base de datos"): crear cuentas, eliminarlas e
 * iniciar sesion con el numero de cuenta.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Banco {

	private CuentaBancaria[] cuentas;
	private int contador;
	private int siguienteNumeroCuenta;

	public Banco(int capacidad) {
		cuentas = new CuentaBancaria[capacidad];
		contador = 0;
		siguienteNumeroCuenta = 1000;
	}

	/* *********************** Crear cuenta ************************/
	public CuentaBancaria crearCuenta(String titular, double saldoInicial) {
		if (contador >= cuentas.length) {
			System.out.println("Error: el banco alcanzo el maximo de cuentas registradas.");
			return null;
		}
		if (titular == null || titular.trim().isEmpty()) {
			System.out.println("Error: el titular no puede estar vacio.");
			return null;
		}
		if (saldoInicial < 0) {
			System.out.println("Error: el saldo inicial no puede ser negativo.");
			return null;
		}
		CuentaBancaria nueva = new CuentaBancaria(siguienteNumeroCuenta, titular, saldoInicial);
		cuentas[contador++] = nueva;
		siguienteNumeroCuenta++;
		return nueva;
	}

	/************************ Buscar cuenta ************************/
	public CuentaBancaria buscarCuenta(int numeroCuenta) {
		for (int i = 0; i < contador; i++) {
			if (cuentas[i].getNumeroCuenta() == numeroCuenta) {
				return cuentas[i];
			}
		}
		return null;
	}

	/* *********************** Iniciar sesion ************************/
	/*
	 * Solo se pide el numero de cuenta: con eso es suficiente para entrar poque no
	 * hare lo de login complejo.
	 */
	public CuentaBancaria iniciarSesion(int numeroCuenta) {
		return buscarCuenta(numeroCuenta);
	}

	/* *********************** Eliminar cuenta ************************/
	public boolean eliminarCuenta(int numeroCuenta) {
		for (int i = 0; i < contador; i++) {
			if (cuentas[i].getNumeroCuenta() == numeroCuenta) {
				cuentas[i].saldarCuenta();
				for (int j = i; j < contador - 1; j++) {
					cuentas[j] = cuentas[j + 1];
				}
				cuentas[contador - 1] = null; /* importante para no tener duplicada la ultima cuenta */
				contador--;
				return true;
			}
		}
		return false;
	}

	public int getContador() {
		return contador;
	}
}
