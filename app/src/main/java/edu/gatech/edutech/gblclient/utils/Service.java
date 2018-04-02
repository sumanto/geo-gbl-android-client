package edu.gatech.edutech.gblclient.utils;

import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.gatech.edutech.gblclient.objects.PlaceAttributes;
import edu.gatech.edutech.gblclient.objects.ThiefAttributes;

// import edu.gatech.edutech.utilities.ExternalWebService;

public class Service {
    private static Service singleton = new Service();

    /**
     * Use this to get a singleton instance of Service
     */
    public static synchronized Service getInstance() {
        return Service.singleton;
    }


    public void clearCache() {
        resetUser();
        thiefAttributes.resetAttributes();
        guessedAttributes.resetAttributes();
    }


    public void fillCache() {
        clearCache();
    }


    public String getCurrentUsername() {
        return userName;
    }

    public void resetUser() {
        this.userName = null;
        this.userFullName = null;
    }


    public void setGameMetadata(JSONObject gameMetadata) {
        this.gameMetadata = gameMetadata;
    }


    public void setCityMetadata(JSONObject cityMetadata) {
        this.cityMetadata = cityMetadata;
    }


    public void setUser(String userName, String userFullName) {
        clearCache();

        this.userName = userName;
        this.userFullName = userFullName;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public JSONObject getCityMetadata() {
        return cityMetadata;
    }

    public JSONObject getGameMetadata() {
        return gameMetadata;
    }


    public ThiefAttributes getThiefAttributes() {
        return thiefAttributes;
    }

    public ThiefAttributes getGuessedAttributes() {
        return guessedAttributes;
    }

    public JSONObject getPresentCity() {
        return presentCity;
    }

    public void setPresentCity(JSONObject presentCity) {
        this.presentCity = presentCity;
    }

    public JSONObject getNextCity() {
        return nextCity;
    }

    public void setNextCity(JSONObject nextCity) {
        this.nextCity = nextCity;
    }

    public String getPresentCityName() {
        return presentCityName;
    }

    public void setPresentCityName(String presentCityName) {
        this.presentCityName = presentCityName;
    }

    public String getNextCityName() {
        return nextCityName;
    }

    public void setNextCityName(String nextCityName) {
        this.nextCityName = nextCityName;
    }

    public String getStolenCityName() {
        return stolenCityName;
    }

    public void setStolenCityName(String stolenCityName) {
        this.stolenCityName = stolenCityName;
    }

    public String getStolenArtifact() {
        return stolenArtifact;
    }

    public void setStolenArtifact(String stolenArtifact) {
        this.stolenArtifact = stolenArtifact;
    }


    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPersonAction() {
        return personAction;
    }

    public void setPersonAction(String personAction) {
        this.personAction = personAction;
    }


    public String getPersonText() {
        return personText;
    }

    public void setPersonText(String personText) {
        this.personText = personText;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlaceAction() {
        return placeAction;
    }

    public void setPlaceAction(String placeAction) {
        this.placeAction = placeAction;
    }


    public String getThiefSalutation() {
        return thiefSalutation;
    }

    public void setThiefSalutation(String thiefSalutation) {
        this.thiefSalutation = thiefSalutation;
    }

    public PlaceAttributes getPlaceAttributes() {
        return placeAttributes;
    }

    public void setPlaceAttributes(PlaceAttributes placeAttributes) {
        this.placeAttributes = placeAttributes;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    private Date dateTime;

    private String userName;
    private String userFullName;

    private JSONObject cityMetadata;
    private JSONObject gameMetadata;

    private String person;
    private String personAction;
    private String personText;

    private String place;
    private String placeAction;
    private PlaceAttributes placeAttributes;

    private JSONObject presentCity;
    private String presentCityName;

    private JSONObject nextCity;
    private String nextCityName;

    private String stolenCityName;
    private String stolenArtifact;

    private ThiefAttributes thiefAttributes = new ThiefAttributes();
    private ThiefAttributes guessedAttributes = new ThiefAttributes();
    private String thiefSalutation;
}
