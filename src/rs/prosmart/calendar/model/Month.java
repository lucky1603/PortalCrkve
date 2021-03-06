/*
 * Month.java
 */
package rs.prosmart.calendar.model;

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

    /**
     * Constructor.
     */
    public Month() {
        name = "Unknown";
        countOfDays = 31;
    }
    
    /**
     * Constructor.
     * @param name String Name of month.
     * @param countOfDays int Count of days in the month.
     * @param initialize  boolean Should the days of the month be initialized.
     */
    public Month(String name, int countOfDays, boolean initialize)
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
    
    public void printOutFull()
    {
        String[] columns = new String[] {"\t", "\t", "\t", "\t", "\t", "\t", "\t"};
        
        for(int i = 0; i < this.countOfDays; i++)
        {
            Day d = this.days.get(i);
            columns[d.getCode() - 1] = d.getShortName() + "-" + d.getIndex() + "\t";
            if(d.getCode() == 7 || i == this.countOfDays - 1)
            {
                System.out.println(columns[0] + " " + columns[1] + " " + columns[2] + " " + columns[3] + " " + columns[4] + " " + columns[5] + " " + columns[6]);
                columns = new String[] {"\t", "\t", "\t", "\t", "\t", "\t", "\t"};
            }
        }        
    }
}
