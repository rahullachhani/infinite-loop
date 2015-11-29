package com.barclays.consumer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    Intent i;
    public static String PREFERENCES_TYPE = "pref_type";
    public static String PREFERENCES_API_PAYU = "payu";
    public static String PREFERENCES_API_PAYPAL = "paypal";
    public static String PREFERENCES_API_VISA = "visa";
    SharedPreferences prefs;
    boolean payPalavailable = false;
    boolean payUMoneyavailable = false;
    boolean visaavailable = false;

    boolean payPalChecked= false;
    boolean payUMoneyChecked= false;
    boolean visaChecked= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle("Payment");
        payPalavailable = false;
        payUMoneyavailable = false;
        visaavailable = false;

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Payment.this);
        payPalavailable = prefs.getBoolean(PaymentOptions.PREFERENCES_IS_PAYPAL, false);
        payUMoneyavailable = prefs.getBoolean(PaymentOptions.PREFERENCES_IS_PAYUMONEY, false);
        visaavailable = prefs.getBoolean(PaymentOptions.PREFERENCES_IS_VISA, false);

        int visaAmt = prefs.getInt(PaymentOptions.PREFERENCES_AMOUNT_VISA, 0);
        int paypalAmt = prefs.getInt(PaymentOptions.PREFERENCES_AMOUNT_PAYPAL, 0);
        int payuMoney = prefs.getInt(PaymentOptions.PREFERENCES_AMOUNT_PAYUMONEY, 0);

//        Toast.makeText(Payment.this, "Visa " + visaavailable + " PaUMoney " +
//                payUMoneyavailable + " PayPal " + payPalavailable, Toast.LENGTH_SHORT).show();

        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup_payment);
        final RadioButton payPalRadio = (RadioButton) findViewById(R.id.radioButton_payPal);
        final RadioButton visaRadio = (RadioButton) findViewById(R.id.radioButton_visa);
        final RadioButton payUMoneyRadio = (RadioButton) findViewById(R.id.radioButton_payUMoney);
        final RadioButton paySplitRadio = (RadioButton) findViewById(R.id.radioButton_splitpay);

        if(payPalavailable){
            payPalRadio.setEnabled(true);
        }else{
            payPalRadio.setEnabled(false);
        }

        if(payUMoneyavailable){
            payUMoneyRadio.setEnabled(true);
        }else{
            payUMoneyRadio.setEnabled(false);
        }

        if(visaavailable){
            visaRadio.setEnabled(true);
        }else{
            visaRadio.setEnabled(false);
        }

        final LinearLayout layout = (LinearLayout) findViewById(R.id.ll_visibility);


           payPalRadio.setText("PayPal                     Rs." + paypalAmt);
             visaRadio.setText("Visa Checkout        Rs." + visaAmt);
        payUMoneyRadio.setText("Pay U Money          Rs." + payuMoney);




        final Button payNow = (Button) findViewById(R.id.button_PayNow);

        final int amountToPay = prefs.getInt(PurchaseActivity.PREFERENCES_PURCHASE_AMOUNT, 350);
        payNow.setText("Pay Now Rs." + amountToPay);

        if(paypalAmt < amountToPay){
            payPalRadio.setEnabled(false);
        }
        if(visaAmt < amountToPay){
            visaRadio.setEnabled(false);
        }
        if(payuMoney < amountToPay){
            payPalRadio.setEnabled(false);
        }

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.radioButton_payPal) {
                    payPalChecked = payPalRadio.isChecked();
                    payUMoneyChecked = false;
                    visaChecked = false;
//                    Toast.makeText(Payment.this, "payPal clicked", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton_payUMoney) {
                    payUMoneyChecked = payUMoneyRadio.isChecked();
                    visaChecked = false;
                    payPalChecked = false;
//                    Toast.makeText(Payment.this, "payUmoney clicked", Toast.LENGTH_SHORT).show();
                } else if(i == R.id.radioButton_visa){
                    visaChecked = visaRadio.isChecked();
                    payPalChecked = false;
                    payUMoneyChecked = false;
//                    Toast.makeText(Payment.this, "visa clicked", Toast.LENGTH_SHORT).show();
                }else{
                    layout.setVisibility(View.VISIBLE);
                }
            }
        });



        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(payPalChecked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(PREFERENCES_TYPE, PREFERENCES_API_PAYPAL);
//                    Toast.makeText(Payment.this, "payPal clicked", Toast.LENGTH_SHORT).show();
                    int paypal = prefs.getInt(PaymentOptions.PREFERENCES_AMOUNT_PAYPAL, 0);
                    int amt = prefs.getInt(PurchaseActivity.PREFERENCES_PURCHASE_AMOUNT, 0);
                    int bal = paypal - amt;
                    editor.putInt(PaymentOptions.PREFERENCES_AMOUNT_PAYPAL, bal);
                    editor.commit();
                    Intent i = new Intent(Payment.this, Web.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.enter, R.anim.exit);

                }else if(payUMoneyChecked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(PREFERENCES_TYPE, PREFERENCES_API_PAYU);
//                    Toast.makeText(Payment.this, "payUmoney clicked", Toast.LENGTH_SHORT).show();
                    int payumoney = prefs.getInt(PaymentOptions.PREFERENCES_AMOUNT_PAYUMONEY, 0);
                    int amt = prefs.getInt(PurchaseActivity.PREFERENCES_PURCHASE_AMOUNT, 0);
                    int bal = payumoney - amt;
                    editor.putInt(PaymentOptions.PREFERENCES_AMOUNT_PAYUMONEY, bal);
                    editor.commit();
                    Intent i = new Intent(Payment.this, Web.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                }else if(visaChecked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(PREFERENCES_TYPE, PREFERENCES_API_VISA);
//                    Toast.makeText(Payment.this, "visa clicked", Toast.LENGTH_SHORT).show();
                    int visa = prefs.getInt(PaymentOptions.PREFERENCES_AMOUNT_VISA, 0);
                    int amt = prefs.getInt(PurchaseActivity.PREFERENCES_PURCHASE_AMOUNT, 0);
                    int bal = visa - amt;
                    editor.putInt(PaymentOptions.PREFERENCES_AMOUNT_VISA, bal);
                    editor.commit();
                    Intent i = new Intent(Payment.this, Web.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                }else if (paySplitRadio.isChecked()){
                    EditText etpayu = (EditText) findViewById(R.id.payUMoneySplitEditText);
                    EditText etpal = (EditText) findViewById(R.id.payPalSplitEditText);
                    EditText etvisa = (EditText) findViewById(R.id.visaSplitEditText);


                    int total = Integer.parseInt( (etpal.getText().toString()).equals("")?  "0" : etpal.getText().toString() ) +
                            Integer.parseInt( (etpayu.getText().toString()).equals("")?  "0" : etpayu.getText().toString() ) +
                            Integer.parseInt( (etvisa.getText().toString()).equals("")?  "0" : etvisa.getText().toString() );

//                    Toast.makeText(Payment.this, "Total = " + total, Toast.LENGTH_SHORT).show();
                    if(total == amountToPay){
                        Intent i = new Intent(Payment.this, Web.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                    }else{
                        payNow.setEnabled(true);
//                        Toast.makeText(Payment.this, "Incorrect Ammounts", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });



//        Button bCreditCard = (Button) findViewById(R.id.button_creditCard);
//        Button bDebitCard = (Button) findViewById(R.id.button_debitCard);
//        Button bAndroidPay = (Button) findViewById(R.id.button_androidPay);
//        Button bApplePay = (Button) findViewById(R.id.button_applePay);
//        Button bSamsungPay = (Button) findViewById(R.id.button_samsungPay);
//        Button bPayTm = (Button) findViewById(R.id.button_payTm);
//        Button bPayPal = (Button) findViewById(R.id.button_payPal);



        i = new Intent(Payment.this, Web.class);


//
//        bCreditCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = "";
//                i.putExtra("url", url);
//                startActivity(i);
//            }
//        });
//
//        bDebitCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        bApplePay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        bAndroidPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        bSamsungPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        bPayTm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        bPayPal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
