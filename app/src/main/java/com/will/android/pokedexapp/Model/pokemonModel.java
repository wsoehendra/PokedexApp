package com.will.android.pokedexapp.Model;

import java.sql.Blob;

/**
 * Created by wsoeh on 19/09/2016.
 */
public class pokemonModel {

    int ID;
    String pokemon;
    String hp;
    String atk;
    String def;
    String sa;
    String sd;
    String spd;
    String typeI;
    String typeII;
    String abilityI;
    String abilityII;
    String prevEv;
    String nextEv;
    String height;
    String weight;
    int sprite;

    public pokemonModel(){
    }

    public pokemonModel(int ID, String pokemon){
        this.ID = ID;
        this.pokemon = pokemon;
    }

    public pokemonModel(int ID, String pokemon, String hp, String atk, String def, String sa, String sd, String spd, String typeI, String typeII, String abilityI, String abilityII, String prevEv, String nextEv, String height, String weight, int sprite) {
        this.ID = ID;
        this.pokemon = pokemon;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.sa = sa;
        this.sd = sd;
        this.spd = spd;
        this.typeI = typeI;
        this.typeII = typeII;
        this.abilityI = abilityI;
        this.abilityII = abilityII;
        this.prevEv = prevEv;
        this.nextEv = nextEv;
        this.height = height;
        this.weight = weight;
        this.sprite = sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

    public int getSprite() {
        return sprite;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    public void setTypeI(String typeI) {
        this.typeI = typeI;
    }

    public void setTypeII(String typeII) {
        this.typeII = typeII;
    }

    public void setAbilityI(String abilityI) {
        this.abilityI = abilityI;
    }

    public void setAbilityII(String abilityII) {
        this.abilityII = abilityII;
    }

    public void setPrevEv(String prevEv) {
        this.prevEv = prevEv;
    }

    public void setNextEv(String nextEv) {
        this.nextEv = nextEv;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getID() {
        return ID;
    }

    public String getPokemon() {
        return pokemon;
    }

    public String getHp() {
        return hp;
    }

    public String getAtk() {
        return atk;
    }

    public String getDef() {
        return def;
    }

    public String getSa() {
        return sa;
    }

    public String getSd() {
        return sd;
    }

    public String getSpd() {
        return spd;
    }

    public String getTypeI() {
        return typeI;
    }

    public String getTypeII() {
        return typeII;
    }

    public String getAbilityI() {
        return abilityI;
    }

    public String getAbilityII() {
        return abilityII;
    }

    public String getPrevEv() {
        return prevEv;
    }

    public String getNextEv() {
        return nextEv;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        return ID+" "+pokemon;
    }
}
