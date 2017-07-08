package com.example.jakubaniola.patienttag.activities.patient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jakubaniola.patienttag.activities.examination.ExaminationActivity;
import com.example.jakubaniola.patienttag.R;
import com.example.jakubaniola.patienttag.base.Adapters.NothingSelectedSpinnerAdapter;
import com.example.jakubaniola.patienttag.base.Adapters.SexSpinnerAdapter;
import com.example.jakubaniola.patienttag.base.TransportObjects.PatientTO;
import com.example.jakubaniola.patienttag.base.TransportObjects.Sex;
import com.rey.material.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientActivity extends Activity {

    private PatientTO patient;

    @BindView(R.id.name_edittext)
    EditText nameET;
    @BindView(R.id.surname_edittext)
    EditText surnameET;
    @BindView(R.id.dateOfBirth_edittext)
    EditText dateOfBirthET;
    @BindView(R.id.sex_spinner)
    Spinner sexSpinner;
    @BindView(R.id.examinations_button)
    LinearLayout examinationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        ButterKnife.bind(this);

        setupSpinner();
        setupButtons();

        patient = (PatientTO) getIntent().getExtras().getSerializable("patient");
        if (patient != null){
            fillFields();
        }
    }

    private void setupButtons() {
        examinationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examinationsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                Intent intent = new Intent(PatientActivity.this, ExaminationActivity.class)
                        .putExtra("patient", patient);
                startActivity(intent);
                examinationsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });
    }

    private void setupSpinner() {
        SexSpinnerAdapter adapter = new SexSpinnerAdapter(getApplicationContext(), R.layout.sex_spinner_item, Sex.values());
        sexSpinner.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.sex_spinner_item, getApplicationContext()));
        sexSpinner.setPadding(16,16,0,0);
    }

    private void fillFields(){
        nameET.setText(patient.getName());
        surnameET.setText(patient.getSurname());
        dateOfBirthET.setText(patient.getDateOfBirth());
        sexSpinner.setSelection(patient.getSex().ordinal()+1);
    }

}

