package POSHI;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import POSPD.*;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TaxCategoryListPanel extends JPanel {
	private JList list;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnAdd;
	DefaultListModel listModel;
	/**
	 * Create the panel.
	 */
	public TaxCategoryListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				TaxCategoryEditPanel tcEditPanel =
						new TaxCategoryEditPanel(currentFrame, store, new TaxCategory(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(tcEditPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(12, 245, 97, 25);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				TaxCategoryEditPanel tcEditPanel =
						new TaxCategoryEditPanel(currentFrame, store, 
								(TaxCategory)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(tcEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnEdit.setBounds(121, 245, 97, 25);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(232, 245, 97, 25);
		add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				store.removeTaxCategory((TaxCategory)list.getSelectedValue());
				listModel.removeElement((TaxCategory)list.getSelectedValue());
			}
		});
		btnDelete.setBounds(341, 245, 97, 25);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		listModel = new DefaultListModel();
		for(Entry<String, TaxCategory> tcEntry : store.getTaxCategories().entrySet())
			listModel.addElement(tcEntry.getValue());
		
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{
				//deal with the buttons being enabled and disabled
				//write an isUsed() function that is based on whether or not it holds any other lists and such
				//use the .size() function. Need to write isUsed methods for each of the objects.
				if (list.isSelectionEmpty())
				{
					 btnDelete.setEnabled(false);
					 btnEdit.setEnabled(false);
				}
				else
				{
					TaxCategory taxCategory = (TaxCategory)list.getSelectedValue();
				 	if (!taxCategory.isUsed())
				 		btnDelete.setEnabled(true);
				 	else 
				 		btnDelete.setEnabled(false);
				 		
				 	btnEdit.setEnabled(true);
				}
			}
		});
		
		list.setBounds(137, 13, 176, 205);
		add(list);

	}

}
