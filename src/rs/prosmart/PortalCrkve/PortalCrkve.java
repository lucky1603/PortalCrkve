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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.Document;
import rs.prosmart.JSHandlers.JSHandlers;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 *
 * @author Sinisa
 */
public class PortalCrkve extends Application {
    private WebView webView;
    private ListView<LinkView> listView;
    private VBox vBox;
    private ObservableList<LinkView> linkViews = FXCollections.observableArrayList();
    private ObjectProperty<LinkView> selectedlinkViewProperty = new SimpleObjectProperty<LinkView>();
    
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
        
        this.selectedlinkViewProperty.addListener((property, oldVal, newVal) -> {
            ((LinkView) oldVal).getStyleClass().remove("selected");
            ((LinkView) newVal).getStyleClass().add("selected");
        });
        
        enableJSHandlers();
        //webView.getEngine().setCreatePopupHandler(popupHandler);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public LinkView getSelectedLinkView()
    {
        return selectedlinkViewProperty.get();
    }
    
    public void setSelectedLinkView(LinkView linkView)
    {
        this.selectedlinkViewProperty.set(linkView);
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
                this.selectLinkView(linkView0);
            });
            linkViews.add(linkView0);
            
            Link link1 = new Link();
            link1.setCaption("Yahoo");
            link1.setURL(new URL("http://www.yahoo.com"));            
            LinkView linkView1 = new LinkView(link1);
            linkView1.getStyleClass().add("submenu");
            //listView.getItems().add(linkView1);
            linkView1.setOnMouseClicked(e -> {
                this.selectLinkView(linkView1);
            });
            linkViews.add(linkView1);
            
            
            Link link2 = new Link();
            link2.setCaption("Google");
            link2.setURL(new URL("http://www.google.com"));
            LinkView linkview2 = new LinkView(link2);
            linkview2.getStyleClass().add("submenu");
            //listView.getItems().add(linkview2);
            linkview2.setOnMouseClicked(e -> {
                this.selectLinkView(linkview2);
            });
            linkViews.add(linkview2);
            
            Link link3 = new Link();
            link3.setCaption("Glavna");
            link3.setURL(new URL(getClass().getResource("Prezentacije/Glavna.html").toExternalForm()));
            LinkView linkview3 = new LinkView(link3);
            linkview3.getStyleClass().add("submenu");
            //listView.getItems().add(linkview3);
            linkview3.setOnMouseClicked(e -> {
                this.selectLinkView(linkview3);
            });
            linkViews.add(linkview3);
            
            Link link4 = new Link();
            link4.setCaption("Vaske");
            link4.setURL(new URL("http://95.140.126.52:15080/panic/"));
            LinkView linkview4 = new LinkView(link4);
            linkview4.getStyleClass().add("submenu");
            //listView.getItems().add(linkview4);
            linkview4.setOnMouseClicked(e -> {
                this.selectLinkView(linkview4);
            });
            linkViews.add(linkview4);
            
            Link link5 = new Link();
            link5.setCaption("Moj");
            link5.setURL(new URL(getClass().getResource("Prezentacije/jshandlers.html").toExternalForm()));
            LinkView linkview5 = new LinkView(link5);
            linkview5.getStyleClass().add("submenu");
            //listView.getItems().add(linkview5);
            linkview5.setOnMouseClicked(e -> {                
                this.selectLinkView(linkview5);
            });
            linkViews.add(linkview5);
            
            // Make first as selected
            this.setSelectedLinkView(linkViews.get(0));
            
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
    
    private void selectLinkView(LinkView linkView)
    {
        URL url = linkView.getLink().getURL();
        webView.getEngine().load(url.toExternalForm());
        setSelectedLinkView(linkView);
    }

    private void enableJSHandlers() {
        webView.getEngine().setPromptHandler(JSHandlers.getPromptHandler());
        webView.getEngine().setCreatePopupHandler(JSHandlers.getPopupHandler());
        webView.getEngine().setOnAlert(JSHandlers::alertHandler);
        webView.getEngine().setConfirmHandler(JSHandlers.getConfirmHandler());            
    }
    
}
