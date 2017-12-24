package exercice1;

public class Baguette {

	private Philosophe proprietaire;

	public Philosophe getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Philosophe proprietaire) {
		this.proprietaire = proprietaire;
	}

	public synchronized void prendre(Philosophe proprietaire) {
		if(proprietaire == null) {
			setProprietaire(proprietaire);
			System.out.println("Le philosophe " + proprietaire.getNumPhilo() + " a maintenant " 
					+ proprietaire.getNbBaguettesDetenues() + " baguettes");
		}
	}

	public synchronized void poser(Philosophe proprietaire) {
		if(proprietaire != null) {
			setProprietaire(null);
		}
	}
}

