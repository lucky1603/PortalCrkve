/*
 * CaledarModel.java
 */
package rs.prosmart.calendar.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author Sinisa
 */
public class CalendarModel {
    private Year year;
    private SimpleObjectProperty<Month> currentMonth = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Month> displayMonth = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Day> currentDay = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Day> displayDay = new SimpleObjectProperty<>();
            
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
        this.currentMonth.set(this.year.getMonth(calendar.get(Calendar.MONTH)));
        this.displayMonth.set(this.year.getMonth(calendar.get(Calendar.MONTH)));
        this.currentDay.set(this.currentMonth.get().getDay(calendar.get(Calendar.DAY_OF_MONTH)));
        this.displayDay.set(this.currentMonth.get().getDay(calendar.get(Calendar.DAY_OF_MONTH)));
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
        this.currentMonth.set(this.year.getMonth(month));
        this.displayMonth.set(this.currentMonth.get());
        this.currentDay.set(this.currentMonth.get().getDay(day));
        this.displayDay.set(this.currentMonth.get().getDay(day));
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
        return currentMonth.get();
    }
    
    public void setCurrentMonth(Month value)
    {
        Month m = this.currentMonth.get();
        if(m != null)
        {
            m.setIsCurrent(false);
        }
        
        value.setIsCurrent(true);
        this.currentMonth.set(value);
    }
    
    /**
     * Gets the display month property.
     * @return 
     */
    public SimpleObjectProperty<Month> getCurrentMonthProperty()
    {
        return currentMonth;
    }

    /**
     * @return the displayMonth
     */
    public Month getDisplayMonth() {
        return displayMonth.get();
    }
    
    /**
     * Gets the display month property.
     * @return 
     */
    public SimpleObjectProperty<Month> getDisplayMonthProperty()
    {
        return displayMonth;
    }

    /**
     * @return the currentDay
     */
    public Day getCurrentDay() {
        return currentDay.get();
    }

    /**
     * @return the displayDay
     */
    public Day getDisplayDay() {
        return displayDay.get();
    }
    
    public List<Map<String, Day>> getLinesForCurrentMonth()
    {
        List<Map<String, Day>> lines = new ArrayList<>();
        Map<String, Day> line = createLine();
        Month month = this.getCurrentMonth();
        for(int i = 0; i < month.getCountOfDays(); i++)
        {
            Day d = month.getDay(i);

        }
        
        return lines;
    }
    
    private Map<String, Day> createLine()
    {
        Map<String, Day> line = new HashMap<>();
        line.put("Ned", null);
        line.put("Pon", null);
        line.put("Uto", null);
        line.put("Sre", null);
        line.put("Čet", null);
        line.put("Pet", null);
        line.put("Sub", null);        
        
        return line;
    }
    
}
