package edu.gatech.edutech.gblclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.util.List;

import edu.gatech.edutech.gblclient.objects.CityObject;
import edu.gatech.edutech.gblclient.objects.PlaceAttributes;
import edu.gatech.edutech.gblclient.objects.ThiefAttributes;
import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;


public class Main extends AppCompatActivity {
    String msg = "** Main: ";

    Service service = Service.getInstance();
    TextView textInformation, textHistory, textPersonAction, textPlaceAction, textTravelAction, textThiefAttributesAction, textWarrantAction, textBack;
    Main thisObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");

        thisObject = this;

        textInformation = findViewById(R.id.textInformation);
        textHistory = findViewById(R.id.textHistory);

        textPersonAction = findViewById(R.id.textPersonAction);
        textPlaceAction = findViewById(R.id.textPlaceAction);
        textTravelAction = findViewById(R.id.textTravelAction);
        textThiefAttributesAction = findViewById(R.id.textThiefAttributesAction);
        textWarrantAction = findViewById(R.id.textWarrantAction);
        textBack = findViewById(R.id.textBack);


        // Set up scrolling
        textHistory.setMovementMethod(new ScrollingMovementMethod());


        textInformation.setText(getInfoBar(service));

        // Differentiate between coming back and initial page
        if (service.isNewGame()) {
            textHistory.setText("");

            // Welcome user
            textHistory.append("Welcome " + service.getUserFullName() + " (username: " + service.getUserName()+ ") !!\n");
            textHistory.append(delimiters());
            textHistory.append("\n");

            setUpNewCity();

            // What the thief stole
            textHistory.append("The thief has stolen " + service.getStolenArtifact() + " !!\n");
            textHistory.append(delimiters());
            textHistory.append("\n");

            service.setNewGame(false);
        } else {
            textHistory.setText(service.getTextHistory());
            textHistory.requestFocus();

            textPersonAction.setText("> " + service.getPersonAction());
            textPlaceAction.setText("> " + service.getPlaceAction());
        }


        textPersonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);

                String persona = Character.toUpperCase(service.getPerson().charAt(0)) + service.getPerson().substring(1);
                builder.setTitle(persona + ":");

                String personaText = service.getPersonText();
                builder.setMessage(personaText);

                textHistory.append(persona  + " said: " + personaText + "\n" + delimiters() + "\n");

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
                builder.setTitle("***** Evidence report");

                PlaceAttributes placeAttributes = service.getPlaceAttributes();
                StringBuilder str = new StringBuilder();
                str.append("> Date: ").append(placeAttributes.getDay()).append("\n");
                str.append("> Time: ").append(placeAttributes.getTime()).append("\n");
                str.append("> Location: ").append(placeAttributes.getCity()).append("\n");
                str.append("> Item: ").append(placeAttributes.getItem()).append("\n");
                str.append("> Description: ").append(placeAttributes.getDescription());

                builder.setMessage(str.toString());

                textHistory.append(">>> Evidence report: <<<\n");
                textHistory.append(str.toString() + "\n" + delimiters() + "\n");

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
                CharSequence flights[] = flightList.toArray(new CharSequence[flightList.size()]);

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
                        Utility.setupNewCityActions(service, cityChoice, service.getRightChoice() == (which + 1));
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


        // Thief attributes action
        textThiefAttributesAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.setTextHistory(textHistory.getText().toString());

                Intent intent = new Intent(getApplicationContext(), ThiefAttributesActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Thief attributes action
        textWarrantAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThiefAttributes guesses = service.getGuessedAttributes();
                ThiefAttributes thiefAttributes = service.getThiefAttributes();

                String alertMessage;
                if (guesses.getSex() == null || guesses.getEyes() == null || guesses.getHobby() == null ||
                        guesses.getFeature() == null || guesses.getHair() == null ||
                        guesses.getFood() == null || guesses.getVehicle() == null) {
                    alertMessage = "You have to set all thief attributes before you can issue a warrant";
                } else {
                    if (thiefAttributes.getSex().equals(guesses.getSex()) &&
                            thiefAttributes.getEyes().equals(guesses.getEyes()) &&
                            thiefAttributes.getHobby().equals(guesses.getHobby()) &&
                            thiefAttributes.getFeature().equals(guesses.getFeature()) &&
                            thiefAttributes.getHair().equals(guesses.getHair()) &&
                            thiefAttributes.getFood().equals(guesses.getFood()) &&
                            thiefAttributes.getVehicle().equals(guesses.getVehicle())) {
                        alertMessage = "Congrats you caught the thief :)";
                    } else {
                        // TODO: make it 3 chances
                        alertMessage = "Your warrant was incorrect. You have 2 more chances. Use them wisely.";
                    }
                }

                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);
                builder.setTitle("*** Warrant ***");
                builder.setMessage(alertMessage);

                // add a button
                builder.setPositiveButton("OK", null);

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        // Back button action
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.setTextHistory(textHistory.getText().toString());

                Intent intent = new Intent(getApplicationContext(), NewGame.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void setUpNewCity() {
        textInformation.setText(getInfoBar(service));
        textPersonAction.setText("> " + service.getPersonAction());
        textPlaceAction.setText("> " + service.getPlaceAction());


        // Welcome to city
        textHistory.append(delimiters());
        textHistory.append("Welcome to " + service.getPresentCityName() + ", also known as " + service.getPresentCityNickname() + " !!\n");
        textHistory.append(" - fun fact: " + service.getPresentCityDescription() + "\n");
        textHistory.append(delimiters());
        textHistory.append("\n");
    }


    public String getInfoBar(Service service) {
        return "Game: " + service.getGameName() + "; City: " + service.getPresentCityName();
    }


    public String delimiters() {
        return "**************\n";
    }
}
