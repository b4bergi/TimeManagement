package com.example.dberghammer.timemanagement;

import java.util.Date;

/**
 * Created by dberghammer on 23.05.2016.
 */
public class Event {
    private String name;
    private Date d;
    private int tagev;
    private String notiz;

    public Event(String notiz, String name, Date d, int tagev) {
        this.notiz = notiz;
        this.name = name;
        this.d = d;
        this.tagev = tagev;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public int getTagev() {
        return tagev;
    }

    public void setTagev(int tagev) {
        this.tagev = tagev;
    }

    public String getNotiz() {
        return notiz;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }
}
