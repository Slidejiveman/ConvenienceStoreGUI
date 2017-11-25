package POSPD;
import java.util.*;
import java.math.*;
import java.text.*;

public class TaxCategory 
{
	private String category;
	private GregorianCalendar effectiveDate;
	private BigDecimal taxRate;

	public TaxCategory(String category, String rateString, String dateString)
	{
		setCategory(category);
		setEffectiveDate(dateString);
		setTaxRate(rateString);
	}
	
	public TaxCategory()
	{
		setTaxRate("0"); // BigDecimal needs to have something in it since it calls .toPlainString()
	}
	
	public String getCategory() 
	{
		return this.category;
	}

	public void setCategory(String category) 
	{
		this.category = category;
	}

	public GregorianCalendar getEffectiveDate() 
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(String effectiveDateString) 
	{
		String dateString[] = effectiveDateString.split("/");
		effectiveDate = new GregorianCalendar(Integer.parseInt(dateString[2]), 
										Integer.parseInt(dateString[0])-1, Integer.parseInt(dateString[1]));
	}

	public BigDecimal getTaxRate() 
	{
		return this.taxRate;
	}

	public void setTaxRate(String taxRate) 
	{
		//convert String into BigDecimal
		this.taxRate = new BigDecimal(taxRate);
	}
	
	public Boolean isUsed()		// Logic determining how this isUsed needs to be fixed
	{
		boolean isUsed = false;
		
		if(getCategory() != null)
		{
			isUsed = true;
		}
		
		return isUsed;
	}
	
	public String toString()
	{
		return getCategory();
	}
}
