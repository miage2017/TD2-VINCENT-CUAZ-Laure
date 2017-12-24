package exercice2;
public class TestOrdre implements Runnable {
	int pas_de_comptage = 0;
	String nom = null;
	int max_value = 0;

	public TestOrdre(String nom, int increment, int max) {
		this.pas_de_comptage = increment;
		this.nom = nom;
		this.max_value = max;
	}
	/* TestOrdre compte de 0 a maxv moins 1 et affiche son comptage a l'ecran */
	public void run() {
		System.out.println("Ici le  thread "+nom+", je debute");
		int myValue=0;
		for (int i = 0; i < max_value; i++) {
			int randomValue = 200 + (int)(Math.random() * ((2000 - 200) + 2000));
			waitHere(randomValue);
			System.out.println("["+nom+"] dit " + myValue);
			myValue += pas_de_comptage;
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("utilisation java "+ TestOrdre.class.getCanonicalName()+" [nbthreads] [maxvalue]\nIl me faut deux parametres ");
			System.exit(-1);
		}

		int nb_threads = Integer.parseInt(args[0]);
		int max_value = Integer.parseInt(args[1]);

		Thread Mes_Jobs[] = new Thread[nb_threads];
		for (int currentThread = 0; currentThread < nb_threads; currentThread++) {
			String jobname = "Job_"+ currentThread;
			TestOrdre ti_Job = new TestOrdre(jobname, currentThread+1, max_value);
			System.out.println("Creating thread "+jobname);
			Mes_Jobs[currentThread] = new Thread(ti_Job);
			Mes_Jobs[currentThread].start();
		}
		System.out.println("Main :Fini ici !");
	}

	public static void waitHere(int num) {
		try {
			int delay = (int) (Math.random() * num);
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}