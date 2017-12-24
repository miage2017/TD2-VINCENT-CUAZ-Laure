import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur_Jouet {

	public Serveur_Jouet() {
		ServerSocket sSocket;
		try {
			sSocket = new ServerSocket(12000);
			Socket client = sSocket.accept();
			InputStreamReader isr = new InputStreamReader(client.getInputStream(), "UTF-8");
			BufferedReader flux_entrant = new BufferedReader(isr) ;
			int i=1;
			String read = flux_entrant.readLine();
			System.out.println("Le client a ecrit :");
			while(read != null) {
				System.out.println("Ligne "+i+" : "+read);
				flux_entrant = new BufferedReader(isr) ;
				if(read.equals("FINISH")) {
					break;
				}
				read = flux_entrant.readLine();
				i++;
			}
			client.close();
			sSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Serveur_Jouet sj = new Serveur_Jouet();
		Serveur_Jouet sj2 = new Serveur_Jouet();
	}
}