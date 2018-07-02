/*
 * Day.java
 */
package rs.prosmart.calendar.model;

import java.util.Calendar;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Day model class.
 * @author Sinisa
 */
public class Day {    
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
        switch(this.code) {
            case Calendar.SUNDAY: 
                return "Nedelja";
            case Calendar.MONDAY:
                return "Ponedeljak";
            case Calendar.TUESDAY:
                return "Utorak";
            case Calendar.WEDNESDAY:
                return "Sreda";
            case Calendar.THURSDAY:
                return "Četvrtak";
            case Calendar.FRIDAY:
                return "Petak";
            default:
                return "Subota";
                
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
    
}
