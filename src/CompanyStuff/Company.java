package CompanyStuff;

import Individuals.*;
import Products.*;
import java.util.ArrayList;

public class Company {
	public ArrayList<Employee> HE = new ArrayList<Employee>();
	public ArrayList<Client> Consumer = new ArrayList<Client>();
	public ArrayList<Product> Products = new ArrayList<Product>();

	public Employee getEmployee(String user) {
		for (Employee e : HE)
			if (e.getUsername().equals(user))
				return e;
		return null;
	}

	public boolean exist(String user) {
		for (int i = 0; i < HE.size(); i++)
			if (HE.get(i).getUsername().equals(user))
				return true;
		return false;
	}

	public boolean isProductExists(Product p) {
		for (Product p2 : Products) {
			if (p2.equals(p))
				return true;
		}
		return false;
	}

	public boolean deleteProduct(Product p) {
		if (this.isProductExists(p)) {
			this.Products.remove(p);
			return true;
		}
		return false;
	}

}
