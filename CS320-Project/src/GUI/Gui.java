package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class Gui {
	
	//Gui takes an int type that specifies whether the logged in user is an employee or the administrator.
	//type can be 1 or 0, where 1 is an employee and 0 is the administrator.
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void Gui(int type) {
		
		
		JFrame frame = new JFrame("Supermarket Management System");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 500);
		frame.setMinimumSize(new Dimension(650, 500));
		
		if(type==0) {
			JPanel managerPanel = new ManagerPanel();
			frame.getContentPane().add(managerPanel);
		}
		else {
			JPanel employeePanel = new EmployeePanel();
			frame.getContentPane().add(employeePanel);
		}
		
		frame.pack();
	    frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}