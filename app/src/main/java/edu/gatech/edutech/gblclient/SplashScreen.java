package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;


public class SplashScreen extends AppCompatActivity {
    String msg = "** SplashScreen: ";

    Service service = Service.getInstance();
    Utility utility;

    Button buttonContinue;
    TextView textTitle, textSubtitle, textWelcome;
    Spinner spinnerDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(msg, "The onCreate() event");

        final Service service = Service.getInstance();

        buttonContinue = findViewById(R.id.buttonContinue);
        textTitle = findViewById(R.id.textTitle);
        textSubtitle = findViewById(R.id.textSubtitle);
        textWelcome = findViewById(R.id.textWelcome);
        spinnerDomain = findViewById(R.id.spinnerDomain);

        AssetManager am = getApplicationContext().getAssets();

        JSONObject gameMetadata = Utility.getJSONObjectFromAssetFile(am, "game_metadata.json");
        service.setGameMetadata(gameMetadata);

        try {
            textTitle.setText(gameMetadata.getString("title"));
            textSubtitle.setText(gameMetadata.getString("description"));
            textWelcome.setText(gameMetadata.getJSONObject("main").getString("welcome"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add("School district Alpha");
        spinnerArray.add("School district Beta");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDomain.setAdapter(adapter);


        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(msg, "Continue button clicked");

                service.setSchoolDistrict((String) spinnerDomain.getSelectedItem());

                // Set intent to Login page
                Intent intent = new Intent(getApplicationContext(), Login.class);
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
