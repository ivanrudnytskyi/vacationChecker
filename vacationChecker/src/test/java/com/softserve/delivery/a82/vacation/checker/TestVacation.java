package test.java.com.softserve.delivery.a82.vacation.checker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static test.java.com.softserve.delivery.a82.vacation.checker.ServiceTest.FIRST_VACATION_END_DATE;
import static test.java.com.softserve.delivery.a82.vacation.checker.ServiceTest.FIRST_VACATION_START_DATE;
import static test.java.com.softserve.delivery.a82.vacation.checker.ServiceTest.getVacation;
import main.java.com.softserve.delivery.a82.vacation.checker.Vacation;

import org.junit.Test;

public class TestVacation {

    private final Vacation firstVacation = getVacation(
            FIRST_VACATION_START_DATE, FIRST_VACATION_END_DATE);

    @Test
    public void testVacationEqualsSameVacation() {

        assertTrue(firstVacation.equals(firstVacation));
    }

    @Test
    public void testVacationEqualsEqualVacations() {

        Vacation secondVacation = getVacation(FIRST_VACATION_START_DATE,
                FIRST_VACATION_END_DATE);

        assertTrue(firstVacation.equals(secondVacation));
    }

    @Test
    public void testVacationEqualsVacationNull() {

        Vacation secondVacation = null;

        assertFalse(firstVacation.equals(secondVacation));
    }

  
    @Test
    public void testSecondVacationEqualsDatesNull() {

        Vacation secondVacation = new Vacation();

        assertFalse(firstVacation.equals(secondVacation));
    }

    @Test
    public void testVacationEqualsNegative() {

        Vacation secondVacation = getVacation(FIRST_VACATION_START_DATE,
                FIRST_VACATION_START_DATE);

        assertFalse(firstVacation.equals(secondVacation));
    }

    @Test
    public void testVacationEqualsObject() {

        Object object = new Object();

        assertFalse(firstVacation.equals(object));
    }
    
    @Test
    public void testFirstVacationEqualsDifferentDates() {

        Vacation firstVacation = getVacation(FIRST_VACATION_END_DATE,
                FIRST_VACATION_END_DATE);
        Vacation secondVacation = getVacation(FIRST_VACATION_START_DATE,
                FIRST_VACATION_END_DATE);

        assertFalse(firstVacation.equals(secondVacation));
    }

    @Test
    public void testHashCode() {

        Vacation secondVacation = getVacation(FIRST_VACATION_START_DATE,
                FIRST_VACATION_END_DATE);

        assertEquals(firstVacation.hashCode(), secondVacation.hashCode());
    }

}
