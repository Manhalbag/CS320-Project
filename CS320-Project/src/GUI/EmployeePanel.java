package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;



public class EmployeePanel extends JPanel {
	
	private JButton saleButton;
	

	private JButton finalizeButton;
	private JLabel priceLabel;
	private JButton addButton;
	private JComboBox boxes;
	private JSpinner spinner;
	private JTable table;
	private double unitPrice;
	private double price;
	private String itemName;
	private int count = 1;
	private JPanel panel = new JPanel();

	public EmployeePanel() {

		saleButton = new JButton("New Sale");

		add(saleButton);

		saleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				remove(saleButton);

				setLayout(new BorderLayout());

				JPanel bottomPanel = new JPanel();
				bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				finalizeButton = new JButton("Finalize");
				finalizeButton.setEnabled(false);
				bottomPanel.add(finalizeButton);
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
			
				addProductPanel_Right.add(boxes);

				spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
				spinner.setEditor(new JSpinner.DefaultEditor(spinner));
				spinner.setEnabled(false);
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

						


						model.addRow(row);

					}

				});

				finalizeButton.addActionListener(new ActionListener() {

					double totalPrice = 0;

					public void actionPerformed(ActionEvent e) {

						JOptionPane.showMessageDialog(null, "The sale is completed.\nTotal price is "
								+ String.format("%.2f", totalPrice) + " TL");

						removeAll();

						setLayout(new FlowLayout());
						add(saleButton);

						repaint();

					}

				});

			}
		});

	}
}
