package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.edutech.gblclient.objects.PlayerStatistices;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.edutech.gblclient.objects.InProgress;
import edu.gatech.edutech.gblclient.utils.DBManager;
import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

public class Unscramble extends AppCompatActivity {


    Button exit, submit;
    TextView result,unscramble,clue;;
    EditText solution;
    Bundle bundle;
    ArrayList<String> scrambledetails;
    List<List<String>> scrambles = new ArrayList<List<String>>();

    InProgress inProgress;
    Utility utility;
    Service service = Service.getInstance();
    //Database Initialize
    private DBManager dbManager;
    PlayerStatistices playerStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unscramble);

        try {
            solution.append(dbManager.getProgressID(utility.getCURRENT_USER().toString()));
        } catch (NullPointerException e) {

        }

        bundle = getIntent().getExtras();
        //scrambledetails = bundle.getStringArrayList("scrambledetails");
        String getclue = bundle.getString("clue");
        String getid = bundle.getString("id");
        String getscramblephrase = bundle.getString("scramblephrase");
        final String getsolutionphrase = bundle.getString("solutionphrase");

        utility = (Utility) getApplicationContext();
        scrambles = service.retrieveScrambleService();
        inProgress = utility.getProgressByIndex(utility.getCURRENT_USER(), getid);
        dbManager = new DBManager(this);
        dbManager.open();
        inProgress.setLastPhraseState("Continue");
        utility.updateProgres(inProgress, utility.getCURRENT_USER());

        clue = (TextView)findViewById(R.id.clue);
        clue.append( " " + getclue);


        playerStatistics = utility.GetplayerStatistices(utility.getCURRENT_USER());

        unscramble=(TextView)findViewById(R.id.unscramble);
        solution = (EditText) findViewById(R.id.solution);
        unscramble.setText(getscramblephrase);

        result = (TextView) findViewById(R.id.result);
        exit = (Button) findViewById(R.id.exit);
        submit = (Button) findViewById(R.id.submit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.updateProgressID(utility.getCURRENT_USER(), inProgress.getInProgressID());
                Unscramble.super.onBackPressed();
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userinput = "";
                Boolean check = false;
                for (int i = 0; i < scrambles.size(); i++) {
                    if (solution.getText().toString().trim().length() == 0) {
                        solution.setError("You must enter a solution");
                    }
                    //get the scramble from ews and eval if the players guess matches solution stored
                    userinput = solution.getText().toString();
                    if (getsolutionphrase.equals(userinput)) {
                        result.setText("Result: True");
                        check = true;

                    }
                }
                if (check) {
                    //mark scramble solved for this player
                    inProgress.setSolves(true);
                    inProgress.setLastPhraseState("Solved");

                    Toast.makeText(getApplicationContext(), "Scramble Solved", Toast.LENGTH_SHORT).show();

                    Log.d("Scramble", inProgress.getInProgressID() + bundle.getString("key"));

                    utility.updateProgres(inProgress, utility.getCURRENT_USER());
                    // increment this players solved in the player statistics
                    playerStatistics.IncrementSolved();
                    utility.setPlayerStatistices(playerStatistics, utility.getCURRENT_USER());


                    List<List<String>> users = service.retrieveScrambleService();
                    for (List<String> user : users) {

                        if (user.get(4).equals(bundle.getString("Creator"))) {
                            PlayerStatistices playerStatistices = utility.GetplayerStatistices(user.get(4));
                            playerStatistices.IncrementSolvedByOtehr();
                            utility.setPlayerStatistices(playerStatistices, bundle.getString("Creator"));
                        }


                    }

                    Intent intent = new Intent(getApplicationContext(), ScrambleList.class);
                    startActivity(intent);
                    finish();
                } else {
                    // if guess does not match solution show False and show the player a clue
                    if (solution.getText().toString().trim().length() > 0) {
                        dbManager.updateProgressID(utility.getCURRENT_USER().toString(), inProgress.getInProgressID().toString());
                        result.setText("Result: False");
                        inProgress.setSolves(false);
                    }
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
