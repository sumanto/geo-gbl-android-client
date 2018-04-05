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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import edu.gatech.edutech.gblclient.objects.CityObject;
import edu.gatech.edutech.gblclient.objects.PlaceAttributes;
import edu.gatech.edutech.gblclient.objects.ThiefAttributes;


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

        int randomNum = ThreadLocalRandom.current().nextInt(0, data.size());
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


    public static void setupNewCityActions(Service service, String presentCity, boolean correctCity) {
        JSONObject cityMetadata = service.getCityMetadata();
        JSONObject gameMetadata = service.getGameMetadata();

        try {
            // City setup
            service.setPresentCity(cityMetadata.getJSONObject("cities").getJSONObject(presentCity));
            service.setPresentCityName(service.getPresentCity().getString("name"));
            service.setPresentCityDescription(service.getPresentCity().getString("description"));
            service.setPresentCityNickname(getRandomData(convertJSONArrayToList(service.getPresentCity().getJSONArray("nicknames"))));

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


            // Person text
            String personText;
            if (correctCity) {
                String thiefSalutation = service.getThiefSalutation();

                String landmark = getRandomData(convertJSONArrayToList(service.getNextCity().getJSONObject("landmarks").names()));
                String landmarkText = getRandomData(convertJSONArrayToList(service.getNextCity().getJSONObject("landmarks").getJSONObject(landmark).getJSONArray("tidbits")));

                personText = thiefSalutation + " talked about " + landmark + " - " + landmarkText;

                String thiefAttr = getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("attributes").names()));

                ThiefAttributes thiefAttributes = service.getThiefAttributes();
                String thiefAttrHelp = "";
                if (thiefAttr.equals("hair")) {
                    thiefAttrHelp = thiefSalutation + " " + getRandomData(convertJSONArrayToList(gameMetadata.getJSONArray(thiefAttr + "-sentences-similars"))) + " " + getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject(thiefAttr).getJSONObject(thiefAttributes.getHair()).getJSONArray("similars")));
                } else if (thiefAttr.equals("eyes")) {
                    thiefAttrHelp = thiefSalutation + " " + getRandomData(convertJSONArrayToList(gameMetadata.getJSONArray(thiefAttr + "-sentences-similars"))) + " " + getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject(thiefAttr).getJSONObject(thiefAttributes.getEyes()).getJSONArray("similars")));
                } else if (thiefAttr.equals("hobby")) {
                    thiefAttrHelp = thiefSalutation + " talked about " + thiefAttributes.getHobby();
                } else if (thiefAttr.equals("vehicle")) {
                    thiefAttrHelp = thiefSalutation + " wanted to ride a " + thiefAttributes.getVehicle();
                } else if (thiefAttr .equals("feature")) {
                    thiefAttrHelp = thiefSalutation + " " + getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("feature").getJSONArray(thiefAttributes.getFeature())));
                } else if (thiefAttr.equals("food")) {
                    thiefAttrHelp = thiefSalutation + " " + getRandomData(convertJSONArrayToList(gameMetadata.getJSONArray("food-sentences"))) + " " + getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("attributes").getJSONObject("food").getJSONArray(thiefAttributes.getFood())));
                }

                personText += " " + thiefAttrHelp;
            } else {
                personText = getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("wrong-person").getJSONArray("person")));
            }

            service.setPersonText(personText);


            // Place text
            PlaceAttributes placeAttributes = new PlaceAttributes();
            placeAttributes.setItem(getRandomData(convertJSONArrayToList(gameMetadata.getJSONObject("evidence-items").names())));
            placeAttributes.setCity(service.getPresentCityName());
            placeAttributes.setDay(DAY_FORMAT.format(service.getDateTime()));
            placeAttributes.setTime(TIME_FORMAT.format(service.getDateTime()));

            if (correctCity) {
                String landmark = getRandomData(convertJSONArrayToList(service.getNextCity().getJSONObject("landmarks").names()));
                String landmarkText = getRandomData(convertJSONArrayToList(service.getNextCity().getJSONObject("landmarks").getJSONObject(landmark).getJSONArray("tidbits")));
                placeAttributes.setDescription("The " + placeAttributes.getItem() + " contained an advertisement for " + landmark + " - " + landmarkText);
            } else {
                JSONArray wrongItems = gameMetadata.getJSONObject("evidence-items").getJSONObject(placeAttributes.getItem()).getJSONArray("wrong");
                if (wrongItems.length() > 0) {
                    placeAttributes.setDescription(getRandomData(convertJSONArrayToList(wrongItems)));
                } else {
                    placeAttributes.setDescription("");
                }
            }

            service.setPlaceAttributes(placeAttributes);


            // Setup information about the next place
            List<String> choices = new ArrayList<>();
            List<CityObject> cityChoiceObjects = new ArrayList<>();
            int rightChoice = ThreadLocalRandom.current().nextInt(1, 4);
            service.setRightChoice(rightChoice);

            List<String> alreadyAdded = new ArrayList<>();

            // Special case for present/next correct citu
            alreadyAdded.add(nextCityName);
            alreadyAdded.add(presentCity);

            int i = 1;
            String flightNumber;
            while (choices.size() < 3) {
                if (rightChoice == i) {
                    flightNumber = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 2000));
                    choices.add(nextCityName + " (flight #: " + flightNumber + ")");
                    CityObject cityObject = new CityObject(nextCityName, flightNumber);
                    cityChoiceObjects.add(cityObject);
                    i++;

                    continue;
                }

                String choice = getRandomData(convertJSONArrayToList(cityMetadata.getJSONObject("cities").names()));
                String cityToAdd = cityMetadata.getJSONObject("cities").getJSONObject(choice).getString("name");
                if (alreadyAdded.indexOf(cityToAdd) >= 0) {
                    continue;
                }

                alreadyAdded.add(cityToAdd);
                flightNumber = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 2000));
                choices.add(cityToAdd + " (flight #: " + flightNumber + ")");
                CityObject cityObject = new CityObject(cityToAdd, flightNumber);
                cityChoiceObjects.add(cityObject);

                i++;
            }

            service.setNextCities(choices);
            service.setNextCitiesObjects(cityChoiceObjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat ("dd.MM.yyyy");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat ("hh:mm");
}
