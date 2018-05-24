
package rs.prosmart.PortalCrkve;

import java.net.URL;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Sinisa
 */
public class Link {
    private SimpleStringProperty captionProperty;
    private SimpleObjectProperty<URL> urlProperty;
    private SimpleObjectProperty<Image> thumbnailProperty;

    public Link() {
        this.thumbnailProperty = new SimpleObjectProperty<>();
        this.urlProperty = new SimpleObjectProperty<>();
        this.captionProperty = new SimpleStringProperty();       
    }

    /**
     * @return the captionProperty
     */
    public SimpleStringProperty getCaptionProperty() {
        return captionProperty;
    }
    
    /**
     * 
     * @return Caption of the link.
     */
    public String getCaption()
    {
        return captionProperty.get();
    }
    
    /**
     * Sets the caption of the link
     * @param newValue Caption of the link
     */
    public void setCaption(String newValue)
    {
        captionProperty.set(newValue);
    }

    /**
     * @return the addressProperty
     */
    public SimpleObjectProperty<URL> getURLProperty() {
        return urlProperty;
    }
    
    /***
     * Address property
     * @return URL
     */
    public URL getURL()
    {
        return urlProperty.get();
    }
    
    public void setURL(URL newValue)
    {
        urlProperty.set(newValue);
    }

    /**
     * @return the thumbnailProperty
     */
    public SimpleObjectProperty<Image> getThumbnailProperty() {
        return thumbnailProperty;
    }
    
    public Image getThumbnail()
    {
        return thumbnailProperty.get();
    }
    
    public void setThumbnail(Image img)
    {
        thumbnailProperty.set(img);
    }
    
    
    
}
