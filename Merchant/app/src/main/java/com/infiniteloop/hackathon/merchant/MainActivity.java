package com.infiniteloop.hackathon.merchant;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static ArrayList<TransactionData> listOfTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfTransactions = new ArrayList<>();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();

        editor.putLong("BALANCE", 10000).apply();

        String title = "Hi " + prefs.getString(SignUpActivity.PREFERENCE_NAME, "User");
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        setTitle(title);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isNetworkAvailable())
                 Snackbar.make(view, "No Internet Connection", Snackbar.LENGTH_SHORT);
                else new TransitionTask().execute(prefs.getInt(SignUpActivity.USER_ID, 0));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, new HomeFragment()).
                        commit();

        } else if (id == R.id.nav_transactions) {
            //if (!getFragmentManager().findFragmentById(R.id.transaction_fragment).isVisible())
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, new TransactionFragment()).
                        commit();


        } else if (id == R.id.nav_account_info) {
            //if (!getFragmentManager().findFragmentById(R.id.info_fragment).isVisible())
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, new InfoFragment()).
                        commit();

        } else if (id == R.id.nav_settings) {
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public ArrayList<TransactionData> getAllTransactions(JSONArray array,Integer integer) throws JSONException {
        ArrayList<TransactionData> list = new ArrayList<>(10);
        TransactionData transactionData = new TransactionData();
        int i=0;
        for(i=0;i<array.length();i++) {
            JSONObject obj = array.getJSONObject(i);
            transactionData.setUname(obj.getString("Name"));
            transactionData.setAmount(Long.parseLong(obj.getString("Amount")));
            list.add(transactionData);
            transactionData.setStatus(obj.getString("Status"));
            transactionData.setPartnerName(obj.getString("Partnername"));
            transactionData.setMid(Integer.parseInt(obj.getString("Mid")));
            transactionData.setTid(Integer.parseInt(obj.getString("Tid")));
            transactionData.setUid(Integer.parseInt(obj.getString("Uid")));
            Log.d("JSON",obj.getString("Name"));
            transactionData.setDate(obj.getString("Date"));
        }
        return list;
    }

    public class TransitionTask extends AsyncTask<Integer,Void,Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            String urlStr = "http://xamplify.16mb.com/Payment/merTransactions.php";
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader reader;
                String line, jsonObjectStr;
                InputStream inputStream = connection.getInputStream();

                JSONArray jsonArray;
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonObjectStr = buffer.toString();
                jsonArray = new JSONArray(jsonObjectStr);
                listOfTransactions = getAllTransactions(jsonArray, params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
