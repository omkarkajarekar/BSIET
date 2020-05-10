package com.example.bsiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ConstraintLayout constraintLayout;
    TextInputLayout user_label,pass_label;
    TextInputEditText username,password;
    Button signin,signup;
    String uname,pass,s_user,s_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout=(ConstraintLayout)findViewById(R.id.constraintlayout);
        username=(TextInputEditText)findViewById(R.id.username);
        password=(TextInputEditText)findViewById(R.id.password);
        user_label=(TextInputLayout)findViewById(R.id.user_label);
        pass_label=(TextInputLayout)findViewById(R.id.pass_label);
        signin=(Button)findViewById(R.id.signIn);
        signup=(Button)findViewById(R.id.signUp);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("users/user1");

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = username.getText().toString().trim();
                pass = password.getText().toString().trim();

                /*if (uname.length() == 0) {
                    user_label.setError("Empty field");
                } else if (!uname.matches("[a-zA-Z0-9]+")) {
                    user_label.setError("special symbols are not allowed");
                } else {
                    user_label.setError(null);
                }

                if (pass.length() == 0) {
                    pass_label.setError("Empty field");
                } else if (pass.length() < 8) {
                    pass_label.setError("too weak");
                } else if (!pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")) {
                    pass_label.setError("should contain at least one symbol and uppercase-lowercase and numbers");
                } else {
                    pass_label.setError(null);
                }*/

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,Object> user1=(Map<String,Object>) dataSnapshot.getValue();
                        s_user=user1.get("username").toString();
                        s_pass=user1.get("password").toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("exception",""+databaseError.getMessage());
                    }
                });

                if (user_label.getError()==null && pass_label.getError()==null) {
                    if (uname.equals(s_user)) {
                        if (pass.equals(s_pass)) {
                            Toast.makeText(getApplicationContext(), "Welcome " + uname, Toast.LENGTH_LONG).show();
                        }
                        if (!pass.equals(s_pass)) {
                            pass_label.setError("incorrect password");
                        }
                    }
                    if (!uname.equals(s_user)){
                        user_label.setError("invalid user");
                    }
                }
            }
        });
        //databaseReference.child("username").setValue(uname);
        //databaseReference.child("password").setValue(pass);
        //TODO users must generate dynamically with a unique number
        //TODO user must be searched in database and then username and password must be validated

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),registration.class);
                startActivity(i);
            }
        });

        constraintLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
