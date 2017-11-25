package POSPD;

public class Person 
{
	private Cashier cashier;
	private String name;
	private String address;
	private String phone;
	private String SSN;
	private String city;
	private String state;
	private String zip;

	public Person(String name, String SSN, String address, String city, String state, String zip, String phone)
	{
		setName(name);
		setAddress(address);
		setPhone(phone);
		setSSN(SSN);
		setCity(city);
		setState(state);
		setZip(zip);
	}
	
	public Person()
	{
		setName("");
		setAddress("");
		setPhone("");
		setSSN("");
		setCity("");
		setState("");
		setZip("");
	}
	
	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAddress() 
	{
		return this.address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getCity() 
	{
		return this.city;
	}
	
	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getState() 
	{
		return this.address;
	}
	
	public void setState(String state) 
	{
		this.state = state;
	}
	
	public String getZip() 
	{
		return this.address;
	}
	
	public void setZip(String zip) 
	{
		this.zip = zip;
	}
	
	public String getPhone() 
	{
		return this.phone;
	}

	public void setPhone(String Phone) 
	{
		this.phone = Phone;
	}

	public String getSSN() 
	{
		return this.SSN;
	}

	public void setSSN(String SSN) 
	{
		this.SSN = SSN;
	}
	
	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}
}
