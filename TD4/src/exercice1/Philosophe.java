package exercice1;

public class Philosophe {

	private int numPhilo;
	private int nbFoisMange;
	private int nbBaguettesDetenues;

	public Philosophe(int numPhilo) {
		this.numPhilo = numPhilo;
		this.nbFoisMange = 0;
		this.nbBaguettesDetenues = 0;
	}

	public int getNumPhilo() {
		return numPhilo;
	}

	public int getNbFoisMange() {
		return nbFoisMange;
	}
	public int getNbBaguettesDetenues() {
		return nbBaguettesDetenues;
	}
	
	public synchronized void prendre() {
		if(nbBaguettesDetenues < 2) {
			nbBaguettesDetenues++;
			System.out.println("Le philosophe " + numPhilo + " a maintenant " + nbBaguettesDetenues + " baguettes");
		} else if(nbBaguettesDetenues == 2) {
			System.out.println("Le philosophe " + numPhilo + " mange");
			nbFoisMange++;
			poser();
		}
	}

	public synchronized void poser() {
		System.out.println("Le philosophe " + numPhilo + " pose ses 2 baguettes");
		nbBaguettesDetenues = 0;
	}
}
