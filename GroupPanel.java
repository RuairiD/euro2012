package euro2012;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GroupPanel extends JPanel {
	
	public GroupPanel(Group group) {

		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		String[] columnNames = {"Team",
								"F",
								"A",
								"GD",
								"Pts"};
		
		Object[][] data = new Object[4][4];
		
		int i = 1;
		for (i = 1; i <= 4; i++) {
			Team team = group.getTeamAtPosition(i);
			Object[] newData = {team.getName(), team.getGoalsFor(), team.getGoalsAgainst(), team.getGoalsFor() - team.getGoalsAgainst(), team.getPoints()};
			data[i-1] = newData;
		}
		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		JTable groupTable = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(groupTable);
		scrollPane.setPreferredSize(new Dimension(800,88));
		scrollPane.revalidate();
		add(scrollPane, BorderLayout.NORTH);
		
		TableColumn column = null;
		for (i = 0; i < 5; i++) {
		    column = groupTable.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(100); //third column is bigger
		    } else {
		        column.setPreferredWidth(30);
		    }
		}
		
	}

}
