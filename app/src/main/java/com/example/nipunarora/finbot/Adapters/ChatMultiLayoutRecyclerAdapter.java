package com.example.nipunarora.finbot.Adapters;

/**
 * Created by nipunarora on 08/04/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nipunarora.finbot.DataModels.ChatTextMessage;
import com.example.nipunarora.finbot.R;
import com.example.nipunarora.finbot.ViewHolders.ChatRecyclerTextMessageHolder;

import java.util.ArrayList;

/***************** The Adapter created below can allow the recycler view to inflate mutiple view types
in this case we are trying to implement quick replies,sliders and general text messages ************************/

public class ChatMultiLayoutRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<Object> chat_object_list;
    /********************** DECLARE CODES FOR ALL KINDS OF Chat Objects ************/

    /*************************** CODES FOR OBJECTS BASED ON SOURCE(INCOMING/OUTGOING)*****************/
    private static final int MESSAGE_TEXT_INCOMING=0;
    private static final int MESSAGE_TEXT_OUTGOING=1;

    /********************************************/

    /*********************** CODES FOR OBJECTS BASED ON VIEW TYPE **********/


    /****************************************/

    public ChatMultiLayoutRecyclerAdapter(ArrayList<Object> chat_object_list) {
        this.chat_object_list = chat_object_list;
    }

    @Override
    public int getItemCount() {
        return chat_object_list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object temp=chat_object_list.get(position);

        //TODO check what does getClass.getName returns and then think of a possibility for a switch case in place of the below if-else ladder

        if(temp instanceof ChatTextMessage)
        {
            ChatTextMessage m=(ChatTextMessage)temp;
            if (m.getSource()==MESSAGE_TEXT_INCOMING)
            {
                return MESSAGE_TEXT_INCOMING;
            }
            else
            {
                return MESSAGE_TEXT_OUTGOING;
            }
        }
        else //the default condition
        {
            return MESSAGE_TEXT_OUTGOING;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Switch case determining the source of message and type of the message which eventually decides the orientation of the message
        // as in whether it would be left-right or right to left

        switch (viewType) {
            case MESSAGE_TEXT_INCOMING:
                View v1 = inflater.inflate(R.layout.chat_text_message_righttoleft, parent, false);
                viewHolder = new ChatRecyclerTextMessageHolder(v1);
                break;
            case MESSAGE_TEXT_OUTGOING:
                View v2 = inflater.inflate(R.layout.chat_text_message_lefttoright, parent, false);
                viewHolder = new ChatRecyclerTextMessageHolder(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.chat_text_message_righttoleft,parent, false);
                viewHolder = new ChatRecyclerTextMessageHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType())
        {
            case MESSAGE_TEXT_INCOMING:ChatRecyclerTextMessageHolder temp=(ChatRecyclerTextMessageHolder)holder;
                ChatTextMessage tmp=(ChatTextMessage)chat_object_list.get(position);
                temp.text_message.setText(tmp.getText());
                break;

            case MESSAGE_TEXT_OUTGOING:ChatRecyclerTextMessageHolder temp1=(ChatRecyclerTextMessageHolder)holder;
                ChatTextMessage tmp1=(ChatTextMessage)chat_object_list.get(position);
                temp1.text_message.setText(tmp1.getText());
                break;
            default:;
        }
    }
}
