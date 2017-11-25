package POSPD;

import java.math.BigDecimal;
import java.util.*;

public class Session 
{

	private Register register;
	private ArrayList<Sale> sales;
	private Cashier cashier;
	private GregorianCalendar startDateTime;
	private GregorianCalendar endDateTime;
	
	public Session(Cashier cashier, Register register)
	{
		sales = new ArrayList<Sale>();
		setRegister(register);
		setCashier(cashier);
		setStartDateTime(new GregorianCalendar());
	}
	
	public Session()
	{
		sales = new ArrayList<Sale>();
		Cashier cashier = new Cashier();
		setStartDateTime(new GregorianCalendar());
	}

	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}
	
	public Cashier getCashier()
	{
		if(this.cashier != null)
			return this.cashier;
		else
			return null;
	}
	
	public void setRegister(Register register)
	{
		this.register = register;
	}
	
	public Register getRegister()
	{
		return this.register;
	}
	
	public GregorianCalendar getStartDateTime() 
	{
		return this.startDateTime;
	}

	public void setStartDateTime(GregorianCalendar startDate) 
	{
		this.startDateTime = startDate;
	}

	public GregorianCalendar getEndDateTime() 
	{
		return this.endDateTime;
	}

	public void setEndDateTime(GregorianCalendar endDate) 
	{
		this.endDateTime = endDate;
	}
	
	public void addSale(Sale sale)
	{
		sales.add(sale);
	}
	
	public String toString()
	{		
		String registerString = register.toString2();
		
		String saleString = "";
		ListIterator<Sale> listIterator = sales.listIterator();
		while(listIterator.hasNext())
		{
			Sale element = listIterator.next();
			saleString += element.toString();
		}
		
		return registerString + "\nSales: \n" + saleString;
	}

	public ArrayList<Sale> getSales() 
	{
		return sales;
	}
	
	public BigDecimal calcTotal()
	{
		BigDecimal total = new BigDecimal("0");
		
		for(Sale sale : getSales())
		{
			total = total.add(sale.getTotal()); // not calculating properly, i think
		}
		
		return total;
	}
}
