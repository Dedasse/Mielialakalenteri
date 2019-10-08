package com.example.mielialakalenteri;


import android.content.Context;
import android.content.SharedPreferences;

import java.time.LocalDate;

import androidx.appcompat.app.AppCompatActivity;

public class GetterSetter  {

    LocalDate date= LocalDate.now();
    public Context context;


    public GetterSetter(Context context) {
    this.context=context;
    }



    public String getPref(Context context){
        SharedPreferences preferences=context.getSharedPreferences("appdata", Context.MODE_PRIVATE); // tallennettujen arvojen lukeminen
        String save=preferences.getString(String.valueOf(date),"");
        return  save;
    }

    public  void setPref(Context context,String x){
        SharedPreferences preferences=context.getSharedPreferences("appdata", Context.MODE_PRIVATE); //talletus arvoille nimellä päivämäärä
        preferences.edit().putString(String.valueOf(LocalDate.now()),x).commit();
    }

}
