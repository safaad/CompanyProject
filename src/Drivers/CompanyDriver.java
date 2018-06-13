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
		System.out.print("Username: ");
		fn = scan.nextLine();
		Employee e = Driver.Website.getAdministrator(fn);
		if (e != null) {
			if (!Driver.EmpD.verifyPassword(e)) {
				System.out.println("Wrong username or password");
				return null;

			}
		} else {
			System.out.println("you are not admistrator in this company");

		}
		return e;
	}

	public void removeEmp(String username) {
		if (Driver.Website.getEmployee(username) != null) {
			Driver.Website.HE.remove(Driver.Website.getEmployee(username));
			Driver.SP.save();
			System.out.println("Successfully removed !!");
			return;
		}
		System.out.println("this employee don't even exist!!");

	}

	public void removeProduct(String itemName) {
		if (Driver.Website.getProduct(itemName) != null) {
			Driver.Website.Pr.remove(Driver.Website.getProduct(itemName));
			Driver.SP.save();
			System.out.println("Successfully removed !!");
			return;
		}
		System.out.println("this product don't even exist !!");
	}

	public void resetSalaries() {
		for (Employee e : Driver.Website.HE) {
			if (e instanceof HourlyEmployee)
				((HourlyEmployee) e).setSalary();
			else if (e instanceof PartTimeEmployee)
				((PartTimeEmployee) e).setSalary();
		}

	}

	public void setAdmin(String username) throws AdminsException {
		if (Driver.Website.alreadyExistsAdmin(username)) {
			System.out.println("he is already an admin");
			return;
		}

		try {
			if (Company.NbofAdmins == 5)
				throw new AdminsException();
			if (!Driver.Website.exist(username)) {
				System.out.println("You aren't even an Employee you need to sign up");

			} else {
				Driver.Website.getEmployee(username).setAdminstartor();
			}
		} catch (AdminsException e) {
			System.out.println("NO MORE PLACES !!");
			// go back to the menu
		}

	}

	public HourlyEmployee getEmployeeOfMonthH() {
		int min = 100000;
		HourlyEmployee he = null;

		for (int i = 0; i < Driver.Website.HE.size(); i++) {
			Employee e = Driver.Website.HE.get(i);
			if (e instanceof HourlyEmployee) {
				if (min < ((HourlyEmployee) e).getTotalNbOfWorkedHours()) {
					min = ((HourlyEmployee) e).getTotalNbOfWorkedHours();
					he = ((HourlyEmployee) e);
				}
			}
		}
		return he;
	}

	public PartTimeEmployee getEmployeeOfMonthP() {
		int min = 100000;
		PartTimeEmployee pe = null;

		for (int i = 0; i < Driver.Website.HE.size(); i++) {
			Employee e = Driver.Website.HE.get(i);
			if (e instanceof PartTimeEmployee) {
				if (min < ((PartTimeEmployee) e).getNbofShifts()) {
					min = ((PartTimeEmployee) e).getNbofShifts();
					pe = ((PartTimeEmployee) e);
				}
			}
		}
		return pe;
	}
}
