/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Element;

/**
 *
 * @author Sinisa
 */
public class PortalModel {
    private ObservableList<Link> links = FXCollections.observableArrayList();
    private URL entryPoint;
    private SimpleBooleanProperty isTheaterModeProperty = new SimpleBooleanProperty();
    
    public PortalModel()
    {
        this.init();
    }
        
    public boolean getIsTheaterMode()
    {
        return this.getIsTheaterModeProperty().get();
    }
        
    public void setIsTheaterMode(boolean isTheaterMode)
    {
        this.getIsTheaterModeProperty().set(isTheaterMode);
    }
    
    public URL getEntryPoint()
    {
        return entryPoint;
    }
    
    /**
     * @return the isTheaterModeProperty
     */
    public SimpleBooleanProperty getIsTheaterModeProperty() {
        return isTheaterModeProperty;
    }
    
    public ObservableList<Link> getLinks()
    {
        return this.links;
    }
    
    public void loadFromXml(Element element)
    {
        
    }
    
    public void saveToXml(Element element)
    {
        
    }
    
    public void loadConfig(String fileName)
    {
        
    }
    
    public void saveConfig(String fileName)
    {
        
    }
    
    // Inicijalizacija podataka.
        
    private void init()
    {
        links.clear();
        
        List<LinkEntry> entries = new ArrayList<>();
        entries.add(new LinkEntry("Храм Св. Ђорђа", "file:///D:/Prosmart/Crkva/html/glavna1.html"));
        entries.add(new LinkEntry("Историја храма", "http://www.spc.rs/sr/crkva"));
        entries.add(new LinkEntry("Свештеници храма", "http://www.spc.rs/sr/crkva"));
        entries.add(new LinkEntry("Светиње храма", "http://www.spc.rs/sr/crkva"));
        entries.add(new LinkEntry("Делатности храма", "http://www.spc.rs/sr/crkva"));
        entries.add(new LinkEntry("Историја српске православне цркве Црква", "http://www.spc.rs/sr/crkva"));
        entries.add(new LinkEntry("Патријарх", "http://www.spc.rs/sr/njegova-svetost-patrijarh-srpski-irinej"));
        entries.add(new LinkEntry("Веронаука", "http://www.spc.rs/sr/veronauka"));               
        
        for(LinkEntry entry : entries)
        {
            Link link = new Link();
            link.setCaption(entry.caption);
            try {
                link.setURL(new URL(entry.url));
            } catch (MalformedURLException ex) {
                Logger.getLogger(PortalModel.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
            links.add(link);
        }
        
        try {
            entryPoint = new URL("file:/D:/Prosmart/Crkva/html/glavna.html");
        } catch (MalformedURLException ex) {
            Logger.getLogger(PortalModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}


