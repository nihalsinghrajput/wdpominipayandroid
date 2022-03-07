package com.wdp.ominipayment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class OminiPay {

    public static void PaymentRequest(Context context, String username, String password, String key, String details){

        Intent intent = new Intent(context,Payment.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("key",key);
        intent.putExtra("details",details);
        ((Activity)context).startActivityForResult(intent,500);

    }

    public static void Subscription(Context context, String username, String password, String key, String details)
    {
        Intent intent = new Intent(context,Subscription.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("key",key);
        intent.putExtra("details",details);
        ((Activity)context).startActivityForResult(intent,500);
    }

}
