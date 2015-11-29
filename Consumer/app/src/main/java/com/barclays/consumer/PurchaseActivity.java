package com.barclays.consumer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;

public class PurchaseActivity extends AppCompatActivity {

    SharedPreferences prefs;
    int amount = 0;
    public static String PREFERENCES_PURCHASE_TITLE = "PREFERENCES_PURCHASE_TITLE";
    public static String PREFERENCES_PURCHASE_AMOUNT = "PREFERENCES_PURCHASE_AMOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        setTitle("Purchase Item");

        prefs = PreferenceManager.getDefaultSharedPreferences(PurchaseActivity.this);
        final SharedPreferences.Editor editor = prefs.edit();

        Button order1 = (Button) findViewById(R.id.order1);
        Button order2 = (Button) findViewById(R.id.order2);
        Button order3 = (Button) findViewById(R.id.order3);
        Button order4 = (Button) findViewById(R.id.order4);
        Button order5 = (Button) findViewById(R.id.order5);

        order1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = 318;
                editor.putInt(PREFERENCES_PURCHASE_AMOUNT, amount);
                editor.commit();
                Intent i = new Intent(PurchaseActivity.this, FullscreenActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = 181;
                editor.putInt(PREFERENCES_PURCHASE_AMOUNT, amount);
                editor.commit();
                Intent i = new Intent(PurchaseActivity.this, FullscreenActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        order3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = 135;
                editor.putInt(PREFERENCES_PURCHASE_AMOUNT, amount);
                editor.commit();
                Intent i = new Intent(PurchaseActivity.this, FullscreenActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        order4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = 145;
                editor.putInt(PREFERENCES_PURCHASE_AMOUNT, amount);
                editor.commit();
                Intent i = new Intent(PurchaseActivity.this, FullscreenActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        order5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = 368;
                editor.putInt(PREFERENCES_PURCHASE_AMOUNT, amount);
                editor.commit();
                Intent i = new Intent(PurchaseActivity.this, FullscreenActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_purchase);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(PurchaseActivity.this, Payment.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.enter, R.anim.exit);
//            }
//        });

//        TypedArray array = getResources().obtainTypedArray(R.array.array_products);
//        ArrayList<Integer> objects = new ArrayList<>(array.length());
//        for (int i=0; i < array.length(); i++)
//            objects.add(array.getResourceId(i, -1));
//
//        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(new EnthusiaSponsorsAdapter(this, objects));
//        animationAdapter.setAbsListView(((StaggeredGridView) this.findViewById(R.id.enthusia_grid_sponsors)));
//        ((StaggeredGridView) this.findViewById(R.id.enthusia_grid_sponsors)).setAdapter(animationAdapter);
    }
}
