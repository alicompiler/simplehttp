package com.baghdadit.simplehttp;

import android.os.AsyncTask;

import com.baghdadit.simplehttp.config.RequestConfig;
import com.baghdadit.simplehttp.listeners.OnComplete;
import com.baghdadit.simplehttp.listeners.OnError;

import java.io.IOException;

public class AsyncSimpleHttpClient {
    private Task task;

    public AsyncSimpleHttpClient(RequestConfig config, OnComplete onComplete, OnError onError) {
        this.task = new Task(config, onComplete, onError);
    }

    public void sendRequest() {
        task.execute();
    }

    public boolean sending() {
        return this.task.sending();
    }

}

class Task extends AsyncTask<Void, Void, Void> {

    private SimpleHttpClient client;
    private OnComplete onComplete;
    private OnError onError;
    private boolean sending;
    private boolean error;
    private Exception exception;
    private SimpleHttpResponse response;

    Task(RequestConfig config, OnComplete onComplete, OnError onError) {
        this.client = new SimpleHttpClient(config);
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