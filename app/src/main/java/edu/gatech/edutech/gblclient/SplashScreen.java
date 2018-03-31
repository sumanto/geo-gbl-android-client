package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

// import com.gatech.yourapp.R;

public class SplashScreen extends AppCompatActivity {
    String msg = "** SplashScreen: ";

    Service service = Service.getInstance();
    Utility utility;

    Button buttonContinue;
    TextView textTitle, textSubtitle, textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(msg, "The onCreate() event");

        // utility = (Utility) getApplicationContext();
        Service service = Service.getInstance();

        buttonContinue = findViewById(R.id.buttonContinue);
        textTitle = findViewById(R.id.textTitle);
        textSubtitle = findViewById(R.id.textSubtitle);
        textWelcome = findViewById(R.id.textWelcome);

        AssetManager am = getApplicationContext().getAssets();

        JSONObject gameMetadata = Utility.getJSONObjectFromAssetFile(am, "game_metadata.json");
        service.setGameMetadata(gameMetadata);

        JSONObject cityMetadata = Utility.getJSONObjectFromAssetFile(am, "us_cities.json");;
        service.setCityMetadata(gameMetadata);

        try {
            textTitle.setText(cityMetadata.getString("title"));
            textSubtitle.setText(cityMetadata.getString("description"));
            textWelcome.setText(cityMetadata.getJSONObject("main").getString("welcome"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(msg, "Continue button clicked");

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
