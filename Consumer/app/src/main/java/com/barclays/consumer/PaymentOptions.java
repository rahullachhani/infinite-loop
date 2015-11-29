package com.barclays.consumer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class PaymentOptions extends AppCompatActivity {
    public static String PREFERENCES_IS_VISA = "visa";
    public static String PREFERENCES_IS_PAYUMONEY = "payumoney";
    public static String PREFERENCES_IS_PAYPAL = "paypal";

    public static String PREFERENCES_AMOUNT_VISA = "AMOUNTvisa";
    public static String PREFERENCES_AMOUNT_PAYUMONEY = "AMOUNTpayumoney";
    public static String PREFERENCES_AMOUNT_PAYPAL = "AMOUNTpaypal";

    boolean payPalChecked;
    boolean payUMoneyChecked;
    boolean visaChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);
        setTitle("Payment Options");

        final CheckBox payPal = (CheckBox) findViewById(R.id.checkBox_payPal);
        final CheckBox payUMoney = (CheckBox) findViewById(R.id.checkBox_payUMoney);
        final CheckBox visa = (CheckBox) findViewById(R.id.checkBox_visa);


        payPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payPalChecked = payPal.isChecked();
//                Toast.makeText(PaymentOptions.this, "payPal clicked", Toast.LENGTH_SHORT).show();
            }
        });

        payUMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payUMoneyChecked = payUMoney.isChecked();
//                Toast.makeText(PaymentOptions.this, "PayUMoney clicked", Toast.LENGTH_SHORT).show();
            }
        });

        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visaChecked = visa.isChecked();
//                Toast.makeText(PaymentOptions.this, "PayTm clicked", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_paymentOptions);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                boolean payPalChecked = payPal.isChecked();
//                boolean payUMoneyChecked = payUMoney.isChecked();
//                boolean payTmChecked = payTm.isChecked();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(PaymentOptions.this);
                SharedPreferences.Editor editor = prefs.edit();

//                Toast.makeText(PaymentOptions.this, "Visa " + visaChecked+ " PaUMoney " + payUMoneyChecked + " PayPal " + payPalChecked, Toast.LENGTH_SHORT).show();
                editor.putBoolean(PREFERENCES_IS_PAYPAL, payPalChecked);
                editor.putBoolean(PREFERENCES_IS_VISA, visaChecked);
                editor.putBoolean(PREFERENCES_IS_PAYUMONEY, payUMoneyChecked);

                if(payPalChecked){
                    editor.putInt(PREFERENCES_AMOUNT_PAYPAL, 2000);
                }else{
                    editor.putInt(PREFERENCES_AMOUNT_PAYPAL, 0);
                }

                if(visaChecked){
                    editor.putInt(PREFERENCES_AMOUNT_VISA, 2000);
                }else{
                    editor.putInt(PREFERENCES_AMOUNT_VISA, 0);
                }

                if(payUMoneyChecked){
                    editor.putInt(PREFERENCES_AMOUNT_PAYUMONEY, 2000);
                }else{
                    editor.putInt(PREFERENCES_AMOUNT_PAYUMONEY, 0);
                }

                editor.commit();
                Intent i = new Intent(PaymentOptions.this, PurchaseActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
    }
}
