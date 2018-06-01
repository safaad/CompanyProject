package Individuals;

import java.util.ArrayList;
import Driver.*;
import Products.*;

public class Client extends Person {
	protected String Username;
	protected double contribution;
	protected Employee FavoriteEmployee;
	protected ArrayList<Product> boughtProducts;
	//protected ArrayList<Product> Cart;

	public Client(String first, String last, int[] birthday, String username, double total, String EmpName) {
		super(first, last, birthday);
		Username = username;
		contribution = total;
		boughtProducts = new ArrayList<Product>();
		//Cart = new ArrayList<Product>();
		FavoriteEmployee = Menu.Website.getEmployee(EmpName);
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
	
	private void setTotal(double value) {
		contribution = value;
	}

	private Employee getFavEmp() {
		return FavoriteEmployee;
	}
	
	private void setFavEmp(Employee fav) {
		FavoriteEmployee = fav;
	}
	
	private void setFavEmp(String favEmpName) {
		FavoriteEmployee = Menu.Website.getEmployee(favEmpName);
	}
}
