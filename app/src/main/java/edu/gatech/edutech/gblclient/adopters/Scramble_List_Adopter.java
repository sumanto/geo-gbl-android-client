package edu.gatech.edutech.gblclient.adopters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.edutech.gblclient.R;
import edu.gatech.edutech.gblclient.Unscramble;
import edu.gatech.edutech.gblclient.objects.InProgress;


public class Scramble_List_Adopter extends RecyclerView.Adapter<Scramble_List_Adopter.MyViewHolder> {

    List<List<String>> scrambles;
    Context context;
    Activity act;
    edu.gatech.edutech.gblclient.utils.Utility Utility;
    Map<String, InProgress> Progress = new HashMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final TextView id;
        public final Button inprogress;
        public  final LinearLayout onclickevent;
        public MyViewHolder(View view) {
            super(view);

            id = (TextView) view.findViewById(R.id.id);
            inprogress = (Button) view.findViewById(R.id.inprogress);
            onclickevent=(LinearLayout)view.findViewById(R.id.onclickevent);
        }

    }


    public Scramble_List_Adopter(List<List<String>> scrambles, Context context, Activity activity, edu.gatech.edutech.gblclient.utils.Utility utility) {
        this.scrambles = scrambles;
        this.context = context;
        this.act=activity;
        this.Utility=utility;
        this.Progress=Utility.retriveInPrgress(utility.getCURRENT_USER());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scramble_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final List<String> current=scrambles.get(position);

        holder.id.setText(current.get(0));
        InProgress inProgress=Utility.getProgressByIndex(Utility.getCURRENT_USER(),current.get(0));
        if (inProgress.getInProgressID()==null)
        {
            inProgress.setInProgressID(current.get(0));
            inProgress.setLastPhraseState("Play");
            inProgress.setSolves(false);
            Utility.addprogress(inProgress ,Utility.getCURRENT_USER());
        }else {
            if (inProgress.getSolves()==true)
            {
                holder.inprogress.setEnabled(false);
                holder.inprogress.setText("Solved");
            }else {
                holder.inprogress.setText(inProgress.getLastPhraseState());
            }

        }
        holder.inprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(act, Unscramble.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",current.get(0));
                bundle.putString("solutionphrase",current.get(1));
                bundle.putString("scramblephrase",current.get(2));
                bundle.putString("clue",current.get(3));
                bundle.putString("Creator",current.get(4));
                //bundle.putStringArrayList("scrambledetails", (ArrayList<String>) current);
                intent.putExtras(bundle);
                act.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return scrambles.size();
    }







}
