package com.example.nipunarora.finbot.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nipunarora.finbot.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by nipunarora on 15/04/17.
 */

public class SpendingReview extends Fragment {
    View rootview;
    PieChart spending;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.spending_pie, container, false);
        spending=(PieChart)rootview.findViewById(R.id.spendingPie);
        return rootview;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //set use percent values
        spending.setUsePercentValues(true);
        // enable hole and configure
        spending.setDrawHoleEnabled(true);

        spending.setHoleRadius(7);
        spending.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        spending.setRotationAngle(0);
        spending.setRotationEnabled(true);
        setupPieChart();
        spending.getLegend().setEnabled(false);
        spending.getDescription().setEnabled(false);
    }

    public SpendingReview() {

    }
    private void setupPieChart()
    {


        spending.setDrawCenterText(true);

        spending.setRotationAngle(0);
        // enable rotation of the chart by touch
        spending.setRotationEnabled(true);
    //*************************** Makng a list of all entries in the piechart the first arguement denotes the percentage on a relative basis *********//
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(50, "Food"));
        entries.add(new PieEntry(80, "Leisure"));
        entries.add(new PieEntry(60, "Travel"));
        entries.add(new PieEntry(120, "worker salary"));
    //******************************** Animating the pie chart *******************//
        spending.animateY(1500);

     //***************************** Making a dataset from the entries and attaching colors to it and finally attaching the dataset to the spending pie chart by converting
        // it to a piedata object*******//
        PieDataSet dataset = new PieDataSet(entries,"");

        dataset.setSliceSpace(3);
        dataset.setSelectionShift(5);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataset.setColors(colors);
        PieData data = new PieData(dataset);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        spending.setData(data);
     //***************** Refreshing the pie chart ******************??
        spending.invalidate();

    }
}
