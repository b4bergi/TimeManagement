package com.example.dberghammer.timemanagement;

/**
 * Created by tmaier,dberghammer on 23.05.2016.
 */
public class NotizenTable {
    public static final String TABLE_NAME="Notizen";
    public static final String id ="_id";
    public static final String TITLE ="Title";
    public static final String DATE = "Date";
    public static final String TIMEBEFORE = "Timebefor";
    public static final String NOTE = "NOTE";
    public static final String[] ALL_COLUMS = { id + " AS _id", TITLE, DATE, TIMEBEFORE, NOTE};

    public static final String SQL_CREATE ="CREATE TABLE " +TABLE_NAME+ "(" +
            id + "INTEGER PRIMARY KEY,"+
            TITLE+ "TEXT NOT NULL,"+
            DATE+ "TEXT NOT NULL,"+
            TIMEBEFORE+ "TEXT NOT NULL,"+
            NOTE+ "TEXT NOT NULL,"+")";


}

