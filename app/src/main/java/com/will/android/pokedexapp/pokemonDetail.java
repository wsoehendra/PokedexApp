package com.will.android.pokedexapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.will.android.pokedexapp.Database.DatabaseAccess;
import com.will.android.pokedexapp.Model.pokemonModel;

public class pokemonDetail extends AppCompatActivity {

    int pokeID;
    pokemonModel pokemon = new pokemonModel();

    TextView kantoPokedex;
    TextView pokemonName;
    TextView pokemonTypeI;
    TextView pokemonTypeII;
    TextView pokemonEntry;
    TextView pokemonHP;
    TextView pokemonATK;
    TextView pokemonDEF;
    TextView pokemonSA;
    TextView pokemonSD;
    TextView pokemonSPD;
    TextView pokemonHeight;
    TextView pokemonWeight;
    TextView pokemonAbilityI;
    TextView pokemonAbilityII;
    ImageView pokemonPrevEvI;
    ImageView pokemonNextEvI;
    TextView pokemonPrevEvT;
    TextView pokemonNextEvT;
    TextView prevEvText;
    TextView nextEvText;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        //Initialize all layout elements
        kantoPokedex = (TextView) findViewById(R.id.kantoPokedex);
        pokemonName = (TextView) findViewById(R.id.pokemon_name);
        pokemonTypeI = (TextView) findViewById(R.id.pokemonTypeI);
        pokemonTypeII = (TextView) findViewById(R.id.pokemonTypeII);
        pokemonEntry = (TextView) findViewById(R.id.pokemonEntry);
        pokemonHP = (TextView) findViewById(R.id.pokemonHP);
        pokemonATK = (TextView) findViewById(R.id.pokemonATK);
        pokemonDEF = (TextView) findViewById(R.id.pokemonDEF);
        pokemonSA = (TextView) findViewById(R.id.pokemonSA);
        pokemonSD = (TextView) findViewById(R.id.pokemonSD);
        pokemonSPD = (TextView) findViewById(R.id.pokemonSPD);
        pokemonHeight = (TextView) findViewById(R.id.pokemonHeight);
        pokemonWeight = (TextView) findViewById(R.id.pokemonWeight);
        pokemonAbilityI = (TextView) findViewById(R.id.pokemonAbilityI);
        pokemonAbilityII = (TextView) findViewById(R.id.pokemonAbilityII);
        pokemonPrevEvI = (ImageView) findViewById(R.id.pokemonPrevEvI);
        pokemonNextEvI = (ImageView) findViewById(R.id.pokemonNextEvI);
        pokemonPrevEvT = (TextView) findViewById(R.id.pokemonPrevEvT);
        pokemonNextEvT = (TextView) findViewById(R.id.pokemonNextEvT);
        prevEvText = (TextView) findViewById(R.id.prevEvText);
        nextEvText = (TextView) findViewById(R.id.nextEvText);
        backBtn = (Button) findViewById(R.id.backBtn);

        //Set custom font for Pokemon Name TextView
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gba.ttf");
        kantoPokedex.setTypeface(font);
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
        pokemonEntry.setText(pokemon.getEntry());
        pokemonHP.setText(pokemon.getHp());
        pokemonATK.setText(pokemon.getAtk());
        pokemonDEF.setText(pokemon.getDef());
        pokemonSA.setText(pokemon.getSa());
        pokemonSD.setText(pokemon.getSd());
        pokemonSPD.setText(pokemon.getSpd());
        pokemonHeight.setText(pokemon.getHeight());
        pokemonWeight.setText(pokemon.getWeight());
        pokemonAbilityI.setText(pokemon.getAbilityI());
        pokemonAbilityII.setText(pokemon.getAbilityII());

        //Set Pokemon Types and colour accordingly
        setTypeColours();

        //Set Evolution Details
        setEvolutions();

        //Back Button functionality
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), pokedexChooser.class);
                startActivity(intent);
            }
        });
    }

    private void setEvolutions(){
        int prevEvID = getEvolutions(pokemon.getPrevEv());
        int nextEvID = getEvolutions(pokemon.getNextEv());

        LinearLayout evFrame = (LinearLayout) findViewById(R.id.evolutionsFrame);

        //If there is no previous/next evolution, remove the entire Evolutions frame
        if(prevEvID == 0 && nextEvID == 0)
        {
            evFrame.setVisibility(View.GONE);
        }

        //If there is no previous evolution, remove the Prev Evolution items
        //Else, display the prev evolutions
        if(prevEvID == 0){
            pokemonPrevEvI.setVisibility(View.GONE);
            pokemonPrevEvT.setVisibility(View.GONE);
            prevEvText.setVisibility(View.GONE);
        } else {
            pokemonPrevEvI.setImageResource(getResources().getIdentifier("@drawable/sa_"+prevEvID, "drawable", getPackageName()));
            pokemonPrevEvT.setText(pokemon.getPrevEv());
        }

        //If there is no next evolution, remove the Next Evolution items
        //ELse, display the next evolutions
        if(nextEvID == 0){
            pokemonNextEvI.setVisibility(View.GONE);
            pokemonNextEvT.setVisibility(View.GONE);
            nextEvText.setVisibility(View.GONE);
        } else {
            pokemonNextEvI.setImageResource(getResources().getIdentifier("@drawable/sa_"+nextEvID, "drawable", getPackageName()));
            pokemonNextEvT.setText(pokemon.getNextEv());
        }
    }

    //Get the name of the pokemon's evolutions, and retrieve ID#
    private int getEvolutions(String pokemon){
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        int evolutionID = 0;
        evolutionID = dbAccess.getEvolutions(pokemon);
        return evolutionID;
    }

    //Get all data for the current Pokemon
    private pokemonModel getPokemonDB(){
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        pokemon = dbAccess.getPokemon(pokeID);
        return pokemon;
    }

    //Set the colours of the PokemonType textfields to change with the Type of the Pokemon
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
