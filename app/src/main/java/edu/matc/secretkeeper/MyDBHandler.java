package edu.matc.secretkeeper;

/**
 * Created by jfish on 2/27/2018.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;


public class MyDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "secrets.db";
    public static final String TABLE_SECRET = "secretTable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SECRET = "secret";


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SECRET_TABLE = "CREATE TABLE " + TABLE_SECRET +
                "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SECRET + " TEXT"
                + " )";
        // Next statement creates the table.
        // Does not re-create if it already exists.
        db.execSQL(CREATE_SECRET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Get rid of old table and create new one.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECRET);
        onCreate(db);
    }



    /**
     * Add a secret to the database, only moving secret because ID is auto incremented.
     */
    public void storeSecret(String nSecret)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_SECRET, nSecret);//store string


        db.insert(TABLE_SECRET, null, values);
        db.close();
    }

    /**
     * Get a secret from the database.
     */
    public Secret getSecret()
    {
        Secret sInfo = new Secret();
        String query = "SELECT * FROM " + TABLE_SECRET;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // Just get last record
        if (cursor.moveToLast())
        {
            cursor.moveToLast();
            sInfo.setSecret(cursor.getString(0));
            cursor.close();
        }
        else
        {
            sInfo = null;   // secret not found in db.
        }
        db.close();
        return sInfo;
    }

    public Cursor getData(){ //pulls data into listview
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SECRET;
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_SECRET +
                " WHERE " + COLUMN_SECRET + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_SECRET + " SET " + COLUMN_SECRET +
                " = '" + newName + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + COLUMN_SECRET + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_SECRET + " WHERE "
                + COLUMN_ID + " = '" + id + "'" +
                " AND " + COLUMN_SECRET + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }





}