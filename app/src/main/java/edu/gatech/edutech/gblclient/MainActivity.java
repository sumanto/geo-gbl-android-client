package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
// import android.support.v7.appcompat.*;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button exit,createscramble,playscramble,scramblestat,playerstat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exit=(Button)findViewById(R.id.exit);
        createscramble=(Button)findViewById(R.id.createscramble);
        playscramble=(Button)findViewById(R.id.playscramble);
        scramblestat=(Button)findViewById(R.id.scramblestatistices);
        playerstat=(Button)findViewById(R.id.playerstatistices);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        createscramble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CreateScramble.class);
                startActivity(intent);
            }
        });
        playscramble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ScrambleList.class);
                startActivity(intent);
            }
        });
        scramblestat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ScrambleStatistics.class);
                startActivity(intent);
            }
        });
        playerstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PlayerStatistics.class);
                startActivity(intent);
            }
        });

    }
}
