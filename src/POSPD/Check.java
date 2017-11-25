package POSPD;
import java.math.*;
import java.util.GregorianCalendar;

public class Check extends AuthorizedPayment 
{
	private String routingNumber;
	private String accountNumber;
	private String checkNumber;
	private BigDecimal amountTendered;

	public Check(String amountTendered, String routingNumber, String accountNumber, String checkNumber)
	{
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.checkNumber = checkNumber;
		this.amountTendered = new BigDecimal(amountTendered);		
	}
	
	public Check()
	{
		
	}
	
	public String getRoutingNumber() 
	{
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber) 
	{
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber() 
	{
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber() 
	{
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) 
	{
		this.checkNumber = checkNumber;
	}

	public BigDecimal getAmountTendered() 
	{
		return this.amountTendered;
	}

	public void setAmountTendered(BigDecimal amountTendered) 
	{
		this.amountTendered = amountTendered;
	}
	
	public String toString()
	{
		return "Amount Tendered: $" + amountTendered.toPlainString() + " \n";
	}
}

