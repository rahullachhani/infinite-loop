package com.infiniteloop.hackathon.merchant;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by parthparekh on 28/11/15.
 */
public class TransactionFragment extends Fragment {
   ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_transaction,container,false);
        //listView=(ListView)rootView.findViewById(R.id.list);
        //listView.setAdapter(new ArrayAdapter<TransactionData>(getContext(), android.R.layout.two_line_list_item,MainActivity.listOfTransactions));
        return rootView;
    }
}
