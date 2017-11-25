package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.*;

public class PriceEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the panel.
	 */
	public PriceEditPanel(JFrame currentFrame, Store store, Item item, Price price, Boolean isAdd) 
	{
		setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				price.setPrice(textField.getText());
				price.setEffectiveDate(textField_1.getText());
												
				if(isAdd) item.addPrice(price);
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
		
		textField = new JTextField(price.getPriceAmount().toPlainString()); 
		textField.setBounds(214, 109, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		String strdate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if (price.getEffectiveDate() != null) 
			strdate = sdf.format(price.getEffectiveDate().getTime());
		textField_1 = new JTextField(strdate);
		textField_1.setBounds(214, 151, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(107, 112, 56, 16);
		add(lblAmount);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setBounds(107, 154, 95, 16);
		add(lblEffectiveDate);
	}
}
