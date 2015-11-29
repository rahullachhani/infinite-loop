package com.infiniteloop.hackathon.merchant;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by parthparekh on 29/11/15.
 */
public class InfoFragment extends Fragment {
    TextView name,bank,balance,accno,ifsc;
    SharedPreferences prefs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs= PreferenceManager.getDefaultSharedPreferences(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView=inflater.inflate(R.layout.fragment_info_account, container, false);
            name=(TextView)rootView.findViewById(R.id.info_fragment_name_text);
            name.setText(prefs.getString(SignUpActivity.PREFERENCE_NAME,"User"));

            accno=(TextView)rootView.findViewById(R.id.info_fragment_accno_text);
            accno.setText(String.valueOf(prefs.getInt(SignUpActivity.PREFERENCE_ACCOUNT_NO, 0)));

            balance=(TextView)rootView.findViewById(R.id.info_fragment_balance_text);
            bank=(TextView)rootView.findViewById(R.id.info_fragment_bank_text);
            bank.setText(prefs.getString(SignUpActivity.PREFERENCE_BANK,"ICICI"));

            ifsc=(TextView)rootView.findViewById(R.id.info_fragment_ifsc_text);
            ifsc.setText(String.valueOf(prefs.getInt(SignUpActivity.PREFERENCE_IFSC_NO,0)));

        return rootView;
    }
}
