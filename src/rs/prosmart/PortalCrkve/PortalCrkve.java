/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserCore;
import com.teamdev.jxbrowser.chromium.internal.Environment;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import java.io.IOException;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import rs.prosmart.JSHandlers.JSHandlers;
import rs.prosmart.calendar.control.CalendarPane;


/**
 *
 * @author Sinisa
 */
public class PortalCrkve extends Application {
    private WebView webView;
    private Browser browser;
    private BrowserView browserView;
    private CookieManager manager;
    private CalendarPane calendarPane;
    
    @Override
    public void start(Stage primaryStage) throws MalformedURLException, ParseException {     
        
        // I approach.
        String currentDir = System.getProperty("user.dir");
        ApplicationContext app = ApplicationContext.getInstance();
        app.setLocale(new Locale("ru", "RU"));
        app.getGeneralSettings().put("AppPath", currentDir);
        app.getGeneralSettings().put("ConfigFileName", "PortalCrkve.xml");           
        
        try {
            app.loadConfiguration();
        } catch (IOException ex) {
            Logger.getLogger(PortalCrkve.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        browser = new Browser();
//        browserView = new BrowserView(browser);                        
        webView = new WebView();        
        webView.getEngine().load(getClass().getResource("Prezentacije/Glavna.html").toExternalForm());
        
        PortalModel model = new PortalModel();
//        BorderVerticalLayout verticalLayout = new BorderVerticalLayout(browserView, model);
        
        VerticalLayout verticalLayout = new VerticalLayout(webView, model);
        
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
        
//        browserView.widthProperty().addListener((ovalue, staro, novo) ->{
//            Document doc = webView.getEngine().getDocument();
//                if(doc != null)
//                {
//                    Element el = (Element) doc.getElementById("glavna");
//                    if(el != null)
//                    {
//                        el.setAttribute("width", novo.toString());                
//                    }
//                    
//                }
//        });
        
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
    
    @Override
    public void stop()
    {
        //browser.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception
    {
        // On Mac OS X Chromium engine must be initialized in non-UI thread.
        if (Environment.isMac()) {
            BrowserCore.initialize();
        }
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
