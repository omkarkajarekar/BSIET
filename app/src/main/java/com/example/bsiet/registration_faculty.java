package com.example.bsiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registration_faculty extends AppCompatActivity {
    Intent base_registration;
    LinearLayout linearLayout;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextInputEditText staff_id,alternate_mobile_no,username,password,retype_password;
    TextInputLayout retype_pass;
    String staff,alternate_m_no,user,pass,retype,selected_designation,selected_department,selected_joining_year;
    String firstname,middlename,lastname,emailadd,mobileno;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_faculty);

        staff_id=(TextInputEditText)findViewById(R.id.staff);
        alternate_mobile_no=(TextInputEditText)findViewById(R.id.alternate_mobile_no);
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

        String[] designation = new String[] {"Head Of Department", "Lecturer","Lab assistant","Office administration","Peon"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, designation);
        final AutoCompleteTextView select_designation = findViewById(R.id.select_designation);
        select_designation.setAdapter(adapter1);

        String[] department = new String[] {"Computer Engineering", "Civil Engineering","Electrical Engineering","Electronics Engineering","Mechanical Engineering","Science and Humanities"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, department);
        final AutoCompleteTextView select_department = findViewById(R.id.select_department);
        select_department.setAdapter(adapter2);

        String[] joining_year = new String[] {"2013","2014", "2015", "2016","2017", "2018", "2019","2020"};

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, joining_year);
        final AutoCompleteTextView select_joining_year = findViewById(R.id.joining_year);
        select_joining_year.setAdapter(adapter3);

        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staff = staff_id.getText().toString().trim();
                alternate_m_no = alternate_mobile_no.getText().toString().trim();
                user = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                retype = retype_password.getText().toString().trim();
                selected_designation =  select_designation.getText().toString().trim();
                selected_department = select_department.getText().toString().trim();
                selected_joining_year = select_joining_year.getText().toString().trim();
                if(pass.equals(retype)) {
                    Map<String, Object> insertValues = new HashMap<>();
                    insertValues.put("first_name",firstname);
                    insertValues.put("middle_name",middlename);
                    insertValues.put("last_name",lastname);
                    insertValues.put("email",emailadd);
                    insertValues.put("mobile",mobileno);
                    insertValues.put("staff_id", staff);
                    insertValues.put("alternate_mobile", alternate_m_no);
                    insertValues.put("username", user);
                    insertValues.put("password", pass);
                    insertValues.put("designation",selected_designation);
                    insertValues.put("department",selected_department);
                    insertValues.put("joining_year",selected_joining_year);
                    String key=databaseReference.push().getKey();
                    databaseReference.child("Faculty").child(key).setValue(insertValues);

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
