package com.example.jakubaniola.patienttag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WriteTagFragment extends Fragment {

    public static WriteTagFragment newInstance() {
        Bundle args = new Bundle();
        WriteTagFragment fragment = new WriteTagFragment();
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
        View view = inflater.inflate(R.layout.fragment_write_tag, container, false);
        return view;
    }

}
