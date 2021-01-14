package com.aoinc.wk3d2_assign_homeworktracker.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;

@Database(version = 1, entities = HomeworkItem.class)
public abstract class HomeworkDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "homework.db";
    public abstract HomeworkDAO getHomeworkDAO();
}
