package edu.gatech.edutech.gblclient.objects;


public class InProgress {

    private static InProgress singleton = new InProgress();

    public static synchronized InProgress getInstance() {
        return InProgress.singleton;
    }


    public void resetProgress() {
        this.inProgressID = null;
        this.lastPhraseState = null;
        this.solves = false;
    }



    String inProgressID;String lastPhraseState;Boolean solves;

    public String getInProgressID() {
        return inProgressID;
    }

    public void setInProgressID(String inProgressID) {
        this.inProgressID = inProgressID;
    }

    public String getLastPhraseState() {
        return lastPhraseState;
    }

    public void setLastPhraseState(String lastPhraseState) {
        this.lastPhraseState = lastPhraseState;
    }

    public Boolean getSolves() {
        return solves;
    }

    public void setSolves(Boolean solves) {
        this.solves = solves;
    }

    public void Pause()
    {

    }
    public void Resume()
    {

    }
    public void SolveScramble()
    {

    }

}
