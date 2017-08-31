package com.example.root.todo1;

/**
 * Created by root on 27/7/17.
 */

/*in this class we will be accessing writable  database and perform some operation in database

 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String head, String description, String date, Integer stats) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_HEAD, head);
        contentValues.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        contentValues.put(DatabaseHelper.COLUMN_DATE, date);
        contentValues.put(DatabaseHelper.COLUMN_STATS, stats);
        sqLiteDatabase.insert(DatabaseHelper.TABLE_TODO, null, contentValues);
    }
        //retriving the value from the table
    public Cursor fetch(Integer status) {
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_HEAD, DatabaseHelper.COLUMN_DESCRIPTION, DatabaseHelper.COLUMN_DATE, DatabaseHelper.COLUMN_STATS};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_TODO, columns, DatabaseHelper.COLUMN_STATS + "=" + status, null, null, null, DatabaseHelper.COLUMN_DATE + " ASC");
        if (cursor != null) {
        }
        return cursor;
    }

    //fetching the values from the database

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_HEAD, DatabaseHelper.COLUMN_DESCRIPTION, DatabaseHelper.COLUMN_DATE, DatabaseHelper.COLUMN_STATS};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_TODO, columns, null, null, null, null, DatabaseHelper.COLUMN_DATE + " ASC");
        if (cursor != null) {
        }
        return cursor;
    }
    //updating the values to the table

    public int update(Integer _id, String title, String description, String date, Integer status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_HEAD, title);
        contentValues.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        contentValues.put(DatabaseHelper.COLUMN_DATE, date);
        contentValues.put(DatabaseHelper.COLUMN_STATS, status);

        int i = sqLiteDatabase.update(DatabaseHelper.TABLE_TODO, contentValues, DatabaseHelper.COLUMN_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TODO, DatabaseHelper.COLUMN_ID + " = " + _id, null);
    }

}