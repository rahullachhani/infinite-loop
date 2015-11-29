package com.infiniteloop.hackathon.merchant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignUpActivity extends AppCompatActivity {
    private Toolbar toolbarUp;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static String USER_ID = "USERID";
    public static String PREFERENCE_EMAILID = "EMAILID";
    public static String PREFERENCE_PASSWORD = "PASSWORD";
    public static String PREFERENCE_ACCOUNT_NO = "ACCOUNTNO";
    public static String PREFERENCE_MOBILE_NO = "MOBILENO";
    public static String PREFERENCE_NAME = "NAME";
    public static String PREFERENCE_BANK = "BANK";
    public static String PREFERENCE_IFSC_NO = "IFSC";
    private JSONObject jsonObject;

    private int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        toolbarUp = (Toolbar) findViewById(R.id.toolbar_sign_up);
        setSupportActionBar(toolbarUp);

        final EditText EditText_FirstName = (EditText) findViewById(R.id.first_name_edit_text);
        final EditText EditText_AccountNo = (EditText) findViewById(R.id.account_edit_text);
        final EditText EditText_email = (EditText) findViewById(R.id.email_edit_text);
        final EditText EditText_Password = (EditText) findViewById(R.id.password_up_edit_text);
        final EditText EditText_MobileNo = (EditText) findViewById(R.id.mobile_edit_text);
        final EditText EditText_ifsc = (EditText) findViewById(R.id.ifsc_edit_text);
        final Spinner spinner = (Spinner) findViewById(R.id.bank_spinner);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = EditText_FirstName.getText().toString();
                String accountNo = EditText_AccountNo.getText().toString();
                String email = EditText_email.getText().toString();
                String password = EditText_Password.getText().toString();
                String mobileNo = EditText_MobileNo.getText().toString();
                String bank = spinner.getSelectedItem().toString();
                String ifsc = EditText_ifsc.getText().toString();

                jsonObject = new JSONObject();
                try {
                    jsonObject.put(PREFERENCE_NAME, firstName);
                    jsonObject.put(PREFERENCE_EMAILID, email);
                    jsonObject.put(PREFERENCE_PASSWORD, password);
                    jsonObject.put(PREFERENCE_MOBILE_NO, mobileNo);
                    jsonObject.put(PREFERENCE_BANK, bank);
                    jsonObject.put(PREFERENCE_ACCOUNT_NO, accountNo);
                    jsonObject.put(PREFERENCE_IFSC_NO, ifsc);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (email.contentEquals("") || password.contentEquals("")) {
                    Snackbar.make(view, "username or password is empty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    prefs = PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this);
                    editor = prefs.edit();

                    editor.putString(PREFERENCE_EMAILID, email);
                    editor.putString(PREFERENCE_PASSWORD, password);
                    editor.apply();
                    new RegisterTask().execute();
                    Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public class RegisterTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String urlStr = "http://xamplify.16mb.com/Payment/custSignup.php";
            try {
                DataOutputStream printout;
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Host", "android.schoolportal.gr");
                connection.connect();

                printout = new DataOutputStream(connection.getOutputStream());
                printout.writeUTF(jsonObject.toString());
                printout.flush();
                Log.d("RESPONSE", connection.getResponseMessage());
                printout.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        return null;}
    }
}

