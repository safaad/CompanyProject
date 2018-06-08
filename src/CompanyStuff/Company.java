package CompanyStuff;

import java.util.ArrayList;

import Individuals.Client;
import Individuals.Employee;
import Individuals.HourlyEmployee;
import Individuals.PartTimeEmployee;
import Products.Product;

public class Company {
	public ArrayList<Employee> HE ;
	public ArrayList<Client> Clients;
	public ArrayList<Product> Pr;
	public ArrayList<Employee> Admins;
	private double budget;
	public static int NbofAdmins = 0;
	public Company(double budget) {
		 HE = new ArrayList<Employee>();
		 Clients = new ArrayList<Client>();
		 Pr = new ArrayList<Product>();
		 Admins=new ArrayList<Employee>();
		 this.budget=budget;
	}
	public boolean exist(String user) {
		for (Employee e : HE)
			if (e.getUsername().equals(user))
				return true;
		return false;
	}

	public boolean existProduct(String itemName) {
		for (Product p : Pr)
			if (p.getItemName().equals(itemName))
				return true;
		return false;
	}

	public Product getProduct(String itemName) {
		for (Product p : Pr)
			if (p.getItemName().equals(itemName))
				return p;
		return null;
	}

	public Employee getEmployee(String user) {
		for (Employee e : HE)
			if (e.getUsername().equals(user))
				return e;
		return null;
	}

	public void PrintListOfEmployees() {
		System.out.println("\n***List Of Employees in this Company are***\n--------------\n");
		for (int i = 0; i < HE.size(); i++)
			if (HE.get(i) instanceof HourlyEmployee)
				System.out.println(((HourlyEmployee) HE.get(i)));
			else
				System.out.println(((PartTimeEmployee) HE.get(i)));
		System.out.println("--------------");
	}

	public void PrintListOfProducts() {
		System.out.println("\n***List Of Products are ***\n---------------\nProduct\tPrice\n");
		for (int i = 0; i < Pr.size(); i++)
			if (Pr.get(i).getQty() != 0)
				System.out.println(Pr.get(i));
		System.out.println("------------------");
	}
}
