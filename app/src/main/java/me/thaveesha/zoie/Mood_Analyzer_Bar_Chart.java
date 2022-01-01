package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Mood_Analyzer_Bar_Chart extends AppCompatActivity {


    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood__analyzer__bar__chart);

        barChart = (BarChart)findViewById(R.id.barChart);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry>  barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, 40f));
        barEntries.add(new BarEntry(2, 44f));
        barEntries.add(new BarEntry(3, 30f));
        barEntries.add(new BarEntry(4, 46f));

        //Set 2
        ArrayList<BarEntry>  barEntries1 = new ArrayList<>();

        barEntries.add(new BarEntry(1, 22f));
        barEntries.add(new BarEntry(2, 56f));
        barEntries.add(new BarEntry(3, 80f));
        barEntries.add(new BarEntry(4, 39f));

        BarDataSet barDataSet = new BarDataSet(barEntries,"Data set 1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet1 = new BarDataSet(barEntries1,"Data set 2");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet,barDataSet1);

        float groupSpace = 0.1f;
        float barSpace = 0.02f;
        float barWidth = 0.43f;


        barChart.setData(data);

        data.setBarWidth(barWidth);
        barChart.groupBars(1,groupSpace,barSpace);



        String[] months = new String[]{"Jan","feb","Mar","April","May"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MYXAxixValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);



        
    }

    public class MYXAxixValueFormatter extends ValueFormatter implements IAxisValueFormatter{
        private String[] mValues;
        public MYXAxixValueFormatter(String[] values){
            this.mValues = values;

        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }
}