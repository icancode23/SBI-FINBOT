package com.example.nipunarora.finbot.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nipunarora.finbot.R;

/**
 * Created by nipunarora on 04/04/17.
 */

public class TextInputFragment extends Fragment{
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.send_text, container, false);
        return rootview;
    }

    public TextInputFragment() {

    }
}
