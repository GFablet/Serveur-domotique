package actions;

import net.CClient;
import dao.CEquipment;

public class CSwitchOffAction implements IAction{

	private int roomID;
	
	public CSwitchOffAction(int roomID)
	{
		this.roomID = roomID;
	}	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			CClient.getInstance().sendCommand(CEquipment.operateEquipmentsFromRoom(0, "0", roomID));
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
