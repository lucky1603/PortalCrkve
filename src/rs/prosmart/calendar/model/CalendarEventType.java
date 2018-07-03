/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.prosmart.calendar.model;

/**
 *
 * @author Sinisa
 */
public enum CalendarEventType {
    TODAY(0), HOLIDAY(1), MEETING(2),TASK(3);
    private int value;
    private CalendarEventType(int value)
    {
        this.value = value;
    }
}
