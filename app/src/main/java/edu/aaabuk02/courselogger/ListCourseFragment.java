package edu.aaabuk02.courselogger;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ListCourseFragment extends Fragment {
    private static final String TAG = "ListCourseFragment";
    ArrayList<CourseContact> courseContacts;
    ArrayList<DeadlineInfoContact> deadlineInfoContacts;

    RecyclerView courseContactList;
    RecyclerView deadlineInfoList;

    CourseAdapter courseAdapter;
    DeadlineAdapter deadlineAdapter;
    public ListCourseFragment() {
        // Required empty public constructor
    }

    public static ListCourseFragment newInstance(String param1, String param2) {
        ListCourseFragment fragment = new ListCourseFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CourseDataSource cs = new CourseDataSource(getContext());
        ArrayList<CourseContact> courseContacts;
        ArrayList<DeadlineInfoContact> deadlineInfoContacts;

        try{
            cs.open();
            deadlineInfoContacts = cs.getDeadlineContacts();
            cs.close();
            RecyclerView deadlineInfoList = view.findViewById(R.id.rvDeadlines);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            deadlineInfoList.setLayoutManager(layoutManager);
            deadlineAdapter = new DeadlineAdapter(deadlineInfoContacts, getContext());
            deadlineAdapter.setDelete(true);
            deadlineInfoList.setAdapter(deadlineAdapter);
        } catch (Exception e) {
            Log.d(TAG,"Fail");
        }

        try{
            cs.open();
            courseContacts = cs.getCourseContacts();
            cs.close();
            RecyclerView courseContactList = view.findViewById(R.id.rvCourseContacts);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            courseContactList.setLayoutManager(layoutManager);
            courseAdapter = new CourseAdapter(courseContacts, getContext());
            courseAdapter.setDelete(true);
            courseContactList.setAdapter(courseAdapter);
        } catch (Exception e) {
            Log.d(TAG,"Fail");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        CourseDataSource cs = new CourseDataSource(getContext());
        ArrayList<CourseContact> courseContacts;
        ArrayList<DeadlineInfoContact> deadlineInfoContacts;

        try{
            cs.open();
            deadlineInfoContacts = cs.getDeadlineContacts();
            cs.close();
            RecyclerView deadlineInfoList = view.findViewById(R.id.rvDeadlines);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            deadlineInfoList.setLayoutManager(layoutManager);
            deadlineAdapter = new DeadlineAdapter(deadlineInfoContacts, getContext());
            deadlineAdapter.setDelete(true);
            deadlineInfoList.setAdapter(deadlineAdapter);
        } catch (Exception e) {
            Log.d(TAG,"Fail");
        }

        try{
            cs.open();
            courseContacts = cs.getCourseContacts();
            cs.close();
            RecyclerView courseContactList = view.findViewById(R.id.rvCourseContacts);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            courseContactList.setLayoutManager(layoutManager);
            courseAdapter = new CourseAdapter(courseContacts, getContext());
            courseAdapter.setDelete(true);
            courseContactList.setAdapter(courseAdapter);
        } catch (Exception e) {
            Log.d(TAG,"Fail");
        }
    }
}