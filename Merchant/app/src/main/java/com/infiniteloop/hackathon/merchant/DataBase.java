package com.infiniteloop.hackathon.merchant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by parthparekh on 28/11/15.
 */
public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="transaction.db";
    private static final int DATABASE_VERSION=1;
    public static final String CREATE_TRANSACTION_TABLE="CREATE TABLE"+TransactionData.TABLE_NAME+
            "Tid INTEGER AUTOINCREMENT PRIMARY KEY"+
            "Uid INTEGER NOT NULL"+
            "Uname VARCHAR NOT NULL"+
            "Mid INTEGER NOT NULL"+
            "partnerName VARCHAR NOT NULL"+
            "amount INTEGER NOT NULL"+
            "status VARCHAR NOT NULL"+
            "date NUMERIC(15,0) NOT NULL";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TransactionData.TABLE_NAME);
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }
    public void insertIntoDb(TransactionData o){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Tid",o.getTid());
        contentValues.put("Uid",o.getUid());
        contentValues.put("Uname",o.getUname());
        contentValues.put("Mid",o.getMid());
        contentValues.put("partnerName",o.getPartnerName());
        contentValues.put("amount",o.getAmount());
        contentValues.put("status",o.getStatus());
        contentValues.put("date",o.getDate());
        db.insert(TransactionData.TABLE_NAME,null,contentValues);

    }
}
