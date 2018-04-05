package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gatech.edutech.gblclient.objects.ThiefAttributes;
import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;


public class Login extends AppCompatActivity {
    String msg = "** Login: ";

    Service service = Service.getInstance();
    Utility utility;

    EditText inputTeacherUserName, inputTeacherName, inputStudentUserName, inputStudentName;
    Button buttonTeacherLogin, buttonStudentLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(msg, "The onCreate() event");

        service.clearCache();
        utility = (Utility) getApplicationContext();

        buttonTeacherLogin = findViewById(R.id.buttonTeacherLogin);
        inputTeacherUserName = findViewById(R.id.inputTeacherUserName);
        inputTeacherName = findViewById(R.id.inputTeacherName);

        buttonStudentLogin = findViewById(R.id.buttonStudentLogin);
        inputStudentUserName = findViewById(R.id.inputStudentUserName);
        inputStudentName = findViewById(R.id.inputStudentName);

        buttonTeacherLogin.setHint("Teacher login is not implemented");
        buttonTeacherLogin.setEnabled(false);
        inputTeacherUserName.setEnabled(false);
        inputTeacherName.setEnabled(false);

        buttonStudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(msg, "Login button clicked");

                String userName = inputStudentUserName.getText().toString();
                if (userName.length() == 0) {
                    inputStudentUserName.setError("You need non-empty username to login");
                    return;
                }

                String user = inputStudentName.getText().toString();
                if (user.length() == 0) {
                    inputStudentName.setError("You need non-empty name to login");
                    return;
                }

                // Fill up cache
                service.setDateTime(new Date());
                service.setUser(userName, user);

                // Set/reset thief attributes
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

                service.setNewLogin(true);


                // Go to main page now
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
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
