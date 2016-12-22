package test.java;

import static main.java.com.softserve.delivery.a8_2.vacationChecker.impl.VacationOverlapChecker.areVacationsOverlapped;
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

import main.java.com.softserve.delivery.a8_2.vacationChecker.Vacation;
import main.java.com.softserve.delivery.a8_2.vacationChecker.VacationHandler;
import main.java.com.softserve.delivery.a8_2.vacationChecker.impl.VacationOverlapChecker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestVacationOverlapChecker {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final List<Date> dates = new ArrayList<Date>();


	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void testMain() {

		Exception exc = null;

		ByteArrayInputStream in = new ByteArrayInputStream(DATES.getBytes());

		System.setIn(in);

		try {
			VacationOverlapChecker.main(new String[] {});
		} catch (Exception e) {
			exc = e;
		}

		System.setIn(System.in);

		assertNull(exc);
	}

	@Test
	public void testAreVacationsOverLappedExpectFalse() {

		Vacation firstVacation = getVacation(FIRST_VACATION_START_DATE,
				FIRST_VACATION_END_DATE);
		Vacation secondVacation = getVacation(
				SECOND_VACATION_START_DATE_NO_OVERLAP, SECOND_VACATION_END_DATE);

		assertFalse(areVacationsOverlapped(firstVacation, secondVacation));
	}

	@Test
	public void testAreVacationsOverLappedExpectTrue() {

		Vacation firstVacation = getVacation(FIRST_VACATION_START_DATE,
				FIRST_VACATION_END_DATE);
		Vacation secondVacation = getVacation(
				SECOND_VACATION_START_DATE_OVERLAP, SECOND_VACATION_END_DATE);

		assertTrue(areVacationsOverlapped(firstVacation, secondVacation));
	}

	@Test
	public void testshowCheckResultNoOverlap() {

		dates.add(parseDate(FIRST_VACATION_START_DATE));
		dates.add(parseDate(FIRST_VACATION_END_DATE));
		dates.add(parseDate(SECOND_VACATION_START_DATE_NO_OVERLAP));
		dates.add(parseDate(SECOND_VACATION_END_DATE));

		VacationHandler.setVacations(dates);

		VacationOverlapChecker.showCheckResult();

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

		VacationOverlapChecker.showCheckResult();

		assertEquals(
				"Overlap " + VacationHandler.getVacations().get(0) + " and "
						+ VacationHandler.getVacations().get(1)
						+ System.getProperty("line.separator"),
				outContent.toString());
	}
}
