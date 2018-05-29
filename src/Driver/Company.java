package Driver;

import Individuals.*;
import java.util.ArrayList;

public class Company {
	protected ArrayList<Employee> HE = new ArrayList<Employee>();
	protected ArrayList<Client> Consumer = new ArrayList<Client>();
	protected boolean exist(String user) {
		for(int i = 0; i < HE.size(); i++)
			if(HE.get(i).getUsername().equals(user))
				return true;
		return false;
	}
	protected PartTimeEmployee getPartTimeEmployee(String user) {
		for(int i = 0; i < HE.size() ; i++)
			if(HE.get(i) instanceof PartTimeEmployee && HE.get(i).getUsername().equals(user))
				return (PartTimeEmployee) HE.get(i);
		return null;
	}
	
	protected HourlyEmployee getHourlyEmployee(String user) {
		for(int i = 0; i < HE.size() ; i++)
			if(HE.get(i) instanceof HourlyEmployee && HE.get(i).getUsername().equals(user))
				return (HourlyEmployee) HE.get(i);
		return null;
	}
	//Arraylist of products
}
