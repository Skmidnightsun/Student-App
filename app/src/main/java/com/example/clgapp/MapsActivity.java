package com.example.clgapp;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng aec = new LatLng(23.7158, 86.9509);
        mMap.addMarker(new MarkerOptions().position(aec).title("Aec"));
        LatLng stud1 = new LatLng(23.6893, 86.9653);
        mMap.addMarker(new MarkerOptions().position(stud1).title("Stu_1"));
        LatLng stud2 = new LatLng(23.6922, 86.9493);
        mMap.addMarker(new MarkerOptions().position(stud2).title("Stu_2"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(aec));
        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aec, zoomLevel));
    }
}