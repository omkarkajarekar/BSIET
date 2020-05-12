package com.example.bsiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

public class registration_student extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_faculty);

        String[] classes = new String[] {"FY", "SY", "TY"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, classes);
        AutoCompleteTextView select_class = findViewById(R.id.select_class);
        select_class.setAdapter(adapter1);

        String[] admission_year = new String[] {"2017", "2018", "2019"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, admission_year);
        AutoCompleteTextView select_year = findViewById(R.id.admission_year);
        select_year.setAdapter(adapter2);

        String[] branch = new String[] {"Computer Engineering", "Civil Engineering","Electrical Engineering","Electronics Engineering","Mechanical Engineering","Science and Humanities"};

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, branch);
        AutoCompleteTextView select_branch = findViewById(R.id.select_branch);
        select_branch.setAdapter(adapter3);


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
