package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

import main.CMain;

public class CClient extends Socket implements Runnable {
	
	
	// Singleton de la classe CClient (l'unique instance qui peut être créée).
	private static CClient sInstance = null;

	private BufferedReader mBr;
	private PrintWriter mPw;

	
	public BufferedReader getmBr() {
		return mBr;
	}

	public PrintWriter getmPw() {
		return mPw;
	}

	public static CClient getInstance(int pPort, String pIPAddr) {
		if (sInstance == null) {
			try {
				sInstance = new CClient(pPort, pIPAddr);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return sInstance;
	}

	// Attention : cette méthode ne doit pas être appelée en premier.
	public static CClient getInstance() {
		if (sInstance == null) {
			System.out.println("Appel interdit.");
		}
		return sInstance;
	}

    private CClient(int pPort, String pIPAddr) throws IOException {
    	super(pIPAddr, pPort);
    	
    	// Création des flux d'E/S.
    	// Flux d'entrée.
    	System.out.println("Client : création des flux.");
    	mBr = new BufferedReader(
    			new InputStreamReader(
    					getInputStream()));
    	// Flux de sortie.
    	mPw = new PrintWriter(
    			getOutputStream(), true);
    	
    	new Thread(this).start();
    }


	@Override
	public void run() {
	}
	
	//Méthode pour envoyer les commandes au simulateur domotique
	public void sendCommand(List<String> equipments) throws IOException {
		//On parcourt la liste d'équipements retournée par les requêtes SQL
		System.out.println("Envoie de la commande.");
		for(String equipment : equipments)
		{
			//On sépare la chaîne concaténée de la requête
			//Pour récupérer l'IP de l'équipement, 
			//puis son entrée et l'état voulu
			String[] equipmentSplit = equipment.split("\\+");
			//On sépare chaque valeur qu'on enverra en octet
			String[] ip = equipmentSplit[0].split("\\.");
			String[] equipmentOut = equipmentSplit[1].split("");
			//On transforme les valeurs en octets
			for(String bytes : ip)
			{
				//System.out.println(bytes);
				mPw.print((char)Integer.parseInt(bytes));
			}
			for(String bytes : equipmentOut)
			{
				//System.out.println(bits);
				mPw.print((char)Integer.parseInt(bytes));
			}
			//mPw.print((char)0);
		}
		mPw.flush();
	}
	
	public void closeClient() throws IOException
	{
		mPw.close();
		mBr.close();
		close();
	}
	
}
