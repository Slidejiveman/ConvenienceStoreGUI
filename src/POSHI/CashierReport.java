package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import POSPD.*;

public class CashierReport extends JPanel 
{
	private TextArea textArea;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CashierReport(JFrame currentFrame, Store store) 
	{
		setLayout(null);
		
		JLabel lblCashierReport = new JLabel("Cashier Report");
		lblCashierReport.setBounds(183, 13, 97, 16);
		add(lblCashierReport);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(93, 55, 56, 16);
		add(lblDate);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String[] rd = textField.getText().split("/");
				String text = generateCashierReport(store, new GregorianCalendar(Integer.parseInt(rd[2]), 
						Integer.parseInt(rd[0])-1, Integer.parseInt(rd[1]))); 
				textArea.setText(text);
			}
		});
		textField.setBounds(205, 52, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textArea = new TextArea();
		textArea.setBounds(10, 105, 430, 127);
		add(textArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(172, 262, 97, 25);
		add(btnClose);

	}
	public String generateCashierReport(Store store, GregorianCalendar date)
	{
		String report = "";
		String nl = "\n";
		String tab = "\t";
		report += "Cashier Report for: " + nl;
		report += nl;
		
		for(Entry<String, Cashier> cashierElement: store.getCashiers().entrySet())
		{
			Cashier cashier = cashierElement.getValue();
			report += cashier.getNumber() + " " + cashier.getPerson().getName() + 
					tab + tab + "# Sales: " + cashier.calcNumberSales(date) + tab + " $ Sales: " + 
					cashier.calcDollarSales(date).toPlainString() + nl;
		}
		
		return report;
	}
}
