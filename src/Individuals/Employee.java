package Individuals;

import java.util.Scanner;
import java.util.ArrayList;

public class Employee extends Person {
	Scanner in;
		protected double salary=1000;//basic
		protected String username;
		protected boolean admin=false;
		protected String password=null;
		protected ArrayList<Boolean> attend;
		protected ArrayList<Boolean> attendExtra;
		
		Employee(String first,String last,int a){
			super(first,last,a);
			username="" + fn.charAt(0) + fn.charAt(1) +fn.charAt(2)
					+ "_" + ln.charAt(0)+ln.charAt(1)+ln.charAt(2);
			attend=new ArrayList<Boolean>();
			attendExtra=new ArrayList<Boolean>();
		}
		
		public void setAdminstartor(){
			admin=true;
			setPassword();
			setSalary();
		}
		public boolean confirmPassword() {//this may be removed to driver
			String pass;
			System.out.println("please confirm your password first:");
			pass=in.nextLine();
			return pass.equals(password);
		}
		public void setPassword() {//it needs so many updates 
			String pass;
			if(password !=null) {
					while(!confirmPassword());
				}
			do {
				System.out.println("please enter the new password:");
				pass=in.nextLine();
				System.out.println("please reenter the password");
				password=in.nextLine();
			}while(!pass.equals(password));
			System.out.println("loged in\n-------------------------");	
		}
		protected void setSalary() {//100$ for admin
			if(admin)
				salary+=100;
		}

}
