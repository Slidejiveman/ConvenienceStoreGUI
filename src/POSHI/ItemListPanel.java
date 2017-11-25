package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSPD.*;

public class ItemListPanel extends JPanel {
	private JList list;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnAdd;
	DefaultListModel listModel;
	/**
	 * Create the panel.
	 */
	public ItemListPanel(JFrame currentFrame, Store store) 
	{
		setLayout(null);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EditItem itemEditPanel =
						new EditItem(currentFrame, store, new Item(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemEditPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(12, 245, 97, 25);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EditItem itemEditPanel =
						new EditItem(currentFrame, store, 
								(Item)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemEditPanel);
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
		
		JButton btnDelete = new JButton("Delete");	//Delete is more complicated for item, so this will have to be fixed
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				store.removeItem((Item)list.getSelectedValue());
				listModel.removeElement((Item)list.getSelectedValue());
			}
		});
		btnDelete.setBounds(341, 245, 97, 25);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		listModel = new DefaultListModel();
		for(Entry<String, Item> iEntry : store.getItems().entrySet())
			listModel.addElement(iEntry.getValue());
		
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
					Item item = (Item)list.getSelectedValue();
				 	if (!item.isUsed() )
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
