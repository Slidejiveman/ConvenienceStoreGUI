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

public class RegisterListPanel extends JPanel {
	private JList list;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnAdd;
	DefaultListModel listModel;
	/**
	 * Create the panel.
	 */
	public RegisterListPanel(JFrame currentFrame, Store store) 
	{
		setLayout(null);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				RegisterEditPanel registerEditPanel =
						new RegisterEditPanel(currentFrame, store, new Register(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerEditPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(12, 245, 97, 25);
		add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				RegisterEditPanel registerEditPanel =
						new RegisterEditPanel(currentFrame, store, 
								(Register)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerEditPanel);
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
				store.removeRegister((Register)list.getSelectedValue());
				listModel.removeElement((Register)list.getSelectedValue());
			}
		});
		btnDelete.setBounds(341, 245, 97, 25);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		listModel = new DefaultListModel();
		for(Entry<String, Register> rEntry : store.getRegisters().entrySet())
			listModel.addElement(rEntry.getValue());
		
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{
				if (list.isSelectionEmpty())
				{
					 btnDelete.setEnabled(false);
					 btnEdit.setEnabled(false);
				}
				else
				{
					Register register = (Register)list.getSelectedValue();
				 	if (!register.isUsed() )
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
