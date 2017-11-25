package POSPD;

import java.util.*;

public class Store 
{
	// issue with sorted maps
	private TreeMap<String, Item> items;
	private TreeMap<String, Item> itemsByNumber;
	private TreeMap<String, Register> registers;
	private TreeMap<String, TaxCategory> taxCategories;
	private TreeMap<String, Cashier> cashiers;
	private TreeMap<String, UPC> UPCs;
	private String number;
	private String name;
	
	public Store(String name, String number)
	{
		this();
		setName(name);
		setNumber(number);
	}
	
	public Store()
	{
		UPCs = new TreeMap<String, UPC>();
		cashiers = new TreeMap<String, Cashier>();
		taxCategories = new TreeMap<String, TaxCategory>();
		registers = new TreeMap<String, Register>();
		items = new TreeMap<String, Item>();
		itemsByNumber = new TreeMap<String,Item>();
	}
	
	public String getNumber() 
	{
		return this.number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public void addItem(Item item)
	{
		items.put(item.getNumber(), item);
		itemsByNumber.put(item.getNumber(), item);
	}
	
	public Item findItemByUPC(String UPCString) 
	{
		UPC UPCcode;
		
		// Search the tree map for UPC
		UPCcode = UPCs.get(UPCString);
		
		// Return UPCs held item
		return UPCcode.getItem();
	}
	
	public Item findItemByNumber(String itemNumber)
	{
		Item item;
		item = itemsByNumber.get(itemNumber);
		return item;
	}

	public void addUPC(UPC UPC)
	{
		// Pass in a UPC that uses the associated string
		// to find a UPC object and add the object to the map
		UPCs.put(UPC.getUPCString(), UPC);
	}
	
	public Cashier findCashierByNumber(String number) 
	{
		Cashier cashier;
		// Search the tree map for UPC
		cashier = cashiers.get(number);
		// Return UPCs held item
		return cashier;
	}
	
	public void addCashier(Cashier cashier)
	{
		cashiers.put(cashier.getNumber(), cashier);
	}
	
	public TaxCategory findTaxCategory(String category)
	{
		TaxCategory taxCategory;
		taxCategory = taxCategories.get(category);
		return taxCategory;
	}
	
	public void addTaxCategory(TaxCategory taxCategory)
	{
		taxCategories.put(taxCategory.getCategory(), taxCategory);
	}
	
	public Register findRegisterByNumber(String number)
	{
		Register register;
		register = registers.get(number);
		return register;
	}
	
	public void addRegister(Register register)
	{
		registers.put(register.getRegisterNumber(), register);
	}
	
	public String toString()
	{
		String storeString = "Store: " + getName() + "; " + 
				"Store Number : " + getNumber();
		
		String cashierString = "";
		for(Map.Entry<String, Cashier> entry : cashiers.entrySet())
		{
			String key = entry.getKey();
			Cashier value = entry.getValue();
			cashierString += value.toString2();
		}
		
		return storeString + "\n\n" + cashierString;
	}
	
	public TreeMap<String, TaxCategory> getTaxCategories()
	{
		return taxCategories;
	}
	
	public TreeMap<String, Cashier> getCashiers()
	{
		return cashiers;
	}
	
	public TreeMap<String, Register> getRegisters()
	{
		return registers;
	}
	
	public TreeMap<String, Item> getItems()
	{
		return items;
	}
	
	public TreeMap<String, Item> getItemsByNumber()
	{
		return itemsByNumber;
	}
	
	public void removeTaxCategory(TaxCategory taxCategory)
	{
		taxCategories.remove(taxCategory.getCategory());
	}
	
	public void removeCashier(Cashier cashier)
	{
		cashiers.remove(cashier.getNumber());
	}
	
	public void removeRegister(Register register)
	{
		registers.remove(register.getRegisterNumber());
	}
	
	public void removeItem(Item item)
	{
		itemsByNumber.remove(item.getNumber());	//There are two lists that have items in them
		items.remove(item.getNumber());			//Might be entirely redundant
	}
	
	public ArrayList<TaxCategory> getTaxCategoriesAsArray()
	{
		ArrayList<TaxCategory> list = new ArrayList<TaxCategory>(taxCategories.values());
		
		return list;
	}
	
	public ArrayList<Cashier> getCashiersAsArray()
	{
		ArrayList<Cashier> list = new ArrayList<Cashier>(cashiers.values());
		
		return list;
	}
	
	public ArrayList<Register> getRegistersAsArray()
	{
		ArrayList<Register> list = new ArrayList<Register>(registers.values());
		
		return list;
	}
	
	public void removeUPC(UPC upc)
	{
		UPCs.remove(upc.getUPCString());
	}
}
