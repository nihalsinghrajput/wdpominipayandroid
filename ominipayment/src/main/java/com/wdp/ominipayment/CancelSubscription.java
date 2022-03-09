package com.wdp.ominipayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CancelSubscription extends AppCompatActivity {

    WebView webView;
    RelativeLayout layoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_subscription);

        webView = (WebView) findViewById(R.id.webView);
        layoutProgress = (RelativeLayout) findViewById(R.id.layoutProgress);

        String uName = getIntent().getStringExtra("username");
        String  pass = getIntent().getStringExtra("password");
        String  key_s = getIntent().getStringExtra("key");
        String  details_josn = getIntent().getStringExtra("details");

        if (TextUtils.isEmpty(uName))
        {
            Toast.makeText(CancelSubscription.this, "Payment gateway Username Required!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(CancelSubscription.this, "Payment gateway Password Required!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(key_s))
        {
            Toast.makeText(CancelSubscription.this, "key missing!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(details_josn))
        {
            Toast.makeText(CancelSubscription.this, "data missing!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            new ApiCall(uName,pass,key_s,details_josn).execute();
        }


    }

    class ApiCall extends AsyncTask
    {
        String username;
        String password;
        String key;
        int responseCode;
        String details;

        public ApiCall(String username,String password, String key, String detail) {
            this.username = username;
            this.password = password;
            this.key = key;
            this.details = detail;
        }


        @Override
        protected Object doInBackground(Object[] objects) {

            String url = "https://psp.digitalworld.com.sa/api/v1/test/payments/cancelSubscription/";

            try {
                url = url + details;
                URL mUrl = new URL(url);
                HttpURLConnection httpConnection = (HttpURLConnection) mUrl.openConnection();
                String userpass = username + ":" + password;
                String basicAuth = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
                }
                httpConnection.setRequestProperty ("Authorization", basicAuth);
                httpConnection.setRequestMethod("GET");
                httpConnection.setRequestProperty("Content-length", "0");
                httpConnection.setUseCaches(false);
                httpConnection.setAllowUserInteraction(false);
                httpConnection.setConnectTimeout(100000);
                httpConnection.setReadTimeout(100000);
                httpConnection.connect();

                responseCode = httpConnection.getResponseCode();

                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(String.valueOf(sb));
                    String m = jsonObject.getString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (responseCode==200)
            {
                layoutProgress.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.putExtra("cancel_status", "Cancel Request Success");
                setResult(RESULT_OK, intent);
                finish();
            }
            else if (responseCode == 401){
                layoutProgress.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.putExtra("cancel_status", "Already cancelled");
                setResult(RESULT_OK, intent);
                finish();
            }
            else {
                layoutProgress.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.putExtra("cancel_status", "something went wrong");
                setResult(RESULT_OK, intent);
                finish();
            }
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Object o) {
            super.onCancelled(o);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}