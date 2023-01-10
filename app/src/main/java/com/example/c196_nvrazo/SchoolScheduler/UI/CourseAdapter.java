package com.example.c196_nvrazo.SchoolScheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196_nvrazo.R;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseNameView;
        private final TextView courseStartView;
        private final TextView courseEndView;

        private CourseViewHolder(View itemview) {
            super(itemview);
            courseNameView = itemview.findViewById(R.id.CourseNameListView);
            courseStartView = itemview.findViewById(R.id.CourseStartDateListView);
            courseEndView = itemview.findViewById(R.id.CourseEndDateListView);


            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, CourseInfo.class);
                    intent.putExtra("Id", current.getCourseID());
                    intent.putExtra("Name", current.getCourseName());
                    intent.putExtra("StartDate", current.getCourseStartDate());
                    intent.putExtra("EndDate", current.getCourseEndDate());
                    intent.putExtra("Status", current.getCourseStatus());
                    intent.putExtra("InstructorName", current.getCourseInstructorName());
                    intent.putExtra("InstructorEmail", current.getCourseInstructorEmail());
                    intent.putExtra("InstructorPhone", current.getCourseInstructorPhone());
                    intent.putExtra("TermId", current.getTermId());

                    context.startActivity(intent);

                }

            });
        }

    }

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public com.example.c196_nvrazo.SchoolScheduler.UI.CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);

        return new com.example.c196_nvrazo.SchoolScheduler.UI.CourseAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.c196_nvrazo.SchoolScheduler.UI.CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            String name = current.getCourseName();
            String start = current.getCourseStartDate();
            String end = current.getCourseEndDate();
            holder.courseNameView.setText(name);
            holder.courseStartView.setText(start);
            holder.courseEndView.setText(end);


        } else {
            holder.courseNameView.setText("No course name");
        }

    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public void setCourses(List<Course> course) {
        mCourses = course;
        notifyDataSetChanged();

    }
}


