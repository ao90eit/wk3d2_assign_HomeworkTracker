package com.aoinc.wk3d2_assign_homeworktracker.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;

import java.util.List;

@Dao
public interface HomeworkDAO {

//    @Query("SELECT * FROM Homework")
//    public List<HomeworkItem> getAllAssignments();

    @Query("SELECT * FROM Homework WHERE (week = :week AND day = :day)")
    public List<HomeworkItem> getAssignmentsForWeekDay(int week, int day);

    @Delete
    public void deleteAssignment(HomeworkItem... deleteHomework);

    @Insert
    public void addAssignment(HomeworkItem... addHomework);

    @Update
    public void updateAssignments(HomeworkItem homeworkItem);
}

