package edu.gatech.edutech.gblclient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import edu.gatech.edutech.gblclient.utils.Service;


public class ScrambleStatistics extends AppCompatActivity {
    String msg = "** ScrambleStatistics: ";

    Service service = Service.getInstance();

    Button buttonBack;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scramble_statistics);
        Log.d(msg, "The onCreate() event");
        Log.d(msg, "Principal: " + service.getCurrentUsername());

        buttonBack = (Button) findViewById(R.id.buttonBackScrambleStatistics);


        // Get View ready
        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        int[] fixedColumnWidths = new int[]{20, 40, 40};
        int[] scrollableColumnWidths = new int[]{45, 25, 35};
        int fixedRowHeight = 50;
        int fixedHeaderHeight = 60;

        // Rpw header
        TableRow row = new TableRow(this);
        TableLayout header = (TableLayout) findViewById(R.id.table_header);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);
        row.setBackgroundColor(Color.GRAY);
        row.addView(makeTableRowWithText(getResources().getString(R.string.scramble_statistics_id), fixedColumnWidths[0], fixedHeaderHeight));
        row.addView(makeTableRowWithText(getResources().getString(R.string.scramble_statistics_solved_number), fixedColumnWidths[1], fixedHeaderHeight));
        row.addView(makeTableRowWithText(getResources().getString(R.string.scramble_statistics_creator_solver), fixedColumnWidths[2], fixedHeaderHeight));
        header.addView(row);

        // rest of the table (within a scroll view)
        TableLayout scrollablePart = (TableLayout) findViewById(R.id.scrollable_part);

        // Sort by times solved
        Map<String, List<List<String>>> map = getScrambleStatistics();
        Object[] keys = new TreeSet<>(map.keySet()).toArray();

        // Iterate and display
        for (int index = keys.length - 1; index >= 0; index--) {
            List<List<String>> allValues = map.get((String)keys[index]);

            for (List<String> values: allValues) {
                // add the row
                row = new TableRow(this);
                row.setLayoutParams(wrapWrapTableRowParams);
                row.setGravity(Gravity.CENTER);
                row.setBackgroundColor(Color.WHITE);

                row.addView(makeTableRowWithText(values.get(0), scrollableColumnWidths[0], fixedRowHeight));
                row.addView(makeTableRowWithText(values.get(1), scrollableColumnWidths[1], fixedRowHeight));
                row.addView(makeTableRowWithText(values.get(2), scrollableColumnWidths[2], fixedRowHeight));

                // Add to view
                scrollablePart.addView(row);
            }
        }

        // Bacl button listener
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(msg, "Back button clicked");

                // Username is not validated
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    // Used to cache scramble statistics
    private Map<String, List<List<String>>> getScrambleStatistics() {
        Map<String, List<List<String>>> map = service.getScrambleStatistics();
        if (map != null) {
            Log.d(msg, "using cached scramble statistics");
            return map;
        }

        Log.d(msg, "cache miss for scramble statistics");

        map = new HashMap<>();
        List<List<String>> players = service.retrievePlayerListService();
        List<List<String>> scrambles = service.retrieveScrambleService();
        Log.d(msg, "Players: " + players);
        Log.d(msg, "Scrambles: " + scrambles);

        // Get list of users for each scramble solved
        Map<String, List<String>> scramblesSolved = new HashMap<>();
        for (List<String> player: players) {
            int index = 4;
            while (index < player.size()) {
                String scrambleId = player.get(index);
                if (scramblesSolved.get(scrambleId) == null) {
                    scramblesSolved.put(scrambleId, new ArrayList<String>());
                }
                scramblesSolved.get(scrambleId).add(player.get(0));
                index++;
            }
        }
        Log.d(msg, "solved: " + scramblesSolved);

        // Get the data in a format we can sort and display later
        for (List<String> scramble: scrambles) {
            List<String> scrambleStatistic = new ArrayList<>();

            // 1st place: id
            String scrambleId = scramble.get(0);
            scrambleStatistic.add(scrambleId);

            // 2nd place: number of times any player has solved them
            List<String> solvedUserIds = scramblesSolved.get(scrambleId);
            if (solvedUserIds == null) {
                solvedUserIds = new ArrayList<>();
            }
            scrambleStatistic.add(Integer.toString(solvedUserIds.size()));

            // 3nd place: solved or created by player
            String relationship = "None";
            if (scramble.get(4).equals(service.getCurrentUsername())) {
                relationship = "Creator";
            } else if (solvedUserIds.indexOf(service.getCurrentUsername()) > 0) {
                relationship = "Solver";
            }
            scrambleStatistic.add(relationship);

            // Add to map by times solved
            List<List<String>> existing = map.get(scrambleStatistic.get(1));
            if (existing == null) {
                existing = new ArrayList<>();
            }
            existing.add(scrambleStatistic);
            map.put(scrambleStatistic.get(1), existing);
        }

        service.setScrambleStatistics(map);
        return map;
    }

    // util method to add textview
    private TextView makeTableRowWithText(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        TextView recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextSize(20);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        recyclableTextView.setGravity(Gravity.CENTER);

        return recyclableTextView;
    }
}
