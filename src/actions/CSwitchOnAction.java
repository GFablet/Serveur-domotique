package actions;

import main.CMain;
import net.CClient;
import dao.CEquipment;

public class CSwitchOnAction implements IAction{

	private String equipmentName;
	private String roomName;
	private String floorName;
	private int time = 0;
	private long unit = 0;
	
	public CSwitchOnAction(String equipmentName, String roomName, String floorName)
	{
		this.equipmentName = equipmentName;
		this.roomName = roomName;
		this.floorName = floorName;
	}	
	public CSwitchOnAction(String equipmentName, String roomID, String floorID, int time, long unit)
	{
		this.equipmentName = equipmentName;
		this.roomName = roomID;
		this.floorName = floorID;
		this.time = time;
		this.unit = unit;
	}
	
	
	@Override
	public void execute() {
		try {
			CClient.getInstance().sendCommand(CEquipment.operateOneEquipment(CEquipment.returnEquipmentID(equipmentName, roomName, floorName), "1"));
			Thread.sleep(time*unit);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
		@Override
		public void stopExecutions() {
			// TODO Auto-generated method stub
			
		}

}

	
