package com.softserve.delivery.a82.vacation.checker.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.delivery.a82.vacation.checker.VacationReader;

/**
 * 
 * @author Ivan Rudnytskyj
 *         <p>
 * @version 1.0.0
 *          <p>
 *          Implementation of the VacationReader interface. Reads from the
 *          consoles dates of the vacations.
 * 
 */

public class ConsoleReaderImpl implements VacationReader {

    private static final int VACATIONS_NUMBER = 2;
    private static final int DATES_NUMBER = VACATIONS_NUMBER * 2;
    private static final String EXIT = "q";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ConsoleReaderImpl.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(
            DATE_FORMAT);

    private Date parseDate(String input) {

        if (input != null) {

            FORMATTER.setLenient(false);

            try {

                return FORMATTER.parse(input);

            } catch (ParseException e) {
                System.out
                        .println("You have entered the wrong value. Try again.");
                LOGGER.error("ConsoleReader.parseDate() " + e);
            }
        }

        return null;
    }

    private boolean endDateBeforeStartDate(List<Date> dates) {

        int size = dates.size();

        if (size > 0 && size % 2 == 0) {
            return dates.get(size - 1).before(dates.get(size - 2));
        }

        return false;
    }

    /**
     * 
     * @return - List<Date> dates, entered from the console.
     *         <p>
     *         The entered dates must conform to the specified format - ISO 8601
     *         (yyyy-MM-dd).
     */

    @Override
    public List<Date> readDates() {

        List<Date> dates = new ArrayList<Date>();
        Scanner scanner = new Scanner(System.in);

        System.out
                .println("Enter four dates - the beginning and the end of the two vacations in the format "
                        + DATE_FORMAT
                        + " (only numbers). To exit enter "
                        + EXIT + ".");

        String input = null;

        do {
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase(EXIT) && parseDate(input) != null) {

                dates.add(parseDate(input));

            }

            if (endDateBeforeStartDate(dates)) {
                dates.remove(dates.size() - 1);
                System.out
                        .println("The end date is before the start date. Please, enter the correct end date");
            }

        } while (dates.size() < DATES_NUMBER && !input.equalsIgnoreCase(EXIT));

        return (dates.size() < DATES_NUMBER) ? Collections.<Date> emptyList()
                : dates;
    }
}
