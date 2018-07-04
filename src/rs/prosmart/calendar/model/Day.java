/*
 * Day.java
 */
package rs.prosmart.calendar.model;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import rs.prosmart.PortalCrkve.ApplicationContext;

/**
 * Day model class.
 * @author Sinisa
 */
public class Day implements Comparable {    
    private int code = Calendar.SUNDAY;
    private int index = 0;
    private SimpleBooleanProperty isCurrent = new SimpleBooleanProperty();
    private Month month;
    private boolean today;
    
    
    /**
     * Constructor.
     */
    public Day()
    {
        this.today = false;
    }
    
    /**
     * Constructor.
     * @param code 
     */
    public Day(int code)
    {
        this.code = code;
        this.index = 0;
        this.today = false;
    }
    
    /**
     * Constructor.
     * @param code 
     * @param index 
     */
    public Day(int code, int index)
    {
        this.code = code;
        this.index = index;
        this.today = false;
    }
    
    
    /**
     * Constructor.
     * @param code
     * @param index
     * @param month 
     */
    public Day(int code, int index, Month month)
    {
        this(code, index);
        this.month = month;
    }
    
    public void setIsToday(boolean today)
    {
        this.today = today;
    }
    
    public boolean getIsToday()
    {
        return this.today;
    }
    
    public boolean getIsCurrent()
    {
        return this.isCurrent.get();
    }
    
    public void setIsCurrent(boolean value)
    {
        isCurrent.set(value);
    }
    
    public SimpleBooleanProperty getIsCurrentProperty()
    {
        return isCurrent;
    }
    
    public void setMonth(Month month)
    {
        this.month = month;
    }
    
    public Month getMonth()
    {
        return this.month;
    }
    
    /**
     * Get day name.
     * @return String Name of the day.
     */
    public String getName()
    {
        ApplicationContext app = ApplicationContext.getInstance();
        switch(this.code) {
            case Calendar.SUNDAY: 
                return app.getLabel("Sunday");
            case Calendar.MONDAY:
                return app.getLabel("Monday");
            case Calendar.TUESDAY:
                return app.getLabel("Tuesday");
            case Calendar.WEDNESDAY:
                return app.getLabel("Wednesday");
            case Calendar.THURSDAY:
                return app.getLabel("Tuesday");
            case Calendar.FRIDAY:
                return app.getLabel("Friday");
            default:
                return app.getLabel("Saturday");
                
        }
    }
    
    /**
     * Get short name.
     * @return String Day name abbreviation.
     */
    public String getShortName()
    {
        switch(this.code) {
            case Calendar.SUNDAY: 
                return "Ned";
            case Calendar.MONDAY:
                return "Pon";
            case Calendar.TUESDAY:
                return "Uto";
            case Calendar.WEDNESDAY:
                return "Sre";
            case Calendar.THURSDAY:
                return "Čet";
            case Calendar.FRIDAY:
                return "Pet";
            default:
                return "Sub";
                
        }
    }
    
    /**
     * Returns the index of the day in the month collection.
     * @return 
     */
    public int getIndex()
    {
        return this.index;
    }
    
    /**
     * Returns the code of the day (1 - 7).
     * @return code
     */
    public int getCode()
    {
        return this.code;
    }
    
    /**
     * Sets the value of the index of the day in the month collection.
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    public static Day getDayForDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Year year = new Year(calendar.get(Calendar.YEAR));
        Month month = year.getMonth(calendar.get(Calendar.MONTH));
        Day day = month.getDay(calendar.get(Calendar.DAY_OF_MONTH));
        return day;
    }

    @Override
    public int compareTo(Object t) {
        Day d = (Day)t;
        if(this.getMonth().getName().equals(d.getMonth().getName()) && 
           this.getIndex() == d.getIndex())
        {
            return 0;
        }
                        
        return -1;
    }
    
}
