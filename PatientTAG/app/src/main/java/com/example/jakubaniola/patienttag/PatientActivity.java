package com.example.jakubaniola.patienttag;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.jakubaniola.patienttag.TransportObjects.PatientTO;

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
    @BindView(R.id.sex_edittext)
    EditText sexET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        ButterKnife.bind(this);

        patient = (PatientTO) getIntent().getExtras().getSerializable("patient");
        if (patient != null){
            fillFields();
        }
    }

    private void fillFields(){
        nameET.setText(patient.getName());
        surnameET.setText(patient.getSurname());
    }

}

