package main.java.com.softserve.delivery.a8_2.vacation.checker;

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
	String SHOW_RESULTS_TO_CONSOLE_SHORT = "y";
	String SHOW_RESULTS_TO_CONSOLE_FULL = "yes";
	
	List<Date> readDates();
	
}
