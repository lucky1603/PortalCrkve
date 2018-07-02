/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.control;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rs.prosmart.calendar.model.CalendarModel;
import rs.prosmart.calendar.model.Month;

/**
 *
 * @author Sinisa
 */
public class CalendarPane extends VBox {
    private HBox commandPanel;
    private Button navLeft;
    private Button navRight;
    private Label lblMonth;
    private MonthPane monthPane;
    private CalendarModel model;
    
    public CalendarPane()
    {
        monthPane = new MonthPane();
        monthPane.prefWidthProperty().bind(this.widthProperty());
        monthPane.prefHeightProperty().bind(this.heightProperty().subtract(50));
        
        monthPane.getStyleClass().add("month");
        //monthPane.prefHeightProperty().bind(this.heightProperty() - 20);
        model = monthPane.getModel();
        navLeft = new Button("Prethodni");
        navLeft.setOnAction(e -> {
            // Model navigate left.
            model.navigateLeft();
        });
        
        navRight = new Button("Sledeci");
        navRight.setOnAction(e -> {
            model.navigateRight();
        });
        
        lblMonth = new Label(model.getDisplayMonth().getName());
        
        model.getDisplayMonthProperty().addListener((obj, stari, novi) -> {
            Month month = novi;
            lblMonth.setText(month.getName());
            monthPane.setMonth(month);
        });
        
        commandPanel = new HBox(5);
        commandPanel.getChildren().addAll(navLeft, lblMonth, navRight);
        
        this.setSpacing(10);
        this.getChildren().addAll(commandPanel, monthPane);
        this.getStyleClass().add("main");
        this.getStylesheets().add(getClass().getResource("calendar.css").toExternalForm());
        
        
    }
    
    public void fitSize(Node parent)
    {
        
    }
    
}
