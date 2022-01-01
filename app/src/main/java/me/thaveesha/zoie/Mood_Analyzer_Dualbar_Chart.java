package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Mood_Analyzer_Dualbar_Chart extends AppCompatActivity {
Button button;
    BarChart dualbarChart;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood__analyzer__dualbar__chart);

        dualbarChart = (BarChart) findViewById(R.id.dualbarChart);
        button = findViewById(R.id.btnDualB);
        textInputLayout = findViewById(R.id.txtDate);

        final List<Data> data = new ArrayList<>();
        data.add(new Data(0f, 0,"12-29"));
        data.add(new Data(1f, 0f,"12-29"));
        data.add(new Data(2f, 0f,"12-29"));
        data.add(new Data(3f, 5f,"12-29"));
        data.add(new Data(4f, 20f,"12-29"));
        data.add(new Data(5f, 49f,"12-29"));
        data.add(new Data(6f, -35f,"12-29"));
        data.add(new Data(7f, -16f,"12-29"));
        data.add(new Data(4f, 50f,"12-29"));
        data.add(new Data(5f, 45f,"12-29"));
        data.add(new Data(6f, 12f,"12-29"));
        data.add(new Data(7f, 0,"12-29"));

        Description description = new Description();
        description.setText("Mood variation during " + textInputLayout.getEditText().getText().toString());
        description.setTextSize(10);
        description.setTextColor(Color.BLACK);
        dualbarChart.setDescription(description);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(data);
            }
        });


    }

    private void setData(List<Data> dataList){
        ArrayList<BarEntry> values = new ArrayList<>();
        List <Integer> colours = new ArrayList<>();

        int green = Color.rgb(110,190,102);
        int red = Color.rgb(211,87,44);

        for(int i=0; i<dataList.size();i++){
            Data d = dataList.get(i);
            BarEntry entry = new BarEntry(d.xValue, d.yValue);
            values.add(entry);

            if(d.yValue>0){
                colours.add(green);
            }else{
                colours.add(red);
            }
        }

        BarDataSet set;
        set = new BarDataSet(values,"Values");
        set.setColors(colours);
        set.setValueTextColors(colours);

        BarData data = new BarData(set);
        data.setValueTextSize(10f);

        data.setValueFormatter(new valueFormatter());
        data.setBarWidth(0.8f);

        dualbarChart.setData(data);
        dualbarChart.invalidate();

    }

    private class Data{
        public String xAxisValue;
        public float xValue;
        public float yValue;

        public Data(float xValue, float yValue,String xAxisValue) {
            this.xAxisValue = xAxisValue;
            this.xValue = xValue;
            this.yValue = yValue;
        }
    }


    private class valueFormatter extends ValueFormatter implements IAxisValueFormatter{
        private DecimalFormat mFormat;

        public valueFormatter(){
            mFormat = new DecimalFormat("#####.0");

        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mFormat.format(value);
        }
    }
}