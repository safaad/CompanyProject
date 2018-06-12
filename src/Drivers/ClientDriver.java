package Drivers;

import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import Individuals.Client;
import Products.Product;

public class ClientDriver {
	
	Scanner scan = new Scanner(System.in);
	
	
	void printMenu() {
		System.out.println("** Menu **");
		System.out.println("1)\tView our products");
		System.out.println("2)\tAdd a product to your cart");
		System.out.println("3)\tRemove a product from your cart");
		System.out.println("4)\tClear cart");
		System.out.println("5)\tAdd to wish list");
		System.out.println("6)\tView Wish list");
		System.out.println("7)\tRemove product from your wish list");
		System.out.println("8)\tView current Cart");
		System.out.println("9)\tRequest delivery");
		System.out.println("10)\tTrack your delivery");
		System.out.println("0)\tLog out");
	}

	void viewWishList(Client c) {
		for (Product p : c.WishList)
			System.out.println(p);
	}

	void trackDelivery() {

	}

	Client signIn() {
		String username, password;
		Client guest;
		System.out.print("Username: ");
		username = scan.nextLine();
		System.out.print("Password: ");
		password = scan.nextLine();
		guest = Driver.Website.getClient(username);
		if (guest == null || !guest.verifyPassword(password)) {
			System.out.println("Wrong username or password!");
			return null;
		}
		System.out.println("Hello " + guest.getUsername() + "!");
		return guest;
	}

	Client signUp() {
		String username, password;
		Client guest;
		String fn, ln, pass = null, b[] = new String[3];
		int a[] = new int[3];
		System.out.print("Enter first name: ");
		fn = scan.nextLine();
		System.out.print("Enter last name: ");
		ln = scan.nextLine();
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
		System.out.print("Username: ");
		username = scan.nextLine();
		do {
			if (pass != null)
				System.out
						.println("\n------------\nPassword missmatch please type your password again!\n------------\n");
			System.out.print("Password: ");
			password = scan.nextLine();
			System.out.print("Verify Password: ");
			pass = scan.nextLine();
		} while (!pass.equals(password));
		guest = new Client(fn, ln, a, username, password);
		return guest;
	}
	
	public void viewCart(Client c) {
		c.viewCart();
	}
	
}