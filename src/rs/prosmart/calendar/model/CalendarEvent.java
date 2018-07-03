/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.model;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.scene.image.Image;
import org.w3c.dom.Element;

/**
 *
 * @author Sinisa
 */
public class CalendarEvent {
    private String name;
    private String description;
    private Image img = null;
    private CalendarEventType eventType = CalendarEventType.TODAY;
    private Day day;
    private URL descriptionUrl;
    private URL imgUrl;
    
    /**
     * Constructor.
     * @param day 
     */
    public CalendarEvent(Day day)
    {
        this.day = day;
    }
        
    /**
     * Constructor.
     * @param day
     * @param name
     * @param description 
     */
    public CalendarEvent(Day day, String name, String description)
    {
        this(day);
        this.name = name;
        this.description = description;        
    }
    
    /**
     * Constructor.
     * @param day
     * @param name
     * @param description
     * @param img 
     */
    public CalendarEvent(Day day, String name, String description, Image img)
    {
        this(day, name, description);
        this.img = img;
    }
    
    /**
     * Gets name.
     * @return 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets description.
     * @return 
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * Gets day.
     * @return 
     */
    public Day getDay()
    {
        return day;
    }
    
    /**
     * Gets event type.
     * @return 
     */
    public CalendarEventType getEventType()
    {
        return this.eventType;
    }
    
    /**
     * Sets event type.
     * @param type 
     */
    public void setEventType(CalendarEventType type)
    {
        this.eventType = type;
    }
    
    public void loadConfig(org.w3c.dom.Element config) throws ParseException
    {
        Day day;
        String name;
        URL url;
        
        Element dateElement = (Element) config.getElementsByTagName("Date").item(0);                
        if(dateElement != null)
        {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
            Date date = dateFormat.parse(dateElement.getTextContent());
            day = Day.getDayForDate(date);
        }
        
        Element nameElement = (Element) config.getElementsByTagName("Name").item(0);
        if(nameElement != null)
        {
            
        }
    }
}
