package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPhone, regPassword;
    Button callLogin, btnRegLogin;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    String name, username, email, phoneNo, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        regName = findViewById(R.id.txtRegName);
        regUsername = findViewById(R.id.txtRegUsername);
        regEmail = findViewById(R.id.txtRegEmail);
        regPhone = findViewById(R.id.txtRegPhoneno);
        regPassword = findViewById(R.id.txtRegPassword);
        btnRegLogin = findViewById(R.id.btnLogin);
        callLogin = findViewById(R.id.btnCallLogin);

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        /*btnRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Get values from frontend
                name = regName.getEditText().getText().toString();
                username = regUsername.getEditText().getText().toString();
                email = regEmail.getEditText().getText().toString();
                phoneNo = regPhone.getEditText().getText().toString();
                password = regPassword.getEditText().getText().toString();


                UserHelperClass userHelperClass = new UserHelperClass(name, username, email, phoneNo, password);
                reference.child(phoneNo).setValue(userHelperClass);
            }
        });*/

    }

    private boolean validateName() {

        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateUserName() {

        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("No white spaces are allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {

        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid Email addree");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePhoneNo() {

        String val = regPhone.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPhone.setError("Field cannot be empty");
            return false;
        } else {
            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {

        String val = regPassword.getEditText().getText().toString();
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
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }

    }


    /*public void registerUser(View view) {

        if(!validateName() | !validateUserName() | !validateEmail() | !validatePhoneNo() | !validatePassword()){

            return;
        }

        //Get all values in String
        name = regName.getEditText().getText().toString();
        username = regUsername.getEditText().getText().toString();
        email = regEmail.getEditText().getText().toString();
        phoneNo = regPhone.getEditText().getText().toString();
        password = regPassword.getEditText().getText().toString();

        UserHelperClass userHelperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(phoneNo).setValue(userHelperClass);

    }

    */

    public void registerUser(View view) {

        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("users");

       if(!validateName() | !validateUserName() | !validateEmail() | !validatePhoneNo() | !validatePassword()){
           return;
        }

        name = regName.getEditText().getText().toString();
        username = regUsername.getEditText().getText().toString();
        email = regEmail.getEditText().getText().toString();
        phoneNo = regPhone.getEditText().getText().toString();
        password = regPassword.getEditText().getText().toString();

        UserHelperClass userHelperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(userHelperClass);
        //Get all values in String


    }
}