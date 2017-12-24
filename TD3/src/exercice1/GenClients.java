package exercice1;

import java.util.ArrayList;
import java.util.Scanner;

public class GenClients {

	private ArrayList<GenClient> listeClients = new ArrayList<GenClient>();

	public ArrayList<GenClient> getListeClients() {
		return listeClients;
	}


	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Il me faut 1 argument:  nb_clients nb_fois");
			System.exit(1);
		} else {
			int nb_clients = Integer.parseInt(args[0]);
			int nb_fois = Integer.parseInt(args[1]);
			GenClients gc = new GenClients();

			for (int i = 1; i < nb_clients+1; i++) {
				System.out.println("Indiquez les éléments suivants pour le " + i + "ième client: hote port identifiant");
				Scanner scanner = new Scanner(System.in);
				String clientElements = scanner.nextLine();
				String[] partsClient = clientElements.split(" ");
				String hote = partsClient[0];
				String port = partsClient[1];
				String id = partsClient[2];
				try {
					GenClient c = new GenClient(hote, Integer.parseInt(port), id);
					gc.getListeClients().add(c);
				} 
				catch (NumberFormatException e) {
					System.out.println("Mauvais format du port\n "+ e.getMessage());
					System.exit(-1);
				}
			}
			while(nb_fois > 0) {
				for (GenClient client : gc.getListeClients()) {
					//try {
					client.execute();
					/*	Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/
				}

				nb_fois--;
			}
		}
	}
}
