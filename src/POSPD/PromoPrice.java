 package POSPD;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PromoPrice extends Price 
{
	private GregorianCalendar endDate;

	public PromoPrice(Item item, String priceString, String effectiveDate, String endDate)
	{
		// Call the super class in order to inherit from it
		super(item, priceString, effectiveDate);
		setPrice(priceString);
		setEffectiveDate(effectiveDate);
		setEndDate(endDate);
	}
	
	public PromoPrice()
	{
		
	}
	
	public GregorianCalendar getEndDate() 
	{
		return this.endDate;
	}

	public void setEndDate(String endDate) 
	{
		String dateString[] = endDate.split("/");
		this.endDate = new GregorianCalendar(Integer.parseInt(dateString[2]), 
										Integer.parseInt(dateString[0])-1, 
										Integer.parseInt(dateString[1]));
	}
	
	public Boolean priceIsInEffect(GregorianCalendar currentDate)
	{
		boolean priceIsInEffect = false;
		
		if((getEffectiveDate().getTimeInMillis()<= currentDate.getTimeInMillis()) && 
				(endDate.getTimeInMillis() >= currentDate.getTimeInMillis()))
		{
			priceIsInEffect = true;
		}
		
		return priceIsInEffect;
	}
}
