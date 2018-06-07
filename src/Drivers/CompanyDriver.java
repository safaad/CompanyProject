package Drivers;

import java.util.Scanner;

import CompanyStuff.Company;
import Individuals.Employee;
import Individuals.HourlyEmployee;
import Individuals.PartTimeEmployee;

public class CompanyDriver {
	Scanner scan = new Scanner(System.in);
	int choice = 0, type = 0;
	int[] a = new int[3];
	public boolean repeat = true, login = false;
	public String fn, ln, shift;
	public HourlyEmployee HE1 = null;
	public PartTimeEmployee PTE1 = null;
	public Employee e;
	public boolean repeat1 = true;

	Employee AdminSignIn() {
		EmployeeDriver d=new EmployeeDriver();
		Employee e=d.signIn();
		if(e) {
			if(e.getAdmin())
				return e;
			System.out.println("Not an admin");
		}
		return null;
	}
	
	

	public void setAdmin(String username) throws AdminsException {
		try {
			if (Company.NbofAdmins == 5)
				throw new AdminsException();
			if (!Driver.Website.exist(username)) {
				System.out.println("You aren't even an Employee you need to sign up");

			} else {
				Driver.Website.getEmployee(username).setAdminstartor();
				Driver.Website.Admins.add(Driver.Website.getEmployee(username));
			}
		} catch (AdminsException e) {
			System.out.println("NO More places");
			// go back to the menu
		}
		// an admin can enter here he can add another admin hhe has the accessibilty to
		// see employees clients and orders and products hik btzakar till now
	}

}
