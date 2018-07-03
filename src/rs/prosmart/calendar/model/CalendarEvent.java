/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.model;

import javafx.scene.image.Image;

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
    
    public CalendarEvent(Day day)
    {
        this.day = day;
    }
        
    public CalendarEvent(Day day, String name, String description)
    {
        this(day);
        this.name = name;
        this.description = description;        
    }
    
    public CalendarEvent(Day day, String name, String description, Image img)
    {
        this(day, name, description);
        this.img = img;
    }
    
    
}
