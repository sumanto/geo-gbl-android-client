package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.edutech.gblclient.utils.Service;

public class WordScramble extends AppCompatActivity {

    List<List<String>> scrambles = new ArrayList<List<String>>();
    Service service = Service.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_scramble);
        // scrambles = service.retrieveScrambleService();

    }
}
