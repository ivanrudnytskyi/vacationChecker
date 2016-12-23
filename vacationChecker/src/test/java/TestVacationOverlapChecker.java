package test.java;

import static org.junit.Assert.*;
import static test.java.ServiceTest.DATES;
import static test.java.ServiceTest.FIRST_VACATION_END_DATE;
import static test.java.ServiceTest.FIRST_VACATION_START_DATE;
import static test.java.ServiceTest.SECOND_VACATION_END_DATE;
import static test.java.ServiceTest.SECOND_VACATION_START_DATE_NO_OVERLAP;
import static test.java.ServiceTest.SECOND_VACATION_START_DATE_OVERLAP;
import static test.java.ServiceTest.parseDate;
import static test.java.ServiceTest.getVacation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.com.softserve.delivery.a8_2.vacationChecker.Main;
import main.java.com.softserve.delivery.a8_2.vacationChecker.Vacation;
import main.java.com.softserve.delivery.a8_2.vacationChecker.VacationChecker;
import main.java.com.softserve.delivery.a8_2.vacationChecker.VacationHandler;
import main.java.com.softserve.delivery.a8_2.vacationChecker.impl.VacationOverlapChecker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestVacationOverlapChecker {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final List<Date> dates = new ArrayList<Date>();
	private final VacationChecker vacationChecker = new VacationOverlapChecker();


	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(System.out);
	}

	@Test
	public void testMainNoArgs() {

		Exception exc = null;

		ByteArrayInputStream in = new ByteArrayInputStream(DATES.getBytes());

		System.setIn(in);

		try {
			Main.main(new String[] {});
		} catch (Exception e) {
			exc = e;
		}

		System.setIn(System.in);

		assertNull(exc);
	}
	
	@Test
	public void testMainOneArg() {

		Exception exc = null;

		ByteArrayInputStream in = new ByteArrayInputStream(DATES.getBytes());

		System.setIn(in);

		try {
			Main.main(new String[] {"yes"});
		} catch (Exception e) {
			exc = e;
		}

		System.setIn(System.in);

		assertNull(exc);
	}

	@Test
	public void testAreVacationsOverLappedExpectFalseAscending() {

		Vacation firstVacation = getVacation(FIRST_VACATION_START_DATE,
				FIRST_VACATION_END_DATE);
		Vacation secondVacation = getVacation(
				SECOND_VACATION_START_DATE_NO_OVERLAP, SECOND_VACATION_END_DATE);

		assertFalse(vacationChecker.areVacationsOverlapped(firstVacation, secondVacation));
	}

	@Test
	public void testAreVacationsOverLappedExpectTrueAscending() {

		Vacation firstVacation = getVacation(FIRST_VACATION_START_DATE,
				FIRST_VACATION_END_DATE);
		Vacation secondVacation = getVacation(
				SECOND_VACATION_START_DATE_OVERLAP, SECOND_VACATION_END_DATE);

		assertTrue(vacationChecker.areVacationsOverlapped(firstVacation, secondVacation));
	}
	
	@Test
	public void testAreVacationsOverLappedExpectFalseDescending() {

		Vacation firstVacation = getVacation(FIRST_VACATION_START_DATE,
				FIRST_VACATION_END_DATE);
		Vacation secondVacation = getVacation(
				SECOND_VACATION_START_DATE_NO_OVERLAP, SECOND_VACATION_END_DATE);

		assertFalse(vacationChecker.areVacationsOverlapped(secondVacation, firstVacation));
	}

	@Test
	public void testAreVacationsOverLappedExpectTrueDescending() {

		Vacation firstVacation = getVacation(FIRST_VACATION_START_DATE,
				FIRST_VACATION_END_DATE);
		Vacation secondVacation = getVacation(
				SECOND_VACATION_START_DATE_OVERLAP, SECOND_VACATION_END_DATE);

		assertTrue(vacationChecker.areVacationsOverlapped(secondVacation, firstVacation));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAreVacationsOverLappedBothNulls() {
		vacationChecker.areVacationsOverlapped(null, null);
	}

	@Test
	public void testshowCheckResultNoOverlap() {

		dates.add(parseDate(FIRST_VACATION_START_DATE));
		dates.add(parseDate(FIRST_VACATION_END_DATE));
		dates.add(parseDate(SECOND_VACATION_START_DATE_NO_OVERLAP));
		dates.add(parseDate(SECOND_VACATION_END_DATE));

		VacationHandler.setVacations(dates);

		vacationChecker.showCheckResultToConsole();

		assertEquals(
				"No overlap for " + VacationHandler.getVacations().get(0)
						+ " and " + VacationHandler.getVacations().get(1)
						+ System.getProperty("line.separator"),
				outContent.toString());
	}

	@Test
	public void testshowCheckResultOverlap() {

		dates.add(parseDate(FIRST_VACATION_START_DATE));
		dates.add(parseDate(FIRST_VACATION_END_DATE));
		dates.add(parseDate(SECOND_VACATION_START_DATE_OVERLAP));
		dates.add(parseDate(SECOND_VACATION_END_DATE));

		VacationHandler.setVacations(dates);

		vacationChecker.showCheckResultToConsole();

		assertEquals(
				"Overlap " + VacationHandler.getVacations().get(0) + " and "
						+ VacationHandler.getVacations().get(1)
						+ System.getProperty("line.separator"),
				outContent.toString());
	}
}
