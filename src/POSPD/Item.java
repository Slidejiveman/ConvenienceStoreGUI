package POSPD;

import java.util.*;
import java.math.*;

public class Item 
{
	private TreeMap<String, SaleLineItem> saleLineItems;
	private TaxCategory taxCategory;
	private String number;
	private TreeSet<Price> prices;
	private TreeMap<String, UPC> UPCs;
	private String description;

	public Item(String number, String description, TaxCategory taxCategory)
	{
		UPCs = new TreeMap<String, UPC>();
		prices = new TreeSet<Price>(); 
		saleLineItems = new TreeMap<String, SaleLineItem>();
		
		setNumber(number);
		setDescription(description);
		setTaxCategory(taxCategory);	
	}
	
	public Item()
	{
		UPCs = new TreeMap<String, UPC>();
		prices = new TreeSet<Price>(); 
		saleLineItems = new TreeMap<String, SaleLineItem>();
	}
	
	public String getNumber() 
	{
		return this.number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getDescription() 
	{
		return this.description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public TaxCategory getTaxCategory()
	{
		return this.taxCategory;
	}
	
	public void setTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategory = taxCategory;
	}
	
	public void addUPC(UPC UPC)
	{
		UPCs.put(UPC.getUPCString(), UPC);
	}

	// I don't think this is going to work down the road.
	public UPC findUPCByCode(String UPC) 
	{
		UPC UPCcode;
		// Search the tree map for UPC
		UPCcode = UPCs.get(UPC);
		// Return UPCs held item
		return UPCcode;
	}

	public void addPrice(Price price)
	{
		prices.add(price);
	}
	
	public Price findPriceByDate(GregorianCalendar currentDate) 
	{
		Price currentPrice = null;
		for(Price price : prices)
		{
			//Put PromoPrices on the bottom of the list so they will override normal prices
			if(price.priceIsInEffect(currentDate))
			{
				currentPrice = price;
			}
		}
		return currentPrice;
	}
	
	public BigDecimal getPriceByDate(GregorianCalendar currentDate) 
	{
		throw new UnsupportedOperationException();
	}

	//Make this by type
	public BigDecimal getTaxRate() 
	{
		BigDecimal taxRate = taxCategory.getTaxRate();
		return taxRate;
	}
	
	public String toString()
	{
		return getDescription();
	}
	
	// Need to make toString functions for each object
	public String toString2()
	{
		// Making a new GregorianCalendar with no arguments causes the default constructor
		// to create a calendar with today's default information
		String itemString = "Item Number: " + getNumber() + " Item Name: " + getDescription() + " Item Price: $" + 
				findPriceByDate(new GregorianCalendar()).getPriceAmount().toPlainString() + " Tax Category: " + 
				taxCategory.getCategory();
		
		String UPCString = "";
		for(Map.Entry<String, UPC> entry : UPCs.entrySet())
		{
			String key = entry.getKey();
			UPC value = entry.getValue();
			UPCString += value.toString2();
		}
		
		//May need to iterate three a tree set
		
		return itemString + UPCString;
	}
	
	public Boolean isUsed()
	{
		boolean isUsed = false;
		
		if(UPCs.size() != 0)
			isUsed = true;
		
		return isUsed;
	}
	
	public TreeSet<Price> getPrices()
	{
		return prices;
	}
	
	public TreeMap<String, UPC> getUPCs()
	{
		return UPCs;
	}
	
	public void removePrice(Price price)
	{
		prices.remove(price);
	}
	
	public void removeUPC(UPC upc)
	{
		UPCs.remove(upc.getUPCString());
	}
	
	public int calcItemSoldCount(GregorianCalendar date)
	{
		int count = 0;
		return count;
		
	}
}
