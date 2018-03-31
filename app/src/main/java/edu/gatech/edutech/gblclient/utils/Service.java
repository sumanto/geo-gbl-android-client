package edu.gatech.edutech.gblclient.utils;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

// import edu.gatech.edutech.utilities.ExternalWebService;

public class Service {
    private static Service singleton = new Service();
    // private static ExternalWebService externalWebService;

//    private List<List<String>> players;
//    private List<List<String>> scrambles;
//
//    private Map<String, List<List<String>>> scrambleStatistics;

    private String userName;
    private String userFullName;


    /**
     * Use this to get a singleton instance of Service
     */
    public static synchronized Service getInstance() {
        return Service.singleton;
    }

    public String newPlayerService(String baseUsername, String firstName, String lastName, String email) throws SocketTimeoutException, IllegalArgumentException {
        // New solve means that existing list of players is stale
        clearCache();

        // return externalWebService.newPlayerService(baseUsername, firstName, lastName, email);
        return null;
    }

    public boolean reportSolveService(String scrambleid, String username) {
        // New solve means that existing list of solved scrambnles is stale, essentially we clear cache
        clearCache();

        // return externalWebService.reportSolveService(scrambleid, username);
        return false;
    }

//    public List<List<String>> retrievePlayerListService() {
////        if (players == null) {
////            players = externalWebService.retrievePlayerListService();
////        }
//        return players;
//    }
//
//    public List<List<String>> retrieveScrambleService() {
////        if (scrambles == null) {
////            scrambles = externalWebService.retrieveScrambleService();
////        }
//        return scrambles;
//    }
//
//    public String newScrambleService(String answer, String scrambled, String clue, String creator) throws SocketTimeoutException, IllegalArgumentException {
//        // New scramble means that existing list of scrambles is stale, essentially we clear cache
//        clearCache();
//
//        // return externalWebService.newScrambleService(answer, scrambled, clue, creator);
//        return null;
//    }
//

    public void clearCache() {
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

    public void setUser(String userName, String userFullName) {
        this.userName = userName;
        this.userFullName = userFullName;
    }

//    public void setScrambleStatistics(Map<String, List<List<String>>> scrambleStatistics) {
//        this.scrambleStatistics = scrambleStatistics;
//    }
//
//    public Map<String, List<List<String>>> getScrambleStatistics() {
//        return scrambleStatistics;
//    }
}
