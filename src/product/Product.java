package product;

public class Product {
	protected String itemName;
	protected String Pid;
	protected float Rate;
	protected float Price;
	protected float netPrice;
	protected int Qty;
	
	public Product(String ItemName, String pid, float price, float NetPrice, int Qty) {
		ItemName = itemName;
		Pid = pid;
		Price = price;
		NetPrice = netPrice;
		this.Qty = Qty;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public float getNetPrice() {
		return netPrice;
	}
	
	public String getPid() {
		return Pid;
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
	
	public void setPid(String pid) {
		Pid = pid;
	}
	
	public void setPrice(float price) {
		Price = price;
	}
	
	public void setQty(int qty) {
		Qty = qty;
	}
	
	public String toString() {
		return "" + itemName + "\t" + Price;
	}
}
