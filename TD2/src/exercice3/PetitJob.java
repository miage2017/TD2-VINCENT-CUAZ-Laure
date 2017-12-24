package exercice3;
public class PetitJob implements Runnable {
	
	private ObjetEntier notre_entier ;
	private int increment;
	
	PetitJob(ObjetEntier notre_entier , int increment){
		this.notre_entier = notre_entier;
		this.increment=increment; 
	}
	
	public void run() {
		for (int i = 0; i < 1e8; i++) {
			notre_entier.add(increment);
		}  
		System.out.println("Thread faisant "+increment+" - termine, compteur= "+notre_entier);

	}
	
	public static void main(String[] args) {
		ObjetEntier Compteur = new ObjetEntier(); 
		PetitJob objex1= new PetitJob(Compteur, 1);
		PetitJob objex2= new PetitJob(Compteur, -1);
		Thread tache1 = new Thread(objex1 ,"incrementeur");
		Thread tache2 = new Thread(objex2, "decrementeur");

		tache1.start();
		tache2.start();

		System.out.println("Le compteur vaut "+ Compteur);
		System.out.println("Le compteur vaut "+ Compteur);
	}
}