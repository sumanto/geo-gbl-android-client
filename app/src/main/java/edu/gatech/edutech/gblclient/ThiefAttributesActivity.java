package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.edutech.gblclient.utils.Service;


public class ThiefAttributesActivity extends AppCompatActivity {
    String msg = "** ThiefAttributesActivity: ";

    Service service = Service.getInstance();

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

        // Fill up spinners, set defaults
        fillUpSpinner(spinnerSex, service.getSex(), service.getGuessedAttributes().getSex());
        fillUpSpinner(spinnerEye, service.getEyes(), service.getGuessedAttributes().getEyes());
        fillUpSpinner(spinnerHair, service.getHair(), service.getGuessedAttributes().getHair());
        fillUpSpinner(spinnerHobby, service.getHobby(), service.getGuessedAttributes().getHobby());
        fillUpSpinner(spinnerFood, service.getFood(), service.getGuessedAttributes().getFood());
        fillUpSpinner(spinnerFeature, service.getFeature(), service.getGuessedAttributes().getFeature());
        fillUpSpinner(spinnerVehicle, service.getVehicle(), service.getGuessedAttributes().getVehicle());

        // Save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save changes
                if (spinnerSex.getSelectedItem() != null) {
                    service.getGuessedAttributes().setSex((String) spinnerSex.getSelectedItem());
                }

                if (spinnerEye.getSelectedItem() != null) {
                    service.getGuessedAttributes().setEyes((String) spinnerEye.getSelectedItem());
                }

                if (spinnerHair.getSelectedItem() != null) {
                    service.getGuessedAttributes().setHair((String) spinnerHair.getSelectedItem());
                }

                if (spinnerHobby.getSelectedItem() != null) {
                    service.getGuessedAttributes().setHobby((String) spinnerHobby.getSelectedItem());
                }

                if (spinnerFood.getSelectedItem() != null) {
                    service.getGuessedAttributes().setFood((String) spinnerFood.getSelectedItem());
                }

                if (spinnerFeature.getSelectedItem() != null) {
                    service.getGuessedAttributes().setFeature((String) spinnerFeature.getSelectedItem());
                }

                if (spinnerVehicle.getSelectedItem() != null) {
                    service.getGuessedAttributes().setVehicle((String) spinnerVehicle.getSelectedItem());
                }


                // Go back to Main screen
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void fillUpSpinner(Spinner spinner, List<String> list, String savedGuess) {
        List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add("");
        spinnerArray.addAll(list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (savedGuess == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(savedGuess)) {
                spinner.setSelection(i + 1);
                return;
            }
        }
    }
}
