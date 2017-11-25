package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.*;

public class CashierEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the panel.
	 */
	public CashierEditPanel(JFrame currentFrame, Store store, Cashier cashier, Boolean isAdd) 
	{
		setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				cashier.getPerson().setName(textField.getText());
				cashier.setNumber(textField_1.getText());
				
				if(isAdd) store.addCashier(cashier);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton.setBounds(121, 228, 81, 25);
		add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(247, 228, 88, 25);
		add(btnCancel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(131, 80, 38, 16);
		add(lblName);
		
		JLabel lblRate = new JLabel("Number: ");
		lblRate.setBounds(131, 109, 54, 16);
		add(lblRate);
		
		textField = new JTextField(cashier.getPerson().getName()); // Have to fix this in order to be able to ADD
		textField.setBounds(202, 77, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(cashier.getNumber());
		textField_1.setBounds(202, 106, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
	}

}
