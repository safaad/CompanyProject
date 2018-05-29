package Individuals;

import java.util.Date;
import java.util.ArrayList;

public class HourlyEmployee extends Employee {
	protected int reqNbOfHours;
	protected int nbOfHours = 0;
	protected int nbOfExtraHours = 0;
	protected int tin;
	protected int tout;

	ArrayList<Integer> nbOfHrs;

	// load
	public HourlyEmployee(String first, String last, int a, int nbOfh) {
		super(first, last, a);
		this.setUsername("2"+username);
		reqNbOfHours = nbOfh;
		nbOfHrs = new ArrayList<Integer>();
	}

	@SuppressWarnings("deprecation")
	protected void registerIn() {
		Date d = new Date();
		tin = d.getHours();
	}

	@SuppressWarnings("deprecation")
	protected void registerOut() {
		Date d = new Date();
		tout = d.getHours();
		nbOfHours = tout - tin;
		if ((nbOfExtraHours = reqNbOfHours - nbOfHours) < 0) {
			this.attend.add(false);
			this.attendExtra.add(false);
			this.nbOfHrs.add(new Integer(nbOfHours));

		} else {
			if ((nbOfExtraHours = reqNbOfHours - nbOfHours) == 0) {
				this.attend.add(true);
				this.attendExtra.add(false);
				this.nbOfHrs.add(new Integer(nbOfHours));
			} else {
				this.attend.add(true);
				this.attendExtra.add(true);
				this.nbOfHrs.add(new Integer(nbOfHours));
			}
		}
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

	protected void setSalary() {
		salary += getExtraPay();
	}

	public int getReqNbOfHrs() {
		return reqNbOfHours;
	}

	public double getAverageExtraWorkedHrs() {
		double s = 0;
		for (int i = 0; i < this.nbOfHrs.size(); i++) {
			if (attendExtra.get(i))
				s += (nbOfHrs.get(i) - reqNbOfHours);
		}
		s = s / nbOfHrs.size();
		return s;
	}

}
