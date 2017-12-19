package GUI;

import Model.Employee;
import Model.Supermarket;
import Model.Product;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class ManagerPanel extends JPanel {

    private JButton exitButton;
	private JButton listEmployeesButton;
	private JButton hireEmployeeButton;
	private JButton fireEmployeeButton;
	private JButton viewFinancialStatusButton;
	private JButton listProductsButton;
    private JButton searchProductButton;
	private JPanel buttonsPanel;
	private JButton addProductButton;
	private JComboBox employeeBox;
    private JTable table;
    private String employeeName;
	private JButton btnLogout;



	public ManagerPanel(Supermarket supermarket) {
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(7,1));
        add(buttonsPanel);

		listEmployeesButton = new JButton("List Employees");
		hireEmployeeButton = new JButton("Hire Employee");
		fireEmployeeButton = new JButton("Fire Employee");
		viewFinancialStatusButton = new JButton("View Financial Status");
		addProductButton = new JButton("Add Product");

		buttonsPanel.add(addProductButton);
		buttonsPanel.add(listEmployeesButton);
		buttonsPanel.add(hireEmployeeButton);
		buttonsPanel.add(fireEmployeeButton);
		listProductsButton = new JButton("List Products");
		buttonsPanel.add(listProductsButton);
		
		        listProductsButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent actionEvent) {
		
		                buttonsPanel.setVisible(false);
		                setLayout(new BorderLayout());
		
		                exitButton = new JButton("Close");
		                JPanel bottomPanel = new JPanel();
		                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		                exitButton.setEnabled(true);
		                bottomPanel.add(exitButton);
		                add(bottomPanel, BorderLayout.SOUTH);
		
		                String[] columnNames = { "Product Name", "Product ID", "Cost", "Price", "Type", "Amount"};
		
		                JPanel currentPanel_Main = new JPanel();
		                add(currentPanel_Main, BorderLayout.CENTER);
		                currentPanel_Main.setLayout(new BorderLayout());
		                currentPanel_Main.add(new JLabel(""), BorderLayout.NORTH);
		
		                JPanel currentPanel = new JPanel();
		                currentPanel_Main.add(currentPanel, BorderLayout.CENTER);
		                currentPanel.setLayout(new BorderLayout());
		
		                table = new JTable();
		                DefaultTableModel model = new DefaultTableModel();
		                model.setColumnIdentifiers(columnNames);
		                table.setModel(model);
		                table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		                table.setEnabled(false);
		                table.setFillsViewportHeight(true);
		                JScrollPane scrollPane = new JScrollPane(table);
		                currentPanel.setLayout(new BorderLayout());
		                currentPanel.add(scrollPane, BorderLayout.NORTH);
		
		                Object[] row = new Object[6];
		
		                for(Product p : supermarket.sortedProducts.values()) {
		                    row[0] = p.name;
		                    row[1] = new Integer(p.ID);
		                    row[2] = new Double(p.cost);
		                    row[3] = new Double(p.price);
		                    row[4] = p.type;
		                    row[5] = new Integer(p.amount);
		                    model.addRow(row);
		                }
		
		                exitButton.addActionListener(new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent actionEvent) {
		                        removeAll();
		                        setLayout(new FlowLayout());
		                        add(buttonsPanel);
		                        buttonsPanel.setVisible(true);
		                        repaint();
		                    }
		                });
		            }
		        });
		searchProductButton = new JButton("Search Product");
		buttonsPanel.add(searchProductButton);
		
		        searchProductButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent actionEvent) {
		
		                Object[] possibleValues = { "By Name","By ID","By Type"};
		                Object selectedValue = JOptionPane.showInputDialog(null,
		                        "Choose one", "Input",
		                        JOptionPane.INFORMATION_MESSAGE, null,
		                        possibleValues, possibleValues[0]);
		
		                buttonsPanel.setVisible(false);
		                setLayout(new BorderLayout());
		
		                JPanel topPanel = new JPanel();
		                add(topPanel, BorderLayout.NORTH);
		                topPanel.setLayout(new GridLayout(1, 2));
		                JPanel leftPanel = new JPanel();
		                topPanel.add(leftPanel);
		                JPanel rightPanel = new JPanel();
		                topPanel.add(rightPanel);
		
		                leftPanel.setLayout(new GridLayout(3, 1));
		                if(Objects.equals(selectedValue, possibleValues[0])){
		                    leftPanel.add(new JLabel("Name: "));
		                }else if(Objects.equals(selectedValue, possibleValues[1])){
		                    leftPanel.add(new JLabel("ID: "));
		                }else if(Objects.equals(selectedValue, possibleValues[2])){
		                    leftPanel.add(new JLabel("Type: "));
		                }else {
		                    removeAll();
		                    setLayout(new FlowLayout());
		                    add(buttonsPanel);
		                    buttonsPanel.setVisible(true);
		                    repaint();
		                }
		
		                rightPanel.setLayout(new GridLayout(3, 1));
		                JTextField nameText = new JTextField();
		                rightPanel.add(nameText);
		                JButton searchButton = new JButton("Search");
		                rightPanel.add(searchButton);
		
		                exitButton = new JButton("Exit");
		                JPanel bottomPanel = new JPanel();
		                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		                exitButton.setEnabled(true);
		                bottomPanel.add(exitButton);
		                add(bottomPanel, BorderLayout.SOUTH);
		
		                searchButton.addActionListener(new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent actionEvent) {
		
		                        leftPanel.setVisible(false);
		                        rightPanel.setVisible(false);
		                        topPanel.setVisible(false);
		                        bottomPanel.setVisible(false);
		
		                        setLayout(new BorderLayout());
		
		                        String[] columnNames = { "Product Name", "Product ID", "Cost", "Price", "Type", "Amount"};
		
		                        JPanel currentPanel_Main = new JPanel();
		                        add(currentPanel_Main, BorderLayout.CENTER);
		                        currentPanel_Main.setLayout(new BorderLayout());
		                        currentPanel_Main.add(new JLabel(""), BorderLayout.NORTH);
		
		                        JPanel currentPanel = new JPanel();
		                        currentPanel_Main.add(currentPanel, BorderLayout.CENTER);
		                        currentPanel.setLayout(new BorderLayout());
		
		                        table = new JTable();
		                        DefaultTableModel model = new DefaultTableModel();
		                        model.setColumnIdentifiers(columnNames);
		                        table.setModel(model);
		                        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		                        table.setEnabled(false);
		                        table.setFillsViewportHeight(true);
		                        JScrollPane scrollPane = new JScrollPane(table);
		                        currentPanel.setLayout(new BorderLayout());
		                        currentPanel.add(scrollPane, BorderLayout.NORTH);
		
		                        exitButton = new JButton("Exit");
		                        JPanel bottomPanel = new JPanel();
		                        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		                        exitButton.setEnabled(true);
		                        bottomPanel.add(exitButton);
		                        add(bottomPanel, BorderLayout.SOUTH);
		
		                        Object[] row = new Object[6];
		
		                        for(Product p : supermarket.sortedProducts.values()) {
		                            if(Objects.equals(selectedValue, possibleValues[0])){
		                                if(nameText.getText().equalsIgnoreCase(p.name)){
		                                    row[0] = p.name;
		                                    row[1] = new Integer(p.ID);
		                                    row[2] = new Double(p.cost);
		                                    row[3] = new Double(p.price);
		                                    row[4] = p.type;
		                                    row[5] = new Integer(p.amount);
		                                    model.addRow(row);
		                                }
		                            }else if(Objects.equals(selectedValue, possibleValues[1])){
		                                if(nameText.getText().equalsIgnoreCase(Integer.toString(p.ID))){
		                                    row[0] = p.name;
		                                    row[1] = new Integer(p.ID);
		                                    row[2] = new Double(p.cost);
		                                    row[3] = new Double(p.price);
		                                    row[4] = p.type;
		                                    row[5] = new Integer(p.amount);
		                                    model.addRow(row);
		                                }
		                            }else {
		                                if(nameText.getText().equalsIgnoreCase(p.type)){
		                                    row[0] = p.name;
		                                    row[1] = new Integer(p.ID);
		                                    row[2] = new Double(p.cost);
		                                    row[3] = new Double(p.price);
		                                    row[4] = p.type;
		                                    row[5] = new Integer(p.amount);
		                                    model.addRow(row);
		                                }
		                            }
		                        }
		
		                        exitButton.addActionListener(new ActionListener() {
		                            @Override
		                            public void actionPerformed(ActionEvent actionEvent) {
		                                removeAll();
		                                setLayout(new FlowLayout());
		                                add(buttonsPanel);
		                                buttonsPanel.setVisible(true);
		                                repaint();
		                            }
		                        });
		                    }
		                });
		
		                exitButton.addActionListener(new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent actionEvent) {
		                        removeAll();
		                        setLayout(new FlowLayout());
		                        add(buttonsPanel);
		                        buttonsPanel.setVisible(true);
		                        repaint();
		                    }
		                });
		            }
		        });
		buttonsPanel.add(viewFinancialStatusButton);
		
		btnLogout = new JButton("Logout");
		buttonsPanel.add(btnLogout);
		btnLogout.setBackground(Color.RED);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                buttonsPanel.setVisible(false);
                setLayout(new BorderLayout());

                JPanel topPanel = new JPanel();
                add(topPanel, BorderLayout.NORTH);
                topPanel.setLayout(new GridLayout(1, 2));
                JPanel leftPanel = new JPanel();
                topPanel.add(leftPanel);
                JPanel rightPanel = new JPanel();
                topPanel.add(rightPanel);

                exitButton = new JButton("Close");
                JPanel bottomPanel = new JPanel();
                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                exitButton.setEnabled(true);
                bottomPanel.add(exitButton);
                add(bottomPanel, BorderLayout.SOUTH);

                leftPanel.setLayout(new GridLayout(6,1));
                leftPanel.add(new JLabel("ID: "));
                leftPanel.add(new JLabel("Name: "));
                leftPanel.add(new JLabel("Cost: "));
                leftPanel.add(new JLabel("Price: "));
                leftPanel.add(new JLabel("Type: "));
                leftPanel.add(new JLabel("Amount: "));

                rightPanel.setLayout(new GridLayout(7, 1));
                JTextField IDText = new JTextField();
                JTextField nameText = new JTextField();
                JTextField costText = new JTextField();
                JTextField priceText = new JTextField();
                JTextField typeText = new JTextField();
                JTextField amountText = new JTextField();
                rightPanel.add(IDText);
                rightPanel.add(nameText);
                rightPanel.add(costText);
                rightPanel.add(priceText);
                rightPanel.add(typeText);
                rightPanel.add(amountText);
                JButton addButton = new JButton("Add");
                rightPanel.add(addButton);

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(supermarket.products.size());

                        boolean isPresent = false;
                        if (IDText.getText().equals("") || nameText.getText().equals("") || costText.getText().equals("") ||
                                priceText.getText().equals("") || typeText.getText().equals("") || amountText.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "None of the fields should be left blank.");
                        }else {
                            for (int i = 1; i < supermarket.products.size(); i++) {
                                if(nameText.getText().equalsIgnoreCase(supermarket.products.get(i).name)){
                                    isPresent = true;
                                }
                            }
                            if (!isPresent) {
                                JOptionPane.showMessageDialog(null, "Item added successfully.");
                                supermarket.products.put(supermarket.products.size() + 1, new Product(nameText.getText(),
                                        supermarket.products.size() + 1, Double.parseDouble(costText.getText()),
                                        Double.parseDouble(priceText.getText()), typeText.getText(), Integer.parseInt(amountText.getText())));
                                System.out.println(supermarket.products.size());
                                supermarket.takeFromBank(Double.parseDouble(costText.getText()) * Double.parseDouble(amountText.getText()));
                                supermarket.sortProducts();
                            }else {
                                for(int i = 1; i < supermarket.products.size();i++){
                                    if(supermarket.products.get(i).name.equalsIgnoreCase(nameText.getText())){
                                        supermarket.products.get(i).amount += Integer.parseInt(amountText.getText());
                                    }
                                }
                            }
                        }
                    }
                });
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        removeAll();
                        setLayout(new FlowLayout());
                        add(buttonsPanel);
                        buttonsPanel.setVisible(true);
                        repaint();
                    }
                });
            }
        });

		listEmployeesButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

                buttonsPanel.setVisible(false);
                setLayout(new BorderLayout());

                exitButton = new JButton("Close");
                JPanel bottomPanel = new JPanel();
                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                exitButton.setEnabled(true);
                bottomPanel.add(exitButton);
                add(bottomPanel, BorderLayout.SOUTH);

                String[] columnNames = { "Employee Name", "Employee ID", "Shift", "Salary", "Title"};

                JPanel currentPanel_Main = new JPanel();
                add(currentPanel_Main, BorderLayout.CENTER);
                currentPanel_Main.setLayout(new BorderLayout());
                currentPanel_Main.add(new JLabel(""), BorderLayout.NORTH);

                JPanel currentPanel = new JPanel();
                currentPanel_Main.add(currentPanel, BorderLayout.CENTER);
                currentPanel.setLayout(new BorderLayout());

                table = new JTable();
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNames);
                table.setModel(model);
                table.setPreferredScrollableViewportSize(new Dimension(600, 400));
                table.setEnabled(false);
                table.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(table);
                currentPanel.setLayout(new BorderLayout());
                currentPanel.add(scrollPane, BorderLayout.NORTH);

                Object[] row = new Object[5];

                for(Employee emp : supermarket.employees) {
                    row[0] = emp.name;
                    row[1] = new Integer(emp.ID);
                    row[2] = emp.shiftHours;
                    row[3] = new Double(emp.salary);
                    row[4] = emp.jobTitle;
                    model.addRow(row);
                }

                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        removeAll();
                        setLayout(new FlowLayout());
                        add(buttonsPanel);
                        buttonsPanel.setVisible(true);
                        repaint();
                    }
                });
			}
		});

		viewFinancialStatusButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}

		});

        hireEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                buttonsPanel.setVisible(false);
                setLayout(new BorderLayout());

                JPanel topPanel = new JPanel();
                add(topPanel, BorderLayout.NORTH);
                topPanel.setLayout(new GridLayout(1, 2));
                JPanel leftPanel = new JPanel();
                topPanel.add(leftPanel);
                JPanel rightPanel = new JPanel();
                topPanel.add(rightPanel);

                exitButton = new JButton("Close");
                JPanel bottomPanel = new JPanel();
                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                exitButton.setEnabled(true);
                bottomPanel.add(exitButton);
                add(bottomPanel, BorderLayout.SOUTH);

                leftPanel.setLayout(new GridLayout(5,1));
                leftPanel.add(new JLabel("ID: "));
                leftPanel.add(new JLabel("Name: "));
                leftPanel.add(new JLabel("Shift: "));
                leftPanel.add(new JLabel("Salary: "));
                leftPanel.add(new JLabel("Title: "));

                rightPanel.setLayout(new GridLayout(6, 1));
                JTextField IDText = new JTextField();
                JTextField nameText = new JTextField();
                JTextField shiftText = new JTextField();
                JTextField salaryText = new JTextField();
                JTextField titleText = new JTextField();
                rightPanel.add(IDText);
                rightPanel.add(nameText);
                rightPanel.add(shiftText);
                rightPanel.add(salaryText);
                rightPanel.add(titleText);
                JButton addButton = new JButton("Add");
                rightPanel.add(addButton);

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(supermarket.employees.size());

                        if(IDText.getText().equals("") ||nameText.getText().equals("") || shiftText.getText().equals("") ||
                                salaryText.getText().equals("") || titleText.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "None of the fields should be left blank");
                        }else {
                            JOptionPane.showMessageDialog(null, "Item added successfully.");
                            supermarket.employees.add(new Employee(supermarket.employees.size()+1, nameText.getText(),
                                    shiftText.getText(), Double.parseDouble(salaryText.getText()), titleText.getText()));
                            System.out.println(supermarket.employees.size());
                        }
                    }
                });
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        removeAll();
                        setLayout(new FlowLayout());
                        add(buttonsPanel);
                        buttonsPanel.setVisible(true);
                        repaint();
                    }
                });
            }
        });

		fireEmployeeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

                buttonsPanel.setVisible(false);
                setLayout(new BorderLayout());

                JPanel topPanel = new JPanel();
                add(topPanel, BorderLayout.NORTH);
                topPanel.setLayout(new GridLayout(1, 2));

                JPanel leftPanel = new JPanel();
                leftPanel.setLayout(new GridLayout(2, 1));
                employeeBox = new JComboBox();
                employeeBox.addItem("");
                for(Employee ee : supermarket.employees){
                    employeeBox.addItem(ee.name);
                }
                leftPanel.add(employeeBox);
                topPanel.add(leftPanel);

                JPanel rightPanel = new JPanel();
                rightPanel.setLayout(new GridLayout(2, 1));
                JButton fireButton = new JButton("Fire Employee");
                rightPanel.add(fireButton);
                topPanel.add(rightPanel);

                exitButton = new JButton("Close");
                JPanel bottomPanel = new JPanel();
                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                exitButton.setEnabled(true);
                bottomPanel.add(exitButton);
                add(bottomPanel, BorderLayout.SOUTH);

                employeeBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        employeeName = (String) e.getItem();

                        if (!employeeName.equals("")) {
                            fireButton.setEnabled(true);
                        } else {
                            fireButton.setEnabled(false);
                        }
                    }
                });

				fireButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    int index = 0;
					    for(int i = 0; i < supermarket.employees.size(); i++){
					        if(supermarket.employees.get(i).name.equalsIgnoreCase(employeeName)){
                                index = i;
                            }
                        }
                        supermarket.employees.remove(index);
                        JOptionPane.showMessageDialog(null, "Employee was fired successfully");
                    }
				});

                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        removeAll();
                        setLayout(new FlowLayout());
                        add(buttonsPanel);
                        buttonsPanel.setVisible(true);
                        repaint();
                    }
                });
			}
		});

	}
}

