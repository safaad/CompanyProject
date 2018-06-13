package Individuals;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HourlyEmployee extends Employee {
	private static final long serialVersionUID = 1L;
	protected int reqNbOfHours;
	protected int nbOfHours = 0;
	protected int nbOfExtraHours = 0;
	protected int tin;
	protected int tout;
	ArrayList<Integer> nbOfHrs;
	boolean flagin = false;

	// load
	public HourlyEmployee(String first, String last, int[] birthday, int nbOfh) {
		super(first, last, birthday);
		reqNbOfHours = nbOfh;
		nbOfHrs = new ArrayList<Integer>();
	}

	public void registerIn() {
		if (flagin) {
			System.out.println("Can't register in");
			return;
		}
		flagin = true;
		tin = LocalDateTime.now().getHour();

	}

	public void registerOut() {
		if (!flagin) {
			System.out.println("Can't register out");
			return;
		}

		tout = LocalDateTime.now().getHour();
		nbOfHours = tout - tin;
		if ((nbOfExtraHours = reqNbOfHours - nbOfHours) < 0) {
			this.attend.add(false);
			this.attendExtra.add(false);
			this.nbOfHrs.add(new Integer(nbOfHours));

		} else {
			if ((nbOfExtraHours = reqNbOfHours - nbOfHours) == 0) {
				this.attend.add(false);
				this.attendExtra.add(false);
				this.nbOfHrs.add(new Integer(nbOfHours));
			} else {
				this.attend.add(true);
				this.attendExtra.add(true);
				this.nbOfHrs.add(new Integer(nbOfHours));
			}
		}
		flagin = false;
	}

	// save
	public double getAverageWorkedHours() {
		double s = 0;
		for (int i = 0; i < this.nbOfHrs.size(); i++) {
			s += nbOfHrs.get(i);
		}
		s = s / nbOfHrs.size();
		return s;
	}

	public double getExtraPay() {
		int attends = 0;
		double extra = 0;
		int extraattends = 0;
		for (int i = 0; i < this.attend.size(); i++) {
			if (attend.get(i))
				attends += 1;
		}
		if (reqNbOfHours >= 7 && attends >= 10) {// hrs-6 ->20$
			extra += (reqNbOfHours - 6) * 20 * (attends * 0.25);
		} else {// only 100$ extra for all attends and worked hours
			extra += 100;
		}
		for (int i = 0; i < attendExtra.size(); i++)// 5 $ for each extra attend
			if (attendExtra.get(i))
				extraattends += 1;
		extra += extraattends * 5;
		return extra;
	}

	public void setSalary() {
		salary += getExtraPay();
	}

	public int getReqNbOfHrs() {
		return reqNbOfHours;
	}

	public int getTotalNbOfWorkedHours() {
		int c = 0;
		for (int i = 0; i < this.nbOfHrs.size(); i++) {
			c += nbOfHrs.get(i);

		}
		return c;

	}

	public double getAverageExtraWorkedHrs() {
		double s = 0;
		for (int i = 0; i < this.nbOfHrs.size(); i++) {
			if (attendExtra.get(i))
				s += (nbOfHrs.get(i) - reqNbOfHours);
		}
		return s / nbOfHrs.size();
	}

	public String toString() {
		return "\tHourlyEmployee " + super.toString() + "\tnb of req hours " + this.reqNbOfHours
				+ "\n\t\t---------------------------\n";
	}
}
