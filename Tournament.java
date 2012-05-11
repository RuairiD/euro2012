package euro2012;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Tournament extends JFrame implements ActionListener {

	/**
	 * @param args
	 */
	
	private Group groupA;
	private Group groupB;
	private Group groupC;
	private Group groupD;
	
	private ArrayList<String> results;
	
	private static Tournament instance;
	
	public static Tournament getInstance() {
		if(instance == null) {
			instance = new Tournament();
		}
	    return instance;
	}
	
	public Tournament() {
		
		super("UEFA Euro 2012");
		setSize(800,600);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		instance = this;
		
		playTournament();
	}
	
	private void playTournament() {
		
		results = new ArrayList<String>();
		setLayout(new BorderLayout());
		
		Vector<Team> teamsA = new Vector<Team>();
		teamsA.add(0, new Team("Czech Rep.", 798, 798));
		teamsA.add(1, new Team("Greece", 961, 961));
		teamsA.add(2, new Team("Poland", 514, 514));
		teamsA.add(3, new Team("Russia", 1049, 1049));
		groupA = new Group(teamsA, "Group A");
		
		Vector<Team> teamsB = new Vector<Team>();
		teamsB.add(0, new Team("Germany", 1345, 1345));
		teamsB.add(1, new Team("Netherlands", 1207, 1207));
		teamsB.add(2, new Team("Portugal", 1190, 1190));
		teamsB.add(3, new Team("Denmark", 1069, 1069));
		groupB = new Group(teamsB, "Group B");
		
		Vector<Team> teamsC = new Vector<Team>();
		teamsC.add(0, new Team("Croatia", 1114, 1114));
		teamsC.add(1, new Team("Italy", 1041, 1041));
		teamsC.add(2, new Team("Ireland", 891, 891));
		teamsC.add(3, new Team("Spain", 1442, 1442));
		groupC = new Group(teamsC, "Group C");
		
		Vector<Team> teamsD = new Vector<Team>();
		teamsD.add(0, new Team("England", 1132, 1132));
		teamsD.add(1, new Team("France", 938, 938));
		teamsD.add(2, new Team("Sweden", 931, 931));
		teamsD.add(3, new Team("Ukraine", 589, 589));
		groupD = new Group(teamsD, "Group D");

		JComponent topPanel = createTopPanel();
		add(topPanel,BorderLayout.NORTH);

		/*System.out.println("Group A");
		System.out.println("");
		groupA.printGroup();
		System.out.println("---");

		System.out.println("Group B");
		System.out.println("");
		groupB.printGroup();
		System.out.println("---");

		System.out.println("Group C");
		System.out.println("");
		groupC.printGroup();
		System.out.println("---");

		System.out.println("Group D");
		System.out.println("");
		groupD.printGroup();*/
		
		Team[] knockoutTeams = new Team[8];
		
		knockoutTeams[0] = groupA.getQualifiers()[0];
		knockoutTeams[5] = groupA.getQualifiers()[1];
		knockoutTeams[4] = groupB.getQualifiers()[0];
		knockoutTeams[1] = groupB.getQualifiers()[1];
		knockoutTeams[2] = groupC.getQualifiers()[0];
		knockoutTeams[7] = groupC.getQualifiers()[1];
		knockoutTeams[6] = groupD.getQualifiers()[0];
		knockoutTeams[3] = groupD.getQualifiers()[1];
		
		playKnockOut(knockoutTeams);
		
	}	
	
	private void addBorder(JComponent component, String title) {
		Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border tb = BorderFactory.createTitledBorder(etch,title);
		component.setBorder(tb);
	}
	
	private JComponent createTopPanel() {
		
		Box result = Box.createVerticalBox();
		
		result.add(createOptionsPanel(), BorderLayout.CENTER);
		result.add(createGroupsPanel());
		
		return result;
		
	}
	
	private JComponent createOptionsPanel() {
		
		Box result = Box.createVerticalBox();
		JLabel titleLabel = new JLabel("UEFA Euro 2012 Simulator - Ruairi Dorrity, 2012", JLabel.CENTER);
		titleLabel.setVerticalTextPosition(JLabel.CENTER);
		titleLabel.setHorizontalTextPosition(JLabel.CENTER);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		
		JLabel nameLabel = new JLabel("Ruairi Dorrity, 2012", JLabel.CENTER);
		nameLabel.setVerticalTextPosition(JLabel.CENTER);
		nameLabel.setHorizontalTextPosition(JLabel.CENTER);
		
		JButton resetButton = new JButton("Create New Tournament");
		resetButton.setVerticalTextPosition(AbstractButton.CENTER);
		resetButton.setHorizontalTextPosition(AbstractButton.CENTER);
		resetButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e){
                Euro2012.resetTournament();
            }
        });
		//Box text = Box.createVerticalBox();
		JPanel titleText = new JPanel();
		JPanel nameText = new JPanel();
		JPanel resetPanel = new JPanel();
		
		titleText.add(titleLabel, SwingConstants.CENTER);
		nameText.add(nameLabel, SwingConstants.CENTER);
		resetPanel.add(resetButton, SwingConstants.CENTER);

		//result.add(resetPanel, SwingConstants.CENTER);
		//result.add(nameText, SwingConstants.CENTER);
		result.add(titleText, SwingConstants.CENTER);
		
		return result;
		
	}
	
	private JComponent createResultsPanel() {
		
		Box result = Box.createVerticalBox();
		JList resultsList = new JList();
		resultsList.setListData(results.toArray());
		JScrollPane scrollPane = new JScrollPane(resultsList);
		scrollPane.setPreferredSize(new Dimension(200,88));
		result.add(scrollPane);
		return result;
		
	}
	
	private JComponent createGroupsPanel() {
		
		Box result = Box.createHorizontalBox();
		result.add( createGroupPanel(groupA) );
		result.add( createGroupPanel(groupB) );
		result.add( createGroupPanel(groupC) );
		result.add( createGroupPanel(groupD) );
		return result;
		
	}
	
	private JComponent createGroupPanel(Group group) {

		GroupPanel groupPanel = new GroupPanel(group);
		addBorder(groupPanel,group.getName());
		return groupPanel;
		
	}
	
	private JComponent createKnockoutPanel() {
		
		Box result = Box.createHorizontalBox();
		return result;
		
	}
	
	private JComponent createQuarterFinalPanel(Team[] teams) {
		
		Box result = Box.createVerticalBox();
		
		int i = 0;
		for (i = 0; i < 4; i++) {
			JComponent newPanel = createMatchPanel(teams[2*i], teams[2*i+1]);
			addBorder(newPanel, "Quarter Final");
			result.add(newPanel);
		}
		return result;
		
	}
	
	private JComponent createSemiFinalPanel(Team[] teams) {
		
		Box result = Box.createVerticalBox();
		
		int i = 0;
		for (i = 0; i < 2; i++) {
			JComponent newPanel = createMatchPanel(teams[2*i], teams[2*i+1]);
			addBorder(newPanel, "Semi Final");
			result.add(newPanel);
		}
		return result;
		
	}
	
	private JComponent createFinalPanel(Team[] teams) {
		
		Box result = Box.createVerticalBox();
		
		int i = 0;
		for (i = 0; i < 1; i++) {
			JComponent newPanel = createMatchPanel(teams[2*i], teams[2*i+1]);
			addBorder(newPanel, "Final");
			result.add(newPanel);
		}
		return result;
		
	}
	
	private JComponent createChampionsPanel(Team team) {
		
		Box result = Box.createVerticalBox();
		
		JLabel message = new JLabel("Champions:");
		JLabel champions = new JLabel(team.getName());
		
		result.add(message);
		result.add(champions);
		
		return result;
		
	}
	
	private JComponent createMatchPanel(Team team1, Team team2) {

		MatchPanel matchPanel = new MatchPanel(team1, team2);
		//addBorder(groupPanel,group.getName());
		return matchPanel;
		
	}
	
	private Team playKnockOut(Team[] teams) {
		
		JComponent knockoutPanel = createKnockoutPanel();
		addBorder(knockoutPanel, "Knockout Round");
		add(knockoutPanel, BorderLayout.CENTER);

		Tournament.getInstance().addResult("Quarter Finals");
		Tournament.getInstance().addResult(" ");
		Team[] semiFinalists = new Team[4];
		semiFinalists[0] = Match.playMatch(teams[0], teams[1], true);
		semiFinalists[1] = Match.playMatch(teams[2], teams[3], true);
		semiFinalists[2] = Match.playMatch(teams[4], teams[5], true);
		semiFinalists[3] = Match.playMatch(teams[6], teams[7], true);
		Tournament.getInstance().addResult("");
		JComponent qfPanel = createQuarterFinalPanel(teams);
		knockoutPanel.add(qfPanel);

		Tournament.getInstance().addResult("Semi Finals");
		Tournament.getInstance().addResult(" ");
		Team[] finalists = new Team[2];
		finalists[0] = Match.playMatch(semiFinalists[0], semiFinalists[1], true);
		finalists[1] = Match.playMatch(semiFinalists[2], semiFinalists[3], true);
		Tournament.getInstance().addResult(" ");
		JComponent sfPanel = createSemiFinalPanel(semiFinalists);
		knockoutPanel.add(sfPanel);

		Tournament.getInstance().addResult("Final");
		Tournament.getInstance().addResult(" ");
		Team champions = Match.playMatch(finalists[0], finalists[1], true);
		JComponent fPanel = createFinalPanel(finalists);
		knockoutPanel.add(fPanel);
		
		JComponent championsPanel = createChampionsPanel(champions);
		knockoutPanel.add(championsPanel);
		
		JComponent resultsPanel = createResultsPanel();
		addBorder(resultsPanel, "Results");
		add(resultsPanel, BorderLayout.WEST);
		
		return champions;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	    if ("reset".equals(e.getActionCommand())) {
	    	playTournament();
	    }
	} 
	
	public void addResult(String result) {
		results.add(result);
	}

}
