package com.infiniteloop.hackathon.merchant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WelcomeActivity extends AppCompatActivity {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Button signUpBtn;
    Button signInBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        prefs=PreferenceManager.getDefaultSharedPreferences(WelcomeActivity.this);
        editor=prefs.edit();

        if(prefs.getString(SignUpActivity.PREFERENCE_EMAILID,null)!=null){
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        signInBtn=(Button)findViewById(R.id.sign_in_button);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUpBtn=(Button)findViewById(R.id.sign_up_button);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}