/* **************************************************
 * Controla la batalla: en cada turno, cada microbio
 * vivo salta a un organo al azar de la persona.
 * Termina cuando mueren todos los microbios o la persona.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Simulacion {

	public static final int MAX_MICROBIOS = 4;

	private Persona persona;
	private Microbio[] microbios;
	private int contador, turno;

	public Simulacion(Persona persona) {
		if (persona == null) {
			System.out.println("Error: la simulacion necesita una persona.");
		}
		this.persona = persona;
		this.microbios = new Microbio[MAX_MICROBIOS];
		this.contador = 0;
		this.turno = 0;
	}

	/* *********************** Gestion de microbios ************************/
	public boolean agregarMicrobio(Microbio microbio) {
		if (microbio == null) {
			System.out.println("Error: el microbio no existe.");
			return false;
		}
		if (contador >= MAX_MICROBIOS) {
			System.out.println("Error: ya hay " + MAX_MICROBIOS + " microbios en el cuerpo.");
			return false;
		}
		for (int i = 0; i < contador; i++) {
			if (microbios[i].getId() == microbio.getId()) {
				System.out.println("Error: ya existe un microbio con el id " + microbio.getId() + ".");
				return false;
			}
		}
		microbios[contador++] = microbio;
		return true;
	}

	public boolean quedanMicrobios() {
		for (int i = 0; i < contador; i++) {
			if (microbios[i].estaVivo()) {
				return true;
			}
		}
		return false;
	}

	/* *********************** Batalla ************************/
	public void iniciar() {
		if (persona == null) {
			System.out.println("Error: no hay persona para infectar.");
			return;
		}
		if (contador == 0) {
			System.out.println("Error: no hay microbios en la simulacion.");
			return;
		}
		if (persona.getContador() == 0) {
			System.out.println("Error: la persona no tiene organos.");
			return;
		}

		System.out.println("\n=============== INICIA LA BATALLA ===============");
		mostrarEstado();
		while (!hayGanador()) {
			turno++;
			System.out.println("\n------------------- Turno " + turno + " -------------------");
			ejecutarTurno();
			mostrarEstado();
		}

		System.out.println("\n================ FIN DE LA BATALLA ================");
		if (persona.estaViva()) {
			System.out.println("GANA " + persona.getNombre() + ": elimino a todos los microbios en " + turno
					+ " turnos (energia final " + persona.getEnergia() + ").");
		} else {
			System.out.println("GANAN LOS MICROBIOS: " + persona.getNombre() + " se quedo sin energia en el turno "
					+ turno + ".");
		}
	}

	private void ejecutarTurno() {
		for (int i = 0; i < contador; i++) {
			if (hayGanador()) {
				return; /* La batalla puede acabar a mitad del turno */
			}
			if (!microbios[i].estaVivo()) {
				continue;
			}
			Organo destino = elegirOrgano();
			System.out.println("\n\t-> Microbio #" + microbios[i].getId() + " salta a " + destino.getNombre());
			microbios[i].saltar(destino, persona);
		}
	}

	private Organo elegirOrgano() {
		int indice = (int) (Math.random() * persona.getContador());
		return persona.getOrgano(indice);
	}

	private boolean hayGanador() {
		return !persona.estaViva() || !quedanMicrobios();
	}

	/* *********************** Informacion ************************/
	public void mostrarEstado() {
		System.out.println("\n  " + persona);
		for (int i = 0; i < contador; i++) {
			System.out.println("  " + microbios[i]);
		}
	}

	public int getTurno() {
		return turno;
	}
}
