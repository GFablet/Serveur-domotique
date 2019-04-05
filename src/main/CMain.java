package main;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.CClient;
import net.CServer;
import dao.CDatabase;
import scenarios.parser.CParserDSDL;

public class CMain {


	public static final int HOUSE_PORT = 39098;
	public static final String HOUSE_IP = "192.168.56.1";
	public static Map<String, Integer> lights = null;
	public static Connection con = null;

	public static void main(String[] args) {
		
		try {
			//Chargement de la classe
			Class c = Class.forName("com.mysql.cj.jdbc.Driver");
			Driver pilote = (Driver)c.newInstance();
			//Enregistrement du pilote aupr�s du DriverManager
			DriverManager.registerDriver(pilote);
			//Connexion
			con = DriverManager.getConnection(CDatabase.CONNECTION_STRING, CDatabase.LOGIN, CDatabase.PASSWORD);
			
<<<<<<< HEAD

			lights = new HashMap<>();
			
			lights.put("lumiere1", 57);
			lights.put("lumiere2", 59);
			lights.put("lumiere3", 16);
			lights.put("lumiere4", 65);
			lights.put("lumiere5", 39);
			
			
			
			
=======
>>>>>>> parent of b488e37... Ajout scénarios
			CClient.getInstance(CMain.HOUSE_PORT, CMain.HOUSE_IP);
			CServer server = new CServer();
			
			/*String etat = "1";
			int id = 1;

			//CEquipment.insertTest("Luminaire nord", "192.168.0.100", "00010111", 1, 1, "0", "Centre", "", con);
			CClient.getInstance(HOUSE_PORT, HOUSE_IP);
			//CEquipment.updateEquipment(1, "0", con);
			CClient.getInstance().sendCommand(CEquipment.turnOnOffOneEquipment(1, "1"));
			//CClient.getInstance().sendCommand(CEquipment.turnOnOffMultipleEquipments(id, 1, etat));
			
			System.out.println(CEquipment.getEquipmentState(id));
			
			CClient.getInstance().closeClient();*/

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
