package com.example.jakubaniola.patienttag;

import android.app.Activity;
import android.os.Bundle;

import com.example.jakubaniola.patienttag.TransportObjects.ExaminationTO;
import com.example.jakubaniola.patienttag.TransportObjects.PatientTO;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExaminationActivity extends Activity {

    @BindView(R.id.temperature_graph)
    GraphView temperatureGraph;
    @BindView(R.id.pressure_graph)
    GraphView pressureGraph;

    private PatientTO patient;
    private ArrayList<ExaminationTO> examinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);

        ButterKnife.bind(this);

        patient = (PatientTO) getIntent().getExtras().getSerializable("patient");
        if (patient != null){
            fillFields();
        }
    }

    private void fillFields() {
        examinations = patient.getExaminations();
        setupGraphs();
    }

    private void setupGraphs() {

        int i = 0;
        LineGraphSeries<DataPoint> temperatureSeries = new LineGraphSeries<>(new DataPoint[] {});
        LineGraphSeries<DataPoint> pressureSeries = new LineGraphSeries<>(new DataPoint[] {});
        for (ExaminationTO examination : examinations) {
            temperatureSeries.appendData((new DataPoint(i, examination.getTemperature())), false, 10);
            pressureSeries.appendData((new DataPoint(i, examination.getPressure())), false, 10);
            i++;
        }
        temperatureGraph.addSeries(temperatureSeries);
        pressureGraph.addSeries(pressureSeries);
    }
}
