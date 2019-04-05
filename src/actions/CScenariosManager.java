package actions;

import java.util.ArrayList;
import java.util.List;

public class CScenariosManager {
    private static CScenariosManager sInstance = null;
	
	private List<IAction> actionsList;
	
	
	/**
	 * 
	 * @return
	 */
	public static CScenariosManager getInstance() {
		if (sInstance == null) {
			sInstance = new CScenariosManager();
		}
		return sInstance;
	}
	
	/**
	 * 
	 */
	private CScenariosManager()
	{
		actionsList = new ArrayList<IAction>();
	}

	/**
	 * 
	 * @param action
	 */
	public void addAction(IAction action)
	{
		if ((action != null) && (actionsList != null)) {
			actionsList.add(action);
		}
	}

	/**
	 * 
	 */
	public void removeAllActions()
	{
		if (actionsList != null) {
			actionsList.clear();
		}
	}

	/**
	 * 
	 */
	public void executeScenario()
	{
		if (actionsList != null) {
			for(IAction action : actionsList)
			{
				if (action != null) {
					action.execute();
				}
			}
		}
	}
}
