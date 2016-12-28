package com.softserve.delivery.a82.vacation.checker.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.softserve.delivery.a82.vacation.checker.VacationReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private BufferedReader br;

    public ConsoleReaderImpl() {
    }

    public ConsoleReaderImpl(BufferedReader reader) {
        this.br = reader;
    }

    private Date parseDate(String input) {

        FORMATTER.setLenient(false);

        if (input != null) {
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

    /**
     * @throws RuntimeException
     *             with appropriate message when unable to read from console or
     *             close input stream
     * 
     * @return - List<Date> dates, entered from the console.
     *         <p>
     *         The entered dates must conform to the specified format (see
     *         interface's VacationReader constants). The entered dates are not
     *         checked for order (start-end) or correctness (number of days in
     *         month etc) - it is the responsibility of the user.
     */

    @SuppressWarnings("unchecked")
    @Override
    public List<Date> readDates() {
        
        List<Date> dates = new ArrayList<Date>();

        try {

            if (br == null) {
                br = new BufferedReader(new InputStreamReader(System.in));
            }            

            System.out
                    .println("Enter four dates - the beginning and the end of the two vacations in the format "
                            + DATE_FORMAT
                            + " (only numbers). To exit enter "
                            + EXIT + ".");
            
            String input = null;

            do {
                input = br.readLine();

                if (!input.equalsIgnoreCase(EXIT) && parseDate(input) != null) {
                    dates.add(parseDate(input));
                }

            } while (dates.size() < DATES_NUMBER
                    && !input.equalsIgnoreCase(EXIT));

        } catch (IOException e) {
            LOGGER.error("ConsoleReader.readDates() exception while trying to read console input "
                    + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error("ConsoleReader.readDates() exception while trying to close console input stream "
                            + e);
                }
            }
        }
        
        return (List<Date>) ((dates.size() < DATES_NUMBER)? Collections.emptyList(): dates);
    }
}
