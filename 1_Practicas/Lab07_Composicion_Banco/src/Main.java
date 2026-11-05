import java.util.InputMismatchException; /* Para el try catch */
import java.util.Scanner;

/* **************************************************
 * Laboratorio 7: Programa principal
 * Orquesta las clases Banco y Cajero (que a su vez
 * orquesta a CuentaBancaria). El Banco NO es el main.
 * @Author: J03 O^2
 * @Date:   Septiembre/2026
 * **************************************************/
public class Main {

	private static Scanner input = new Scanner(System.in);
	private static Banco banco = new Banco();
	private static Cajero cajero = new Cajero(banco);

	public static void main(String[] args) {
		int opcion;

		do {
			System.out.println("==================================================");
			System.out.println("   BANCO - SISTEMA DE CAJERO AUTOMATICO   ");
			System.out.println("==================================================");
			System.out.println("(Modo demo) Super usuario -> usuario: " + Banco.USUARIO_SUPER
					+ "  clave: " + Banco.CLAVE_SUPER);
			imprimirEstadoSesion();
			/*
			 * System.out.println("\n1  -\tIniciar sesion como super usuario");
			 * System.out.println("2  -\tCerrar sesion de super usuario");
			 * System.out.println("3  -\tAbrir cuenta");
			 * System.out.println("4  -\tCerrar cuenta (administrativo)");
			 * System.out.println("5  -\tMostrar cuentas");
			 * System.out.println("6  -\tIniciar sesion en el cajero");
			 * System.out.println("7  -\tCerrar sesion del cajero");
			 * System.out.println("8  -\tRetirar");
			 * System.out.println("9  -\tAbonar");
			 * System.out.println("10 -\tCambiar PIN");
			 * System.out.println("11 -\tEliminar mi cuenta");
			 * System.out.println("12 -\tImprimir recibo");
			 */
			System.out.printf("\n1  -\tTest\n");
			System.out.println("0  -\tSalir del programa");
			System.out.printf("\nOpcion:\t");

			opcion = leerEntero();
			switch (opcion) {
				case 1:
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

	/* *********************** Sesion ************************/
	private static void imprimirEstadoSesion() {
		if (banco.haySesionSuperUsuario()) {
			System.out.println("\n[Sesion activa: SUPER USUARIO]");
		} else if (cajero.haySesion()) {
			System.out.println("\n[Sesion activa: Cuenta #" + cajero.getCuentaActual().getNumeroCuenta()
					+ " (" + cajero.getCuentaActual().getTitular() + ")]");
		} else {
			System.out.println("\n[Sin sesion iniciada]");
		}
	}

	/*
	 * *********************** Prueba automatica de todos los metodos
	 ************************/
	private static void test() {
		System.out.println("\n========== TEST: todas las operaciones ==========");

		Banco bancoPrueba = new Banco();
		Cajero cajeroPrueba = new Cajero(bancoPrueba);

		System.out.println("\n---------- 1) Login de super usuario ----------");
		System.out.println("Credenciales invalidas (debe rechazar): "
				+ bancoPrueba.loginSuperUsuario("hacker", "1234"));
		System.out.println("Credenciales validas (debe aceptar): "
				+ bancoPrueba.loginSuperUsuario(Banco.USUARIO_SUPER, Banco.CLAVE_SUPER));

		System.out.println("\n---------- 2) Sin sesion de super usuario no se puede abrir cuenta ----------");
		Banco bancoSinSesion = new Banco();
		System.out.println("Intentar abrir cuenta sin login (invalido, debe rechazar): "
				+ (bancoSinSesion.abrirCuenta("Nadie", 0) == null));

		System.out.println("\n---------- 3) Abrir cuentas ----------");
		CuentaBancaria c1 = bancoPrueba.abrirCuenta("Yuliana Flores", 500);
		System.out.println("Abierta: " + c1);
		CuentaBancaria c2 = bancoPrueba.abrirCuenta("Zide Quintero", 1000);
		System.out.println("Abierta: " + c2);
		CuentaBancaria c3 = bancoPrueba.abrirCuenta("Andrea Mercado", 0);
		System.out.println("Abierta: " + c3);

		System.out.println("\nAbrir con titular vacio (invalido, debe rechazar):");
		bancoPrueba.abrirCuenta("", 100);

		System.out.println("\nAbrir con saldo inicial negativo (invalido, debe rechazar):");
		bancoPrueba.abrirCuenta("Alguien", -50);

		System.out.println("\n---------- 4) Login en el cajero ----------");
		System.out.println("Numero o PIN incorrectos (invalido, debe rechazar): "
				+ cajeroPrueba.login(c1.getNumeroCuenta(), "0000"));
		System.out.println("Credenciales validas (debe aceptar): "
				+ cajeroPrueba.login(c1.getNumeroCuenta(), c1.getPin()));

		System.out.println("\n---------- 5) Solo una sesion a la vez en el cajero ----------");
		System.out.println("Intentar loguear otra cuenta con sesion ya activa (invalido, debe rechazar): "
				+ cajeroPrueba.login(c2.getNumeroCuenta(), c2.getPin()));

		System.out.println("\n---------- 6) Retirar / abonar (cuenta de Karla, sesion activa) ----------");
		System.out.println("Abonar 200 (valido, debe aceptar): " + cajeroPrueba.abonar(200));
		System.out.println("Retirar monto negativo (invalido, debe rechazar): " + cajeroPrueba.retirar(-10));
		System.out.println("Retirar mas saldo del disponible (invalido, debe rechazar): "
				+ cajeroPrueba.retirar(999999));
		System.out.println("Retirar 300 (valido, debe aceptar): " + cajeroPrueba.retirar(300));
		System.out.println("Saldo esperado 500 + 200 - 300 = 400. Cuenta actual: " + c1);

		System.out.println("\n---------- 7) Sin sesion no se puede operar ----------");
		Banco bancoAux = new Banco();
		Cajero cajeroSinSesion = new Cajero(bancoAux);
		System.out.println("Retirar sin sesion (invalido, debe rechazar): " + cajeroSinSesion.retirar(10));
		System.out.println("Abonar sin sesion (invalido, debe rechazar): " + cajeroSinSesion.abonar(10));

		System.out.println("\n---------- 8) Cambiar PIN ----------");
		System.out.println("PIN actual incorrecto (invalido, debe rechazar): "
				+ cajeroPrueba.cambiarPin("0000", "9999"));
		System.out.println("PIN nuevo con formato invalido (invalido, debe rechazar): "
				+ cajeroPrueba.cambiarPin(c1.getPin(), "abc"));
		String pinViejo = c1.getPin();
		System.out.println("Cambio valido (debe aceptar): " + cajeroPrueba.cambiarPin(pinViejo, "5555"));

		System.out.println("\n---------- 9) Recibo ----------");
		cajeroPrueba.imprimirRecibo();

		System.out.println("\n---------- 10) Logout y volver a entrar con el PIN nuevo ----------");
		cajeroPrueba.logout();
		System.out.println("Entrar con el PIN viejo (invalido, debe rechazar): "
				+ cajeroPrueba.login(c1.getNumeroCuenta(), pinViejo));
		System.out.println("Entrar con el PIN nuevo (valido, debe aceptar): "
				+ cajeroPrueba.login(c1.getNumeroCuenta(), "5555"));

		System.out.println("\n---------- 11) Eliminar mi cuenta desde el cajero ----------");
		System.out.println("Eliminar cuenta activa (valido, debe aceptar): " + cajeroPrueba.eliminarCuentaActual());
		System.out.println("Hay sesion despues de eliminar (debe ser false): " + cajeroPrueba.haySesion());
		System.out.println("Intentar volver a entrar con esa cuenta ya eliminada (invalido, debe rechazar): "
				+ cajeroPrueba.login(c1.getNumeroCuenta(), "5555"));

		System.out.println("\n---------- 12) Cerrar cuenta de forma administrativa (super usuario) ----------");
		System.out.println("Numero inexistente (invalido, debe rechazar): " + bancoPrueba.cerrarCuenta(9999));
		System.out.println("Numero valido, cuenta de Hector (debe aceptar): "
				+ bancoPrueba.cerrarCuenta(c2.getNumeroCuenta()));

		System.out.println("\n---------- 13) Estado final ----------");
		bancoPrueba.imprimirCuentas();

		System.out.println("\n---------- 14) Logout de super usuario ----------");
		bancoPrueba.logout();
		System.out.println("Hay sesion de super usuario despues del logout (debe ser false): "
				+ bancoPrueba.haySesionSuperUsuario());

		System.out.println("\n========== FIN DEL TEST ==========");
	}

	/* *********************** Lectura segura de datos ************************/
	private static int leerEntero() {
		while (true) {
			try {
				int valor = input.nextInt();
				input.nextLine(); /* importante limpiar el buffer. */
				return valor;
			} catch (InputMismatchException e) {
				System.out.print("Valor invalido:\t");
				input.nextLine();
			}
		}
	}

	private static double leerDouble() {
		while (true) {
			try {
				double valor = input.nextDouble();
				input.nextLine(); /* importante limpiar el buffer. */
				return valor;
			} catch (InputMismatchException e) {
				System.out.print("Valor invalido:\t");
				input.nextLine();
			}
		}
	}
}
