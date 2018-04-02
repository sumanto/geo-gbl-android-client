package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
// import android.support.v7.appcompat.*;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.edutech.gblclient.utils.Service;

public class Main extends AppCompatActivity {

    Service service = Service.getInstance();
    TextView textHistory, textPersonAction, textPlaceAction, textTravelAction, textThiefAttributesAction, textWarrantAction, textLogout;
    Main thisObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisObject = this;

        textHistory = findViewById(R.id.textHistory);

        textPersonAction = findViewById(R.id.textPersonAction);
        textPlaceAction = findViewById(R.id.textPlaceAction);
        textTravelAction = findViewById(R.id.textTravelAction);
        textThiefAttributesAction = findViewById(R.id.textThiefAttributesAction);
        textWarrantAction = findViewById(R.id.textWarrantAction);
        textLogout = findViewById(R.id.textLogout);


        // Set up scrolling
        textHistory.setMovementMethod(new ScrollingMovementMethod());

        textPersonAction.setText("> " + service.getPersonAction());
        textPlaceAction.setText("> " + service.getPlaceAction());


        // TODO: differentiate between coming back and initial page
        // Welcome user
        textHistory.setText("");
        textHistory.append("Welcome " + service.getUserFullName() + " !!\n");
        textHistory.append("Welcome to " + service.getPresentCityName() + " !!\n");
        textHistory.append("The thief has stolen " + service.getStolenArtifact() + " from the city !!\n");


        textPersonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(thisObject);
                builder.setTitle("My title");
                builder.setMessage("This is my message.");

                // add a button
                builder.setPositiveButton("OK", null);

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        // Logout action
        textLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

//        createscramble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),CreateScramble.class);
//                startActivity(intent);
//            }
//        });
//        playscramble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),ScrambleList.class);
//                startActivity(intent);
//            }
//        });
//        scramblestat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),ScrambleStatistics.class);
//                startActivity(intent);
//            }
//        });
//        playerstat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),PlayerStatistics.class);
//                startActivity(intent);
//            }
//        });

    }
}
