package com.barclays.consumer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private Toolbar toolbarUp;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public static String FirstName = "";
    public static String LastName = "";
    public static String Address = "";
    public static String City = "";
    public static String State = "";
    public static String Zip = "";
    public static String Country = "";
    public static String Email = "";
    public static String PhoneNo = "";

    public static String key_FirstName = "key_FirstName";
    public static String key_LastName = "key_LastName";
    public static String key_Address = "key_Address";
    public static String key_City = "key_City";
    public static String key_State = "key_State";
    public static String key_Zip = "key_Zip";
    public static String key_Country = "key_Country";
    public static String key_Email = "key_Email";
    public static String key_PhoneNo = "key_PhoneNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        toolbarUp = (Toolbar)findViewById(R.id.toolbar_sign_up);
        setSupportActionBar(toolbarUp);

        final EditText EditText_FirstName = (EditText) findViewById(R.id.first_name_edit_text);
        final EditText EditText_LastName = (EditText) findViewById(R.id.last_name_edit_text);
        final EditText EditText_Address = (EditText) findViewById(R.id.address_edit_text);
        final EditText EditText_city = (EditText) findViewById(R.id.city_edit_text);
        final EditText EditText_state = (EditText) findViewById(R.id.state_edit_text);
        final EditText EditText_zip = (EditText) findViewById(R.id.zip_edit_text);
        final EditText EditText_country = (EditText) findViewById(R.id.country_edit_text);
        final EditText EditText_email = (EditText) findViewById(R.id.email_edit_text);
        final EditText EditText_phone = (EditText) findViewById(R.id.phoneno_edit_text);

        final EditText EditText_Username = (EditText) findViewById(R.id.username_edit_text);
        final EditText EditText_Password = (EditText) findViewById(R.id.password_up_edit_text);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String username = EditText_Username.getText().toString();
                String password = EditText_Password.getText().toString();

                String firstName = EditText_FirstName.getText().toString();
                String lastName = EditText_LastName.getText().toString();
                String Address = EditText_Address.getText().toString();
                String city = EditText_city.getText().toString();
                String state = EditText_state.getText().toString();
                String zip = EditText_zip.getText().toString();
                String country = EditText_country.getText().toString();
                String email = EditText_email.getText().toString();
                String phone = EditText_phone.getText().toString();

                if(username.contentEquals("") || password.contentEquals("")){
                    Snackbar.make(view, "username or password is empty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    prefs= PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this);
                    editor=prefs.edit();

                    editor.putString(key_FirstName,firstName);
                    editor.putString(key_LastName,lastName);
                    editor.putString(key_Address,Address);
                    editor.putString(key_City,city);
                    editor.putString(key_State,state);
                    editor.putString(key_Zip,zip);
                    editor.putString(key_Country,country);
                    editor.putString(key_Email,email);
                    editor.putString(key_PhoneNo,phone);

                    editor.putString(SignInActivity.PREFERENCE_USERNAME,username);
                    editor.putString(SignInActivity.PREFERENCE_PASSWORD, password);
                    editor.commit();

                    Intent i = new Intent(SignUpActivity.this, PaymentOptions.class);
                    startActivity(i);
                    finish();
                }




            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
