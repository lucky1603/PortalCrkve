/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.PortalCrkve;

import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import rs.prosmart.calendar.control.CalendarLinkDialog;
import rs.prosmart.calendar.control.CalendarPane;
import rs.prosmart.calendar.control.DayPane;

/**
 *
 * @author Sinisa
 */
public class VerticalLayout extends BorderPane implements EventHandler {
    private VBox itemsBox;
    private VBox commandBox;
    private final ObservableList<LinkView> linkViews = FXCollections.observableArrayList();
    private final ObservableList<LinkView> localLinkViews = FXCollections.observableArrayList();
    private SimpleObjectProperty<LinkView> selectedLinkViewProperty = new SimpleObjectProperty<>();
    private WebView connectedView;
    private PortalModel portalModel;
    private ImageView exitImageView;
    private ImageView startImageView;
    private ImageView homeImageView;
    private ImageView calendarImageView;
    private LinkView calendarLinkView;
    private CalendarPane calendarPane;
    private StackPane sPane;
    
    /**
     * Constructor
     */
    public VerticalLayout(WebView view, PortalModel model) throws MalformedURLException {
        super();
        portalModel = model;
        connectedView = view;
        ApplicationContext app = ApplicationContext.getInstance();
        app.setEventHandler(this);
        calendarPane = new CalendarPane(app.getCalendarModel());
        
        this.initMenu();
                
        this.selectedLinkViewProperty.addListener((property, oldVal, newVal) -> {
            if(oldVal != null)
            {
                ((LinkView) oldVal).getStyleClass().remove("selected");
            }            
            ((LinkView) newVal).getStyleClass().add("selected");
            selectLinkView(newVal);
        });
        
        if(this.linkViews.size() > 0)
        {
            this.setSelectedLinkView(this.linkViews.get(0));
        }
                       
        sPane = new StackPane();
        sPane.getChildren().add(connectedView);
        
        Image img = new Image(PortalCrkve.class.getResource("Slike/3D-Glasses-icon-64.png").toExternalForm());
        startImageView = new ImageView();
        startImageView.setImage(img);
        //startImageView.setScaleX(0.15);
        //startImageView.setScaleY(0.15);
        startImageView.setOpacity(0.8);
        
        // Mouse clicked.
        startImageView.setOnMouseClicked(e -> {
            URL url = this.portalModel.getEntryPoint();
            this.connectedView.getEngine().load(url.toExternalForm());
            this.portalModel.setIsTheaterMode(true);
        });
        
        // Touch
        startImageView.setOnTouchPressed(e -> {
            URL url = this.portalModel.getEntryPoint();
            this.connectedView.getEngine().load(url.toExternalForm());
            this.portalModel.setIsTheaterMode(true);
        });
        
        Image exitImg = new Image(PortalCrkve.class.getResource("Slike/Log-Out-icon-128.png").toExternalForm());
        exitImageView = new ImageView();
        exitImageView.setImage(exitImg);
        exitImageView.setScaleX(0.5);
        exitImageView.setScaleY(0.5);
        exitImageView.setOpacity(0.9);
        
        // Mouse click.
        exitImageView.setOnMouseClicked(e -> {            
            this.portalModel.setIsTheaterMode(false);
            LinkView linkView = this.linkViews.get(0);
            this.selectLinkView(linkView);            
        });
        
        // Touch.
        exitImageView.setOnTouchPressed(e -> {            
            this.portalModel.setIsTheaterMode(false);
            LinkView linkView = this.linkViews.get(0);
            this.selectLinkView(linkView);            
        });
        
        exitImageView.setVisible(false);
        
        Image calendarImage = new Image(PortalCrkve.class.getResource("Slike/Calendar-icon-64x64.png").toExternalForm());
        calendarImageView = new ImageView();
        calendarImageView.setImage(calendarImage);
        calendarImageView.setOpacity(0.8);
        calendarImageView.setOnMouseClicked(e -> {
            this.selectLinkView(calendarLinkView);
            this.calendarImageView.setVisible(false);
        });
        
        calendarImageView.setVisible(false);
        
        Image homeImg = new Image(PortalCrkve.class.getResource("Slike/home-icon-blue.png").toExternalForm());
        homeImageView = new ImageView();
        homeImageView.setImage(homeImg);
        homeImageView.setOpacity(0.8);
        
        // Mouse click.
        homeImageView.setOnMouseClicked(e -> {
            URL url = this.portalModel.getEntryPoint();
            this.connectedView.getEngine().load(url.toExternalForm());
        });
        
        // Touch.
        homeImageView.setOnTouchPressed(e -> {
            //URL url = this.getSelectedLinkView().getLink().getURL();
            URL url = this.portalModel.getEntryPoint();
            this.connectedView.getEngine().load(url.toExternalForm());
        });
        
        homeImageView.setVisible(false);        
                
        sPane.getChildren().add(exitImageView);
        StackPane.setAlignment(exitImageView, Pos.BOTTOM_RIGHT);        
        StackPane.setMargin(exitImageView, new Insets(0, 20, 30, 0 ));
        
        sPane.getChildren().add(homeImageView);
        StackPane.setAlignment(homeImageView, Pos.TOP_LEFT);
        StackPane.setMargin(homeImageView, new Insets(20, 0, 0, 20));
        
        sPane.getChildren().add(calendarImageView);
        StackPane.setAlignment(calendarImageView, Pos.TOP_LEFT);
        StackPane.setMargin(calendarImageView, new Insets(20, 0, 0, 20));
        
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        itemsBox.getChildren().add(spacer);
        
        Label lblInteractive = new Label("Интерактивна екскурзија");
        lblInteractive.getStyleClass().add("interactive");
        lblInteractive.setWrapText(true);
        //lblInteractive.setTextAlignment(TextAlignment.CENTER);

        commandBox = new VBox(5, lblInteractive, startImageView);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.getStyleClass().add("commands");
        
        itemsBox.getChildren().add(commandBox);        
        itemsBox.prefWidth(400);
        
        this.setLeft(itemsBox);
        this.setCenter(sPane);    
       
        
    }
            
    // Properties    
    /**
     * Gets portal model.
     * @return 
     */
    public PortalModel getModel()
    {
        return this.portalModel;
    }
            
    /**
     * Gets the selected link view.
     * @return LinkView selected link view object.
     */
    public LinkView getSelectedLinkView()
    {
        return this.selectedLinkViewProperty.get();
    }
    
    /**
     * Sets the selected link view property.
     * @param view New link view to be selected.
     */
    public void setSelectedLinkView(LinkView view)
    {
        this.selectedLinkViewProperty.set(view);
    }
    
    // Private functions
    
    /**
     * Initialization of menu items.
     */
    private void initMenu()
    {
        //listView.getStyleClass().add("lista");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String strTitle = "Данас је " + dateFormat.format(date);
        Label title = new Label(strTitle);
        title.getStyleClass().add("title");         

        this.linkViews.clear();
        for(Link link : this.portalModel.getLinks())
        {
            LinkView linkView = new LinkView(link);
            linkView.setOnMouseClicked(e -> {
                this.setSelectedLinkView(linkView);
            });
            this.linkViews.add(linkView);
        }        
        
        this.localLinkViews.clear();
        for(Link link : this.portalModel.getLocalLinks())
        {
            LinkView linkView = new LinkView(link);
            linkView.setOnMouseClicked(e -> {
                this.setSelectedLinkView(linkView);
            });
            this.localLinkViews.add(linkView);
        }

        itemsBox = new VBox(5);            
        itemsBox.getChildren().add(title);
        ////////////////
        Link calendarLink = new Link();
        calendarLink.setCaption("Kalendar");
        calendarLink.setURL(null);
        calendarLinkView = new LinkView(calendarLink);
        calendarLinkView.getStyleClass().add("submenu");
        calendarLinkView.setOnMouseClicked(e -> {
            //this.selectLinkView(linkView);
            this.setSelectedLinkView(calendarLinkView);
        });
        itemsBox.getChildren().add(calendarLinkView);
        ///////////////
        for(LinkView linkView : this.linkViews)
        {
            itemsBox.getChildren().add(linkView);
        }
        
        itemsBox.getChildren().add(new LinkSeparator());
        
        for(LinkView linkView : this.localLinkViews)
        {
            itemsBox.getChildren().add(linkView);
        }

        itemsBox.getStyleClass().add("vbox");        
    }

    /**
     * Sets the link view as selected and loads the content of its hyper link to the attached WebView object.
     * @param linkView LinkView to be selected.
     */
    private void selectLinkView(LinkView linkView) {
        URL url = linkView.getLink().getURL();
        if(url == null)
        {
            this.setCenter(calendarPane);
        } else {
            if(this.getCenter() == null || !this.getCenter().equals(sPane))
            {
                this.setCenter(sPane);
            }
            
            connectedView.getEngine().load(url.toExternalForm());
            setSelectedLinkView(linkView);
            connectedView.toBack();
        }
                
    }

    public void showNavigationMenu(boolean b) {
        if(!b)
        {
            this.setLeft(null);
            

        } else {
            this.setLeft(itemsBox);
        }     
        
        int idx = this.linkViews.indexOf(this.getSelectedLinkView());
        
        startImageView.setVisible(b);        
        exitImageView.setVisible(!b);
        homeImageView.setVisible(!b);
    }
    
    public void showCalendarLink(URL url)
    {
        
    }

    @Override
    public void handle(Event event) {
        DayPane dPane = (DayPane) event.getSource();
        if(dPane != null)
        {
            URL url = dPane.getLinkUrl();
            if(url != null)
            {
                CalendarLinkDialog dlg = new CalendarLinkDialog(dPane.getLinkUrl());
                dlg.showAndWait();
            }
        }
    }
}
