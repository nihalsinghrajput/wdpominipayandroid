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
	 
	 
	 
*for subscription - 
	
	OminiPay.Subscription(MainActivity.this,username,password,key,params_subs);
	
	params format -
	String params_subs =
                        "{          \"name\":\"Raj\",\n" +
                                "            \"email\":\"raj@mailiinator.com\",\n" +
                                "            \"interval\":\"1\",\n" +
                                "            \"interval_type\":\"3\",\n" +
                                "            \"interval_count\":\"\",\n" +
                                "            \"amount\":10.00,\n" +
                                "            \"currency\":\"SAR\",\n" +
                                "            \"order_id\":420,\n" +
                                "            \"card_number\":\"5105105105105100\",\n" +
                                "            \"exp_month\":\"12\",\n" +
                                "            \"exp_year\":\"23\",\n" +
                                "            \"cvv\":\"999\"\n" +
                                "    }";
	

*for Cancel Subscription - 

	OminiPay.CancelSubscription(MainActivity.this,username,password,key,user_id);
	user_id = "2"
	
