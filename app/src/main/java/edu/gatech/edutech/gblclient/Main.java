package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
// import android.support.v7.appcompat.*;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    TextView textHistory, textLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLogout = findViewById(R.id.textLogout);
        textHistory = findViewById(R.id.textHistory);

        textHistory.setMovementMethod(new ScrollingMovementMethod());
        textHistory.setText("Hello\n");
        for (int i = 0; i < 100; i++) {
            textHistory.append("And welcome\n");
        }

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
