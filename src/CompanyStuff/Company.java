package CompanyStuff;

import Individuals.*;
import Products.*;
import java.util.ArrayList;

<<<<<<< HEAD
import Individuals.Client;
import Individuals.Employee;
import Individuals.HourlyEmployee;
import Individuals.PartTimeEmployee;
import product.Product;

public class Company {
	public ArrayList<Employee> HE = new ArrayList<Employee>();
	public ArrayList<Client> Clients =new ArrayList<Client>();
	public ArrayList<Product> Pr=new ArrayList<Product>();
	//private double budget;
	public static int maxNbofAdmins=5;
=======
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

>>>>>>> 6c08e62de3bb1a74168a8d56bbd9dad99ed4550d
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
<<<<<<< HEAD
	
	public void PrintListOfEmployees() {
		System.out.println("\n***List Of Employees in this Company are***\n--------------\n");
		for(int i=0;i<HE.size();i++) {
			if(HE.get(i) instanceof HourlyEmployee)
				System.out.println(((HourlyEmployee)HE.get(i)));
			else
				System.out.println(((PartTimeEmployee)HE.get(i)));
		}
		System.out.println("--------------");
	}
	public void PrintListOfProducts() {
		System.out.println("\n***List Of Products are ***\n---------------\n");
		for(int i=0;i<Pr.size();i++) {
			if(Pr.get(i).getQty()!=0) {
				System.out.println(Pr.get(i));
			}
		}
		System.out.println("------------------");
	}
=======

	public boolean deleteProduct(Product p) {
		if (this.isProductExists(p)) {
			this.Products.remove(p);
			return true;
		}
		return false;
	}

>>>>>>> 6c08e62de3bb1a74168a8d56bbd9dad99ed4550d
}
