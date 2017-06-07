package com.example.jakubaniola.patienttag.Utils;

import com.example.jakubaniola.patienttag.TransportObjects.PatientTO;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

    public static PatientTO jsonToPatient(JSONObject jsonResponse) throws JSONException {
        Integer id = jsonResponse.getInt("id");
        String name = jsonResponse.getString("name");
        String surname = jsonResponse.getString("surname");
        return new PatientTO(id, name, surname);
    }

}
