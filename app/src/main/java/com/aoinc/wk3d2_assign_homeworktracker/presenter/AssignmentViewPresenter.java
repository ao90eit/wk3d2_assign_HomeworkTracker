package com.aoinc.wk3d2_assign_homeworktracker.presenter;

import android.util.Log;

import androidx.room.Room;

import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;
import com.aoinc.wk3d2_assign_homeworktracker.model.db.HomeworkDatabase;
import com.aoinc.wk3d2_assign_homeworktracker.presenter.AssignmentViewerPresenterContract.*;

public class AssignmentViewPresenter implements AssignmentPresenterInterface {
    private AssignmentViewInterface assignmentView;
    private HomeworkDatabase homeworkDatabase;

    public AssignmentViewPresenter(AssignmentViewInterface assignmentView) {
        this.assignmentView = assignmentView;

        homeworkDatabase = Room.databaseBuilder(assignmentView.getContext(),
                HomeworkDatabase.class, HomeworkDatabase.DATABASE_NAME)
                .build();
    }

    @Override
    public void getDaysAssignments(int week, int day) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                assignmentView.updateAssignmentDisplay(homeworkDatabase.getHomeworkDAO()
                        .getAssignmentsForWeekDay(week, day));
            }
        }.start();
    }

    @Override
    public void addAssignment(HomeworkItem addAssignment) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                homeworkDatabase.getHomeworkDAO().addAssignment(addAssignment);
                assignmentView.runListUpdate();
            }
        }.start();
    }
}
