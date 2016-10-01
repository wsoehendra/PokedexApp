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

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

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
