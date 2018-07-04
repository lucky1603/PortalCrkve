/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.control;

import java.awt.Color;
import java.net.URL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rs.prosmart.PortalCrkve.PortalCrkve;

/**
 *
 * @author Sinisa
 */
public class CalendarLinkDialog extends Stage {
    private WebView webView;
    private ImageView closeImageView;
    private URL url;
    
    public CalendarLinkDialog(URL url)
    {   
        super(StageStyle.UNDECORATED);
        
        this.initModality(Modality.APPLICATION_MODAL);
        
        webView = new WebView();
        webView.getEngine().load(url.toExternalForm());

        Image img = new Image(PortalCrkve.class.getResource("Slike/Calendar-icon-64x64.png").toExternalForm());
        closeImageView = new ImageView();
        closeImageView.setImage(img);
        //closeImageView.setOpacity(0.5);
        closeImageView.setOnMouseClicked(e -> {
            this.close();
        });

        StackPane sPane = new StackPane();
        sPane.getChildren().addAll(webView, closeImageView);
        StackPane.setAlignment(closeImageView, Pos.TOP_LEFT);        
        StackPane.setMargin(closeImageView, new Insets(20, 0, 0, 20));
        Scene scene = new Scene(sPane, 600, 600);
        this.setScene(scene);                   
    }        
}
