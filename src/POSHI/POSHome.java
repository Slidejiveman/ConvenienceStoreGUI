package POSHI;

import javax.swing.JPanel;

import POSPD.Store;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class POSHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public POSHome(Store store) 
	{
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(store.getName());
		lblNewLabel.setBounds(134, 148, 163, 16);
		add(lblNewLabel);

	}

}
