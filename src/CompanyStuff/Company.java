package CompanyStuff;


import java.util.ArrayList;

import Individuals.Employee;

public class Company {
	public ArrayList<Employee> HE = new ArrayList<Employee>();
	//protected ArrayList<Client> Consumer = new ArrayList<Client>();
	
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
	//Arraylist of products
}
