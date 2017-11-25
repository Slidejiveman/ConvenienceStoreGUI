package POSHI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POSPD.Store;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class POSFrame extends JFrame 
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void altMain(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					POSFrame frame = new POSFrame( new Store());
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public static void run(Store store)
	{
		try
		{
			POSFrame frame = new POSFrame(store);
			frame.setVisible(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public POSFrame(Store store) 
	{
		JFrame currentFrame = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMaintain = new JMenu("Maintain");
		menuBar.add(mnMaintain);
		
		JMenuItem mntmStore = new JMenuItem("Store");
		mntmStore.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				getContentPane().add(new StoreEditPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmStore);
		
		JMenuItem mntmItem = new JMenuItem("Item");
		mntmItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				getContentPane().add(new ItemListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmItem);
		
		JMenuItem mntmRegister = new JMenuItem("Register");
		mntmRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				getContentPane().add(new RegisterListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmRegister);
		
		JMenuItem mntmCashier = new JMenuItem("Cashier");
		mntmCashier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				getContentPane().add(new CashierListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmCashier);
		
		JMenuItem mntmTaxCategory = new JMenuItem("Tax Category");
		mntmTaxCategory.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		
		mnMaintain.add(mntmTaxCategory);
		
		JMenu mnPos = new JMenu("POS");
		menuBar.add(mnPos);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				getContentPane().add(new POSLogin(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnPos.add(mntmLogin);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmCashierReport = new JMenuItem("Cashier Report");
		mntmCashierReport.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				getContentPane().add(new CashierReport(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmCashierReport);
		
		JMenuItem mntmItemReport = new JMenuItem("Item Report");
		mntmItemReport.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				getContentPane().add(new ItemReport(currentFrame, store));
				getContentPane().revalidate();
			
			}
		});
		mnReports.add(mntmItemReport);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		
		// Display home screen on start up
		currentFrame.getContentPane().removeAll();
		currentFrame.getContentPane().add(new POSHome(store));
		currentFrame.getContentPane().revalidate();
	}
}
