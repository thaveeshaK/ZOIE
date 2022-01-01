package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Rest_Analytics_Page extends AppCompatActivity {

    Button routeMood, routeDayMood, routeWeeklyMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest__analytics__page);

        //Hooks
        routeMood = findViewById(R.id.btnGoToMoodStatus);
        routeDayMood = findViewById(R.id.btnGoToAnalyseDay);
        routeWeeklyMood = findViewById(R.id.btnGoToAnalyseWeek);

        routeMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rest_Analytics_Page.this, Mood_Analyzer_Pie_Chart.class);
                startActivity(intent);
            }
        });

        routeDayMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rest_Analytics_Page.this, Mood_Analyzer_Dualbar_Chart.class);
                startActivity(intent);
            }
        });

        routeWeeklyMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rest_Analytics_Page.this, Mood_Analyzer_LineChart.class);
                startActivity(intent);
            }
        });
    }



}