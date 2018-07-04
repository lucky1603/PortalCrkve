/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.control;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import rs.prosmart.PortalCrkve.ApplicationContext;
import rs.prosmart.calendar.model.CalendarModel;
import rs.prosmart.calendar.model.Day;
import rs.prosmart.calendar.model.Month;

/**
 *
 * @author Sinisa
 */
public class MonthPane extends GridPane {
    CalendarModel model;
    ObservableList<DayPane> dayPanes = FXCollections.observableArrayList();
    public MonthPane(CalendarModel model)
    {     
        this.model = model;
        this.setMonth(model.getCurrentMonth());        
    }
    
    public CalendarModel getModel()
    {
        return this.model;
    }
    
    public void setMonth(Month month)
    {
        this.getChildren().clear();
        List<Map<Integer, Day>> lines = model.getLinesForMonth(month);
        int rows = 0, columns = 7;
        for(/* Map<Integer, Day> line : lines */ int j = 0; j < lines.size(); j++)
        {
            rows++;
            Map<Integer, Day> line = lines.get(j);
            for(int i = 1; i <= 7; i++)
            {
                Day d = line.get(i);
                
                if(d == null)
                    continue;
                
                DayPane dPane = new DayPane(this.model, d);
                this.add(dPane, i - 1, j);                
            }
        }
        
        this.getRowConstraints().clear();
        this.getColumnConstraints().clear();
        
        for(int i = 0; i < rows; i++)
        {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / rows);
            this.getRowConstraints().add(rc);
        }
        
        for(int i = 0; i < 7; i ++)
        {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100 / 7.0);
            this.getColumnConstraints().add(cc);
        }
        
        this.setHgap(10);
        this.setVgap(10);
        
    }
    
   public void registerForEvents()
   {
       ApplicationContext app = ApplicationContext.getInstance();
       EventHandler handler = app.getEventHandler();
       if(handler != null)
       {
           this.dayPanes.forEach(p -> p.setOnMouseClicked(handler));           
       }
   }
}
