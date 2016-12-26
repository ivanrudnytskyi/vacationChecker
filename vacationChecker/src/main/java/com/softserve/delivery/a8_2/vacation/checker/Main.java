package main.java.com.softserve.delivery.a8_2.vacation.checker;

import static main.java.com.softserve.delivery.a8_2.vacation.checker.VacationReader.SHOW_RESULTS_TO_CONSOLE_FULL;
import static main.java.com.softserve.delivery.a8_2.vacation.checker.VacationReader.SHOW_RESULTS_TO_CONSOLE_SHORT;
import main.java.com.softserve.delivery.a8_2.vacation.checker.impl.ConsoleReaderImpl;
import main.java.com.softserve.delivery.a8_2.vacation.checker.impl.VacationOverlapCheckerImpl;

import org.apache.log4j.BasicConfigurator;

/**
 * Main method
 * <p>
 * 
 * @param args
 *            - the first parameter is used for showing the result of the check
 *            to the console. "yes" or "y" must be entered in the command line
 *            as the first argument.
 */

public final class Main {
	
	private Main(){
	    
	}

	public static void main(String[] args) {

		BasicConfigurator.configure();

		ConsoleReaderImpl reader = new ConsoleReaderImpl();

		VacationChecker vacationChecker = new VacationOverlapCheckerImpl();

		VacationHandler.setVacations(reader.readDates());

		if (args.length > 0
				&& (args[0].equalsIgnoreCase(SHOW_RESULTS_TO_CONSOLE_SHORT) || args[0]
						.equalsIgnoreCase(SHOW_RESULTS_TO_CONSOLE_FULL))) {
			vacationChecker.showCheckResultToConsole();
		}

	}

}
