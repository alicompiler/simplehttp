package com.baghdadit.simplehttp;

import android.os.AsyncTask;

import com.baghdadit.simplehttp.SimpleHttpClient;
import com.baghdadit.simplehttp.SimpleHttpResponse;
import com.baghdadit.simplehttp.listeners.OnComplete;
import com.baghdadit.simplehttp.listeners.OnError;

import java.io.IOException;


public class AsyncSimpleHttpClient extends AsyncTask<Void, Void, Void> {

    private SimpleHttpClient client;
    private OnComplete onComplete;
    private OnError onError;
    private boolean sending;
    private boolean error;
    private Exception exception;
    private SimpleHttpResponse response;

    public AsyncSimpleHttpClient(SimpleHttpClient client, OnComplete onComplete, OnError onError) {
        this.client = client;
        this.onComplete = onComplete;
        this.onError = onError;
        this.sending = false;
        this.error = false;
        this.exception = null;
        this.response = null;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        this.sending = true;
        try {
            String response = this.client.sendRequest();
            this.response = new SimpleHttpResponse(response);
        } catch (IOException e) {
            this.error = true;
            this.exception = e;
        }
        this.sending = false;
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (this.error && this.onError != null) {
            this.onError.onError(this.exception);
        } else {
            if (this.onComplete != null) {
                this.onComplete.onComplete(this.response);
            }
        }
    }

    public boolean sending() {
        return this.sending;
    }
}