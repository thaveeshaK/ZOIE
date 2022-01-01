package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullname, username, email, phone, password;
    TextView fullnameLabel, usernamelabel;
    Button update;

    //Global variables to hold user data inside this activity
    String _USERNAME, _NAME, _eMAIL, _PHONENO, _PASSWORD;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //update = findViewById(R.id.btnUpdate);
        reference = FirebaseDatabase.getInstance().getReference("users");


        //Hooks
        fullname = findViewById(R.id.proFullName);
        username = findViewById(R.id.proUsername);
        email = findViewById(R.id.proEmail);
        phone = findViewById(R.id.proPhone);
        password = findViewById(R.id.proPassword);
        fullnameLabel = findViewById(R.id.lblFullName);
        usernamelabel = findViewById(R.id.lblUsername);

        //ShowAllData
        showAllUserData();
    }

    private void showAllUserData() {

        Intent intent = getIntent();
        String user_fullname = intent.getStringExtra("name");
        String user_username = intent.getStringExtra("username");
        String user_email = intent.getStringExtra("email");
        String user_phone = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        fullnameLabel.setText(user_fullname);
        usernamelabel.setText(user_username);

        fullname.getEditText().setText(user_fullname);
        username.getEditText().setText(user_username);
        email.getEditText().setText(user_email);
        phone.getEditText().setText(user_phone);
        password.getEditText().setText(user_password);


    }



    public void updateData(View view) {

        Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
/*
*         if(validateName() && validateUserName() && validateEmail() && validatePassword() && validatePassword() && validatePhoneNo()){
            if (isNameChanged() || isPasswordChanged()) {
                Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Data is same !", Toast.LENGTH_LONG).show();
            }
        }
* */


    }

    public void updateDetails(){
        Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
    }

    private boolean isPasswordChanged() {
        _PASSWORD = password.getEditText().getText().toString();
        if (!_PASSWORD.equals(password.getEditText().getText().toString())) {

            reference.child(_PASSWORD).child("password").setValue(password.getEditText().getText().toString());

            return true;

        } else {
            return false;
        }
    }

    private boolean isNameChanged() {
        _NAME = fullname.getEditText().getText().toString();
        if (!_NAME.equals(fullname.getEditText().getText().toString())) {

            reference.child(_USERNAME).child("name").setValue(fullname.getEditText().getText().toString());

            return true;
        } else {
            return false;
        }
    }

// validations

    private boolean validateName() {

        String val = fullname.getEditText().getText().toString();
        if (val.isEmpty()) {
            fullname.setError("Field cannot be empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateUserName() {

        String val = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            username.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            username.setError("No white spaces are allowed");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {

        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid Email addree");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePhoneNo() {

        String val = phone.getEditText().getText().toString();
        if (val.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {

        String val = password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";


        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}