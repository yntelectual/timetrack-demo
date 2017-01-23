package me.matus.demo.timetrack.frontend.model;

import java.util.Date;

/**
 * DTO holding information for single time record.
 * @author Matus_Majchrak
 */
public class TimeRecordDTO {
    
    private Date start;
    private Date end;
    private String email;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
