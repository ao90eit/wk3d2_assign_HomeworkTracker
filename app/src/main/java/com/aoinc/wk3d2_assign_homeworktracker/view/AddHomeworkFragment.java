package com.aoinc.wk3d2_assign_homeworktracker.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aoinc.wk3d2_assign_homeworktracker.R;
import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;
import com.aoinc.wk3d2_assign_homeworktracker.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddHomeworkFragment extends Fragment {
    @BindView(R.id.add_week_menu)
    AutoCompleteTextView weekMenu;

    @BindView(R.id.add_day_menu)
    AutoCompleteTextView dayMenu;

    @BindView(R.id.add_title_EditText)
    EditText titleEditText;

    @BindView(R.id.add_subject_EditText)
    EditText subjectEditText;

    @BindView(R.id.add_description_EditText)
    EditText descriptionEditText;

    List<String> daysAbbr;

    public interface AddHomeworkInterface {
        void addHomeworkItem(HomeworkItem homeworkItem);
        void popAddFragment();
    }

    AddHomeworkInterface assignmentView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        assignmentView = (AddHomeworkInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_homework_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        weekMenu.setAdapter(new ArrayAdapter<Integer>(view.getContext(),
                R.layout.week_dropdown_list_item, R.id.week_item_textView, Constants.WEEK_NUMS));
        weekMenu.setText("1", false);

        daysAbbr = Arrays.asList(Constants.getDayAbbrs(view.getContext()));

        dayMenu.setAdapter(new ArrayAdapter<String>(view.getContext(),
                R.layout.day_dropdown_list_item, R.id.week_item_textView, Constants.getDayAbbrs(view.getContext())));
        dayMenu.setText("M", false);
    }

    @OnClick(R.id.add_button)
    public void onClickAdd(View view) {
        String title = titleEditText.getText().toString().trim();
        String subject = subjectEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        int week = Integer.parseInt(weekMenu.getText().toString().trim());
        int day = daysAbbr.indexOf(dayMenu.getText().toString().trim()) + 1;

        assignmentView.addHomeworkItem(new HomeworkItem(
                title, description, subject, week, day, false));

        titleEditText.setText("");
        subjectEditText.setText("");
        descriptionEditText.setText("");
        weekMenu.setText("1");
        dayMenu.setText("M");

        assignmentView.popAddFragment();
    }
}
