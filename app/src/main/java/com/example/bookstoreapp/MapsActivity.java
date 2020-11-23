package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity {

    private static double currentLat;
    private static double currentLong;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;
    LocationRequest locationRequest;
    Location LastLocation;
    Marker CurrentMarker;
    //double currentLat, currentLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
        else
        {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

    }

    private String getURL(double lat, double lng, String nearbyplace)
    {
        /*StringBuilder googleplaceurl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleplaceurl.append("location="+lat+","+lng);
        googleplaceurl.append("&radius="+100000);
        googleplaceurl.append("&type="+nearbyplace);
        googleplaceurl.append("&sensor=true");
        googleplaceurl.append("&key="+"AIzaSyDlEuODqK-sYGxRoEVXbJxuxbh8PDYWBQo");*/

        StringBuilder googleplaceurl = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
        googleplaceurl.append("query="+MyRecyclerViewAdapter.getBookstorename());
        googleplaceurl.append("&key=AIzaSyDlEuODqK-sYGxRoEVXbJxuxbh8PDYWBQo");

        return googleplaceurl.toString();


    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null)
                {
                    currentLat = location.getLatitude();
                    currentLong = location.getLongitude();
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("Your Location");
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            mMap.addMarker(options);
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }
        }
    }

    public static double getCurrentLat() {
        return currentLat;
    }

    public static double getCurrentLong() {
        return currentLong;
    }

    public void onClick(View v)
    {
        Object dataTransfer[] = new Object[2];
        NearbySearch getNearbyPlacesData = new NearbySearch();
        switch (v.getId())
        {
            case R.id.btn_locations:
                //mMap.clear();
                String hospital = "book_store";
                String url = getURL(currentLat, currentLong, hospital);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                break;
        }
    }
}