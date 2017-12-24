package exercice1;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class LanceurDiner implements Runnable {

	private ArrayList<Baguette> baguettes;
	private ArrayList<Philosophe> philosophes;
	private final ReentrantLock myLock = new ReentrantLock();

	public LanceurDiner(ArrayList<Baguette> baguettes, ArrayList<Philosophe> philosophes) {
		this.baguettes = baguettes;
		this.philosophes = philosophes;
	}

	public void run() {
	/*	myLock.lock();  // block until condition holds
		try {
			// ... method body
		} finally {
			myLock.unlock();
		}*/
		for (int i = 0; i < philosophes.size(); i++) {
			// Check baguette not owned by philo
			if(i < philosophes.size()-1) {
				if(philosophes.get(i+1).getNbBaguettesDetenues() <= 1) {
					philosophes.get(i).prendre();
					baguettes.get(i).setProprietaire(philosophes.get(i));
				}
			} else {
				if(philosophes.get(0).getNbBaguettesDetenues() <= 1) {
					philosophes.get(i).prendre();
					baguettes.get(i).setProprietaire(philosophes.get(i));
				}
			}
		}
	}

	public static void main(String[] args) {
		int nbPersonnes = 5;
		ArrayList<Baguette> baguettes = new ArrayList<Baguette>();
		ArrayList<Philosophe> philosophes = new ArrayList<Philosophe>();
		for (int i = 0; i < nbPersonnes; i++) {
			Philosophe p = new Philosophe(i);
			Baguette b = new Baguette();
			baguettes.add(b);
			philosophes.add(p);
		}
		LanceurDiner ld = new LanceurDiner(baguettes, philosophes);
		for (int j = 0; j < philosophes.size(); j++) {
			new Thread(ld).start();
		}
	}
}
