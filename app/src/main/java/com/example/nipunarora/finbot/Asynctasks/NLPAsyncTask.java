package com.example.nipunarora.finbot.Asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.nipunarora.finbot.Interfaces.AsyncTaskMailer;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class NLPAsyncTask extends AsyncTask<AIRequest, Void, AIResponse> {
    String TAG="NLPAsyncTask";
    AIDataService aiDataService;
    AsyncTaskMailer asyncTaskMailer;

    public NLPAsyncTask(AIDataService aiDataService,AsyncTaskMailer asyncTaskMailer) {
        this.aiDataService = aiDataService;
        this.asyncTaskMailer=asyncTaskMailer;
    }

    @Override
    protected AIResponse doInBackground(AIRequest... requests) {
        final AIRequest request = requests[0];
        try {
            final AIResponse response = aiDataService.request(request);
            return response;
        } catch (AIServiceException e) {
            Log.d(TAG, "ASYNCTASK" + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(AIResponse aiResponse) {
        if (aiResponse != null) {
            // process aiResponse here
            Result result = aiResponse.getResult();
            // Get the speech
            Log.d(TAG, "The speech is:" + result.getFulfillment().getSpeech());
             asyncTaskMailer.onReceiveMail(result.getFulfillment().getSpeech());
            //Get the parameters
            final HashMap<String, JsonElement> params = result.getParameters();
            if (params != null && !params.isEmpty()) {
                Log.i(TAG, "Parameters: ");
                for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                    Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
                }
            }


        }
    }
}
