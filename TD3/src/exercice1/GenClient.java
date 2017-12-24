package exercice1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class GenClient {
	private final String hote;
	private final int port;
	private final String id;
	private Scanner console_input;
	final String Finish = "" + (char) 4;  //Signal de fin de connection aussi nommé EOT  ctrl-d
	byte[] datas=null;
	private String dataClient = null; 

	public GenClient(String hote, int port, String mon_id) {
		this.hote = hote;
		this.port = port;
		this.id = mon_id;
	}

	private void getdata(PrintWriter ma_sortie) {
		Random rand = new Random();
		int randomNum = rand.nextInt((10 - 1) + 1) + 1;
		boolean wait_data = true;
		while (wait_data) {
			dataClient = this.id + " toque " + randomNum + " fois";
			datas = dataClient.getBytes();
			return;
		}
	}

	public void execute() {
		Socket la_connection = null;
		OutputStream os;
		PrintWriter  ma_sortie; 
		try {
			la_connection = new Socket(this.hote, this.port);
		} catch (IOException e) {
			System.out.println("Probleme de connection avec le serveur " + e);
			System.exit(-1);}
		try {
			os= la_connection.getOutputStream();
			ma_sortie = new PrintWriter(os, true);
			System.out.println(" Contacting " + this.hote + " on " + this.port);
			ma_sortie.println("Hello je suis :" + this.id);

			getdata(ma_sortie);
			os.write(datas, 0, datas.length);
			System.out.println("Données envoyées, envoi de la terminaison");
			ma_sortie.println(Finish);
		} 
		catch (IOException e) {
			System.out.println("data not fully transmited : " + e);}
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Il me faut 3 arguments:  hote  port identifiant");
			System.exit(1);}

		try {
			new Client(args[0], Integer.parseInt(args[1]), args[2]).execute();} 
		catch (NumberFormatException e) {
			System.out.println("Mauvais format du port\n "+ e.getMessage());
			System.exit(-1);
		}
	}

}
