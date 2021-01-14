package com.aoinc.wk3d2_assign_homeworktracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;

import com.aoinc.wk3d2_assign_homeworktracker.R;
import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;
import com.aoinc.wk3d2_assign_homeworktracker.presenter.AssignmentViewPresenter;
import com.aoinc.wk3d2_assign_homeworktracker.presenter.AssignmentViewerPresenterContract.AssignmentViewInterface;
import com.aoinc.wk3d2_assign_homeworktracker.util.Constants;
import com.aoinc.wk3d2_assign_homeworktracker.view.WeekDaySelectorFragment.WeekDaySelectorInterface;

import java.util.List;

public class AssignmentViewerActivity extends AppCompatActivity
        implements AssignmentViewInterface, WeekDaySelectorInterface {

    private int selectedDay = 1;
    private int selectedWeek = 1;

    AssignmentViewPresenter assignmentViewPresenter;
    AssignmentListFragment assignmentListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_viewer);

//        ButterKnife.bind(this);

        assignmentViewPresenter = new AssignmentViewPresenter(this);
        assignmentListFragment = (AssignmentListFragment)
                getSupportFragmentManager().findFragmentById(R.id.assignment_display_fragment);

        // TEST ONLY
        assignmentViewPresenter.addAssignment(new HomeworkItem(
        "Do calculations", "Math teacher expects calculus things.",
                "Math", 1, 1, false));

        assignmentViewPresenter.addAssignment(new HomeworkItem(
                "Blow things up", "We get the best homework",
                "Chemistry", 1, 1, false));

        assignmentViewPresenter.addAssignment(new HomeworkItem(
                "Do calculations", "Math teacher expects calculus things.",
                "Math", 2, 4, false));

        assignmentViewPresenter.addAssignment(new HomeworkItem(
                "Blow things up", "We get the best homework",
                "Chemistry", 7, 6, false));
        // END TEST

        retrieveDisplayedAssignments();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateAssignmentDisplay(List<HomeworkItem> assignmentList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assignmentListFragment.displayList(assignmentList);
            }
        });
    }

    @Override
    public void runListUpdate() {
        retrieveDisplayedAssignments();
    }


    @Override
    public void updateSelectedWeek(int weekSelection) {
        selectedWeek = weekSelection;
        retrieveDisplayedAssignments();
    }

    @Override
    public void updateSelectedDay(int tabPosition) {
        selectedDay = tabPosition;
        retrieveDisplayedAssignments();
    }

    private void retrieveDisplayedAssignments() {
        assignmentViewPresenter.getDaysAssignments(selectedWeek, selectedDay);
    }

}