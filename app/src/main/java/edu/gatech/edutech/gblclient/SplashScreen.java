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

        utility = (Utility) getApplicationContext();

        buttonContinue = findViewById(R.id.buttonContinue);
        textTitle = findViewById(R.id.textTitle);
        textSubtitle = findViewById(R.id.textSubtitle);
        textWelcome = findViewById(R.id.textWelcome);

        // String contents = new String(Files.readAllBytes(Paths.get("manifest.mf")));
        AssetManager am = getApplicationContext().getAssets();

        JSONObject gameMetadata = null;
        try {
            InputStream is = am.open("game_metadata.json");
            String contents = convertStreamToString(is);
            Log.d(msg, "Contents: " + contents);
            gameMetadata = new JSONObject(contents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject cityMetadata = null;
        try {
            InputStream is = am.open("us_cities.json");
            String contents = convertStreamToString(is);
            Log.d(msg, "Contents: " + contents);
            cityMetadata = new JSONObject(contents);
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }


    public static String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }
}
