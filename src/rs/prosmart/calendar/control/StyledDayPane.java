/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.control;

import java.net.URL;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import rs.prosmart.PortalCrkve.ApplicationContext;
import rs.prosmart.calendar.model.CalendarEventType;
import rs.prosmart.calendar.model.CalendarModel;
import rs.prosmart.calendar.model.Day;

/**
 *
 * @author Sinisa
 */
public class StyledDayPane extends VBox {
    private Label lblMonth;
    private Label lblNumber;
    private Label lblDay;
    private Label lblEvent;
    private Day day;
    private CalendarModel model;
    private URL linkUrl;
    
    public StyledDayPane(CalendarModel model, Day day)
    {
        this.day = day;
        this.model = model;
                

        lblNumber = new Label(String.format("%d", day.getIndex()));
        lblNumber.getStyleClass().add("index");
        lblEvent = new Label("ABC");
        lblEvent.setVisible(false);
        
        lblNumber.getStyleClass().add("calDayIndex");
        lblEvent.getStyleClass().add("calEvent");
        lblEvent.setWrapText(true);
        lblEvent.setTextAlignment(TextAlignment.CENTER);
                        
        this.getChildren().addAll(lblNumber, lblEvent);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        if(this.day.equals(this.model.getCurrentDay()))
        {
            this.getStyleClass().add("today");
        } else {
            this.getStyleClass().add("day");
        }
        
        this.model.getCalendarEvents().forEach(v -> {
            if(v.getDay().compareTo(this.day) == 0) {
                if(v.getEventType() == CalendarEventType.HOLIDAY)
                {
                    lblEvent.setText(v.getName());
                    lblEvent.setVisible(true);
                    this.getStyleClass().add("holiday");
                    this.linkUrl = v.getLinkUrl();                    
                }
                
                if(v.getEventType() == CalendarEventType.TASK)
                {
                    lblEvent.setText(v.getName());
                    lblEvent.setVisible(true);
                    this.linkUrl = v.getLinkUrl();
                }
                
                if(v.getEventType() == CalendarEventType.SUNDAY)
                {
                    lblEvent.setText(v.getName());
                    lblEvent.setVisible(true);
                    this.getStyleClass().add("sunday");
                    this.linkUrl = v.getLinkUrl();
                }
            }
        });
        
        ApplicationContext app = ApplicationContext.getInstance();
        this.setOnMouseClicked(app.getEventHandler());
                        
        //this.getStylesheets().add(getClass().getResource("calendar.css").toExternalForm());
        
    }
    
    public URL getLinkUrl()
    {
        return this.linkUrl;
    }
}
