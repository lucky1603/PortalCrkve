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
import javafx.scene.control.Label;
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
    private Label caption;
    private Rectangle boundingRect;

    
    public LinkView(Link link)
    {
        this.link = link;

        caption = new Label();
        caption.setText(link.getCaption());        
        caption.getStyleClass().add("caption");
        
        
        
        
//        boundingRect = new Rectangle();
//        boundingRect.getStyleClass().add("rect");
        
//        boundingRect.heightProperty().bind(this.heightProperty());  
//        this.widthProperty().addListener((o, oldVal, newVal) -> {
//            boundingRect.setWidth(this.widthProperty().get() - this.getInsets().getLeft() - this.getInsets().getRight());
//        });
        
        

        
        this.getChildren().addAll(/* boundingRect,*/ caption);
        this.setAlignment(Pos.CENTER);                
        this.getStyleClass().add("frame");
    }
    
    public Link getLink()
    {
        return this.link;
    }
    
}
