package Drivers;

import java.util.Scanner;

import Individuals.Employee;
import Individuals.HourlyEmployee;
import Individuals.PartTimeEmployee;

public class EmployeeDriver {
	Scanner scan = new Scanner(System.in);
	int choice = 0, type = 0;
	int[] a = new int[3];
	public boolean repeat = true, login = false;
	public String fn, ln, shift;
	public HourlyEmployee HE1 = null;
	public PartTimeEmployee PTE1 = null;
	public Employee e;
	public boolean repeat1 = true;

	Employee signIn() {
		System.out.println("Username:\t");
		fn = scan.nextLine();
		e = Driver.Website.getEmployee(fn);
		if (e != null) {
			if (e instanceof PartTimeEmployee) {
				type = 2;
				PTE1 = (PartTimeEmployee) e;
				System.out.println("Enter password");
				String s = scan.nextLine();
				if (!PTE1.confirmPassword(s))
					e = null;
			} else {
				type = 1;
				HE1 = (HourlyEmployee) e;
				System.out.println("Enter password");
				String s = scan.nextLine();
				if (!HE1.confirmPassword(s))
					e = null;
			}
		} else
			System.out.println("Wrong username or password.");
		return e;
	}

	Employee signUp() {
		System.out.println("Enter first name: ");
		fn = scan.next();
		System.out.println("Enter last name: ");
		ln = scan.next();
		/*
		 * String user = "" + fn.charAt(0) + fn.charAt(1) + fn.charAt(2) + "_" +
		 * ln.charAt(0) + ln.charAt(1) + ln.charAt(2); if ((e =
		 * Driver.Website.getEmployee(user)) == null) return null;
		 */
		System.out.println("Birthdate :(dd/mm/yyyy) ");
		for (int i = 0; i < 3; i++)
			a[i] = scan.nextInt();
		System.out.println("(1) hourly or (2) part time ?");
		type = scan.nextInt();
		if (type == 1) {
			System.out.print("Enter the number of hours: ");
			int x = scan.nextInt();

			HE1 = new HourlyEmployee(fn, ln, a, x);
			System.out.println("Enter new password");
			scan.nextLine();
			String s = scan.nextLine();

			if (!Driver.Website.exist(HE1.getUsername())) {

				HE1.setPassword(s);

				login = true;
				e = HE1;
				Driver.Website.HE.add(HE1);
			}
		}
		if (type == 2) {
			System.out.println("Enter your shift time (am/pm)");
			scan.nextLine();
			shift = scan.nextLine();
			if (!shift.equals("am") && !shift.equals("AM") && !shift.equals("pm") && !shift.equals("PM")) {
				return null;
			}
					
			PTE1 = new PartTimeEmployee(fn, ln, a, shift);
			System.out.println("Enter new password");

			scan.nextLine();
			String s = scan.nextLine();
			if (!Driver.Website.exist(PTE1.getUsername())) {

				PTE1.setPassword(s);
				login = true;
				Driver.Website.HE.add(PTE1);
				e = PTE1;
			}

		}
		return e;
	}

	public void checkIn(Employee emp) {

		if (emp instanceof HourlyEmployee) {
			HE1 = (HourlyEmployee) emp;
			HE1.registerIn();
		} else {
			PTE1 = (PartTimeEmployee) emp;
			PTE1.registerIn();
		}
		System.out.println("You successfully signed in\n");
	}

	public void checkOut(Employee emp) {

		if (emp instanceof HourlyEmployee) {
			HE1 = (HourlyEmployee) emp;
			HE1.registerOut();
		} else {
			PTE1 = (PartTimeEmployee) emp;
			PTE1.registerOut();
		}
		System.out.println("You successfully signed out \n");
	}

	public void showExtraPay(Employee emp) {
		System.out.print("You will get an extra payment of : ");
		if (emp instanceof HourlyEmployee) {
			HE1 = (HourlyEmployee) emp;
			System.out.println(HE1.getExtraPay() + " $");
		} else {
			PTE1 = (PartTimeEmployee) emp;
			PTE1.registerIn();
			System.out.println(PTE1.getExtraPay() + " $");
		}
	}

}
