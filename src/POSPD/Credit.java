package POSPD;
import java.util.*;
import java.math.*;

public class Credit extends AuthorizedPayment 
{
	private String cardType;
	private String cardNumber;
	private BigDecimal amountTendered;
	private GregorianCalendar expirationDate;
	private ArrayList<String>cardTypes;
	

	public Credit(String amountTendered, String cardType, String cardNumber, String expirationDate)
	{
			
		setType(cardType);
		setNumber(cardNumber);
		setAmountTendered(new BigDecimal(amountTendered));
		setExpireDate(expirationDate);
		cardTypes = new ArrayList<String>();
		setCardTypes("MC", "Visa", "Discover", "American Express");
		
	}
	
	public Credit()
	{
		
	}
	
	public String getCardType() 
	{
		return this.cardType;
	}

	public void setType(String type) 
	{
		this.cardType = type;
	}
	
	public ArrayList<String> getCardTypes()
	{
		return cardTypes;
	}
	
	public void setCardTypes(String mc, String visa, String discover, String americanExpress)
	{
		cardTypes.add(mc);
		cardTypes.add(visa);
		cardTypes.add(discover);
		cardTypes.add(americanExpress);
	}

	public String getNumber() 
	{
		return this.cardNumber;
	}

	public void setNumber(String number) 
	{
		this.cardNumber = number;
	}

	public GregorianCalendar getExpireDate() 
	{
		return this.expirationDate;
	}

	public void setExpireDate(String expireDate)
	{
		String dateString[] = expireDate.split("/");
		this.expirationDate = new GregorianCalendar(Integer.parseInt(dateString[2]), 
										Integer.parseInt(dateString[0])-1, Integer.parseInt(dateString[1]));
	}
	
	public void setExpireDate(GregorianCalendar expireDate) 
	{
		this.expirationDate = expireDate;
	}
	
	//These are overwritten
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
		return "Amount Tendered $" + amountTendered.toPlainString() + " \n";
	}

}
