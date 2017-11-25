package POSPD;
import java.math.*;
import java.util.*;

public class SaleLineItem 
{

	private Item item;
	private BigDecimal quantity;
	private String quantityString;
	GregorianCalendar currentDate;
	private Sale sale;

	public SaleLineItem(Item item, String quantity)
	{
		quantityString = quantity;
		setQuantity(quantity);
		this.item = item;
		currentDate = new GregorianCalendar();
		calcTax();
		calcSubTotal();	
	}
	
	// Used on the Sale screen, quantity might be an integer
	public SaleLineItem(Sale sale, Item item, String quantity)
	{
		setQuantity(quantity);
		this.item = item;
		this.sale = sale;
		currentDate = new GregorianCalendar();
		calcTax();
		calcSubTotal();
	}
	
	public SaleLineItem()
	{
		
	}
	
	public BigDecimal getQuantity() 
	{
		return this.quantity;
	}

	public void setQuantity(String quantity) 
	{
		this.quantity = new BigDecimal(quantity);
	}

	public BigDecimal calcSubTotal() 
	{
		BigDecimal subTotal;
		subTotal = item.findPriceByDate(currentDate).getPriceAmount()
				.multiply(quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
		return subTotal;
	}

	public BigDecimal calcTax() 
	{
		BigDecimal tax;
		BigDecimal taxRate = item.getTaxRate();
		
		// tax is calculated by price * taxRate
		tax = calcSubTotal().multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return tax;
	}
	
	public String toString()
	{
		return item.getDescription() + "  Qt: " + quantityString + " Price $: " +
				item.findPriceByDate(new GregorianCalendar()).toString();
	}
	
	public String toString2()
	{
		return item.toString2() + " Quantity: " + quantityString + "\n";
	}
}
