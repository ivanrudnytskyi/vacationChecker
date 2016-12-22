package test.java;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static test.java.ServiceTest.DATES;
import static test.java.ServiceTest.DATES_SIZE;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import main.java.com.softserve.delivery.a8_2.vacationChecker.impl.ConsoleReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConsoleReader {
	
	private ByteArrayInputStream in;
	private ConsoleReader reader;
	
	@Before
	public void setUp() {
		 reader = new ConsoleReader();
	}

	@After
	public void cleanUp() {
		System.setIn(System.in);
	}
	
	@Test
	public void testReadDatesPositive() {

		in = new ByteArrayInputStream(DATES.getBytes());		

		System.setIn(in);

		List<Date> dates = reader.readDates();

		assertTrue(dates.size() == DATES_SIZE);
	}
	
	@Test (expected=NullPointerException.class)
	public void testReadDatesWrongFormat() {

		ByteArrayInputStream in = new ByteArrayInputStream(" ".getBytes());

		System.setIn(in);

		List<Date> dates = reader.readDates();

		assertNull(dates);
	}

}
