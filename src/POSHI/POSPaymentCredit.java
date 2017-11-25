package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.*;

public class POSPaymentCredit extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox comboBox;
	private String cardTypes;
	/**
	 * Create the panel.
	 */
	public POSPaymentCredit(JFrame currentFrame, Store store, Session session, Sale sale, Credit credit) 
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
				credit.setAmountTendered(new BigDecimal(textField.getText()));
				credit.setType(textField_1.getText());
				credit.setNumber(textField_2.getText());
				credit.setExpireDate(textField_3.getText());
				
				sale.addPayment(credit);
				
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
		
		//Might use a comboBox for the card types
		
		//DefaultComboBoxModel comboModel = new 
		//		DefaultComboBoxModel(credit.getCardTypes().toArray());
		//comboBox = new JComboBox(comboModel);
		//comboBox.addActionListener(new ActionListener() 
		//{
		//	public void actionPerformed(ActionEvent e) 
		//	{
				
		//	}
		//});
		//comboBox.setBounds(289, 21, 96, 22);
		//add(comboBox);
		
		JLabel lblRouting = new JLabel("Card Type: ");
		lblRouting.setBounds(76, 128, 82, 16);
		add(lblRouting);
		
		textField_1 = new JTextField();
		textField_1.setBounds(213, 125, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAccount = new JLabel("Card #:");
		lblAccount.setBounds(76, 171, 56, 16);
		add(lblAccount);
		
		textField_2 = new JTextField("0");
		textField_2.setBounds(213, 168, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Expiration Date:");
		lblNewLabel.setBounds(76, 216, 97, 16);
		add(lblNewLabel);
		
		String strdate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if (credit.getExpireDate() != null) //I think the display problem is right here. It never performs this logic
			strdate = sdf.format(credit.getExpireDate().getTime());
		textField_3 = new JTextField(strdate);
		textField_3.setBounds(213, 210, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);
	}

}
