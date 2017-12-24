package exercice1;

public class ClasseTest implements Runnable {
	String nom;
	int debut;
	int fin;

	public ClasseTest(String nom, int debut, int fin) {
		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
	}

	public void run() {
		System.out.println("Ici le  thread "+ nom +", je debute!");
		if(debut <= fin) {
			for (int i = debut; i < fin; i++) {
				System.out.println("["+ nom +"] dit : "+ i); 	
			}
			System.out.println("Ici le  thread "+ nom +", je  termine!");
		} else {
			for (int i = debut; i >= fin; i--) {
				System.out.println("["+ nom +"] dit : "+ i); 	
			}
			System.out.println("Ici le  thread "+ nom +", je  termine!");
		}
	}

	public static void main(String[] args) {
		ClasseTest TA = new ClasseTest("TA", 1, 1000);
		Thread monJob1 = new Thread(TA);
		monJob1.start();
		ClasseTest TB = new ClasseTest("TB", 1000, 1);
		Thread monJob2 = new Thread(TB);
		monJob2.start();
	}
}