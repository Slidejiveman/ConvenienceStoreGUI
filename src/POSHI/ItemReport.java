package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import POSPD.*;
import java.awt.TextArea;

public class ItemReport extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ItemReport(JFrame currentFrame, Store store) 
	{
		setLayout(null);
		
		JLabel lblItemReport = new JLabel("Item Report");
		lblItemReport.setBounds(185, 27, 74, 16);
		add(lblItemReport);
		
		// Date is the selection criteria of this report
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setBounds(60, 73, 56, 16);
		add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(167, 70, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(185, 240, 97, 25);
		add(btnClose);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 98, 430, 131);
		add(textArea);
		
	}
}
