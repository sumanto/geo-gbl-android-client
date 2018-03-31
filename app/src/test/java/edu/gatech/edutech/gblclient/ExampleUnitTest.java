package edu.gatech.edutech.gblclient;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// import edu.gatech.edutech.utilities.ExternalWebService;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    // private static ExternalWebService externalService;
    private static List<List<String>> players;

    @BeforeClass
    public static void setup() {
//        externalService = ExternalWebService.getInstance();
//        players = externalService.retrievePlayerListService();
    }

    @Test
    public void validateCreatePlayerUsername() throws Exception {
//        String userName = externalService.newPlayerService("user1", "John", "Doe", "john@acme.com");
//        assertTrue(userName.contains("user1"));
    }
}