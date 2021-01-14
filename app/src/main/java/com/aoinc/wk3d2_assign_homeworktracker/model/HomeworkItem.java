package com.aoinc.wk3d2_assign_homeworktracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Homework")
public class HomeworkItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int databaseID;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "subject")
    private String subject;

    @ColumnInfo(name = "week")
    private int week;

    @ColumnInfo(name = "day")
    private int day;

    @ColumnInfo(name = "complete")
    private boolean isComplete;

    @Ignore
    public HomeworkItem(String title, String description, String subject, int week, int day, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.week = week;
        this.day = day;
        this.isComplete = isComplete;
    }

    public HomeworkItem(int databaseID, String title, String description, String subject, int week, int day, boolean isComplete) {
        this.databaseID = databaseID;
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.week = week;
        this.day = day;
        this.isComplete = isComplete;
    }

    public int getDatabaseID() {
        return databaseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
