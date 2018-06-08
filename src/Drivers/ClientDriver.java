package Drivers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import Individuals.Client;
import Products.Product;
	
public class ClientDriver {
	static ArrayList<Client> Clients = new ArrayList<Client>();
	Scanner scan = new Scanner(System.in);
	
	void printMenu() {
		System.out.println("** Hello **");
		System.out.println("1)\tView Wish list");
		System.out.println("2)\tTrack your delivery");
		System.out.println("3)\tView current Cart");
		System.out.println("0)\tLog out");
	}
	void viewWishList(Client c) {
		
		for(Product p : c.WishList) System.out.println(p);
	}
	
	void viewCart() {
		
	}
	
	void trackDelivery() {
		
	}
	
<<<<<<< HEAD
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

=======
	Client signIn() {
		String username, password;
		Client guest;
		System.out.print("Username: ");
		username = scan.nextLine();
		System.out.print("Password: ");
		password = scan.nextLine();
		guest = ClientDriver.getClient(username);
		if (guest == null || !guest.verifyPassword(password)) {
			System.out.println("Wrong username or password!");
			return null;
		}
		System.out.println("Hello " + guest.getUsername() + "!");
		return guest;
	}
>>>>>>> ab45f91305b06d0d72e4e05d317cce52888fec97
	
	Client signUp() {
		int choice;
		String username, password;
		Client guest;
		String fn, ln, pass = null, b[] = new String [3];
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
			if(pass != null) System.out.println("\n------------\nPassword missmatch please type your password again!\n------------\n");
			System.out.print("Password: ");
			password = scan.nextLine();
			System.out.print("Verify Password: ");
			pass = scan.nextLine();
		}while(!pass.equals(password));
		guest = new Client(fn, ln, a, username, password);
		return guest;
	}
	
	public static boolean exists(String username) {
		for(Client c : Clients)
			if(c.getUsername().equals(username))
				return true;
		return false;
	}
	public static Client getClient(String username) {
		for(Client c : Clients)
			if(c.getUsername().equals(username))
				return c;
		return null;
	}
}
