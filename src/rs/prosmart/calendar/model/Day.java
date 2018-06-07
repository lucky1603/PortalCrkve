/*
 * Day.java
 */
package rs.prosmart.calendar.model;

import java.util.Calendar;

/**
 * Day model class.
 * @author Sinisa
 */
public class Day {    
    private int code = Calendar.SUNDAY;
    private int index = 0;
    
    /**
     * Constructor.
     */
    public Day()
    {
        
    }
    
    /**
     * Constructor.
     * @param code 
     */
    public Day(int code)
    {
        this.code = code;
        this.index = 0;
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
