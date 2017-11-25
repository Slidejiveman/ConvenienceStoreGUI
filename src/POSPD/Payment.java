package POSPD;
import java.math.*;

public class Payment 
{

	private BigDecimal amountTendered;
	
	public Payment()
	{
		this.amountTendered = amountTendered;
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
		return "Amount Tendered: $" + amountTendered.toPlainString();
	}
}
