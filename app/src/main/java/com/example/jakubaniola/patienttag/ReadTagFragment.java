package com.example.jakubaniola.patienttag;

import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadTagFragment extends Fragment {

    @BindView(R.id.read_tag_text_view)
    TextView readTagTextView;

    private ListenerInterface mListener;

    public static ReadTagFragment newInstance() {
        Bundle args = new Bundle();
        ReadTagFragment fragment = new ReadTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_tag, container, false);
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

    public void onNfcDetected(Ndef ndef){
        readFromNFC(ndef);
    }

    private void readFromNFC(Ndef ndef) {
        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            String message = new String(ndefMessage.getRecords()[0].getPayload());
            readTagTextView.setText(message);
            readTagTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            ndef.close();
        } catch (IOException | FormatException e) {
            e.printStackTrace();
        }
    }

}
