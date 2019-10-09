package com.example.mielialakalenteri;


import android.content.Context;
import android.content.SharedPreferences;
import java.time.LocalDate;

/**
 * Sharedpreferenssin luku ja kirjoitin luokka
 * @author Mikael Ylivaara
 */

public class GetterSetter  {

    LocalDate date= LocalDate.now();
    public Context context;


    public GetterSetter(Context context) {
    this.context=context;
    }


    /**
     * Tallennetujen arevojen lukija
     * @param context
     * @return palauttaa tämänpäivän avaimella tallennetun arvon/stringin
     */
    public String getPref(Context context){
        SharedPreferences preferences=context.getSharedPreferences("appdata", Context.MODE_PRIVATE); // tallennettujen arvojen lukeminen
        String save=preferences.getString(String.valueOf(date),"");
        return  save;
    }

    /**
     * Annetun arvon tallentaja
     * @param context
     * @param x tallennettava string joka tallentuu tämänpäivän avaimeen
     */
    public  void setPref(Context context,String x){
        SharedPreferences preferences=context.getSharedPreferences("appdata", Context.MODE_PRIVATE); //talletus arvoille nimellä päivämäärä
        preferences.edit().putString(String.valueOf(LocalDate.now()),x).commit();
    }

}
