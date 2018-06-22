package com.example.peris.myapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Peris on 3/1/2018.
 */

public class SqliteDBHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME= "college.db";
    private static int DATABASE_VERSION = 1;
    public static String TABLE_NAME = "student";
    public static String ID = "_id";
    public static String NAME = "name";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    public static String EMAIL = "email";
    public static String CONTACT= "contact";

    private Context context;

    private static String CREATE_TABLE = "CREATE table " + TABLE_NAME + "( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            NAME + " varchar(250) ," +
            USERNAME + " varchar(250) ," +
            PASSWORD + " varchar(250) ," +
            EMAIL + " varchar(250) ," +
            CONTACT + " varchar(200) " +
            ");";
    //CONSTURCTOR
    public SqliteDBHelper(Context context){
        //calls parent sqlitedbhelper
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context= context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d("Test", CREATE_TABLE);
            db.execSQL(CREATE_TABLE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String checkPass(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query ="select USERNAME and PASSWORD from "+TABLE_NAME;
        Cursor cursor= db.rawQuery(query, null);
        String pass,user;
        pass="not found";
        Log.d("Check","Check pass");
        if (cursor.moveToFirst()){
            do {
                user = cursor.getString(0);


                if (user.equals(username))
                {
                    pass= cursor.getString(1);
                    break;
                }

            }while (cursor.moveToNext());
        }
        return pass;

    }
}
