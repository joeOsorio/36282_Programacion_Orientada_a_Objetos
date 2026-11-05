/* **************************************************
 * Viaja de organo en organo: en cada salto ataca a la
 * persona y luego recibe el efecto del organo
 * (dynamic dispatching sobre Organo).
 * En español quiere decir que no importa se le paso un corzon o cerebro estaran en el mismo congunto de organos y en tiempo * de ejecucion del programa sabra como tratar a cada uno.
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Microbio {

	public static final int ENERGIA_INICIAL = 256, ATAQUE = 40;

	private int id, energia, turnosDebilitado;
	private int defensa; /* 0 normal, negativa cuando el Cerebro la debilita */
	private Organo organoActual;

	public Microbio(int id) {
		this.id = id;
		this.energia = ENERGIA_INICIAL;
		this.defensa = 0;
		this.turnosDebilitado = 0;
		this.organoActual = null;
	}

	/* *********************** Movimiento ************************/
	public void saltar(Organo organo, Persona persona) {
		if (organo == null || persona == null) {
			System.out.println("Error: el microbio necesita un organo y una persona.");
			return;
		}
		if (!estaVivo()) {
			System.out.println("Error: el microbio #" + id + " ya no tiene energia.");
			return;
		}
		organoActual = organo;
		atacar(persona); /* "dynamic dispatching" Siempre ataca, sin importar el organo */
		actualizarTurno();
		organo.aplicarEfecto(persona, this); /* Polimorfismo: corre la version del organo real */
		/* this hace referencia al mismo objeto que lo ejecuta */
	}

	public void atacar(Persona persona) {
		if (persona == null) {
			return;
		}
		System.out.println("\t   Microbio #" + id + " ataca con " + ATAQUE + ".");
		persona.recibirAtaque(ATAQUE);
	}

	/* *********************** Combate ************************/
	/* Defensa negativa 'baja' = recibe danio extra */
	public void recibirDanio(int danio) {
		if (danio < 0) {
			danio = 0;
		}
		int real = Math.max(0, danio - defensa); /* Elijo el valor maximo */
		energia = Math.max(0, energia - real);
		System.out.println("\t   Microbio #" + id + " pierde " + real
				+ (defensa < 0 ? " (incluye " + (-defensa) + " extra por estar debilitado)" : "")
				+ " -> energia " + energia + (estaVivo() ? "" : "  *** ELIMINADO ***"));
	}

	public void restaurarEnergia(int cantidad) {
		if (cantidad <= 0 || !estaVivo()) {
			return;
		}
		energia = Math.min(ENERGIA_INICIAL, energia + cantidad); /* Elege el valor minimo */
		System.out.println("\t   Microbio #" + id + " recupera " + cantidad + " -> energia " + energia);
	}

	public void debilitar(int cantidad, int turnos) {
		if (turnosDebilitado > 0) {
			turnosDebilitado++; /* Si ya esta debilitado, solo se refresca 1 turno mas */
			System.out.println("\t   Microbio #" + id + " sigue debilitado: " + turnosDebilitado + " saltos.");
			return;
		}
		defensa = -cantidad;
		turnosDebilitado = turnos;
		System.out.println("\t   Microbio #" + id + " queda con defensa " + defensa + " por " + turnos + " saltos.");
	}

	public void actualizarTurno() {
		if (turnosDebilitado > 0) {
			turnosDebilitado--;
			if (turnosDebilitado == 0) {
				defensa = 0;
				System.out.println("\t   Microbio #" + id + " recupera sus defensas.");
			}
		}
	}

	public boolean estaVivo() {
		return energia > 0;
	}

	/* Getters */
	public int getId() {
		return id;
	}

	public int getEnergia() {
		return energia;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getTurnosDebilitado() {
		return turnosDebilitado;
	}

	public Organo getOrganoActual() {
		return organoActual;
	}

	/* *********************** Informacion ************************/
	@Override
	public String toString() {
		if (!estaVivo()) {
			return "Microbio #" + id + "  | ELIMINADO";
		}
		return String.format("Microbio #%d  | Energia: %3d/%d | Defensa: %3d (%d saltos) | En: %s",
				id, energia, ENERGIA_INICIAL, defensa, turnosDebilitado,
				organoActual == null ? "-" : organoActual.getNombre());
	}
}
