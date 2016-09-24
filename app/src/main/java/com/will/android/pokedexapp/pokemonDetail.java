package com.will.android.pokedexapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.will.android.pokedexapp.Database.DatabaseAccess;
import com.will.android.pokedexapp.Model.pokemonModel;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class pokemonDetail extends AppCompatActivity {

    int pokeID;
    pokemonModel pokemon = new pokemonModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().SYSTEM_UI_FLAG_FULLSCREEN);
        actionBar.hide();
        setContentView(R.layout.activity_pokemon_detail);
        TextView pokemonName = (TextView) findViewById(R.id.pokemon_name);
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gba.ttf");
        pokemonName.setTypeface(font);

        Intent prevIntent = getIntent();
        Bundle mBundle = prevIntent.getExtras();
        pokeID = (int) mBundle.get("pokeID");
        getPokemonDB();

        ImageView img = (ImageView) findViewById(R.id.pokemon_sprite);
        img.setImageResource(getResources().getIdentifier("@drawable/sa_"+pokeID, "drawable", getPackageName()));

        pokemonName.setText(pokemon.getPokemon());
    }

    private pokemonModel getPokemonDB(){
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        pokemon = dbAccess.getPokemon(pokeID);
        Log.d("POKE", pokemon.toString());
        return pokemon;
    }

}
