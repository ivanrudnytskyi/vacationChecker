package main.java.com.softserve.delivery.a8_2.vacationChecker;

import java.util.List;

public class VacationChecker {

	public static void main(String[] args) {
		
		ConsoleReader reader = new ConsoleReader();
		
		reader.readDates();
		
		List<Vacation> vacations = reader.getVacationDates();
		
		for (int i = 0; i < vacations.size() - 1; i++){
			if (vacations.get(i).checkOverlap(vacations.get(i + 1))){
				System.out.println("Overlap " + vacations.get(i) + " and " + vacations.get(i + 1));
			} else {
				System.out.println("No overlap for " + vacations.get(i) + " and " + vacations.get(i + 1));
			}
		}
	}
	
	public boolean areOverlapped (Vacation vac0, Vacation vac1){
		
		if (vac0.getStartDate().before(vac1.getStartDate())) {
			return vac0.getEndDate().after(vac1.getStartDate());
		} 
		return vac0.getStartDate().before(vac1.getEndDate());
		
	}
}
