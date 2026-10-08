/* **************************************************
 * La pesona infectada. Tiene energia, una defensa
 * temporal (Corazon) y sus 6 organos en un arreglo
 * de Organo (composicion: los crea en su constructor).
 * @Author: J03 O^2
 * @Date:   Octubre/2026
 * **************************************************/
public class Persona {

	public static final int ENERGIA_INICIAL = 2048, MAX_ORGANOS = 6;

	private int energia, defensa, turnosDefensa, contador;
	private String nombre;
	private Organo[] organos;

	public Persona(String nombre) {
		this.nombre = nombre;
		this.energia = ENERGIA_INICIAL;
		this.organos = new Organo[MAX_ORGANOS];
		this.defensa = this.turnosDefensa = this.contador = 0;

		/* Composicion y upcasting: cada clase hija se guarda como Organo */
		agregarOrgano(new Cerebro());
		agregarOrgano(new Corazon());
		agregarOrgano(new Estomago());
		agregarOrgano(new Pancreas());
		agregarOrgano(new Rinones());
		agregarOrgano(new Intestino());
	}

	/* *********************** Gestion de organos ************************/
	public boolean agregarOrgano(Organo organo) {
		if (organo == null) {
			System.out.println("Error: el organo no existe.");
			return false;
		}
		if (contador >= MAX_ORGANOS) {
			System.out.println("Error: la persona ya tiene sus " + MAX_ORGANOS + " organos.");
			return false;
		}
		organos[contador++] = organo;
		return true;
	}

	public Organo getOrgano(int indice) {
		if (indice < 0 || indice >= contador) {
			System.out.println("Error: indice de organo no valido.");
			return null;
		}
		return organos[indice];
	}

	/* *********************** Combate ************************/
	/* Cada ataque recibido cuenta como un salto y consume un turno de defensa. */
	public void recibirAtaque(int danio) {
		if (danio < 0) {
			danio = 0;
		}
		int real = Math.max(0, danio - defensa);
		energia = Math.max(0, energia - real);
		System.out.println("\t   " + nombre + " recibe " + real + " de danio"
				+ (defensa > 0 ? " (bloqueo " + Math.min(defensa, danio) + ")" : "") + " -> energia " + energia);
		actualizarTurno();
	}

	public void restaurarEnergia(int cantidad) {
		if (cantidad <= 0 || !estaViva()) {
			return;
		}
		energia = Math.min(ENERGIA_INICIAL, energia + cantidad);
		System.out.println("\t   " + nombre + " recupera " + cantidad + " -> energia " + energia);
	}

	public void aumentarDefensa(int cantidad, int turnos) {
		if (turnosDefensa > 0) {
			turnosDefensa++; /* Si ya existe, solo se refresca 1 turno mas */
			System.out.println("\t   Defensa ya activa, se refresca: " + turnosDefensa + " saltos.");
			return;
		}
		defensa = cantidad;
		turnosDefensa = turnos;
		System.out.println("\t   " + nombre + " gana +" + cantidad + " de defensa por " + turnos + " saltos.");
	}

	public void actualizarTurno() {
		if (turnosDefensa > 0) {
			turnosDefensa--;
			if (turnosDefensa == 0) {
				defensa = 0;
				System.out.println("\t   Se acabo la defensa extra de " + nombre + ".");
			}
		}
	}

	public boolean estaViva() {
		return energia > 0;
	}

	/* Getters */
	public String getNombre() {
		return nombre;
	}

	public int getEnergia() {
		return energia;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getTurnosDefensa() {
		return turnosDefensa;
	}

	public int getContador() {
		return contador;
	}

	/* *********************** Informacion ************************/
	@Override
	public String toString() {
		return String.format("%-12s | Energia: %4d/%d | Defensa: %2d (%d saltos)",
				nombre, energia, ENERGIA_INICIAL, defensa, turnosDefensa);
	}
}
