package main.java.com.softserve.delivery.a8_2.vacationChecker;

import org.apache.log4j.BasicConfigurator;

import main.java.com.softserve.delivery.a8_2.vacationChecker.impl.ConsoleReader;
import main.java.com.softserve.delivery.a8_2.vacationChecker.impl.VacationOverlapChecker;

/**
 * Main method
 * <p>
 * @param args
 *            - the first parameter is used for showing the result of the
 *            check to the console. "yes" or "y" must be entered in the
 *            command line as the first argument.
 */

public class Main {

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		ConsoleReader reader = new ConsoleReader();
		
		VacationChecker vacationChecker = new VacationOverlapChecker();

		VacationHandler.setVacations(reader.readDates());

		if (args.length > 0
				&& (args[0].toLowerCase().equals("y") || args[0].toLowerCase()
						.equals("yes"))) {
			vacationChecker.showCheckResultToConsole();
		}

	}

}
