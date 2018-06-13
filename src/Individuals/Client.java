package Individuals;

import java.util.ArrayList;
import Drivers.*;
import Products.Product;
public class Client extends Person {
	
	private static final long serialVersionUID = 1L;
	protected String Username, Password;
	protected double contribution;
	protected Employee FavoriteEmployee;
	protected ArrayList<Product> boughtProducts;
	public ArrayList<Product> WishList;
	protected ArrayList<Product> Cart;

	public Client(String first, String last, int[] birthday, String username, double total, String EmpName) {
		super(first, last, birthday);
		Username = username;
		contribution = total;
		boughtProducts = new ArrayList<Product>();
		// Cart = new ArrayList<Product>();
		FavoriteEmployee = Driver.Website.getEmployee(EmpName);
		boughtProducts = new ArrayList<Product>();
	}

	public Client(String first, String last, int[] birthday, String username, double total) {
		super(first, last, birthday);
		Username = username;
		contribution = total;
		boughtProducts = new ArrayList<Product>();
		// Cart = new ArrayList<Product>();
	}
	
	public Client(String fn, String ln, int[] birthday, String username, String password) {
		super(fn, ln, birthday);
		Username = username;
		Password = password;
		WishList = new ArrayList<Product>();
		Cart = new ArrayList<Product>();
		boughtProducts = new ArrayList<Product>();
	}
	
	public boolean confirmPassword(String password) {
		return (Password.equals(password));
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public double getTotal() {
		return contribution;
	}

	protected void setTotal(double value) {
		contribution = value;
	}

	protected Employee getFavEmp() {
		return FavoriteEmployee;
	}
	protected void setFavEmp(Employee fav) {
		FavoriteEmployee = fav;
	}
	
	protected void setFavEmp(String favEmpName) {
		FavoriteEmployee = Driver.Website.getEmployee(favEmpName);
	}
	
	public boolean inWishList(String item) {
		for(Product p : WishList)
			if(p.getItemName().equals(item))
				return true;
		return false;
	}
	
	public void addToWishList(String item) {
		ArrayList<Product> k = Driver.Website.getProducts();
		if(inWishList(item))
			return;
		for(Product p : k)
			if(p.getItemName().equals(item))
				WishList.add(p);
	}
	
	public void removeFromWishList(String item) {
		for(int i = 0; i < WishList.size(); i++)
			if(WishList.get(i).getItemName().equals(item))
				WishList.remove(i);
	}
	
	public void viewWishList() {
		if(WishList.isEmpty())
			System.out.println("Your wish list is empty!");
		for(Product p : WishList)
			System.out.println(p);
	}
	
	public boolean order(String item, int Qty) {
		ArrayList<Product> k = Driver.Website.getProducts();
		if(Qty <= 0)
			return false;
		for(Product p : k)
			if(p.getItemName().equals(item)) {
				if(p.getQty() < Qty) // if the amount needed is not present in the Website return false
					return false;
				p.setQty(p.getQty() - Qty); // quantity of product p in Website will be decreased by Qty 
				for(Product pr : Cart)
					if(pr.getItemName() == p.getItemName()) {
						pr.setQty(pr.getQty() + Qty);
						return true;
					}
				Cart.add(new Product(p.getItemName(), p.getPrice(), p.getNetPrice(), Qty));
				return true;
			}
		return false;
	}
	
	public void removeOrder(String item, int Qty) {
		ArrayList<Product> k = Driver.Website.getProducts();
		for(Product p : k)
			if(p.getItemName().equals(item))
				k.get(k.indexOf(p)).setQty(p.getQty() + Qty);
		for(Product p : Cart)
			if(p.getItemName().equals(item))
				if(p.getQty() == Qty) {
					Cart.remove(p);
					break;
				}
				else {
					p.setQty(p.getQty() - Qty);
					break;
				}
	}
	
	public int existInCart(String item) {
		for(Product p : Cart)
			if(p.getItemName().equals(item))
				return Cart.indexOf(p);
		return -1;
	}
	
	public void viewCart() {
		if(Cart.isEmpty())
			System.out.println("Your cart is empty!");
		for(Product p : Cart) 
			System.out.println(p);
	}
	
	public void clearCart() {
		for(int i = 0; i < Cart.size(); i++) { //NOIIIIIIIIIIIIIIIIIIIIIIIIIIIIICE
			Product p = Cart.get(i);
			removeOrder(p.getItemName(), p.getQty());
		}
	}
	
	public ArrayList<Product> getCart() {
		return Cart;
	}
	
	public void refresh() {
		for(int i = 0; i < Cart.size(); i++)
			for(int j = i + 1; j < Cart.size(); j++)
				if(Cart.get(i).getItemName().equals(Cart.get(j).getItemName())) {
					Cart.get(i).setQty(Cart.get(i).getQty() + Cart.get(j).getQty());
					Cart.remove(j);
				}
	}
	
}