package main.java.com.softserve.delivery.a8_2.vacation.checker.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.com.softserve.delivery.a8_2.vacation.checker.VacationReader;

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

    private final static int VACATIONS_NUMBER = 2;
    private final static int DATES_NUMBER = VACATIONS_NUMBER * 2;
    private final static String EXIT = "q";
    private final static Logger LOGGER = LoggerFactory
            .getLogger(ConsoleReaderImpl.class);

    private BufferedReader br;

    private Date parseDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(
                VacationReader.DATE_FORMAT);
        formatter.setLenient(false);
        return formatter.parse(stringDate);
    }

    public ConsoleReaderImpl() {
    }

    public ConsoleReaderImpl(BufferedReader reader) {
        this.br = reader;
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

    @Override
    public List<Date> readDates() {

        LOGGER.debug("ConsoleReader.readDates()");

        List<Date> dates = new ArrayList<Date>();

        try {

            if (br == null) {
                br = new BufferedReader(new InputStreamReader(System.in));
            }

            String input;

            System.out
                    .println("Enter four dates - the beginning and the end of the two vacations in the format "
                            + DATE_FORMAT
                            + " (only numbers). To exit enter "
                            + EXIT + ".");

            do {
                input = br.readLine();

                try {
                    dates.add(parseDate(input));
                } catch (ParseException e) {
                    if (!input.equalsIgnoreCase(EXIT)) {
                        System.out
                                .println("You have entered the wrong value. Try again.");
                    }
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
        return dates;
    }

}
