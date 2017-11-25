package POSPD;

import java.math.BigDecimal;
import java.util.*;

public class Cashier 
{
	private Person person;
	private ArrayList<Session> sessions;
	private String number;
	private String password;
 
	public Cashier(String number, String password, Person person)
	{
		setNumber(number);
		setPassword(password);
		setPerson(person);
		
		sessions = new ArrayList<Session>();
	}
	
	public Cashier()
	{
		sessions = new ArrayList<Session>();
		person = new Person();
		
	}
	
	public String getNumber() 
	{
		return this.number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getPassword() 
	{
		return this.password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Person getPerson() 
	{
		return this.person;
	}

	public void setPerson(Person person) 
	{
		this.person = person;
	}

	public Boolean isAuthorized(String password) 
	{
		throw new UnsupportedOperationException();
	}
	
	public void addSession(Session session)
	{
		sessions.add(session);
	}
	
	public String toString()
	{
		return person.getName();
	}
	
	public String toString2()
	{
		String sessionString = "\nSession on:";
		String cashierString = "Cashier Name: " + person.getName()+ "; Cashier Number: " + getNumber();
		
		ListIterator<Session> listIterator = sessions.listIterator();
		while(listIterator.hasNext())
		{
			Session element = listIterator.next();
			sessionString +=element.toString();
		}
		return (cashierString + sessionString);
	}
	
	public Boolean isUsed()
	{
		boolean isUsed = false;
		
		if(sessions.size() != 0)
			isUsed = true;
		
		return isUsed;
	}
	
	public ArrayList<Session> getSessions()
	{
		return sessions;
	}
	
	public int calcNumberSales(GregorianCalendar date)
	{
		int numSales = 0;
		
		for(Session session : getSessions())
		{
			if(session.getStartDateTime().get(Calendar.YEAR) == date.get(Calendar.YEAR)&&
					session.getStartDateTime().get(Calendar.DAY_OF_YEAR) == 
					date.get(Calendar.DAY_OF_YEAR))
			{
				numSales += session.getSales().size();
			}
		}
			
		return numSales;
	}
	
	public BigDecimal calcDollarSales(GregorianCalendar date)
	{
		BigDecimal dollarSales = new BigDecimal("0");
	
		for(Session session : getSessions())
		{
			if(session.getStartDateTime().get(Calendar.YEAR) == date.get(Calendar.YEAR)&&
				session.getStartDateTime().get(Calendar.DAY_OF_YEAR) == 
				date.get(Calendar.DAY_OF_YEAR))
			{
				dollarSales = dollarSales.add(session.calcTotal()); //add this method
			}
		}
		
		return dollarSales;
	}
}
