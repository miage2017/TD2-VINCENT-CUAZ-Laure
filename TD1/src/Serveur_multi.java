import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur_multi {

	private ServerSocket sSocket ;
	private boolean isRunning = true;

	public Serveur_multi() {
		try {
			this.sSocket = new ServerSocket(12000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Thread t = new Thread(new Runnable(){
			public void run(){
				while(isRunning == true){
					try {
						//On attend une connexion d'un client
						final Socket client = sSocket.accept();
						//Une fois recue, on la traite dans un thread separe
						Thread t1 = new Thread(new Runnable(){
							public void run() {
								Service_Client(client);
							}
						});
						t1.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public void Service_Client(Socket client) {
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(client.getInputStream(), "UTF-8");
			BufferedReader flux_entrant = new BufferedReader(isr) ;
			PrintStream out = new PrintStream(client.getOutputStream());
			String read = flux_entrant.readLine();
			String nameClient = read.split("bonjour c'est le client ")[1];
			out.println("Bonjour "+nameClient+"!");
			while(read!=null) {
				System.out.println("le client numero "+nameClient+" ecrit : "+read);
				flux_entrant = new BufferedReader(isr) ;
				if(read.equals("FINISH")) {
					break;
				}
				read = flux_entrant.readLine();
			}
			client.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if(args[2] != null) {
			String s = args[2];
			try { 
				int port = Integer.parseInt(s); 
				if(port < 0 && port > 65535) {
					System.out.println("Mauvais format du numero de port");
					System.exit(0);
				}
			} catch(NumberFormatException e) { 
				System.out.println("Mauvais format du numero de port");
				System.exit(0);
			} catch(NullPointerException e) {
				System.out.println("Mauvais format du numero de port");
				System.exit(0);
			}
		} else {
			System.out.println("Utiliser : java Server_multi num_port");
			System.exit(0);
		}
		Serveur_multi serv = new Serveur_multi();
		serv.open();
	}
}
