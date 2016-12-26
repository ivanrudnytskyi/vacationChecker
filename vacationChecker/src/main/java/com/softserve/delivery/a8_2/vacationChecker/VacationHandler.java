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

public final class VacationHandler {
	
	private VacationHandler(){}

	private static List<Vacation> vacations;

	/**
	 * Creates List<Vacation>, sets start and end dates of each vacation.
	 * 
	 * @param dates
	 *            - List with dates for the vacations in order
	 *            firstVacation.startDate, firstVacation.endDate, secondVacation.startDate,
	 *            secondVacation.endDate.
	 */

	public static void setVacations(List<Date> dates) {
		
		vacations = new ArrayList<Vacation>();
		
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
