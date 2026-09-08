import java.util.InputMismatchException;
import java.util.Scanner;

/* **************************************************
 * Laboratorio 4: Programa principal - Cajero automatico
 * Orquesta las clases Banco, CuentaBancaria y Cajero:
 * pantalla de inicio (iniciar sesion / crear cuenta) y,
 * ya con sesion iniciada, el menu de operaciones del
 * cajero (depositar, retirar, eliminar cuenta, imprimir
 * recibo, salir).
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Main {

	private static final int CAPACIDAD_BANCO = 20;

	private static Scanner input = new Scanner(System.in);
	private static Banco banco = new Banco(CAPACIDAD_BANCO);
	private static Cajero cajero = new Cajero(banco);

	public static void main(String[] args) {
		int opcion;

		do {
			System.out.println("\n==================================================");
			System.out.println("                  BANCO - INICIO                   ");
			System.out.println("==================================================");
			System.out.println("1 -\tIniciar sesion");
			System.out.println("2 -\tCrear cuenta nueva");
			System.out.println("3 -\tCargar cuentas de prueba");
			System.out.println("0 -\tSalir del programa");
			System.out.printf("\nOpcion:\t");

			opcion = leerEntero();
			switch (opcion) {
				case 1:
					iniciarSesion();
					break;
				case 2:
					crearCuenta();
					break;
				case 3:
					cargarCuentasPrueba();
					break;
				case 4:
					test();
					break;
				case 0:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo.");
			}
		} while (opcion != 0);

		input.close();
	}
	
	/* *********************** Inicio de sesion ************************/
	private static void iniciarSesion() {
		System.out.print("\nNumero de cuenta:\t");
		int numeroCuenta = leerEntero();

		if (cajero.iniciarSesion(numeroCuenta)) {
			System.out.printf("\nBienvenido(a) %s.\n", cajero.getCuentaActual().getTitular());
			menuCajero();
		} else {
			System.out.println("\nNo se encontro ninguna cuenta con ese numero.");
		}
	}

	/* *********************** Crear cuenta ************************/
	private static void crearCuenta() {
		System.out.println("\n---------- Crear cuenta nueva ----------");
		System.out.print("Nombre del titular:\t");
		String titular = input.nextLine();
		System.out.print("Saldo inicial:\t\t");
		double saldoInicial = leerDouble();

		CuentaBancaria nueva = banco.crearCuenta(titular, saldoInicial);
		if (nueva != null) {
			System.out.printf("\nCuenta creada con exito. Numero de cuenta: %d\n", nueva.getNumeroCuenta());
			System.out.println("Conserva este numero, lo necesitas para iniciar sesion.");
		}
	}

	/* *********************** Menu del cajero (con sesion activa) ************************/
	private static void menuCajero() {
		int opcion;

		do {
			System.out.println("\n==================================================");
			System.out.println("                CAJERO AUTOMATICO                  ");
			System.out.println("==================================================");
			System.out.printf("Cuenta:\t%d\tSaldo:\t$%.2f\n", cajero.getCuentaActual().getNumeroCuenta(),
					cajero.getCuentaActual().getSaldo());
			System.out.println("1 -\tDepositar");
			System.out.println("2 -\tRetirar");
			System.out.println("3 -\tEliminar cuenta");
			System.out.println("4 -\tImprimir recibo");
			System.out.println("0 -\tSalir (cerrar sesion)");
			System.out.printf("\nOpcion:\t");

			opcion = leerEntero();
			switch (opcion) {
				case 1:
					depositar();
					break;
				case 2:
					retirar();
					break;
				case 3:
					eliminarCuenta();
					break;
				case 4:
					cajero.reimprimirUltimoRecibo();
					break;
				case 0:
					cajero.salir();
					System.out.println("Sesion cerrada.");
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo.");
			}
		} while (opcion != 0 && cajero.haySesionActiva());
	}

	private static void depositar() {
		System.out.print("\nMonto a depositar:\t");
		double monto = leerDouble();
		cajero.depositar(monto);
	}

	private static void retirar() {
		System.out.print("\nMonto a retirar:\t");
		double monto = leerDouble();
		cajero.retirar(monto);
	}

	private static void eliminarCuenta() {
		System.out.print("\nEsta seguro que desea eliminar la cuenta? (S/N):\t");
		String confirmacion = input.nextLine();
		if (confirmacion.equalsIgnoreCase("S")) {
			cajero.eliminarCuenta();
		} else {
			System.out.println("Operacion cancelada.");
		}
	}

	/* *********************** Datos de prueba (para probar rapido) ************************/
	private static void cargarCuentasPrueba() {
		banco.crearCuenta("Ana Torres", 1500);
		banco.crearCuenta("Luis Perez", 500);
		banco.crearCuenta("Maria Lopez", 10000);
		System.out.println("\nSe crearon 3 cuentas de prueba (numeros 1000, 1001 y 1002).");
	}

	/* *********************** Prueba automatica de todos los metodos ************************/
	/*
	 * Opcion oculta (no se imprime en el menu) para no ensuciar la interfaz
	 * pensada para el usuario final. Usa un Banco y un Cajero de prueba
	 * aparte (no los del programa) para no alterar las cuentas reales, y
	 * recorre todas las operaciones de CuentaBancaria, Banco y Cajero -
	 * validas e invalidas - con datos fijos, sin pedir nada por teclado.
	 */
	private static void test() {
		System.out.println("\n========== TEST: todas las operaciones ==========");

		System.out.println("\n---------- 1) CuentaBancaria (uso directo) ----------");
		CuentaBancaria cuentaPrueba = new CuentaBancaria(9999, "Cuenta de prueba", 1000);
		System.out.println(cuentaPrueba);

		System.out.println("\nDepositar 500 (valido):");
		cuentaPrueba.depositar(500);
		System.out.println(cuentaPrueba);

		System.out.println("\nDepositar -100 (invalido, debe rechazar):");
		cuentaPrueba.depositar(-100);

		System.out.println("\nDepositar 0 (invalido, debe rechazar):");
		cuentaPrueba.depositar(0);

		System.out.println("\nRetirar 300 (valido):");
		cuentaPrueba.retirar(300);
		System.out.println(cuentaPrueba);

		System.out.println("\nRetirar -50 (invalido, debe rechazar):");
		cuentaPrueba.retirar(-50);

		System.out.println("\nRetirar 0 (invalido, debe rechazar):");
		cuentaPrueba.retirar(0);

		System.out.println("\nRetirar 999999 (mayor al saldo, debe rechazar):");
		cuentaPrueba.retirar(999999);

		System.out.println("\nHistorial de la cuenta de prueba:");
		System.out.print(cuentaPrueba.historialToString());

		System.out.println("\nSaldar cuenta de prueba (borra titular, numero y saldo):");
		cuentaPrueba.saldarCuenta();
		System.out.println(cuentaPrueba);

		System.out.println("\n---------- 2) Banco (crear, buscar, eliminar) ----------");
		Banco bancoPrueba = new Banco(5);

		System.out.println("\nCrear cuenta valida (Juan Perez, saldo 2000):");
		CuentaBancaria c1 = bancoPrueba.crearCuenta("Juan Perez", 2000);
		System.out.println(c1);

		System.out.println("\nCrear cuenta con titular vacio (invalido, debe rechazar):");
		bancoPrueba.crearCuenta("", 500);

		System.out.println("\nCrear cuenta con saldo inicial negativo (invalido, debe rechazar):");
		bancoPrueba.crearCuenta("Pedro Ruiz", -100);

		System.out.println("\nCrear una segunda cuenta valida (Maria Diaz, saldo 300):");
		CuentaBancaria c2 = bancoPrueba.crearCuenta("Maria Diaz", 300);
		System.out.println(c2);

		System.out.println("\nBuscar cuenta existente (" + c1.getNumeroCuenta() + "):");
		System.out.println(bancoPrueba.buscarCuenta(c1.getNumeroCuenta()));

		System.out.println("\nBuscar cuenta inexistente (55555):");
		System.out.println(bancoPrueba.buscarCuenta(55555));

		System.out.println("\nEliminar cuenta existente (" + c2.getNumeroCuenta() + "):");
		boolean eliminada = bancoPrueba.eliminarCuenta(c2.getNumeroCuenta());
		System.out.println("Eliminada: " + eliminada + " | Cuentas restantes: " + bancoPrueba.getContador());

		System.out.println("\nEliminar cuenta inexistente (55555):");
		System.out.println("Eliminada: " + bancoPrueba.eliminarCuenta(55555));

		System.out.println("\n---------- 3) Cajero (sesion, depositar, retirar, recibo, eliminar) ----------");
		Cajero cajeroPrueba = new Cajero(bancoPrueba);

		System.out.println("\nIniciar sesion con numero inexistente (debe fallar):");
		System.out.println("Sesion iniciada: " + cajeroPrueba.iniciarSesion(55555));

		System.out.println("\nIniciar sesion con cuenta valida (" + c1.getNumeroCuenta() + "):");
		System.out.println("Sesion iniciada: " + cajeroPrueba.iniciarSesion(c1.getNumeroCuenta()));

		System.out.println("\nDepositar 700 con sesion activa (debe imprimir recibo):");
		cajeroPrueba.depositar(700);

		System.out.println("\nDepositar -50 con sesion activa (invalido, sin recibo):");
		cajeroPrueba.depositar(-50);

		System.out.println("\nRetirar 200 con sesion activa (debe imprimir recibo):");
		cajeroPrueba.retirar(200);

		System.out.println("\nRetirar 999999 con sesion activa (invalido, sin recibo):");
		cajeroPrueba.retirar(999999);

		System.out.println("\nReimprimir el ultimo recibo generado:");
		cajeroPrueba.reimprimirUltimoRecibo();

		System.out.println("\nCerrar sesion (operacion salir):");
		cajeroPrueba.salir();
		System.out.println("Hay sesion activa: " + cajeroPrueba.haySesionActiva());

		System.out.println("\nIntentar depositar sin sesion activa (debe rechazar):");
		cajeroPrueba.depositar(100);

		System.out.println("\nVolver a iniciar sesion para probar eliminar cuenta:");
		cajeroPrueba.iniciarSesion(c1.getNumeroCuenta());

		System.out.println("\nEliminar cuenta desde el cajero (recibo final y cierre de sesion):");
		cajeroPrueba.eliminarCuenta();
		System.out.println("Hay sesion activa: " + cajeroPrueba.haySesionActiva());

		System.out.println("\nIntentar iniciar sesion con la cuenta ya eliminada (debe fallar):");
		System.out.println("Sesion iniciada: " + cajeroPrueba.iniciarSesion(c1.getNumeroCuenta()));

		System.out.println("\n========== FIN DEL TEST ==========");
	}

	/* *********************** Lectura segura de datos ************************/
	private static int leerEntero() {
		while (true) {
			try {
				int valor = input.nextInt();
				input.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.print("Valor invalido, ingrese un numero entero:\t");
				input.nextLine();
			}
		}
	}

	private static double leerDouble() {
		while (true) {
			try {
				double valor = input.nextDouble();
				input.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.print("Valor invalido, ingrese un numero:\t");
				input.nextLine();
			}
		}
	}
}
