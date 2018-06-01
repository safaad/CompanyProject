package Individuals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Person {
	protected String fn, ln;
	protected int age;

	Person(String first, String last, int [] birthday) {
		fn = first;
		ln = last;
		age =	calculateAge(
				LocalDate.of(birthday[2], birthday[1], birthday[0]), 
				LocalDate.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth())
			);

	}
	
	private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null))
			return Period.between(birthDate, currentDate).getYears();
		else
			return 0;
	}

	public String getFirstName() {
		return fn;
	}

	public String getLastName() {
		return ln;
	}

	public int getAge() {
		return age;
	}

	public void setName(String first, String last) {
		fn = first;
		ln = last;
	}

	public void setAge(int a) {
		age = a;
	}

	public String toString() {
		return fn + "  " + ln + "\n";
	}
}
