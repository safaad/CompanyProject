package Drivers;

import java.util.InputMismatchException;
import java.util.Scanner;

import CompanyStuff.*;
import Individuals.*;
import Products.Transaction;

public class Driver {
	public static Scanner scan = new Scanner(System.in);
	public static Company Website = new Company(50000);
	public static files SP = new files();
	public static EmployeeDriver EmpD = new EmployeeDriver();
	public static CompanyDriver CmpD = new CompanyDriver();
	public static ClientDriver CD = new ClientDriver();
	private static boolean Gflag = false;

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
					System.out.println("1-\tShow List Of Admins\n2-\tSet another Admin\n3-\tRemove Admin/Employee");
					System.out.println("4-\tGet Employee Of the month");
					System.out.println("5-\tGet list Employees" + "\n6-\tGet list of attendance of all employees");
					System.out.println("7-\tReset the salaries of the employees" + "\n8-\tGet list of products\n"
							+ "9-\tRemove a Product\n" + "10-\tGet list of clients\n11-\tPrint all Transactions\n12-\tShow total money in this company");
					System.out.println("13-\tBack to menu" + "\n14-\tExit from Admin side");
					try {
						choice = scan.nextInt();
					} catch (InputMismatchException ex) {
						System.out.print("Incorrect choice reEnter : ");
						scan.nextLine();
						choice = scan.nextInt();
					}
					switch (choice) {
					case 1:
						Website.printListOfAdmins();
						break;
					case 2:
						Website.PrintListOfEmployees();
						System.out.print("Enter the username of that Employee : ");
						scan.nextLine();
						String id = scan.nextLine();
						try {
							CmpD.setAdmin(id);
						} catch (AdminsException e1) {
							break;
						}
						break;
					case 3:
						System.out.println("What do you want to remove? (Admin/Emp)");
						scan.nextLine();
						String remove = scan.nextLine();
						if (remove.equalsIgnoreCase("emp")) {
							if (Website.HE.size() != 0) {
								Website.PrintListOfEmployees();
								System.out.print("Enter the username of that Employee : ");
								id = scan.nextLine();
								CmpD.removeEmp(id);
							}
						} else if (remove.equalsIgnoreCase("admin")) {
							if (Website.Admins.size() != 0) {
								Website.printListOfAdmins();
								System.out.print("Enter the username of that Admin : ");
								id = scan.nextLine();
								CmpD.removeAdmin(id);
							}
						} else
							System.out.println("Invalid choice!");
						break;
					case 4:
						System.out.println("Our Employees Of the month are !!!");
						if (CmpD.getEmployeeOfMonthH() != null) {
							System.out.println("from the Hourly Employees : " + CmpD.getEmployeeOfMonthH());
						}
						if (CmpD.getEmployeeOfMonthP() != null) {
							System.out.println("from the PartTime Employees : " + CmpD.getEmployeeOfMonthP());
						}
						break;
					case 5:
						Website.PrintListOfEmployees();
						break;
					case 6:
						Website.PrintListOfEmployeesAttendance();
						break;
					case 7:
						CmpD.resetSalaries();
						break;
					case 8:
						Website.PrintListOfProducts();
						break;
					case 9:
						Website.PrintListOfProducts();
						System.out.print("Enter the name of that product : ");
						scan.nextLine();
						id = scan.nextLine();
						CmpD.removeProduct(id);
						break;
					case 10:
						Website.PrintListOfClients();
						break;
					case 11:
						Website.printListOfTransactions();
						break;
					case 12:
						System.out.println("--Total money: " + Website.getMoney() + "$--\n");
						break;
					case 13:
						repeat1 = false;
						break;
					case 14:
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
					try {
						choice = scan.nextInt();
					} catch (InputMismatchException ex) {
						System.out.print("Incorrect choice reEnter : ");
						scan.nextLine();
						choice = scan.nextInt();
					}
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
						break;
					case 4:
						EmpD.showExtraPay(e);
						break;
					case 5:
						e.PrintAttendance();
						break;
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
		int choice = 2, prQty;
		Client guest = null;
		String prName;
		boolean loggedIn = false;
		if (!Gflag) {
			System.out.println("(1) Login\t(2) Register\t(0) Exit");
			scan.nextLine();
			try {
				choice = scan.nextInt();
			} catch (InputMismatchException ex) {
				System.out.print("Incorrect choice reEnter : ");
				scan.nextLine();
				choice = scan.nextInt();
			}
		}
		while (!loggedIn) {
			if (choice == 1) {
				guest = CD.signIn();
				loggedIn = (guest != null);
				if (loggedIn == false)
					return;
			}
			else if (choice == 2) {
				guest = CD.signUp();
				Website.Clients.add(guest);
				loggedIn = true;
			}
			else if(choice == 0)
				return;
			else {
				System.out.println("Your choice was none of the above!");
				return ;
			}
		}
		Gflag = false;
		while (loggedIn) {
			CD.printMenu();
			try {
				choice = scan.nextInt();
			}catch(InputMismatchException e9) {
				System.out.println("Invalid choice! Enter another input");
				scan.nextLine();
				try {
					choice = scan.nextInt();
				}catch(InputMismatchException e8) {
					System.out.println("Invalid choice!");
					scan.nextLine();
					return;
				}
			}
			switch (choice) {
			case 0:
				if(!guest.getCart().isEmpty()) {
					System.out.println("You're leaving with products in your cart! If you continue you will lose them!");
					System.out.println("Continue? (Yes/No)");
					scan.nextLine();
					String lose = scan.nextLine();
					if(lose.equalsIgnoreCase("no")) {
						break;
					}
					else if(lose.equalsIgnoreCase("yes")) {
						guest.clearCart();
					}
					else {
						System.out.println("Wrong choice!");
						break;
					}
				}
				loggedIn = false;
				break;
			case 1:
				Website.PrintListOfProducts();
				break;
			case 2:
				Website.PrintListOfProducts();
				System.out.print("Enter product name: ");
				scan.nextLine();
				prName = scan.nextLine();
				System.out.print("How much of " + prName + " do you want to purchase?\nWe currently have "
						+ Driver.Website.getProduct(prName).getQty() + "\nQuantity: ");
				prQty = scan.nextInt();
				guest.order(prName, prQty);
				// CD.viewCart(guest);
				break;
			case 3:
				guest.viewCart();
				System.out.println("Enter product name: ");
				scan.nextLine();
				prName = scan.nextLine();
				System.out.println("How much of " + prName + " do you want to remove?");
				prQty = scan.nextInt();
				guest.removeOrder(prName, prQty);
				break;
			case 4:
				guest.clearCart();
				break;
			case 5:
				System.out.println("Enter product name: ");
				scan.nextLine();
				prName = scan.nextLine();
				guest.addToWishList(prName);
				break;
			case 6:
				guest.viewWishList();
				break;
			case 7:
				System.out.print("Enter item name: ");
				scan.nextLine();
				prName = scan.nextLine();
				guest.removeFromWishList(prName);
				break;
			case 8:
				guest.refresh();
				guest.viewCart();
				break;
			case 9:
				if (guest.getCart().size() == 0) {
					System.out.println("Cart is empty!");
					break;
				}
				Transaction t = new Transaction(guest, guest.getCart());
				Website.setMoney(t.getTotalMoney() + Website.getMoney());
				Website.Transactions.add(t);
				System.out.println(t);
				guest.getCart().clear();
				//guest.clearCart();
				break;
			case 10:
				Website.printTran(guest);
				break;
			case 11:
				if (CD.removeAcc(guest)) {
					System.out.println("Successfully removed account!");
					return;
				}
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		}
	}

	private static void GuestLogin() {
		int choice;
		boolean guest = true;
		while (guest) {
			System.out.println("** Menu **\n1)\tView our products\n2)\tRegister\n0)\tQuit");
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				Website.PrintListOfProducts();
				break;
			case 2:
				Gflag = true;
				ClientLogin();
				guest = false;
				break;
			case 0:
				guest = false;
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
		while (running) {
			System.out.println("\nSign in as a: \n");
			System.out.println("(1) Guest\t(2) Client");
			System.out.println("(3) Employee\t(4) Company Admin");
			System.out.println("\n(0) Terminate");
			// System.out.println("Login as a:\n\n(1) Client\t(2) Employee\n(3) Company
			// Admin\t(4) Guest\t(0) to Terminate");
			try {
				choice = scan.nextInt();
			} catch (InputMismatchException ex) {
				System.out.print("Incorrect choice reEnter : ");
				scan.nextLine();
				choice = scan.nextInt();
			}
			switch (choice) {
			case 0:
				running = false;
				break;
			case 1:
				GuestLogin();
				break;
			case 2:
				ClientLogin();
				break;
			case 3:
				EmployeeLogin();
				break;
			case 4:
				AdminLogin();
				break;
			}
		}
		SP.save();
	}

}