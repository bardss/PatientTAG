package com.example.jakubaniola.patienttag.TransportObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class PatientTO implements Serializable {

    private Integer id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private Sex sex;

    private ArrayList<ExaminationTO> examinations;

    public PatientTO(Integer id, String name, String surname, String dateOfBirth, Sex sex, ArrayList<ExaminationTO> examinations){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        if (examinations == null) {
            this.examinations = new ArrayList<>();
        } else {
            this.examinations = examinations;
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public ArrayList<ExaminationTO> getExaminations() {
        return examinations;
    }

    public void setExaminations(ArrayList<ExaminationTO> examinations) {
        this.examinations = examinations;
    }
}
