package com.aoinc.wk3d2_assign_homeworktracker.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aoinc.wk3d2_assign_homeworktracker.R;
import com.aoinc.wk3d2_assign_homeworktracker.model.HomeworkItem;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeworkItemAdapter extends RecyclerView.Adapter<HomeworkItemAdapter.HomeworkViewHolder> {

    private List<HomeworkItem> assignmentList;

    public HomeworkItemAdapter(List<HomeworkItem> assignmentList) {
        this.assignmentList = assignmentList;
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
                .inflate(R.layout.homework_item_layout, parent, false));
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

    class HomeworkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.homework_title_textView)
        TextView title;

        @BindView(R.id.homework_subject_textView)
        TextView subject;

        @BindView(R.id.homework_complete_checkBox)
        CheckBox completed;

        public HomeworkViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
