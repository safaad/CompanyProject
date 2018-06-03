package Drivers;

import CompanyStuff.Company;

public class CompanyDriver {
	
	public void setAdmin(String username) throws AdminsException {
		try {
			if(Company.NbofAdmins==5)
				throw new AdminsException();
			else {
				if(!Driver.Website.exist(username))
					System.out.println("You aren't even an Employee go to the Employee's side and sign up");
				else {
					Driver.Website.getEmployee(username).setAdminstartor();
				}
			}
		}
		
	}
	
}
