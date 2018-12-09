package com.baghdadit.simplehttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_ULR = "http://180.100.198.150:8000/api/";
    private static final String GET_REQUEST_URL = BASE_ULR + "test_get";
    private static final String POST_REQUEST_URL = BASE_ULR + "test_post";
    private static final String UPLOAD_FILE_REQUEST_URL = BASE_ULR + "test_upload";


    private ProgressBar progressBar;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        responseTextView = findViewById(R.id.responseTextView);

    }

    public void sendGetRequest(View view) {
        //RequestConfig config = new RequestConfig();
    }

    public void sendPostRequest(View view) {
    }

    public void uploadFile(View view) {
    }
}
