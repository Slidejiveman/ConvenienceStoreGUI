package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import POSPD.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

public class POSSaleEntry extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JList list;
	private JCheckBox chckbxTaxFree;
	private String quantity;
	private DefaultListModel listModel;
	private JButton btnGetPayment;
	private JButton btnComplete;
	private JButton btnCancel;
	private JButton btnEndSession;

	/**
	 * Create the panel.
	 */
	public POSSaleEntry(JFrame currentFrame, Store store, Session session, 
			Sale sale) 
	{
		setLayout(null);
		
		btnGetPayment = new JButton("Get Payment");
		btnGetPayment.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSPayment(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnGetPayment.setBounds(12, 224, 112, 25);
		add(btnGetPayment);
		
		btnComplete = new JButton("Comp. Sale");
		btnComplete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				session.addSale(sale);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSSessionSummaryPanel(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnComplete.setBounds(12, 262, 112, 25);
		add(btnComplete);
		
		btnCancel = new JButton("Cancel Sale");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSSaleEntry(currentFrame, store, session, new Sale()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(144, 224, 112, 25);
		add(btnCancel);
		
		btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new SessionEndPanel(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(144, 262, 112, 25);
		add(btnEndSession);
		
		
		chckbxTaxFree = new JCheckBox("Tax Free");
		
		if(sale.getTaxStatus())
		{
			chckbxTaxFree.doClick();
		}
		chckbxTaxFree.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(chckbxTaxFree.isSelected())
				{
					sale.setTaxStatus(true);
				}
				else
					sale.setTaxStatus(false);
				
				sale.calcSubTotal();
				sale.calcTax();
				sale.calcTotal();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSSaleEntry(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		chckbxTaxFree.setBounds(174, 10, 79, 25);
		add(chckbxTaxFree);
		
		JLabel lblAmtTend = new JLabel("Amt. Tend:");
		lblAmtTend.setBounds(268, 101, 67, 16);
		add(lblAmtTend);
		
		textField_5 = new JTextField(sale.getTotalPayments().toPlainString());
		textField_5.setBounds(334, 98, 104, 22);
		add(textField_5);
		textField_5.setColumns(10);
		
		// Disable check box if there is a payment
		if(!textField_5.getText().equals("0"))
		{
			chckbxTaxFree.setEnabled(false);
		}
		
		JLabel lblUpc = new JLabel("UPC:");
		lblUpc.setBounds(12, 11, 56, 16);
		add(lblUpc);
		
		textField_3 = new JTextField();
		textField_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Item item = store.findItemByUPC(textField_3.getText());
				quantity = textField_4.getText();
				
				sale.addSaleLineItem(new SaleLineItem(item, quantity));
				sale.calcTax();
				sale.calcSubTotal();
				sale.calcTotal();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(
						new POSSaleEntry(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
				
			}
		});
		textField_3.setBounds(50, 8, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSubTotal = new JLabel("Sub Total:");
		lblSubTotal.setBounds(269, 14, 67, 16);
		add(lblSubTotal);
		
		textField = new JTextField(sale.calcSubTotal().toPlainString());	
		textField.setBounds(334, 11, 104, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setBounds(269, 43, 56, 16);
		add(lblTax);
		
		textField_1 = new JTextField(sale.getTotalTax().toPlainString());
		textField_1.setBounds(334, 43, 104, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Total:");
		lblNewLabel.setBounds(269, 72, 56, 16);
		add(lblNewLabel);
		
		textField_2 = new JTextField(sale.getTotal().toPlainString());
		textField_2.setBounds(334, 69, 104, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblQt = new JLabel("Qt:");
		lblQt.setBounds(12, 40, 56, 16);
		add(lblQt);
		
		textField_4 = new JTextField("1");
		textField_4.setBounds(50, 37, 116, 22);
		add(textField_4);
		textField_4.setColumns(10);
		
		
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(268, 130, 56, 16);
		add(lblChange);
		
		textField_6 = new JTextField(sale.calcChange().toPlainString());
		textField_6.setBounds(334, 130, 104, 22);
		add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblCashier = new JLabel("Cashier:");
		lblCashier.setBounds(268, 201, 56, 16);
		add(lblCashier);
		
		textField_7 = new JTextField(session.getCashier().toString());
		textField_7.setBounds(334, 198, 104, 22);
		add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblRegister = new JLabel("Register:");
		lblRegister.setBounds(268, 233, 56, 16);
		add(lblRegister);
		
		textField_8 = new JTextField(session.getRegister().getRegisterNumber());
		textField_8.setBounds(334, 227, 104, 22);
		add(textField_8);
		textField_8.setColumns(10);
		
		listModel = new DefaultListModel();
		Iterator<SaleLineItem> iterator = sale.getSaleLineItems().iterator();
		list = new JList(listModel);
		while(iterator.hasNext())
		{
			listModel.addElement(iterator.next());
		}
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				
			}
		});
		list.setBounds(12, 81, 244, 136);
		add(list);
		
		// Handle button access
		if(listModel.getSize() > 0)
		{
			btnCancel.setEnabled(true);
		}
		else
			btnCancel.setEnabled(false);
		
		if(listModel.getSize() == 0)
		{
			btnEndSession.setEnabled(true);
		}
		else
			btnEndSession.setEnabled(false);
		
		if(sale.isPaymentEnough() && listModel.getSize() > 0)
		{
			btnComplete.setEnabled(true);
		}
		else
			btnComplete.setEnabled(false);
		
	}
}
