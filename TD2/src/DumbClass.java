public class DumbClass  implements Runnable{
	String nom="Toto";
	int maxIter= 1000 ;
	
	public DumbClass(String  nom)    {
		this.nom = nom;
	}

	public void run()    {
		System.out.println("Ici le  thread "+ nom +", je debute!");
		for (int i = 0; i < maxIter; i++) {
			System.out.println("["+ nom +"] dit  je suis Ici "+ i); 	}
		System.out.println("Ici le  thread "+ nom +", je  Termine!");
	}

	public static void main(String[] args)  throws  Exception {
		String jobName= "Job_0";
		DumbClass   objetExec  = new DumbClass( jobName);
		Thread job = new Thread(objetExec);
		System.out.println("Creating thread "+ jobName);
		job.start();
		System.out.println("Thread principal terminé  !\n");
	}

}