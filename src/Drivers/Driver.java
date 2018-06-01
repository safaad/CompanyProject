
package Drivers;

import java.util.Scanner;

import CompanyStuff.Company;
import Individuals.*;

public class Driver {
	public static Scanner scan = new Scanner(System.in);
	public static Company Website = new Company();

	public static void clientLogin() {

	}

	public static void adminLogin() {
		int choice = 0, type = 0;
		int[] a = new int[3];
		boolean repeat = true, login = false;
		String fn, ln, shift;
		HourlyEmployee HE1 = null;
		PartTimeEmployee PTE1 = null;
		Employee e;
		boolean repeat1=true;
		System.out.println("** Admin's hub **");
		while (repeat) {
		
			
			while (!login) {
				System.out.println("(1) Login\t(2) Register");
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.println("Username:\t");
					fn = scan.nextLine();
					e=Website.getEmployee(fn);
					if (e!=null) {
						if (e instanceof PartTimeEmployee) {
							type=2;
							PTE1 = (PartTimeEmployee) e;
							System.out.println("Enter password");
							String s=scan.nextLine();
							if(PTE1.confirmPassword(s))
								login=true;
						} else {
							type=1;
							HE1 =(HourlyEmployee) e;
							System.out.println("Enter password");
							String s=scan.nextLine();
							if(HE1.confirmPassword(s))
								login=true;
						}
					} else
						System.out.println("Wrong username or password.");
				} else if (choice == 2) {
					// new employee then add it to the arraylist
					System.out.println("Enter first name: ");
					fn = scan.next();
					System.out.println("Enter last name: ");
					ln = scan.next();
					System.out.println("Birthdate :(dd/mm/yyyy) ");
					for(int i=0;i<3;i++)
						a[i]=scan.nextInt();
					System.out.println("(1) hourly or (2) part time ?");
					type = scan.nextInt();
					if (type == 1) {
						System.out.print("Enter the number of hours: ");
						int x = scan.nextInt();
						System.out.println("Enter new password");
						String s=scan.nextLine();
						HE1 = new HourlyEmployee(fn, ln, a, x);
						if (!Website.exist(HE1.getUsername())) {
							System.out.println("set Password");
							HE1.setPassword(s);
							login = true;
							Website.HE.add(HE1);
						}
					}
					if (type == 2) {
						System.out.println("Enter your shift time (am/pm)");
						shift = scan.nextLine();
						PTE1 = new PartTimeEmployee(fn, ln, a, shift);
						System.out.println("Enter new password");
						String s=scan.nextLine();
						if (!Website.exist(PTE1.getUsername())) {
							PTE1.setPassword(s);
							login = true;
							Website.HE.add(PTE1);
						}
					}
				}
			}
			// end of login loop
			while(repeat1){
				System.out.println("1-Register In:\n2-Register Out");
				System.out.println("3-Back to menu\\n4-change password\\n5-get extra payment\n6-get list attendance\n7-get list of extra attendance\n8-Back to menu\n9-Exit from Employee side");
				choice=scan.nextInt();
			switch (choice) {
			case 1:
					if(type==1)
						HE1.registerIn();
					else
						PTE1.registerIn();
					break;
			case 2:
				if(type==1)
					HE1.registerOut();
				else
					PTE1.registerOut();
				break;
			case 3:
				repeat1=false;
				break;
			case 4:	
				System.out.println("Enter new password");
				String s=scan.nextLine();
				if(type==1)
					HE1.setPassword(s);
				else
					PTE1.setPassword(s);
				break;
			case 5:
				if(type==1)
					System.out.println(HE1.getExtraPay());
				else
					System.out.println(PTE1.getExtraPay());
				break;
			case 6:
				if(type==1)
					HE1.PrintAttendance();
				else
					PTE1.PrintAttendance();
				break;
			case 7:
				if(type==1)
					HE1.PrintAttendanceExtra();
				else
					PTE1.PrintAttendanceExtra();
				break;
			case 8:
				repeat1=false;
				break;
			case 9:
				repeat1=false;
				repeat=false;
			}
		}
			}
	}

	public static void main(String[] args) {
		System.out.println("** Hello and Welcome! **");
		int choice;
		//EmployeeDriver ED = new EmployeeDriver();
		boolean running = true;

		while (running) {
			System.out.print("Login as a\n(1) Client\t(2) Employee\n(3) Company Admin");
			choice = scan.nextInt();

			switch (choice) {
			case 0:
				running = false;
				break;
			case 1:
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
