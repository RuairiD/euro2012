package euro2012;

import java.util.Collections;
import java.util.Vector;

public class Group {

	private Vector<Team> teams;
	private String name;
	
	public Group(Vector<Team> pTeams, String pName) {
	
		teams = pTeams;
		name = pName;
		
		Tournament.getInstance().addResult(name);
		Tournament.getInstance().addResult(" ");
		
		playGroup(1);
		
	}
	
	public Team[] getQualifiers() {
		
		Team[] result = new Team[2];
		result[0] = getTeamAtPosition(1);
		result[1] = getTeamAtPosition(2);
		
		return result;
		
	}
	
	public void printGroup() {
		int i = 1; 
		for (i = 1; i <= 4; i++) {
			System.out.println(getTeamAtPosition(i).getName() + ": " + getTeamAtPosition(i).getPoints());
		}
		System.out.println("---");
	}
	
	private void playGroup(int rounds) {
		
		if (rounds <= 0) {

			Tournament.getInstance().addResult(" ");
			return;
			
		}
		
		playMatch(teams.get(0), teams.get(1));
		playMatch(teams.get(2), teams.get(3));
		
		playMatch(teams.get(0), teams.get(2));
		playMatch(teams.get(1), teams.get(3));
		
		playMatch(teams.get(0), teams.get(3));
		playMatch(teams.get(1), teams.get(2));
		
		playGroup(rounds - 1);
		
	}
	
	private void playMatch(Team team1, Team team2) {
		
		Team winningTeam = Match.playMatch(team1, team2, false);
		
		if (winningTeam.getName() == "draw") {
			team1.draw();
			team2.draw();
		} else {
			winningTeam.win();
		}
		
	}
	
	public Team getTeamAtPosition(int position) {

		
		Team team = new Team("", 0, 0);
		Vector<Team> group = copyGroup(teams);
		
		int i = 0;
		for (i = 0; i < position; i++) {
			
			team = getFirstTeam(group);
			group.remove(team);
			
		}
		
		return team;
		
	}
	
	public Team getFirstTeam(Vector<Team> pGroup) {
		
		Team firstTeam = pGroup.get(0);
		int i = 0;
		
		for (i = 0; i < pGroup.size(); i++) {
			
			if (pGroup.get(i).getPoints() > firstTeam.getPoints() ) {
				
				firstTeam = pGroup.get(i);
				
			} else if (pGroup.get(i).getPoints() == firstTeam.getPoints() ) {
				
				int firstTeamGD = firstTeam.getGoalsFor() - firstTeam.getGoalsAgainst();
				int newTeamGD = pGroup.get(i).getGoalsFor() - pGroup.get(i).getGoalsAgainst();
				
				if (newTeamGD > firstTeamGD) {
					
					firstTeam = pGroup.get(i);
					
				} else if (newTeamGD == firstTeamGD && pGroup.get(i).getGoalsFor() > firstTeam.getGoalsFor()) {
					
					firstTeam = pGroup.get(i);
					
				}
				
			}
			
		}
		
		return firstTeam;
		
	}
	
	private Vector<Team> copyGroup(Vector<Team> pGroup) {
		
		Vector<Team> group = new Vector<Team>();
		int i = 0;
		
		for (i = 0; i < pGroup.size(); i++) {
			
			group.add(i, pGroup.get(i));
			
		}
		
		Collections.copy(group, pGroup);
		
		return group;
		
	}
	
	public String getName() {
		
		return name;
		
	}

}
