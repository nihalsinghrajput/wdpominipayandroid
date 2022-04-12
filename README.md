# wdpominipayandroid
omini payment gateway 

Step 1 -
add maven in below files 

1. build.gradle(Project:DemoProjectName)
2. settings.gradle(Project setting)

allprojects 
{
		repositories {
			maven 
			{ 
			url 'https://jitpack.io'
			}
		}
	}
  
  
  
  Step 2- add dependency

  	dependencies 
	{
	        implementation 'com.github.nihalsinghrajput:wdpominipayandroid:ominiP1.6'
	}
	
	
	
Step 3- 

        String username = "psp_test.sgkvcacb.c2drdmNhY2I2YTc3MA==";
        String password = "b3pFSnVJb3V3SW5QTnFneVRFSy9wQT09";
        String key = "a134f83650694bf419fb78b4288c2197";


*for Payment use :

	JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name","Raj");
                    jsonObject.put("email","raj@mailinator.com");
                    jsonObject.put("amount","10");
                    jsonObject.put("currency","SAR");
                    jsonObject.put("order_id","420");
                    jsonObject.put("card_number","5105105105105100");
                    jsonObject.put("exp_month","12");
                    jsonObject.put("exp_year","23");
                    jsonObject.put("cvv","999");
                    jsonObject.put("remark","This payment is done by card Android");

//Convert Json into String and pass into Payment Api as String-

	 String params = jsonObject.toString();
	 
	 OminiPay.PaymentRequest(MainActivity.this,username,password,key,params);
	 
	 
	 
//get Response in OnActivityResult
if(data !=null && requestcode == 500)
{

}


@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if((data !=null && requestCode == 500) {
            String strValue = data.getStringExtra("payment_status");
            txtStatus.setText(strValue);
        }
    }
	 
	 
	 
	 
*for subscription - 
	
	 JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name","Raj");
                    jsonObject.put("email","raj@mailinator.com");
                    jsonObject.put("interval","1");
                    jsonObject.put("interval_type","3");
                    jsonObject.put("interval_count","");
                    jsonObject.put("amount","10");
                    jsonObject.put("currency","SAR");
                    jsonObject.put("order_id","420");
                    jsonObject.put("card_number","5105105105105100");
                    jsonObject.put("exp_month","12");
                    jsonObject.put("exp_year","23");
                    jsonObject.put("cvv","999");
		   
		    
		    
//Convert Json into String and pass into Payment Api as String-

	 String params = jsonObject.toString();
	 
	  OminiPay.Subscription(MainActivity.this,username,password,key,params);
	  
//get Response in OnActivityResult (requestcode == 600):

@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
         if(data != null && requestCode == 600) {
            String strValue = data.getStringExtra("payment_status");
            txtStatus.setText(strValue);
        }
    }
	

*for Cancel Subscription - 

	OminiPay.CancelSubscription(MainActivity.this,username,password,key,user_id);
	user_id = "2"
	
//get Response in OnActivityResult (requestcode == 700):

@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
         if(data != null && requestCode == 700) {
            String strValue = data.getStringExtra("cancel_status");
            txtStatus.setText(strValue);
        }
    }
    
    
    
    *for Payment Checkout
    
     OminiPay.Checkout(this,username,password,key,
                "customer_name",
                "customer_email","Price","currency","remark");
		
		
//get Response in OnActivityResult (requestcode == 800):
 if(data != null && requestCode == 800) {
            String strValue = data.getStringExtra("payment_status");
            txtStatus.setText(strValue);
        }

	
