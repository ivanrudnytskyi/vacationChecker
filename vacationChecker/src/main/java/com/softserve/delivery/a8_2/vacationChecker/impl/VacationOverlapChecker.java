package main.java.com.softserve.delivery.a8_2.vacationChecker.impl;

import main.java.com.softserve.delivery.a8_2.vacationChecker.Vacation;
import main.java.com.softserve.delivery.a8_2.vacationChecker.VacationChecker;
import main.java.com.softserve.delivery.a8_2.vacationChecker.VacationHandler;

/**
 * 
 * @author Ivan Rudnytskyj
 *         <p>
 * @version 1.0.0
 *          <p>
 *          Main class for checking if two vacations overlap. Implements
 *          VacationChecker interface. The user is asked to enter four dates -
 *          start/end date for each vacation in a succession.
 */

public class VacationOverlapChecker implements VacationChecker {

	/**
	 * Main method
	 * <p>
	 * @param args
	 *            - the first parameter is used for showing the result of the
	 *            check to the console. "yes" or "y" must be entered in the
	 *            command line as the first argument.
	 */

	public static void main(String[] args) {

		ConsoleReader reader = new ConsoleReader();

		VacationHandler.setVacations(reader.readDates());

		if (args.length > 0
				&& (args[0].toLowerCase().equals("y") || args[0].toLowerCase()
						.equals("yes"))) {
			showCheckResult();
		}
	}

	/**
	 * 
	 * Checks if the two vacations overlap.
	 * 
	 * @param firstVacation
	 * @param secondVacation
	 * 
	 * @return - result of the comparison
	 */

	public static boolean areVacationsOverlapped(Vacation firstVacation, Vacation secondVacation) {

		if (firstVacation.getStartDate().before(secondVacation.getStartDate())) {
			return firstVacation.getEndDate().after(secondVacation.getStartDate());
		}
		return firstVacation.getStartDate().before(secondVacation.getEndDate());

	}

	/**
	 * Shows the result of the comparison on the console.
	 */

	public static void showCheckResult() {

		for (int i = 0; i < VacationHandler.getVacations().size() - 1; i++) {
			if (areVacationsOverlapped(VacationHandler.getVacations().get(i),
					VacationHandler.getVacations().get(i + 1))) {
				System.out.println("Overlap "
						+ VacationHandler.getVacations().get(i) + " and "
						+ VacationHandler.getVacations().get(i + 1));
			} else {
				System.out.println("No overlap for "
						+ VacationHandler.getVacations().get(i) + " and "
						+ VacationHandler.getVacations().get(i + 1));
			}
		}
	}
}
