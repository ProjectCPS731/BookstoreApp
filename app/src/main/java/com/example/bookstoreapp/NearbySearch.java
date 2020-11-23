package com.example.bookstoreapp;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;

import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.RankBy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class NearbySearch extends AsyncTask<Object, String, String> {

    String googlePlacedata;
    GoogleMap mMap;
    String url;


    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String) objects[1];
        System.out.println(url);
        DownloadURL downloadURL = new DownloadURL();
        try {
            googlePlacedata = downloadURL.readURL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacedata;
    }

    @Override
    protected void onPostExecute(String s) {
       List<HashMap<String,String>> nearbyPlaceList = null;
       JsonParser2 parser2 = new JsonParser2();
       nearbyPlaceList = parser2.parse(s);
       showNearbyPlaces(nearbyPlaceList);
    }

    private void showNearbyPlaces(List<HashMap<String,String>> nearbyPlaceList)
    {
        for (int i = 0; i < nearbyPlaceList.size(); i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String,String> googlePlace = nearbyPlaceList.get(i);

            if (googlePlace.get("place_name").equals(MyRecyclerViewAdapter.getBookstorename()))
            {
                String placename = googlePlace.get("place_name");
                String vicinity = googlePlace.get("formatted_address");
                double lat = Double.parseDouble(googlePlace.get("lat"));
                double lng = Double.parseDouble(googlePlace.get("lng"));

                LatLng latLng = new LatLng(lat, lng);
                markerOptions.position(latLng);
                markerOptions.title(placename + ": " + vicinity);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            }



        }
    }
}
