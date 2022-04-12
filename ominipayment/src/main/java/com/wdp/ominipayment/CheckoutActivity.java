package com.wdp.ominipayment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CheckoutActivity extends AppCompatActivity {

    WebView webView;
    RelativeLayout layoutProgress;
    private static   String AES_IV = "AGNNMLDKYPKEZDNK";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        String url = "https://psp.digitalworld.com.sa/checkouts/pay?";

        webView = (WebView) findViewById(R.id.webView);
        layoutProgress = (RelativeLayout) findViewById(R.id.layoutProgress);

        String uName = getIntent().getStringExtra("username");
        String  pass = getIntent().getStringExtra("password");
        String  key_s = getIntent().getStringExtra("key");
        String  customer_name = getIntent().getStringExtra("customer_name");
        String  customer_email = getIntent().getStringExtra("customer_email");
        String  amount = getIntent().getStringExtra("amount");
        String  currency = getIntent().getStringExtra("currency");
        String  remark = getIntent().getStringExtra("remark");

        url = url+"api_user_name="+uName;
        url = url+"&api_password="+pass;
        url = url+"&customer_name="+customer_name;
        url = url+"&customer_email="+customer_email;
        url = url+"&amount="+amount;
        url = url+"&currency="+currency;
        url = url+"&remark="+remark;


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        if (!TextUtils.isEmpty(url))
        {
            startWebView(url);
        }




    }

    private void startWebView(String url) {

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        progressDialog = new ProgressDialog(CheckoutActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                if (url.contains("payment_status=APPROVED")) {

                    Intent intent = new Intent();
                    intent.putExtra("payment_status", "Payment Success");
                    setResult(RESULT_OK, intent);
                    finish();
                    //Toast.makeText(OminiPayAndroid.this, "Payment Success!", Toast.LENGTH_SHORT).show();

                    return true;
                }
                else if (url.contains("payment_status=FAILED"))
                {
                    Intent intent = new Intent();
                    intent.putExtra("payment_status", "Payment Faild");
                    setResult(RESULT_OK, intent);
                    finish();
                    //Toast.makeText(OminiPayAndroid.this, "Error!", Toast.LENGTH_SHORT).show();
                    return true;
                }


                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }
        });
        webView.loadUrl(url);
    }
}