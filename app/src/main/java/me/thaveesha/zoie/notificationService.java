package me.thaveesha.zoie;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class notificationService extends AppCompatActivity {

    TextInputLayout date, time, description;
    Button btnNSendNotification;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_service);
        createNotificationChannel();

        btnNSendNotification = findViewById(R.id.btnRemind);
        description = findViewById(R.id.txtDescTo);
        date = findViewById(R.id.txtRemDate);
        time = findViewById(R.id.txtRemTime);

        NotificationCompat.Builder builder;
        builder = new NotificationCompat.Builder(this,"zoieN")
            .setSmallIcon(R.drawable.ic_add_alert)
            .setContentTitle("ZOIE is looking for you!")
            .setContentText("Hello Thaveesha, You have to "+ description.getEditText().getText().toString() +" now")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        btnNSendNotification.setOnClickListener(v -> {
            if(validate(date)){
                if(validate(time)){
                    if(validate(description)){
                        Toast.makeText(this,"Reminder Set",Toast.LENGTH_SHORT).show();
                        notificationManagerCompat.notify(100,builder.build());
                    }
                }
            }

        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "studentChannel";
            String description = "Channel for studemt notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("zoieN", name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
    private boolean validate(TextInputLayout textInputLayout) {

        String val = textInputLayout.getEditText().getText().toString();
        if (val.isEmpty()) {
            textInputLayout.setError("Field cannot be empty");
            return false;
        } else {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
            return true;
        }
    }
}