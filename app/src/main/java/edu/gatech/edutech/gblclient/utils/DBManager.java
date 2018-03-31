package edu.gatech.edutech.gblclient.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.edutech.gblclient.persistence.MyDBHandler;


public class DBManager {

    private MyDBHandler dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new MyDBHandler(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //Insert a new user to the database and Players table
    public void insertNewPlayer(String username, String firstname, String lastname, String email) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(MyDBHandler.USERNAME, username);
        contentValue.put(MyDBHandler.FIRSTNAME, firstname);
        contentValue.put(MyDBHandler.LASTNAME, lastname);
        contentValue.put(MyDBHandler.EMAIL, email);
        database.insert(MyDBHandler.TABLE_NAME_PLAYERS, null, contentValue);
    }


    // Update progressid for a particular username
    public void updateProgressID(String username,String progressid) {
        SQLiteDatabase db = database;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHandler.PROGRESSID, progressid);

        db.update(MyDBHandler.TABLE_NAME_PLAYERS,
                contentValues,
                "username = ?",
                new String[]{username});


    }

    //Get the progressid for a user
    public String getProgressID(String username){

        SQLiteDatabase db = database;
        Cursor cursor = null;
        String progressid = "";

        try {
            cursor = db.rawQuery("SELECT progressid FROM PLAYERS WHERE username = ?", new String[] {username});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                progressid = cursor.getString(cursor.getColumnIndex("progressid"));
            }
            return progressid;
        }finally {
            cursor.close();
        }


    }




    //// new code
    public boolean insertInProgress(String username, String lastPhraseState, String solves,String ProgressID) {

        Cursor CheckForExisting =getInProgress(username,ProgressID);
        if (CheckForExisting.moveToNext())
        {

                SQLiteDatabase db = database;
                ContentValues contentValues = new ContentValues();
                contentValues.put(MyDBHandler.USERNAME,username );
                contentValues.put(MyDBHandler.LASTPROGESSSTATE, lastPhraseState);
                contentValues.put(MyDBHandler.SOLVES, solves);
                contentValues.put(MyDBHandler.PROGRESS_SCRAMBLE_ID,ProgressID );
                int result=db.update(MyDBHandler.TABLE_NAME_PROGRESS_STATE, contentValues,MyDBHandler.USERNAME + " = ? and  "+MyDBHandler.PROGRESS_SCRAMBLE_ID+ " = ? ", new String[] { username,ProgressID} );


            return false;

        }else {
            SQLiteDatabase db = database;
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyDBHandler.USERNAME,username );
            contentValues.put(MyDBHandler.LASTPROGESSSTATE, lastPhraseState);
            contentValues.put(MyDBHandler.SOLVES, solves);
            contentValues.put(MyDBHandler.PROGRESS_SCRAMBLE_ID,ProgressID );
            db.insert(MyDBHandler.TABLE_NAME_PROGRESS_STATE, null, contentValues);
            return true;
        }


    }
    public Cursor getInProgress(String username,String ProgressID) {
        SQLiteDatabase db = this.database;
        Cursor res = db.rawQuery( "SELECT * FROM " + MyDBHandler.TABLE_NAME_PROGRESS_STATE + " WHERE " +
                MyDBHandler.USERNAME + "=? and "+MyDBHandler.PROGRESS_SCRAMBLE_ID+ "=?", new String[] { username,ProgressID } );
        return res;
    }
    public Cursor getAllInprogressStat(String Username) {
        SQLiteDatabase db = this.database;
        Cursor res = db.rawQuery( "SELECT * FROM " + MyDBHandler.TABLE_NAME_PROGRESS_STATE , null);
        Log.d("","Record Length "+res.getCount());
        return res;
    }


    public void insertPlayerStat(String username, String countSolves, String CountScrambleCreated,String CountOtherSolved, String avagTimesSolved) {

        Cursor CheckForExisting =getPlayerStat(username);
        if (CheckForExisting.moveToNext())
        {

            SQLiteDatabase db = database;
            ContentValues contentValues = new ContentValues();
            if (!countSolves.equals("0"))
            contentValues.put(MyDBHandler.COUNTSOLVES, countSolves);
            if (!CountScrambleCreated.equals("0"))
            contentValues.put(MyDBHandler.COUNTSCRAMBLECREATED, CountScrambleCreated);
            if (!CountOtherSolved.equals("0"))
            contentValues.put(MyDBHandler.COUNTOTHERSOLVED, CountOtherSolved);
            if (!avagTimesSolved.equals("0"))
            contentValues.put(MyDBHandler.AVGTIMESSOLVED, avagTimesSolved);
            db.update(MyDBHandler.TABLE_NAME_PLAYER_STAT, contentValues,MyDBHandler.USERNAME + " = ? ", new String[] { username} );

            Log.d("","malik");
        }else {
            SQLiteDatabase db = database;
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyDBHandler.USERNAME,username );
            contentValues.put(MyDBHandler.COUNTSOLVES, countSolves);
            contentValues.put(MyDBHandler.COUNTSCRAMBLECREATED, CountScrambleCreated);
            contentValues.put(MyDBHandler.COUNTOTHERSOLVED, CountOtherSolved);
            contentValues.put(MyDBHandler.AVGTIMESSOLVED, avagTimesSolved);
            db.insert(MyDBHandler.TABLE_NAME_PLAYER_STAT, null, contentValues);
            Log.d("","asif");
        }


    }
    public Cursor getPlayerStat(String username) {
        SQLiteDatabase db = this.database;
        Cursor res = db.rawQuery( "SELECT * FROM " + MyDBHandler.TABLE_NAME_PLAYER_STAT + " WHERE " +
                MyDBHandler.USERNAME + "=?", new String[] { username } );
        return res;
    }
    public Cursor PlayerCheck(String username) {
        SQLiteDatabase db = this.database;
        Cursor res = db.rawQuery( "SELECT * FROM " + MyDBHandler.TABLE_NAME_PLAYER_STAT + " WHERE " +
                MyDBHandler.USERNAME + "=?", new String[] { username } );
        return res;
    }



    public List<String> getAllData(String tableName) {
        Log.d("DBManager", "Getting all rows for table " + tableName);
        SQLiteDatabase db = this.database;
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        List<String> data = new ArrayList<>();

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            do {
                String row_values = "";

                for(int i = 0 ; i < cursor.getColumnCount(); i++){
                    row_values = row_values + " || " + cursor.getString(i);
                }

                Log.d("DBManager", "Adding row: " + row_values);
                data.add(row_values);

                cursor.moveToNext();
            } while (!cursor.moveToNext());
        }
        cursor.close();
        return  data;
    }

}