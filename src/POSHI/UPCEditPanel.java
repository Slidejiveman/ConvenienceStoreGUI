package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.*;

public class UPCEditPanel extends JPanel {
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public UPCEditPanel(JFrame currentFrame, Store store, Item item, UPC uPC, Boolean isAdd) 
	{
		setLayout(null);
		
		JButton btnNewButton = new JButton("Save");		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				uPC.setUPC(textField.getText());
								
				if(isAdd) 
				{
					item.addUPC(uPC);
					store.addUPC(uPC);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new EditItem(currentFrame, store, item, isAdd));
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
				currentFrame.getContentPane().add(new EditItem(currentFrame, store, item, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(247, 228, 88, 25);
		add(btnCancel);
		
		JLabel lblName = new JLabel("UPC Code:");
		lblName.setBounds(131, 80, 38, 16);
		add(lblName);
		
		textField = new JTextField(uPC.getUPCString()); 
		textField.setBounds(202, 77, 116, 22);
		add(textField);
		textField.setColumns(10);
	}

}
