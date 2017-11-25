package POSPD;

public class UPC 
{
	private Item item;
	private String UPC;

	public UPC(String code, Item item)
	{
		setUPC(code);
		this.item = item;
	}
	
	public UPC()
	{
		
	}
	
	public String getUPCString() 
	{
		return this.UPC;
	}
	
	public void setUPC(String UPC) 
	{
		this.UPC = UPC;
	}

	public Item getItem() 
	{
		return this.item;
	}
	
	public String toString()
	{
		return getUPCString();
	}
	
	public String toString2()
	{
		String UPCString = getUPCString(); 
		return " UPC Code(s):" + UPCString;
	}
	
	//public Boolean isUsed()
	//{
	//	boolean isUsed = false;
		
	//	if(item.getDescription() != null)
			//isUsed = true;
		
	//	return isUsed;
	//}
}
