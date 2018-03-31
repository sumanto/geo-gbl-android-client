package edu.gatech.edutech.gblclient.utils;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.edutech.gblclient.objects.InProgress;
import edu.gatech.edutech.gblclient.objects.PlayerStatistices;
import edu.gatech.edutech.gblclient.persistence.MyDBHandler;


public class Utility extends Application {
    Map<String, InProgress> progress = new HashMap<>();
    Map<String, PlayerStatistices> Player = new HashMap<>();
    public String CURRENT_USER;

    DBManager dbManager;

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }


    public String getCURRENT_USER() {
        return CURRENT_USER;
    }

    public void setCURRENT_USER(String CURRENT_USER) {
        this.CURRENT_USER = CURRENT_USER;
    }

    public PlayerStatistices GetplayerStatistices(String username) {
        PlayerStatistices playerStatistices = new PlayerStatistices();
        Cursor cursor = dbManager.getPlayerStat(username);
        if (cursor.moveToNext()) {
            playerStatistices.setUsername(username);
            playerStatistices.setAvagTimesSolved(Double.parseDouble(cursor.getString(cursor.getColumnIndex(MyDBHandler.COUNTSOLVES))));
            playerStatistices.setCountOtherSolved(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyDBHandler.COUNTOTHERSOLVED))));
            playerStatistices.setCountScrambleCreated(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyDBHandler.COUNTSCRAMBLECREATED))));
            playerStatistices.setCountSolves(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyDBHandler.COUNTSOLVES))));
        }
        return playerStatistices;
    }

    public void setPlayerStatistices(PlayerStatistices playerStatistices, String key) {
        dbManager.insertPlayerStat(key, String.valueOf(playerStatistices.getCountSolves()), String.valueOf(playerStatistices.getCountScrambleCreated()), String.valueOf(playerStatistices.getCountOtherSolved()), String.valueOf(playerStatistices.getAvagTimesSolved()));
        Log.d("bac", "player " + key);
    }

    public void addprogress(InProgress inProgress, String key) {
        dbManager.insertInProgress(key, inProgress.getLastPhraseState(), String.valueOf(inProgress.getSolves()), inProgress.getInProgressID());
    }

    public Map<String, InProgress> retriveInPrgress(String username) {

        InProgress inProgress = new InProgress();
        Cursor cursor = dbManager.getAllInprogressStat(username);
        while (cursor.moveToNext()) {
            inProgress.setSolves(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(MyDBHandler.SOLVES))));
            inProgress.setInProgressID(cursor.getString(cursor.getColumnIndex(MyDBHandler.LASTPROGESSSTATE)));
            inProgress.setInProgressID(cursor.getString(cursor.getColumnIndex(MyDBHandler.PROGRESS_SCRAMBLE_ID)));
            this.progress.put(cursor.getString(cursor.getColumnIndex(MyDBHandler.USERNAME)), inProgress);
        }

        return this.progress;
    }

    public void updateProgres(InProgress update, String key) {
        dbManager.insertInProgress(key, update.getLastPhraseState(), String.valueOf(update.getSolves()), update.getInProgressID());
    }

    public InProgress getProgressByIndex(String key, String ProgressID) {
        InProgress inProgress = new InProgress();
        Cursor cursor = dbManager.getInProgress(key, ProgressID);
        if (cursor.moveToNext()) {
            inProgress.setSolves(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(MyDBHandler.SOLVES))));
            inProgress.setLastPhraseState(cursor.getString(cursor.getColumnIndex(MyDBHandler.LASTPROGESSSTATE)));
            inProgress.setInProgressID(cursor.getString(cursor.getColumnIndex(MyDBHandler.PROGRESS_SCRAMBLE_ID)));
        }
        return inProgress;
    }
}
