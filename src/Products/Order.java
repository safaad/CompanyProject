package Products;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String Oid;
	protected String owner;
	protected int orderDay;
	protected Date day = new Date();
	protected int orderHour;
	protected int deliverDay;
	protected int deliverHour;

	ArrayList<Product> Cart = new ArrayList<Product>();

	@SuppressWarnings("deprecation")
	public Order(String username, ArrayList<Product> Cart) {
		owner = username;
		this.Cart = Cart;
		orderDay = LocalDate.now().getDayOfMonth();
		orderHour = day.getHours();
		deliverHour = orderHour;
		deliverDay = (orderDay + 1) % 30;
	}

	public String getOwner() {
		return owner;
	}

	public int getDeliverDay() {
		return deliverDay;
	}

	public int getDeliverHour() {
		return deliverHour;
	}

	public String toString() {
		return "Order will be at your place on day " + deliverDay + " at " + deliverHour + ":00";
	}
}
