package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import com.gatech.yourapp.R;
import edu.gatech.edutech.gblclient.objects.PlayerStatistices;
import edu.gatech.edutech.gblclient.utils.DBManager;
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

        utility = (Utility) getApplicationContext();

        buttonTeacherLogin = (Button) findViewById(R.id.buttonTeacherLogin);
        inputTeacherUserName = (EditText) findViewById(R.id.inputTeacherUserName);
        inputTeacherName = (EditText) findViewById(R.id.inputTeacherName);

        buttonTeacherLogin.setHint("Teacher login is not implemented");
        buttonTeacherLogin.setEnabled(false);
        inputTeacherUserName.setEnabled(false);
        inputTeacherName.setEnabled(false);

        buttonStudentLogin = (Button) findViewById(R.id.buttonStudentLogin);
        inputStudentUserName = (EditText) findViewById(R.id.inputStudentUserName);
        inputStudentName = (EditText) findViewById(R.id.inputStudentName);

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

//                // Means the username is correct, continue to next page
//                service.setUser(player.get(0), player.get(1), player.get(2), player.get(3));

//                // Fill up cache
//                service.fillCache();

//                utility.setCURRENT_USER(player.get(0));

//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
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
