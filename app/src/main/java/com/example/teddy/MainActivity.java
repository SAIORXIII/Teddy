package com.example.teddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnkinderdagverblijven = findViewById(R.id.buttonKinderdagVerblijven);
        Button btnZoekOpEigenMaat = findViewById(R.id.buttonZoekOpEigenMaat);


        btnkinderdagverblijven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KinderdagverblijvenActivity.class));
            }
        });

        btnZoekOpEigenMaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ZoekOpEigenMaatActivity.class));
            }
        });
    }
}
