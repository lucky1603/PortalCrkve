/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.Document;
import rs.prosmart.JSHandlers.JSHandlers;

/**
 *
 * @author Sinisa
 */
public class PortalCrkve extends Application {
    private WebView webView;
    private ListView<LinkView> listView;
    private VBox vBox;
    
    @Override
    public void start(Stage primaryStage) {
        
        SplitPane splitPane = new SplitPane();
        
        
        webView = new WebView();
        webView.getEngine().load(getClass().getResource("Prezentacije/Glavna.html").toExternalForm());
        
        this.initMenu();
        
        splitPane.getItems().addAll(vBox, webView );
        splitPane.setDividerPosition(0, 0.19);
        
        Scene scene = new Scene(splitPane, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("crkva.css").toExternalForm());
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Callback<PopupFeatures, WebEngine> popupHandler = pFeatures -> {
            Stage stage = new Stage();
            stage.setTitle("Sinisa");
            
            WebView popupView = new WebView();
            WebView secondView = new WebView();
            Document doc = popupView.getEngine().getDocument();
            secondView.getEngine().load("http://www.google.com");
            
            
            Label lbl = new Label("adresa");
            TextField txtfld = new TextField();
            Button btnTest = new Button("Test");
            HBox bar = new HBox(20, lbl, txtfld, btnTest);            
            VBox root = new VBox(10, bar, popupView);
            Scene popupScene = new Scene(root);
            stage.setScene(popupScene);
            
            stage.show();
            
            
            return popupView.getEngine();
        };
        
        enableJSHandlers();
        //webView.getEngine().setCreatePopupHandler(popupHandler);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void initMenu()
    {
        try {
            
            //listView.getStyleClass().add("lista");
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = new Date();
            String strTitle = "Today is " + dateFormat.format(date);
            Label title = new Label(strTitle);
            title.getStyleClass().add("title");
            
            
            
            Link link0 = new Link();
            link0.setCaption("NASLOV");                  
            LinkView linkView0 = new LinkView(link0);
            linkView0.getStyleClass().add("submenu");
            //listView.getItems().add(linkView0);
            linkView0.setOnMouseClicked(e -> {
                URL url = linkView0.getLink().getURL();
                webView.getEngine().load(url.toExternalForm());
            });
            
            Link link1 = new Link();
            link1.setCaption("Yahoo");
            link1.setURL(new URL("http://www.yahoo.com"));            
            LinkView linkView1 = new LinkView(link1);
            linkView1.getStyleClass().add("submenu");
            //listView.getItems().add(linkView1);
            linkView1.setOnMouseClicked(e -> {
                URL url = linkView1.getLink().getURL();
                webView.getEngine().load(url.toExternalForm());
            });
            
            
            Link link2 = new Link();
            link2.setCaption("Google");
            link2.setURL(new URL("http://www.google.com"));
            LinkView linkview2 = new LinkView(link2);
            linkview2.getStyleClass().add("submenu");
            //listView.getItems().add(linkview2);
            linkview2.setOnMouseClicked(e -> {
                URL url = linkview2.getLink().getURL();
                webView.getEngine().load(url.toExternalForm());
            });
            
            Link link3 = new Link();
            link3.setCaption("Glavna");
            link3.setURL(new URL(getClass().getResource("Prezentacije/Glavna.html").toExternalForm()));
            LinkView linkview3 = new LinkView(link3);
            linkview3.getStyleClass().add("submenu");
            //listView.getItems().add(linkview3);
            linkview3.setOnMouseClicked(e -> {
                URL url = linkview3.getLink().getURL();
                webView.getEngine().load(url.toExternalForm());
            });
            
            Link link4 = new Link();
            link4.setCaption("Vaske");
            link4.setURL(new URL("http://95.140.126.52:15080/panic/"));
            LinkView linkview4 = new LinkView(link4);
            linkview4.getStyleClass().add("submenu");
            //listView.getItems().add(linkview4);
            linkview4.setOnMouseClicked(e -> {
                URL url = linkview4.getLink().getURL();
                webView.getEngine().load(url.toExternalForm());
            });
            
            Link link5 = new Link();
            link5.setCaption("Moj");
            link5.setURL(new URL(getClass().getResource("Prezentacije/jshandlers.html").toExternalForm()));
            LinkView linkview5 = new LinkView(link5);
            linkview5.getStyleClass().add("submenu");
            //listView.getItems().add(linkview5);
            linkview5.setOnMouseClicked(e -> {
                URL url = linkview5.getLink().getURL();
                webView.getEngine().load(url.toExternalForm());
            });
            

            VBox itemBox = new VBox(5, title, linkView1, linkview2, linkview3, linkview4, linkview5);
            itemBox.getStyleClass().add("flow");
            itemBox.setAlignment(Pos.CENTER);
            
            
            vBox = new VBox(itemBox);
            vBox.getStyleClass().add("vbox");
            vBox.prefWidth(250);
            //vBox.setPadding(new Insets(200, 0, 0, 0));
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(PortalCrkve.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    private void enableJSHandlers() {
        webView.getEngine().setPromptHandler(JSHandlers.getPromptHandler());
        webView.getEngine().setCreatePopupHandler(JSHandlers.getPopupHandler());
        webView.getEngine().setOnAlert(JSHandlers::alertHandler);
        webView.getEngine().setConfirmHandler(JSHandlers.getConfirmHandler());            
    }
    
}
