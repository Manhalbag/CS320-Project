package GUI;
import Model.Employee;
import Model.Manager;
import Model.Product;
import Model.Supermarket;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.util.TreeMap;

public class Login {

	private JFrame frmSupermarketManagementSystem;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmSupermarketManagementSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
    String managerName;

	private void initialize() {
		
        Supermarket market = new Supermarket();
        
	    try {
    		
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMS","root","");
			
			Statement command = connection.createStatement();
			
			ResultSet data = command.executeQuery("SELECT * FROM Products");
			
			int count=1;
			while(data.next()) {
				market.products.put(count,new Product(data.getString("name"),data.getInt("ID"),data.getDouble("cost"),data.getDouble("price"),data.getString("type"),data.getInt("amount"), data.getInt("originalAmount")));
				count++;
			}
			
			data = command.executeQuery("SELECT * FROM Employees");
			while(data.next()) {
				if(data.getInt("ID")!=1) {
					market.employees.add(new Employee(data.getInt("ID"), data.getString("name"), data.getString("shift"), data.getDouble("salary"), data.getString("title")));
				}
				else {
					market.employees.add(new Manager(data.getInt("ID"), data.getString("name"), data.getString("shift"), data.getDouble("salary"), data.getString("title")));
				}
			}
			
			data = command.executeQuery("SELECT * FROM Employees WHERE id=1");
			while(data.next()) {
				managerName = data.getString("name");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        market.sortProducts();


		frmSupermarketManagementSystem = new JFrame();
		frmSupermarketManagementSystem.setTitle("Supermarket Management System");
		frmSupermarketManagementSystem.setBounds(100, 100, 570, 340);
		frmSupermarketManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isMatched = false;
				Boolean isManager = false;
				
				for(Employee emp : market.employees) {
					if(txtId.getText().equalsIgnoreCase(emp.name)) {
	    					isMatched = true;
		    				if(txtId.getText().equalsIgnoreCase(managerName)){
		    					isManager = true;
		    				}
	    				}
				}
		    	
		    		
		    		if(isMatched) {
	    				frmSupermarketManagementSystem.setVisible(false);
		    			
		    			//use Gui to 0
		    			if(isManager) {
		    				Gui n = new Gui();
		    				//The passed attribute must be changed when integrating the backend part of the code. 
		    				n.startGui(market,0);
		    			}
		    			//use Gui to 1
		    			else {
		    				Gui n = new Gui();
		    				//The passed attribute must be changed when integrating the backend part of the code. 
		    				n.startGui(market,1);
		    			}
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(frmSupermarketManagementSystem,
		    				    "Username is not correct.",
		    				    "Error",
		    				    JOptionPane.ERROR_MESSAGE);
		    			txtId.setText("");
		    		}
			}
		});
		
		JLabel lblWelcome = new JLabel("Welcome To SMS");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Lucida Grande", Font.BOLD, 36));
		
		JLabel lblUsername = new JLabel("Username   :");
		
		txtId = new JTextField();
		txtId.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmSupermarketManagementSystem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblWelcome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(167)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtId, Alignment.LEADING))
					.addContainerGap(185, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblWelcome)
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogin)
					.addGap(149))
		);
		frmSupermarketManagementSystem.getContentPane().setLayout(groupLayout);
	}

}
