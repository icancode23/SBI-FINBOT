package com.example.nipunarora.finbot.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.nipunarora.finbot.Fragments.TextInputFragment;
import com.example.nipunarora.finbot.R;

public class Bot_Activity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout input;
    CardView voice_input,text_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_);
        voice_input=(CardView)findViewById(R.id.voice);
        text_input=(CardView)findViewById(R.id.textit);
        text_input.setOnClickListener(this);
        voice_input.setOnClickListener(this);

        /*input=(FrameLayout)findViewById(R.id.interaction_options);
        getSupportFragmentManager().beginTransaction().replace(R.id.interaction_options,new TextInputFragment(),"text").commit();*/
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.voice:
                Toast.makeText(this,"Voice Button cLicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.textit:
                Toast.makeText(this,"Text Button cLicked",Toast.LENGTH_SHORT).show();
                break;
            default:
                ;
                break;
        }
    }

}
