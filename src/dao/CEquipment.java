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
	public static List<String> turnOnOffOneEquipment(int id, String etat)
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
	public static List<String> turnOnOffEquipmentsFromRoom(int equipmentType,
			String etat, int roomID) throws SQLException {
		List<String> equipmentsIpPortState = new ArrayList<String>();
		// Envoi d'une requête
		String sql;
		sql = "select * from equipements where type = " + equipmentType
				+ " and idemplacement = " + roomID;
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			equipmentsIpPortState.add(rs.getString("IP") + "+"
					+ rs.getString("automate") + etat);
			updateEquipment(equipmentType, "etat", etat, "type", CMain.con);
		}

		return equipmentsIpPortState;
	}

	// Allumer ou éteindre les équipements d'un étage
	public static List<String> turnOnOffEquipmentsFromFloor(int equipmentType,
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

	// Allumer ou éteindre un équipement
	public static List<String> turnOnOffOneAC(int id, String etat, String mode)
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
	public static List<String> turnOnOffACFromRoom(String etat, String mode,
			int roomID) throws SQLException {
		List<String> equipmentsIpPortState = new ArrayList<String>();
		// Envoi d'une requête
		String sql;
		sql = "select * from equipements where type = 3 and IDEMPLACEMENT = "
				+ roomID;

		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			equipmentsIpPortState.add(rs.getString("IP") + "+"
					+ rs.getString("automate") + etat + mode);
			updateEquipment(3, "etat", etat, "type", CMain.con);
			updateEquipment(3, "clim", mode, "type", CMain.con);
		}

		return equipmentsIpPortState;
	}

	// Allumer ou éteindre les équipements d'un étage
	public static List<String> turnOnOffACFromFloor(String etat, 
			String mode, int floorID) throws SQLException {
		List<String> equipmentsIpPortState = new ArrayList<String>();
		// Envoi d'une requête
		String sql;
		sql = "select * from equipements inner join emplacements on IDEMPLACEMENT = EmpID"
				+ " where ZoneID = " + floorID + " and type = 3";
		Statement smt = CMain.con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			equipmentsIpPortState.add(rs.getString("IP") + "+"
					+ rs.getString("automate") + etat);
			updateEquipment(3, "etat", etat, "type", CMain.con);
			updateEquipment(3, "clim", mode, "type", CMain.con);
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