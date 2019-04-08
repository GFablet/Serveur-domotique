package actions;

import java.util.ArrayList;
import java.util.List;

public class CScenariosManager {
    private static CScenariosManager sInstance = null;
	
	private List<IActions> actionsList;
	
	
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
		actionsList = new ArrayList<IActions>();
	}

	/**
	 * 
	 * @param action
	 */
	public void addAction(IActions action)
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
			for(IActions action : actionsList)
			{
				if (action != null) {
					action.execute();
				}
			}
		}
	}
}