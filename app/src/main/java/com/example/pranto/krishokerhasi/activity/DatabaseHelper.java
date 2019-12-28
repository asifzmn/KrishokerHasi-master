package com.example.pranto.krishokerhasi.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data.db";
    public static final String table = "datatable";
    public SQLiteDatabase db;

    public DatabaseHelper(after_update_clicked context) {
        super(context, DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }

    public DatabaseHelper(after_catagory_clicked context) {
        super(context, DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }

    public DatabaseHelper(MainActivity context) {
        super(context, DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }

    public DatabaseHelper(after_crop_clicked context) {
        super(context, DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }

    public DatabaseHelper(after_fish_clicked context) {
        super(context, DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }

    public DatabaseHelper(after_animal_clicked context) {
        super(context, DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }

    public DatabaseHelper(after_fruits_clicked context) {
        super(context, DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }

    public DatabaseHelper(common_page context) {
        super(context, DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table+" (ID INTEGER,INFO TEXT)");
        //db.execSQL("DROP TABLE IF EXISTS datatable");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }

    public boolean insertData(int id,String info)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("INFO",info);
        long result = db.insert(table,null,contentValues);
        if(result==-1)
            return false;
        return true;
    }

    public boolean UpdateData(int id,String info)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("INFO", info);
        db.update(table,contentValues,"ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Cursor getAllData(int i)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table+" where ID = "+i,null);
        return res;
    }

}