package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public StoreEditPanel(JFrame currentFrame, Store store) 
	{
		setLayout(null);
		
		JLabel lblEditStore = new JLabel("Edit Store");
		lblEditStore.setBounds(182, 32, 56, 16);
		add(lblEditStore);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(37, 79, 56, 16);
		add(lblName);
		
		textField = new JTextField(store.getName());
		textField.setBounds(133, 76, 170, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNumber = new JLabel("Number: ");
		lblNumber.setBounds(37, 122, 56, 16);
		add(lblNumber);
		
		textField_1 = new JTextField(store.getNumber());
		textField_1.setBounds(133, 119, 170, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				store.setName(textField.getText());
				store.setNumber(textField_1.getText());
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(96, 241, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(249, 241, 97, 25);
		add(btnCancel);
		
		

	}
}
