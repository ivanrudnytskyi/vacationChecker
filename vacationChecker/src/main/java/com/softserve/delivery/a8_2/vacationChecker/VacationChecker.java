package main.java.com.softserve.delivery.a8_2.vacationChecker;

/**
 * 
 * @author Ivan Rudnytskyj
 * <p>
 * @version 1.0.0
 * <p>
 */

public interface VacationChecker {
	
	boolean areVacationsOverlapped(Vacation firstVacation, Vacation secondVacation);
	
	void showCheckResultToConsole();

}
