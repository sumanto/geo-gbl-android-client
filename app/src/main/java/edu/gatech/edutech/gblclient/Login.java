package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

// import com.gatech.yourapp.R;
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

//                // Means the username is correct, continue to next page
//                service.setUser(player.get(0), player.get(1), player.get(2), player.get(3));

//                // Fill up cache
//                service.fillCache();

//                utility.setCURRENT_USER(player.get(0));

                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

//        buttonExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
//                startActivity(intent);
//                finish();
//            }
//        });
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
