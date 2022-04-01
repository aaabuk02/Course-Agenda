package edu.aaabuk02.courselogger;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class GradeCalculatorFragment extends Fragment {

    private TextView textViewResult;

    public Button clearButton;
    public Button calculateButton;

    public EditText editTextsAssignment_1;
    private EditText editTextsAssignment_2;
    private EditText editTextsAssignment_3;
    private EditText editTextsAssignment_4;
    private EditText editTextsAssignment_5;
    private EditText editTextsAssignment_6;
    private EditText editTextsAssignment_7;

    public EditText editTextsGrade_1;
    private EditText editTextsGrade_2;
    private EditText editTextsGrade_3;
    private EditText editTextsGrade_4;
    private EditText editTextsGrade_5;
    private EditText editTextsGrade_6;
    private EditText editTextsGrade_7;

    public EditText editTextsWeight_1;
    private EditText editTextsWeight_2;
    private EditText editTextsWeight_3;
    private EditText editTextsWeight_4;
    private EditText editTextsWeight_5;
    private EditText editTextsWeight_6;
    private EditText editTextsWeight_7;

    public GradeCalculatorFragment() {
        // Required empty public constructor
    }

    public static GradeCalculatorFragment newInstance(String param1, String param2) {
        GradeCalculatorFragment fragment = new GradeCalculatorFragment();
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
        return inflater.inflate(R.layout.fragment_grade_calculator, container, false);
    }
    String gradeToLetterGrade(float gradePercent)
    {
        if(gradePercent >= 97)
        {
            return "A+";
        }
        else if(gradePercent >= 93)
        {
            return "A";
        }
        else if(gradePercent >= 90)
        {
            return "A-";
        }
        else if(gradePercent >= 87)
        {
            return "B+";
        }
        else if(gradePercent >= 83)
        {
            return "B";
        }
        else if(gradePercent >= 80)
        {
            return "B-";
        }
        else if(gradePercent >= 77)
        {
            return "C+";
        }
        else if(gradePercent >= 73)
        {
            return "C";
        }
        else if(gradePercent >= 70)
        {
            return "C-";
        }
        else if(gradePercent >= 67)
        {
            return "D+";
        }
        else if(gradePercent >= 63)
        {
            return "D";
        }
        else if(gradePercent >= 60)
        {
            return "D";
        }
        else
        {
            return "F";
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextsGrade_1 = (EditText) view.findViewById(R.id.editTextGrade1);
        editTextsGrade_2 = (EditText) view.findViewById(R.id.editTextGrade2);
        editTextsGrade_3 = (EditText) view.findViewById(R.id.editTextGrade3);
        editTextsGrade_4 = (EditText) view.findViewById(R.id.editTextGrade4);
        editTextsGrade_5 = (EditText) view.findViewById(R.id.editTextGrade5);
        editTextsGrade_6 = (EditText) view.findViewById(R.id.editTextGrade6);
        editTextsGrade_7 = (EditText) view.findViewById(R.id.editTextGrade7);

        editTextsAssignment_1 = (EditText) view.findViewById(R.id.editTextAssignment1);
        editTextsAssignment_2 = (EditText) view.findViewById(R.id.editTextAssignment2);
        editTextsAssignment_3 = (EditText) view.findViewById(R.id.editTextAssignment3);
        editTextsAssignment_4 = (EditText) view.findViewById(R.id.editTextAssignment4);
        editTextsAssignment_5 = (EditText) view.findViewById(R.id.editTextAssignment5);
        editTextsAssignment_6 = (EditText) view.findViewById(R.id.editTextAssignment6);
        editTextsAssignment_7 = (EditText) view.findViewById(R.id.editTextAssignment7);

        editTextsWeight_1 = (EditText) view.findViewById(R.id.editTextWeight1);
        editTextsWeight_2 = (EditText) view.findViewById(R.id.editTextWeight2);
        editTextsWeight_3 = (EditText) view.findViewById(R.id.editTextWeight3);
        editTextsWeight_4 = (EditText) view.findViewById(R.id.editTextWeight4);
        editTextsWeight_5 = (EditText) view.findViewById(R.id.editTextWeight5);
        editTextsWeight_6 = (EditText) view.findViewById(R.id.editTextWeight6);
        editTextsWeight_7 = (EditText) view.findViewById(R.id.editTextWeight7);


        textViewResult = view.findViewById(R.id.textViewResult);
        calculateButton = view.findViewById(R.id.calculateButton);
        clearButton = view.findViewById(R.id.clearButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextsGrade_1.getText().clear();
                editTextsGrade_2.getText().clear();
                editTextsGrade_3.getText().clear();
                editTextsGrade_4.getText().clear();
                editTextsGrade_5.getText().clear();
                editTextsGrade_6.getText().clear();
                editTextsGrade_7.getText().clear();

                editTextsWeight_1.getText().clear();
                editTextsWeight_2.getText().clear();
                editTextsWeight_3.getText().clear();
                editTextsWeight_4.getText().clear();
                editTextsWeight_5.getText().clear();
                editTextsWeight_6.getText().clear();
                editTextsWeight_7.getText().clear();

                editTextsAssignment_1.getText().clear();
                editTextsAssignment_2.getText().clear();
                editTextsAssignment_3.getText().clear();
                editTextsAssignment_4.getText().clear();
                editTextsAssignment_5.getText().clear();
                editTextsAssignment_6.getText().clear();
                editTextsAssignment_7.getText().clear();

                textViewResult.setText("");

            }
        });



        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float weight_1 = 0, weight_2= 0, weight_3= 0, weight_4= 0, weight_5= 0, weight_6= 0, weight_7 = 0;
                float  grade_1= 0, grade_2= 0,grade_3= 0,grade_4= 0,grade_5= 0,grade_6= 0,grade_7 = 0;
                if(!(editTextsWeight_1.getText().toString().trim().isEmpty()))
                {
                    weight_1 = Float.valueOf(String.valueOf(editTextsWeight_1.getText()));
                }
                if(!(editTextsWeight_2.getText().toString().trim().isEmpty()))
                {
                    weight_2 = Float.valueOf(String.valueOf(editTextsWeight_2.getText()));
                }
                if(!(editTextsWeight_3.getText().toString().trim().isEmpty()))
                {
                    weight_3 = Float.valueOf(String.valueOf(editTextsWeight_3.getText()));
                }
                if(!(editTextsWeight_4.getText().toString().trim().isEmpty()))
                {
                    weight_4 = Float.valueOf(String.valueOf(editTextsWeight_4.getText()));
                }
                if(!(editTextsWeight_5.getText().toString().trim().isEmpty()))
                {
                    weight_5 = Float.valueOf(String.valueOf(editTextsWeight_5.getText()));
                }
                if(!(editTextsWeight_6.getText().toString().trim().isEmpty()))
                {
                    weight_6 = Float.valueOf(String.valueOf(editTextsWeight_6.getText()));
                }
                if(!(editTextsWeight_7.getText().toString().trim().isEmpty()))
                {
                    weight_7 = Float.valueOf(String.valueOf(editTextsWeight_7.getText()));
                }
                if(!(editTextsGrade_1.getText().toString().trim().isEmpty()))
                {
                    grade_1 = Float.valueOf(String.valueOf(editTextsGrade_1.getText()));
                }
                if(!(editTextsGrade_2.getText().toString().trim().isEmpty()))
                {
                    grade_2 = Float.valueOf(String.valueOf(editTextsGrade_2.getText()));
                }
                if(!(editTextsGrade_3.getText().toString().trim().isEmpty()))
                {
                    grade_3 = Float.valueOf(String.valueOf(editTextsGrade_3.getText()));
                }
                if(!(editTextsGrade_4.getText().toString().trim().isEmpty()))
                {
                    grade_4 = Float.valueOf(String.valueOf(editTextsGrade_4.getText()));
                }
                if(!(editTextsGrade_5.getText().toString().trim().isEmpty()))
                {
                    grade_5 = Float.valueOf(String.valueOf(editTextsGrade_5.getText()));
                }
                if(!(editTextsGrade_6.getText().toString().trim().isEmpty()))
                {
                    grade_6 = Float.valueOf(String.valueOf(editTextsGrade_6.getText()));
                }
                if(!(editTextsGrade_7.getText().toString().trim().isEmpty()))
                {
                    grade_7 = Float.valueOf(String.valueOf(editTextsGrade_7.getText()));
                }
                float totalWeight = weight_1 +  weight_2 + weight_3 + weight_4 + weight_5 + weight_6 + weight_7;
                float result = 0;
                if(totalWeight != 0)
                    result = (weight_1*grade_1 +  weight_2*grade_2 + weight_3*grade_3 + weight_4*grade_4+
                            weight_5*grade_6 + weight_7*grade_7)/(totalWeight);
                textViewResult.setText(Float.toString(result) +"% "+ gradeToLetterGrade(result));

            }
        });


    }
}