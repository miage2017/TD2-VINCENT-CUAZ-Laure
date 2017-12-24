package exercice1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Repartiteur {

	public Repartiteur(int numPort) {
		ServerSocket sSocket;
		try {
			ServeurClient sClient = new ServeurClient(numPort);
			sSocket = sClient.getMon_connecteur();
			Socket client = sSocket.accept();
			boolean serviceClient = sClient.Service_Client(client);
			while(serviceClient) {
				sClient.run();
				client.close();
				sSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if(args[0] != null) {
			String s = args[0];
			try { 
				int port = Integer.parseInt(s); 
				if(port < 0 && port > 65535) {
					System.out.println("Mauvais format du numero de port");
					System.exit(0);
				} else {
					new Repartiteur(port);
				}
			} catch(NumberFormatException e) { 
				System.out.println("Mauvais format du numero de port");
				System.exit(0);
			} catch(NullPointerException e) {
				System.out.println("Mauvais format du numero de port");
				System.exit(0);
			}
		} else {
			System.out.println("Utiliser : java Repartiteur num_port");
			System.exit(0);
		}
	}
}
