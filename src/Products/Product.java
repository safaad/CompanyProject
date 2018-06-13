package Products;

import java.io.Serializable;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String itemName;
	protected float Rate;
	protected float Price;
	protected float netPrice;
	protected int Qty;

	public Product(String ItemName, float price, float NetPrice, int Qty) {
		itemName = ItemName;
		Price = price;
		netPrice = NetPrice;
		this.Qty = Qty;
	}

	public String getItemName() {
		return itemName;
	}

	public float getNetPrice() {
		return netPrice;
	}

	public float getPrice() {
		return Price;
	}

	public int getQty() {
		return Qty;
	}

	public float getRate() {
		return Rate;
	}

	public void setRate(float rate) {
		Rate = rate;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setNetPrice(float netPrice) {
		this.netPrice = netPrice;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public String toString() {
		return itemName + "\t$" + Price + "\tQuantity: " + Qty;
	}
}
