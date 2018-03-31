package edu.gatech.edutech.gblclient;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by LizKellerman on 10/9/17.
 */

public class PlayerCreateTests {
    // private static ExternalWebService externalService;
    private static List<List<String>> players;

    @BeforeClass
    public static void setup() {
//        externalService = ExternalWebService.getInstance();
//        players = externalService.retrievePlayerListService();
    }

    @Test
    public void validateCreatePlayerUsername() throws Exception {
        // String userName = externalService.newPlayerService("lizk", "Liz", "Kellerman", "Liz@email.com");
        assertTrue(userName.contains("lizk"));
    }
}
