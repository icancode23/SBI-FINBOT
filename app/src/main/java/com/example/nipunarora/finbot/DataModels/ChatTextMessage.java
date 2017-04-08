package com.example.nipunarora.finbot.DataModels;

import java.io.Serializable;

/**
 * Created by nipunarora on 08/04/17.
 */

public class ChatTextMessage implements Serializable{
    private String text;
    private int source;   //This int will signify whether this is a self generated message or a message received from some foreign user
    static int view_type=2; //this int signifies decides the layout of the message
    public ChatTextMessage(int source,String text,int view_type) {
        this.text=text;
        this.source = source;
    }

    public String getText() {
        return text;
    }

    public int getSource() {
        return source;
    }
}

