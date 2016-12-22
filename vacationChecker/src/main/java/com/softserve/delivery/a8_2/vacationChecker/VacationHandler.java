package main.java.com.softserve.delivery.a8_2.vacationChecker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Ivan Rudnytskyj
 * 
 * @version 1.0.0
 * 
 *          Utility class for creating list of Vacations.
 */

public class VacationHandler {

	private static List<Vacation> vacations = new ArrayList<Vacation>();

	/**
	 * Creates List<Vacation>, sets start and end dates of each vacation.
	 * 
	 * @param dates
	 *            - List with dates for the vacations in order
	 *            Vacation0.startDate, Vacation0.endDate, Vacation1.startDate,
	 *            Vacation1.endDate.
	 */

	public static void setVacations(List<Date> dates) {
		for (int i = 0; i < dates.size(); i += 2) {
			Vacation vacation = new Vacation();
			vacation.setStartDate(dates.get(i));
			vacation.setEndDate(dates.get(i + 1));

			vacations.add(vacation);
		}
	}

	/**
	 * 
	 * @return - List<Vacation>
	 */
	
	public static List<Vacation> getVacations() {
		return vacations;
	}

}
