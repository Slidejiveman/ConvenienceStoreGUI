package POSPD;
import java.math.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Price implements Comparator<Price>, Comparable<Price>
{

	private BigDecimal price;
	private GregorianCalendar effectiveDate;
	private Item item;

	public Price(Item item, String priceString, String dateString)
	{
		setPrice(priceString);
		setEffectiveDate(dateString);
		this.item = item;
	}
	
	public Price()
	{
		setPrice("0");
	}
	
	// Overriding the CompareTo method
	public int compareTo(Price price)
	{
		// handle when they are equal
		return getEffectiveDate().compareTo(price.getEffectiveDate());
	}
	
	// Overriding the compare method
	public int compare(Price price1, Price price2)
	{
		// Can break tie based on the type of class they are
		return (int) (price1.getEffectiveDate().getTimeInMillis() - 
				price2.getEffectiveDate().getTimeInMillis());
	}
	
	public BigDecimal getPriceAmount() 
	{
		return this.price;
	}

	public void setPrice(String price) 
	{
		// Convert String to BigDecimal
		this.price = new BigDecimal(price);
	}

	public GregorianCalendar getEffectiveDate() 
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) 
	{
		String dateString[] = effectiveDate.split("/");
		this.effectiveDate = new GregorianCalendar(Integer.parseInt(dateString[2]), 
										Integer.parseInt(dateString[0])-1, Integer.parseInt(dateString[1]));
		
	}
	
	public Boolean priceIsInEffect(GregorianCalendar currentDate)
	{
		// Initializes the calendar with the current dateTime
		// GregorianCalendar currentDate = new GregorianCalendar();
		boolean priceIsInEffect = false;
		
		if(effectiveDate.getTimeInMillis()<= currentDate.getTimeInMillis())
		{
			priceIsInEffect = true;
		}
		
		return priceIsInEffect;
	}
	
	public String toString()
	{
		String string = getPriceAmount().toPlainString();
		return string;
	}
	
	public Boolean isUsed()
	{
		boolean isUsed = false;
		
		// A price is used if it has ever been effective
		if(effectiveDate.getTimeInMillis() < new GregorianCalendar().getTimeInMillis())
			isUsed = true;
		
		return isUsed;
	}
}
