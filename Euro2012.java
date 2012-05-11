package euro2012;

public class Euro2012 {

	/**
	 * @param args
	 */
	public static Tournament euro2012;
	
	private static Euro2012 instance;
	
	public static Euro2012 getInstance() {
		if(instance == null) {
			instance = new Euro2012();
		}
	    return instance;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		resetTournament();
		
	}
	
	public static void resetTournament() {

		euro2012 = new Tournament();
		euro2012.setVisible(true);
		
		
	}

}
