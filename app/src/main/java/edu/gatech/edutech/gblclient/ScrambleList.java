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

import edu.gatech.edutech.gblclient.adopters.Scramble_List_Adopter;
import edu.gatech.edutech.gblclient.utils.Service;
import edu.gatech.edutech.gblclient.utils.Utility;

public class ScrambleList extends AppCompatActivity {

    List<List<String>> scrambles=new ArrayList<List<String>>();
    private RecyclerView recyclerView;
    private Scramble_List_Adopter mAdapter;
    Utility utility;
    Service service = Service.getInstance();
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scramble_list);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        utility = (Utility)getApplicationContext();
//        scrambles = service.retrieveScrambleService();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new Scramble_List_Adopter(scrambles, getApplicationContext(), ScrambleList.this,utility);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new Scramble_List_Adopter(scrambles, getApplicationContext(),ScrambleList.this, utility);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        super.onResume();
    }
}
