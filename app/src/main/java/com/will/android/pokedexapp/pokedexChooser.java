package com.will.android.pokedexapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.will.android.pokedexapp.Database.DatabaseAccess;
import com.will.android.pokedexapp.Model.pokemonModel;

import java.util.ArrayList;
import java.util.List;

//This activity shows a list of all 151 Pokemon
//in a RecyclerView

public class pokedexChooser extends AppCompatActivity {

    //An empty list of Pokemon objects
    List<pokemonModel> pokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_chooser);

        //Create a DatabaseAccess object in order to
        //open a connection to the DB and run a query for
        //a list of Pokemon to display
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        pokemons = dbAccess.getPokemonBrief();

        //Go through the list of Pokemon, and add
        //a sprite image for each item in the list
        String uri = "@drawable/s";
        for(pokemonModel p : pokemons){
            String ID = String.valueOf(p.getID());
            uri = uri+ID;
            p.setSprite(getResources().getIdentifier(uri,"drawable",getPackageName()));
            Log.d("SPRITES", uri);
            uri = "@drawable/s";
        }

        dbAccess.close();

        //Setup the RecyclerView
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        //Ensure that each item in the RecyclerView has the same size
        rv.setHasFixedSize(true);

        //Create and link a LayoutManager (Linear) to the RecyclerView
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        //Create an RVAdapter adapter for the RecyclerView and use it
        RVAdapter adapter = new RVAdapter(pokemons);
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent e) {return true;}});

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child)+1;
                    if(position == 133){
                        Intent intent = new Intent(child.getContext(),eeveeDetail.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(child.getContext(),pokemonDetail.class);
                        intent.putExtra("pokeID", position);
                        startActivity(intent);
                    }

                } return false; }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) { }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }});
    }
}
