package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MeditationMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_main_page);

        Button article = findViewById(R.id.btnGoToArticles);
        Button music = findViewById(R.id.btnGoToMediMusic);
        Button stories = findViewById(R.id.btnGoToMotivationStories);

        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeditationMainPage.this, ArticlePage.class);
                startActivity(intent);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeditationMainPage.this, medi_story_page.class);
                startActivity(intent);
            }
        });

        stories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeditationMainPage.this, medi_motivation_page.class);
                startActivity(intent);
            }
        });
    }
}