package com.example.nipunarora.finbot.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.nipunarora.finbot.Fragments.TextInputFragment;
import com.example.nipunarora.finbot.R;

public class Bot_Activity extends AppCompatActivity {
    FrameLayout input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_);
        input=(FrameLayout)findViewById(R.id.interaction_options);
        getSupportFragmentManager().beginTransaction().replace(R.id.interaction_options,new TextInputFragment(),"text").commit();
    }
}
