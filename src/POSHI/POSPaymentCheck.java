package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.*;

public class POSPaymentCheck extends JPanel {
private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;
private JTextField textField_3;
	/**
	 * Create the panel.
	 */
	public POSPaymentCheck(JFrame currentFrame, Store store, Session session, Sale sale, Check check) 
	{
		setLayout(null);
		
		JLabel lblPaymentAmount = new JLabel("Payment Amount: ");
		lblPaymentAmount.setBounds(76, 84, 106, 16);
		add(lblPaymentAmount);
		
		textField = new JTextField("0");
		textField.setBounds(213, 81, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterPaymentAmount = new JLabel("Enter Payment Amount");
		lblEnterPaymentAmount.setBounds(129, 44, 137, 16);
		add(lblEnterPaymentAmount);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				check.setAmountTendered(new BigDecimal(textField.getText()));
				check.setRoutingNumber(textField_1.getText());
				check.setAccountNumber(textField_2.getText());
				check.setCheckNumber(textField_3.getText());
				sale.addPayment(check);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSSaleEntry(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(96, 245, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSPayment(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(232, 245, 97, 25);
		add(btnCancel);
		
		JLabel lblRouting = new JLabel("Routing #: ");
		lblRouting.setBounds(76, 128, 97, 16);
		add(lblRouting);
		
		textField_1 = new JTextField("0");
		textField_1.setBounds(213, 125, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAccount = new JLabel("Account #:");
		lblAccount.setBounds(76, 171, 79, 16);
		add(lblAccount);
		
		textField_2 = new JTextField("0");
		textField_2.setBounds(213, 168, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Check #:");
		lblNewLabel.setBounds(76, 216, 56, 16);
		add(lblNewLabel);
		
		textField_3 = new JTextField("0");
		textField_3.setBounds(213, 210, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);
	}

}
