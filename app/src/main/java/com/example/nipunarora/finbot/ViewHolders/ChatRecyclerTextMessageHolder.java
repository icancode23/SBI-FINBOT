package com.example.nipunarora.finbot.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.nipunarora.finbot.R;

/**
 * Created by nipunarora on 08/04/17.
 */

public class ChatRecyclerTextMessageHolder extends RecyclerView.ViewHolder {
    public TextView text_message;

    public ChatRecyclerTextMessageHolder(View itemView) {
        super(itemView);
        text_message= (TextView)itemView.findViewById(R.id.textView);
    }
}
