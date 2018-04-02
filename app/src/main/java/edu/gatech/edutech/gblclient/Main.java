package edu.gatech.edutech.gblclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
// import android.support.v7.appcompat.*;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.util.List;

import edu.gatech.edutech.gblclient.objects.CityObject;
import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

public class Main extends AppCompatActivity {
    String msg = "** Main: ";

    Service service = Service.getInstance();
    TextView textHistory, textPersonAction, textPlaceAction, textTravelAction, textThiefAttributesAction, textWarrantAction, textLogout;
    Main thisObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");

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


        // TODO: differentiate between coming back and initial page
        textHistory.setText("");

        // Welcome user
        textHistory.append("Welcome " + service.getUserFullName() + " !!\n");
        textHistory.append(delimiters());
        textHistory.append("\n");

        setUpNewCity();

        // What the thief stole
        textHistory.append("The thief has stolen " + service.getStolenArtifact() + " !!\n");
        textHistory.append(delimiters());
        textHistory.append("\n");



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

                List<String> flightList = service.getNextCities();
                CharSequence flights[] = flightList.toArray(new CharSequence[flightList.size()]); // new CharSequence[] {"red", "green", "blue", "black"};

                AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);
                builder.setTitle("Where do you want to travel to?");
                builder.setItems(flights, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(msg, "clicked: " + which);
                        CityObject nextCityObject = service.getNextCitiesObjects().get(which);
                        String flightNumber = nextCityObject.getFlightNumber();
                        String cityChoice = nextCityObject.getCityName();

                        // Setup data
                        Utility.setupNewCityActions(service, cityChoice, service.getRightChoice() == which);
                        setUpNewCity();

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


    public void setUpNewCity() {
        textPersonAction.setText("> " + service.getPersonAction());
        textPlaceAction.setText("> " + service.getPlaceAction());


        // Welcome user
        textHistory.append(delimiters());
        textHistory.append("Welcome to " + service.getPresentCityName() + " !!\n");
        textHistory.append(delimiters());
        textHistory.append("\n");
    }


    public String delimiters() {
        return "**************\n";
    }
}
