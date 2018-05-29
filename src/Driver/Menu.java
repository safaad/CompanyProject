package Driver;

import java.util.Scanner;
import Individuals.*;

public class Menu {
	public static Scanner scan = new Scanner(System.in);
	public static Company Website = new Company();
	
	public static void clientLogin() {

	}

	public static void adminLogin() {
		int choice = 0, a, type;
		boolean repeat = true, login = false;
		String fn, ln, shift;
		HourlyEmployee HE1 = null;
		PartTimeEmployee PTE1 = null;
		while (repeat) {
			System.out.println("** Admin's hub **");
			System.out.println("(1) Login\t(2) Register");
			// login loop
			while (choice != 1 && !login) {
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.println("Username:\t");
					fn = scan.nextLine();
					if (Website.exist(fn)) {
						if (fn.charAt(0) == '2') {
							type=2;
							PTE1 = Website.getPartTimeEmployee(fn);
							PTE1.confirmPassword();
						} else {
							type=1;
							HE1 = Website.getHourlyEmployee(fn);
							HE1.confirmPassword();
						}
					} else
						System.out.println("Wrong username or password.");
					login = true;
				} else if (choice == 2) {
					// new employee then add it to the arraylist
					System.out.println("Enter first name: ");
					fn = scan.nextLine();
					System.out.println("Enter last name: ");
					ln = scan.nextLine();
					System.out.println("age: ");
					a = scan.nextInt();
					System.out.println("(1) hourly or (2) part time ?");
					type = scan.nextInt();
					if (type == 1) {
						System.out.print("Enter the number of hours: ");
						int x = scan.nextInt();
						HE1 = new HourlyEmployee(fn, ln, a, x);
						if (!Website.exist(HE1.getUsername())) {
							HE1.setPassword();
							login = true;
							Website.HE.add(HE1);
						}
					}
					if (type == 2) {
						System.out.println("Enter your shift time (am/pm)");
						shift = scan.nextLine();
						PTE1 = new PartTimeEmployee(fn, ln, a, shift);
						if (!Website.exist(PTE1.getUsername())) {
							PTE1.setPassword();
							login = true;
							Website.HE.add(PTE1);
						}
					}
				}
			}
			// end of login loop
			System.out.println("1-Register In:\n2-Register Out\n3-Back to menu\n4-change password\n5-get extra payment\n6-get average worked hours\n7-exit");
			switch (choice) {
				
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("** Hello and Welcome! **");
		int choice;
		boolean running = true;

		while (running) {
			System.out.print("** Welcome **\nLogin as a client(1)/employee(2): ");
			choice = scan.nextInt();

			switch (choice) {
			case 0:
				running = false;
				break;
			case 1:
				clientLogin();
				break;
			case 2:
				adminLogin();
				break;
			case 3:

				break;
			}
		}
	}

}
