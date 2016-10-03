package com.will.android.pokedexapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

//This activity shows a splashscreen when opening the app

public class FullscreenActivity extends AppCompatActivity {

    ImageButton openBtn;
    TextView kantoPokedex;
    TextView openText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        kantoPokedex = (TextView) findViewById(R.id.kantoPokedex);
        openText = (TextView) findViewById(R.id.openText);
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gba.ttf");
        kantoPokedex.setTypeface(font);
        openText.setTypeface(font);

        //Create the Open Pokedex Button and set it to open pokedexChooser activity when clicked
        openBtn = (ImageButton) findViewById(R.id.openBtn);
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChooser();
            }
        });
        openText.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChooser();
            }
        }));
    }

    //Method that switches activities to pokedexChooser
    public void goToChooser(){
        Intent intent = new Intent(this, pokedexChooser.class);
        startActivity(intent);
    }

}
