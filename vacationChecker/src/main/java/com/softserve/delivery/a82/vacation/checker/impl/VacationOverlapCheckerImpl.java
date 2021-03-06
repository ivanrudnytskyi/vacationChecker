package com.softserve.delivery.a82.vacation.checker.impl;

import com.softserve.delivery.a82.vacation.checker.Vacation;
import com.softserve.delivery.a82.vacation.checker.VacationChecker;
import com.softserve.delivery.a82.vacation.checker.VacationHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class VacationOverlapCheckerImpl implements VacationChecker {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(VacationOverlapCheckerImpl.class);

    /**
     * 
     * Checks if the two vacations overlap.
     * 
     * @param firstVacation
     * @param secondVacation
     * 
     * @throws IllegalArgumentException
     *             if either (or both) @param is null
     * 
     * @return - result of the comparison
     */

    @Override
    public boolean areVacationsOverlapped(Vacation firstVacation,
            Vacation secondVacation) {

        if (firstVacation == null) {
            LOGGER.error("Null parameter " + firstVacation);
            throw new IllegalArgumentException(
                    "Parameter firstVacation must NOT be null");
        }

        if (secondVacation == null) {
            LOGGER.error("Null parameter " + secondVacation);
            throw new IllegalArgumentException(
                    "Parameter secondVacation must NOT be null");
        }

        if (firstVacation.getStartDate().before(secondVacation.getStartDate())) {
            return firstVacation.getEndDate().after(
                    secondVacation.getStartDate());
        }
        return firstVacation.getStartDate().before(secondVacation.getEndDate());

    }

    /**
     * Shows the result of the comparison on the console.
     */
    @Override
    public void showCheckResultToConsole() {

        if (VacationHandler.getVacations() != null) {

            for (int i = 0; i < VacationHandler.getVacations().size() - 1; i++) {
                if (areVacationsOverlapped(VacationHandler.getVacations()
                        .get(i), VacationHandler.getVacations().get(i + 1))) {
                    LOGGER.info("Overlap "
                            + VacationHandler.getVacations().get(i) + " and "
                            + VacationHandler.getVacations().get(i + 1));
                } else {
                    LOGGER.info("No overlap for "
                            + VacationHandler.getVacations().get(i) + " and "
                            + VacationHandler.getVacations().get(i + 1));
                }
            }
        }
    }
}
