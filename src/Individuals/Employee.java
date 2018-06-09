package Individuals;

import java.util.Scanner;

import CompanyStuff.Company;
import Drivers.Driver;

import java.util.ArrayList;

public  class Employee extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Scanner in;
	protected double salary = 1000;
	protected String username, password;
	protected boolean admin = false;
	protected ArrayList<Boolean> attend, attendExtra;

	public Employee(String first, String last, int[] birthday) {
		super(first, last, birthday);
		try{username = "" +fn.substring(0,3) + "_" + ln.substring(0,3);
		}catch(StringIndexOutOfBoundsException e) {
			username=fn+"-"+ln;
		}
		attend = new ArrayList<Boolean>();
		attendExtra = new ArrayList<Boolean>();
	}

	public void setAdminstartor() {
		admin = true;
		Company.NbofAdmins++;
		Driver.Website.Admins.add(this);
		salary += 100;
	}

	public boolean confirmPassword(String pass) {
		return pass.equals(password);
	}

	public void setPassword(String pass) {
		password = pass;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void PrintAttendance() {
		int i;
		for (i = 0; i < attend.size(); i++)
			System.out.print("Day " + i + 1 + " : " + attend.get(i) + "  ||  ");
	}

	public void PrintAttendanceExtra() {
		int i;
		for (i = 0; i < attendExtra.size(); i++)
			System.out.print("Day " + i + 1 + " : " + attendExtra.get(i) + "  ||  ");
	}
	public boolean getAdmin() {
		return this.admin;
	}
	public String toString() {
		String s = super.toString();
		s += "\tUserName : " + getUsername() + "\n";
		return s;
	}

}
