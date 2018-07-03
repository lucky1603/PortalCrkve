/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import rs.prosmart.calendar.model.CalendarEvent;

/**
 *
 * @author Sinisa
 */
public class ApplicationContext {
    List<CalendarEvent> events = new ArrayList<>();
    Map<String, Object> generalSettings = new HashMap<>();    
    
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
    
    /**
     * Private constructor.
     */
    private ApplicationContext()
    {
        
    }
    
    public void loadConfiguration() throws IOException
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
    }
}
