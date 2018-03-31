package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import com.gatech.yourapp.R;
import edu.gatech.edutech.gblclient.objects.PlayerStatistices;
import edu.gatech.edutech.gblclient.utils.DBManager;
import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

public class Login extends AppCompatActivity {
    String msg = "** Login: ";

    Service service = Service.getInstance();
    Utility utility;
    private static Map<String, List<String>> players = new HashMap<String, List<String>>();

    EditText userName;
    Button buttonLogin;
    Button buttonCreatePlayer;

    PlayerStatistices playerStatistices;

    //Database Initialize
    private DBManager dbManager;

    // Setup local list of users to use
    List<List<String>> users = service.retrievePlayerListService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(msg, "The onCreate() event");

        dbManager = new DBManager(this);
        dbManager.open();
        //Add users from database to ExternalWebService

        // List allExistingUsers =  dbManager.getAllData("PLAYERS");
        // Log.d(msg, "Existing players in db: " + allExistingUsers);

        utility = (Utility) getApplicationContext();
        utility.setDbManager(dbManager);

        for (List<String> user : users) {
            players.put(user.get(0), user);
            playerStatistices = new PlayerStatistices();
            utility.setPlayerStatistices(playerStatistices, user.get(0));
            Log.d(msg, user.get(0));
        }


        // User not logged in
        service.resetUser();

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCreatePlayer = (Button) findViewById(R.id.buttonCreatePlayer);
        userName = (EditText) findViewById(R.id.inputUserName);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(msg, "Login button clicked");

                String user = userName.getText().toString();
                if (user.length() == 0) {
                    userName.setError("You need non-empty username to login");
                    return;
                }

                List<String> player = players.get(userName.getText().toString());
                if (player == null) {
                    userName.setError("Invalid Username");
                    Log.d(msg, "Invalid username, " + user + ", valid usernames: " + players.keySet().toString());
                    return;
                }

                // Means the username is correct, continue to next page
                service.setUser(player.get(0), player.get(1), player.get(2), player.get(3));
                // Fill up cache
                service.fillCache();
                utility.setCURRENT_USER(player.get(0));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonCreatePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(msg, "Create Player button clicked");

                // Username is not validated
                Intent intent = new Intent(getApplicationContext(), CreatePlayer.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }
}
