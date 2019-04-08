package actions;

public class CWaitAction implements IActions{
	
	private long milliseconds = 0;

	public CWaitAction(long milliseconds)
	{
		this.milliseconds = milliseconds;
	}
	
	@Override
	public void execute() {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void stopExecutions() {
		// TODO Auto-generated method stub
		
	}
	
	
}
