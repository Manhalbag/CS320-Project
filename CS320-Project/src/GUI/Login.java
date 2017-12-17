package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Login {

	private JFrame frmSupermarketManagementSystem;
	private JTextField txtId;
	private String [] names = {"manhal","berk"};
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
		frmSupermarketManagementSystem = new JFrame();
		frmSupermarketManagementSystem.setTitle("Supermarket Management System");
		frmSupermarketManagementSystem.setBounds(100, 100, 570, 340);
		frmSupermarketManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isMatched = false;
		    		for(int i=0; i<names.length; i++) {
		    			if( txtId.getText().equals(names[i])) {
		    				isMatched = true;
		    			}
		    		}
		    		
		    		if(isMatched) {
	    				frmSupermarketManagementSystem.setVisible(false);
		    			Boolean isManager = false;
		    			//use Gui to 0
		    			if(isManager) {
		    				Gui n = new Gui();
		    				//The passed attribute must be changed when integrating the backend part of the code. 
		    				n.Gui(0);
		    			}
		    			//use Gui to 1
		    			else {
		    				Gui n = new Gui();
		    				//The passed attribute must be changed when integrating the backend part of the code. 
		    				n.Gui(1);
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
