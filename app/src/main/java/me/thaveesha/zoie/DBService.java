package me.thaveesha.zoie;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DBService extends AppCompatActivity {

    Button refresh, view, delete;
    DBHelper dbHelper;
    DBHelperData dbHelperData;
    RequestSentimentAnalyze requestSentimentAnalyze;

    String testEmotion1 = "Positive";
    String testEmotion2 = "Negative";
    String testEmotion3 = "Neutral";
    int countPositive, countNegative, countNeutral = 0;

    String emotion;
    Boolean checkInsertData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_b_service);

        refresh = findViewById(R.id.btnRefresh);
        view = findViewById(R.id.btnView);
        delete = findViewById(R.id.btnDelete);
        dbHelper = new DBHelper(this);
        dbHelperData = new DBHelperData(this);
        requestSentimentAnalyze = new RequestSentimentAnalyze();



        //Insert Method
        refresh.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String datetime = getCurrentDatetime();

                System.out.println("out : " + datetime);
                System.out.println("out : " + emotion);


                try {
                    checkInsertData = dbHelper.insertData(datetime, emotion);


                } catch (NullPointerException e) {
                    System.err.println("Null pointer exception");
                    Toast.makeText(DBService.this, "Null pointer exception", Toast.LENGTH_SHORT).show();

                }

                if (checkInsertData)
                    Toast.makeText(DBService.this, "New entry inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DBService.this, "New entry insert failed", Toast.LENGTH_SHORT).show();
            }
        });

        //View Method
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Cursor res = dbHelperData.getBetweenData("'2021/08/25'","'2021/08/31'");
                //Cursor res = dbHelper.getDateData("'2021/08/25'");
                Cursor res = dbHelperData.getData();

                if (res.getCount() == 0) {
                    Toast.makeText(DBService.this, "No entry exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();

                while (res.moveToNext()) {
                    stringBuffer.append("Date Time : " + res.getString(0) + "\n");
                    stringBuffer.append("Emotion : " + res.getString(1) + "\n\n");

                    System.out.println(res.getString(1));
                    //requestSentimentAnalyze.getAnalysis("I am angry");

                    if ((res.getString(1).equals("Positive"))) {
                        countPositive++;
                    }else if((res.getString(1).equals("Negative"))){
                        countNegative++;
                    }else if((res.getString(1).equals("Neutral"))){
                        countNeutral++;
                    }


                }

                //Popup
                AlertDialog.Builder builder = new AlertDialog.Builder(DBService.this);
                builder.setCancelable(true);
                builder.setTitle("Emotion Data");
                builder.setMessage(stringBuffer.toString());
                builder.show();

                System.out.println("Positive : " + countPositive);
                System.out.println("Negative : " + countNegative);
                System.out.println("Neutral : " + countNeutral);

            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCurrentDatetime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return (dtf.format(now));
    }
}