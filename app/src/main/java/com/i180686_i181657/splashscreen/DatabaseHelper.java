package com.i180686_i181657.splashscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public DatabaseHelper( Context context)
    {
        super(context, "Userdata.db" , null , 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB)
    {

        DB.execSQL("create Table Userdetails(name TEXT primary key , duration TEXT , explan TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion)
    {
        DB.execSQL("DROP TABLE IF EXISTS Userdetails");

    }

    public boolean insertuserdata(String name , String duration , String explan)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues( );
        contentValues.put("name" , name);
        contentValues.put("duration" , duration);
        contentValues.put("explan" , explan);

        long result = DB.insert("Userdetails" , null , contentValues) ;

        if(result == -1)
        {
            return false ;
        }
        else
        {
            return true ;
        }


    }


    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery(" select * from Userdetails " , null);
        return cursor ;


    }
}
