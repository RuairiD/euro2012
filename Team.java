package euro2012;

public class Team {

	private String name;
	private long rating;
	private int points;
	private int atkRating;
	private int defRating;
	private int goals;
	private int goalsFor;
	private int goalsAgainst;
	
	public Team(String pName, int atk, int def) {
		
		name = pName;
		atkRating = atk;
		defRating = def;
		points = 0;
		goalsFor = 0;
		goalsAgainst = 0;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public long getRating() {
		
		return rating;
		
	}
	
	public int getAtk() {
		
		return atkRating;
		
	}
	
	public int getDef() {
		
		return defRating;
		
	}
	
	public int getPoints() {
		
		return points;
		
	}
	
	public void addGoal() {
		
		goals++;
		goalsFor++;
		
	}
	
	public void setGoals(int g) {
		
		goals = g;
		
	}
	
	public int getGoals() {
		
		return goals;
		
	}
	
	public void addGoalAgainst() {
		
		goalsAgainst++;
		
	}
	
	public int getGoalsFor() {
		
		return goalsFor;
		
	}
	
	public int getGoalsAgainst() {
		
		return goalsAgainst;
		
	}
	
	public void win() {
		
		points += 3;
		
	}
	
	public void lose() {
		
		points += 0;
		
	}
	
	public void draw() {
		
		points += 1;
		
	}

}
