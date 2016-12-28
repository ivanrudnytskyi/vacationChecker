package com.softserve.delivery.a82.vacation.checker;

import static com.softserve.delivery.a82.vacation.checker.ServiceTest.CORRECT_DATES;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.DATES_SIZE;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.DATES_IN_INCORRECT_ORDER;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserve.delivery.a82.vacation.checker.impl.ConsoleReaderImpl;

public class TestConsoleReader {

	private ByteArrayInputStream in;
	private ConsoleReaderImpl reader;

	@Before
	public void setUp() {
		reader = new ConsoleReaderImpl();
	}

	@After
	public void cleanUp() {
		System.setIn(System.in);
	}

	@Test
	public void testReadDatesPositive() {

		in = new ByteArrayInputStream(CORRECT_DATES.getBytes());

		System.setIn(in);

		List<Date> dates = reader.readDates();

		assertTrue(dates.size() == DATES_SIZE);
	}
	
	   @Test
	    public void testReadDatesPositiveWithIncorrectDatesorder() {

	        in = new ByteArrayInputStream(DATES_IN_INCORRECT_ORDER.getBytes());

	        System.setIn(in);

	        List<Date> dates = reader.readDates();

	        assertTrue(dates.size() == DATES_SIZE);
	    }
	
	   @Test(expected = NoSuchElementException.class)
	    public void testReadDatesWithParseException() {

	        ByteArrayInputStream in = new ByteArrayInputStream(" ".getBytes());

	        System.setIn(in);   

	        reader.readDates();
	    }

}
