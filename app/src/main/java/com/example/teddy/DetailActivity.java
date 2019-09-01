package com.example.teddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();


        String urlms = intent.getStringExtra("urlattribuut");
        String linkms = intent.getStringExtra("linkattribuut");
        String telefoonm = intent.getStringExtra("telefoonattribuut");
        String emailm = intent.getStringExtra("emailattribuut");
        String naamm = intent.getStringExtra("naamattribuut");

        setTitle(naamm);
        TextView urlT = findViewById(R.id.urlz_verblijf);
        TextView linkT = findViewById(R.id.link_verblijf2);
        TextView telefoonT = findViewById(R.id.Telefoon_verblijf3);
        TextView emailT = findViewById(R.id.email_verblijf2);


        urlT.setText(urlms);
        linkT.setText(linkms);
        telefoonT.setText(telefoonm);
        emailT.setText(emailm);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent = getIntent();
        Double xCoordinate = intent.getDoubleExtra("xCoordinate",000000);
        Double yCoordinate = intent.getDoubleExtra("yCoordinate",000000);
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.


        LatLng land = new LatLng(yCoordinate, xCoordinate);
        googleMap.addMarker(new MarkerOptions().position(land)
                .title("Antwerpen"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(land));
        googleMap.setMinZoomPreference(10);
    }
}
