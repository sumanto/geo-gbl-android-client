package edu.gatech.edutech.gblclient.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;



public class Utility extends Application {
    public static final String msg = "** Utility: ";

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }



    public static JSONObject getJSONObjectFromAssetFile(AssetManager am, String assetFileName) {
        try {
            InputStream is = am.open(assetFileName);
            String contents = Utility.convertStreamToString(is);
            Log.d(msg, "Contents: " + contents);

            return new JSONObject(contents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getRandomData(List<String> data) {
        if (data == null || data.size() == 0) {
            return null;
        }

        int randomNum = ThreadLocalRandom.current().nextInt(0, data.size() - 1);
        return data.get(randomNum);
    }


    public static List<String> convertJSONArrayToList(JSONArray jsonArray) {
        ArrayList<String> listData = new ArrayList<String>();
        if (jsonArray != null) {
            for (int i=0;i<jsonArray.length();i++) {
                try {
                    listData.add(jsonArray.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return listData;
    }


    public static void setupNewCityActions(Service service, String presentCity) {
        JSONObject cityMetadata = service.getCityMetadata();
        JSONObject gameMetadata = service.getGameMetadata();

        try {
            // City setup
            service.setPresentCity(cityMetadata.getJSONObject("cities").getJSONObject(presentCity));
            service.setPresentCityName(service.getPresentCity().getString("name"));

            String nextCityName = Utility.getRandomData(Utility.convertJSONArrayToList(cityMetadata.getJSONObject("cities").names()));
            JSONObject nextCity = cityMetadata.getJSONObject("cities").getJSONObject(nextCityName);
            service.setNextCity(nextCity);
            service.setNextCityName(service.getNextCity().getString("name"));

            // Actions setup
            String person = getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("person-places").names()));
            service.setPerson(person);
            String personAction = getRandomData(convertJSONArrayToList(gameMetadata.getJSONArray("person-actions"))) + " " + person;
            service.setPersonAction(personAction);

            String place = getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("person-places").getJSONArray(person)));
            service.setPlace(place);
            String placeAction = getRandomData(convertJSONArrayToList(gameMetadata.getJSONArray("place-actions"))) + " " + place;
            service.setPlaceAction(placeAction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
