/* **************************************************
 * Laboratorio 6: Clase Veterinaria
 * Administra los doctores y las mascotas de 
 * la veterinaria, y controla el login
 * del administrador y de doctores.
 * @Author: Joshua Osorio Osorio
 * @Date:   Septiembre/2026
 * **************************************************/
public class Veterinaria {

	/*
	 * En un sistema real esto NUNCA se dejaria fijo en el codigo fuente, por temas
	 * de seguridad.
	 */
	public static final String USUARIO_SUPER = "root";
	public static final String CLAVE_SUPER = "admin";

	/*
	 * Composicion: la Veterinaria crea y controla el ciclo de vida de sus doctores
	 */
	private Doctor[] doctores;
	private int contadorDoctores;
	private int siguienteNumeroEmpleado;

	/*
	 * Composicion: la Veterinaria crea y controla el ciclo de vida de sus mascotas
	 */
	private Mascota[] mascotas;
	private int contadorMascotas;
	private int siguienteIdMascota;

	/* Sesion activa */
	private Doctor doctorActual;
	private boolean superUsuarioActivo;

	public Veterinaria() {
		this.doctores = new Doctor[10];
		this.contadorDoctores = 0;
		this.siguienteNumeroEmpleado = 1;

		this.mascotas = new Mascota[50];
		this.contadorMascotas = 0;
		this.siguienteIdMascota = 1;

		this.doctorActual = null;
		this.superUsuarioActivo = false;
	}

	/* *********************** Sesion ************************/
	public boolean loginSuperUsuario(String usuario, String clave) {
		if (!USUARIO_SUPER.equals(usuario) || !CLAVE_SUPER.equals(clave)) {
			System.out.println("Error: usuario o clave de administrador incorrectos.");
			return false;
		}
		superUsuarioActivo = true;
		return true;
	}

	public boolean loginDoctor(String usuario, String clave) {
		Doctor doctor = buscarDoctorPorUsuario(usuario);
		if (doctor == null || !doctor.verificarClave(clave)) {
			System.out.println("Error: usuario o clave de doctor incorrectos.");
			return false;
		}
		doctorActual = doctor;
		if (doctor.estaSuspendido()) {
			System.out.println("Advertencia: tu cuenta esta suspendida, no podras dar de alta,");
			System.out.println("dar de baja ni diagnosticar mascotas hasta que te reactiven.");
		}
		return true;
	}

	public void logout() {
		doctorActual = null;
		superUsuarioActivo = false;
	}

	public boolean haySesionSuperUsuario() {
		return superUsuarioActivo;
	}

	public boolean haySesionDoctor() {
		return doctorActual != null;
	}

	public Doctor getDoctorActual() {
		return doctorActual;
	}

	/*************************************************
	 * Gestion de doctores (requiere se administrador)
	 ************************************************/
	public Doctor contratarDoctor(String nombre, String telefono, String usuario, String clave) {
		if (!superUsuarioActivo) {
			System.out.println("Error: se necesita sesion de administrador para contratar doctores.");
			return null;
		}
		if (contadorDoctores >= doctores.length) {
			System.out.println("Error: ya se alcanzo el limite de doctores (10).");
			return null;
		}
		if (nombre == null || nombre.isEmpty() || usuario == null || usuario.isEmpty()
				|| clave == null || clave.isEmpty()) {
			System.out.println("Error: nombre, usuario y clave son obligatorios.");
			return null;
		}
		if (buscarDoctorPorUsuario(usuario) != null) {
			System.out.println("Error: ya existe un doctor con ese usuario.");
			return null;
		}

		Doctor nuevo = new Doctor(nombre, telefono, siguienteNumeroEmpleado, usuario, clave);
		siguienteNumeroEmpleado++;
		doctores[contadorDoctores] = nuevo;
		contadorDoctores++;
		return nuevo;

		/*
		 * Si solo un atributo esta mal te regresara null, creo que esta parte de la
		 * comprovacionse deberia de manejar por el main o en donde se captura la
		 * informacion.
		 */
	}

	public boolean despedirDoctor(int numeroEmpleado) {
		if (!superUsuarioActivo) {
			System.out.println("Error: se necesita sesion de administrador para despedir doctores.");
			return false;
		}
		int indice = indiceDoctorPorNumero(numeroEmpleado);
		if (indice == -1) {
			System.out.println("Error: no existe un doctor con ese numero de empleado.");
			return false;
		}

		/* Si el doctor despedido tenia la sesion abierta, se cierra */
		if (doctorActual != null && doctorActual.getNumeroEmpleado() == numeroEmpleado) {
			doctorActual = null;
		}

		/* Recorre el arreglo para no dejar huecos */
		for (int i = indice; i < contadorDoctores - 1; i++) {
			doctores[i] = doctores[i + 1];
		}
		doctores[contadorDoctores - 1] = null;
		contadorDoctores--;
		return true;
	}

	public boolean suspenderDoctor(int numeroEmpleado) {
		if (!superUsuarioActivo) {
			System.out.println("Error: se necesita sesion de administrador para suspender/reactivar doctores.");
			return false;
		}
		Doctor doctor = buscarDoctorPorNumero(numeroEmpleado);
		if (doctor == null) {
			System.out.println("Error: no existe un doctor con ese numero de empleado.");
			return false;
		}

		/*
		 * Alterna el estado: si estaba activo lo suspende, si estaba suspendido lo
		 * reactiva
		 */
		doctor.setSuspendido(!doctor.estaSuspendido());
		return true;
	}

	public Doctor buscarDoctorPorUsuario(String usuario) {
		for (int i = 0; i < contadorDoctores; i++) {
			if (doctores[i].getUsuario().equals(usuario)) {
				return doctores[i];
			}
		}
		return null;
	}

	public Doctor buscarDoctorPorNumero(int numeroEmpleado) {
		int indice = indiceDoctorPorNumero(numeroEmpleado);
		return indice == -1 ? null : doctores[indice];
	}

	private int indiceDoctorPorNumero(int numeroEmpleado) {
		for (int i = 0; i < contadorDoctores; i++) {
			if (doctores[i].getNumeroEmpleado() == numeroEmpleado) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * *********************** Gestion de mascotas (requiere doctor logueado y no
	 * suspendido)
	 ************************/
	public Mascota darDeAltaMascota(String nombre, String especie, int edad, String nombreDueno, String telefonoDueno) {
		if (!puedeOperarComoDoctor()) {
			return null;
		}
		if (contadorMascotas >= mascotas.length) {
			System.out.println("Error: ya se alcanzo el limite de mascotas registradas (50).");
			return null;
		}
		if (nombre == null || nombre.isEmpty() || nombreDueno == null || nombreDueno.isEmpty()) {
			System.out.println("Error: el nombre de la mascota y el del dueno son obligatorios.");
			return null;
		}
		if (edad < 0) {
			System.out.println("Error: la edad no puede ser negativa.");
			return null;
		}

		Mascota nueva = new Mascota(siguienteIdMascota, nombre, especie, edad, nombreDueno, telefonoDueno);
		siguienteIdMascota++;
		mascotas[contadorMascotas] = nueva;
		contadorMascotas++;
		return nueva;
	}

	public boolean darDeBajaMascota(int id) {
		if (!puedeOperarComoDoctor()) {
			return false;
		}
		int indice = indiceMascotaPorId(id);
		if (indice == -1) {
			System.out.println("Error: no existe una mascota con ese id.");
			return false;
		}

		for (int i = indice; i < contadorMascotas - 1; i++) {
			mascotas[i] = mascotas[i + 1];
		}
		mascotas[contadorMascotas - 1] = null;
		contadorMascotas--;
		return true;
	}

	public boolean darDiagnostico(int id, String diagnostico) {
		if (!puedeOperarComoDoctor()) {
			return false;
		}
		if (diagnostico == null || diagnostico.isEmpty()) {
			System.out.println("Error: el diagnostico no puede estar vacio.");
			return false;
		}
		Mascota mascota = buscarMascota(id);
		if (mascota == null) {
			System.out.println("Error: no existe una mascota con ese id.");
			return false;
		}

		mascota.setDiagnostico(diagnostico);
		doctorActual.registrarAtencion(mascota);
		return true;
	}

	public Mascota buscarMascota(int id) {
		int indice = indiceMascotaPorId(id);
		return indice == -1 ? null : mascotas[indice];
	}

	private int indiceMascotaPorId(int id) {
		for (int i = 0; i < contadorMascotas; i++) {
			if (mascotas[i].getId() == id) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * Validacion comun a las 3 acciones de mascotas: requiere doctor logueado y no
	 * suspendido
	 */
	private boolean puedeOperarComoDoctor() {
		if (doctorActual == null) {
			System.out.println("Error: se necesita iniciar sesion como doctor.");
			return false;
		}
		if (doctorActual.estaSuspendido()) {
			System.out.println("Error: tu cuenta esta suspendida, no puedes realizar esta accion.");
			return false;
		}
		return true;
	}

	/* *********************** Informacion ************************/
	public void imprimirDoctores() {
		if (contadorDoctores == 0) {
			System.out.println("No hay doctores registrados.");
			return;
		}
		for (int i = 0; i < contadorDoctores; i++) {
			System.out.println(doctores[i]);
		}
	}

	public void imprimirMascotas() {
		if (contadorMascotas == 0) {
			System.out.println("No hay mascotas registradas.");
			return;
		}
		for (int i = 0; i < contadorMascotas; i++) {
			System.out.println(mascotas[i]);
		}
	}
}
