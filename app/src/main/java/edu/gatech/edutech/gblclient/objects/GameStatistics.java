package edu.gatech.edutech.gblclient.objects;

public class GameStatistics {
    public int getCitiesVisited() {
        return citiesVisited;
    }

    public void setCitiesVisited(int citiesVisited) {
        this.citiesVisited = citiesVisited;
    }

    public int getCorrectCitiesVisited() {
        return correctCitiesVisited;
    }

    public void setCorrectCitiesVisited(int correctCitiesVisited) {
        this.correctCitiesVisited = correctCitiesVisited;
    }

    public int getPersonsTalkedTo() {
        return personsTalkedTo;
    }

    public void setPersonsTalkedTo(int personsTalkedTo) {
        this.personsTalkedTo = personsTalkedTo;
    }

    public int getPlacesVisited() {
        return placesVisited;
    }

    public void setPlacesVisited(int placesVisited) {
        this.placesVisited = placesVisited;
    }

    public int getWarrantsIssued() {
        return warrantsIssued;
    }

    public void setWarrantsIssued(int warrantsIssued) {
        this.warrantsIssued = warrantsIssued;
    }

    private int citiesVisited = 0;
    private int correctCitiesVisited = 0;
    private int personsTalkedTo = 0;
    private int placesVisited = 0;
    private int warrantsIssued = 0;
}
