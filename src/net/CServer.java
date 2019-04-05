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
    public static boolean SERVER_WAITS = true;

	
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
				
				//A ce stade, un client est connect�. On peut communiqu� avec lui gr�ce � un Socket
				//Cr�ation d'un flux d'entr�e(client vers serveur)
				System.out.println("Cr�ation des flux d'entr�e");
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								s.getInputStream()));
				
				//Cr�ation du flux de sortie(serveur vers client)
				System.out.println("Cr�ation des flux de sortie");
				PrintWriter pw = new PrintWriter(
								s.getOutputStream(), true);
				String message; String[] command; String result; 
			
					//R�cup�ration de la requ�te du client
			  	System.out.println("R�cup�ration de la requ�te du client");
			  	message = br.readLine();
			  	command = message.split("\\/");
			  	result = "";
			  	//Traitement de la requ�te du client(par exemple il s'agit de mettre en majuscules le message du client)
			      System.out.println("Traitement de la requ�te client");
			          
			    if(command[0].equals("getState"))
			    {
			          	result = CEquipment.getEquipmentState(Integer.parseInt(command[1]));
			          	pw.println(result);
			          	//System.out.println(result);
			    }else if(command[0].equals("turnOnOffOneEquipment"))
			    {
			    	//CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
			        CClient.getInstance().sendCommand(CEquipment.turnOnOffOneEquipment(Integer.parseInt(command[1]), command[2]));
			    } else if(command[0].equals("turnOnOffEquipmentsFromRoom"))
			    {
			        //CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
			        CClient.getInstance().sendCommand(CEquipment.turnOnOffEquipmentsFromRoom(Integer.parseInt(command[1]), command[2], Integer.parseInt(command[3])));
			    } else if(command[0].equals("turnOnOffEquipmentsFromFloor"))
			    {
			        //CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
			        CClient.getInstance().sendCommand(CEquipment.turnOnOffEquipmentsFromFloor(Integer.parseInt(command[1]), command[2], Integer.parseInt(command[3])));
			    } else if(command[0].equals("turnOnOffOneAC"))
			    {
			       // CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
			        CClient.getInstance().sendCommand(CEquipment.turnOnOffOneAC(Integer.parseInt(command[1]), command[2], command[3]));
			    } else if(command[0].equals("turnOnOffACFromRoom"))
			    {
			        //CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
			        CClient.getInstance().sendCommand(CEquipment.turnOnOffACFromRoom(command[1], command[2], Integer.parseInt(command[3])));
			    } 
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
