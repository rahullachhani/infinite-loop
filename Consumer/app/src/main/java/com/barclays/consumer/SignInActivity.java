package com.barclays.consumer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.test.SingleLaunchActivityTestCase;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {
    private Toolbar toolbarIn;
    private String USERNAME;
    private String PASSWORD;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public static String PREFERENCE_USERNAME = "USERNAME";
    public static String PREFERENCE_PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        prefs=PreferenceManager.getDefaultSharedPreferences(this);
        //editor=prefs.edit();
//
//        toolbarIn = (Toolbar) findViewById(R.id.toolbar_sign_in);
//        setSupportActionBar(toolbarIn);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userEdit=(EditText)findViewById(R.id.name_edit_text);
                EditText passEdit=(EditText)findViewById(R.id.password_edit_text);
                USERNAME=userEdit.getText().toString();
                PASSWORD=passEdit.getText().toString();
                if(USERNAME.equals("")||PASSWORD.equals("")||USERNAME==null||PASSWORD==null)
                    Snackbar.make(view, "Enter details", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                else {
//                    editor.putString(PREFERENCE_USERNAME,USERNAME).apply();
//                    editor.putString(PREFERENCE_PASSWORD,PASSWORD).apply();
                    String actualUsername = prefs.getString(PREFERENCE_USERNAME, "");
                    String actualPassword = prefs.getString(PREFERENCE_PASSWORD, "");

                    if(USERNAME.contentEquals(actualUsername) && PASSWORD.contentEquals(actualPassword)){
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Snackbar.make(view, "Incorrect Credentials", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
