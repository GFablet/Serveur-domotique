package actions;

import net.CClient;
import dao.CEquipment;

public class CSwitchOnAction implements IAction{

	private int roomID;
	
	public CSwitchOnAction(int roomID)
	{
		this.roomID = roomID;
	}	
	
	@Override
	public void execute() {
		try {
			CClient.getInstance().sendCommand(CEquipment.operateEquipmentsFromRoom(0, "1", roomID));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void stopExecutions() {
		// TODO Auto-generated method stub
	}
}
