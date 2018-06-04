/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Sinisa
 */
public class LinkView extends StackPane {
    private Link link;    
    private Label caption;
    private Rectangle boundingRect;

    
    public LinkView(Link link)
    {
        this.link = link;

        caption = new Label();
        caption.setText(link.getCaption());        
        caption.getStyleClass().add("caption");
        this.getChildren().addAll(/* boundingRect,*/ caption);
        this.setAlignment(Pos.CENTER);                
        this.getStyleClass().add("frame");
    }
    
    public Link getLink()
    {
        return this.link;
    }
    
}
