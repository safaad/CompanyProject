package Individuals;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class HourlyEmployee extends Employee {
	protected int reqNbOfHours;
	protected int nbOfHours=0;
	protected int nbOfExtraHours=0;
	protected int  tin;
	protected int tout;
	protected double extraS=0;

	ArrayList<Integer> nbOfHrs;
//load
	HourlyEmployee(String first,String last,int a,int nbOfh){
		super(first,last,a);
		reqNbOfHours=nbOfh;
		nbOfHrs=new ArrayList<Integer>();
	}
	@SuppressWarnings("deprecation")
	protected void registerIn() {
		Date d=new Date();
		tin= d.getHours();
	}
	protected void registerOut() {
		Date d=new Date();
		tout=d.getHours();
		nbOfHours=tout-tin;
		if((nbOfExtraHours=reqNbOfHours-nbOfHours)<0) {
			this.attend.add(false);
			this.attendExtra.add(false);
			this.nbOfHrs.add(new Integer(nbOfHours));

		}
		else {
			if((nbOfExtraHours=reqNbOfHours-nbOfHours)==0) {
				this.attend.add(true);
				this.attendExtra.add(false);
				this.nbOfHrs.add(new Integer(nbOfHours));
			}
			else {
				this.attend.add(true);
				this.attendExtra.add(true);
				this.nbOfHrs.add(new Integer(nbOfHours));
			}
		}
	}
	
	//save
	

}
