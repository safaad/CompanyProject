package Drivers;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import Individuals.Employee;
import Individuals.HourlyEmployee;
import Individuals.PartTimeEmployee;
import Products.Product;

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

	public boolean verifyPassword(Employee e) {
		int i = 4;
		String s;
		do {
			System.out.print("Enter password : (" + i + " remaining attempts): ");
			s = scan.nextLine();
			i--;
		} while (i > 0 && !e.confirmPassword(s));
		if (!e.confirmPassword(s))
			return false;
		return true;

	}

	Employee signIn() {
		System.out.print("Username: ");
		fn = scan.nextLine();
		e = Driver.Website.getEmployee(fn);
		if (e != null) {
			if (e instanceof PartTimeEmployee) {
				type = 2;
				PTE1 = (PartTimeEmployee) e;

				if (!verifyPassword(PTE1))
					e = null;
			} else {
				if (e instanceof HourlyEmployee) {
					type = 1;
					HE1 = (HourlyEmployee) e;
					if (!verifyPassword(HE1))
						e = null;

				}
			}
		}

		else
			System.out.println("Wrong username or password!");
		return e;
	}

	Employee signUp() {
		System.out.print("Enter first name: ");
		fn = scan.next();
		System.out.print("Enter last name: ");
		ln = scan.next();
		if (Driver.Website.alreadyExistEmployee(fn, ln)) {
			System.out.println("you are already an employee");
			return null;
		}
		String[] b;
		scan.nextLine();
		do {
			System.out.print("Birthdate (dd/mm/yyyy): ");

			String birth = scan.nextLine();
			try {
				b = birth.split("/");
			} catch (PatternSyntaxException e) {
				b = null;
			}
		} while (b.length != 3 || !b[0].matches("-?\\d+(\\.\\d+)?")
				|| (Integer.parseInt(b[0]) > 31 || Integer.parseInt(b[0]) <= 0) || !b[1].matches("-?\\d+(\\.\\d+)?")
				|| (Integer.parseInt(b[1]) > 12 || Integer.parseInt(b[1]) <= 0) || !b[2].matches("-?\\d+(\\.\\d+)?"));
		a[0] = Integer.parseInt(b[0]);
		a[1] = Integer.parseInt(b[1]);
		a[2] = Integer.parseInt(b[2]);

		do {
			try {
				System.out.print("(1) Hourly or (2) Part time ? ");
				type = scan.nextInt();
			} catch (InputMismatchException ex) {
				scan.nextLine();
				type = -1;
			}
		} while (type != 1 && type != 2);

		if (type == 1) {
			System.out.print("Enter the number of hours: ");
			int x = scan.nextInt();

			HE1 = new HourlyEmployee(fn, ln, a, x);
			System.out.print("Enter new password: ");
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
			System.out.print("Enter your shift time (am/pm): ");
			do {
				scan.nextLine();
				shift = scan.nextLine();
			} while (shift.compareToIgnoreCase("am") != 0 && shift.compareToIgnoreCase("pm") != 0);

			PTE1 = new PartTimeEmployee(fn, ln, a, shift);
			System.out.print("Enter new password: ");
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
	}

	public void checkOut(Employee emp) {

		if (emp instanceof HourlyEmployee) {
			HE1 = (HourlyEmployee) emp;
			HE1.registerOut();
		} else {
			PTE1 = (PartTimeEmployee) emp;
			PTE1.registerOut();
		}
		
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

	public void addToStock(String itemName, int qt) {
		Product p;
		if (Driver.Website.existProduct(itemName)) {
			Driver.Website.getProduct(itemName).setQty(qt + Driver.Website.getProduct(itemName).getQty());
		} else {
			System.out.print("Enter the product's price: ");
			float price = scan.nextFloat();
			System.out.print("Enter the product's netPrice: ");
			float netp = scan.nextFloat();
			p = new Product(itemName, price, netp, qt);
			Driver.Website.Pr.add(p);
			System.out.println(p);
		}
	}
}
