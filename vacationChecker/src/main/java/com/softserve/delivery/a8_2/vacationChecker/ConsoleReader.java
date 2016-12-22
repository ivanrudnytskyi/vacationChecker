package main.java.com.softserve.delivery.a8_2.vacationChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsoleReader {

	private final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
	private final String DATE_FORMAT = "yyyy-MM-dd";
	private final String EXIT = "q";

	private List<Date> dates = new ArrayList<Date>();
	private List<Vacation> vacationDates = new ArrayList<Vacation>();

	private BufferedReader br;

	private Date parseDate(String stringDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.parse(stringDate);
	}

	private void addVacations() {
		for (int i = 0; i < dates.size(); i += 2) {
			Vacation vacation = new Vacation();
			vacation.setStartDate(dates.get(i));
			try {
				vacation.setEndDate(dates.get(i + 1));
			} catch (IndexOutOfBoundsException e) {
				vacation.setEndDate(dates.get(i));
			}
			vacationDates.add(vacation);
		}
	}

	public void readDates() {
		try {

			br = new BufferedReader(new InputStreamReader(System.in));

			String input;

			System.out
					.print("Enter pair amount of dates - the beginning and the end of the vacations in the format "
							+ DATE_FORMAT
							+ " (only numbers).\nTo finish entering type " + EXIT + " and hit Enter. \n");

			do {
				input = br.readLine();

				if (input.matches(DATE_PATTERN)) {
					try {
						dates.add(parseDate(input));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else if (!input.matches(EXIT)) {
					System.out.println("You have entered the wrong value. Try again.");
				}
			} while (!input.equals(EXIT));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			addVacations();
			
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Vacation> getVacationDates() {
		return vacationDates;
	}

}
