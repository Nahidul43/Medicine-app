package com.example.medical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {
    public DatabaseHelper(Context context) {
        super(context, "medicine.db",  null, 1);

    }

    public Cursor getAlldata(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM brand ORDER BY brand_name ASC",null);
        return  cursor;

    }

    public Cursor seacrchList(String name){

        SQLiteDatabase database=this.getReadableDatabase();

     Cursor cursor=   database.rawQuery("select * from brand where brand_name like '%"+name+"%'",null);

     return cursor;

    }


    public Cursor ShowDetails(String id){

        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor=   database.rawQuery("select * from generic where generic_id like '%"+id+"%'",null);

        return cursor;

    }


}
