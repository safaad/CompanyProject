package Products;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Date;
import java.io.Serializable;
import java.time.LocalDate;

public class OrderManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Queue<Order> Orders = new LinkedList<Order>();
	public final int carLimit = 5;
	public int activeCars = 0;
	public boolean[] isActiveCars = new boolean[carLimit];
	public Order[] Orderat = new Order[carLimit];
	protected Date date = new Date();
	protected int day = LocalDate.now().getDayOfMonth();
	//@SuppressWarnings("deprecation")
	//protected Time time = new Time(date.getHours(), date.getMinutes(), date.getSeconds());
	
	private boolean isAvailableCar() {
		return (activeCars < carLimit);
	}
	
	private boolean isEmptyQueue() {
		return (Orders.size() == 0);
	}
	
	public boolean delivering(Order O) {
		for(Order or : Orderat)
			if(or.getOwner().equals(O.getOwner()))
				return true;
		return false;
	}
	
	private boolean isFullCarage() {
		return (activeCars == carLimit);
	}
	
	public void sendCar(Order O) {
		if(isAvailableCar()){
			Orders.remove();
			isActiveCars[activeCars] = true;
			Orderat[activeCars++] = O;
		}
	}
	
	private void recieveCar() {
		if(!isFullCarage())
			isActiveCars[--activeCars] = false;
	}
	
	public void trackOrder(String username) {
		for(Order p : Orderat) {
			if(p != null && p.getOwner().equals(username))
				System.out.println("Your order has been shipped!\n" + p);
		}
		for(Order p : Orders)
			if(p.getOwner().equals(username))
				System.out.println("Your order hasn't been shipped yet!" + p);
	}
	
	public void EnqueueOrder(Order O) {
		Orders.add(O);
		deliveryCycle();
	}
	
	@SuppressWarnings("deprecation")
	public void deliveryCycle() {
		for(int i = 0; i < Orderat.length; i++) {
			if(Orderat[i] == null)
				break;
			if(Orderat[i].getDeliverDay() >= LocalDate.now().getDayOfMonth() && Orderat[i].getDeliverHour() >= date.getHours()) {
				isActiveCars[i] = false;
				recieveCar();
			}
			else
				break;
		}
		while(isAvailableCar() && !isEmptyQueue())
			sendCar(Orders.peek());
	}
}