package main.java.com.softserve.delivery.a8_2.vacationChecker;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Ivan Rudnytskyj
 * <p>
 * @version 1.0.0
 * <p>
 */

public interface VacationReader {

	String DATE_FORMAT = "yyyy-MM-dd";
	
	List<Date> readDates();
	
}
