package com.will.android.pokedexapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//This activity shows a splashscreen when opening the app

public class FullscreenActivity extends AppCompatActivity {

    ImageButton openBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        //Create the Open Pokedex Button and set it to open pokedexChooser activity when clicked
        openBtn = (ImageButton) findViewById(R.id.openBtn);
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChooser();
            }
        });
    }

    //Method that switches activities to pokedexChooser
    public void goToChooser(){
        Intent intent = new Intent(this, pokedexChooser.class);
        startActivity(intent);
    }

}
