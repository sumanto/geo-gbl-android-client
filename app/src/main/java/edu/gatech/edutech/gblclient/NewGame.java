package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.edutech.gblclient.objects.GameStatistics;
import edu.gatech.edutech.gblclient.objects.ThiefAttributes;
import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;


public class NewGame extends AppCompatActivity {
    String msg = "** NewGame: ";

    Service service = Service.getInstance();
    Button buttonNewGame, buttonContinueGame, buttonGameStatistics, buttonLogout;
    NewGame thisObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        Log.d(msg, "The onCreate() event");

        thisObject = this;

        buttonNewGame = findViewById(R.id.buttonNewGame);
        buttonContinueGame = findViewById(R.id.buttonContinueGame);
        buttonGameStatistics = findViewById(R.id.buttonGameStatistics);
        buttonLogout = findViewById(R.id.buttonLogout);


        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject gameMetadata = service.getGameMetadata();
                JSONObject cityMetadata = service.getCityMetadata();

                ThiefAttributes thiefAttributes = service.getThiefAttributes();

                try {
                    List<String> sexList = new ArrayList<>();
                    sexList.add("male");
                    sexList.add("female");
                    service.setSex(sexList);
                    thiefAttributes.setSex(Utility.getRandomData(sexList));

                    if (thiefAttributes.getSex().equals("male")) {
                        service.setThiefSalutation("He");
                    } else {
                        service.setThiefSalutation("She");
                    }

                    List<String> eyesList = Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("eyes").names());
                    service.setEyes(eyesList);
                    thiefAttributes.setEyes(Utility.getRandomData(eyesList));

                    List<String> hairList = Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("hair").names());
                    service.setHair(hairList);
                    thiefAttributes.setHair(Utility.getRandomData(hairList));

                    List<String> hobbyList = Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("hobby").names());
                    service.setHobby(hobbyList);
                    thiefAttributes.setHobby(Utility.getRandomData(hobbyList));

                    List<String> foodList = Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("food").names());
                    service.setFood(foodList);
                    thiefAttributes.setFood(Utility.getRandomData(foodList));

                    List<String> featureList = Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("feature").names());
                    service.setFeature(featureList);
                    thiefAttributes.setFeature(Utility.getRandomData(featureList));

                    List<String> vehicleList = Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("vehicle").names());
                    service.setVehicle(vehicleList);
                    thiefAttributes.setVehicle(Utility.getRandomData(vehicleList));

                    Log.d(msg, "Thief attributes: " + thiefAttributes.toString());


                    // Choose a initial city, as well as next city
                    String firstCity = Utility.getRandomData(Utility.convertJSONArrayToList(cityMetadata.getJSONObject("cities").names()));
                    Utility.setupNewCityActions(service, firstCity, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                // Setup stolen information
                try {
                    service.setStolenCityName(service.getPresentCity().getString("name"));
                    service.setStolenArtifact(Utility.getRandomData(Utility.convertJSONArrayToList(service.getPresentCity().getJSONArray("steal"))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                service.setNewGame(true);
                service.setGameInProgress(true);

                // Reset game statistics
                GameStatistics gameStatistics = service.getGameStatistics();
                gameStatistics.setCitiesVisited(1);
                gameStatistics.setCorrectCitiesVisited(1);
                gameStatistics.setPersonsTalkedTo(0);
                gameStatistics.setPlacesVisited(0);
                gameStatistics.setWarrantsIssued(0);

                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                finish();
            }
        });


        buttonContinueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!service.isGameInProgress()) {
                    // setup the alert builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);
                    builder.setTitle("No game in progress");
                    builder.setMessage("Create new game...");

                    // add a button
                    builder.setPositiveButton("OK", null);

                    // create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return;
                }

                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                finish();
            }
        });


        buttonGameStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);

                builder.setTitle("*** Game statistics ***");
                GameStatistics statistics = service.getGameStatistics();
                builder.setMessage("Cities visited: " + statistics.getCitiesVisited()
                        + "\nCorrect cities visited: " + statistics.getCorrectCitiesVisited()
                        + "\nPersons talked to: " + statistics.getPersonsTalkedTo()
                        + "\nPlaces visited: " + statistics.getPlacesVisited()
                        + "\nWarrants issues: " + statistics.getWarrantsIssued());

                builder.setPositiveButton("OK", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        // Logout action
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
