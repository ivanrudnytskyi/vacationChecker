package com.softserve.delivery.a82.vacation.checker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.softserve.delivery.a82.vacation.checker.Vacation;

/**
 * 
 * @author Ivan Rudnytskyj
 *         <p>
 * @version 1.0.0
 *          <p>
 *          Service class for test classes
 */

public final class ServiceTest {

	final static String CORRECT_DATES = "2016-01-01\n2016-01-02\n2016-01-03\n2016-01-04\n";
	final static String DATES_IN_INCORRECT_ORDER = "2016-01-10\n2016-01-01\n2016-01-11\n2016-01-03\n2016-01-01\n2016-01-10\n";
	final static int DATES_SIZE = 4;
	final static int VACATIONS_SIZE = 2;
	static final String DATE_FORMAT = "yyyy-MM-dd";

	final static String FIRST_VACATION_START_DATE = "2016-01-01";
	final static String FIRST_VACATION_END_DATE = "2016-01-10";
	final static String SECOND_VACATION_START_DATE_NO_OVERLAP = "2016-01-20";
	final static String SECOND_VACATION_START_DATE_OVERLAP = "2016-01-09";
	final static String SECOND_VACATION_END_DATE = "2016-01-30";

	static Date parseDate(String date) {

		try {
			return new SimpleDateFormat(DATE_FORMAT).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	static Vacation getVacation(String startDate, String enddate) {
		Vacation vacation = new Vacation();
		vacation.setStartDate(parseDate(startDate));
		vacation.setEndDate(parseDate(enddate));

		return vacation;
	}
	
	private ServiceTest(){
	}

}
