package Individuals;

import java.util.Date;
import java.sql.Time;

public class PartTimeEmployee extends Employee {
	@SuppressWarnings("deprecation")
	private Time amIn = new Time(8, 0, 0);
	@SuppressWarnings("deprecation")
	private Time amOut = new Time(15, 0, 0);
	@SuppressWarnings("deprecation")
	private Time pmIn = new Time(17, 0, 0);
	@SuppressWarnings("deprecation")
	private Time pmOut = new Time(0, 0, 0);
	private boolean flagin;
	protected int Shift;// 1 for am 2 for pm
	private String shift;

	public PartTimeEmployee(String first, String last, int[] birthday, String shift) {
		super(first, last, birthday);
		this.shift = shift;
		if (shift.equals("am") || shift.equals("AM"))
			Shift = 1;
		else if (shift.equals("pm") || shift.equals("PM"))
			Shift = 2;
	}

	@SuppressWarnings("deprecation")
	public void registerIn() {
		Date d = new Date();
		Time in = new Time(d.getHours(), d.getMinutes(), d.getSeconds());
		if (Shift == 1) {
			if (in.equals(amIn) || (in.after(amIn) && in.before(new Time(9, 0, 0))))
				flagin = true;
		} else {
			if (in.equals(pmIn) || (in.after(pmIn) && in.before(new Time(18, 0, 0))))
				flagin = true;
		}
		flagin = false;
	}

	@SuppressWarnings("deprecation")
	public void registerOut() {
		Date d = new Date();
		Time out = new Time(d.getHours(), d.getMinutes(), d.getSeconds());
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
		}
	}

	protected void setSalary() {// for all shifts 500$ for extra shift 10 $
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

	public String toString() {
		return super.toString() + "he is an PartTimeEmployee \nshift " + this.shift + "-----------------\n";
	}
}
