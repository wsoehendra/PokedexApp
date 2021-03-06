package com.will.android.pokedexapp;

import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.will.android.pokedexapp.Model.pokemonModel;

import java.util.List;

import pl.droidsonroids.gif.GifTextView;

/**
 * Created by wsoeh on 20/09/2016.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.pokemonViewHolder> {

    //Create a ViewHolder for the elements in the card
    public static class pokemonViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView pokemonName;
        TextView pokemonID;
        GifTextView pokemonSprite;

        pokemonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            pokemonName = (TextView)itemView.findViewById(R.id.pokemon_name);
            pokemonID = (TextView)itemView.findViewById(R.id.pokemon_id);
            pokemonSprite = (GifTextView)itemView.findViewById(R.id.pokemon_sprite);

            //Set PokemonName font as custom font
            Typeface font = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/gba.ttf");
            this.pokemonName.setTypeface(font);
        }

    }

    List<pokemonModel> pokemonsList;

    RVAdapter(List<pokemonModel> pokemonsList){
        this.pokemonsList = pokemonsList;
    }

    @Override
    public int getItemCount() {
        return pokemonsList.size();
    }

    @Override
    public pokemonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup, false);
        pokemonViewHolder pvh = new pokemonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(pokemonViewHolder pokemonViewHolder, int i) {
        pokemonViewHolder.pokemonName.setText(pokemonsList.get(i).getPokemon());
        pokemonViewHolder.pokemonID.setText("# "+String.valueOf(pokemonsList.get(i).getID()));
        pokemonViewHolder.pokemonSprite.setBackgroundResource(pokemonsList.get(i).getSprite());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
