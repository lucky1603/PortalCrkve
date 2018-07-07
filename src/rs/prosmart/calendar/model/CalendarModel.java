/*
 * CaledarModel.java
 */
package rs.prosmart.calendar.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import rs.prosmart.PortalCrkve.ApplicationContext;

/**
 * @author Sinisa
 */
public class CalendarModel {
    private Locale locale;
    private ResourceBundle labels;
    private Year year;
    private SimpleObjectProperty<Month> currentMonth = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Month> displayMonth = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Day> currentDay = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Day> displayDay = new SimpleObjectProperty<>();    
    private List<CalendarEvent> events = new ArrayList<>();
            
    /**
     * Constructor.
     */
    public CalendarModel(Locale locale)
    {
        this(locale, new Date());
    }
    
    /**
     * Constructor.
     * @param date  A specific date.
     */
    public CalendarModel(Locale locale, Date date)
    {
        this.setLocale(locale);
        this.setDate(date);
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
    
    public void setDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.year = new Year(calendar.get(Calendar.YEAR));
        this.currentMonth.set(this.year.getMonth(calendar.get(Calendar.MONTH)));
        this.displayMonth.set(this.year.getMonth(calendar.get(Calendar.MONTH)));
        this.currentDay.set(this.currentMonth.get().getDay(calendar.get(Calendar.DAY_OF_MONTH)));
        this.displayDay.set(this.currentMonth.get().getDay(calendar.get(Calendar.DAY_OF_MONTH)));
    }
    
    public void navigateLeft()
    {
        int index = year.getMonths().indexOf(this.getDisplayMonth());
        if(index > 0)
        {
            index --;
            this.displayMonth.set(year.getMonth(index));
        }
    }
    
    public void navigateRight()
    {
        int index = year.getMonths().indexOf(this.getDisplayMonth());
        if(index < year.getMonths().size() - 1)
        {
            index ++;
            this.displayMonth.set(year.getMonth(index));
        }
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
    
    /**
     * Sets the working locale.
     * @param locale 
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        this.labels = ResourceBundle.getBundle("LabelsBundle", this.locale);
    }
    
    /**
     * Gets localized string.
     * @param key
     * @return 
     */
    public String getLabel(String key)
    {
        if(!key.isEmpty() && this.labels.containsKey(key))
        {
            return this.labels.getString(key);
        }
        
        return key;
    }
    
    public List<Map<Integer, Day>> getLinesForMonth(Month month)
    {
        List<Map<Integer, Day>> lines = new ArrayList<>();
        Map<Integer, Day> line = createLine();
        Day d = null;
        for(int i = 0; i < month.getCountOfDays(); i++)
        {
            d = month.getDay(i+1);
            line.put(d.getCode(), d);
            if(d.getCode() == 7)
            {
                lines.add(line);
                line = createLine();
            }
        }
        
        if(d != null && d.getCode() != 7)
        {
            lines.add(line);
        }
        
        return lines;
    }
    
    public List<CalendarEvent> getCalendarEvents()
    {
        return this.events;
    }
    
    public List<String> getDaysOfWeek()
    {
        ApplicationContext app = ApplicationContext.getInstance();
        List<String> days = new ArrayList<>();
        days.add(app.getLabel("Sunday"));
        days.add(app.getLabel("Monday"));
        days.add(app.getLabel("Tuesday"));
        days.add(app.getLabel("Wednesday"));
        days.add(app.getLabel("Thursday"));
        days.add(app.getLabel("Friday"));
        days.add(app.getLabel("Saturday"));
        return days;
    }
    
    private Map<Integer, Day> createLine()
    {
        Map<Integer, Day> line = new HashMap<>();
        line.put(1, null);
        line.put(2, null);
        line.put(3, null);
        line.put(4, null);
        line.put(5, null);
        line.put(6, null);
        line.put(7, null);        
        
        return line;
    }




}
