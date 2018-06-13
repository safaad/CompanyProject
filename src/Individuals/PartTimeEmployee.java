package Individuals;

import java.util.Calendar;
import java.util.Date;
import java.sql.Time;

public class PartTimeEmployee extends Employee {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("deprecation")
	private Time amIn = new Time(8, 0, 0);
	@SuppressWarnings("deprecation")
	private Time amOut = new Time(15, 0, 0);
	@SuppressWarnings("deprecation")
	private Time pmIn = new Time(17, 0, 0);
	@SuppressWarnings("deprecation")
	private Time pmOut = new Time(0, 0, 0);
	private boolean flagin = false;
	protected int Shift;// 1 for am 2 for pm
	private String shift;

	public PartTimeEmployee(String first, String last, int[] birthday, String shift) {
		super(first, last, birthday);
		this.shift = shift;
		if (shift.compareToIgnoreCase("am") == 0)
			Shift = 1;
		else if (shift.compareToIgnoreCase("pm") == 0)
			Shift = 2;
	}

	@SuppressWarnings("deprecation")
	public void registerIn() {
		Date d = new Date();
		if (flagin) {
			System.out.println("Can't register in");
			return;
		}
		Time in = new Time(d.getHours(), d.getMinutes(), d.getSeconds());
		if (Shift == 1) {
			if (in.equals(amIn) || (in.after(amIn) && in.before(new Time(9, 0, 0))))
				flagin = true;

		} else {
			if (in.equals(pmIn) || (in.after(pmIn) && in.before(new Time(18, 0, 0))))
				flagin = true;

		}
	}

	@SuppressWarnings("deprecation")
	public void registerOut() {
		Date d = new Date();
		Time out = new Time(d.getHours(), d.getMinutes(), d.getSeconds());
		if (!flagin) {
			System.out.println("Can't register out");
			return;
		}
		if (flagin) {
			if (Shift == 1) {
				if (out.equals(amOut) || (out.after(new Time(14, 40, 0)) && out.before(amOut))) {
					this.attend.add(true);
					this.attendExtra.add(false);
				} else {
					if (out.before(new Time(14, 40, 0))) {
						this.attend.add(false);
						this.attendExtra.add(false);

					} else {
						this.attend.add(true);
						this.attend.add(true);
					}
				}
			} else {
				if (out.equals(pmOut) || (out.after(new Time(23, 40, 0)) && out.before(pmOut))) {
					this.attend.add(true);
					this.attendExtra.add(false);
				} else {
					if (out.before(new Time(23, 40, 0))) {
						this.attend.add(false);
						this.attendExtra.add(false);

					} else {
						this.attend.add(true);
						this.attend.add(true);
					}
				}
			}
		} else {
			this.attend.add(false);
			this.attendExtra.add(false);
			return;
		}
		this.attendTime.add(Calendar.getInstance());
	}

	public void setSalary() {// for all shifts 500$ for extra shift 10 $
		int c = 0;
		for (int i = 0; i < this.attendExtra.size(); i++)
			if (attendExtra.get(i))
				c++;
		salary += 500 + c * 10;
	}

	public double getExtraPay() {
		double c = 0;
		for (int i = 0; i < this.attendExtra.size(); i++)
			if (attendExtra.get(i))
				c++;
		return c * 10;
	}

	public int getNbofShifts() {
		int c = 0;
		for (int i = 0; i < this.attend.size(); i++) {
			if (attend.get(i)) {
				c++;
				if (attendExtra.get(i)) {
					c++;
				}
			}
		}
		return c;
	}

	public String toString() {
		return "\tPartTimeEmployee" + super.toString() + "\tshift " + this.shift
				+ "\n\t\t---------------------------\n";
	}
}