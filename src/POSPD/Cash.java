package POSPD;
import java.math.*;

public class Cash extends Payment 
{
	private BigDecimal amountTendered;

	public Cash(String amountTendered)
	{
		this.amountTendered = new BigDecimal(amountTendered);
	}
	
	public Cash()
	{
		
	}
	
	// Why should I need to overwrite these functions?
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
