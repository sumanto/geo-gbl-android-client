package edu.gatech.edutech.gblclient.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME_PLAYERS = "PLAYERS";
    public static final String TABLE_NAME_CREATE_SCRAMBLE = "CREATE_SCRAMBLES";
    public static final String TABLE_NAME_SOLVE_SCRAMBLE = "SOLVE_SCRAMBLES";

    public static final String TABLE_NAME_PLAYER_STAT = "PLAYER_STAT";
    public static final String TABLE_NAME_PROGRESS_STATE = "SCRAMBLES_PROGRESS_STATE";

    // Table columns
    public static final String _ID = "_id";
    public static final String USERNAME = "username";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String PROGRESSID = "progressid";
    public static final String PHRASE = "phrase";
    public static final String CLUE = "clue";
    public static final String SCRAMBLED = "scrambled";
    public static final String SCRAMBLE_ID = "scrambleId";
    public static final String PHRASE_TRIED = "phrase";

    public static final String COUNTSOLVES = "countSolves";
    public static final String COUNTSCRAMBLECREATED = "CountScrambleCreated";
    public static final String COUNTOTHERSOLVED = "CountOtherSolved";
    public static final String AVGTIMESSOLVED = "avagTimesSolved";

    public static final String LASTPROGESSSTATE = "lastPhraseState";
    public static final String SOLVES = "solves";
    public static final String PROGRESS_SCRAMBLE_ID = "progressScrambleID";








    // Database Information
    static final String DB_NAME = "SDPSCRAMBLE.DB";

    // database version
    static final int DB_VERSION = 1;

    // Tables
    private static final String CREATE_TABLE_PLAYERS = "create table " + TABLE_NAME_PLAYERS + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + FIRSTNAME + " TEXT, " + LASTNAME + " TEXT, " + EMAIL + " TEXT, " + PROGRESSID + " TEXT);";
    private static final String CREATE_TABLE_CREATE_SCRAMBLES = "create table " + TABLE_NAME_CREATE_SCRAMBLE + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + PHRASE + " TEXT, " + CLUE + " TEXT, " + SCRAMBLED + " TEXT);";
    private static final String CREATE_TABLE_SOLVE_SCRAMBLES = "create table " + TABLE_NAME_SOLVE_SCRAMBLE + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + SCRAMBLE_ID + " TEXT, " + PHRASE_TRIED + " TEXT);";


    private static final String CREATE_TABLE_PLAYER_STAT = "create table " + TABLE_NAME_PLAYER_STAT + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + COUNTSOLVES + " TEXT, " + COUNTSCRAMBLECREATED + " TEXT," + COUNTOTHERSOLVED + " TEXT," + AVGTIMESSOLVED + " TEXT);";

    private static final String CREATE_TABLE_PROGRESS_STATE = "create table " + TABLE_NAME_PROGRESS_STATE + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + LASTPROGESSSTATE + " TEXT, " + SOLVES + " TEXT, " + PROGRESS_SCRAMBLE_ID + " TEXT);";


    public MyDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyDBHandlder:", "Creating tables");
        db.execSQL(CREATE_TABLE_PLAYERS);
        db.execSQL(CREATE_TABLE_CREATE_SCRAMBLES);
        db.execSQL(CREATE_TABLE_SOLVE_SCRAMBLES);
        db.execSQL(CREATE_TABLE_PLAYER_STAT);
        db.execSQL(CREATE_TABLE_PROGRESS_STATE);
        Log.d("MyDBHandlder:", "Creating done");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyDBHandlder:", "Upgrading tables");
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PLAYERS);
//        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CREATE_SCRAMBLES);
//        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SOLVE_SCRAMBLES);
//        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PLAYER_STAT);
//        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PROGRESS_STATE);
        onCreate(db);
        Log.d("MyDBHandlder:", "Upgrading tables");
    }



}

