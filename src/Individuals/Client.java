package Individuals;

import java.util.ArrayList;
import Drivers.*;
import Products.Product;

public class Client extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String Username, Password;
	protected double contribution;
	protected Employee FavoriteEmployee;
	protected ArrayList<Product> boughtProducts;
	public ArrayList<Product> WishList;
	// protected ArrayList<Product> Cart;

	public Client(String first, String last, int[] birthday, String username, double total, String EmpName) {
		super(first, last, birthday);
		Username = username;
		contribution = total;
		boughtProducts = new ArrayList<Product>();
		// Cart = new ArrayList<Product>();
		FavoriteEmployee = Driver.Website.getEmployee(EmpName);
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
	}
	
	public boolean verifyPassword(String password) {
		return (this.Password.equals(password));
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
}
