package com.example.bertiepc.groupass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SplashPage extends AppCompatActivity {

    EditText artistName;
    Button addButton;
    Spinner spinner;

   DatabaseReference artistDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        artistDatabase = FirebaseDatabase.getInstance().getReference("artists");





        artistName = (EditText) findViewById(R.id.artistName);
        addButton = (Button) findViewById(R.id.addButton);
        spinner = (Spinner) findViewById(R.id.spinner);

        listViewArtists = (ListView) findViewById(R.id.listViewArtists);

        artistList = new ArrayList<>();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
            }
        });
    }

    ListView listViewArtists;

    List<Artist> artistList;

    @Override
    protected void onStart() {
        super.onStart();
        artistDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Artist artist = artistSnapshot.getValue(Artist.class);

                    artistList.add(artist);
                }

                ArtistList adapter = new ArtistList(SplashPage.this, artistList);
                listViewArtists.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addArtist(){
        String name = artistName.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)){
            String id = artistDatabase.push().getKey();
            Artist artist = new Artist(id, name, genre);
            artistDatabase.child(id).setValue(artist);
            Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Enter an Artist name", Toast.LENGTH_LONG).show();
        }

    }
}
