public class DummyClassHeritage  extends Thread { 
	String nom = "Toto";
	int maxv = 10 ;
	
	public DummyClassHeritage(String  nom){
		this.nom=nom;
	}

	public void run() {
		System.out.println("Ici le  thread "+ nom +", je debute!");
		for (int i = 0; i < maxv; i++) {
			System.out.println("["+ nom +"] dit  je suis la " +i); 	
		}
	}
	
	public static void main(String[] args)  throws  Exception {
		String jobname= "Job_0";
		DummyClassHeritage objet_executable   = new DummyClassHeritage(jobname);
		System.out.println("Creating thread " + jobname);
		objet_executable.start();
		System.out.println("Main :Fini ici  !");
	}
}