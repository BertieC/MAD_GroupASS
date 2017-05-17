package com.example.bertiepc.groupproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    DatabaseHelper db;

    String selectedTown;
    String selectedType;
    ListView places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        selectedTown = extras.getString("selectedTown");
        selectedType = extras.getString("selectedType");
        places = (ListView) findViewById(R.id.placesListView);

        displayPlaces(selectedTown,selectedType);
    }

    public void displayPlaces(String selectedTown, String selectedType){

        ArrayList<String> placeNameList = new ArrayList<>();

        Cursor data = db.getPlaces(selectedTown,selectedType);

        while (data.moveToNext()){
            placeNameList.add("\n \n" + data.getString(1) + "\n \n" + data.getString(8) + "\n");
            ListAdapter nameListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, placeNameList);places.setAdapter(nameListAdapter);
        }

    }
}
