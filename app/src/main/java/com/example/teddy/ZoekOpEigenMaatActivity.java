package com.example.teddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ZoekOpEigenMaatActivity extends AppCompatActivity {
    String gemeente;
    String prijsType;
    String type;


    Spinner spinnerGemeente;
    Spinner spinnerPrijsType;
    Spinner spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoek_op_eigen_maat);

        spinnerGemeente= findViewById(R.id.spinnerGemeente);
        spinnerPrijsType= findViewById(R.id.spinnerPrijsType);
        spinnerType= findViewById(R.id.spinnerType);

        Button zoek = findViewById(R.id.zoeken);



        ArrayAdapter<CharSequence> adapterGemeente =
                ArrayAdapter.createFromResource(this,R.array.gemeentes,android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterPrijsType =
                ArrayAdapter.createFromResource(this,R.array.prijsTypes,android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterType =
                ArrayAdapter.createFromResource(this,R.array.types,android.R.layout.simple_spinner_item);

        adapterGemeente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPrijsType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerGemeente.setAdapter(adapterGemeente);
        spinnerPrijsType.setAdapter(adapterPrijsType);
        spinnerType.setAdapter(adapterType);

        prijsType = spinnerPrijsType.getSelectedItem().toString();
        type = spinnerType.getSelectedItem().toString();

        spinnerGemeente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                gemeente = spinnerGemeente.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinnerPrijsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                prijsType = spinnerPrijsType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                type = spinnerType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        zoek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZoekOpEigenMaatActivity.this,ZoekOpEigenMaatVerblijvenActivity.class);

                intent.putExtra("gemeente",gemeente);
                intent.putExtra("prijsType",prijsType);
                intent.putExtra("type",type);


                startActivity(intent);
            }
        });




    }



}
