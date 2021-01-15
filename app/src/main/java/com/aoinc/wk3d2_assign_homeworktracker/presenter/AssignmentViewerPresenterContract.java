package com.aoinc.wk3d2_assign_homeworktracker.presenter;

import android.content.Context;

import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;

import java.util.List;

public interface AssignmentViewerPresenterContract {
    public interface AssignmentPresenterInterface {
        void getDaysAssignments(int week, int day);
//        void deleteAssignment();
        void addAssignment(HomeworkItem addAssignment);
        void updateAssignment(HomeworkItem updateAssignment);
    }

    public interface AssignmentViewInterface {
        Context getContext();
        void updateAssignmentDisplay(List<HomeworkItem> assignmentList);
        void runListUpdate();
    }
}
