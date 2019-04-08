package actions;

import main.CMain;
import net.CClient;
import dao.CEquipment;

public class CSwitchOffAction implements IAction{

	private String equipmentName;
	private String roomID;
	private String floorID;
	private int time = 0;
	private long unit = 0;
	
	public CSwitchOffAction(String equipmentName, String roomID, String floorID)
	{
		this.equipmentName = equipmentName;
		this.roomID = roomID;
		this.floorID = floorID;
	}	
	public CSwitchOffAction(String equipmentName, String roomID, String floorID, int time, long unit)
	{
		this.equipmentName = equipmentName;
		this.roomID = roomID;
		this.floorID = floorID;
		this.time = time;
		this.unit = unit;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			CClient.getInstance().sendCommand(CEquipment.operateOneEquipment(CMain.lights.get(equipmentName), "0"));
			Thread.sleep(time*unit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stopExecutions() {
		// TODO Auto-generated method stub
		
	}
	
}
