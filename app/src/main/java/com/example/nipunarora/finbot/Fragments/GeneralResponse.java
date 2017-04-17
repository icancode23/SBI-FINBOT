package com.example.nipunarora.finbot.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nipunarora.finbot.R;

/**
 * Created by nipunarora on 17/04/17.
 */

public class GeneralResponse extends Fragment {
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.general_response, container, false);
        //************* SETUP THE LAYOUT OF THE GENERAL RESPONSE  as in whether more query text and image is required or not*******************//
        setupLayout();
        return rootview;
    }

    public GeneralResponse() {

    }
    private void setupLayout()
    {
        Bundle args=getArguments();
        Log.d("General Reponse","The Arguements received are:"+ args.toString());
        Boolean img= args.getBoolean("Image");
        Boolean more=args.getBoolean("morequery");
        TextView intro=(TextView)rootview.findViewById(R.id.intro);
        intro.setText(args.getString("intro"));
        if (img)
        {
            ImageView imgholder=(ImageView)rootview.findViewById(R.id.imageContent);
            imgholder.setVisibility(View.VISIBLE);
            int image=args.getInt("imgdrawable");
            imgholder.setImageResource(image);

        }
        if (more)
        {
            TextView moretext=(TextView)rootview.findViewById(R.id.morequery);
            moretext.setVisibility(View.VISIBLE);
            moretext.setText(args.getString("moretext"));
        }


    }
}

