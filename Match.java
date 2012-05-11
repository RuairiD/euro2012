package euro2012;

public class Match {

	public static Team playMatch(Team team1, Team team2, boolean knockout) {
		
		double goalProb;
		team1.setGoals(0);
		team2.setGoals(0);
		
		int i = 0;
		int time = 90;
		for (i = 0; i < time; i++) {
			
			//Decide if a goal is scored in this time period
			goalProb = Math.random();
			
			//Decide which team has the attacking opportunity
			Team attackingTeam;
			Team defendingTeam;
			double team1Rating = team1.getAtk() + team1.getDef();
			double team2Rating = team2.getAtk() + team2.getDef();
			double opportunity  = Math.random() * (team1Rating + team2Rating); 
			
			if (opportunity < team1Rating) {
				attackingTeam = team1;
				defendingTeam = team2;
			} else {
				attackingTeam = team2;
				defendingTeam = team1;
			}
			
			double atkScore = Math.random() * attackingTeam.getAtk();
			double defScore = Math.random() * defendingTeam.getDef();

			/*
			 * Prob of a goal each minute at Euro 2008 was  0.02759856631,
			 * but this doesn't produce great results in the algorithm.
			 * A tested probability of 0.05 was used instead */
			if (atkScore > defScore && goalProb < 0.04) {
				attackingTeam.addGoal();
				defendingTeam.addGoalAgainst();
			}
		
			if (knockout == true && team1.getGoals() == team2.getGoals()) {
				i--;
			}
			
		}
	
		String result = team1.getName() + " " + team1.getGoals() + " - "  + team2.getGoals()+ " " +  team2.getName();
		Tournament.getInstance().addResult(result);
		
		if (team1.getGoals() > team2.getGoals()) {
			return team1;
		} else if (team2.getGoals() > team1.getGoals()) {
			return team2;
		} else {
			return new Team("draw", 0, 0);
		}
		
		/*double ratingSum = team1.getRating() + team2.getRating();
		double matchOutcome = Math.random() * ratingSum;
		
		if (matchOutcome < team1.getRating() ) {
			return team1;
		} else {
			return team2;
		}*/
		
	}
	
}
