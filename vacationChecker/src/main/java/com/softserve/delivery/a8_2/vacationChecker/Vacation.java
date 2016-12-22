package main.java.com.softserve.delivery.a8_2.vacationChecker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vacation {

	private Date startDate;

	private Date endDate;
	
	private final String DATE_FORMAT = "yyyy-MM-dd";
	
	private String formatDate(Date date){
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean checkOverlap(Vacation vacation) {
		if (this.startDate.before(vacation.startDate)) {
			return this.endDate.after(vacation.startDate);
		} 
		return this.startDate.before(vacation.endDate);
	}

	@Override
	public String toString() {
		return "Vacation [startDate=" + formatDate(startDate) + ", endDate=" + formatDate(endDate)
				+ "]";
	}

}
