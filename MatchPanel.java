package euro2012;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MatchPanel extends JPanel {
	
	public MatchPanel(Team team1, Team team2) {

		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		String[] columnNames = {"Team", "Goals"};
		
		Object[][] data = {
				{ team1.getName(), team1.getGoals() }, { team2.getName(), team2.getGoals() }
		};
		
		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		JTable matchTable = new JTable(model);
		
		TableColumn column = null;
		int i = 0;
		for (i = 0; i < 2; i++) {
		    column = matchTable.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(200);
		    } else {
		        column.setPreferredWidth(20);
		    }
		}
		
		add(matchTable);
		
	}

}
