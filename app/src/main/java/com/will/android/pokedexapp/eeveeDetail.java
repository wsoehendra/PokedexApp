package com.will.android.pokedexapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.will.android.pokedexapp.Database.DatabaseAccess;
import com.will.android.pokedexapp.Model.pokemonModel;


/**
 * Created by wsoeh on 30/09/2016.
 */

public class eeveeDetail extends AppCompatActivity {

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
    Button backBtn;

    pokemonModel pokemon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eevee_detail);

        //Open a DatabaseAccess object and get Eevee
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        pokemon = dbAccess.getPokemon(133);
        dbAccess.close();

        //Initialize all layout elements
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
        backBtn = (Button) findViewById(R.id.backBtn);


        //Set custom font for Pokemon Name TextView
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gba.ttf");
        pokemonName.setTypeface(font);

        //Display the pokemon's artwork
        ImageView img = (ImageView) findViewById(R.id.pokemon_sprite);
        img.setImageResource(getResources().getIdentifier("@drawable/sa_133", "drawable", getPackageName()));

        //Populate Eevee data
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

        //Set the Type for Eevee
        pokemonTypeI.setBackground(tvBG(0xFFA8A878));

        //Back Button functionality
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), pokedexChooser.class);
                startActivity(intent);
            }
        });
    }

    //Create a Gradient Drawable to set colour and corner radius of Type TextView
    private GradientDrawable tvBG(int colour){
        GradientDrawable gradientDrawable = new GradientDrawable();

        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(colour);
        gradientDrawable.setStroke(2, colour);
        gradientDrawable.setCornerRadius(100.0f);

        return gradientDrawable;
    }

}
