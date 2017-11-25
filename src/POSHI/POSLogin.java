package POSHI;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import POSPD.*;

public class POSLogin extends JPanel 
{
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private Register register;

	/**
	 * Create the panel.
	 */
	public POSLogin(JFrame currentFrame, Store store) 
	{
		setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Cashier cashier = (Cashier)comboBox.getSelectedItem();
				
				// Compare two character arrays
				if (Arrays.equals(cashier.getPassword().toCharArray(),passwordField.getPassword()))
				{
					// Put money in cash drawer
					register = (Register)comboBox_1.getSelectedItem();
					register.getCashDrawer().setCashAmount(textField_2.getText());
					
					POSSaleEntry saleScreen =
							new POSSaleEntry(
									currentFrame, store, new Session(cashier, register), new Sale());
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(saleScreen);
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					
					// Can't display this label
					System.out.println("Password incorrect");
					
					//JLabel lblNewLabel = new JLabel("Password is Wrong");
					//lblNewLabel.setForeground(Color.RED);
					//lblNewLabel.setBounds(188, 31, 56, 16);
					//add(lblNewLabel);

					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSLogin(currentFrame, store));
					currentFrame.getContentPane().revalidate();
				}
			}
		});
		btnLogin.setBounds(77, 229, 97, 25);
		add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSHome(store));
					currentFrame.getContentPane().revalidate();
				
			}
		});
		btnCancel.setBounds(276, 229, 97, 25);
		add(btnCancel);
		
		JLabel lblCashierNumber = new JLabel("Cashier:");
		lblCashierNumber.setBounds(94, 89, 80, 16);
		add(lblCashierNumber);
		
		DefaultComboBoxModel comboModel = new 
				DefaultComboBoxModel(store.getCashiersAsArray().toArray());
		comboBox = new JComboBox(comboModel);
		JComboBox comboBox = new JComboBox(comboModel);
		comboBox.setBounds(217, 86, 116, 22);
		add(comboBox);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(94, 184, 80, 16);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(217, 180, 116, 25);
		add(passwordField);
		
		JLabel lblCashInDrawer = new JLabel("Cash in Drawer:");
		lblCashInDrawer.setBounds(94, 148, 97, 16);
		add(lblCashInDrawer);
		
		textField_2 = new JTextField();
		textField_2.setBounds(217, 145, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblRegister = new JLabel("Register:");
		lblRegister.setBounds(93, 119, 56, 16);
		add(lblRegister);
		
		DefaultComboBoxModel comboModel2 = new 
				DefaultComboBoxModel(store.getRegistersAsArray().toArray());
		comboBox_1 = new JComboBox(comboModel2);
		comboBox_1.setBounds(217, 116, 116, 22);
		add(comboBox_1);	
			
	}
}
