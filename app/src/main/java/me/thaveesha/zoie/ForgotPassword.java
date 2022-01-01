package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassword extends AppCompatActivity {

    TextInputLayout email;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.txtForgotEmail);
        bt = findViewById(R.id.btnForgotPassword);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(validateEmail()){
                    Toast.makeText(getApplicationContext(),"Password reset mail sent",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private boolean validateEmail() {

        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Please enter valid email");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }
}