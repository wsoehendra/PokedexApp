package com.will.android.pokedexapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.will.android.pokedexapp.Database.DatabaseAccess;
import com.will.android.pokedexapp.Model.pokemonModel;

public class pokemonDetail extends AppCompatActivity {

    int pokeID;
    pokemonModel pokemon = new pokemonModel();

    TextView pokemonName;
    TextView pokemonTypeI;
    TextView pokemonTypeII;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().SYSTEM_UI_FLAG_FULLSCREEN);
        actionBar.hide();
        setContentView(R.layout.activity_pokemon_detail);

        //Initialize all layout elements
        pokemonName = (TextView) findViewById(R.id.pokemon_name);
        pokemonTypeI = (TextView) findViewById(R.id.pokemon_typeI);
        pokemonTypeII = (TextView) findViewById(R.id.pokemon_typeII);

        //Set custom font for Pokemon Name TextView
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gba.ttf");
        pokemonName.setTypeface(font);

        //Get the pokemon ID from the preceding intent
        Intent prevIntent = getIntent();
        Bundle mBundle = prevIntent.getExtras();
        pokeID = (int) mBundle.get("pokeID");

        //Retrieve the details of the specific pokemon to display
        getPokemonDB();

        //Display the pokemon's artwork
        ImageView img = (ImageView) findViewById(R.id.pokemon_sprite);
        img.setImageResource(getResources().getIdentifier("@drawable/sa_"+pokeID, "drawable", getPackageName()));

        pokemonName.setText(pokemon.getPokemon());
        pokemonTypeI.setText(pokemon.getTypeI());
        pokemonTypeII.setText(pokemon.getTypeII());

        setTypeColours();

    }

    private pokemonModel getPokemonDB(){
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        pokemon = dbAccess.getPokemon(pokeID);
        Log.d("POKE", pokemon.toString());
        return pokemon;
    }

    private void setTypeColours(){
        switch(pokemon.getTypeI().toLowerCase()){
            case "normal": pokemonTypeI.setBackgroundColor(0xFFA8A878); break;
            case "fire": pokemonTypeI.setBackgroundColor(0xFFF08030); break;
            case "fighting": pokemonTypeI.setBackgroundColor(0xFFC03028); break;
            case "water": pokemonTypeI.setBackgroundColor(0xFF6890F0); break;
            case "flying": pokemonTypeI.setBackgroundColor(0xFFA890F0); break;
            case "grass": pokemonTypeI.setBackgroundColor(0xFF78C850); break;
            case "poison": pokemonTypeI.setBackgroundColor(0xFFA040A0); break;
            case "electric": pokemonTypeI.setBackgroundColor(0xFFF8D030); break;
            case "ground": pokemonTypeI.setBackgroundColor(0xFFE0C068); break;
            case "psychic": pokemonTypeI.setBackgroundColor(0xFFF85888); break;
            case "rock": pokemonTypeI.setBackgroundColor(0xFFB8A038); break;
            case "ice": pokemonTypeI.setBackgroundColor(0xFF98D8D8); break;
            case "bug": pokemonTypeI.setBackgroundColor(0xFFA8B820); break;
            case "dragon": pokemonTypeI.setBackgroundColor(0xFF7038F8); break;
            case "ghost": pokemonTypeI.setBackgroundColor(0xFF705898); break;
            case "dark": pokemonTypeI.setBackgroundColor(0xFF705848); break;
            case "steel": pokemonTypeI.setBackgroundColor(0xFFB8B8D0); break;
            default: return;
        }

        switch(pokemon.getTypeII().toLowerCase()){
            case "normal": pokemonTypeII.setBackgroundColor(0xFFA8A878); break;
            case "fire": pokemonTypeII.setBackgroundColor(0xFFF08030); break;
            case "fighting": pokemonTypeII.setBackgroundColor(0xFFC03028); break;
            case "water": pokemonTypeII.setBackgroundColor(0xFF6890F0); break;
            case "flying": pokemonTypeII.setBackgroundColor(0xFFA890F0); break;
            case "grass": pokemonTypeII.setBackgroundColor(0xFF78C850); break;
            case "poison": pokemonTypeII.setBackgroundColor(0xFFA040A0); break;
            case "electric": pokemonTypeII.setBackgroundColor(0xFFF8D030); break;
            case "ground": pokemonTypeII.setBackgroundColor(0xFFE0C068); break;
            case "psychic": pokemonTypeII.setBackgroundColor(0xFFF85888); break;
            case "rock": pokemonTypeII.setBackgroundColor(0xFFB8A038); break;
            case "ice": pokemonTypeII.setBackgroundColor(0xFF98D8D8); break;
            case "bug": pokemonTypeII.setBackgroundColor(0xFFA8B820); break;
            case "dragon": pokemonTypeII.setBackgroundColor(0xFF7038F8); break;
            case "ghost": pokemonTypeII.setBackgroundColor(0xFF705898); break;
            case "dark": pokemonTypeII.setBackgroundColor(0xFF705848); break;
            case "steel": pokemonTypeII.setBackgroundColor(0xFFB8B8D0); break;
            default: return;
        }
    }

}
