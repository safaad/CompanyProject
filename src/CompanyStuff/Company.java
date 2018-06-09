package CompanyStuff;

import java.util.ArrayList;

import Individuals.Client;
import Individuals.Employee;
import Individuals.HourlyEmployee;
import Individuals.PartTimeEmployee;
import Products.Product;

public class Company {
	public ArrayList<Employee> HE;
	public ArrayList<Client> Clients;
	public ArrayList<Product> Pr;
	public ArrayList<Employee> Admins;
	private double budget;
	public static int NbofAdmins = 0;

	public Company(double budget) {
		HE = new ArrayList<Employee>();
		Clients = new ArrayList<Client>();
		Pr = new ArrayList<Product>();
		Admins = new ArrayList<Employee>();
		this.budget = budget;
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
		System.out.println(
				"\n***List Of Employees in this Company are***\n==================================================\n");
		for (int i = 0; i < HE.size(); i++)
			if (HE.get(i) instanceof HourlyEmployee)
				System.out.println(((HourlyEmployee) HE.get(i)));
			else {
				if (HE.get(i) instanceof PartTimeEmployee)
					System.out.println(((PartTimeEmployee) HE.get(i)));
			}
		System.out.println("\n==================================================");
	}

	public void PrintListOfProducts() {
		System.out.println("\n***List Of Products are ***\n---------------\nProduct\tPrice\n");
		for (int i = 0; i < Pr.size(); i++)
			if (Pr.get(i).getQty() != 0)
				System.out.println(Pr.get(i));
		System.out.println("------------------");
	}

	public void PrintListOfClients() {
		System.out.println(
				"\n***List Of Clients in this Company are***\n==================================================\n");
		for (int i = 0; i < Clients.size(); i++)
			System.out.println(Clients.get(i));
		System.out.println("\n==================================================");
	}

	public void printListOfAdmins() {
		System.out.println(
				"\n***List Of Admins in this Company are***\n==================================================\n");
		for (int i = 0; i < Admins.size(); i++)
			System.out.println(Admins.get(i));
		System.out.println("\n==================================================");
	}

	public Employee getAdministrator(String username) {
		for (Employee e : Admins)
			if (e.getUsername().equals(username))
				return e;
		return null;
	}

	public void PrintListOfEmployeesAttendance() {
		System.out.println(
				"\n***List Of Employees in this Company are***\n==================================================\n");
		for (int i = 0; i < HE.size(); i++) {
			if (HE.get(i) instanceof HourlyEmployee) {
				System.out.println(((HourlyEmployee) HE.get(i)));
				HE.get(i).PrintAttendance();
			} else {
				if (HE.get(i) instanceof PartTimeEmployee) {
					System.out.println(((PartTimeEmployee) HE.get(i)));
					HE.get(i).PrintAttendance();
				}
			}
			System.out.println("\n==================================================");
		}
	}

	public boolean alreadyExistEmployee(String fname, String lname) {
		for (Employee e : HE) {
			if (fname.compareToIgnoreCase(e.getFirstName()) == 0 && lname.compareToIgnoreCase(e.getLastName()) == 0)
				return true;
		}
		return false;
	}

	public boolean alreadyExistsAdmin(String user) {
		for (Employee e : Admins)
			if (user.equals(e.getUsername()))
				return true;
		return false;
	}

	public boolean exists(String username) {
		for (Client c : Clients)
			if (c.getUsername().equals(username))
				return true;
		return false;
	}

	public Client getClient(String username) {
		for (Client c : Clients)
			if (c.getUsername().equals(username))
				return c;
		return null;
	}

}
