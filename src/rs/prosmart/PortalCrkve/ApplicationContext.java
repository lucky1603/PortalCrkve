/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import rs.prosmart.calendar.model.CalendarEvent;
import rs.prosmart.calendar.model.CalendarModel;

/**
 *
 * @author Sinisa
 */
public class ApplicationContext {
    List<CalendarEvent> events = new ArrayList<>();
    Map<String, Object> generalSettings = new HashMap<>();    
    CalendarModel calendarModel = new CalendarModel();
    WeakReference handler;
    
    private static class Holder {
        static final ApplicationContext INSTANCE = new ApplicationContext();
    }
    
    /**
     * Gets instance of this class.
     * @return 
     */
    public static ApplicationContext getInstance()
    {
        return Holder.INSTANCE;
    }
    
    public Map<String, Object> getGeneralSettings()
    {
        return this.generalSettings;
    }
    
    public CalendarModel getCalendarModel()
    {
        return this.calendarModel;            
    }
    
    /**
     * Sets the actual event handler for the child component events.
     * @param handler 
     */
    public void setEventHandler(EventHandler handler)
    {
        if(this.handler != null)
        {
            this.handler.clear();
        }
                        
        this.handler = new WeakReference<>(handler);
    }
    
    /**
     * Gets the actual event handler for the child components.
     * @return Object which handles the control events.
     */
    public EventHandler getEventHandler()
    {
        if(this.handler != null)
            return (EventHandler) this.handler.get();
        return null;
    }
    
    /**
     * Private constructor.
     */
    private ApplicationContext()
    {
        
    }
    
    public void loadConfiguration() throws IOException, ParseException
    {
        String configFile = this.generalSettings.get("AppPath") + "/" + this.generalSettings.get("ConfigFileName");
        // open xml.
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new File(configFile));
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(SceneDocument.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        }
        
        NodeList events = doc.getDocumentElement().getElementsByTagName("Event");
        for(int i = 0; i < events.getLength(); i++)
        {
            Element eventElement = (Element) events.item(i);
            if(eventElement != null)
            {
                CalendarEvent event = new CalendarEvent();
                event.loadConfig(eventElement);
                this.calendarModel.getCalendarEvents().add(event);
            }
        }
        
    }
}
