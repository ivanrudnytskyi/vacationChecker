package com.softserve.delivery.a82.vacation.checker;

import static org.junit.Assert.assertTrue;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.DATES_SIZE;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.FIRST_VACATION_START_DATE;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.VACATIONS_SIZE;
import static com.softserve.delivery.a82.vacation.checker.ServiceTest.parseDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.softserve.delivery.a82.vacation.checker.VacationHandler;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestVacationHandler {
	
	private static List <Date> dates = new ArrayList<Date>();
	
	@BeforeClass
	public static void addDates(){	
		
		for (int i = 0; i < DATES_SIZE; i++){
			dates.add(parseDate(FIRST_VACATION_START_DATE));
		}
	}

	@Test
	public void testGetVacations() {
		
		VacationHandler.setVacations(dates);
		
		assertTrue(VacationHandler.getVacations().size() == VACATIONS_SIZE);
	}

}
