package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

// import com.gatech.yourapp.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
                service.setUser(userName, user);

                // Set thief attributes
                JSONObject gameMetadata = service.getGameMetadata();

                ThiefAttributes thiefAttributes = service.getThiefAttributes();
                try {
                    List<String> sexList = new ArrayList<>();
                    sexList.add("male");
                    sexList.add("female");
                    thiefAttributes.setSex(Utility.getRandomData(sexList));

                    thiefAttributes.setEyes(Utility.getRandomData(Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("eyes").names())));
                    thiefAttributes.setHair(Utility.getRandomData(Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("hair").names())));
                    thiefAttributes.setHobby(Utility.getRandomData(Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("hobby").names())));
                    thiefAttributes.setFood(Utility.getRandomData(Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("food").names())));
                    thiefAttributes.setFeature(Utility.getRandomData(Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("feature").names())));
                    thiefAttributes.setVehicle(Utility.getRandomData(Utility.convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("vehicle").names())));

                    Log.d(msg, "Thief attributes: " + thiefAttributes.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
