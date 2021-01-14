package com.aoinc.wk3d2_assign_homeworktracker.presenter;

import com.aoinc.wk3d2_assign_homeworktracker.presenter.AssignmentViewerPresenterContract.*;

public class AssignmentViewPresenter implements AssignmentPresenterInterface {
    private AssignmentViewInterface assignmentViewInterface;

    public AssignmentViewPresenter(AssignmentViewInterface assignmentViewInterface) {
        this.assignmentViewInterface = assignmentViewInterface;
    }
}
