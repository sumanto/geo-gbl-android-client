package edu.gatech.edutech.gblclient.objects;


public class PlayerStatistices {



    private static PlayerStatistices singleton = new PlayerStatistices();

    public static synchronized PlayerStatistices getInstance() {
        return PlayerStatistices.singleton;
    }



    String username;int countSolves=0;int CountScrambleCreated=0;int CountOtherSolved=0; Double avagTimesSolved=0.0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCountSolves() {
        return countSolves;
    }

    public void setCountSolves(int countSolves) {
        this.countSolves = countSolves;
    }

    public int getCountOtherSolved() {
        return CountOtherSolved;
    }

    public void setCountOtherSolved(int countOtherSolved) {
        CountOtherSolved = countOtherSolved;
    }

    public int getCountScrambleCreated() {
        return CountScrambleCreated;
    }

    public void setCountScrambleCreated(int countScrambleCreated) {
        CountScrambleCreated = countScrambleCreated;
    }

    public Double getAvagTimesSolved() {
        return avagTimesSolved;
    }

    public void setAvagTimesSolved(Double avagTimesSolved) {
        this.avagTimesSolved = avagTimesSolved;
    }

    public  Double calcAvg()
    {
        return Double.parseDouble(String.valueOf(this.CountOtherSolved/this.CountScrambleCreated));
    }

    public void displaySortedlist()


    {

    }public void IncrementCreated()
    {
        this.CountScrambleCreated+=1;
    }
    public void IncrementSolved()
    {
        this.countSolves+=1;
    }
    public void IncrementSolvedByOtehr()
    {
        this.CountOtherSolved+=1;
    }
}
