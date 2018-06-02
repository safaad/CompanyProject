package Individuals;

import java.util.Scanner;
import java.util.ArrayList;

public abstract class Employee extends Person {
	Scanner in;
	protected double salary = 1000;
	protected String username, password;
	protected boolean admin = false;
	protected ArrayList<Boolean> attend, attendExtra;

	Employee(String first, String last, int [] birthday) {
		super(first, last, birthday);
		username = "" + fn.charAt(0) + fn.charAt(1) + fn.charAt(2) + "_" + ln.charAt(0) + ln.charAt(1) + ln.charAt(2);
		attend = new ArrayList<Boolean>();
		attendExtra = new ArrayList<Boolean>();
	}
	

	public void setAdminstartor() {
		admin = true;
		setSalary();
	}
	public boolean confirmPassword(String pass) { return pass.equals(password); }

	public void setPassword(String pass) { password = pass; }
	protected void setSalary() {
		if (admin)
			salary += 100;
	}
	public void setUsername(String username) { this.username = username; }
	public String getUsername() { return username; }
	public void PrintAttendance() {
		int i;
		for(i=0;i<attend.size();i++)
			System.out.print("Day "+ i+1 +" : "+ attend.get(i) + "  ||  ");
	}
	public void PrintAttendanceExtra() {
		int i;
		for(i=0;i<attendExtra.size();i++)
			System.out.print("Day "+ i+1 +" : "+ attendExtra.get(i) + "  ||  ");
	}
	public String toString() {
		String s="Employee  " +super.toString();
		s+="UserName : " + getUsername() +"\n";
		return s;
	}
	
}
