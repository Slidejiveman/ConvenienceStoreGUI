package POSHI;

import javax.swing.JPanel;
import javax.swing.JFrame;
import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class POSPaymentCash extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public POSPaymentCash(JFrame currentFrame, Store store, Session session, Sale sale, Cash cash) 
	{
		setLayout(null);
		
		JLabel lblPaymentAmount = new JLabel("Payment Amount: ");
		lblPaymentAmount.setBounds(76, 110, 106, 16);
		add(lblPaymentAmount);
		
		textField = new JTextField("0");
		textField.setBounds(213, 107, 116, 22);
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
				cash.setAmountTendered(new BigDecimal(textField.getText()));
				sale.addPayment(cash);
				
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
		
	}

}
