package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.*;

public class RegisterEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Create the panel.
	 */
	public RegisterEditPanel(JFrame currentFrame, Store store, Register register, Boolean isAdd) 
	{
		setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				register.setNumber(textField.getText());
								
				if(isAdd) store.addRegister(register);	//Make sure there are add functions in store
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame, store));
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
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(247, 228, 88, 25);
		add(btnCancel);
		
		JLabel lblRate = new JLabel("Number: ");
		lblRate.setBounds(131, 109, 54, 16);
		add(lblRate);
		
		textField = new JTextField(register.getRegisterNumber());
		textField.setBounds(202, 106, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount $:");
		lblAmount.setBounds(121, 138, 64, 16);
		add(lblAmount);
		
		textField_1 = new JTextField(register.getCashDrawer().getCashAmount().toPlainString());
		textField_1.setBounds(202, 135, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
	}

}
