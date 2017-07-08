package com.example.jakubaniola.patienttag.activities.checktag;

import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jakubaniola.patienttag.R;

import java.io.IOException;
import java.nio.charset.Charset;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteTagFragment extends Fragment {

    @BindView(R.id.write_id_edit_text)
    EditText writeIdEditText;

    public static WriteTagFragment newInstance() {
        Bundle args = new Bundle();
        WriteTagFragment fragment = new WriteTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ListenerInterface mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_tag, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (CheckTagActivity)context;
        mListener.onDialogDisplayed();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onDialogDismissed();
    }

    public void onNfcDetected(Ndef ndef, String messageToWrite){
        writeToNfc(ndef,messageToWrite);
    }

    private void writeToNfc(Ndef ndef, String message){
        if (ndef != null) {

            try {
                ndef.connect();
                NdefRecord mimeRecord = NdefRecord.createMime("text/plain", message.getBytes(Charset.forName("UTF-8")));
                ndef.writeNdefMessage(new NdefMessage(mimeRecord));
                ndef.close();
            } catch (IOException | FormatException e) {
                e.printStackTrace();
                Log.e("error","error");
            } finally {
            }

        }
    }

    public String getIdToWrite(){
        return writeIdEditText.getText().toString();
    }

}
