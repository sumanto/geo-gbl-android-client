package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

public class CreateScramble extends AppCompatActivity {
    // Initialize external webservice
    Utility utility;
    EditText phrase;
    EditText clue;
    //EditText scrambledPhrase;
    TextView scrambledPhrase;
    String scrambledText;
    Button submitScramble;
    Button createScramble;
    Button rescramble;
    Button back_button;
    boolean isScrambleset;
    String scrambleID;
    boolean isReadyPhrase = false;


    public  static boolean checkForChars (String word){


        boolean hasChars = false;

        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                hasChars = true;
                break;
            }
        }

        return hasChars;

    };

    public static String scrambleWord(String word )
    {
        Random random = new Random();



        // Convert string into array
        char shuffleword[] = word.toCharArray();

        // Scramble the letters
        for( int i=0 ; i< shuffleword.length ; i++ )
        {

            int j = random.nextInt( shuffleword.length);
            // Swap letters
            char tempword =  shuffleword[i];
            shuffleword[i] =  shuffleword[j];
            shuffleword[j] = tempword;
        }
        String finalword =  new String(shuffleword);
        return finalword;
    }

    public static String getscrambleWord(String phrase )
    {
        String tempword;
        String tempphrase = "";
        Integer count = 0;
        String[] phrasearry = phrase.split("\\s+");
        for(String word: phrase.split("\\s+")){
            tempword = scrambleWord(word);
            count = count + 1;
            // Only add a space if the sentence has more than one word
            if (count == 1){
                tempphrase = tempphrase + tempword;}
            else{
                tempphrase = tempphrase + " " +  tempword;
            }

        }
        return tempphrase;
    };

    public  static boolean checkForDigits (String userPhrase){


        boolean hasDigits = false;

        for (int i = 0; i < userPhrase.length(); i++) {
            if (Character.isDigit(userPhrase.charAt(i))) {
                hasDigits = true;
                break;
            }
        }

        return hasDigits;

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_scramble);
//        phrase = (EditText) findViewById(R.id.phrase);
//        clue = (EditText) findViewById(R.id.clue);
//        scrambledPhrase = (TextView) findViewById(R.id.scrambledPhrase);
//        submitScramble = (Button) findViewById(R.id.submit_scramble);
//        createScramble = (Button) findViewById(R.id.create_scramble);
//        back_button = (Button) findViewById(R.id.back_button);
//        rescramble = (Button) findViewById(R.id.rescramble);
//
//
//        utility = (Utility) getApplicationContext();
//        playerStatistics=utility.GetplayerStatistices(utility.getCURRENT_USER());
//
//
//
//
//        back_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Main.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        createScramble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                boolean digitStatus = false;
//                isScrambleset = false;
//
//                //.*[a-zA-Z]+.*
//                if ((phrase.getText().toString().matches(".*[a-zA-Z]+.*")) && (phrase.getText().toString() != null || !(phrase.getText().toString().isEmpty()) || !(phrase.getText().toString().equals("")))){
//                    //Log.d("Phrase Message: ",phrase.getText().toString());
//
//                    digitStatus = checkForDigits (phrase.getText().toString());
//                    if (digitStatus == false){
//                        isReadyPhrase = true;}
//                    else{
//                        phrase.setError("Error: No digits allowed!");
//                        scrambledText = "";
//                        scrambledPhrase.setText("");
//                        isReadyPhrase = false;
//
//                    }
//                }
//                else{
//                    phrase.setError("Invalid phrase!");
//                    scrambledText = "";
//                    scrambledPhrase.setText("");
//                    isReadyPhrase = false;
//
//                }
//                /*
//                if ((clue.getText().toString().matches(".*[a-zA-Z]+.*")) && (clue.getText().toString() != null || !(clue.getText().toString().isEmpty()) || !(clue.getText().toString().equals("")))){
//                    //Log.d("Phrase Message: ",phrase.getText().toString());
//
//                    isReadyClue = true;
//                }
//                else{
//                    clue.setError("Invalid clue!");
//                    scrambledText = "";
//                    scrambledPhrase.setText("");
//                    isReadyClue = false;
//
//                }*/
//                //Create scramble result
//                if (isReadyPhrase == true){
//
//                    scrambledText =  getscrambleWord(phrase.getText().toString());
//                    isScrambleset = true;
//                    scrambledPhrase.setText(scrambledText);
//                }
//
//            }
//        });
//
//        submitScramble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                if ((phrase.getText().toString().matches(".*[a-zA-Z]+.*")) && (phrase.getText().toString() != null || !(phrase.getText().toString().isEmpty()) || !(phrase.getText().toString().equals("")))){
//                    //Log.d("Phrase Message: ",phrase.getText().toString());
//                    //Check for digits in string
//
//                    isReadyPhrase = true;
//                }
//                else{
//                    phrase.setError("Invalid phrase!");
//                    scrambledText = "";
//                    scrambledPhrase.setText("");
//                    isReadyPhrase = false;
//
//                }
//                /*
//                if ( (clue.getText().toString() != null || !(clue.getText().toString().isEmpty()) || !(clue.getText().toString().equals("")))){
//                    //Log.d("Phrase Message: ",phrase.getText().toString());
//
//                    isReadyClue = true;
//                }
//                else{
//                    clue.setError("Invalid clue!");
//                    scrambledText = "";
//                    scrambledPhrase.setText("");
//                    isReadyClue = false;
//
//                    //Save clue somewhere??
//
//                }*/
//
//                if (isScrambleset == false){
//
//                    Toast.makeText(getApplicationContext(), "ALERT: Please create a scramble first!",
//                            Toast.LENGTH_SHORT).show();
//                }
//                //Submit Scramble
//                if (isReadyPhrase == true && isScrambleset == true){
//                    //getusername from database  ***********
//                    String current_username = service.getCurrentUsername();
//
//                    try {
//                        String getanswer = phrase.getText().toString();
//                        String getscramble = scrambledText.toString();
//                        String getclue = clue.getText().toString();
//                        String getcurrentuser = current_username.toString();
//                        // scrambleID = service.newScrambleService(getanswer,getscramble,getclue,getcurrentuser);
//
//                        //Log.d("Created Scrambled ID: ",scrambleID);
//                        //Add scramble data to database  *********
//
//                        playerStatistics.IncrementCreated();
//                        utility.setPlayerStatistices(playerStatistics, utility.getCURRENT_USER());
//
//                        Toast.makeText(getApplicationContext(), "Successfully added a new scramble!",
//                                Toast.LENGTH_SHORT).show();
//                        //Clear fields
//                        scrambledText = "";
//                        phrase.setText("");
//                        scrambledPhrase.setText("");
//                        clue.setText("");
////                    }
////                    catch (SocketTimeoutException e) {
////
////                        e.printStackTrace();
//                    } catch (IllegalArgumentException e) {
//
//                        e.printStackTrace();
//                    }
//
//
//
//
//                }
//
//            }
//        });
//
//        rescramble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if ((scrambledPhrase.getText().toString() != null || !(scrambledPhrase.getText().toString().isEmpty()) || !(scrambledPhrase.getText().toString().equals("")))){
//                    //Log.d("Phrase Message: ",phrase.getText().toString());
//                    scrambledText =  getscrambleWord(scrambledPhrase.getText().toString());
//                    isScrambleset = true;
//                    scrambledPhrase.setText(scrambledText);
//                }
//
//
//
//            }
//        });

    }
}

