package com.will.android.pokedexapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public List<pokemonModel> getPokemons() {
        List<pokemonModel> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM POKEMONS", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pokemonModel poke = new pokemonModel();
            poke.setID(cursor.getInt(0));
            poke.setPokemon(cursor.getString(1));
            poke.setHp(cursor.getString(2));
            poke.setAtk(cursor.getString(3));
            poke.setDef(cursor.getString(4));
            poke.setSa(cursor.getString(5));
            poke.setSd(cursor.getString(6));
            poke.setSpd(cursor.getString(7));
            poke.setTypeI(cursor.getString(8));
            poke.setTypeII(cursor.getString(9));
            poke.setAbilityI(cursor.getString(10));
            poke.setAbilityII(cursor.getString(11));
            poke.setNextEv(cursor.getString(12));
            poke.setPrevEv(cursor.getString(13));
            poke.setHeight(cursor.getString(14));
            poke.setWeight(cursor.getString(15));
            list.add(poke);
        }
        cursor.close();
        return list;
    }

    public List<pokemonModel> getQuotes() {
        List<pokemonModel> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ID,POKEMON FROM POKEMONS", null);
        cursor.moveToFirst();
        pokemonModel poke = new pokemonModel();
        while (!cursor.isAfterLast()) {
            poke.setID(cursor.getInt(0));
            poke.setPokemon(cursor.getString(1));
            list.add(poke);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
