package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.Item;
import POSPD.PromoPrice;
import POSPD.Store;

public class PromoPriceEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	/**
	 * Create the panel.
	 */
	public PromoPriceEditPanel(JFrame currentFrame, Store store, Item item, PromoPrice price, Boolean isAdd) 
	{
		setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				price.setPrice(textField.getText());
				price.setEffectiveDate(textField_1.getText());
				price.setEndDate(textField_3.getText());				
												
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
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(107, 185, 56, 16);
		add(lblEndDate);
		
		String strdate2 = "";
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		if (price.getEndDate() != null) 
			strdate2 = sdf.format(price.getEndDate().getTime());
		textField_3 = new JTextField(strdate2);
		textField_3.setBounds(214, 186, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);
	
	}

}
