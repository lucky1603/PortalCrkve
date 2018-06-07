/*
 * CaledarModel.java
 */
package rs.prosmart.calendar.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Sinisa
 */
public class CalendarModel {
    private Year year;
    private Month currentMonth;
    private Month displayMonth;
    private Day currentDay;
    private Day displayDay;
            
    /**
     * Constructor.
     */
    public CalendarModel()
    {
        this(new Date());
    }
    
    /**
     * Constructor.
     * @param date  A specific date.
     */
    public CalendarModel(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.year = new Year(calendar.get(Calendar.YEAR));
        this.currentMonth = this.year.getMonth(calendar.get(Calendar.MONTH));
        this.currentDay = this.currentMonth.getDay(calendar.get(Calendar.DAY_OF_MONTH));
    }
    
    /**
     * Constructor.
     * @param day Day of month.
     * @param month Month of year.
     * @param year Year.
     */
    public CalendarModel(int day, int month, int year)
    {        
        this.year = new Year(year);
        this.currentMonth = this.year.getMonth(month);
        this.currentDay = this.currentMonth.getDay(day);
    }
    
    /**
     * Test function.
     * @return 
     */
    public String printToday()
    {
        String today = this.getCurrentDay().getName() + ", " + this.getCurrentDay().getIndex() + ". " + this.getCurrentMonth().getName() + " " + this.getYear().getName();
        return today;
    }

    /**
     * @return the year
     */
    public Year getYear() {
        return year;
    }

    /**
     * @return the currentMonth
     */
    public Month getCurrentMonth() {
        return currentMonth;
    }

    /**
     * @return the displayMonth
     */
    public Month getDisplayMonth() {
        return displayMonth;
    }

    /**
     * @return the currentDay
     */
    public Day getCurrentDay() {
        return currentDay;
    }

    /**
     * @return the displayDay
     */
    public Day getDisplayDay() {
        return displayDay;
    }
    
    
}
