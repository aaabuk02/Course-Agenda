package edu.aaabuk02.courselogger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter {

    private ArrayList<CourseContact> courseData;
    private View.OnClickListener myOnItemClickListener;
    private Context parentContext;
    private boolean isDeleting;
    private boolean isInc;
    private boolean isDec;
    private Object CourseViewHolder;

    public class CourseViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewCourseName;
        public TextView textViewCourseDesc;
        public TextView textViewTargetStudyHours;
        public Button decrementStudyHoursButton;
        public Button incrementStudyHoursButton;
        public Button deleteButton;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseName = itemView.findViewById(R.id.textCourseName);
            textViewCourseDesc = itemView.findViewById(R.id.textCourseDesc);
            textViewTargetStudyHours = itemView.findViewById(R.id.textTargetStudyHours);
            decrementStudyHoursButton = itemView.findViewById(R.id.buttonDecrement);
            incrementStudyHoursButton = itemView.findViewById(R.id.buttonIncrement);
            deleteButton = itemView.findViewById(R.id.buttonDeleteCourse);
        }

        public TextView getTextViewCourseName() {
            return textViewCourseName;
        }

        public TextView getTextViewCourseDesc() {
            return textViewCourseDesc;
        }

        public TextView getTextViewTargetStudyHours() {
            return textViewTargetStudyHours;
        }

        public Button getIncrementStudyHoursButton() {
            return incrementStudyHoursButton;
        }

        public Button getDecrementStudyHoursButton() {
            return decrementStudyHoursButton;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }


    }

    public CourseAdapter(ArrayList<CourseContact> arrayList, Context context) {
        courseData = arrayList;
        parentContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CourseViewHolder cvh = (CourseViewHolder) holder;
        cvh.getTextViewCourseName().setText(courseData.get(position).getCourseName());
        cvh.getTextViewCourseDesc().setText(courseData.get(position).getCourseDesc());
        cvh.getTextViewTargetStudyHours().setText(courseData.get(position).getStudyHourTarget());

        cvh.getIncrementStudyHoursButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementItem(position);

            }
        });

        cvh.getDecrementStudyHoursButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementItem(position);
            }
        });

        if (isDeleting) {
            cvh.getDeleteButton().setVisibility(View.VISIBLE);
            cvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        } else {
            cvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return courseData.size();
    }

    private void deleteItem(int position) {
        CourseContact courseContact = courseData.get(position);
        CourseDataSource cs = new CourseDataSource(parentContext);
        try {
            cs.open();
            boolean didDelete = cs.deleteCourseContact(courseContact.getCourseContactID());
            cs.close();
            if (didDelete) {
                courseData.remove(position);
                notifyDataSetChanged();
            } else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void incrementItem(int position) {
        CourseContact courseContact = courseData.get(position);
        int currentStudy = Integer.parseInt(courseContact.getStudyHourTarget());
        currentStudy++;
        courseContact.setStudyHourTarget(Integer.toString(currentStudy));
        CourseDataSource cs = new CourseDataSource(parentContext);
        boolean didInc;
        try {
            cs.open();
            didInc = cs.updateCourseContact(courseContact);
            cs.close();
            if (didInc) {
                notifyDataSetChanged();
            } else {
                Toast.makeText(parentContext, "Inc Failed!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(parentContext, "Inc Failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void decrementItem(int position) {
        CourseContact courseContact = courseData.get(position);
        int currentStudy = Integer.parseInt(courseContact.getStudyHourTarget());
        currentStudy--;
        if(currentStudy < 0)
        {
            currentStudy = 0;
        }
        courseContact.setStudyHourTarget(Integer.toString(currentStudy));
        CourseDataSource cs = new CourseDataSource(parentContext);
        boolean didDec;
        try {
            cs.open();
            didDec = cs.updateCourseContact(courseContact);
            cs.close();
            if (didDec) {
                notifyDataSetChanged();
            } else {
                Toast.makeText(parentContext, "Dec Failed!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(parentContext, "Dec Failed!", Toast.LENGTH_LONG).show();
        }
    }


    public void setDelete(boolean b) {
        isDeleting = b;
    }

}
