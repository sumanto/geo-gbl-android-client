package edu.gatech.edutech.gblclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

public class CreatePlayer extends AppCompatActivity {

    Button createplayerbtn, backbtn;
    EditText firstname,lastname,email,username;
    Service service = Service.getInstance();
    Utility utility;

    //method used to check if any player fields are empty
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create__player);
//        createplayerbtn=(Button)findViewById(R.id.buttonCreatePlayer);
//        backbtn= (Button) findViewById(R.id.buttonBack);
//        dbManager = new DBManager(this);
//        dbManager.open();
//        firstname=(EditText)findViewById(R.id.firstName);
//        lastname=(EditText)findViewById(R.id.lastName);
//        email=(EditText)findViewById(R.id.email);
//        username=(EditText)findViewById(R.id.username);
//        utility=(Utility)getApplicationContext();
//        //listener for clicking back
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
//        //listener for Create Player
//        createplayerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//
//                    String checkemail = email.getText().toString();
//
//                    String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
//                    Pattern pattern = Pattern.compile(regex);
//                    Matcher matcher = pattern.matcher(checkemail);
//
//                    if (matcher.matches()) {
//                        String returnvalue = service.newPlayerService(username.getText().toString(), firstname.getText().toString(), lastname.getText().toString(), email.getText().toString());
//                        System.out.println("This is username: " + returnvalue);
//                        TextView result = (TextView) findViewById(R.id.result);
//                        result.append(returnvalue);
//
//                        // Add Database entries for new player
//                        dbManager.insertNewPlayer(returnvalue.toString(), firstname.getText().toString(), lastname.getText().toString(), email.getText().toString());
//                    }
//                    else{
//                        email.setError("Invalid Email address!");
//
//                    }
//                } catch (SocketTimeoutException e) {
//                    e.printStackTrace();
//                } catch (IllegalArgumentException e) {
//                    //if following fields are empty show meaningful error message
//                    if (isEmpty(email)) {
//                        email.setError("Email required");
//                    }
//                    if (isEmpty(firstname)) {
//                        firstname.setError("First name required");
//                    }
//                    if (isEmpty(lastname)) {
//                        lastname.setError("Last name required");
//                    }
//                    if (isEmpty(username)) {
//                        username.setError("Desired username required");
//                    }
//                }
//            }
//        });
    }
}
