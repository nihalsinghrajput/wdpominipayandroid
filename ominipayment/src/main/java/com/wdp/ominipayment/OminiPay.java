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
        ((Activity)context).startActivityForResult(intent,600);
    }

    public static void CancelSubscription(Context context, String username, String password, String key, String details)
    {
        Intent intent = new Intent(context,CancelSubscription.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("key",key);
        intent.putExtra("details",details);
        ((Activity)context).startActivityForResult(intent,700);
    }


    public static void Checkout(
                                Context context, String username, String password,
                                String key, String customer_name, String customer_email,
                                String amount, String currency, String remark)
    {
        Intent intent = new Intent(context,CheckoutActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("key",key);
        intent.putExtra("customer_name",customer_name);
        intent.putExtra("customer_email",customer_email);
        intent.putExtra("amount",amount);
        intent.putExtra("currency",currency);
        intent.putExtra("remark",remark);
        ((Activity)context).startActivityForResult(intent,800);
    }

}
