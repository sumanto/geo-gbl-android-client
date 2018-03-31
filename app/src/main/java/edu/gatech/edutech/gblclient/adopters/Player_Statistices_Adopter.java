package edu.gatech.edutech.gblclient.adopters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.edutech.gblclient.R;
import edu.gatech.edutech.gblclient.objects.PlayerStatistices;


public class Player_Statistices_Adopter extends RecyclerView.Adapter<Player_Statistices_Adopter.MyViewHolder> {

    List<PlayerStatistices> scrambles;
    Context context;
    Activity act;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final TextView name,createdscramble,solvedscramble,average;
        public MyViewHolder(View view) {
            super(view);

            name=(TextView)view.findViewById(R.id.name);
            createdscramble=(TextView)view.findViewById(R.id.createdscramble);
            solvedscramble=(TextView)view.findViewById(R.id.solvedscramble);
            average=(TextView)view.findViewById(R.id.average);

        }

    }


    public Player_Statistices_Adopter(List<PlayerStatistices> scrambles, Context context, Activity activity) {
        this.scrambles = scrambles;
        this.context = context;
        this.act=activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_statistics_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final PlayerStatistices current=scrambles.get(position);

        holder.name.setText(current.getUsername());
        holder.average.setText(String.valueOf(current.getAvagTimesSolved()));
        holder.solvedscramble.setText(String.valueOf(current.getCountSolves()));
        holder.createdscramble.setText(String.valueOf(current.getCountScrambleCreated()));


    }


    @Override
    public int getItemCount() {
        return scrambles.size();
    }







}
