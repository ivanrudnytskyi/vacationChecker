package com.softserve.delivery.a82.vacation.checker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Ivan Rudnytskyj
 *         <p>
 * @version 1.0.0
 *          <p>
 *          POJO for vacation. Fields - start date and end date;
 */

public class Vacation {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private Date startDate;
    private Date endDate;

    private String formatDate(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Vacation [startDate=" + formatDate(startDate) + ", endDate="
                + formatDate(endDate) + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result
                + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Vacation other = (Vacation) obj;

        return this.startDate.equals(other.getStartDate())
                && this.endDate.equals(other.getEndDate());
    }
}
