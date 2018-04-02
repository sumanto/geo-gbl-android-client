package edu.gatech.edutech.gblclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
// import android.support.v7.appcompat.*;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

public class Main extends AppCompatActivity {

    Service service = Service.getInstance();
    TextView textHistory, textPersonAction, textPlaceAction, textTravelAction, textThiefAttributesAction, textWarrantAction, textLogout;
    Main thisObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisObject = this;

        textHistory = findViewById(R.id.textHistory);

        textPersonAction = findViewById(R.id.textPersonAction);
        textPlaceAction = findViewById(R.id.textPlaceAction);
        textTravelAction = findViewById(R.id.textTravelAction);
        textThiefAttributesAction = findViewById(R.id.textThiefAttributesAction);
        textWarrantAction = findViewById(R.id.textWarrantAction);
        textLogout = findViewById(R.id.textLogout);


        // Set up scrolling
        textHistory.setMovementMethod(new ScrollingMovementMethod());

        textPersonAction.setText("> " + service.getPersonAction());
        textPlaceAction.setText("> " + service.getPlaceAction());


        // TODO: differentiate between coming back and initial page
        // Welcome user
        textHistory.setText("");
        textHistory.append("Welcome " + service.getUserFullName() + " !!\n");
        textHistory.append("Welcome to " + service.getPresentCityName() + " !!\n");
        textHistory.append("The thief has stolen " + service.getStolenArtifact() + " from the city !!\n");


        textPersonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);
                builder.setTitle(Character.toUpperCase(service.getPerson().charAt(0)) + service.getPerson().substring(1) + ":");
                builder.setMessage(service.getPersonText());

                // add a button
                builder.setPositiveButton("OK", null);

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        textPlaceAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);
                builder.setTitle(service.getPlace());
                builder.setMessage(service.getPlaceAttributes().getItem() + ": " + service.getPlaceAttributes().getDescription());

                // add a button
                builder.setPositiveButton("OK", null);

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        textTravelAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                let choices = [];
                let rightChoice = getRandomInt(1, 4);
                d('Random int: ' + rightChoice);

                let alreadyAdded = [];
                let choiceFlights = {};
                choiceFlights[nextCorrectCity] = Math.floor(Math.random() * 1000) + 1000;

                alreadyAdded.push(nextCorrectCity);

                let i = 1;
                while (choices.length < 3) {
                    if (rightChoice === i) {
                        choices.push({
                                name: nextCorrectCity + '; flight #: ' + choiceFlights[nextCorrectCity],
                                value: nextCorrectCity
                    });
                        i++;
                        continue;
                    }

                    let choice = getRandomData(Object.keys(cityMetadata.cities));
                    let cityToAdd = cityMetadata.cities[choice].name;
                    if (alreadyAdded.indexOf(cityToAdd) >= 0) {
                        continue;
                    }

                    alreadyAdded.push(cityToAdd);
                    choiceFlights[cityToAdd] = Math.floor(Math.random() * 1000) + 1000;
                    choices.push({
                            name: cityToAdd + '; flight #: ' + choiceFlights[cityToAdd],
                            value: cityToAdd
                });

                    i++;
                }
                d(choices);
                */

                CharSequence colors[] = new CharSequence[] {"red", "green", "blue", "black"};

                AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);
                builder.setTitle("Where do you want to travel to?");
                builder.setItems(colors, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String flightNumber = "";
                        String cityChoice = "";

                        // setup the alert builder
                        AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);
                        builder.setTitle("Travelling...");
                        String flightText = "Your reservation has been confirmed for flight number " + flightNumber + " to " + cityChoice + ".";
                        try {
                            flightText += " " + Utility.getRandomData(Utility.convertJSONArrayToList(service.getGameMetadata().getJSONObject("flight").getJSONArray("line2")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        builder.setMessage(flightText);

                        // add a button
                        builder.setPositiveButton("OK", null);

                        // create and show the alert dialog
                        AlertDialog newDialog = builder.create();
                        newDialog.show();
                    }
                });

                builder.show();
            }
        });


        // Logout action
        textLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

//        createscramble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),CreateScramble.class);
//                startActivity(intent);
//            }
//        });
//        playscramble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),ScrambleList.class);
//                startActivity(intent);
//            }
//        });
//        scramblestat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),ScrambleStatistics.class);
//                startActivity(intent);
//            }
//        });
//        playerstat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),PlayerStatistics.class);
//                startActivity(intent);
//            }
//        });

    }
}
