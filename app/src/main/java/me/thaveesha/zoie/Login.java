package me.thaveesha.zoie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputLayout logUsername, logPassword;
    Button btnLogin, callSignUP, callForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        logUsername = findViewById(R.id.txtLogUsername);
        logPassword = findViewById(R.id.txtLogPassword);
        btnLogin = findViewById(R.id.btnLogin);
        callSignUP = findViewById(R.id.btnCallSignUp);
        callForgotPassword = findViewById(R.id.btnCallForgot);


        callSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        callForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });


    }

    private boolean validateUserName() {

        String val = logUsername.getEditText().getText().toString();
        if (val.isEmpty()) {
            logUsername.setError("Field cannot be empty");
            return false;
        } else {
            logUsername.setError(null);
            logUsername.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {

        String val = logPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            logPassword.setError("Field cannot be empty");
            return false;
        } else {
            logPassword.setError(null);
            logPassword.setErrorEnabled(false);
            return true;
        }

    }

    public void loginUser(View view) {
        //validate login info
        if (!validateUserName() | !validatePassword()) {
            return;
        } else {
            isUser();
        }

    }

    private void isUser() {
        //progressBar.setVisibility(View.VISIBLE);
        final String userEnteredUsername = logUsername.getEditText().getText().toString().trim();
        final String userEnteredPassword = logPassword.getEditText().getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = databaseReference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    logUsername.setError(null);
                    logUsername.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {
                        logUsername.setError(null);
                        logUsername.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Dashboard2.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                       // retreiveMoodDetails retreiveMoodDetails = new retreiveMoodDetails();
                        //retreiveMoodDetails.retreiveMoodData();

                    } else {
                        logPassword.setError("Wrong Password");
                        logPassword.requestFocus();
                    }
                } else {
                    logUsername.setError("No such user exist");
                    logUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}