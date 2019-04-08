package net;

import java.io.IOException;
import java.sql.SQLException;

import actions.CScenariosManager;
import dao.CEquipment;

public class CCommandManager {

	public static String sendCommand(String message) throws IOException, Exception
	{
		//R�cup�ration de la requ�te du client
	  	System.out.println("Récupération de la requête du client");
	  	String[] command = message.split("\\/");
	  	String result = "Requête envoyée";
	  	
	  	//Traitement de la requ�te du client
	      System.out.println("Traitement de la requête client");
	          
	    if(command[0].equals("getState"))
	    {
	          	result = CEquipment.getEquipmentState(Integer.parseInt(command[1]));
	          	//System.out.println(result);
	    }else if(command[0].equals("operateOneEquipment"))
	    {
	    	//CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
	        CClient.getInstance().sendCommand(CEquipment.operateOneEquipment(Integer.parseInt(command[1]), command[2]));
	    } else if(command[0].equals("operateEquipmentsFromRoom"))
	    {
	        //CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
	        CClient.getInstance().sendCommand(CEquipment.operateEquipmentsFromRoom(Integer.parseInt(command[1]), command[2], Integer.parseInt(command[3])));
	    } else if(command[0].equals("operateEquipmentsFromFloor"))
	    {
	        //CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
	        CClient.getInstance().sendCommand(CEquipment.operateEquipmentsFromFloor(Integer.parseInt(command[1]), command[2], Integer.parseInt(command[3])));
	    } else if(command[0].equals("operateOneAC"))
	    {
	       // CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
	        CClient.getInstance().sendCommand(CEquipment.operateOneAC(Integer.parseInt(command[1]), command[2], command[3]));
	    } else if(command[0].equals("operateACFromFloor"))
	    {
	        //CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
	        CClient.getInstance().sendCommand(CEquipment.operateACFromFloor(command[1], command[2], Integer.parseInt(command[3])));
	    } else if(command[0].equals("operateGarageDoors"))
	    {
	        //CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
	        CClient.getInstance().sendCommand(CEquipment.operateGarageDoors(command[1], Integer.parseInt(command[2])));
	    } else if(command[0].equals("scenario"))
	    {
	    	CScenariosManager.getInstance().executeScenario();
	    } 
	    
	    return result;
	}
}