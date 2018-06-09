
package Drivers;

import java.util.InputMismatchException;
import java.util.Scanner;

import CompanyStuff.*;
import Individuals.*;

public class Driver {
	public static Scanner scan = new Scanner(System.in);
	public static Company Website = new Company(50000);
	public static files SP = new files();
	public static EmployeeDriver EmpD = new EmployeeDriver();
	public static CompanyDriver CmpD = new CompanyDriver();

	public static void AdminLogin() {
		int choice = 0;
		boolean login = false, repeat = true;
		boolean repeat1 = true;
		Employee e = null;
		System.out.println("** Admin's hub **");
		while (repeat) {

			System.out.println("You need to Login : ");
			e = CmpD.AdminSignIn();

			if (e == null) {
				System.out.println("Unsuccessful logging in !!");
				return;
			}
			login = true;

			if (login) {
				System.out.println("\n\nHello " + e.getUsername() + "!\n\t\t\t*** Menu ***\t\t\t\n");
				repeat1 = true;
				while (repeat1) {
					System.out.println("1-\tSet another Admin\n2-\tRemove Admin/Employee");
					System.out.println("3-\tGet Employee Of the month");
					System.out.println("4-\tGet list Employees" + "\n5-\tGet list of attendance of all employees");
					System.out.println("6-\tReset the salaries of the employees" + "\n7-\tGet list of products\n"
							+ "8-\tRemove a Product\n" + "9-\t Get list of clients");
					System.out.println("10-\tBack to menu" + "\n11-\tExit from Employee side");
					choice = scan.nextInt();
					switch (choice) {
					case 1:
						Website.PrintListOfEmployees();
						System.out.print("Enter the username of that Employee : ");
						String id = scan.nextLine();
						try {
							CmpD.setAdmin(id);
						} catch (AdminsException e1) {
							// TODO Auto-generated catch block
						}
						break;
					case 2:
						Website.PrintListOfEmployees();
						System.out.print("Enter the username of that Employee : ");
						id = scan.nextLine();
						CmpD.removeEmp(id);
						break;
					case 3:
						System.out.println("Our Employees Of the month are !!!");
						if (CmpD.getEmployeeOfMonthH() != null) {
							System.out.println("from the Hourly Employees : " + CmpD.getEmployeeOfMonthH());
						}
						if (CmpD.getEmployeeOfMonthP() != null) {
							System.out.println("from the PartTime Employees : " + CmpD.getEmployeeOfMonthP());
						}
						break;
					case 4:
						Website.PrintListOfEmployees();
					case 5:
						Website.PrintListOfEmployeesAttendance();
						break;
					case 6:
						// reset
						break;
					case 7:
						Website.PrintListOfProducts();
						break;
					case 8:
						Website.PrintListOfProducts();
						System.out.print("Enter the name of that product : ");
						id = scan.nextLine();
						CmpD.removeProduct(id);
						break;
					case 9:
						Website.PrintListOfClients();
						break;
					case 10:
						login = false;
						repeat1 = false;
						break;
					case 11:
						repeat1 = false;
						repeat = false;
						break;
					default:
						break;
					}
				}
			}
		}

	}

	public static void EmployeeLogin() {
		int choice = 0;
		boolean repeat = true, login = false;
		Employee e = null;
		boolean repeat1 = true;
		System.out.println("** Employee's hub **");
		while (repeat) {

			while (!login) {
				System.out.println("(1) Login\t(2) Register\t(3) exit");
				try {
					choice = scan.nextInt();
				} catch (InputMismatchException ex) {
					System.out.print("Incorrect choice reEnter : ");
					choice = scan.nextInt();
				}
				if (choice == 1) {
					e = EmpD.signIn();

					if (e == null)
						login = false;
					else
						login = true;
				} else {
					if (choice == 2) {
						e = EmpD.signUp();
						if (e == null)
							login = false;
						else
							login = true;
					} else {
						return;
					}
				}

			}

			if (login) {
				System.out.println("\n\nHello " + e.getUsername() + "!\n\t\t\t*** Menu ***\t\t\t\n");
				repeat1 = true;
				while (repeat1) {
					System.out.println("1-\tRegister In" + "\n2-\tRegister Out");
					System.out.println("3-\tChange password" + "\n4-\tGet extra payment");
					System.out.println("5-\tGet list attendance" + "\n6-\tGet list of extra attendance");
					System.out.println(
							"7-\tAdd Products to stock" + "\n8-\tGet username" + "\n9-\tshow list of products");
					System.out.println("10-\tBack to menu" + "\n11-\tExit from Employee side");
					choice = scan.nextInt();
					switch (choice) {
					case 1:
						EmpD.checkIn(e);
						break;
					case 2:
						EmpD.checkOut(e);
						break;

					case 3:
						if (!EmpD.verifyPassword(e)) {
							System.out.println("failure !!");
							break;
						}

						System.out.print("Enter new password: ");
						scan.nextLine();
						String s = scan.nextLine();
						e.setPassword(s);
					case 4:
						EmpD.showExtraPay(e);
						break;
					case 5:
						e.PrintAttendance();
					case 6:
						e.PrintAttendanceExtra();
						break;
					case 7:
						System.out.print("Enter the product Name and quantity: ");
						scan.nextLine();
						String n = scan.nextLine();
						int i = scan.nextInt();
						EmpD.addToStock(n, i);
						Website.getProduct(n).setItemName(n);
						System.out.println("Successfully added\n------------");
						Website.PrintListOfProducts();
						break;
					case 8:
						System.out
								.println("\n----------------\nUsername is: " + e.getUsername() + "\n----------------");
						break;
					case 9:
						Website.PrintListOfProducts();
						break;
					case 10:
						login = false;
						repeat1 = false;
						break;
					case 11:
						repeat1 = false;
						repeat = false;
						break;
					default:
						break;
					}
				}
			}
		}
	}

	public static void ClientLogin() {
		int choice;
		Client guest = null;
		ClientDriver CD = new ClientDriver();
		boolean loggedIn = false;
		System.out.println("(1) Login\t(2) Register");
		scan.nextLine();
		choice = scan.nextInt();
		while (!loggedIn) {
			if (choice == 1) {
				guest = CD.signIn();
				loggedIn = true;
			}
			if (choice == 2) {
				guest = CD.signUp();
				Website.Clients.add(guest);
				loggedIn = true;
			}
			if (choice != 1 && choice != 2)
				System.out.println("Your choice was none of the above!");
		}
		while (loggedIn) {
			CD.printMenu();
			choice = scan.nextInt();
			switch (choice) {
			case 0:
				loggedIn = false;
				break;
			case 1:
				CD.viewWishList(guest);
				break;

			}
		}
	}

	public static void main(String[] args) {
		System.out.println("** Hello and Welcome! **");
		int choice;
		boolean running = true;
		SP.read();
		//int a[] = { 12, 12, 1999 };
		//Employee admin = new Employee("admin", "admin", a);
		//admin.setPassword("admin");
		//admin.setAdminstartor();
		//Website.HE.add(admin);

		while (running) {
			System.out.println("**Login as a**\n\n(1) Client\t(2) Employee\n(3) Company Admin\t(0) to Terminate");
			choice = scan.nextInt();

			switch (choice) {
			case 0:
				running = false;
				break;
			case 1:
				ClientLogin();
				break;
			case 2:
				EmployeeLogin();
				break;
			case 3:
				AdminLogin();
				break;
			}
		}

		SP.save();
	}

}
