package classes;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Vector;

public class Salary {
	protected String week;
	protected double baseHours;
	protected double additionHours;
	protected double totalSalary;
	protected Vector <WorkingDay> workDays;

	public Salary(Vector<WorkingDay> workDays) {
		LocalDate date = LocalDate.now().plusDays(7);
		WeekFields w = WeekFields.of(Locale.getDefault());
		int weekNumber = date.get(w.weekOfWeekBasedYear());
		this.week = String.valueOf(weekNumber);
		this.baseHours = 0;
		this.additionHours = 0;
		this.totalSalary = 0;
		this.workDays = new Vector<WorkingDay>();
		this.workDays = workDays;
	}

	public void calculateSalary(double wagePerHour){
		double countHours = 0, difference;
		if(!workDays.isEmpty()) {
			for(int i=0; i<workDays.size(); i++) {
				if(!(workDays.elementAt(i) == null)) {
					difference = stringToHours(workDays.elementAt(i).getFinishTime()) - stringToHours(workDays.elementAt(i).getStartTime());
					if(difference > 8.0) {
						this.additionHours += difference-8.0;
						countHours += 8.0;
					}else
						countHours +=difference;
				}
			}
		}
		this.baseHours = countHours;
		this.totalSalary = (countHours*wagePerHour) + (additionHours*wagePerHour*1.25);

	}

	
	
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public double getBaseHours() {
		return baseHours;
	}

	public void setBaseHours(double baseHours) {
		this.baseHours = baseHours;
	}

	public double getAdditionHours() {
		return additionHours;
	}

	public void setAdditionHours(double additionHours) {
		this.additionHours = additionHours;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public double stringToHours(String str) {
		String [] newStr = str.split(":");
		double hours,min;
		hours = Double.parseDouble(newStr[0]);
		min = Double.parseDouble(newStr[1]);
		return hours + min/60;
	}
}
