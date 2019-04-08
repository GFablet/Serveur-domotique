package scenarios;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import scenarios.parser.CParserDSDL;
import actions.IAction;

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
	
	public void createScenario(String scenario) throws scenarios.parser.ParseException, ParseException
	{
		InputStream stream = new ByteArrayInputStream(scenario.getBytes());
		CParserDSDL lParser = new CParserDSDL(stream);

        lParser.Start();
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