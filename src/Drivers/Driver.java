package Drivers;

import java.util.InputMismatchException;
import java.util.Scanner;

import CompanyStuff.Company;
import CompanyStuff.savePerson;
import Individuals.*;

public class Driver {
	public static Scanner scan = new Scanner(System.in);
	public static Company Website = new Company();
	public static savePerson SP = new savePerson();

	public static void clientLogin() {

	}

	public static void EmployeeLogin() {
		int choice = 0, type = 0;
		int[] a = new int[3];
		boolean repeat = true, login = false;
		EmployeeDriver EmpD = new EmployeeDriver();
		HourlyEmployee HE1 = null;
		PartTimeEmployee PTE1 = null;
		Employee e = null;
		boolean repeat1 = true;

		System.out.println("** Admin's hub **");
		while (repeat) {
			tryAgain: while (!login) {
				System.out.println("(1) Login\t(2) Register\n");
				try {
					choice = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Wrong input\n");
					scan.nextLine();
					continue tryAgain;
				}
				if (choice == 1) {
					e = EmpD.signIn();

					if (e == null)
						login = false;
					else
						login = true;
				} else {
					e = EmpD.signUp();
					if (e == null)
						login = false;
					else
						login = true;
				}
			}
			if (e == null)
				System.out.println("hi1");
			if (login) {
				// log that he logged in?
				repeat1 = true;
				while (repeat1) {
					System.out.println("1-Register In:\n2-Register Out");
					System.out.println(
							"3-change password\n4-get extra payment\n5-get list attendance\n6-get list of extra attendance");
					System.out.println("7-get username\n8-Back to menu\n9-Exit from Employee side\n");
					choice = scan.nextInt();
					switch (choice) {
					case 1:
						EmpD.checkIn(e);
						break;
					case 2:
						EmpD.checkOut(e);
						break;

					case 3:
						System.out.println("Enter new password: ");
						scan.nextLine();
						String s = scan.nextLine();
						e.setPassword(s);
						break;
					case 4:
						EmpD.showExtraPay(e);
						break;
					case 5:
						e.PrintAttendance();
					case 6:
						e.PrintAttendanceExtra();
						break;
					case 7:
						System.out.println(
								"\n----------------\n" + "username is: " + e.getUsername() + "\n----------------\n");
						break;
					case 8:
						login = false;
						repeat1 = false;
						break;
					case 9:
						repeat1 = false;
						repeat = false;
						break;
					case 10:

						break;
					case 11:

						break;
					default:
						break;
					}
				}
			}
			// log that he logged off
		}
	}

	public static void main(String[] args) {
		System.out.println("** Hello and Welcome! **");
		int choice;
		// EmployeeDriver ED = new EmployeeDriver();
		boolean running = true;
		SP.read(); // File reading
		while (running) {
			System.out.println("Login as a\n(1) Client\t(2) Employee\n(3) Company Admin\n(0)To terminate program");
			choice = scan.nextInt();

			switch (choice) {
			case 0:
				running = false; // Termination then save all to files
				break;
			case 1:

				break;
			case 2:
				EmployeeLogin();
				break;
			case 3:

				break;
			}
		}
		SP.save(); // File saving
	}

}
