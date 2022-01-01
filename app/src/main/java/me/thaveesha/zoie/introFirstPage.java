package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class introFirstPage extends AppCompatActivity {

    private static  int INTRO_FIRST = 5000;

    ImageView image;
    TextView txtOne,txtTwo,txtThree,txtFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_first_page);

        //Hooks
        image = findViewById(R.id.introOneImageOne);
        txtOne = findViewById(R.id.introOneTxtOne);
        txtTwo = findViewById(R.id.introOneTxtTwo);
        txtThree = findViewById(R.id.introOneTxtThree);


        image.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        txtOne.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        txtTwo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        txtThree.animate().translationY(1400).setDuration(1000).setStartDelay(4000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(introFirstPage.this,introThirdPage.class);
                startActivity(intent);
                finish();
            }
        },INTRO_FIRST);




    }
}