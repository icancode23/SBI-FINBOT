package com.example.nipunarora.finbot.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nipunarora.finbot.Asynctasks.NLPAsyncTask;
import com.example.nipunarora.finbot.Fragments.ATMCreditWallet;
import com.example.nipunarora.finbot.Fragments.FormFilling;
import com.example.nipunarora.finbot.Fragments.GeneralResponse;
import com.example.nipunarora.finbot.Fragments.IntroVoiceBot;
import com.example.nipunarora.finbot.Fragments.SpendingReview;
import com.example.nipunarora.finbot.Interfaces.AsyncTaskMailer;
import com.example.nipunarora.finbot.R;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Metadata;
import ai.api.model.Result;
import ai.api.model.Status;

public class Voice_Bot extends AppCompatActivity implements TextToSpeech.OnInitListener,AIListener,AsyncTaskMailer{
    ImageView start_voice;
    TextToSpeech tts;
    HashMap<String,String> voicereplies;
    private static final int SPEECH_REQUEST_CODE = 0;
    static String TAG="VoiceBotActivity";
    String spech;
    AIDataService aiDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice__bot);
        start_voice=(ImageView)findViewById(R.id.startVoice);
        tts=(TextToSpeech)new TextToSpeech(this,this);
        voicereplies=new HashMap<String, String>();
        start_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Was clicked",Toast.LENGTH_SHORT).show();
                listen();
                
            }
        });

        final AIConfiguration config = new AIConfiguration(getResources().getString(R.string.client_access_token),
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

         aiDataService = new AIDataService(this,config);

        final AIRequest aiRequest = new AIRequest();

        //**************** Loading Intro Fragment **********//

        getSupportFragmentManager().beginTransaction().replace(R.id.voiceBotFrame,new IntroVoiceBot(),"hey").commitAllowingStateLoss();
        
        //******** Filling in voicereplies ***********//
        voicereplies.put("form","You can fill the home finance form  as shown in the image below");
        voicereplies.put("Spending","You spent Rs 4410 today");
        voicereplies.put("Homeloan","You have 80 percent chances of getting a loan of 70 lacs.Your loan percentage was calculated using behaviour score");
        voicereplies.put("wallet","Your cash wallet balance is rupees five thousand");
        voicereplies.put("goals"," if you cut down 10% from your entertainment and 5% on food you can afford it next month .");
        voicereplies.put("invest","You should primarily look  to invest in Forex.Other Options include Mutual Funds Reccuring Deposits Fixed Deposits");

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

    @Override
    public void onResult(AIResponse result) {
        final Status status = result.getStatus();
        Log.i(TAG, "Status code: " + status.getCode());
        Log.i(TAG, "Status type: " + status.getErrorType());
        //
        Log.i(TAG, "Resolved query: " + result.getResult().getResolvedQuery());
        //
        final Result result_1 = result.getResult();
        Log.i(TAG, "Action: " + result_1.getAction());
        //
        final String speech = result_1.getFulfillment().getSpeech();
        Log.i(TAG, "Speech: " + speech);
        //
        final Metadata metadata = result_1.getMetadata();
        if (metadata != null) {
            Log.i(TAG, "Intent id: " + metadata.getIntentId());
            Log.i(TAG, "Intent name: " + metadata.getIntentName());
        }
        //
        final HashMap<String, JsonElement> params = result_1.getParameters();
        if (params != null && !params.isEmpty()) {
            Log.i(TAG, "Parameters: ");
            for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
            }
        }
    }

    @Override
    public void onError(final AIError error) {
        Log.d("Api.ai Error",error.toString());
    }

    @Override
    public void onListeningStarted() {}

    @Override
    public void onListeningCanceled() {}

    @Override
    public void onListeningFinished() {}

    @Override
    public void onAudioLevel(final float level) {}

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

    private void speakOut(String text_to_be_spoken) {
        tts.speak(text_to_be_spoken, TextToSpeech.QUEUE_FLUSH, null,null);
        //enable the speaking button
        start_voice.setEnabled(Boolean.TRUE);


    }

    @Override
    public void onReceiveMail(Object Mail) {
        String reply=(String)Mail;
        speakOut(reply);
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
            start_voice.setEnabled(Boolean.FALSE);
            analyseUserInput(spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    void analyseUserInput(String current_query)
    {

        if (current_query.equals("what was my spending for the day"))
        {
            speakOut(voicereplies.get("Spending"));
            showGraphics(1);
        }
        else {
            if (current_query.equals( "help me fill the form for home loan")) {
                speakOut(voicereplies.get("form"));
                showGraphics(4);
            } else {
                if (current_query.equals( "what are my chances of getting a home loan of 70 lacs with my current financial status")) {
                    speakOut(voicereplies.get("Homeloan"));
                    showGraphics(2);
                } else {
                    if (current_query.equals( "I wish I had an iPhone")) {
                        speakOut(voicereplies.get("goals"));
                        showGraphics(3);
                    }
                    else {
                        if (current_query.equals( "show my cash wallet")) {
                            speakOut(voicereplies.get("wallet"));
                            showGraphics(6);
                        }
                        else {
                            if (current_query.equals( "I have some money how can I invest it")) {
                                speakOut(voicereplies.get("invest"));
                                showGraphics(5);
                            }
                            else {
                                AIRequest airequest1=new AIRequest();
                                airequest1.setQuery(current_query);
                                new NLPAsyncTask(aiDataService,this).execute(airequest1);
                            }
                        }
                    }
                }
            }

        }

    }
    //********************************* RENDERING GRAPHICS *************************//
    void showGraphics(int condition_code) {
        //**************** show the suitable graphs ******************/
        //hard coded for now
        switch (condition_code) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.voiceBotFrame, new SpendingReview(), "hey").commitAllowingStateLoss();
                break;
            case 2:
                GeneralResponse g = new GeneralResponse();
                Bundle args = new Bundle();
                args.putBoolean("Image", false);
                args.putBoolean("morequery", false);
                args.putString("intro", "You have 80% chances of getting \n a home loan of 70 lacs \n this was calculated using the \n behaviour score");
                g.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.voiceBotFrame, g, "hey").commitAllowingStateLoss();
                break;
            case 3:
                GeneralResponse g1 = new GeneralResponse();
                Bundle args1 = new Bundle();
                args1.putBoolean("Image", false);
                args1.putBoolean("morequery", false);
                args1.putString("intro", "if you cut down \n 10% from your entertainment \n and 5% on food \n you can afford it next month ");
                g1.setArguments(args1);
                getSupportFragmentManager().beginTransaction().replace(R.id.voiceBotFrame, g1, "hey").commitAllowingStateLoss();
                break;
            case 4:getSupportFragmentManager().beginTransaction().replace(R.id.voiceBotFrame, new FormFilling(), "hey").commitAllowingStateLoss();
                break;
            case 5:
                GeneralResponse g2 = new GeneralResponse();
                Bundle args2 = new Bundle();
                args2.putBoolean("Image", true);
                args2.putBoolean("morequery", true);
                args2.putInt("imgdrawable",R.drawable.invest);
                args2.putString("moretext","Other Options include: \n Mutual Funds \n Reccuring Deposits \n Fixed Deposits");
                args2.putString("intro","You should primarily look \n to invest in Forex(USD)");
                g2.setArguments(args2);
                getSupportFragmentManager().beginTransaction().replace(R.id.voiceBotFrame, g2, "hey").commitAllowingStateLoss();
                break;
            case 6:getSupportFragmentManager().beginTransaction().replace(R.id.voiceBotFrame, new ATMCreditWallet(), "hey").commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

}
