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

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class registration extends AppCompatActivity {
    Intent student,faculty;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextInputEditText first_name,middle_name,last_name,mobile,email;
    RadioGroup radioGroup;
    LinearLayout linearLayout;
    Button next;
    String value,firstname,middlename,lastname,emailadd,mobileno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        radioGroup = (RadioGroup) findViewById(R.id.role_selection);
        first_name=(TextInputEditText)findViewById(R.id.first_name);
        middle_name=(TextInputEditText)findViewById(R.id.middle_name);
        last_name=(TextInputEditText)findViewById(R.id.last_name);
        email=(TextInputEditText)findViewById(R.id.email);
        mobile=(TextInputEditText)findViewById(R.id.mobile);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname= first_name.getText().toString().trim();
                middlename = middle_name.getText().toString().trim();
                lastname = last_name.getText().toString().trim();
                emailadd = email.getText().toString().trim();
                mobileno = mobile.getText().toString().trim();

                Map<String,Object> insertValues = new HashMap<>();
                insertValues.put("first_name",firstname);
                insertValues.put("middle_name",middlename);
                insertValues.put("last_name",lastname);
                insertValues.put("email",emailadd);
                insertValues.put("mobile",mobileno);

                value = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                if (value.equals("Student")){
                    String key=databaseReference.push().getKey();
                    databaseReference.child("Student").child(key).setValue(insertValues);
                    /*student=new Intent(getBaseContext(),registration_student.class);
                    startActivity(student);*/

                }
                if (value.equals("Faculty")) {
                    String key=databaseReference.push().getKey();
                    databaseReference.child("Faculty").child(key).setValue(insertValues);
                    /*faculty=new Intent(getBaseContext(),registration_faculty.class);
                    startActivity(faculty);*/
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
