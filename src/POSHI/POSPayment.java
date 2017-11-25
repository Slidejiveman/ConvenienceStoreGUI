package POSHI;

import javax.swing.JPanel;
import POSPD.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSPayment extends JPanel {

	/**
	 * Create the panel.
	 */
	public POSPayment(JFrame currentFrame, Store store, Session session, Sale sale) 
	{
		setLayout(null);
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSPaymentCash(currentFrame, store, session, sale, new Cash()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCash.setBounds(98, 97, 97, 25);
		add(btnCash);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSPaymentCheck(currentFrame, store, session, sale, new Check()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCheck.setBounds(249, 97, 97, 25);
		add(btnCheck);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSPaymentCredit(currentFrame, store, session, sale, new Credit()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCredit.setBounds(98, 168, 97, 25);
		add(btnCredit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSSaleEntry(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(249, 168, 97, 25);
		add(btnCancel);
		
		JLabel lblSelectPaymentType = new JLabel("Select Payment Type");
		lblSelectPaymentType.setBounds(166, 47, 127, 16);
		add(lblSelectPaymentType);
		
	}

}
