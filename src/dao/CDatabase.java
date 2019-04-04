package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;

import com.mysql.cj.jdbc.Driver;

public class CDatabase {
	//Adresse IP et port de la base de donnée
	public static final String DB_IP = "localhost";
	public static final String DB_PORT = "3306"; //port MySQL par défaut
	//Protocole de connexion
	public static final String PROTOCOLE = "jdbc:mysql:";
	//Nom de la BDD
	public static final String DB_NAME = "clicom";
	public static final String CONNECTION_STRING = PROTOCOLE + "//" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
	//Identifiants
	public static final String LOGIN = "root";
	public static final String PASSWORD = "";

//	    //Chargement de la classe
//		Class c = Class.forName("com.mysql.cj.jdbc.Driver");
//		Driver pilote = (Driver)c.newInstance();
//		//Enregistrement du pilote auprès du DriverManager
//		
//		//Protocole de connexion
//		String protocole = "jdbc:mysql:";
//		//Adresse IP de l'hôte de la base et port
//		String ip = "localhost";
//		String port = "3306"; //port MySQL par défaut
//		//Nom de la BDD
//		String nomBase = "clicom";
//		String conString = protocole + "//" + ip + ":" + port + "/" + nomBase;
//		//Identifiants
//		String nomConnexion = "root";
//		String motDePasse = "";


//	public Connection getConnection()
//	{
//		//Connexion
//		Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse);
//		return con;
//	}
	
}
