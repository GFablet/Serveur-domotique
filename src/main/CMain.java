package main;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import actions.CScenariosManager;
import actions.CSwitchOffAction;
import actions.CSwitchOnAction;
import actions.CWaitAction;
import net.CClient;
import net.CServer;
import dao.CDatabase;

public class CMain {


	public static final int HOUSE_PORT = 3379;
	public static final String HOUSE_IP = "192.168.56.1";
	public static Connection con = null;

	public static void main(String[] args) {
		
		try {
			//Chargement de la classe
			Class c = Class.forName("com.mysql.cj.jdbc.Driver");
			Driver pilote = (Driver)c.newInstance();
			//Enregistrement du pilote auprès du DriverManager
			DriverManager.registerDriver(pilote);
			//Connexion
			con = DriverManager.getConnection(CDatabase.CONNECTION_STRING, CDatabase.LOGIN, CDatabase.PASSWORD);
			
			
			CScenariosManager.getInstance().addAction(new CSwitchOnAction(22));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(200));
	    	CScenariosManager.getInstance().addAction(new CSwitchOffAction(16));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(1000));
	    	CScenariosManager.getInstance().addAction(new CSwitchOnAction(8));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(200));
	    	CScenariosManager.getInstance().addAction(new CSwitchOffAction(22));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(1000));
	    	CScenariosManager.getInstance().addAction(new CSwitchOnAction(17));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(200));
	    	CScenariosManager.getInstance().addAction(new CSwitchOffAction(8));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(1000));
	    	CScenariosManager.getInstance().addAction(new CSwitchOnAction(23));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(200));
	    	CScenariosManager.getInstance().addAction(new CSwitchOffAction(17));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(1000));
	    	CScenariosManager.getInstance().addAction(new CSwitchOnAction(12));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(200));
	    	CScenariosManager.getInstance().addAction(new CSwitchOffAction(23));
	    	CScenariosManager.getInstance().addAction(new CWaitAction(5000));
	    	CScenariosManager.getInstance().addAction(new CSwitchOffAction(12));
			
			
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
