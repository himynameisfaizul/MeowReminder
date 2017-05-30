package com.codemeister.meowreminder.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codemeister.meowreminder.model.Meow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imgur on 27/05/2017.
 */

public class SqliteDatabase extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "meow1";

    // Contacts table name
    private static final String TABLE_CAT = "cat";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "catname";
    private static final String KEY_BREED = "breed";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CAT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_BREED + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT);
        onCreate(db);
    }


    // Adding new meow
    public void addMeow(Meow meow) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, meow.getCatname());
        values.put(KEY_BREED, meow.getBreed());


        db.insert(TABLE_CAT, null, values);
        db.close();
    }

    public Meow getMeow(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CAT, new String[] { KEY_ID,
                        KEY_NAME, KEY_BREED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Meow meow = new Meow(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return meow;
    }


    // Getting All Meow
    public List<Meow> getAllMeow() {
        List<Meow> meowList = new ArrayList<Meow>();


        String selectQuery = "SELECT  * FROM " + TABLE_CAT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Meow meow = new Meow();
                meow.setId(Integer.parseInt(cursor.getString(0)));
                meow.setCatname(cursor.getString(1));
                meow.setBreed(cursor.getString(2));
                meowList.add(meow);
            } while (cursor.moveToNext());
        }
        return meowList;
    }
}
