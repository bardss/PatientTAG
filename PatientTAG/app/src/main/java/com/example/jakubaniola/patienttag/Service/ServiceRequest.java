package com.example.jakubaniola.patienttag.Service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.jakubaniola.patienttag.CheckTagActivity;
import com.example.jakubaniola.patienttag.TransportObjects.PatientTO;
import com.example.jakubaniola.patienttag.Utils.JsonUtil;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceRequest {

    private final OkHttpClient client = new OkHttpClient();

    public void getPatient(final Activity activity, final String id) throws Exception {
        final Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/user/" + id)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        ((CheckTagActivity) activity).errorMessageNoPatient(id);
                    }
                });
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                try {
                    String jsonData = response.body().string();
                    JSONObject jsonResponse = new JSONObject(jsonData);
                    final PatientTO patient = JsonUtil.getInstance().fromJson(jsonData, new TypeToken<PatientTO>() {
                    }.getType());
                    //final PatientTO patient = JsonUtil.jsonToPatient(jsonResponse);

                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            ((CheckTagActivity) activity).openPatientActivity(patient);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
