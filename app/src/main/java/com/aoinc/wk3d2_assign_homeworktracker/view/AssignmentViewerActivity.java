package com.aoinc.wk3d2_assign_homeworktracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.aoinc.wk3d2_assign_homeworktracker.R;
import com.aoinc.wk3d2_assign_homeworktracker.presenter.AssignmentViewPresenter;
import com.aoinc.wk3d2_assign_homeworktracker.presenter.AssignmentViewerPresenterContract.AssignmentViewInterface;
import com.aoinc.wk3d2_assign_homeworktracker.util.Constants;
import com.aoinc.wk3d2_assign_homeworktracker.view.WeekDaySelectorFragment.WeekDaySelectorInterface;

public class AssignmentViewerActivity extends AppCompatActivity
        implements AssignmentViewInterface, WeekDaySelectorInterface {

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

        updateAssignmentDisplay(0);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateAssignmentDisplay(int tabPosition) {
        assignmentListFragment.displayText(String.valueOf(tabPosition + 1));
    }
}