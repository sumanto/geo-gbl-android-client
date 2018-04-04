package edu.gatech.edutech.gblclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.edutech.gblclient.objects.CityObject;
import edu.gatech.edutech.gblclient.objects.PlaceAttributes;
import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

// import android.support.v7.appcompat.*;

public class ThiefAttributesActivity extends AppCompatActivity {
    String msg = "** ThiefAttributesActivity: ";

    Service service = Service.getInstance();
    ThiefAttributesActivity thisObject;

    Spinner spinnerSex, spinnerEye, spinnerHair, spinnerHobby, spinnerFood, spinnerFeature, spinnerVehicle;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thief_attributes);
        Log.d(msg, "The onCreate() event");

        spinnerSex = findViewById(R.id.spinnerSex);
        spinnerEye = findViewById(R.id.spinnerEye);
        spinnerHair = findViewById(R.id.spinnerHair);
        spinnerHobby = findViewById(R.id.spinnerHobby);
        spinnerFood = findViewById(R.id.spinnerFood);
        spinnerFeature = findViewById(R.id.spinnerFeature);
        spinnerVehicle = findViewById(R.id.spinnerVehicle);

        buttonSave = findViewById(R.id.buttonSave);

        thisObject = this;

        fillUpSpinner(spinnerSex, service.getSex());
        fillUpSpinner(spinnerEye, service.getEyes());
        fillUpSpinner(spinnerHair, service.getHair());
        fillUpSpinner(spinnerHobby, service.getHobby());
        fillUpSpinner(spinnerFood, service.getFood());
        fillUpSpinner(spinnerFeature, service.getFeature());
        fillUpSpinner(spinnerVehicle, service.getVehicle());

        // Save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void fillUpSpinner(Spinner spinner, List<String> list) {
        List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add("");
        spinnerArray.addAll(list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(1);
    }
}
