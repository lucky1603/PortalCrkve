/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.model;

import java.net.MalformedURLException;
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
    private URL linkUrl;
    
    public CalendarEvent() {
        
    }
    
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
     * Gets the link URL.
     * @return URL Address of the link to navigate to.
     */
    public URL getLinkUrl()
    {
        return this.linkUrl;
    }
    
    /**
     * Sets the link address.
     * @param url 
     */
    public void setLinkUrl(URL url)
    {
        this.linkUrl = url;
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
    
    /**
     * Load event from configuration node.
     * @param config
     * @throws ParseException
     * @throws MalformedURLException 
     */
    public void loadConfig(org.w3c.dom.Element config) throws ParseException, MalformedURLException
    {        
        // Do daljnjeg. I ovo ce se uskoro ucitavati iz konfiguracije.        
        this.eventType = CalendarEventType.valueOf(config.getAttribute("type"));
        
        Element dateElement = (Element) config.getElementsByTagName("date").item(0);                
        if(dateElement != null)
        {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
            Date date = dateFormat.parse(dateElement.getTextContent());
            this.day = Day.getDayForDate(date);
        }
        
        Element nameElement = (Element) config.getElementsByTagName("name").item(0);
        if(nameElement != null)
        {
            this.name = nameElement.getTextContent();
        }
        
        Element urlElement = (Element) config.getElementsByTagName("url").item(0);
        if(urlElement != null)
        {
            this.linkUrl = new URL(urlElement.getTextContent());
        }
    }
}
