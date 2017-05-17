package com.example.bertiepc.groupproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    Spinner townsSpinner;
    Spinner typeSpinner;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        townsSpinner = (Spinner) findViewById(R.id.selectTownSpinner);
        typeSpinner = (Spinner) findViewById(R.id.selectTypeSpinner);
        searchButton = (Button) findViewById(R.id.searchButton);

        db = new DatabaseHelper(this);

        db.insertDataT1();

        loadTownsSpinnerData();
        loadTtypeSpinnerData();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Bundle extras = new Bundle();
                extras.putString("selectedTown", townsSpinner.getSelectedItem().toString());
                extras.putString("selectedType", typeSpinner.getSelectedItem().toString());
                intent.putExtras(extras);
                MainActivity.this.startActivity(intent);
            }
        });


    }

    private void loadTownsSpinnerData() {
        List<String> towns = db.getAllTownsT1();

        ArrayAdapter<String> townsDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, towns);

        townsDataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        townsSpinner.setAdapter(townsDataAdapter);
    }

    private void loadTtypeSpinnerData() {
        List<String> types = db.getAllTypesT1();

        ArrayAdapter<String> typesDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);

        typesDataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typesDataAdapter);
    }
}
