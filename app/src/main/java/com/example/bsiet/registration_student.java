package com.example.bsiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class registration_student extends AppCompatActivity {
    Intent base_registration;
    LinearLayout linearLayout;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextInputEditText enrollment_no,roll_no,parent_mobile_no,username,password,retype_password;
    TextInputLayout retype_pass;
    String enrollment,roll,parent_m_no,user,pass,retype,selected_class,selected_branch;
    String firstname,middlename,lastname,emailadd,mobileno;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_student);

        enrollment_no=(TextInputEditText)findViewById(R.id.enrollment_no);
        roll_no=(TextInputEditText)findViewById(R.id.roll_no);
        parent_mobile_no=(TextInputEditText)findViewById(R.id.parent_mobile_no);
        username=(TextInputEditText)findViewById(R.id.create_username);
        password=(TextInputEditText)findViewById(R.id.create_password);
        retype_password=(TextInputEditText)findViewById(R.id.retype_password);
        retype_pass=(TextInputLayout)findViewById(R.id.re_password);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users");

        base_registration=getIntent();
        firstname=base_registration.getStringExtra("first_name");
        middlename=base_registration.getStringExtra("middle_name");
        lastname=base_registration.getStringExtra("last_name");
        emailadd=base_registration.getStringExtra("email");
        mobileno=base_registration.getStringExtra("mobile");

        String[] classes = new String[] {"FY", "SY", "TY"};

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, classes);
        final AutoCompleteTextView select_class = findViewById(R.id.class_select);
        select_class.setAdapter(adapter1);

        String[] branch = new String[] {"Computer Engineering", "Civil Engineering","Electrical Engineering","Electronics Engineering","Mechanical Engineering","Science and Humanities"};

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, branch);
        final AutoCompleteTextView select_branch = findViewById(R.id.select_branch);
        select_branch.setAdapter(adapter3);

        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrollment = enrollment_no.getText().toString().trim();
                roll = roll_no.getText().toString().trim();
                parent_m_no = parent_mobile_no.getText().toString().trim();
                user = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                retype = retype_password.getText().toString().trim();
                selected_class =  select_class.getText().toString().trim();
                selected_branch = select_branch.getText().toString().trim();
                if(pass.equals(retype)) {
                    Map<String, Object> insertValues = new HashMap<>();
                    insertValues.put("first_name",firstname);
                    insertValues.put("middle_name",middlename);
                    insertValues.put("last_name",lastname);
                    insertValues.put("email",emailadd);
                    insertValues.put("mobile",mobileno);
                    insertValues.put("enrollment_no", enrollment);
                    insertValues.put("roll_no", roll);
                    insertValues.put("parent_mobile_no", parent_m_no);
                    insertValues.put("username", user);
                    insertValues.put("password", pass);
                    insertValues.put("class",selected_class);
                    insertValues.put("branch",selected_branch);
                    String key=databaseReference.push().getKey();
                    databaseReference.child("Student").child(key).setValue(insertValues);

                }
                else {
                    retype_pass.setError("password not matching");
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
