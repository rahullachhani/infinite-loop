package com.infiniteloop.hackathon.merchant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

/**
 * Created by parthparekh on 28/11/15.
 */
public class HomeFragment extends Fragment {
    private PieChart mPieChart;
    TextView balanceText;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public HomeFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs= PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        balanceText=(TextView)rootView.findViewById(R.id.home_fragment_balance_text);
        balanceText.setText(String.valueOf(prefs.getLong("BALANCE",10000)));
        return rootView;
    }
}
