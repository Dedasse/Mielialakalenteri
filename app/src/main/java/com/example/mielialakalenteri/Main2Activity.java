package com.example.mielialakalenteri;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.view.View;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {


    LocalDate date= LocalDate.now();
    SharedPreferences prefs;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        prefs=getPreferences(Context.MODE_PRIVATE);

        listView=(ListView) findViewById(R.id.listview);
        ArrayList<String> arrayList=new ArrayList<>();

        File prefsdir=new File(getApplicationInfo().dataDir,"shared_prefs");
        if(prefsdir.exists() && prefsdir.isDirectory()){
            String[] list = prefsdir.list();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1,list);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            
            String item = (String) listView.getSelectedItem();
            //remove .xml from the file name
         /*   String preffile = item.substring(0, item.length()-4);

            SharedPreferences sp2 = getSharedPreferences(preffile, MODE_PRIVATE);
            Map<String, ?> map = sp2.getAll();

            for (Map.Entry<String, ?> entry : map.entrySet()){
                System.out.println("key is "+ entry.getKey() + " and value is " + entry.getValue());
            }*/



        }

    }


}
