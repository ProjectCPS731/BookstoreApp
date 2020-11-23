package com.example.bookstoreapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser2 {

    private HashMap<String,String> getPlace(JSONObject googlePlacejson)
    {
        HashMap<String,String> googlePlacesMap = new HashMap<>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String lat = "";
        String lng = "";
        String ref = "";
        try {
            if (!googlePlacejson.isNull("name"))
            {
                placeName = googlePlacejson.getString("name");
            }
            if (!googlePlacejson.isNull("formatted_address"))
            {
                vicinity = googlePlacejson.getString("formatted_address");
            }
            lat = googlePlacejson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            lng = googlePlacejson.getJSONObject("geometry").getJSONObject("location").getString("lng");

            ref = googlePlacejson.getString("reference");

            googlePlacesMap.put("place_name" , placeName);
            googlePlacesMap.put("formatted_address", vicinity);
            googlePlacesMap.put("lat",lat);
            googlePlacesMap.put("lng", lng);
            googlePlacesMap.put("reference", ref);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlacesMap;
    }

    private List<HashMap<String,String>> getPlaces (JSONArray jsonArray)
    {
        int count = jsonArray.length();
        List<HashMap<String,String>> placesList = new ArrayList<>();
        HashMap<String,String> placeMap = null;

        for (int x = 0; x < count; x++)
        {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(x));
                placesList.add(placeMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return placesList;
    }

    public List<HashMap<String,String>> parse(String jsonData)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }
}
