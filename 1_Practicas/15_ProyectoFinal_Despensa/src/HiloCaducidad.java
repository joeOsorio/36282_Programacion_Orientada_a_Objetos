import utils.Lista;

/* ****************************************************************
 * Proyecto Final: Clase HiloCaducidad (Runnable)
 * Corre en segundo plano: cada N segundos revisa la despensa y
 * prepara avisos de lo vencido / por vencer. NO imprime directo
 * (ensuciaria el menu); Main los muestra antes de dibujar el menu.
 * Despensa y la lista de avisos se comparten entre hilos, por eso
 * los metodos de avisos son synchronized y 'activo' es volatile.
 * @Author: Joshua Osorio Osorio
 * @Date:   Noviembre/2026
 * ****************************************************************/
public class HiloCaducidad implements Runnable {
	private final Despensa despensa;
	private final int intervaloSeg;
	private final int diasAviso;

	/* volatile: el cambio que hace main se ve de inmediato en el otro hilo */
	private volatile boolean activo;
	private Thread hilo;

	private Lista<String> avisos;
	private String ultimaFirma;
	private boolean hayNuevos;
	private int revisiones;

	public HiloCaducidad(Despensa despensa, int intervaloSeg, int diasAviso) {
		this.despensa = despensa;
		this.intervaloSeg = Math.max(1, intervaloSeg);
		this.diasAviso = Math.max(0, diasAviso);
		this.avisos = new Lista<>();
		this.ultimaFirma = "";
	}

	/* *********************** Ciclo de vida ************************/
	public void iniciar() {
		if (activo)
			return;
		activo = true;
		hilo = new Thread(this, "HiloCaducidad");
		hilo.setDaemon(true); /* si main termina, este no detiene el programa */
		hilo.start();
	}

	public void detener() {
		activo = false;
		if (hilo != null)
			hilo.interrupt();
	}

	@Override
	public void run() {
		while (activo) {
			revisar();
			try {
				Thread.sleep(intervaloSeg * 1000L);
			} catch (InterruptedException e) {
				break; /* detener() lo despierta para salir rapido */
			}
		}
	}

	/* *********************** Revision ************************/
	/* Publica avisos nuevos solo si cambio algo desde la ultima vez */
	public synchronized void revisar() {
		Lista<String> nuevos = new Lista<>();
		for (var n = despensa.vencidos().begin(); n != null; n = n.sig)
			nuevos.append("VENCIDO   " + n.valor.getNombre() + " - " + n.valor.getDetalle());
		for (var n = despensa.porVencer(diasAviso).begin(); n != null; n = n.sig)
			nuevos.append("POR VENCER " + n.valor.getNombre() + " - " + n.valor.getDetalle());

		String firma = nuevos.toString();
		if (!firma.equals(ultimaFirma)) {
			avisos = nuevos;
			ultimaFirma = firma;
			hayNuevos = !nuevos.estaVacia();
		}
		revisiones++;
	}

	/* Main los pide antes de dibujar el menu; despues ya no son "nuevos" */
	public synchronized Lista<String> tomarAvisosNuevos() {
		if (!hayNuevos)
			return new Lista<>();
		hayNuevos = false;
		return avisos;
	}

	/* Getters */
	public synchronized Lista<String> getAvisos() {
		return avisos;
	}

	public synchronized boolean hayNuevos() {
		return hayNuevos;
	}

	public synchronized int getRevisiones() {
		return revisiones;
	}

	public boolean estaActivo() {
		return activo;
	}

	public int getIntervaloSeg() {
		return intervaloSeg;
	}

	public int getDiasAviso() {
		return diasAviso;
	}
}
