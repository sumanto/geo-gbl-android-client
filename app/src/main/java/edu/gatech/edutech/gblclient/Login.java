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

    EditText inputStudentUserName, inputStudentName;
    Button buttonStudentLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(msg, "The onCreate() event");

        service.clearCache();
        utility = (Utility) getApplicationContext();

        buttonStudentLogin = findViewById(R.id.buttonStudentLogin);
        inputStudentUserName = findViewById(R.id.inputStudentUserName);
        inputStudentName = findViewById(R.id.inputStudentName);

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

                // Go to main page now
                Intent intent = new Intent(getApplicationContext(), ChooseGame.class);
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
