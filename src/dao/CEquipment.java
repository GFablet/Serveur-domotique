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

	// Méthode d'insertion d'un équipement
	// TODO mettre à jour
	
	//ID des portes des garages nord et ouest
	private static final int N_GARAGE_DOOR_N = 172;
	private static final int N_GARAGE_DOOR_S = 173;
	private static final int W_GARAGE_DOOR_E = 175;
	private static final int W_GARAGE_DOOR_W = 176;
	//Référence du type des climatiseurs dans la base de donn�e
	private static final int AC_TYPE = 1;
	private static final int N_GARAGE_ID = 19;
	private static final int W_GARAGE_ID = 20;
	
	public static void insertTest(String nom, String ip, String port, int type,
			int idEmplacement, String etat, String positionXY,
			String commentaire, Connection con) {
		Statement smt;
		try {
			smt = con.createStatement();
			String sql = "insert into equipements(NOM, IP, PORT, TYPE, IDEMPLACEMENT, ETAT, PositionXY, commentaire) values ("
					+ "'"
					+ nom
					+ "', '"
					+ ip
					+ "', '"
					+ port
					+ "', "
					+ type
					+ ", "
					+ idEmplacement
					+ ", '"
					+ etat
					+ "', '"
					+ positionXY
					+ "', '" + commentaire + "');";
			int count = smt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Allumer ou éteindre un équipement
	public static List<String> operateOneEquipment(int id, String etat)
			throws Exception {
		List<String> equipmentIpPortState = new ArrayList<String>();
		// Envoi d'une requête pour récupérer les infos de l'equipement
		String sqlQuery = "SELECT * FROM equipements WHERE EquID = " + id;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sqlQuery);
		updateEquipment(id, "etat", etat, "EquID", CMain.con);

		while (rs.next()) {
			equipmentIpPortState.add(rs.getString("IP") + "+"
					+ rs.getString("automate") + etat);
		}
		System.out.println("id : " + id + ", etat : " + etat);

		return equipmentIpPortState;
	}

	// Allumer ou éteindre les équipements d'une pièce
	public static List<String> operateEquipmentsFromRoom(int equipmentType,
			String etat, int roomID) throws SQLException {
		List<String> equipmentsIpPortState = new ArrayList<String>();
		// Envoi d'une requête
		String sql;
		sql = "select * from equipements where type = " + equipmentType
				+ " and idemplacement = " + roomID;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			//On ignore les portes de garage
			if(rs.getInt("EquID") != N_GARAGE_DOOR_N && rs.getInt("EquID") != N_GARAGE_DOOR_S
			   && rs.getInt("EquID") != W_GARAGE_DOOR_E && rs.getInt("EquID") != W_GARAGE_DOOR_W)
			{
				equipmentsIpPortState.add(rs.getString("IP") + "+"
						+ rs.getString("automate") + etat);
				updateEquipment(equipmentType, "etat", etat, "type", CMain.con);
			}
			
		}

		return equipmentsIpPortState;
	}

	// Allumer ou éteindre les équipements d'un étage
	public static List<String> operateEquipmentsFromFloor(int equipmentType,
			String etat, int floorID) throws SQLException {
		List<String> equipmentsIpPortState = new ArrayList<String>();
		// Envoi d'une requête
		String sql;
		sql = "select * from equipements inner join emplacements on IDEMPLACEMENT = EmpID"
				+ " where ZoneID = " + floorID + " and type = " + equipmentType;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			equipmentsIpPortState.add(rs.getString("IP") + "+"
					+ rs.getString("automate") + etat);
			updateEquipment(equipmentType, "etat", etat, "type", CMain.con);
		}

		return equipmentsIpPortState;
	}
	
	public static List<String> operateGarageDoors(String etat, int roomID) throws Exception
	{
		List<String> garageDoorsIpPortState = new ArrayList<String>();
		if(roomID == N_GARAGE_ID)
		{
			garageDoorsIpPortState.addAll(operateOneEquipment(N_GARAGE_DOOR_N, etat));
			garageDoorsIpPortState.addAll(operateOneEquipment(N_GARAGE_DOOR_S, etat));
			return garageDoorsIpPortState;
		}
		else 
		{
			garageDoorsIpPortState.addAll(operateOneEquipment(W_GARAGE_DOOR_W, etat));
			garageDoorsIpPortState.addAll(operateOneEquipment(W_GARAGE_DOOR_E, etat));
			return garageDoorsIpPortState;
		}
	}

	// Allumer ou éteindre un équipement
	public static List<String> operateOneAC(int id, String etat, String mode)
			throws Exception {
		List<String> equipmentIpPortState = new ArrayList<String>();
		// Envoi d'une requête pour récupérer les infos de l'equipement
		String sqlQuery = "SELECT * FROM equipements WHERE EquID = " + id;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sqlQuery);

		while (rs.next()) {
			equipmentIpPortState.add(rs.getString("IP") + "+"
					+ rs.getString("automate") + etat + mode);
			updateEquipment(id, "etat", etat, "EquID", CMain.con);
		}
		System.out.println("id : " + id + ", etat : " + etat);

		return equipmentIpPortState;
	}

	// Allumer ou éteindre la clim d'une pièce
	public static List<String> operateACFromRoom(String etat, String mode,
			int roomID) throws SQLException {
		List<String> equipmentsIpPortState = new ArrayList<String>();
		// Envoi d'une requête
		String sql;
		sql = "select * from equipements where type = "+ AC_TYPE +" and IDEMPLACEMENT = "
				+ roomID;

		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			equipmentsIpPortState.add(rs.getString("IP") + "+"
					+ rs.getString("automate") + etat + mode);
			updateEquipment(AC_TYPE, "etat", etat, "type", CMain.con);
			updateEquipment(AC_TYPE, "clim", mode, "type", CMain.con);
		}

		return equipmentsIpPortState;
	}

	// Allumer ou éteindre les équipements d'un étage
	public static List<String> operateACFromFloor(String etat, 
			String mode, int floorID) throws SQLException {
		List<String> equipmentsIpPortState = new ArrayList<String>();
		// Envoi d'une requête
		String sql;
		sql = "select * from equipements inner join emplacements on IDEMPLACEMENT = EmpID"
				+ " where ZoneID = " + floorID + " and type = " + AC_TYPE;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			equipmentsIpPortState.add(rs.getString("IP") + "+"
					+ rs.getString("automate") + etat + mode);
			updateEquipment(AC_TYPE, "etat", etat, "type", CMain.con);
			updateEquipment(AC_TYPE, "clim", mode, "type", CMain.con);
		}

		return equipmentsIpPortState;
	}

	// Modifier une colomne d'un équipement
	public static void updateEquipment(int idType, String column, String etat,
			String where, Connection con) throws SQLException {
		String sql;
		PreparedStatement preparedSmt = null;
		try {
			sql = "update equipements set " + column + " = ? where " + where
					+ " = ?";

			preparedSmt = con.prepareStatement(sql);
			preparedSmt.setString(1, etat);
			preparedSmt.setInt(2, idType);

			preparedSmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Récupérer l'état d'un équipement
	public static String getEquipmentState(int id) throws SQLException {
		String sqlQuery = "SELECT * FROM equipements WHERE EquID = " + id;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sqlQuery);
		String state = "";

		while (rs.next()) {
			state = rs.getString("etat");
		}
		return state;
	}

}