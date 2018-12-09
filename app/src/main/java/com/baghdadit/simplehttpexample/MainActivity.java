package com.baghdadit.simplehttpexample;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baghdadit.simplehttp.AsyncSimpleHttpClient;
import com.baghdadit.simplehttp.config.HttpActionType;
import com.baghdadit.simplehttp.config.RequestConfig;
import com.baghdadit.simplehttp.config.RequestConfigBuilder;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

import org.json.JSONException;

import java.io.File;
import java.net.URI;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String SIMPLE_HTTP_LOGGING_TAG = "SIMPLE_HTTP";

    private static final String BASE_ULR = "http://192.168.0.102:8000/api/";
    private static final String GET_REQUEST_URL = BASE_ULR + "test_get";
    private static final String POST_REQUEST_URL = BASE_ULR + "test_post";
    private static final String UPLOAD_FILE_REQUEST_URL = BASE_ULR + "test_upload";


    private ProgressBar progressBar;
    private TextView responseTextView;
    private EditText numberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        responseTextView = findViewById(R.id.responseTextView);
        numberEditText = findViewById(R.id.numberInput);

    }

    public void sendGetRequest(View view) {
        String number = numberEditText.getText().toString();
        RequestConfig config = new RequestConfigBuilder()
                .url(GET_REQUEST_URL)
                .parameter("count", number)
                .header("API_SECRET", "SOME_KEY")
                .build();
        loading();
        AsyncSimpleHttpClient asyncClient = new AsyncSimpleHttpClient(config, response ->
        {
            //ON COMPLETE
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "Sending GET request completed");
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "response : " + response.asString());
            int length = response.asJsonArray().length();
            String message = String.format(Locale.ENGLISH, "response : %d random number generated", length);
            stopProgressAndSetMessage(message);
        }, exception ->
        {
            //ON ERROR
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "Sending GET request failed");
            Log.e(SIMPLE_HTTP_LOGGING_TAG, exception.getMessage());
            String message = "GET request failed";
            stopProgressAndSetMessage(message);
        });
        asyncClient.sendRequest();
    }

    public void sendPostRequest(View view) {
        String number = numberEditText.getText().toString();
        RequestConfig config = new RequestConfigBuilder()
                .url(POST_REQUEST_URL)
                .actionType(HttpActionType.POST)
                .parameter("number", number)
                .header("API_SECRET", "SOME_KEY")
                .build();
        loading();
        AsyncSimpleHttpClient asyncClient = new AsyncSimpleHttpClient(config, response ->
        {
            //ON COMPLETE
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "Sending POST request completed");
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "response : " + response.asString());
            try {
                int result = response.asJsonObject().getInt("result");
                String message = String.format(Locale.ENGLISH, "response : %d is the result", result);
                stopProgressAndSetMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, exception ->
        {
            //ON ERROR
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "Sending POST request failed");
            Log.e(SIMPLE_HTTP_LOGGING_TAG, exception.getMessage());
            String message = "POST request failed";
            stopProgressAndSetMessage(message);
        });
        asyncClient.sendRequest();
    }

    public void uploadFile(View view) {

        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;
        FilePickerDialog dialog = new FilePickerDialog(MainActivity.this, properties);
        dialog.setTitle("Select a File");
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                if (files.length > 0) {
                    File file = new File(files[0]);
                    uploadFile(file);
                }
            }
        });

        dialog.show();

    }

    private void uploadFile(File file) {
        String number = numberEditText.getText().toString();
        RequestConfig config = new RequestConfigBuilder()
                .url(UPLOAD_FILE_REQUEST_URL)
                .actionType(HttpActionType.POST)
                .parameter("number", number)
                .header("API_SECRET", "SOME_KEY")
                .attach(file, "file", null)
                .build();
        loading();
        AsyncSimpleHttpClient asyncClient = new AsyncSimpleHttpClient(config, response ->
        {
            //ON COMPLETE
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "upload request completed");
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "response : " + response.asString());
            try {
                String path = response.asJsonObject().getString("path");
                String message = String.format(Locale.ENGLISH, "response : %s", path);
                stopProgressAndSetMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, exception ->
        {
            exception.printStackTrace();

            //ON ERROR
            Log.i(SIMPLE_HTTP_LOGGING_TAG, "upload request failed");
            Log.e(SIMPLE_HTTP_LOGGING_TAG, exception.getMessage());
            String message = "upload request failed";
            stopProgressAndSetMessage(message);
        });
        asyncClient.sendRequest();
    }

    private void loading() {
        progressBar.setVisibility(View.VISIBLE);
        responseTextView.setText("Loading...");
    }

    private void stopProgressAndSetMessage(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        responseTextView.setText(message);
    }
}
