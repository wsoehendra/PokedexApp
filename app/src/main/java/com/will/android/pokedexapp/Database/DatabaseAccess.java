package com.will.android.pokedexapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.will.android.pokedexapp.Model.pokemonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsoeh on 19/09/2016.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //Returns an instance of DatabaseAccess: to be called when using this class
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //Open connection
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    //close connection
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    //Get a list of Pokemons (Only ID# and Name), used for PokedexChooser
    public ArrayList<pokemonModel> getPokemonBrief() {
        ArrayList<pokemonModel> list = new ArrayList<pokemonModel>();
        Cursor cursor = database.rawQuery("SELECT * FROM POKEMON", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pokemonModel poke = new pokemonModel();
            poke.setID(cursor.getInt(0));
            poke.setPokemon(cursor.getString(1));
            list.add(poke);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //Get a specific Pokemon, given an ID#
    //Used in pokemonDetail class
    public pokemonModel getPokemon(int pokeID){
        pokemonModel selectedPoke = new pokemonModel();
        Cursor cursor = database.rawQuery("SELECT * FROM POKEMON WHERE ID = "+pokeID, null);
        cursor.moveToFirst();
        selectedPoke.setID(cursor.getInt(0));
        selectedPoke.setPokemon(cursor.getString(1));
        selectedPoke.setEntry(cursor.getString(2));
        selectedPoke.setHp(cursor.getString(3));
        selectedPoke.setAtk(cursor.getString(4));
        selectedPoke.setDef(cursor.getString(5));
        selectedPoke.setSa(cursor.getString(6));
        selectedPoke.setSd(cursor.getString(7));
        selectedPoke.setSpd(cursor.getString(8));
        selectedPoke.setTypeI(cursor.getString(9));
        selectedPoke.setTypeII(cursor.getString(10));
        selectedPoke.setAbilityI(cursor.getString(11));
        selectedPoke.setAbilityII(cursor.getString(12));
        selectedPoke.setPrevEv(cursor.getString(13));
        selectedPoke.setNextEv(cursor.getString(14));
        selectedPoke.setHeight(cursor.getString(15));
        selectedPoke.setWeight(cursor.getString(16));

        cursor.close();
        return selectedPoke;
    }

    //Used for getting a Pokemon's evolutions
    //(Esentially converts Pokemon Name into Pokemon ID# so it can be called
    public int getEvolutions(String pokemonName){
        int pokeID;
        Cursor cursor = database.rawQuery("SELECT * FROM POKEMON WHERE POKEMON = '"+pokemonName+"'", null);
        cursor.moveToFirst();
        try{
            return pokeID = cursor.getInt(0);
        } catch (CursorIndexOutOfBoundsException ex) {
            return pokeID = 0;
        }

    }

}
