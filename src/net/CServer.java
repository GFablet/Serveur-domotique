package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import main.CMain;
import dao.CEquipment;

public class CServer extends ServerSocket implements Runnable{
    private static final int PORT = 45000; //Valeur arbitraire
    //public static boolean SERVER_WAITS = true;

	
    public CServer() throws Exception{
    	super(PORT);
    	new Thread(this).start();
    }


	@Override
	public void run() {
    	while(true)
    	{
			try {
				System.out.println("Attente d'un client...");
		    	Socket s = accept();
				//Permet d'attendre qu'un client se connecte
		          //Attention cette instruction est bloquante
				System.out.println("Un client vient de se connecter");
				
				//A ce stade, un client est connecté. On peut communiqué avec lui grâce à un Socket
				//Création d'un flux d'entrée(client vers serveur)
				System.out.println("Création des flux d'entrée");
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								s.getInputStream()));
				
				//Création du flux de sortie(serveur vers client)
				System.out.println("Création des flux de sortie");
				PrintWriter pw = new PrintWriter(
								s.getOutputStream(), true);
			          
			    pw.println(CCommandManager.sendCommand(br.readLine()));
			    
			    pw.close(); br.close();
			}
			    
			catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
