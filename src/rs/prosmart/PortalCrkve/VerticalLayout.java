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
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 *
 * @author Sinisa
 */
public class VerticalLayout extends SplitPane {
    private VBox frameBox;
    private VBox itemsBox;
    private final ObservableList<LinkView> linkViews = FXCollections.observableArrayList();
    private SimpleObjectProperty<LinkView> selectedLinkViewProperty = new SimpleObjectProperty<>();
    private WebView connectedView;
    private PortalModel portalModel;
    private ImageView exitImageView;
    private ImageView startImageView;
    private ImageView homeImageView;
    
    /**
     * Constructor
     */
    public VerticalLayout(WebView view, PortalModel model) throws MalformedURLException {
        super();
        portalModel = model;
        connectedView = view;
        
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
                       
        StackPane sPane = new StackPane();
        sPane.getChildren().add(connectedView);
        
        Image img = new Image(PortalCrkve.class.getResource("Slike/Actions-media-playback-start-icon.png").toExternalForm());
        startImageView = new ImageView();
        startImageView.setImage(img);
        startImageView.setScaleX(0.3);
        startImageView.setScaleY(0.3);
        startImageView.setOpacity(0.8);
        startImageView.setOnMouseClicked(e -> {
            this.portalModel.setIsTheaterMode(true);
        });
        
        Image exitImg = new Image(PortalCrkve.class.getResource("Slike/Log-Out-icon-128.png").toExternalForm());
        exitImageView = new ImageView();
        exitImageView.setImage(exitImg);
        //exitImageView.setScaleX(0.15);
        //exitImageView.setScaleY(0.15);
        exitImageView.setOpacity(0.9);
        exitImageView.setOnMouseClicked(e -> {
            this.portalModel.setIsTheaterMode(false);
        });
        
        exitImageView.setVisible(false);
        
        Image homeImg = new Image(PortalCrkve.class.getResource("Slike/home-icon-blue.png").toExternalForm());
        homeImageView = new ImageView();
        homeImageView.setImage(homeImg);
        exitImageView.setScaleX(0.5);
        exitImageView.setScaleY(0.5);
        homeImageView.setOpacity(0.8);
        homeImageView.setOnMouseClicked(e -> {
            URL url = this.getSelectedLinkView().getLink().getURL();
            this.connectedView.getEngine().load(url.toExternalForm());
        });
        
        homeImageView.setVisible(false);
        
        
        sPane.getChildren().add(startImageView);
        sPane.setAlignment(Pos.CENTER);
        
        sPane.getChildren().add(exitImageView);
        StackPane.setAlignment(exitImageView, Pos.BOTTOM_RIGHT);        
        StackPane.setMargin(exitImageView, new Insets(0, 20, 30, 0 ));
        
        sPane.getChildren().add(homeImageView);
        StackPane.setAlignment(homeImageView, Pos.TOP_LEFT);
        StackPane.setMargin(homeImageView, new Insets(20, 0, 0, 20));
        
        this.getItems().addAll(frameBox, sPane);
        this.setDividerPosition(0, 0.19);

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
                linkView.getStyleClass().add("submenu");
                linkView.setOnMouseClicked(e -> {
                    //this.selectLinkView(linkView);
                    this.setSelectedLinkView(linkView);
                });
                this.linkViews.add(linkView);
            }            
                        
            itemsBox = new VBox(5);            
            itemsBox.getChildren().add(title);
            for(LinkView linkView : this.linkViews)
            {
                itemsBox.getChildren().add(linkView);
            }
            
            itemsBox.getStyleClass().add("flow");
            itemsBox.setAlignment(Pos.CENTER);
                        
            frameBox = new VBox(itemsBox);
            frameBox.getStyleClass().add("vbox");
            frameBox.prefWidth(250);                        
            
        
    }

    /**
     * Sets the link view as selected and loads the content of its hyper link to the attached WebView object.
     * @param linkView LinkView to be selected.
     */
    private void selectLinkView(LinkView linkView) {
        URL url = linkView.getLink().getURL();
        connectedView.getEngine().load(url.toExternalForm());
        setSelectedLinkView(linkView);
        int index = this.linkViews.indexOf(this.getSelectedLinkView());
        if(this.startImageView == null)
        {
            return;
        }
        
        if(index == 0)
        {
            this.startImageView.setVisible(true);
        } else {
            this.startImageView.setVisible(false);
        }
    }

    public void showNavigationMenu(boolean b) {
        if(!b)
        {
            this.getItems().remove(frameBox);

        } else {
            this.getItems().add(0, frameBox);
            this.setDividerPosition(0, 0.18);
        }     
        
        int idx = this.linkViews.indexOf(this.getSelectedLinkView());
        
        startImageView.setVisible(b);        
        exitImageView.setVisible(!b);
        homeImageView.setVisible(!b);
    }
}
