/**
 * 
 */
package POSDM;
import java.io.*;
import POSPD.*;

/**
 * @author RyderDale
 *
 */
public class DataRead 
{
	public DataRead(Store myStore)
	{
		dataRead(myStore);
	}
	public static void dataRead(Store myStore)
	{
		String inFile = "CStoreTestIn.csv";
		String line = null;
		Session session = null;
		Sale sale = null;
		// String dataType;
		
		try
		{
			//Open and read file
			FileReader fileReader = new FileReader(inFile);
			//Wrap opened file in BufferedReader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//Add statements to populate an array of split strings
			while((line = bufferedReader.readLine()) != null)
			{
				//Split the line string into sub-strings which can be assigned
				//to the individual elements of an array
				String[] result = line.split(",");
				
				
				//for(int i = 0; i< result.length; i++)
				//{
					//System.out.println(Integer.toString(i)+ " " + result[i]);
				//}
				
				if(result[0].equals("Store"))
				{
					myStore.setName(result[1]);
					myStore.setNumber(result[2]);
				}
				
				else if (result[0].equals("TaxCategory"))
				{
					TaxCategory taxCategory = new TaxCategory(result[1], result[2], result[3]);
					myStore.addTaxCategory(taxCategory);
				}
				
				else if (result[0].equals("Cashier"))
				{
					Person person = new Person(result[3], result[4], result[5], result[6], result[7], result[8], result[9]);
					Cashier cashier = new Cashier(result[1], result[2], person);
					
					// Send cashier to person object
					person.setCashier(cashier);
					myStore.addCashier(cashier);
				}
				
				else if (result[0].equals("Item"))
				{
					// Created in this order so that objects can be passed along
					// if this isn't acceptable, the objects could be passed store
					// and the information could be requested from store
					TaxCategory taxCategory = myStore.findTaxCategory(result[4]);
					Item item = new Item(result[1], result[3], taxCategory);
					UPC upc = new UPC(result[2], item);
					Price price = new Price(item, result[5], result[6]);
					
					item.addUPC(upc);
					item.addPrice(price);
					myStore.addUPC(upc);
					myStore.addItem(item);
					
					// Add extra price information when available
					if(result.length > 7)
					{
						PromoPrice promoPrice = new PromoPrice(item, result[7], result[8], result[9]);
						item.addPrice(promoPrice);
					}
						
				}
				
				else if (result[0].equals("Register"))
				{
					
					CashDrawer cashDrawer = new CashDrawer(result[2], "1");
					Register register = new Register(result[1], cashDrawer);
					
					myStore.addRegister(register);
				}
				
				else if (result[0].equals("Session"))
				{
					Cashier cashier = myStore.findCashierByNumber(result[1]);
					Register register = myStore.findRegisterByNumber(result[2]);
					session = new Session(cashier, register);
					cashier.addSession(session);
				}
				
				else if (result[0].equals("Sale"))
				{
					Boolean isTaxFree = false;
					
					if(result[1].equals("Y"))
					{
						isTaxFree = false;
					}
					else if(result[1].equals("N"))
					{
						isTaxFree = true;
					}
					// else throw an exception
					
					sale = new Sale(isTaxFree, session);
					session.addSale(sale);
				}
				
				else if (result[0].equals("SaleLineItem"))
				{
					Item item = myStore.findItemByNumber(result[1]);	
					SaleLineItem saleLineItem = new SaleLineItem(item, result[2]);
					sale.addSaleLineItem(saleLineItem);
				}
				
				else if (result[0].equals("Payment"))
				{
					if(result[1].equals("Cash"))
					{
						Cash cash = new Cash(result[3]);
						sale.addPayment(cash);
					}
					else if (result[1].equals("Credit"))
					{
						// amount tendered, card type, card number, expiration date
						Credit credit = new Credit(result[3], result[4], result[5], result[6]);
						sale.addPayment(credit);
					}
					else if (result[1].equals("Check"))
					{
						// amountTendered, routing number, account number, check number
						Check check = new Check(result[3], result[4], result[5], result[6]);
						sale.addPayment(check);
					}
				}
			}
			
			// Close file
			bufferedReader.close();
		}
		catch(FileNotFoundException exception)
		{
			System.out.println("Unfortunately, the file was not found.\n");
		}
		catch(IOException exception)
		{
			System.out.println("There was an error trying to read the file.\n");
		}
	}
}