package com.example.bsiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButton;

public class registration extends AppCompatActivity {
    Intent student,faculty;
    RadioGroup radioGroup;
    LinearLayout linearLayout;
    Button next;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        radioGroup = (RadioGroup) findViewById(R.id.role_selection);
        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                if (value.equals("Student")){
                    student=new Intent(getBaseContext(),registration_student.class);
                    startActivity(student);
                }
                if (value.equals("Faculty")) {
                    faculty=new Intent(getBaseContext(),registration_faculty.class);
                    startActivity(faculty);
                }
            }
        });


        linearLayout=(LinearLayout)findViewById(R.id.linear);
        linearLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
