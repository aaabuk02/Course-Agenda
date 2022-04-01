package edu.aaabuk02.courselogger;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SaveCourseFragment extends Fragment {

    private static final String TAG = "Save Course Frag";

    private CourseContact currentCourseContact = new CourseContact();
    private DeadlineInfoContact currentDeadlineInfoContact = new DeadlineInfoContact();

    public EditText editTextCourseName;
    public EditText editTextCourseDesc;
    public EditText editTextStudyHours;
    public EditText editTextAddress;
    public EditText editTextState;
    public EditText editTextCity;
    public EditText editTextZipCode;
    public EditText editTextDeadlineInfo;
    public TextView textViewCoordinates;
    public TextView textViewDeadlineInfo;
    public TextView textViewDeadlineDate;
    public Button buttonSaveDeadline;
    public Button buttonSaveCourse;
    public CalendarView datePickerDeadline;
    public String selectedDeadlineDate;
    public String selectedDeadlineInfo;

    public SaveCourseFragment() {
        // Required empty public constructor
    }

    public static SaveCourseFragment newInstance(String param1, String param2) {
        SaveCourseFragment fragment = new SaveCourseFragment();
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

        return inflater.inflate(R.layout.fragment_save_course, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonGetCoordinates = view.findViewById(R.id.buttonGetCoordinates);
        editTextCourseName = view.findViewById(R.id.editTextCourseName);
        editTextCourseDesc = view.findViewById(R.id.editTextCourseDesc);
        editTextStudyHours = view.findViewById(R.id.editTextStudyHours);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextState = view.findViewById(R.id.editTextState);
        editTextCity = view.findViewById(R.id.editTextCity);
        editTextZipCode = view.findViewById(R.id.editTextZipCode);
        editTextDeadlineInfo = view.findViewById(R.id.editTextDeadlineInfo);
        textViewCoordinates = view.findViewById(R.id.textViewCoordinates);
        textViewDeadlineDate = view.findViewById(R.id.textViewDeadlineDate);
        textViewDeadlineInfo = view.findViewById(R.id.textViewDeadlineInfo);
        buttonSaveDeadline = view.findViewById(R.id.buttonSaveReminder);
        datePickerDeadline = view.findViewById(R.id.calendarViewDeadline);
        buttonSaveCourse = view.findViewById(R.id.buttonSaveCourse);


        buttonSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful = false;
                CourseDataSource ds = new CourseDataSource(getContext());
                CourseAdapter courseAdapter;

                try {
                    Log.d(TAG, "About to insert");
                    ds.open();
                    if (currentCourseContact.getCourseContactID() == -1) {
                        wasSuccessful = ds.insertCourseContact(currentCourseContact);
                        int newId = ds.getLastCourseContactId();
                    } else {
                        wasSuccessful = ds.updateCourseContact(currentCourseContact);
                    }
                    Log.d(TAG, String.valueOf(wasSuccessful));

                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }


            }
        });


        datePickerDeadline.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                String selectedDates = sdf.format(new Date(year - 1900, month, dayOfMonth));
                Log.d(TAG, "onClick: Save Deadline " + selectedDates);
                currentDeadlineInfoContact.setDeadlineDate(selectedDates);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                datePickerDeadline.setDate(calendar.getTimeInMillis());
            }
        });

        buttonSaveDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Calendar calender = Calendar.getInstance();
                calender.setTimeInMillis(datePickerDeadline.getDate());
                selectedDeadlineDate = sdf.format(calender.getTime());
                Log.d(TAG, "onClick: Save Deadline " + selectedDeadlineDate);

                boolean wasSuccessful = false;
                CourseDataSource ds = new CourseDataSource(getContext());
                currentDeadlineInfoContact.setCourseContactID(currentCourseContact.getCourseContactID());
                try {
                    Log.d(TAG, "About to insert");
                    ds.open();
                    if (currentDeadlineInfoContact.getDeadLineContactInfoID() == -1) {
                        wasSuccessful = ds.insertDeadlineInfoContact(currentDeadlineInfoContact);
                        int newId = ds.getLastDeadlineInfoContactId();
                        currentDeadlineInfoContact.setDeadLineContactInfoID(newId);
                        ds = new CourseDataSource(getContext());

                    } else {
                        wasSuccessful = ds.updateDeadlineInfoContact(currentDeadlineInfoContact);
                    }
                    Log.d(TAG, String.valueOf(wasSuccessful));

                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                ArrayList<CourseContact> courseContacts;
                ArrayList<DeadlineInfoContact> deadlineInfoContacts;
                DeadlineAdapter deadlineAdapter;

                try{
                    ds.open();
                    deadlineInfoContacts = ds.getDeadlineContacts();
                    ds.close();
                    RecyclerView deadlineInfoList = view.findViewById(R.id.rvDeadlines);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    deadlineInfoList.setLayoutManager(layoutManager);
                    deadlineAdapter = new DeadlineAdapter(deadlineInfoContacts, getContext());
                    deadlineAdapter.setDelete(true);
                    deadlineInfoList.setAdapter(deadlineAdapter);
                } catch (Exception e) {
                    Log.d(TAG,"Fail");
                }


            }
        });

        buttonGetCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = editTextAddress.getText().toString() + "," +
                        editTextState.getText().toString() + "," +
                        editTextCity.getText().toString() + "," +
                        editTextZipCode.getText().toString();

                Log.d(TAG, "onClick: " + address);

                List<Address> addresses = null;
                Geocoder geo = new Geocoder(getContext());

                try {
                    addresses = geo.getFromLocationName(address, 1);
                    Log.d(TAG, "onClick: " + addresses.get(0).toString());
                    textViewCoordinates.setText(String.valueOf("Latitude: " + addresses.get(0).getLatitude()) + "\nLongitude: " +
                            String.valueOf(addresses.get(0).getLongitude()));
                } catch (IOException e) {
                    Log.d(TAG, "OnClick: Failed To Get Coordinates");
                    e.printStackTrace();
                }

            }
        });

        initTextChangedEvents();
    }

    private void initTextChangedEvents() {
        editTextCourseName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentCourseContact.setCourseName(editTextCourseName.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        editTextCourseDesc.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentCourseContact.setCourseDesc(editTextCourseDesc.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        editTextStudyHours.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentCourseContact.setStudyHourTarget(editTextStudyHours.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        editTextAddress.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentCourseContact.setStreetAddress(editTextAddress.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        editTextState.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentCourseContact.setState(editTextState.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        editTextCity.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentCourseContact.setCity(editTextCity.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        editTextZipCode.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentCourseContact.setZipCode(editTextZipCode.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        editTextDeadlineInfo.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentDeadlineInfoContact.setDeadlineInfo(editTextDeadlineInfo.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

    }


}

