package GUI;
import Model.Employee;
import Model.Manager;
import Model.Product;
import Model.Supermarket;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.util.TreeMap;

public class Login {

	private JFrame frmSupermarketManagementSystem;
	private JTextField txtId;
	private String [] managers = {"manhal","berk"};
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
	private void initialize() {
        Supermarket market = new Supermarket();
        Product a = new Product("Chocolate", 1,0.3,1.2,"food",5);
        Product b = new Product("Milk", 2,0.5,1.5,"beverage",10);
        Product c = new Product("Bleach", 3,1.1,3.5,"utilities",2);
        Product d = new Product("Chicken",4, 1,5,"food",12);

        Employee x = new Manager(1, "Ali", "09:00-17:00", 5000.00, "Manager");
        Manager t = new Manager(2, "Murat", "10:00-18:00", 4600.00, "Manager");
        Employee v = new Employee(3, "Süreyya", "10:00-18:00", 2600.00, "Employee");
        market.products.put(1, a);
        market.products.put(2, b);
        market.products.put(3, c);
        market.products.put(4, d);
        market.employees.add(x);
        market.employees.add(t);
        market.employees.add(v);
        market.sortProducts();

		frmSupermarketManagementSystem = new JFrame();
		frmSupermarketManagementSystem.setTitle("Supermarket Management System");
		frmSupermarketManagementSystem.setBounds(100, 100, 570, 340);
		frmSupermarketManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLogin = new JButton("Login");
//        JRadioButton checkTitle = new JRadioButton("I am a Manager.");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isMatched = false;
		    		for(int i=0; i<managers.length; i++) {
		    			if( txtId.getText().equalsIgnoreCase(managers[i])) {
		    				isMatched = true;
		    			}
		    		}
		    		
		    		if(isMatched) {
	    				frmSupermarketManagementSystem.setVisible(false);
		    			Boolean isManager = true;
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
