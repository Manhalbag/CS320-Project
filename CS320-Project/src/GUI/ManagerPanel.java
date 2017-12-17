package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.awt.FlowLayout;

public class ManagerPanel extends JPanel {
	private JButton listEmployeesButton;
	private JButton hireEmployeeButton;
	private JButton fireEmployeeButton;
	private JButton calculateExpenseButton;
	private JPanel buttonsPanel;
	private JPanel centerPanel;
	private JButton button;
	private JComboBox employeeBox;


	public ManagerPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonsPanel = new JPanel();
		add(buttonsPanel);
		centerPanel = new JPanel();
		add(centerPanel);

		listEmployeesButton = new JButton("List Employees");
		hireEmployeeButton = new JButton("Hire Employee");
		fireEmployeeButton = new JButton("Fire Employee");
		calculateExpenseButton = new JButton("Calculate Expense");
		
		button = new JButton("Add Product");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonsPanel.add(button);
		buttonsPanel.add(listEmployeesButton);
		buttonsPanel.add(hireEmployeeButton);
		buttonsPanel.add(fireEmployeeButton);
		buttonsPanel.add(calculateExpenseButton);

		listEmployeesButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				centerPanel.removeAll();
				centerPanel.repaint();

				centerPanel.setLayout(new BorderLayout());
				JPanel listPanel = new JPanel();
				centerPanel.add(listPanel, BorderLayout.NORTH);
				

				JLabel idLabel = new JLabel("ID");
				idLabel.setForeground(Color.RED);
				listPanel.add(idLabel);

				JLabel nameLabel = new JLabel("NAME");
				nameLabel.setForeground(Color.RED);
				listPanel.add(nameLabel);

				JLabel jobLabel = new JLabel("JOB");
				jobLabel.setForeground(Color.RED);
				listPanel.add(jobLabel);

			

				centerPanel.revalidate();

			}

		});

		calculateExpenseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				centerPanel.removeAll();
				centerPanel.repaint();
				centerPanel.setLayout(new BorderLayout());
				JPanel expensePanel = new JPanel();
				centerPanel.add(expensePanel, BorderLayout.NORTH);
				expensePanel.setLayout(new GridLayout(5, 2));

			

				expensePanel.add(new JLabel("Expenses: "));
				
				

				expensePanel.add(new JLabel());
				expensePanel.add(new JLabel());

				expensePanel.add(new JLabel("Revenue: "));
			
			

				expensePanel.add(new JLabel());
				expensePanel.add(new JLabel());

				expensePanel.add(new JLabel("Profit: "));
				
				

				centerPanel.revalidate();
			}

		});

		hireEmployeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				centerPanel.removeAll();
				centerPanel.repaint();
				centerPanel.setLayout(new BorderLayout());

				JPanel topPanel = new JPanel();
				centerPanel.add(topPanel, BorderLayout.NORTH);
				topPanel.setLayout(new GridLayout(1, 2));
				JPanel leftPanel = new JPanel();
				topPanel.add(leftPanel);
				JPanel rightPanel = new JPanel();
				topPanel.add(rightPanel);

				leftPanel.setLayout(new GridLayout(3, 1));
				leftPanel.add(new JLabel("Name: "));
				leftPanel.add(new JLabel("Salary: "));

				rightPanel.setLayout(new GridLayout(3, 1));
				JTextField nameText = new JTextField();
				rightPanel.add(nameText);
				JTextField salaryText = new JTextField();
				rightPanel.add(salaryText);
				JButton addButton = new JButton("Add");
				rightPanel.add(addButton);

				centerPanel.revalidate();

				addButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if(nameText.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Error: Enter a name !", "Error", 0, null);
							try{
								double trial = Double.parseDouble(salaryText.getText());
							}catch (NumberFormatException e2) {
								salaryText.setText("");
							}
						}else{
							try {
								
								JOptionPane.showMessageDialog(null, "Employee was added successfully");
								nameText.setText("");
								salaryText.setText("");
							} catch (NumberFormatException e2) {
								if (!nameText.getText().equals("")) {
									salaryText.setText("");
									JOptionPane.showMessageDialog(null, "Error: Enter a salary !", "Error", 0, null);
	
								} else {
									JOptionPane.showMessageDialog(null, "Error: Enter a name !", "Error", 0, null);
								}
							}
						}
					}

				});
			}

		});

		fireEmployeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				centerPanel.removeAll();
				centerPanel.repaint();
				centerPanel.setLayout(new BorderLayout());

				JPanel topPanel = new JPanel();
				centerPanel.add(topPanel, BorderLayout.NORTH);
				topPanel.setLayout(new GridLayout(1, 2));
				JPanel leftPanel = new JPanel();
				topPanel.add(leftPanel);
				JPanel rightPanel = new JPanel();
				topPanel.add(rightPanel);

				leftPanel.setLayout(new GridLayout(2, 1));
				employeeBox = new JComboBox();
				employeeBox.addItem("");
				leftPanel.add(employeeBox);

				rightPanel.setLayout(new GridLayout(2, 1));

				JButton fireButton = new JButton("Fire Employee");
				rightPanel.add(fireButton);

				centerPanel.revalidate();

				fireButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Employee was fired successfully");
					}

				});

			}

		});
	}
}

