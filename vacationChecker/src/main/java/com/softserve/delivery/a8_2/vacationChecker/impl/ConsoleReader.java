package main.java.com.softserve.delivery.a8_2.vacationChecker.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.softserve.delivery.a8_2.vacationChecker.VacationReader;

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

public class ConsoleReader implements VacationReader {

	private final int VACATIONS_NUMBER = 2;
	private final int DATES_NUMBER = VACATIONS_NUMBER * 2;

	private final Logger logger = LoggerFactory.getLogger(ConsoleReader.class);

	private BufferedReader br;

	private Date parseDate(String stringDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(
				VacationReader.DATE_FORMAT);
		return formatter.parse(stringDate);
	}

	/**
	 * 
	 * @return - List<Date> dates, entered from the console.
	 *         <p>
	 *         The entered dates must conform to the specified format (see
	 *         interface's VacationReader constants). The entered dates are not
	 *         checked for order (start-end) or correctness (number of days in
	 *         month etc) - it is the responsibility of the user.
	 */

	public List<Date> readDates() {

		logger.debug("ConsoleReader.readDates()");

		List<Date> dates = new ArrayList<Date>();

		try {

			br = new BufferedReader(new InputStreamReader(System.in));

			String input;

			System.out
					.println("Enter four dates - the beginning and the end of the two vacations in the format "
							+ DATE_FORMAT + " (only numbers).");

			do {
				input = br.readLine();

				if (input.matches(DATE_PATTERN)) {
					try {
						dates.add(parseDate(input));
					} catch (ParseException e) {
						logger.error("ConsoleReader.readDates() exception while trying to parse console input "
								+ e.getMessage());
					}
				} else {
					System.out
							.println("You have entered the wrong value. Try again.");
				}
			} while (dates.size() < DATES_NUMBER);

		} catch (IOException e) {
			logger.error("ConsoleReader.readDates() exception while trying to read console input "
					+ e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("ConsoleReader.readDates() exception while trying to close console input stream "
							+ e.getMessage());
				}
			}
		}
		return dates;
	}
}
