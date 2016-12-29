package com.softserve.delivery.a82.vacation.checker;

import static com.softserve.delivery.a82.vacation.checker.ServiceTest.CORRECT_DATES;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.FIRST_VACATION_END_DATE;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.FIRST_VACATION_START_DATE;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.SECOND_VACATION_END_DATE;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.SECOND_VACATION_START_DATE_NO_OVERLAP;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.SECOND_VACATION_START_DATE_OVERLAP;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.getVacation;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserve.delivery.a82.vacation.checker.impl.VacationOverlapCheckerImpl;

public class TestVacationOverlapChecker {
    
    private static final String SHOW_RESULTS_TO_CONSOLE_SHORT = "y";
    private static final String SHOW_RESULTS_TO_CONSOLE_FULL = "yes";
    private static final String SHOW_RESULTS_TO_CONSOLE_INCORRECT = "no";

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final VacationChecker vacationChecker = new VacationOverlapCheckerImpl();


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

		ByteArrayInputStream in = new ByteArrayInputStream(CORRECT_DATES.getBytes());

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
	public void testMainOneArgFull() {

		Exception exc = null;

		ByteArrayInputStream in = new ByteArrayInputStream(CORRECT_DATES.getBytes());

		System.setIn(in);

		try {
			Main.main(new String[] {SHOW_RESULTS_TO_CONSOLE_FULL});
		} catch (Exception e) {
			exc = e;
		}

		System.setIn(System.in);

		assertNull(exc);
	}
	
	   @Test
	    public void testMainOneArgShort() {

	        Exception exc = null;

	        ByteArrayInputStream in = new ByteArrayInputStream(CORRECT_DATES.getBytes());

	        System.setIn(in);

	        try {
	            Main.main(new String[] {SHOW_RESULTS_TO_CONSOLE_SHORT});
	        } catch (Exception e) {
	            exc = e;
	        }

	        System.setIn(System.in);

	        assertNull(exc);
	    }
	   
       @Test
       public void testMainOneArgIncorrect() {

           Exception exc = null;

           ByteArrayInputStream in = new ByteArrayInputStream(CORRECT_DATES.getBytes());

           System.setIn(in);

           try {
               Main.main(new String[] {SHOW_RESULTS_TO_CONSOLE_INCORRECT});
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
	public void testAreVacationsOverLappedFirstNull() {
		Vacation vacation = getVacation(FIRST_VACATION_START_DATE,
				FIRST_VACATION_END_DATE);
		vacationChecker.areVacationsOverlapped(null, vacation);
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public void testAreVacationsOverLappedSecondNull() {
		Vacation vacation = getVacation(FIRST_VACATION_START_DATE,
				FIRST_VACATION_END_DATE);
		vacationChecker.areVacationsOverlapped(vacation, null);
	}	
}
