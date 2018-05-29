/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import rs.prosmart.JSHandlers.JSHandlers;

/**
 *
 * @author Sinisa
 */
public class PortalCrkve extends Application {
    private WebView webView;
    
    @Override
    public void start(Stage primaryStage) throws MalformedURLException {       
        webView = new WebView();
        //webView.getEngine().load(getClass().getResource("Prezentacije/Glavna.html").toExternalForm());
        PortalModel model = new PortalModel();
        VerticalLayout verticalLayout = new VerticalLayout(webView, model);
        model.getIsTheaterModeProperty().addListener((o, stari, novi) -> {
            if(model.getIsTheaterMode())
            {
                verticalLayout.showNavigationMenu(false);           
            } else {
                verticalLayout.showNavigationMenu(true);                
            }
        });

        Scene scene = new Scene(verticalLayout, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("crkva.css").toExternalForm());
        
        primaryStage.setTitle("Portal Crkve");
        primaryStage.setScene(scene);
        primaryStage.show();
                        
        enableJSHandlers();                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
        
    /**
     * Enable java script event handlers.
     */
    private void enableJSHandlers() {
        webView.getEngine().setPromptHandler(JSHandlers.getPromptHandler());
        webView.getEngine().setCreatePopupHandler(JSHandlers.getPopupHandler());
        webView.getEngine().setOnAlert(JSHandlers::alertHandler);
        webView.getEngine().setConfirmHandler(JSHandlers.getConfirmHandler());            
    }
    
}
