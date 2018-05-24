/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Sinisa
 */
public class LinkView extends StackPane {
    private Link link;    
    private Text caption;
    private Rectangle boundingRect;

    
    public LinkView(Link link)
    {
        this.link = link;
        caption = new Text();
        caption.setText(link.getCaption());        
        caption.getStyleClass().add("caption");

        
        this.getChildren().add(caption);
        this.setAlignment(Pos.CENTER);                
        this.getStyleClass().add("frame");
    }
    
    public Link getLink()
    {
        return this.link;
    }
    
}
