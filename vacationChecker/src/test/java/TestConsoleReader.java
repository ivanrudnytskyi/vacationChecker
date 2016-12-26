package test.java;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static test.java.ServiceTest.DATES;
import static test.java.ServiceTest.DATES_SIZE;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import main.java.com.softserve.delivery.a8_2.vacationChecker.impl.ConsoleReaderImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestConsoleReader {

	private ByteArrayInputStream in;
	private ConsoleReaderImpl reader;
	private final BufferedReader mockReader = Mockito.mock(BufferedReader.class);

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

		in = new ByteArrayInputStream(DATES.getBytes());

		System.setIn(in);

		List<Date> dates = reader.readDates();

		assertTrue(dates.size() == DATES_SIZE);
	}

	@Test(expected = RuntimeException.class)
	public void testReadDatesWithReadIOException() throws IOException{

		ByteArrayInputStream in = new ByteArrayInputStream(DATES.getBytes());

		System.setIn(in);
		
		reader = new ConsoleReaderImpl(mockReader);
		
		when(mockReader.readLine()).thenThrow(new IOException());

		reader.readDates();
	}
	
	@Test(expected = RuntimeException.class)
	public void testReadDatesWithCloseIOException() throws IOException{

		ByteArrayInputStream in = new ByteArrayInputStream(DATES.getBytes());

		System.setIn(in);	
		
		reader = new ConsoleReaderImpl(mockReader);
		
		doThrow(new IOException()).when(mockReader).close();

		reader.readDates();
	}

}
