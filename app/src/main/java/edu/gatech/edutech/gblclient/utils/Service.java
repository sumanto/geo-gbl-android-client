package edu.gatech.edutech.gblclient.utils;

import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

// import edu.gatech.edutech.utilities.ExternalWebService;

public class Service {
    private static Service singleton = new Service();

    private String userName;
    private String userFullName;
    private JSONObject cityMetadata;
    private JSONObject gameMetadata;


    /**
     * Use this to get a singleton instance of Service
     */
    public static synchronized Service getInstance() {
        return Service.singleton;
    }


    public void clearCache() {
        resetUser();
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

}
