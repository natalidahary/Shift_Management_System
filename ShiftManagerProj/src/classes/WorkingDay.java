package classes;

import java.util.Date;

public class WorkingDay {
	protected String date;
	protected String startTime;
	protected String finishTime;
	
	public WorkingDay(String date, String startTime, String finishTime) {
		this.date = date;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public String toString() {
		return "WorkingDay [date=" + date + ", startTime=" + startTime + ", finishTime=" + finishTime + "]";
	}
	
}
