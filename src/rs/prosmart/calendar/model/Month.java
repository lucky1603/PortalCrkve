/*
 * Month.java
 */
package rs.prosmart.calendar.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Month model class.
 * @author Sinisa
 */
public class Month {
    private String name;
    private int countOfDays;
    private final ObservableList<Day> days = FXCollections.observableArrayList();
    private SimpleBooleanProperty isCurrent = new SimpleBooleanProperty();
    private int index;

    /**
     * Constructor.
     */
    public Month() {
        index = 0;
        name = "Unknown";
        countOfDays = 31;
    }
    
    /**
     * Constructor.
     * @param name String Name of month.
     * @param countOfDays int Count of days in the month.
     * @param initialize  boolean Should the days of the month be initialized.
     */
    public Month(int index, String name, int countOfDays, boolean initialize)
    {
        this.name = name;
        this.countOfDays = countOfDays;
        if(initialize)
        {
            for(int i = 1; i <= countOfDays; i++)
            {
                days.add(new Day());
            }    
        }
        
    }
    
    /**
     * Returns the index of the month.
     * @return 
     */
    public int getIndex()
    {
        return this.index;
    }
    
    /**
     * Gets the count of the days in the month.
     * @return int Count of days.
     */
    public int getCountOfDays()
    {
        return this.countOfDays;
    }
    
    /**
     * Sets the count of days in the month.
     * @param count Count of days.
     */
    public void setCountOfDays(int count)
    {
        this.countOfDays = count;
    }
    
    /**
     * Gets the name of month.
     * @return 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Get the Day object for the given index.
     * @param i Zero-based index of day in month.
     * @return 
     */
    public Day getDay(int i)
    {
        return days.get(i-1);
    }
    
    /**
     * Sets the day on the given position.
     * @param i
     * @param day 
     */
    public void setDay(int i, Day day)
    {
        days.set(i, day);
    }
    
    /**
     * Get the list of days.
     * @return 
     */
    public ObservableList<Day> getDays()
    {
        return this.days;
    }
    
    /**
     * Is it current month.
     * @return boolean Is it current or not.
     */
    public boolean getIsCurrent()
    {
        return this.isCurrent.get();
    }
    
    /**
     * Sets month as current.
     * @param current Is it current or not.
     */
    public void setIsCurrent(boolean current)
    {
        this.isCurrent.set(current);
    }
    
    /**
     * Gets isBoolean property.
     * @return SimpleBooleanProperty IsCurrent.
     */
    public SimpleBooleanProperty getIsCurrentProperty()
    {
        return this.isCurrent;
    }
    
    /**
     * Test function, printing out the days.
     * @param count 
     */
    public void printOutDays(int count)
    {
        if(count > 0)
        {
            for(int i = 0; i < count; i++)
            {
                Day day = this.getDay(i);
                System.out.println(day.getName() + ", " + (i + 1) + "." + this.name);
            }
        } else {
            for(int i = 0; i < this.countOfDays; i++)
            {
                Day day = this.getDay(i);
                System.out.println(day.getName() + ", " + (i + 1) + "." + this.name);
            }
        }
    }
    
    public void printOutFull(CalendarModel model)
    {
        String[] columns = new String[] {"\t", "\t", "\t", "\t", "\t", "\t", "\t"};
        boolean currentDay = false;
        
        for(int i = 0; i < this.countOfDays; i++)
        {
            Day d = this.days.get(i);
            if(this.equals(model.getCurrentMonth()) && d.equals(model.getCurrentDay()))
            {
                currentDay = true;
            } else {
                currentDay = false;
            }
            if(currentDay)
            {
                columns[d.getCode() - 1] = d.getShortName() + "-" + d.getIndex() + "*" + "\t";
                
            } else {
                columns[d.getCode() - 1] = d.getShortName() + "-" + d.getIndex() + "\t";
            }
            
            if(d.getCode() == 7 || i == this.countOfDays - 1)
            {
                System.out.println(columns[0] + " " + columns[1] + " " + columns[2] + " " + columns[3] + " " + columns[4] + " " + columns[5] + " " + columns[6]);
                columns = new String[] {"\t", "\t", "\t", "\t", "\t", "\t", "\t"};
            }
        }        
    }
}
