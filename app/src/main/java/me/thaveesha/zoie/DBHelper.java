package me.thaveesha.zoie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "mood.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table moodDetails(datetime TEXT primary key,emotion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists moodDetails");
    }

    //Insert Method
    public boolean insertData(String datetime,String emotion){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("datetime",datetime);
        contentValues.put("emotion",emotion);

        long result = db.insert("moodDetails",null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    //Update Method
    public boolean updateUserData(String datetime,String emotion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emotion", emotion);
        Cursor cursor = db.rawQuery("Select * from Userdetails where datetime = ?", new String[]{datetime});

        if (cursor.getCount() > 0) {

            long result = db.update("moodDetails", contentValues, "datetime=?", new String[]{datetime});

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

        Cursor cursor = db.rawQuery("Select * from moodDetails where datetime = ?", new String[]{datetime});

        if (cursor.getCount() > 0) {

            long result = db.delete("moodDetails", "datetime=?", new String[]{datetime});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    //View Method WHERE InvoiceDate BETWEEN '2010-01-01' AND '2010-01-31'
    public Cursor getData() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from moodDetails", null);

        return cursor;
    }

    //View Exact Method
    public Cursor getDateData(String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor cursor = db.rawQuery("Select * from moodDetails WHERE `datetime` >= '2021/08/20' and `datetime` <= '2021/08/30'", null);
        Cursor cursor = db.rawQuery("Select * from moodDetails WHERE `datetime` =  date('now')", null);

        return cursor;
    }

    //View Between Method
    public Cursor getBetweenData(String from,String to) {

        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor cursor = db.rawQuery("Select * from moodDetails WHERE `datetime` >= '2021/08/20' and `datetime` <= '2021/08/30'", null);
        Cursor cursor = db.rawQuery("Select * from moodDetails WHERE `datetime` >= " +from+ " and `datetime` <= " +to+ "", null);

        return cursor;
    }
}
