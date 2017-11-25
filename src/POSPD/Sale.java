package POSPD;

import java.util.*;
import java.math.*;

public class Sale 
{
	private ArrayList<SaleLineItem> saleLineItems;
	private ArrayList<Payment> payments;
	private Session session;
	private GregorianCalendar date;
	private Boolean isTaxFree = false;
	private BigDecimal total = new BigDecimal("0");
	private BigDecimal totalPayment = new BigDecimal("0");
	private BigDecimal change = new BigDecimal("0");
	private BigDecimal totalTax = new BigDecimal("0");
	private BigDecimal subTotal = new BigDecimal("0");
	
	public Sale(Boolean taxStatus, Session session)
	{
		this();
		setTaxStatus(taxStatus);
		date = new GregorianCalendar();
		this.session = session;
		
	}
	
	public Sale()
	{
		saleLineItems = new ArrayList<SaleLineItem>();
		payments = new ArrayList<Payment>();
	}

	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return saleLineItems;
	}
	
	public void addSaleLineItem(SaleLineItem saleLineItem)
	{
		saleLineItems.add(saleLineItem);
	}
	
	public void addPayment(Payment payment)
	{
		payments.add(payment);
	}
	
	public GregorianCalendar getDate() 
	{
		return this.date;
	}

	public void setDate(GregorianCalendar date) 
	{
		this.date = date;
	}

	public Boolean getTaxStatus() 
	{
		return this.isTaxFree;
	}

	public void setTaxStatus(Boolean taxStatus) 
	{
		this.isTaxFree = taxStatus;
	}

	public BigDecimal calcTotal() 
	{
			
		this.total = calcSubTotal().add(calcTax());
	
		return this.total;
	}

	public BigDecimal calcSubTotal() 
	{
		subTotal = new BigDecimal("0");
		for(SaleLineItem sli : saleLineItems)
		{
			subTotal = subTotal.add(sli.calcSubTotal());
		}
		return subTotal;
	}

	public BigDecimal calcTax() 
	{
		totalTax = new BigDecimal("0");
		for(SaleLineItem sli : saleLineItems)
		{
			if(!isTaxFree)
			{
				totalTax = totalTax.add(sli.calcTax());
			}			
		}
		return totalTax;
	}
	
	public BigDecimal getTotalTax()
	{
		return this.totalTax;
	}

	public BigDecimal getTotalPayments() 
	{
		totalPayment = new BigDecimal("0");
		for(Payment p : payments)
		{
			totalPayment = totalPayment.add(p.getAmountTendered());
		}
		return totalPayment;
	}
	
	public BigDecimal getCashOrCheckPayments()
	{
		BigDecimal cashOrCheck = new BigDecimal("0");
		for (Payment p: payments)
		{
			// Do not increment cash drawers if object is of type "Credit"
			if(!p.getClass().equals(Credit.class))
			{
				cashOrCheck = cashOrCheck.add(p.getAmountTendered());
			}
		}
		return cashOrCheck;
	}
	
	public BigDecimal calcCashDrawerAmount()
	{
		BigDecimal cashDrawerAmount;
		cashDrawerAmount = session.getRegister().getCashDrawer().getCashAmount()
				.add(getCashOrCheckPayments()).subtract(calcChange());
		
		// Update the amount in the cash drawer as the process goes
		session.getRegister().getCashDrawer().setCashAmount(cashDrawerAmount.toPlainString());
		return cashDrawerAmount;
	}
	
	public BigDecimal getTotal()
	{
		return this.total;
	}
	
	public BigDecimal getChange()
	{
		return this.change;
	}

	public Boolean isPaymentEnough() 
	{
		Boolean isPaymentEnough = false;
		if(getTotalPayments().compareTo(calcTotal()) >= 0)
		{
			isPaymentEnough = true;
		}
		
		return isPaymentEnough;
	}
	
	public BigDecimal calcChange()
	{
		change = new BigDecimal("0");
		change = totalPayment.subtract(total);
		return change;
	}
	
	public String toString()
	{
		String saleString = "Total: $" + calcTotal().toPlainString() +
							" Amount Tendered: $" + getTotalPayments().toPlainString() +
							" Change: $" + calcChange().toPlainString() +
							" Tax Free: " + getTaxStatus() +
							" Tax Amount: $" + getTotalTax().toPlainString() +
							"\nDate: " + date.get(Calendar.MONTH)+ 
							"/" + date.get(Calendar.DAY_OF_MONTH) + 
							"/" + date.get(Calendar.YEAR);
		
		String saleLineItemString = "";
		ListIterator<SaleLineItem> listIterator1 = saleLineItems.listIterator();
		while(listIterator1.hasNext())
		{
			SaleLineItem element = listIterator1.next();
			saleLineItemString +=element.toString2();
		}
		
		String paymentString = "";
		ListIterator<Payment> listIterator2 = payments.listIterator();
		while(listIterator2.hasNext())
		{
			Payment element = listIterator2.next();
			paymentString +=element.toString();
		}
		
		return saleLineItemString + saleString +  
				"\nCash and Check in Drawer: $" + calcCashDrawerAmount() + "\n\n";	
	}
}
