package com.example.coronatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import java.io.IOException;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private TextView ResponseText;
    private String ResponseSaved;
    private String TurkeyUrl = "https://api.collectapi.com/corona/countriesData?country=Turkey"; //endpoint
    private String GlobalUrl = "https://api.collectapi.com/corona/totalData"; //endpoint


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResponseText = (TextView) findViewById(R.id.ResponseText);
    }

    public void onClickAllCountries(View v){
        Request(GlobalUrl);
    }

    public void onClickTurkey(View v){
        Request(TurkeyUrl);
    }

    public void Request(String url) {   //click event RequestButton
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .header("content-type", "application/json")
                    .header("authorization", "apikey 04gqRC2bcoXRMDwejdOMuP:7kikK3mxbBsAy8bMXy8QjU")
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ResponseText.setText("Failure");
                        }
                    });
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String MyResponse = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ResponseText.setText(MyResponse);
                            }
                            catch (Exception e) {
                                //ResponseText.setText("error");
                            }
                        }
                    });
                }
            });
        }
        catch(Exception e) {
            //ResponseText.setText("catch io");
        }
    }

}






