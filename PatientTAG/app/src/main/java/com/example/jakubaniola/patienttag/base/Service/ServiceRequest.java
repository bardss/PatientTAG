package com.example.jakubaniola.patienttag.base.Service;

import android.app.Activity;

import com.example.jakubaniola.patienttag.activities.checktag.CheckTagActivity;
import com.example.jakubaniola.patienttag.base.TransportObjects.PatientTO;
import com.example.jakubaniola.patienttag.base.Utils.JsonUtil;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceRequest {

    private final OkHttpClient client = new OkHttpClient();

    public void getPatient(final Activity activity, final String id) throws Exception {
        final Request request = new Request.Builder()
                .url("http://172.20.10.4:8080/user/" + id)
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

                String jsonData = response.body().string();
                final PatientTO patient = JsonUtil.getInstance().fromJson(jsonData, new TypeToken<PatientTO>() {
                }.getType());

                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        ((CheckTagActivity) activity).openPatientActivity(patient);
                    }
                });

            }
        });
    }

}
