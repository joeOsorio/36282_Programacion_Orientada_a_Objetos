/* **************************************************
 * Laboratorio 4: Clase Cajero
 * Contiene la logica de las operaciones del cajero
 * automatico sobre la cuenta con sesion iniciada:
 * depositar, retirar, eliminar cuenta e imprimir
 * recibo. El inicio de sesion lo resuelve la clase
 * Banco; el Cajero solo guarda la cuenta en turno.
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Cajero {

	private Banco banco;
	private CuentaBancaria cuentaActual;
	private String ultimoRecibo;

	public Cajero(Banco banco) {
		this.banco = banco;
		this.cuentaActual = null;
		this.ultimoRecibo = null;
	}

	/* *********************** Sesion ************************/
	public boolean iniciarSesion(int numeroCuenta) {
		cuentaActual = banco.iniciarSesion(numeroCuenta);
		return cuentaActual != null;
	}

	public boolean haySesionActiva() {
		return cuentaActual != null;
	}

	public CuentaBancaria getCuentaActual() {
		return cuentaActual;
	}

	/* Operacion de salir: cierra la sesion en el cajero */
	public void salir() {
		cuentaActual = null;
	}

	/* *********************** Depositar ************************/
	public boolean depositar(double monto) {
		if (!haySesionActiva()) {
			System.out.println("Error: no hay ninguna sesion iniciada.");
			return false;
		}
		boolean exito = cuentaActual.depositar(monto);
		if (exito) {
			imprimirRecibo("Deposito", monto);
		}
		return exito;
	}

	/* *********************** Retirar ************************/
	public boolean retirar(double monto) {
		if (!haySesionActiva()) {
			System.out.println("Error: no hay ninguna sesion iniciada.");
			return false;
		}
		boolean exito = cuentaActual.retirar(monto);
		if (exito) {
			imprimirRecibo("Retiro", monto);
		}
		return exito;
	}

	/* *********************** Eliminar cuenta ************************/
	public boolean eliminarCuenta() {
		if (!haySesionActiva()) {
			System.out.println("Error: no hay ninguna sesion iniciada.");
			return false;
		}
		/* Se captura la informacion antes de que Banco la borre (saldarCuenta) */
		int numeroCuenta = cuentaActual.getNumeroCuenta();
		String titular = cuentaActual.getTitular();
		double saldoFinal = cuentaActual.getSaldo();
		CuentaBancaria cuentaCerrada = cuentaActual;

		boolean exito = banco.eliminarCuenta(numeroCuenta);
		if (exito) {
			/*
			 * historialToString() se pide despues del borrado: el arreglo de
			 * historial sigue intacto y ya incluye la linea de "Cierre de cuenta"
			 */
			imprimirRecibo("Eliminacion de cuenta", saldoFinal, numeroCuenta, titular, saldoFinal,
					cuentaCerrada.historialToString());
			cuentaActual = null;
		} else {
			System.out.println("Error: no se pudo eliminar la cuenta.");
		}
		return exito;
	}

	/* *********************** Recibo ************************/
	/* Aplico sobrecarga de metodos para hacerlo más facil al cidificar */
	private void imprimirRecibo(String operacion, double monto) {
		imprimirRecibo(operacion, monto, cuentaActual.getNumeroCuenta(), cuentaActual.getTitular(),
				cuentaActual.getSaldo(), cuentaActual.historialToString());
	}

	private void imprimirRecibo(String operacion, double monto, int numeroCuenta, String titular,
			double saldoActual, String historial) {
		String mascara = enmascararCuenta(numeroCuenta);
		StringBuilder sb = new StringBuilder();
		sb.append("\n==================================================\n");
		sb.append("                 RECIBO DEL CAJERO                \n");
		sb.append("==================================================\n");
		sb.append(String.format("Operacion:      %s\n", operacion));
		sb.append(String.format("Monto:          $%.2f\n", monto));
		sb.append("--------------- Informacion de cuenta -------------\n");
		sb.append(String.format("Titular:        %s\n", titular));
		sb.append(String.format("No. de cuenta:  %s\n", mascara));
		sb.append(String.format("Saldo actual:   $%.2f\n", saldoActual));
		sb.append("------------------- Historial ---------------------\n");
		sb.append(historial);
		sb.append("==================================================\n");

		ultimoRecibo = sb.toString();
		System.out.println(ultimoRecibo);
	}

	/* Operacion de imprimir recibo: vuelve a mostrar el ultimo recibo generado */
	public void reimprimirUltimoRecibo() {
		if (ultimoRecibo == null) {
			System.out.println("Aun no se ha generado ningun recibo.");
			return;
		}
		System.out.println(ultimoRecibo);
	}

	/* Oculta parte del numero de cuenta por ser informacion sensible */
	private String enmascararCuenta(int numeroCuenta) {
		String numero = String.valueOf(numeroCuenta);
		if (numero.length() <= 2) {
			return numero;
		}
		String visibles = numero.substring(numero.length() - 2);
		StringBuilder oculto = new StringBuilder();
		for (int i = 0; i < numero.length() - 2; i++) {
			oculto.append('*');
		}
		return oculto.append(visibles).toString();
	}
}
