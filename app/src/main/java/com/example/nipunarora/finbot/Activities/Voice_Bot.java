package com.example.nipunarora.finbot.Activities;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nipunarora.finbot.Fragments.ATMCreditWallet;
import com.example.nipunarora.finbot.Fragments.IntroVoiceBot;
import com.example.nipunarora.finbot.Fragments.SpendingReview;
import com.example.nipunarora.finbot.R;

import java.util.List;
import java.util.Locale;

public class Voice_Bot extends AppCompatActivity implements TextToSpeech.OnInitListener{
    ImageView start_voice;
    TextToSpeech tts;
    private static final int SPEECH_REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice__bot);
        start_voice=(ImageView)findViewById(R.id.startVoice);
        tts=(TextToSpeech)new TextToSpeech(this,this);
        start_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Was clicked",Toast.LENGTH_SHORT).show();
                listen();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.voiceBotFrame,new SpendingReview(),"hey").commit();
    }


    /////************************************* onInit Text to speech*********************/
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //on compatible language you can facilitate your operations related to tts
                ;


            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
    //************************************ end init **************************/
    //**************************************** I/O operations of Finbot ********************/
    public void listen() {
        Log.d( "MAIN ACTIVITY","displaySpeechRecognizer:Worked ");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi");//the code "hi" denotes hindi
        //flushDisplay();
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    //****************************** Parsing Input Output **************************/

    // Get result from Speech Recognition activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            Toast.makeText(this,"You Said "+spokenText, Toast.LENGTH_SHORT).show();
            Log.d("you said",spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
