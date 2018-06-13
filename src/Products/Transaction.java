package Products;
import java.util.Date;

import Individuals.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Transaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Date date;
	public ArrayList<Product> boughtProducts = new ArrayList<Product>();
	protected Client customer;
	protected static int serial = 1;
	private String TrxId;
	private double totalMoney = 0;

	public Transaction(Client c, ArrayList<Product> boughtProducts){
		customer = c;
		date = Calendar.getInstance().getTime();
		TrxId = "" + c.getUsername().charAt(0) + serial;
		serial++;
		for(Product p : boughtProducts) {
			this.boughtProducts.add(p);
			this.totalMoney += p.getPrice() * p.getQty();
		}
	}

	public static void setSerial(int serial2) {
		serial = serial2;
	}

	public static int getSerial() {
		return serial;
	}
	
	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String toString() {
		String s = "";
		s += "Transaction " + TrxId + " on " + date + "\n";
		s += "-----------\n";
		
		s += "Client:" + customer.getUsername() + "\n";
		s += "---------------------------------\n";
		s += "List of bought products: \n";
		for (int i = 0; i < boughtProducts.size(); i++)
				s += boughtProducts.get(i) + "\n";
		s += "---------------------------------\n";
		s += "Total amount paid: " + totalMoney + "$\n";
		s += "---------------------------------\n";
		return s;
	}
}