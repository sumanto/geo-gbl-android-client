package edu.gatech.edutech.gblclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

public class PlayerStatistics extends AppCompatActivity {


//    List<PlayerStatistices> Playerstatices= new ArrayList<>();
//    private RecyclerView recyclerView;
//    private Player_Statistices_Adopter mAdapter;
    Service service = Service.getInstance();
    Utility utility;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_statistics);


//        back=(Button)findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        utility = (Utility) getApplicationContext();
////        List<List<String>> users = service.retrievePlayerListService();
////
////        for (List<String> user: users) {
////            PlayerStatistices playerStatistices = utility.GetplayerStatistices(user.get(0));
////            if (playerStatistices!=null) {
////                playerStatistices.setUsername(user.get(0));
////                Playerstatices.add(playerStatistices);
////            }
////        }
//
//
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        mAdapter = new Player_Statistices_Adopter(Playerstatices, getApplicationContext(),PlayerStatistics.this);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
    }
}
