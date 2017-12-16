package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class Gui {

	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame("Supermarket Management System");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 500);
		frame.setMinimumSize(new Dimension(650, 500));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel employeePanel = new EmployeePanel();
		tabbedPane.add("Employee", employeePanel);
		
		JPanel managerPanel = new ManagerPanel();
		tabbedPane.add("Manager", managerPanel);
		
		
		frame.add(tabbedPane);
		
		frame.pack();
	    frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
