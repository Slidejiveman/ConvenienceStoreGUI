/**
 * 
 */
package POSHI;
import POSPD.*;
import POSDM.*;

/**
 * @author RyderDale
 *
 */
public class StoreTest 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String namePlaceholder = " ";
		String numPlaceholder = " ";
		Item item;
		
		// Create store object that will be populated from data sheet
		Store myStore = new Store(namePlaceholder, numPlaceholder);
		DataRead dataRead = new DataRead(myStore); // Read data in from sheet
		
		System.out.println(myStore.toString());	
		POSFrame.run(myStore);
	}
}
