package actions;

<<<<<<< HEAD
import main.CMain;
import net.CClient;
import dao.CEquipment;

public class CSwitchOnAction implements IAction{

	private String equipmentName;
	private String roomID;
	private String floorID;
	private int time = 0;
	private long unit = 0;
	
	
	
	public CSwitchOnAction(String equipmentName, String roomID, String floorID)
	{
		this.equipmentName = equipmentName;
		this.roomID = roomID;
		this.floorID = floorID;
	}	
	public CSwitchOnAction(String equipmentName, String roomID, String floorID, int time, long unit)
	{
		this.equipmentName = equipmentName;
		this.roomID = roomID;
		this.floorID = floorID;
		this.time = time;
		this.unit = unit;
	}
	
	
	@Override
	public void execute() {
		try {
			CClient.getInstance().sendCommand(CEquipment.operateOneEquipment(CMain.lights.get(equipmentName), "1"));
			Thread.sleep(time*unit);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
=======
public class CSwitchOnAction implements IActions{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
>>>>>>> parent of b488e37... Ajout scénarios
	}

	@Override
	public void stopExecutions() {
		// TODO Auto-generated method stub
		
	}

}
