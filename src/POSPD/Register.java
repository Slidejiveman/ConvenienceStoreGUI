package POSPD;

import java.math.BigDecimal;

public class Register 
{
	private CashDrawer cashDrawer;
	private String number;

	public Register(String number, CashDrawer cashDrawer)
	{
		setNumber(number);
		setCashDrawer(cashDrawer);
	}
	
	public Register()
	{
		setNumber("");
		cashDrawer = new CashDrawer();
	}
	
	public String getRegisterNumber()
	{
		return this.number;
	}

	public CashDrawer getCashDrawer()
	{
		return this.cashDrawer;
	}
	
	public void setCashDrawer(CashDrawer cashDrawer)
	{
		this.cashDrawer = cashDrawer;
	}
	public void setNumber(String number) 
	{
		this.number = number;
	}
	
	public String toString()
	{
		return getRegisterNumber();
	}
	
	public String toString2()
	{
		String registerString = " Register Number: " + getRegisterNumber();
		
		return registerString;
	}
	
	public Boolean isUsed()
	{
		boolean isUsed = false;
		
		if(cashDrawer.getCashAmount().compareTo(new BigDecimal(0)) != 0)
		{
			isUsed = true;					
		}
		
		return isUsed;
	}
}
