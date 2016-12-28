package com.softserve.delivery.a82.vacation.checker;

import com.softserve.delivery.a82.vacation.checker.impl.ConsoleReaderImpl;
import com.softserve.delivery.a82.vacation.checker.impl.VacationOverlapCheckerImpl;

/**
 * Main method
 * <p>
 * 
 * @param args
 *            - the first parameter is used for showing the result of the check
 *            to the console. "yes" or "y" must be entered in the command line
 *            as the first argument if a user wants to get the result on the
 *            screen.
 */

public final class Main {

    private static final String SHOW_RESULTS_TO_CONSOLE_SHORT = "y";
    private static final String SHOW_RESULTS_TO_CONSOLE_FULL = "yes";

    private Main() {

    }

    public static void main(String[] args) {

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
