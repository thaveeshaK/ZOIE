package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard2 extends AppCompatActivity {

    Button routeChatbot,routeUserProfile,routeMeditation,routeMyAnalytics,routeToDoList,routeNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);


        //Hooks
        routeChatbot = findViewById(R.id.btnGoToChatbot);
        routeUserProfile = findViewById(R.id.btnGoToUserProfile);
        routeMeditation = findViewById(R.id.btnGoToMeditation);
        routeMyAnalytics = findViewById(R.id.btnGoToMyAnalytics);
        routeToDoList = findViewById(R.id.btnGoToToDoList);
        routeNotification = findViewById(R.id.btnGoToNotifications);

        routeChatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        routeMyAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard2.this, Rest_Analytics_Page.class);
                startActivity(intent);
            }
        });

        routeNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard2.this, notificationService.class);
                startActivity(intent);
            }
        });

        routeMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard2.this, MeditationMainPage.class);
                startActivity(intent);
            }
        });

        routeToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard2.this, ToDoActivity.class);
                startActivity(intent);
            }
        });

        routeUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard2.this, UserProfile.class);
                startActivity(intent);
            }
        });
    }
}