/*
 * Year.java
 */
package rs.prosmart.calendar.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Year model for the calendar model.
 * @author Sinisa
 */
public class Year {
    private String name;
    private boolean prestupna = false;
    private ObservableList<Month> months = FXCollections.observableArrayList();
    private int counter = 1;
        
    /**
     * Constructor.
     */
    public Year()
    {
        this.name = "Unknown";        
    }
    
    /**
     * Constructor.
     * @param year Four digit integer number that represents the desired year.
     */
    public Year(int year)
    {
        if(year % 4 == 0)
        {
            this.prestupna = true;
        }
        
        this.name = String.format("%d", year);
    
        this.initMonths();    
        
        try {
            this.calibrate();
        } catch (ParseException ex) {
            Logger.getLogger(Year.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the Month object for the given index.
     * @param idx Integer index of a month in the months collection.
     * @return Month object.
     */
    public Month getMonth(int idx) {
        return this.months.get(idx);
    }
    
    /**
     * Gets the list of the Month objects.
     * @return 
     */
    public ObservableList<Month> getMonths()
    {
        return this.months;
    }
    
    /**
     * Initialize 'Months' collection.
     */
    private void initMonths() {
        months.add(new Month(1, "Januar", 31, false));
        if(this.prestupna)
        {
            months.add(new Month(2, "Februar", 29, false));
        } else {
            months.add(new Month(2, "Februar", 28, false));
        }
        
        months.add(new Month(3, "Mart", 31, false));
        months.add(new Month(4, "April", 30, false));
        months.add(new Month(5, "Maj", 31, false));
        months.add(new Month(6, "Jun", 30, false));
        months.add(new Month(7, "Jul", 31, false));
        months.add(new Month(8, "Avgust", 31, false));
        months.add(new Month(9, "Septembar", 30, false));
        months.add(new Month(10, "Oktobar", 31, false));
        months.add(new Month(11, "Novembar", 30, false));
        months.add(new Month(12, "Decembar", 31, false));
    }

    private void calibrate() throws ParseException {
        String sDate1="01/01/" + this.name;  
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
        Calendar calendar = Calendar.getInstance(Locale.GERMAN);        
        calendar.setTime(date1);                
        
        counter = calendar.get(Calendar.DAY_OF_WEEK);
        for(Month month : this.months)
        {
            for(int i = 0; i < month.getCountOfDays(); i++)
            {
                Day d = this.getWeekdayByIndex(counter);
                d.setIndex(i + 1);     
                d.setMonth(month);
                month.getDays().add(d);                
                counter++;
                if(counter == 8)
                {
                    counter = 1;
                }
            }
        }
                
    }
    
    private Day getWeekdayByIndex(int idx)
    {
        return new Day(idx);        
    }
}
