package test.java;

import static org.junit.Assert.*;
import static test.java.ServiceTest.FIRST_VACATION_END_DATE;
import static test.java.ServiceTest.FIRST_VACATION_START_DATE;
import static test.java.ServiceTest.getVacation;
import main.java.com.softserve.delivery.a8_2.vacationChecker.Vacation;

import org.junit.Test;

public class TestVacation {
	
	private final Vacation firstVacation = getVacation(FIRST_VACATION_START_DATE, FIRST_VACATION_END_DATE);
	
	@Test
	public void testVacationEqualsSameVacation(){
		
		assertTrue(firstVacation.equals(firstVacation));		
	}
	
	@Test
	public void testVacationEqualsEqualVacations(){
		
		Vacation secondVacation = getVacation(FIRST_VACATION_START_DATE, FIRST_VACATION_END_DATE);
				
		assertTrue(firstVacation.equals(secondVacation));		
	}
	
	@Test
	public void testVacationEqualsVacationNull(){
		
		Vacation secondVacation = null;
				
		assertFalse(firstVacation.equals(secondVacation));		
	}
	
	@Test
	public void testVacationEqualsDatesNull(){
		
		Vacation secondVacation = new Vacation();
				
		assertFalse(firstVacation.equals(secondVacation));		
	}
	
	@Test
	public void testVacationEqualsNegative(){
		
		Vacation secondVacation = getVacation(FIRST_VACATION_START_DATE, FIRST_VACATION_START_DATE);
		
		assertFalse(firstVacation.equals(secondVacation));		
	}
	
	@Test
	public void testHashCode(){
		
		Vacation secondVacation = getVacation(FIRST_VACATION_START_DATE, FIRST_VACATION_END_DATE);
		
		assertEquals(firstVacation.hashCode(), secondVacation.hashCode());
	}

}
