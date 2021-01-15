package com.aoinc.wk3d2_assign_homeworktracker.view;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.aoinc.wk3d2_assign_homeworktracker.R;
import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;
import com.aoinc.wk3d2_assign_homeworktracker.view.adapter.HomeworkItemAdapter;
import com.aoinc.wk3d2_assign_homeworktracker.view.adapter.HomeworkItemAdapter.OnCheckChangedListenerI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class AssignmentListFragment extends Fragment
        implements OnCheckChangedListenerI {

    @BindView(R.id.homework_recyclerView)
    RecyclerView homeworkRecyclerView;

    private HomeworkItemAdapter homeworkItemAdapter;
    private AssignmentListFragmentInterface mainView;

    public interface AssignmentListFragmentInterface {
        void updateHomeworkCompleted(HomeworkItem homeworkItem);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.assignment_list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        homeworkItemAdapter = new HomeworkItemAdapter(new ArrayList<>(), this);
        homeworkRecyclerView.setAdapter(homeworkItemAdapter);
    }

    public void displayList(List<HomeworkItem> assignments) {
        homeworkItemAdapter.updateAssignments(assignments);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainView = (AssignmentListFragmentInterface) context;
    }

    @Override
    public void onCheckChanged(int position, boolean checked) {
        HomeworkItem homeworkItem = ((AssignmentViewerActivity) mainView).homeworkItemList.get(position);
        homeworkItem.setComplete(checked);
        mainView.updateHomeworkCompleted(homeworkItem);
    }
}
