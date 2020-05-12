package com.example.bsiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

public class registration_faculty extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_faculty);

        String[] designation = new String[] {"Head Of Department", "Lecturer","Lab assistant","Office administration","Peon"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, designation);
        AutoCompleteTextView select_designation = findViewById(R.id.select_designation);
        select_designation.setAdapter(adapter1);

        String[] department = new String[] {"Computer Engineering", "Civil Engineering","Electrical Engineering","Electronics Engineering","Mechanical Engineering","Science and Humanities"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, department);
        AutoCompleteTextView select_department = findViewById(R.id.select_department);
        select_department.setAdapter(adapter2);

        String[] joining_year = new String[] {"2013","2014", "2015", "2016","2017", "2018", "2019","2020"};

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, joining_year);
        AutoCompleteTextView select_joining_year = findViewById(R.id.joining_year);
        select_joining_year.setAdapter(adapter2);

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
