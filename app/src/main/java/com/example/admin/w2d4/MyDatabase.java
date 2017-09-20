package com.example.admin.w2d4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 9/19/2017.
 */

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DBNAME = "mydb.db";
    private static final int VERSION = 1;

    private static final String TABLE_NAME = "employees";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String PHONE_NUMBER = "phonenumber";
    private static final String BIRTH_CITY = "birthcity";
    private static final String JOB = "job";

    SQLiteDatabase myDB;

    public MyDatabase(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryTable = "CREATE TABLE " + TABLE_NAME
                + " ("
                + FIRST_NAME + " TEXT NOT NULL, "
                + LAST_NAME + " TEXT NOT NULL, "
                + PHONE_NUMBER + " TEXT NOT NULL, "
                + BIRTH_CITY + " TEXT NOT NULL, "
                + JOB + " TEXT NOT NULL "
                + ")";

        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void closeDB(){

        if(myDB != null && myDB.isOpen()){
            myDB.close();
        }

    }

    public long create(String fName, String lName, String pNumber, String bCity, String job){

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, fName);
        contentValues.put(LAST_NAME, lName);
        contentValues.put(PHONE_NUMBER, pNumber);
        contentValues.put(BIRTH_CITY, bCity);
        contentValues.put(JOB, job);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long insert(String fName, String lName, String pNumber, String bCity, String job){

        ContentValues values = new ContentValues();

        values.put(FIRST_NAME, fName);
        values.put(LAST_NAME, lName);
        values.put(PHONE_NUMBER, pNumber);
        values.put(BIRTH_CITY, bCity);
        values.put(JOB, job);

        return myDB.insert(TABLE_NAME, null, values);
    }


    public long update(String fName, String lName, String pNumber, String bCity, String job){

        ContentValues values = new ContentValues();

        values.put(FIRST_NAME, fName);
        values.put(LAST_NAME, lName);
        values.put(PHONE_NUMBER, pNumber);
        values.put(BIRTH_CITY, bCity);
        values.put(JOB, job);

        String where = FIRST_NAME + " = " + fName;

        return myDB.update(TABLE_NAME, values, where, null);
    }

    public long delete(String fName){

        String where = FIRST_NAME + " = " +fName;

        return myDB.delete(TABLE_NAME, where, null);
    }


    List<String> Read(String id) {
        Object[] values = {TABLE_NAME, FIRST_NAME, LAST_NAME, PHONE_NUMBER, BIRTH_CITY, JOB};

        String SELECT_STATEMENT = "SELECT * FROM {0}";

        if (!id.isEmpty()) {
            String whereStatement = " WHERE {1} = {6}";
            SELECT_STATEMENT = SELECT_STATEMENT + whereStatement;
        }

        String query = MessageFormat.format(SELECT_STATEMENT, values);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        List<String> persons = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToNext()) {
            do {
                Log.d("TAG", "Read: " +
                        "NAME: " + cursor.getString(0) +
                        "LASTNAME: " + cursor.getString(1) +
                        "PHONENUMBER: " + cursor.getString(2) +
                        "BIRTHCITY: " + cursor.getString(3) +
                        "JOB: " + cursor.getString(4)
                );

                persons.add(cursor.getString(0) + "|" + cursor.getString(1) + "|" + cursor.getString(2)+ "|" + cursor.getString(3)+ "|" + cursor.getString(4));

            } while (cursor.moveToNext());

        }
        return persons;
    }


}
