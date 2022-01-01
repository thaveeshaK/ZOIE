package me.thaveesha.zoie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperData extends SQLiteOpenHelper {

    public DBHelperData(Context context) {
        super(context, "data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table dataStore(datetime TEXT primary key,rawtext TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists dataStore");
    }

    //Insert Method
    public boolean insertData(String datetime,String rawtext){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("datetime",datetime);
        contentValues.put("rawtext",rawtext);

        long result = db.insert("dataStore",null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    //Update Method
    public boolean updateUserData(String datetime,String rawtext) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rawtext", rawtext);
        Cursor cursor = db.rawQuery("Select * from dataStore where datetime = ?", new String[]{datetime});

        if (cursor.getCount() > 0) {

            long result = db.update("dataStore", contentValues, "datetime=?", new String[]{datetime});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    //Delete Method
    public boolean deleteData(String datetime) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from dataStore where datetime = ?", new String[]{datetime});

        if (cursor.getCount() > 0) {

            long result = db.delete("dataStore", "datetime=?", new String[]{datetime});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    //View Method
    public Cursor getData() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from dataStore", null);

        return cursor;
    }
    public Cursor getBetweenData(String from,String to) {

        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor cursor = db.rawQuery("Select * from moodDetails WHERE `datetime` >= '2021/08/20' and `datetime` <= '2021/08/30'", null);
        Cursor cursor = db.rawQuery("Select * from dataStore WHERE `datetime` >= " +from+ " and `datetime` <= " +to+ "", null);

        return cursor;
    }

    //View Exact Method
    public Cursor getDateData() {

        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor cursor = db.rawQuery("Select * from moodDetails WHERE `datetime` >= '2021/08/20' and `datetime` <= '2021/08/30'", null);
        Cursor cursor = db.rawQuery("Select * from dataStore", null);

        return cursor;
    }
}
