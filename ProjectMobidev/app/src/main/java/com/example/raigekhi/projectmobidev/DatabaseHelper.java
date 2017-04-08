package com.example.raigekhi.projectmobidev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by raigekhi on 3/19/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "Student_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "StudentName";
    public static final String COLUMN_4 = "Section";
    public static final String COLUMN_5 = "Major";
    public static final String COLUMN_6 = "Address";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,StudentName TEXT,Section TEXT,Major TEXT,Address TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String studentname, String section, String major, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, studentname);
        contentValues.put(COLUMN_4, section);
        contentValues.put(COLUMN_5, major);
        contentValues.put(COLUMN_6, address);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean searchdata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String []columns = {COLUMN_1,COLUMN_2,COLUMN_4,COLUMN_5,COLUMN_6};
        String []selectionArgs = {id};
        Cursor result = db.query(TABLE_NAME, columns, "ID = ?",selectionArgs,null,null,null);
        boolean found = false;
        if(result.moveToNext()){
            found=true;
        }
        return found;
    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }





    public boolean updateData(String id,String studentname, String section,String major,String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,id);
        contentValues.put(COLUMN_2,studentname);
        contentValues.put(COLUMN_4,section);
        contentValues.put(COLUMN_5,major);
        contentValues.put(COLUMN_6,address);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }


    }