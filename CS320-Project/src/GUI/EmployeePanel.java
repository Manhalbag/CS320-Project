package GUI;

import Model.Product;
import Model.Supermarket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class EmployeePanel extends JPanel {
	
	private JButton saleButton;
    private JButton listProductsButton;
    private JButton searchProductButton;

    private JButton exitButton;
	private JButton finalizeButton;
	private JLabel priceLabel;
    private JPanel buttonsPanel;
    private JButton addButton;
	private JComboBox boxes;
	private JSpinner spinner;
	private JTable table;
	private double unitPrice;
	private double price;
	private String itemName;
	private int count = 1;
	private JButton btnLogout;

	public EmployeePanel(Supermarket supermarket) {


        buttonsPanel = new JPanel();
        add(buttonsPanel);
		saleButton = new JButton("New Sale");
        listProductsButton = new JButton("List Products");
        searchProductButton = new JButton("Search Product");

		buttonsPanel.add(saleButton);
		buttonsPanel.add(listProductsButton);
		buttonsPanel.add(searchProductButton);
		
		btnLogout = new JButton("Logout");
		buttonsPanel.add(btnLogout);
		btnLogout.setBackground(Color.RED);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				  try {
			    		
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMS","root","");
						
						Statement command = connection.createStatement();
						
			            for(Product p : supermarket.sortedProducts.values()) {
			            		int data = command.executeUpdate("UPDATE Products SET amount="+p.amount+" WHERE ID="+p.ID);
			            }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
				  
				  	System.exit(0);
				
			}
		});
		btnLogout.setHorizontalAlignment(SwingConstants.RIGHT);

		saleButton.addActionListener(new ActionListener() {

            double totalPrice = 0;
            @Override
			public void actionPerformed(ActionEvent e) {

//				remove(saleButton);
			    buttonsPanel.setVisible(false);

				setLayout(new BorderLayout());

				JPanel bottomPanel = new JPanel();
				bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				exitButton = new JButton("Close");
				finalizeButton = new JButton("Finalize");
				finalizeButton.setEnabled(false);
				exitButton.setEnabled(true);
				bottomPanel.add(finalizeButton);
				bottomPanel.add(exitButton);
				add(bottomPanel, BorderLayout.SOUTH);

				JPanel addProductPanel_Main = new JPanel();
				addProductPanel_Main.setLayout(new BorderLayout());
				addProductPanel_Main.add(new JLabel("-Sell Product-"), BorderLayout.NORTH);

				add(addProductPanel_Main, BorderLayout.NORTH);

				JPanel addProductPanel = new JPanel();
				addProductPanel_Main.add(addProductPanel, BorderLayout.CENTER);

				addProductPanel.setLayout(new GridLayout(1, 2));

				JPanel addProductPanel_Right = new JPanel();
				JPanel addProductPanel_Left = new JPanel();
				addProductPanel.add(addProductPanel_Left);
				addProductPanel.add(addProductPanel_Right);
				addProductPanel_Right.setLayout(new GridLayout(5, 1));
				addProductPanel_Left.setLayout(new GridLayout(5, 1));
				addProductPanel_Left.add(new JLabel("Product:"));
				addProductPanel_Left.add(new JLabel("Count:"));
				addProductPanel_Left.add(new JLabel("Price:"));

				boxes = new JComboBox();
				boxes.addItem("");
				for(Product p : supermarket.products.values()){
                    boxes.addItem(p.name);
                }
			
				addProductPanel_Right.add(boxes);

				spinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
				spinner.setEditor(new JSpinner.DefaultEditor(spinner));
				spinner.setEnabled(true);
				addProductPanel_Right.add(spinner);

				JPanel pricePanel = new JPanel();
				priceLabel = new JLabel("0,00 TL");
				addProductPanel_Right.add(pricePanel);
				pricePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				pricePanel.add(priceLabel);

				addButton = new JButton("Add");
				addButton.setEnabled(false);
				addProductPanel_Right.add(addButton);

				JPanel currentOrderPanel_Main = new JPanel();
				add(currentOrderPanel_Main, BorderLayout.CENTER);
				currentOrderPanel_Main.setLayout(new BorderLayout());
				currentOrderPanel_Main.add(new JLabel("-Current Sale-"), BorderLayout.NORTH);

				JPanel currentOrderPanel = new JPanel();
				currentOrderPanel_Main.add(currentOrderPanel, BorderLayout.CENTER);
				currentOrderPanel.setLayout(new BorderLayout());

				String[] columnNames = { "Product Name", "Count", "Price", };

				table = new JTable();
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);

				table.setPreferredScrollableViewportSize(new Dimension(400, 200));
				table.setEnabled(false);
				table.setFillsViewportHeight(true);
				JScrollPane scrollPane = new JScrollPane(table);
				currentOrderPanel.setLayout(new BorderLayout());
				currentOrderPanel.add(scrollPane, BorderLayout.NORTH);

				boxes.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						spinner.setValue(1);
						itemName = (String) e.getItem();

						if (!itemName.equals("")) {
							spinner.setEnabled(true);
							addButton.setEnabled(true);
						} else {
							spinner.setEnabled(false);
							addButton.setEnabled(false);
						}

						unitPrice = 0;
						for(Product p : supermarket.products.values()){
						    if(itemName.equalsIgnoreCase(p.name)) {
                                unitPrice += p.price;
                                supermarket.addToBank(p.price - p.cost);
                            }
                        }
						
						priceLabel.setText(String.format("%.2f", unitPrice) + " TL");

					}
				});

				spinner.addChangeListener(new ChangeListener() {

					public void stateChanged(ChangeEvent e) {

						count = (int) spinner.getValue();
						price = unitPrice * count;
						priceLabel.setText(String.format("%.2f", price) + " TL");
					}
				});

				addButton.addActionListener(new ActionListener() {

					Object[] row = new Object[3];

					public void actionPerformed(ActionEvent e) {

					    for(Product p : supermarket.products.values()){
					        if(itemName.equalsIgnoreCase(p.name)){
					            if (p.amount != 0){
					                if(p.amount - count < 0){
					                    JOptionPane.showMessageDialog(null,"You can sell only "+p.amount+" of this item.");
                                    }else {
                                        p.amount -= count;
                                        row[0] = itemName;
                                        row[1] = new Integer(count);
                                        row[2] = new Double(count * p.price);
                                        for (int i = 0; i < count; i++) {
                                            totalPrice += p.price;
                                        }
                                        model.addRow(row);
                                        finalizeButton.setEnabled(true);
                                    }
                                }else {
					                JOptionPane.showMessageDialog(null, "We are out of this item.");
                                }
                            }
                        }
					}

				});

				finalizeButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						JOptionPane.showMessageDialog(null, "The sale is completed.\nTotal price is "
								+ String.format("%.2f", totalPrice) + " TL");

						removeAll();
						setLayout(new FlowLayout());
						add(buttonsPanel);
						buttonsPanel.setVisible(true);
                        repaint();
                        totalPrice = 0;
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

                Object[] row = new Object[7];

                for(Product p : supermarket.sortedProducts.values()) {
                    row[0] = p.name;
                    row[1] = new Integer(p.ID);
                    row[2] = new Double(p.cost);
                    row[3] = new Double(p.price);
                    row[4] = p.type;
                    row[5] = new Integer(p.amount);
                    row[6] = new Integer(p.originalAmount);
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

                        Object[] row = new Object[7];

                        for(Product p : supermarket.sortedProducts.values()) {
                            if(Objects.equals(selectedValue, possibleValues[0])){
                                if(nameText.getText().equalsIgnoreCase(p.name)){
                                    row[0] = p.name;
                                    row[1] = new Integer(p.ID);
                                    row[2] = new Double(p.cost);
                                    row[3] = new Double(p.price);
                                    row[4] = new String(p.type);
                                    row[5] = new Integer(p.amount);
                                    row[6] = new Integer(p.originalAmount);
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
                                    row[6] = new Integer(p.originalAmount);
                                    model.addRow(row);
                                }
                            }else {
                                if(nameText.getText().equalsIgnoreCase(p.type)){
                                    row[0] = p.name;
                                    row[1] = new Integer(p.ID);
                                    row[2] = new Double(p.cost);
                                    row[3] = new Double(p.price);
                                    row[4] = new String(p.type);
                                    row[5] = new Integer(p.amount);
                                    row[6] = new Integer(p.originalAmount);
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
	}
}