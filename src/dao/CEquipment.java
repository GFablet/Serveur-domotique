package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.CMain;

public class CEquipment {
	
			
	
	//Méthode d'insertion d'un équipement
	public static void insertTest(String nom, String ip, String port, int type, int idEmplacement, String etat,
								  String positionXY, String commentaire,Connection con)
	{
		Statement smt;
		try {
			smt = con.createStatement();
			String sql = "insert into equipment(NOM, IP, PORT, TYPE, IDEMPLACEMENT, ETAT, PositionXY, commentaire) values (" 
					+ "'" + nom + "', '" + ip + "', '" + port + "', " + type + ", " + idEmplacement 
					+ ", '" + etat + "', '" + positionXY + "', '" + commentaire + "');";
			int count = smt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//Allumer ou éteindre un équipement
	public static List<String> turnOnOffOneEquipment(int id, String etat) throws Exception
	{
		List<String> equipmentIpPortState = new ArrayList<String>();
		//Envoi d'une requête pour récupérer les infos de l'equipement
		String sqlQuery = "SELECT * FROM equipment WHERE EquID = " + id;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sqlQuery);
		updateEquipment(id, etat, "EquID", CMain.con);
		
			while(rs.next())
			{
				equipmentIpPortState.add(rs.getString("IP") + "+" + rs.getString("port") + etat);
			}
		System.out.println("id : " + id + ", etat : " + etat);
			
		smt = CMain.con.createStatement();	
			
		return equipmentIpPortState;
	}
	
	//Allumer ou éteindre plusieurs équipements
	public static List<String> turnOnOffMultipleEquipments(int equipmentType, String etat, int roomID) throws SQLException
	{
		List<String> equipmentsIpPortState = new ArrayList<String>();
		//Envoi d'une requête
		String sql;
		sql = "select * from equipment where type = " + equipmentType;
		if(roomID != 0)
		{
			sql += " and IDEMPLACEMENT = " + roomID;
		}
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
				
		while(rs.next())
		{
			equipmentsIpPortState.add(rs.getString("IP") + "+" + rs.getString("port") + etat);
			updateEquipment(equipmentType, etat, "type", CMain.con);			
		}
		smt = CMain.con.createStatement();		
		
		return equipmentsIpPortState;
	}
	
	//Modifier l'état d'un équipement
	public static void updateEquipment(int idType, String etat, String where, Connection con) throws SQLException
	{
		String sql;
		PreparedStatement preparedSmt = null;
		try {
			sql = "update equipment set etat = ? where "+ where +" = ?";
			
			preparedSmt = con.prepareStatement(sql);
			preparedSmt.setString(1, etat);
			preparedSmt.setInt(2, idType);
			
			preparedSmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//Récupérer l'état d'un équipement
	public static String getEquipmentState(int id) throws SQLException
	{
		String sqlQuery = "SELECT * FROM equipment WHERE EquID = " + id;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sqlQuery);
		String state = "";
		
		while(rs.next())
		{
			state = rs.getString("etat");
		}
		return state;
	}
	
}