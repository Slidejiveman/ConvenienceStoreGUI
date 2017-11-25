package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import POSPD.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSSessionSummaryPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public POSSessionSummaryPanel(JFrame currentFrame, Store store, Session session, Sale sale) 
	{
		setLayout(null);
		
		JLabel lblSaleSuccessfullyCompleted = new JLabel("Sale Successfully Completed");
		lblSaleSuccessfullyCompleted.setBounds(141, 47, 178, 16);
		add(lblSaleSuccessfullyCompleted);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(25, 159, 51, 16);
		add(lblTotal);
		
		JLabel lblPayment = new JLabel("Payment: ");
		lblPayment.setBounds(21, 188, 71, 16);
		add(lblPayment);
		
		JLabel lblChange = new JLabel("Change: ");
		lblChange.setBounds(21, 217, 56, 16);
		add(lblChange);
		
		textField = new JTextField(sale.getTotal().toPlainString());
		textField.setBounds(128, 156, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(sale.getTotalPayments().toPlainString());
		textField_1.setBounds(128, 185, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(sale.getChange().toPlainString());
		textField_2.setBounds(128, 217, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSSaleEntry(currentFrame, store, new Session(session.getCashier(), session.getRegister()), new Sale()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnReturn.setBounds(161, 262, 97, 25);
		add(btnReturn);
		
	}
}
