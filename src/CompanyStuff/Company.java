package CompanyStuff;


import java.util.ArrayList;

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
	public boolean exist(String user) {
		for(Employee e : HE)
			if(e.getUsername().equals(user))
				return true;
		return false;
	}
	
	public Employee getEmployee(String user) {
		for(Employee e : HE)
			if(e.getUsername().equals(user))
				return e;
		return null;
	}
	
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
}
