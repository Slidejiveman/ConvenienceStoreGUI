package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;

public class SessionEndPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnLogout;
	String outString = "unused";

	/**
	 * Create the panel.
	 */
	public SessionEndPanel(JFrame currentFrame, Store store, Session session, Sale sale) 
	{
		setLayout(null);
		
		JLabel lblClosingSession = new JLabel("Closing Session");
		lblClosingSession.setBounds(183, 13, 97, 16);
		add(lblClosingSession);
		
		JLabel lblEnterCashDrawer = new JLabel("Enter Cash Count:");
		lblEnterCashDrawer.setBounds(45, 91, 169, 16);
		add(lblEnterCashDrawer);
		
		//if(outString.equals("unused"))
			//btnLogout.setEnabled(true);
		//else
			
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				session.setEndDateTime(new GregorianCalendar());
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSLogin(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnLogout.setBounds(99, 262, 97, 25);
		btnLogout.setEnabled(false);
		add(btnLogout);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (!textField.getText().equals(textField_1.getText()))
				{
					outString = "Cash Drawer Amount Unbalanced.";
				
				}
				else 
				{
					outString = "Cash Drawer Amount Balanced!";
				}
						
				btnLogout.setEnabled(true);
				
				JFormattedTextField formattedTextField = new JFormattedTextField(outString);
				formattedTextField.setBounds(117, 209, 226, 22);
				add(formattedTextField);
				
			}
		});
		textField.setBounds(247, 88, 116, 22);
		add(textField);
		textField.setColumns(10);
						
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSSaleEntry(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(249, 262, 97, 25);
		add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Estimated Drawer Total: ");
		lblNewLabel.setBounds(45, 141, 151, 16);
		add(lblNewLabel);
		
		textField_1 = new JTextField(session.getRegister().getCashDrawer().getCashAmount().toPlainString());
		textField_1.setBounds(247, 138, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
	}
}
