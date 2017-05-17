package com.example.bertiepc.groupproject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    Spinner townsSpinner;
    Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        townsSpinner = (Spinner) findViewById(R.id.selectTownSpinner);
        typeSpinner = (Spinner) findViewById(R.id.selectTypeSpinner);

        db = new DatabaseHelper(this);

        db.insertDataT1();

        loadTownsSpinnerData();
        loadTtypeSpinnerData();


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
