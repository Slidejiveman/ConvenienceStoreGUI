package POSHI;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TaxCategoryEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	
	/**
	 * Create the panel.
	 */
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd) {
		setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				taxCategory.setCategory(textField.getText());
				taxCategory.setTaxRate(textField_1.getText());
				taxCategory.setEffectiveDate(textField_3.getText()); //This gets the info out of the text field and into the object
				if(isAdd) store.addTaxCategory(taxCategory);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(89, 245, 97, 25);
		add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(263, 245, 97, 25);
		add(btnCancel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(85, 70, 56, 16);
		add(lblName);
		
		JLabel lblRate = new JLabel("Rate: ");
		lblRate.setBounds(85, 114, 56, 16);
		add(lblRate);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setBounds(85, 154, 101, 16);
		add(lblEffectiveDate);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setBounds(214, 67, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(taxCategory.getTaxRate().toPlainString());
		textField_1.setBounds(214, 111, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		String strdate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if (taxCategory.getEffectiveDate() != null) //I think the display problem is right here. It never performs this logic
			strdate = sdf.format(taxCategory.getEffectiveDate().getTime());
		textField_3 = new JTextField(strdate);
		textField_3.setBounds(214, 151, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);

	}
}
