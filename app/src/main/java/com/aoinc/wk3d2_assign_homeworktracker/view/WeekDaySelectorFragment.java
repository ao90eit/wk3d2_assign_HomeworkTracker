package com.aoinc.wk3d2_assign_homeworktracker.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aoinc.wk3d2_assign_homeworktracker.R;
import com.aoinc.wk3d2_assign_homeworktracker.util.Constants;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class WeekDaySelectorFragment extends Fragment {

    @BindView(R.id.week_menu_autoCompleteTextView)
    AutoCompleteTextView weekMenu;

    @BindView(R.id.days_tab_layout)
    TabLayout daysTabLayout;

    public interface WeekDaySelectorInterface {
        public void updateSelectedWeek(int weekSelection);
        public void updateSelectedDay(int tabPosition);
    }
    WeekDaySelectorInterface assignmentViewer;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        assignmentViewer = (WeekDaySelectorInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.week_day_selector_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        weekMenu.setAdapter(new ArrayAdapter<Integer>(view.getContext(),
                R.layout.week_dropdown_list_item, R.id.week_item_textView, Constants.WEEK_NUMS));
        weekMenu.setText("1", false);

        weekMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                Toast.makeText(view.getContext(), String.valueOf(pos), Toast.LENGTH_SHORT).show();
                assignmentViewer.updateSelectedWeek(pos + 1);
            }
        });

        daysTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                assignmentViewer.updateSelectedDay(tab.getPosition() + 1);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                return;
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                return;
            }
        });
    }
}
