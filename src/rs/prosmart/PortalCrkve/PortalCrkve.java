/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import java.net.CookieManager;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import rs.prosmart.JSHandlers.JSHandlers;
import rs.prosmart.calendar.model.CalendarModel;
import rs.prosmart.calendar.model.Month;
import rs.prosmart.calendar.model.Year;

/**
 *
 * @author Sinisa
 */
public class PortalCrkve extends Application {
    private WebView webView;
    private CookieManager manager;
    
    @Override
    public void start(Stage primaryStage) throws MalformedURLException {     
//        manager = new CookieManager();
//        CookieHandler.setDefault(manager);
//        manager.getCookieStore().removeAll();

        CalendarModel calModel = new CalendarModel();
        System.out.println(calModel.printToday());
        for(int i = 0; i < calModel.getYear().getMonths().size(); i++)
        {
            Month month = calModel.getYear().getMonth(i);
            System.out.println("\n\n\n");
            System.out.println(month.getName());
            month.printOutFull();
        }
        
        webView = new WebView();
        //webView.getEngine().load(getClass().getResource("Prezentacije/Glavna.html").toExternalForm());
        PortalModel model = new PortalModel();
        BorderVerticalLayout verticalLayout = new BorderVerticalLayout(webView, model);
        model.getIsTheaterModeProperty().addListener((o, stari, novi) -> {
            if(model.getIsTheaterMode())
            {
                verticalLayout.showNavigationMenu(false);           
            } else {
                verticalLayout.showNavigationMenu(true);                
            }
        });

        webView.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> o, Number stari, Number novi) {                
                Document doc = webView.getEngine().getDocument();
                if(doc != null)
                {
                    Element el = (Element) doc.getElementById("glavna");
                    if(el != null)
                    {
                        el.setAttribute("width", novi.toString());                
                    }
                    
                }
                
            }
        });
        
        webView.heightProperty().addListener((o, stari, novi) -> {
            Document doc = webView.getEngine().getDocument();
            if(doc != null) {
                Element el = (Element) doc.getElementById("glavna");
                if(el != null)
                {
                    el.setAttribute("height", novi.toString());                
                }                
            }            
        });
        
        
        Scene scene = new Scene(verticalLayout, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("crkva.css").toExternalForm());
        
        primaryStage.setTitle("Portal Crkve");
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
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
