package com.will.android.pokedexapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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
    TextView pokemonHP;
    TextView pokemonATK;
    TextView pokemonDEF;
    TextView pokemonSA;
    TextView pokemonSD;
    TextView pokemonSPD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        //Initialize all layout elements
        pokemonName = (TextView) findViewById(R.id.pokemon_name);
        pokemonTypeI = (TextView) findViewById(R.id.pokemonTypeI);
        pokemonTypeII = (TextView) findViewById(R.id.pokemonTypeII);
        pokemonHP = (TextView) findViewById(R.id.pokemonHP);
        pokemonATK = (TextView) findViewById(R.id.pokemonATK);
        pokemonDEF = (TextView) findViewById(R.id.pokemonDEF);
        pokemonSA = (TextView) findViewById(R.id.pokemonSA);
        pokemonSD = (TextView) findViewById(R.id.pokemonSD);
        pokemonSPD = (TextView) findViewById(R.id.pokemonSPD);


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

        //Populate stats textviews with correct Pokemon data
        pokemonName.setText(pokemon.getPokemon());
        pokemonTypeI.setText(pokemon.getTypeI());
        pokemonTypeII.setText(pokemon.getTypeII());
        pokemonHP.setText(pokemon.getHp());
        pokemonATK.setText(pokemon.getAtk());
        pokemonDEF.setText(pokemon.getDef());
        pokemonSA.setText(pokemon.getSa());
        pokemonSD.setText(pokemon.getSd());
        pokemonSPD.setText(pokemon.getSpd());

        //Set Pokemon Types and colour accordingly
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
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(10);

        switch(pokemon.getTypeI().toLowerCase()){
            case "normal": pokemonTypeI.setBackground(tvBG(0xFFA8A878)); break;
            case "fire": pokemonTypeI.setBackground(tvBG(0xFFF08030)); break;
            case "fighting": pokemonTypeI.setBackground(tvBG(0xFFC03028)); break;
            case "water": pokemonTypeI.setBackground(tvBG(0xFF6890F0)); break;
            case "flying": pokemonTypeI.setBackground(tvBG(0xFFA890F0)); break;
            case "grass": pokemonTypeI.setBackground(tvBG(0xFF78C850)); break;
            case "poison": pokemonTypeI.setBackground(tvBG(0xFFA040A0)); break;
            case "electric": pokemonTypeI.setBackground(tvBG(0xFFF8D030)); break;
            case "ground": pokemonTypeI.setBackground(tvBG(0xFFE0C068)); break;
            case "psychic": pokemonTypeI.setBackground(tvBG(0xFFF85888)); break;
            case "rock": pokemonTypeI.setBackground(tvBG(0xFFB8A038)); break;
            case "ice": pokemonTypeI.setBackground(tvBG(0xFF98D8D8)); break;
            case "bug": pokemonTypeI.setBackground(tvBG(0xFFA8B820)); break;
            case "dragon": pokemonTypeI.setBackground(tvBG(0xFF7038F8)); break;
            case "ghost": pokemonTypeI.setBackground(tvBG(0xFF705898)); break;
            case "dark": pokemonTypeI.setBackground(tvBG(0xFF705848)); break;
            case "steel": pokemonTypeI.setBackground(tvBG(0xFFB8B8D0)); break;
            default: ; return;
        }

        switch(pokemon.getTypeII().toLowerCase()){
            case "normal": pokemonTypeII.setBackground(tvBG(0xFFA8A878)); break;
            case "fire": pokemonTypeII.setBackground(tvBG(0xFFF08030)); break;
            case "fighting": pokemonTypeII.setBackground(tvBG(0xFFC03028)); break;
            case "water": pokemonTypeII.setBackground(tvBG(0xFF6890F0)); break;
            case "flying": pokemonTypeII.setBackground(tvBG(0xFFA890F0)); break;
            case "grass": pokemonTypeII.setBackground(tvBG(0xFF78C850)); break;
            case "poison": pokemonTypeII.setBackground(tvBG(0xFFA040A0)); break;
            case "electric": pokemonTypeII.setBackground(tvBG(0xFFF8D030)); break;
            case "ground": pokemonTypeII.setBackground(tvBG(0xFFE0C068)); break;
            case "psychic": pokemonTypeII.setBackground(tvBG(0xFFF85888)); break;
            case "rock": pokemonTypeII.setBackground(tvBG(0xFFB8A038)); break;
            case "ice": pokemonTypeII.setBackground(tvBG(0xFF98D8D8)); break;
            case "bug": pokemonTypeII.setBackground(tvBG(0xFFA8B820)); break;
            case "dragon": pokemonTypeII.setBackground(tvBG(0xFF7038F8)); break;
            case "ghost": pokemonTypeII.setBackground(tvBG(0xFF705898)); break;
            case "dark": pokemonTypeII.setBackground(tvBG(0xFF705848)); break;
            case "steel": pokemonTypeII.setBackground(tvBG(0xFFB8B8D0)); break;
            default: return;
        }
    }

    //Create a GradientDrawable object to colour and round Type TextView corners
    private GradientDrawable tvBG(int colour){
        GradientDrawable gradientDrawable = new GradientDrawable();

        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(colour);
        gradientDrawable.setStroke(2, colour);
        gradientDrawable.setCornerRadius(100.0f);

        return gradientDrawable;
    }

}
