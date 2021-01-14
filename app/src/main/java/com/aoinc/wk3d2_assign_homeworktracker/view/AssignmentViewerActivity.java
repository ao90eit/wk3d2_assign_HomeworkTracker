package com.aoinc.wk3d2_assign_homeworktracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aoinc.wk3d2_assign_homeworktracker.R;
import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;
import com.aoinc.wk3d2_assign_homeworktracker.presenter.AssignmentViewPresenter;
import com.aoinc.wk3d2_assign_homeworktracker.presenter.AssignmentViewerPresenterContract.AssignmentViewInterface;
import com.aoinc.wk3d2_assign_homeworktracker.view.AddHomeworkFragment.AddHomeworkInterface;
import com.aoinc.wk3d2_assign_homeworktracker.view.WeekDaySelectorFragment.WeekDaySelectorInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignmentViewerActivity extends AppCompatActivity
        implements AssignmentViewInterface, WeekDaySelectorInterface, AddHomeworkInterface {

    private int selectedDay = 1;
    private int selectedWeek = 1;

    AssignmentViewPresenter assignmentViewPresenter;
    AssignmentListFragment assignmentListFragment;
    AddHomeworkFragment addHomeworkFragment = new AddHomeworkFragment();

    @BindView(R.id.floating_add_button)
    FloatingActionButton floatingAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_viewer);

        ButterKnife.bind(this);

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

    @Override
    public void addHomeworkItem(HomeworkItem homeworkItem) {
        assignmentViewPresenter.addAssignment(homeworkItem);
    }

    @Override
    public void popAddFragment() {
        getSupportFragmentManager().popBackStackImmediate();
        enableFloatingAddButton(true);
    }

    public void enableFloatingAddButton(boolean enabled) {
        if (enabled)
            floatingAddButton.show();
        else
            floatingAddButton.hide();
    }

    @OnClick(R.id.floating_add_button)
    void onClickFloatingAdd(View view) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.full_page_frame, addHomeworkFragment)
                .addToBackStack(addHomeworkFragment.getTag())
                .commit();

        enableFloatingAddButton(false);
    }
}