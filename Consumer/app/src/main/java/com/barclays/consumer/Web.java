package com.barclays.consumer;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Web extends AppCompatActivity {
    String IP, path, parameters="";
    SharedPreferences prefs;
    int amount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setTitle("Payment in Proces...");

        prefs = PreferenceManager.getDefaultSharedPreferences(Web.this);
        String firstName = prefs.getString(SignUpActivity.key_FirstName, "parag");
        String lastName = prefs.getString(SignUpActivity.key_LastName, "pachpute");
        String Address = prefs.getString(SignUpActivity.key_Address, "BigBangWall");
        String city = prefs.getString(SignUpActivity.key_City, "London");
        String state = prefs.getString(SignUpActivity.key_State, "Maharashtra");
        String zip = prefs.getString(SignUpActivity.key_Zip, "421501");
        String country = prefs.getString(SignUpActivity.key_Country, "UK");
        String email = prefs.getString(SignUpActivity.key_Email, "paragpachpute3@gmail.com");
        String phone = prefs.getString(SignUpActivity.key_PhoneNo, "8698030488");

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Web.this);
        String type = prefs.getString(Payment.PREFERENCES_TYPE, Payment.PREFERENCES_API_VISA);

        amount = prefs.getInt(PurchaseActivity.PREFERENCES_PURCHASE_AMOUNT, 350);

        if(type.contentEquals(Payment.PREFERENCES_API_PAYPAL)){
//            Toast.makeText(Web.this, "payPal found", Toast.LENGTH_SHORT).show();
            IP = "http://xamplify.16mb.com";
            //IP = "http://10.228.154.82";
            path = "/Payment/PayPal/index.php";
            parameters = "?product_quantity=1" +
                    "&product_amount=" + amount +
                    "&cmd=_cart&currency_code=INR" +
                    "&invoice=090805" +
                    "&product_name=pepe" +
                    "&product_id=123" +
                    "&payer_fname=" + firstName +
                    "&payer_lname=" + lastName +
                    "&payer_address=" + Address +
                    "&payer_city=" + city +
                    "&payer_state=" + state +
                    "&payer_country=" + country +
                    "&payer_zip=" + zip +
                    "&payer_email=" + email ;

        }else if(type.contentEquals(Payment.PREFERENCES_API_VISA)){
//            Toast.makeText(Web.this, "visa found", Toast.LENGTH_SHORT).show();
            IP = "http://xamplify.16mb.com";
            path = "/Payment/VISA/payment_visa.php";
            parameters = "?amount=" + amount +
                    "&fname=" + firstName +
                    "&email=" + email +
                    "&mname=" + "Amazon" ;

        }else if(type.contentEquals(Payment.PREFERENCES_API_PAYU)){
//            Toast.makeText(Web.this, "payumoney found", Toast.LENGTH_SHORT).show();
            IP = "http://xamplify.16mb.com";
            path = "/Payment/PayUMoney/payumoney_aggregator_form.php";
            parameters = "?amount=" + amount +
                    "&fname=" + firstName +
                    "&email=" + email +
                    "&phone=" + phone;
        }

        WebView wv1 = (WebView) findViewById(R.id.webView);

        //webView.loadUrl("IP + path);

        wv1.setWebViewClient(new MyBrowser());
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(IP + path + parameters);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
