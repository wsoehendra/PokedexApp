package com.will.android.pokedexapp;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class pokemonDetail extends AppCompatActivity {

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

        ImageView img = (ImageView) findViewById(R.id.pokemon_sprite);
        img.setImageResource(getResources().getIdentifier("@drawable/sa_1", "drawable", getPackageName()));
    }

}
