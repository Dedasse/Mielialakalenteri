package com.example.mielialakalenteri;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {


    LocalDate date= LocalDate.now();
    SharedPreferences prefs;
    ListView listView;
    List<String> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        prefs=getBaseContext().getSharedPreferences("appdata",MODE_PRIVATE);

        listView=(ListView) findViewById(R.id.listview);
        final ArrayList<String> arrayList=new ArrayList<>();
        List<String>lists=new ArrayList<>();


            Map<String, ?> allEntries = prefs.getAll();
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                Log.d("map values", entry.getKey() + " : " + entry.getValue().toString());
                String list = entry.getKey() + ": " + entry.getValue().toString();
                lists.add(list);
            }


            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1,lists);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setContentView(R.layout.notepad2);
                TextView tx=(TextView)findViewById(R.id.description);
                TextView txt=(TextView)findViewById(R.id.Title);
                String data=adapter.getItem(i).toString();
                String[] sdata=data.split(",",2);
                txt.setText(sdata[0]);
                tx.setText(sdata[1]);
            }
        });

    }


}
