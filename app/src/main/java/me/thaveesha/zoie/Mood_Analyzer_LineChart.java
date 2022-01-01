package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Map;

public class Mood_Analyzer_LineChart extends AppCompatActivity {

    private static final  String TAG = "Mood_Analyzer_LineChart";
    Button button;

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood__analyzer__line_chart);


        button = findViewById(R.id.btnLineC);
        lineChart = (LineChart)findViewById(R.id.lineChart);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0,60f));
        yValues.add(new Entry(1,20f));
        yValues.add(new Entry(2,60f));
        yValues.add(new Entry(3,13f));
        yValues.add(new Entry(4,42f));
        yValues.add(new Entry(5,38f));
        yValues.add(new Entry(6,60f));
        yValues.add(new Entry(7,85f));

        LineDataSet set1 = new LineDataSet(yValues,"Data Set 1");
        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextColor(Color.BLACK);
        set1.setValueTextSize(10f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);

     //   lineChart.setOnChartGestureListener(Mood_Analyzer_LineChart.this);
    //    lineChart.setOnChartValueSelectedListener(Mood_Analyzer_LineChart.this);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                load();
            }
        });


    }

    public void load(){

        Toast.makeText(this,"No data available in the given period",Toast.LENGTH_SHORT).show();
    }
}