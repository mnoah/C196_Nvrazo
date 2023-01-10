package com.example.c196_nvrazo.SchoolScheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196_nvrazo.R;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Assessment;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<com.example.c196_nvrazo.SchoolScheduler.UI.AssessmentAdapter.AssessmentViewHolder> {

        class AssessmentViewHolder extends RecyclerView.ViewHolder {
            private final TextView assessmentNameView;
            private final TextView assessmentEndView;

            private AssessmentViewHolder(View itemview) {
                super(itemview);
               assessmentNameView  = itemview.findViewById(R.id.AssessmentNameListView);
               assessmentEndView   = itemview.findViewById(R.id.AssessmentEndDateListView);


                itemview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        final Assessment current = mAssessment.get(position);
                        Intent intent = new Intent(context, AssessmentInfo.class);
                        intent.putExtra("Id", current.getAssessmentID());
                        intent.putExtra("Name", current.getAssessmentTitle());
                        intent.putExtra("EndDate", current.getAssessmentEndDate());
                        intent.putExtra("CourseId", current.getCourseId());

                        context.startActivity(intent);

                    }

                });
            }

        }

        private List<Assessment> mAssessment;
        private final Context context;
        private final LayoutInflater mInflater;

        public AssessmentAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @NonNull
        @Override
        public com.example.c196_nvrazo.SchoolScheduler.UI.AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);

            return new com.example.c196_nvrazo.SchoolScheduler.UI.AssessmentAdapter.AssessmentViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.c196_nvrazo.SchoolScheduler.UI.AssessmentAdapter.AssessmentViewHolder holder, int position) {
            if (mAssessment != null) {
                Assessment current = mAssessment.get(position);
                String name = current.getAssessmentTitle();
                String end = current.getAssessmentEndDate();
                holder.assessmentNameView.setText(name);
                holder.assessmentEndView.setText(end);


            } else {
                holder.assessmentNameView.setText("No course name");
            }

        }

        @Override
        public int getItemCount() {
            return mAssessment.size();
        }

        public void setAssessment(List<Assessment> assessments) {
            mAssessment = assessments;
            notifyDataSetChanged();

        }
    }

