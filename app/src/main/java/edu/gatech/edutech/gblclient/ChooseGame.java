package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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


public class ChooseGame extends AppCompatActivity {
    String msg = "** ChooseGame: ";

    Service service = Service.getInstance();
    Utility utility;

    Button buttonContinueChooseGame;
    TextView textGameDescription;
    Spinner spinnerChooseGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        Log.d(msg, "The onCreate() event");

        final Service service = Service.getInstance();
        final AssetManager am = getApplicationContext().getAssets();

        buttonContinueChooseGame = findViewById(R.id.buttonContinueChooseGame);
        textGameDescription = findViewById(R.id.textGameDescription);
        spinnerChooseGame = findViewById(R.id.spinnerChooseGame);


        List<String> spinnerArray =  new ArrayList<>();
        try {
            if (service.getSchoolDistrict().equals("School district Alpha")) {
                JSONObject cityMetadata = Utility.getJSONObjectFromAssetFile(am, "us_cities.json");
                service.setCityMetadata(cityMetadata);

                spinnerArray.add(cityMetadata.getString("title"));
                textGameDescription.setText(cityMetadata.getString("description"));

            } else if (service.getSchoolDistrict().equals("School district Beta")) {
                JSONObject cityMetadata = Utility.getJSONObjectFromAssetFile(am, "european_cities.json");
                service.setCityMetadata(cityMetadata);

                spinnerArray.add(cityMetadata.getString("title"));
                textGameDescription.setText(cityMetadata.getString("description"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChooseGame.setAdapter(adapter);

        spinnerChooseGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(15);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // TODO: check if needed
            }

        });


        buttonContinueChooseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(msg, "Continue button clicked");

                // Set intent to Login page
                Intent intent = new Intent(getApplicationContext(), NewGame.class);
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
