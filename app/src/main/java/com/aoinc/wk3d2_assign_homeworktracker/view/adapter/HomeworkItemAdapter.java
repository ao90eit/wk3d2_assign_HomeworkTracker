package com.aoinc.wk3d2_assign_homeworktracker.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aoinc.wk3d2_assign_homeworktracker.R;
import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;

import org.w3c.dom.Text;

import java.lang.annotation.Annotation;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class HomeworkItemAdapter extends RecyclerView.Adapter<HomeworkItemAdapter.HomeworkViewHolder> {

    private List<HomeworkItem> assignmentList;

    private OnCheckChangedListenerI onCheckChangedListenerI;
    public interface OnCheckChangedListenerI {
        void onCheckChanged(int position, boolean checked);
    }

    public HomeworkItemAdapter(List<HomeworkItem> assignmentList, OnCheckChangedListenerI onCheckChangedListenerI) {
        this.assignmentList = assignmentList;
        this.onCheckChangedListenerI = onCheckChangedListenerI;
    }

    public void updateAssignments(List<HomeworkItem> homeworkItemList) {
        assignmentList = homeworkItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeworkViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homework_item_layout, parent, false),
                onCheckChangedListenerI);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        HomeworkItem assignment = assignmentList.get(position);

        holder.title.setText(assignment.getTitle());
        holder.subject.setText(assignment.getSubject());
        holder.completed.setChecked(assignment.isComplete());
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    class HomeworkViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

        @BindView(R.id.homework_title_textView)
        TextView title;

        @BindView(R.id.homework_subject_textView)
        TextView subject;

        @BindView(R.id.homework_complete_checkBox)
        CheckBox completed;

        private OnCheckChangedListenerI onCheckChangedListenerI;

        public HomeworkViewHolder(@NonNull View itemView, OnCheckChangedListenerI onCheckChangedListenerI) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            completed.setOnCheckedChangeListener(this);
            this.onCheckChangedListenerI = onCheckChangedListenerI;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            onCheckChangedListenerI.onCheckChanged(getLayoutPosition(), isChecked);
        }
    }
}
