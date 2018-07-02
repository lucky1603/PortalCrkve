/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.control;

import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import rs.prosmart.calendar.model.CalendarModel;
import rs.prosmart.calendar.model.Day;
import rs.prosmart.calendar.model.Month;

/**
 *
 * @author Sinisa
 */
public class MonthPane extends GridPane {
    CalendarModel model = new CalendarModel();
    ObservableList<DayPane> dayPanes = FXCollections.observableArrayList();
    public MonthPane()
    {     
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
                
                DayPane dPane = new DayPane(d);
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
        
    }
    
   
}