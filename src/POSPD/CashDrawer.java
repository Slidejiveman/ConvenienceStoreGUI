package POSPD;
import java.math.*;

public class CashDrawer 
{
	private BigDecimal cashAmount;
	private int position;

	public CashDrawer(String cashAmount, String position)
	{
		setCashAmount(cashAmount);
		setPosition(position);
	}
	
	public CashDrawer()
	{
		setCashAmount("0");
		setPosition("1");
	}
	
	public BigDecimal getCashAmount() 
	{
		return this.cashAmount;
	}

	public void setCashAmount(String cashAmount) 
	{
		this.cashAmount = new BigDecimal(cashAmount);
	}

	public int getPosition() 
	{
		return this.position;
	}

	public void setPosition(String position) 
	{
		this.position = Integer.parseInt(position);
	}
}
