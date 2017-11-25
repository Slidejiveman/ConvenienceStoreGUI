package POSHI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import POSPD.*;
import javax.swing.JComboBox;

public class EditItem extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	JButton btnEditPrice;
	JButton btnDelPrice;
	JButton btnEditUPC;
	JButton btnDelUPC;
	DefaultListModel listModel;
	DefaultListModel listModel_1;
	private JList list;
	private JList list_1;
	JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public EditItem(JFrame currentFrame, Store store, Item item, Boolean isAdd) 
	{
		setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				item.setDescription(textField.getText());
				item.setNumber(textField_1.getText());
				item.setTaxCategory((TaxCategory)comboBox.getSelectedItem());
				
				if(isAdd) store.addItem(item);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnNewButton.setBounds(135, 262, 81, 25);
		add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(232, 262, 88, 25);
		add(btnCancel);
		
		JLabel lblName = new JLabel("Description:");
		lblName.setBounds(12, 24, 71, 16);
		add(lblName);
		
		JLabel lblRate = new JLabel("Number: ");
		lblRate.setBounds(12, 53, 54, 16);
		add(lblRate);
		
		textField = new JTextField(item.getDescription()); // Have to fix this in order to be able to ADD
		textField.setBounds(86, 21, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(item.getNumber());
		textField_1.setBounds(88, 50, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		listModel = new DefaultListModel(); //TreeSet has different iteration code need to fix this
		for(Price price : item.getPrices())
			listModel.addElement(price);
		
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				//deal with the buttons being enabled and disabled
				//write an isUsed() function that is based on whether or not it holds any other lists and such
				//use the .size() function. Need to write isUsed methods for each of the objects.
				if (list.isSelectionEmpty())
				{
					 btnDelPrice.setEnabled(false);
					 btnEditPrice.setEnabled(false);
				}
				else
				{
					Price price = (Price)list.getSelectedValue();
				 	if (!price.isUsed() )
				 		btnDelPrice.setEnabled(true);
				 	else 
				 		btnDelPrice.setEnabled(false);
				 		
				 	btnEditPrice.setEnabled(true);
				}
			}
		});
		
		list.setBounds(288, 76, 110, 120);
		add(list);
		
		btnEditPrice = new JButton("Edit Price");
		btnEditPrice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Price price = (Price)list.getSelectedValue();
				
				if(price.getClass().toString().equals("class POSPD.Price"))
				{
				PriceEditPanel priceEditPanel =
						new PriceEditPanel(currentFrame, store, item, 
								price, false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(priceEditPanel);
				}
				else if(price.getClass().toString().equals("class POSPD.PromoPrice"))
				{
					PromoPriceEditPanel promoEditPanel =
							new PromoPriceEditPanel(currentFrame, store, item, 
									(PromoPrice)price, false);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(promoEditPanel);
				}
				currentFrame.revalidate();
			}
		});
		btnEditPrice.setBounds(240, 209, 97, 25);
		btnEditPrice.setEnabled(false);
		add(btnEditPrice);
		
		JButton btnAddPrice = new JButton("Add Price");
		btnAddPrice.addActionListener(new ActionListener() 	//ADD PRICE ISN'T WORKING
		{
			public void actionPerformed(ActionEvent e) 
			{
				PriceEditPanel priceEditPanel =
						new PriceEditPanel(currentFrame, store, item, 
								new Price(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(priceEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAddPrice.setBounds(341, 209, 97, 25);
		add(btnAddPrice);
		
		btnDelPrice = new JButton("Delete Price");
		btnDelPrice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				item.removePrice((Price)list.getSelectedValue());
				listModel.removeElement((Price)list.getSelectedValue());
			}
		});
		btnDelPrice.setBounds(232, 234, 105, 25);
		btnDelPrice.setEnabled(false);
		add(btnDelPrice);
		
		JButton btnAddPromo = new JButton("Add Promo");
		btnAddPromo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				PromoPriceEditPanel promoEditPanel =
						new PromoPriceEditPanel(currentFrame, store, item, 
								new PromoPrice(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(promoEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAddPromo.setBounds(351, 234, 97, 25);
		add(btnAddPromo);
		
		
		listModel_1 = new DefaultListModel();
		for(Entry<String, UPC> upcEntry : item.getUPCs().entrySet())
			listModel_1.addElement(upcEntry.getValue());
		
		list_1 = new JList(listModel_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				if (list_1.isSelectionEmpty())
				{
					 btnDelUPC.setEnabled(false);
					 btnEditUPC.setEnabled(false);
				}
				else
				{
					UPC uPC = (UPC)list_1.getSelectedValue();
				 	//if (!uPC.isUsed() )
				 		btnDelUPC.setEnabled(true);
				 	//else 
				 	//	btnDelUPC.setEnabled(false);
				 		
				 	btnEditUPC.setEnabled(true);
				}
			}
			
		});
		list_1.setBounds(57, 82, 116, 120);
		add(list_1);
		
		btnEditUPC = new JButton("Edit UPC");
		btnEditUPC.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				UPC uPC = (UPC)list_1.getSelectedValue();
				UPCEditPanel uPCEditPanel =
						new UPCEditPanel(currentFrame, store, item, 
								uPC, false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(uPCEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEditUPC.setBounds(12, 209, 97, 25);
		btnEditUPC.setEnabled(false);
		add(btnEditUPC);
		
		JButton btnAddUpc = new JButton("Add UPC");
		btnAddUpc.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				UPCEditPanel uPCEditPanel =
						new UPCEditPanel(currentFrame, store, item, 
								new UPC(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(uPCEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAddUpc.setBounds(119, 209, 97, 25);
		add(btnAddUpc);
		
		btnDelUPC = new JButton("Delete UPC");		//Delete UPC isn't working
		btnDelUPC.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String uPCString;
				UPC upc = (UPC)list_1.getSelectedValue();
				uPCString = upc.getUPCString();
				Item item = store.findItemByUPC(uPCString);
				store.removeItem(item);
				store.removeUPC(upc);
				item.removeUPC((UPC)list_1.getSelectedValue());
				listModel.removeElement((UPC)list_1.getSelectedValue());
				
				EditItem editItem = new EditItem(currentFrame, store, item, false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(editItem);
				currentFrame.revalidate();
			}
		});
		btnDelUPC.setBounds(59, 234, 97, 25);
		btnDelUPC.setEnabled(false);
		add(btnDelUPC);
		
		// Add tax categories into the combo box
		DefaultComboBoxModel comboModel = new 
				DefaultComboBoxModel(store.getTaxCategoriesAsArray().toArray());
		comboBox = new JComboBox(comboModel);
		if(!isAdd)
			comboBox.setSelectedItem(item.getTaxCategory());
		
		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		comboBox.setBounds(289, 21, 96, 22);
		add(comboBox);
		
		
	}
}
