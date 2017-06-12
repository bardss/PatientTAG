package com.example.jakubaniola.patienttag.TransportObjects;

import java.io.Serializable;

public class ExaminationTO implements Serializable {

    private String dateOfExamination;
    private Double temperature;
    private Double pressure;

    public ExaminationTO(String dateOfExamination, Double temperature, Double pressure){
        this.dateOfExamination = dateOfExamination;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public String getDateOfExamination() {
        return dateOfExamination;
    }

    public void setDateOfExamination(String dateOfExamination) {
        this.dateOfExamination = dateOfExamination;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
}
