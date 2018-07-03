/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.control;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import rs.prosmart.calendar.model.CalendarModel;
import rs.prosmart.calendar.model.Day;

/**
 *
 * @author Sinisa
 */
public class DayPane extends VBox {
    private Label lblMonth;
    private Label lblNumber;
    private Label lblDay;
    private Day day;
    private CalendarModel model;
    
    public DayPane(CalendarModel model, Day day)
    {
        this.day = day;
        this.model = model;
        
        
        lblMonth = new Label(day.getMonth().getName());
        lblNumber = new Label(String.format("%d", day.getIndex()));
        lblNumber.getStyleClass().add("index");
        lblDay = new Label(day.getName());
        
        lblMonth.getStyleClass().add("calMonth");
        lblNumber.getStyleClass().add("calDayIndex");
        lblDay.getStyleClass().add("calDayName");
        
        this.getChildren().addAll(lblMonth, lblNumber, lblDay);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        if(this.day.equals(this.model.getCurrentDay()))
        {
            this.getStyleClass().add("today");
        } else {
            this.getStyleClass().add("day");
        }
        
        //this.getStylesheets().add(getClass().getResource("calendar.css").toExternalForm());
        
    }
}
